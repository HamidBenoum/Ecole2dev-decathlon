# Lot 2

Dans ce lot, nous allons ajouter des vues HTML pour gérer les clients et les salles de sport.

Créez une page permettant de lister, ajoutez et supprimez les clients et les salles de sport.

## Pas à pas

Commencez par ajouter la dépendance vers Thymeleaf dans votre `pom.xml` :

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

Créez un fichier `client.html` situé dans `/src/main/resources/templates/` et ajoutez y du contenu.

Créer un nouveau controller et ajoutez-y la méthode qui renvoie votre vue :

```java 
@Controller
@RequestMapping("html/client/")
public class ClientVueController {

    @GetMapping("list")
    public String listClient(){
        return "client"; // nom du fichier html sans l'extension .html
    }
}

```

> Lancez votre application et essayez d'appeler votre vue via l'url <http://localhost:8080/html/client/list>

Nous allons maintenant ajouter des informations dans votre page. Pour cela, nous allons ajouter l'objet `Model` en paramètre de notre méthode et y ajouter une valeur :

```java
@GetMapping("list")
public String listClient(Model model){
    model.addAttribute("name","decathlon");
    return "client";
}
```	

et dans notre HTML :

```html
<html xmlns:th="http://www.thymeleaf.org">
    <head>
        <title>Ecole de dev</title>
    </head>
    <body>
        <span th:text="'Hello '+${name}"></span>
    </body>
</html>
```

> Testez avec une simple valeur

Nous souhaitons afficher la liste des clients, pour cela nous allons simplement ajouter la liste de clients :

```java

@GetMapping("list")
public String listClient(Model model){
    model.addAttribute("clients",clientService.getAll());
    return "client";
}

```

et dans notre template :

```html

<table border="1">
    <thead>
    <td>Id</td>
        <td>Nom</td>
    </thead>
    <tr th:each ="client : ${clients}">
        <td th:text="${client.id}"></td>
        <td th:text="${client.name}"></td>
    </tr>
</table>
```

> Testez

Pour finir, nous allons créer un formulaire pour ajouter un client. Nous allons ajouter dans notre page liste un objet `Client`

```java
model.addAttribute("newClient",new Client());
```

et dans notre html, un formulaire :

```html
<form th:action="@{ajouterClient}"
      th:object="${newClient}" method="POST">
    Name :
    <input type="text" th:field="*{name}" />
    <input type="submit" value="Create" />
</form>
```

Enfin, une méthode pour sauvegarder le client :

```java
@PostMapping(value = {"/ajouterClient"})
public String savePerson(RedirectAttributes model,@ModelAttribute("client") Client client) {
    if(!StringUtils.isEmpty(client.getName())) {
        clientService.create(client);
    } else
    {
        model.addAttribute("errorMessage","Le nom ne peutpas être vide");
    }
    return "redirect:list";
}
```

> Note 1 : dans les paramètres, nous recevons notre client via `@ModelAttribute("client") Client client`, ainsi que `RedirectAttributes model` pour envoyer un message d'erreur si le nom est vide

> Note 2 : j'utilise la méthode `StringUtils.isEmpty(str)` qui provient de String et vérifie à la fois si un String est null ou vide

> Note 3 : je fais une redirection vers ma méthode qui liste les client grâce à `return "redirect:list"` qui va recevoir en paramètre ce que j'ai ajouté dans `RedirectAttributes model`

```java

@GetMapping("list")
public String listClient(Model model, @RequestParam(value ="errorMessage",required = false) String message) {
    model.addAttribute("clients", clientService.getAll());
    model.addAttribute("newClient",new Client());
    if(!StringUtils.isEmpty(message)){
        model.addAttribute("errorMessage",message);
    }
    return "client";
}
@PostMapping(value = {"/ajouterClient"})
public String savePerson(RedirectAttributes model,@ModelAttribute("client") Client client) {
    if(!StringUtils.isEmpty(client.getName())) {
        clientService.create(client);
    } else
    {
        model.addAttribute("errorMessage","Le nom ne peutpas être vide");
    }
    return "redirect:list";
}

```

```html
<table border="1">
        <thead>
            <td>Id</td>
            <td>Nom</td>
        </thead>
        <tr th:each ="client : ${clients}">
            <td th:text="${client.id}"></td>
            <td th:text="${client.name}"></td>
        </tr>
    </table>
    <span th:if="${errorMessage}" th:text="${errorMessage}"style="color:red"></span><br />
    Ajouter un client :<br />
    <form th:action="@{ajouterClient}"
          th:object="${newClient}" method="POST">
        Name :
        <input type="text" th:field="*{name}" />
        <input type="submit" value="Create" />
    </form>
```

Pour finir, créez la méthode permettant de supprimer un client et de rediriger vers la page liste avec un message confirmant la suppression du client.

```html

<table border="1">
    <thead>
        <th>Delete</th>
        <td>Id</td>
        <td>Nom</td>
    </thead>
    <tr th:each ="client : ${clients}">
        <th><a th:href="'delete/'+{client.id">delete</a></th>
        <td th:text="${client.id}"></td>
        <td th:text="${client.name}"></td>
    </tr>
</table>
```
