# Lot 7

Votre PO vous informe que les salles doivent être facturé aux clients de type PRIVEE. Le prix des salles est géré par une autre API. Vous devez implémenter les règles suivantes :

Quand on liste les salles, on veut ajouter l’information du prix
Quand on liste une salle, on veut ajouter l’information du prix
Si l’API ne répond pas, on fixe un prix à 50 Euros par défault

Vous devez donc appeler cette API pour obtenir le prix d’une salle. 

---

## Pas à pas

Récupérez le jar ecolededev-prices et lancer le via la commande :

`java -jar ecolededev-prices.jar `

Testez l’url : <http://localhost:8081/price/1>
> L’application prend comme id, l’id de la salle de sport.


Spring fournit un client Http pour faire des appels à des API : `RestTemplate`

Voici un exemple d’implémentation :

```java
public int getPrice(Long id) {
   RestTemplate restTemplate = new RestTemplate();
   URI uri = null;
   try {
       uri = new URI("http://localhost:8081/price/" + id);
   } catch (URISyntaxException e) {
       log.error("erreur lors de la construction de l'uri");
       return 0;
   }
   ResponseEntity<Integer> response = restTemplate.getForEntity(uri, Integer.class);
   return response.getBody();
}
```