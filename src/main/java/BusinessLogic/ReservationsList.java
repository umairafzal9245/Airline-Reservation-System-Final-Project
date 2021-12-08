package BusinessLogic;

import DatabaseCode.DataBaseHandler;
import DatabaseCode.OracleDataBase;

import java.util.ArrayList;

public class ReservationsList
{
    public ArrayList<Reservation> getTotalreservations() {
        return totalreservations;
    }

    public void setTotalreservations(ArrayList<Reservation> totalreservations) {
        this.totalreservations = totalreservations;
    }

    private ArrayList<Reservation> totalreservations;

    ReservationsList()
    {
        FlightReservationSystem.database = new OracleDataBase();
        totalreservations = new ArrayList<Reservation>();
    }
    public void ReadReservationsFromDatabase() throws NoReservationsFoundException {
        ArrayList<Reservation> data = FlightReservationSystem.database.GetReservation();
        if(data.isEmpty())
        {
            throw new NoReservationsFoundException("No Reservations Present in Database");
        }
        for (int i=0;i<data.size();i++)
        {
            totalreservations.add(data.get(i));
        }
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
    public ArrayList<Reservation> GetTickets(String Name)
    {
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        for (int i=0;i<totalreservations.size();i++)
        {
            if(totalreservations.get(i).getCustomername().equalsIgnoreCase(Name))
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
    public void displayticket(String name,FlightCalender totalflights) throws NoTicketFoundException
    {
        if(totalreservations.isEmpty())
        {
            throw new NoTicketFoundException("Tickets List is Empty\n");
        }
        boolean found = false;
        int m = 1;
        for (int i=0;i<totalreservations.size();i++)
        {
            if(totalreservations.get(i).getCustomername().equalsIgnoreCase(name))
            {
                found = true;
                ArrayList<Integer> seatnumbers = totalflights.getSeats(totalreservations.get(i).getFlightid(),name);
                totalreservations.get(i).display(m,seatnumbers);
                m++;
            }
        }
        if (found == false){
            System.out.println("No tickets Present");}
    }
    public String deletereservation(int reference,String name) throws InvalidBookingReferenceException, BookingReferenceNotown {
        int index = searchreservation(reference);
        if(index == -1)
        {
            throw new InvalidBookingReferenceException("Booking reference is not correct\n");
        }
        if(!totalreservations.get(index).getCustomername().equalsIgnoreCase(name))
        {
            throw new BookingReferenceNotown("This booking reference not belongs to you\n");
        }
        String flightid = totalreservations.get(index).getFlightid();
        FlightReservationSystem.database.DeleteReservation(totalreservations.get(index));
        totalreservations.remove(index);
        return flightid;
    }
    public void display(FlightCalender totalflights) throws NoReservationsFoundException, NoTicketFoundException {
        if(totalreservations.isEmpty())
        {
            throw new NoReservationsFoundException("Reservations list is empty\n");
        }
        for (int i=0;i<totalreservations.size();i++)
        {
            ArrayList<Integer> seatnumbers = totalflights.getSeats(totalreservations.get(i).getFlightid(),totalreservations.get(i).getCustomername());
            totalreservations.get(i).display(i+1,seatnumbers);
        }
    }
}
