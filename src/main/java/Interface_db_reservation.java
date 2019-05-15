import java.util.List;

public interface Interface_db_reservation {
    Reservation get_reservationInfos(Reservation r);
    List<Reservation> get_reservationList();
    void addReservation(Reservation r);
    void deleteReservation(Reservation r);
    void modifyReservation(Reservation r);
}
