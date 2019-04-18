# Lot 2

Implémentez les API qui permettent de gérer les réservations de salles :
* Création 
* Mise à jour
* Suppression

---

Pour gérer une réservation vous aurez besoin de créer une table qui à des clés étrangères vers vos tables `client` et `sportHall`. Cela se représente grâce à l’annotation `@OneToOne`

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
