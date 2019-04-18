# Lot 3

Votre client souhaite que vos API respectent certaines bonnes pratiques. 

Pour le moment, quand une ressource n’est pas trouvé, votre application renvoie une erreur `500` au lieu d’une erreur `404`.

>Assurez vous que vous renvoyez bien une erreur 404 lorsque une entité n’est pas trouvé. 

---

## Step 1

Vous pouvez rajouter dans votre controller le paramètre `HttpServletResponse`. Vous aurez ainsi la possible de manipuler la réponse http.

```java
@GetMapping("{id}")
public Client getById(@PathVariable Long id, HttpServletResponse response) {
   Client client=null;
   try{
       client = clientService.getOne(id);
   }catch (EntityNotFoundException e){
       response.setStatus(HttpStatus.NOT_FOUND.value());
   }
   return client;
}
```

> Essayez cette solution

## Step 2

Une solution plus élégante est de créer un `ControllerAdvice` qui réagit à des exceptions (dans notre cas on remarque que hibernate nous lève une exception de type `EntityNotFoundException` . Exemple :

```java
@ControllerAdvice
public class HandleException {

   @ExceptionHandler(EntityNotFoundException.class)
   public ResponseEntity notFound(EntityNotFoundException enfe){

       return new ResponseEntity<>(enfe.getMessage(),HttpStatus.NOT_FOUND);
   }
}
```

> Quels sont les avantages de la solution 2 ?

## Step 3

Ecrirez un test d’intégration pour vérifier que cela fonctionne

Dans notre test, nous souhaitons vérifier que lorsqu’une ressource est trouvé,l’API nous renvoie un code http 200, et lorsque c’est non trouvé, l’application nous renvoie un code http 404.

Nous allons mocker notre service, car nous ne voulons pas le tester, et lui paramètrer des comportements dans la méthode précédé de l’annotation `@Before`

```java
@RunWith(SpringRunner.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTest {

   @Autowired
   private MockMvc mvc;

   @MockBean
   private ClientService clientService;


   @Before
   public void setup() throws Exception {
       //client trouvé
       when(clientService.getOne(1L)).thenReturn(new Client());
       //client non trouvé
       when(clientService.getOne(2L)).thenThrow(new EntityNotFoundException());
   }

   @Test
   public void getClient_should_return_http_200_when_client_is_found() throws Exception {
       mvc.perform(get("/VOTRE_URL"))
               .andExpect(status().isOk());
   }

   @Test
   public void getClient_should_return_http_400_when_client_is_not_found() throws Exception {
       mvc.perform(get("/VOTRE_URL"))
               .andExpect(status().isNotFound());
   }
  
}
```

> Créez des tests d'intégrations pour la recherche de client, de sporthall et de booking