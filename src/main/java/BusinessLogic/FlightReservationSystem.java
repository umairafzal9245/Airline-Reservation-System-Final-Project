package BusinessLogic;

import DatabaseCode.DataBaseHandler;
import DatabaseCode.OracleDataBase;

import java.util.ArrayList;
import java.util.Random;

public class FlightReservationSystem
{
    public Admin getAdmin() {
        return admin;
    }

    public CustomerAccounts getCustomers() {
        return customers;
    }

    public FlightCalender getTotalflights() {
        return totalflights;
    }

    public ReservationsList getReservations() {
        return reservations;
    }

    private final Admin admin;
    private final CustomerAccounts customers;
    private final FlightCalender totalflights;
    private final ReservationsList reservations;

    public static final DataBaseHandler database = new OracleDataBase();

    public FlightReservationSystem()
    {
        admin = new Admin();
        customers = new CustomerAccounts();
        try {
            customers.ReadCustomersFromDatabase();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        totalflights = new FlightCalender();
        try {
            totalflights.ReadFlightsFromDatabase();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        try {
            totalflights.ReadSeatsfromdatabase();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        reservations = new ReservationsList();
        try {
            reservations.ReadReservationsFromDatabase();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void CancelReservation(int reference) throws BookingReferenceNotown, InvalidBookingReferenceException {
        String name = customers.getCustomerslist().get(customers.searchcustomerloggedin()).getName();
        String Flightid = reservations.deletereservation(reference,name);
        totalflights.cancelseats(Flightid, name);
    }
    public ArrayList<Reservation> GetReservations()
    {
        String name = customers.getCustomerslist().get(customers.searchcustomerloggedin()).getName();
        return reservations.GetTickets(name);
    }
    public Integer BookFlight(String id, Integer numberofpassengers, ArrayList<Integer> seatnumbers,String holdername,String cardnum,String expiry,Integer cvv) throws SeatNumberIncorrectException, NoFlightsFoundException, LessSeatsAvailableException, AlreadyBookedSeatException
    {
            boolean flag = false;
            String name = customers.getCustomerslist().get(customers.searchcustomerloggedin()).getName();
            int totalfares = numberofpassengers * totalflights.getFlightsschedule().get(totalflights.searchflight(id)).getFares();
            String type = totalflights.getFlightsschedule().get(totalflights.searchflight(id)).getClasse();

            totalflights.bookaflight(id, numberofpassengers, name, seatnumbers);

            Reservation object = new Reservation();
            object.setCustomername(name);
            object.setFlightid(id);
            Random rnd = new Random();
            int boo = rnd.nextInt(999999);
            object.setBookingreference(boo);
            object.getPayment().addpayment(holdername, cardnum, expiry, cvv);
            object.getPayment().setBookingreference(boo);
            object.getTicket().addticket(numberofpassengers, totalfares, type);
            object.getTicket().setBookingreference(boo);
            reservations.addreservation(object);

            return boo;
    }
    /*public static void main(String args[]) throws CustomerAlreadyPresentException {

    }*/
}
