package DatabaseCode;

import BusinessLogic.Customer;
import BusinessLogic.Flight;
import BusinessLogic.Reservation;
import BusinessLogic.Seats;

import java.util.ArrayList;

public class DataBaseHandler
{

    public void AddCustomer(String name, String gender, int age, String address, Integer passport_number, int loginpin,Double bal)
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
    public void CancelSeats(String flightid,Integer customerpassport)
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
