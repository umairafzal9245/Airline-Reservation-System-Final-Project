package DatabaseCode;

import BusinessLogic.Customer;
import BusinessLogic.Flight;
import BusinessLogic.Reservation;
import BusinessLogic.Seats;
import java.util.ArrayList;

public class FileSystem extends DataBaseHandler
{
    public FileSystem db;
    private FileSystem()
    {

    }

    public FileSystem getDb() {
        if(db == null)
            db = new FileSystem();
        return db;
    }

    public void AddCustomer(String name, String gender, int age, String address, Integer passport_number, int loginpin)
    {

    }
    public ArrayList<Customer> GetCustomer()
    {
        return new ArrayList<Customer>();
    }
    public void RemoveCustomer(Customer object)
    {

    }
    public void AddFlight(Flight object)
    {

    }
    public ArrayList<Flight> GetFlight()
    {
        return new ArrayList<Flight>();
    }
    public <T> void RemoveFlight(T object)
    {

    }
    public void AddReservations(Reservation object)
    {

    }
    public ArrayList<Reservation> GetReservation()
    {
        return new ArrayList<Reservation>();
    }
    public void DeleteReservation(Reservation object)
    {

    }
    public void AddSeats(Seats object)
    {

    }
    public void CancelSeats(String flightid,String customername)
    {

    }
    public void CancelSeats(String flightid)
    {

    }
    public ArrayList<Seats> GetSeat()
    {
        return new ArrayList<Seats>();
    }
}
