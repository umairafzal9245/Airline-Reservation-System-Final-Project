package DatabaseCode;

import BusinessLogic.Customer;
import BusinessLogic.Flight;
import BusinessLogic.Reservation;
import BusinessLogic.Seats;

import java.util.ArrayList;

public class DataBaseHandler
{
    public void AddCustomer(String name,String gender,int age,String address,String passport_number,int loginpin)
    {

    }
    public ArrayList<Customer> GetCustomer()
    {
        return new ArrayList<Customer>();
    }
    public void RemoveCustomer(String name)
    {

    }
    public <T> void AddFlight(T object)
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
    public void DeleteReservation(int bookingreference)
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