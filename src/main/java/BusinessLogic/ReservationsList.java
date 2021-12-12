package BusinessLogic;


import java.util.ArrayList;

public class ReservationsList
{
    private final ArrayList<Reservation> totalreservations;

    ReservationsList()
    {
        totalreservations = new ArrayList<Reservation>();
    }
    public void ReadReservationsFromDatabase() throws NoReservationsFoundException {
        ArrayList<Reservation> data = FlightReservationSystem.database.GetReservation();
        if(data.isEmpty())
        {
            throw new NoReservationsFoundException("No Reservations Present in Database");
        }
        totalreservations.addAll(data);
        System.out.println("Successfully Retreived reservations From Database");
    }
    public int searchreservation(int bookingreference)
    {
        int index = -1;
        for (int i=0;i<totalreservations.size();i++)
        {
            if(totalreservations.get(i).getBookingreference() == bookingreference)
                index = i;
        }
        return index;
    }
    public Reservation getReservation(int bookingref)
    {
        int index = searchreservation(bookingref);
        return totalreservations.get(index);
    }
    public ArrayList<Reservation> GetTickets(Integer passport)
    {
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        for (int i=0;i<totalreservations.size();i++)
        {
            if(totalreservations.get(i).getCustomerPassport().equals(passport))
            {
                reservations.add(totalreservations.get(i));
            }
        }
        return reservations;
    }
    public void addreservation(Reservation object)
    {
        totalreservations.add(object);
        FlightReservationSystem.database.AddReservations(object);
    }
    public String deletereservation(int reference, Integer passport) throws InvalidBookingReferenceException, BookingReferenceNotown {
        int index = searchreservation(reference);
        if(index == -1)
        {
            throw new InvalidBookingReferenceException("Booking reference is not correct\n");
        }
        if(!totalreservations.get(index).getCustomerPassport().equals(passport))
        {
            throw new BookingReferenceNotown("This booking reference not belongs to you\n");
        }
        String flightid = totalreservations.get(index).getFlightid();
        FlightReservationSystem.database.DeleteReservation(totalreservations.get(index));
        totalreservations.remove(index);
        return flightid;
    }

    public ArrayList<Reservation> getTotalreservations() {
        return totalreservations;
    }
}
