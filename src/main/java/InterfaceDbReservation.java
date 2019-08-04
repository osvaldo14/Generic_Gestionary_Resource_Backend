import java.util.Date;
import java.util.List;

public interface InterfaceDbReservation {
    Reservation getReservationInfos(Reservation r);
    List<Reservation> getReservationList();
    void addReservation(Reservation r);
    void deleteReservation(Reservation r);
    void modifyReservation(Integer id, Date newStart, Date newEnd);
}
