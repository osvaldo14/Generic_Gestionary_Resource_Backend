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

        //
        db.getReservationList().forEach((reservation -> {
            System.out.println("On est entrain de comparer ..."+ reservation.getName());
            //Check si la réservation qu'on souhaite comparée n'est pas déjà dans la liste de conflits auquel cas pas besoin de la comparer.
            if (this.conflicts.contains(reservation.getID())){
                System.out.println(reservation.getID() + "est deja dans la liste des conflits");
            }else{

                //CONFLICT DETECTION : on ne garde que les réservations problématiques
                tmp.removeIf(r -> {
                    //tester qu'on est pas entrain de comparer deux fois la même réservation
                    if (reservation.getID().equals(r.getID())) {
                        return true;
                    }
                    //On regarde si les dates ne se chevauchent pas.
                    if(     r.getStart().compareTo(reservation.getEnd()) >= 0||
                            r.getEnd().compareTo(reservation.getStart()) <= 0)
                    {
                        System.out.println("les res ne se chevauchent pas");
                        return true;
                    }else {
                        //Si les dates de 2 réservations se chevauchent alors on compare leurs ressources.
                        for (String resourceName : r.getResourceList()){
                            if (reservation.getResourceList().contains(resourceName)){
                                return false;
                            }
                        }
                        return true;
                    }
                });
                if (!tmp.equals(null)) tmp.forEach((n) -> this.conflicts.add(n.getID().toString()));
                System.out.println(reservation.getName()+" est en conflit avec : "+tmp);
            }
        }));
        return new ArrayList<>(this.conflicts);
    }

    private boolean compareResources( Reservation r1, Reservation r2) {
        for (String resourceName : r1.getResourceList()) {
            if (r2.getResourceList().contains(resourceName)){
                return true;
            }
        }
        return false;
    }

    /*
    Retourne false si les dates si les dates ne chevauchent pas.
    Retourne true si les dates se chevauchent.
     */
    private boolean compareDates( Reservation r1, Reservation r2) {
        if (r1.getStart().compareTo(r2.getEnd()) >= 0|| r1.getEnd().compareTo(r2.getStart()) <= 0) {
            return false;
        }else return true;
    }

    public List<String> ConflictDetection(InMemoryDataBase db){
        List<String> emptyList = new ArrayList<>();
        List<Reservation> Reservations = new ArrayList<>(db.getReservationList());

        //S'il n'y a pas de réservation ou qu'une seule nous allons retourner une liste vide.
        if (Reservations.equals(null) || Reservations.size() == 1 ) return emptyList;

        db.getReservationList().forEach((r1 -> {
            //Si l'id de la réservation est déjà présent alors on sait déjà qu'elle représente un conflit
            if (this.conflicts.contains(r1.getID())){
                System.out.println(r1.getID() + "est deja dans la liste des conflits");
            }else{
                Reservations.forEach((r2 -> {
                    if (r1.getID().equals(r2.getID())){
                        System.out.println("On ne compare pas --> r1 = r2");
                    }
                    else {
                        if (compareDates(r1,r2)){
                            if (compareResources(r1,r2)){
                                //Permet d'ajouter r1 aux conflit qu'une seul fois
                                if (this.conflicts.contains(r1.getID())){
                                    this.conflicts.add(r1.getID().toString());
                                    this.conflicts.add(r2.getID().toString());
                                }else {
                                    this.conflicts.add(r2.getID().toString());
                                }
                            }
                        }
                    }

                }));
            }
        }));
        return new ArrayList<>(this.conflicts);
    }


}
