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
    private DataBaseHandler Database;

    ReservationsList()
    {
        Database = new OracleDataBase();
        totalreservations = new ArrayList<Reservation>();
    }
    public void ReadReservationsFromDatabase() throws NoReservationsFoundException {
        ArrayList<Reservation> data = Database.GetReservation();
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
    public void addreservation(Reservation object)
    {
        totalreservations.add(object);
        Database.AddReservations(object);
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
        totalreservations.remove(index);
        Database.DeleteReservation(reference);
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
