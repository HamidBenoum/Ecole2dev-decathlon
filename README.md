# Lot 1

L'objectif du premier lot et d'apprendre à créer une application.
Pour cela vous devrez générer une application et créer des API qui permettent de créer, mettre à jour, supprimer :
* Une salle
* Un client

Voici la liste des API à implémenter :

| METHOD        | URL           | Note :  |
| ------------- |:-------------:| -----:|
| POST     | /clients/ | Créé un client |
| GET      | /clients/      |   Retourne la liste des clients |
| GET | /clients/{id}      |    Retourne un client |
| DELETE | /clients/{id}      |    Supprime un client |
| PATCH | /clients/{id}      |    Met à jour un client |
| POST | /sporthalls/     |    Créé une salle de sport |
| GET | /sporthalls/     |    Retourne la liste des salles de sports |
| GET | /sporthalls/{id}    |    Retourne une salle de sport |
| DELETE | /sporthalls/{id}    |    Supprime une salle de sport |
| PATCH | /sporthalls/    |    Met à jour une salle de sport |

Les méthodes POST et PATCH nécéssite de passer dans le body de la request la ressource.

---

## Pas à pas

> Initialisation :

La première étape consiste à créer un projet en se rendant sur https://start.spring.io/ 

Vous aurez besoin des dépendances :
* web
* jpa 
* PostgreSQL

Vous aurez également besoin d’ajouter dans le fichier `application.properties` la configuration suivante :

```
spring.datasource.driver-class-name=org.postgresql.Driver
#information de votre base de données
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=ecolededev
spring.datasource.password=password123

#To let hibernate create and update the schema
spring.jpa.hibernate.ddl-auto=update

#To prevent error on the log at startup ( https://github.com/pgjdbc/pgjdbc/issues/1102 )
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
```

> Vérifiez que votre projet se lance.

Vous pouvez rajouter le plugin suivant dans votre `pom.xml` pour obtenir un rapport sur la couverture de code :

```xml
<plugin>
   <groupId>org.jacoco</groupId>
   <artifactId>jacoco-maven-plugin</artifactId>
   <version>0.8.2</version>
   <executions>
       <execution>
           <goals>
               <goal>prepare-agent</goal>
           </goals>
       </execution>
       <!-- attached to Maven test phase -->
       <execution>
           <id>report</id>
           <phase>test</phase>
           <goals>
               <goal>report</goal>
           </goals>
       </execution>
   </executions>
</plugin>
```

> Code

Vous aurez besoin de créer un `@RestController`, pour mapper les requêtes :

```java
@RestController
@RequestMapping("/clients/")
public class ClientController {
```

Le rôle d’un contrôleur se limite à mapper les requêtes, et faire de la validation de données. Vous aurez donc besoin de créer un service pour gérer votre règles de gestion, vos interactions avec la base de données, d’autres API, etc :

```java
@Service
public class ClientService {
```

Enfin, c’est le rôle des classes repository de faire les opérations en base de données. Pour cela il suffit de créer une interface qui implémente JpaRepository :

```java
@Repository
public interface ClientRepository extends JpaRepository<ClientModel,Long> {

}
```

Pour finir, vous avez besoin de représenter votre base de données à travers une classe java. Il s’agit de votre model :

```java

@Table(name = "client")
public class ClientModel {

   @Id
   @GeneratedValue
   private Long id;

   private String name;
}
```

> Comme nous avons configuré hibernate pour qu’il gère le schéma, il va se charger de créer les tables en bases. 

---

## Swagger 

Pour générer automatiquement une documentation de vos API et les tester, vous pouvez installer swagger et swagger-ui qui vont générer automatiquement votre swagger.

Pour cela vous devez ajouter les dépendances suivantes dans votre `pom.xml` :

```xml
<dependency>
   <groupId>io.springfox</groupId>
   <artifactId>springfox-swagger2</artifactId>
   <version>2.9.2</version>
</dependency>

<dependency>
   <groupId>io.springfox</groupId>
   <artifactId>springfox-swagger-ui</artifactId>
   <version>2.9.2</version>
</dependency>
```

et créer la classe de configuration suivante :

```java

@Configuration
@EnableSwagger2
public class SwaggerConfig {
   @Bean
   public Docket api() {
       return new Docket(DocumentationType.SWAGGER_2)
               .select()
               .apis(RequestHandlerSelectors.any())
               .paths(PathSelectors.any())
               .build();
   }
}

```

Votre swagger sera accessible à cette adresse : <http://localhost:8080/swagger-ui.html>

