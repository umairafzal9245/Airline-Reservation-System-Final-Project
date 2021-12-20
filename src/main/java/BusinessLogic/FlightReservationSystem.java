package BusinessLogic;

import DatabaseCode.DataBaseHandler;
import DatabaseCode.FileSystem;
import DatabaseCode.OracleDataBase;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ForkJoinWorkerThread;

public class FlightReservationSystem
{
    private final Admin admin;
    private final CustomerAccounts customers;
    private final FlightCalender totalflights;
    private final ReservationsList reservations;

    public static final DataBaseHandler database = OracleDataBase.getDb();

    public FlightReservationSystem()
    {
        admin = new Admin();
        customers = new CustomerAccounts();
        totalflights = new FlightCalender();
        reservations = new ReservationsList();
    }
    public void LoadDataFromDatabases()
    {
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        customers.ReadCustomersFromDatabase();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            });
            t.start();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        totalflights.ReadFlightsFromDatabase();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            });
            t.start();
            t.join();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        totalflights.ReadSeatsfromdatabase();
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
            });
            t.start();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        reservations.ReadReservationsFromDatabase();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            });
            t.start();
        }
        catch (Exception e)
        {
           System.out.println(e.getMessage());
        }
    }

    // Refunding Money: Paisy Wapis
    public void CancelReservation(int reference) throws BookingReferenceNotown, InvalidBookingReferenceException
    {
        Integer passport = customers.getCustomerslist().get(customers.searchcustomerloggedin()).getPassport_number();

        final String[] flightid = {new String()};
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    flightid[0] = reservations.deletereservation(reference,passport);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                totalflights.cancelseats(flightid[0], passport);
            }
        });
        t2.start();
    }
    public void CancelReservation(int refe,Integer passport) throws BookingReferenceNotown, InvalidBookingReferenceException {

        final String[] flightid = {new String()};
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    flightid[0] = reservations.deletereservation(refe,passport);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                totalflights.cancelseats(flightid[0], passport);
            }
        });
        t2.start();
    }
    public ArrayList<Reservation> GetReservations()
    {
        Integer passport = customers.getCustomerslist().get(customers.searchcustomerloggedin()).getPassport_number();
        return reservations.GetTickets(passport);
    }
    public Integer BookFlight(String id, Integer numberofpassengers, ArrayList<Integer> seatnumbers,String holdername,String cardnum,String expiry,Integer cvv) throws SeatNumberIncorrectException, NoFlightsFoundException, LessSeatsAvailableException, AlreadyBookedSeatException, LessBalanceException {
            Integer passport = customers.getCustomerslist().get(customers.searchcustomerloggedin()).getPassport_number();
            int totalfares = numberofpassengers * totalflights.getFlightsschedule().get(totalflights.searchflight(id)).getFares();
            String type = totalflights.getFlightsschedule().get(totalflights.searchflight(id)).getClasse();
            Double bal = customers.getLoggedInCustomer().getBalance();
            if(bal < totalfares)
            {
                throw new LessBalanceException("low balance");
            }
            bal = bal - totalfares;
            customers.getLoggedInCustomer().setBalance(bal);

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                    totalflights.bookaflight(id, numberofpassengers, passport, seatnumbers);
                    }
                    catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
            });
            t.start();

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
            object.getTicket().generatebookingdateandtime();
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
