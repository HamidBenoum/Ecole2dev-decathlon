# Lot 3

Implémentez les API qui permettent de gérer les réservations de salles :
* Création 
* Mise à jour
* Suppression

Voici la liste des API à implémenter :

| METHOD        | URL           | Note :  |
| ------------- |:-------------:| -----:|
| POST     | /bookings/ | Créé une réservation |
| GET      | /bookings/      |   Retourne la liste des réservations |
| GET | /booking/{id}      |    Retourne une réservation |

L'utilisateur de l'API connait l'id du client et de la salle de sport. Pour créer une réservation il passe dans le body de la request le document json suivant :

```json
{
	"idSportHall":"2",
	"idClient":"3",
	"start":"2018-12-12T12:00:00",
	"end":"2018-12-12T14:00:00"
}
```

---

Pour gérer une réservation vous aurez besoin de créer une table qui à des clés étrangères vers vos tables `client` et `sportHall`. Cela se représente grâce à l’annotation `@OneToOne`

Exemple :

```java
@OneToOne
private ClientModel clientModel;
```

---

Il existe une librairie qui permet de générer automatiquement les getters / setters (entre autres). Vous pouvez l’installer simplement dans votre projet : 

```xml
<dependency>
   <groupId>org.projectlombok</groupId>
   <artifactId>lombok</artifactId>
   <optional>true</optional>
</dependency>
```

https://projectlombok.org/
