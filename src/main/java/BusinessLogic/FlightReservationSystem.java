package BusinessLogic;

import DatabaseCode.DataBaseHandler;
import DatabaseCode.FileSystem;
import DatabaseCode.OracleDataBase;

import java.util.ArrayList;
import java.util.Random;

public class FlightReservationSystem
{
    private final Admin admin;
    private final CustomerAccounts customers;
    private final FlightCalender totalflights;
    private final ReservationsList reservations;

    public static final DataBaseHandler database = FileSystem.getDb();

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
        Integer passport = customers.getCustomerslist().get(customers.searchcustomerloggedin()).getPassport_number();
        String Flightid = reservations.deletereservation(reference,passport);
        totalflights.cancelseats(Flightid, passport);
    }
    public ArrayList<Reservation> GetReservations()
    {
        Integer passport = customers.getCustomerslist().get(customers.searchcustomerloggedin()).getPassport_number();
        return reservations.GetTickets(passport);
    }
    public Integer BookFlight(String id, Integer numberofpassengers, ArrayList<Integer> seatnumbers,String holdername,String cardnum,String expiry,Integer cvv) throws SeatNumberIncorrectException, NoFlightsFoundException, LessSeatsAvailableException, AlreadyBookedSeatException
    {
            Integer passport = customers.getCustomerslist().get(customers.searchcustomerloggedin()).getPassport_number();
            int totalfares = numberofpassengers * totalflights.getFlightsschedule().get(totalflights.searchflight(id)).getFares();
            String type = totalflights.getFlightsschedule().get(totalflights.searchflight(id)).getClasse();

            totalflights.bookaflight(id, numberofpassengers, passport, seatnumbers);

            Reservation object = new Reservation();
            object.setCustomerPassport(passport);
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
    /*public static void main(String args[]) throws CustomerAlreadyPresentException {

    }*/
}
