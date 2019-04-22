# Lot 5

L'objectif de ce lot et d'implémenter plusieurs règles de gestion.

* Un client peut émettre un souhait de réservation pour une salle. Dans ce cas, la réservation à un status : EN_ATTENTE_DE_VALIDATION.

> Utilisez un Enum dans votre model pour gérer les status

* Un admin peut récupérer la liste des réservations qui sont dans le status : EN_ATTENTE_DE_VALIDATION

> Votre api aura comme chemin : /booking/status/{status}
 
* Un admin peut valider un souhait. Dans ce cas la réservation passe en status : VALIDE

> Votre api aura comme chemin : /booking/{id}/validate

* Si un client émet un souhait de réservation pour une salle qui n’est plus disponible, le souhait de réservation est enregistré avec le status CONFLIT, et l’api renvoie un code http 409

--- 

La dernière règle de gestion est plus compliqué à implémenter. En effet vous devez :

* Récupérer la liste des réservations futur à la date de réservation souhaité pour une salle de sport

> Pour cela vous allez devoir créer une requête dans votre classe BookingRepository 

```java
    @Query("select b from BookingModel b where ..."
    List<BookingModel> findByStartingDate(LocalDateTime startingDate,Long id);
```

* Vérifier si il n'y a pas de conflit

* Si il n'y a pas de conflit sauvegarder la réservation, sinon la sauvergarder avec un status CONFLIT et renvoyer une erreur 409 (utilisez ce que l'on a vu précédément avec les exceptions)

Pour vérifier si une salle est disponible sur un slot (date de début, date de fin), implémentez l'interface suivante :

```java
public interface SlotService {

    /**
     * This method take a slot and a list of slot and return true if the slot is available
     *
     * @param slotList
     * @param slot
     * @return true if the slot is available
     */
    boolean isAvailable(List<Slot> slotList, Slot slot);

    /**
     * This method will check if the slot is correct
     * A correct slot have a @Slot.start > @Slot.end
     *
     * @param slot
     * @return
     */
    boolean isCorrectSlot(Slot slot);

}
```

Voici la classe Slot :

```java
@Data
@Builder
public class Slot {

    String id;

    /**
     * The beggining of the booking
     */
    LocalDateTime start;

    /**
     * The end of the booking
     */
    LocalDateTime end;
}
```

Je vous propose de procéder en écrivant d'abord vos tests avant l'implémentation.

Voici la liste des cas à tester :

* slot_isNotAvailable_when_inside_a_slot
* slot_isNotAvailable_when_start_inside_a_slot
* slot_isNotAvailable_when_end_inside_a_slot
* slot_isNotAvailable_when_slot_start_before_and_end_after
* slot_isNotAvailable_when_already_exist
* slot_isAvailable_when_before_a_slot
* slot_isAvailable_when_after_a_slot
* slot_isAvailable_when_start_when_a_slot_end
* slot_isAvailable_when_end_when_a_slot_start
* incorrect_slot_start_before_end

La méthodologie Test Driven Development consiste à
* écrire votre test
* vérifier si il échoue
* écrire l'implémentation
* vérifier qu'il passe

En procédant test par test, vous serez sur que votre code fonctionne, et de ne pas avoir écrit plus de code que nécéssaire.

> Utilisez les méthodes LocalDateTime#isAfter et LocalDateTime#isBefore pour comparer les dates

