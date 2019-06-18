import java.util.ArrayList;
import java.util.List;

public class ConflictHandler {
    private List<String> conflicts = new ArrayList<>();

    public ConflictHandler(){}

    public List<String> naiveConflictDetection(InMemoryDataBase db){
        List<String> l = new ArrayList<>();
        List<Reservation> tmp = new ArrayList<>(db.getReservationList());

        //Si il n'y a pas de réservation nous allons retourner une liste vide.
        if (tmp.equals(null) || tmp.size() == 1 ) return l;

        //Nous imprimons toutes les réservations enregistrées dans la dbb.
        tmp.forEach((n) -> System.out.println("Liste de toutes les réservation de la bdd "+n.getName()));

        //
        db.getReservationList().forEach((reservation -> {
            System.out.println("On est entrain de comparer ..."+ reservation.getName());
            //Check si la réservation qu'on souhaite comparée n'est pas déjà dans la liste de conflits auquel cas pas besoin de la comparer.
            if (this.conflicts.contains(reservation.getName())){
                System.out.println(reservation.getName() + "est deja dans la liste des conflits");
            }else{

                //CONFLICT DETECTION : on ne garde que les réservations problématiques
                tmp.removeIf(r -> {
                    //On regarde si les dates ne se chevauchent pas.
                    if(     r.getStart().compareTo(reservation.getEnd()) >= 0||
                            r.getEnd().compareTo(reservation.getStart()) <= 0)
                    {
                        return true;
                    }else {
                        //Si les dates de 2 réservations se chevauchent alors on compare leurs ressources.
                        for (String resourceName : r.getResourceList()){
                            for (String te : reservation.getResourceList()){
                                if(te.equals(resourceName)){
                                    System.out.println(te + " ... " + resourceName);
                                    return false;
                                }
                            }
                        }
                        return true;
                    }
                });
                if (!tmp.equals(null)) tmp.forEach((n) -> this.conflicts.add(n.getName()));
            }
        }));
        return new ArrayList<>(this.conflicts);
    }

}
