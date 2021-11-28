package BusinessLogic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FlightReservationSystem
{
    public static Admin admin;
    public static CustomerAccounts customers;
    public static FlightCalender totalflights;
    public static ReservationsList reservations;

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
    private static void Accountsmenu() throws NoCustomerPresentException, PinUnverifiedException, CustomerAlreadyPresentException, CustomerNameNotFoundException {
        /*Delete and modify customer details*/
        System.out.println("1. Login an account");
        System.out.println("2. Register an account");
        System.out.println("3. Display Customers accounts");
        System.out.println("4. Display Admin account");
        System.out.println("5. Delete customer account");
        System.out.println("6. Logout Account");
        System.out.println("7. Exit");
        System.out.print("Enter an option: ");
        Scanner input = new Scanner(System.in);
        int option = input.nextInt();
        input.nextLine();
        if (option == 1)
        {
            if(customers.checkloginofcustomer() || admin.isLogin())
            {
                System.out.println("One Account already logged in logout it First");
                return;
            }
            System.out.print("Enter the type of account (admin,customer): ");
            String type = input.nextLine();
            if (type.equalsIgnoreCase("admin"))
            {
                System.out.print("Enter the Pin: ");
                int pin = input.nextInt();
                admin.loginanaccount(pin);
            }
            else if (type.equalsIgnoreCase("customer"))
            {
                if(customers.getCustomerslist().isEmpty())
                {
                    throw new NoCustomerPresentException("\n\tCustomers list is empty");
                }
                System.out.print("Enter the name: ");
                String name = input.nextLine();
                System.out.print("Enter the Pin: ");
                int pin = input.nextInt();
                customers.loginanaccount(name, pin);
            }
        }
        else if (option == 2)
        {
            System.out.print("Enter the type of account (admin,customer): ");
            String type = input.nextLine();
            if (type.equalsIgnoreCase("admin"))
            {
                if(admin.isLogin())
                {
                    System.out.print("Enter the name: ");
                    String name = input.nextLine();
                    System.out.print("Enter the pin: ");
                    int pin = input.nextInt();
                    admin.registeraccount(name, pin);
                }
                else
                {
                    throw new RuntimeException("You must be login as admin to change details");
                }
            }
            else if (type.equalsIgnoreCase("customer"))
            {
                    if(!(customers.checkloginofcustomer() || admin.isLogin()))
                    {
                        System.out.print("Enter the name: ");
                        String name = input.nextLine();
                        System.out.print("Enter the gender: ");
                        String gender = input.nextLine();
                        System.out.print("Enter the age: ");
                        int age = input.nextInt();
                        System.out.print("Enter the pin: ");
                        int pin = input.nextInt();
                        System.out.print("Enter the address: ");
                        input.nextLine();
                        String address = input.nextLine();
                        System.out.print("Enter the passport number: ");
                        String passport_number = input.nextLine();
                        customers.RegisteranAccount(name, gender, age, address, passport_number, pin);
                    }
                    else
                    {
                        System.out.println("Please logged out first");
                    }
            }
        }
        else if(option == 3)
        {
            if(admin.isLogin())
                customers.display();
            else
            {
                if(customers.checkloginofcustomer())
                {
                    customers.diplaylogincustomer();
                }
                else
                {
                    System.out.println("Please login first to display details");
                }
            }
        }
        else if(option == 4)
        {
            if(admin.isLogin())
                admin.display();
            else
                System.out.println("You must be login as admin\n");
        }
        else if(option == 5)
        {
            if (customers.checkloginofcustomer()) {
                String name = customers.getCustomerslist().get(customers.searchcustomerloggedin()).getName();
                customers.DeleteAccount(name);
            }
            else
                System.out.println("No account logged in first login account");
        }
        else if(option == 6)
        {
            if(admin.isLogin() || customers.checkloginofcustomer()){
                customers.logoutallcustomer();
                admin.logout();}
            else
            {
                System.out.println("No account logged in first login account");
            }
        }
        else if(option == 7)
        {
            return;
        }
    }
    private static void Flightsschedulemenu() throws NoFlightsFoundException, FlightsDuplicateFoundException, FlightIDIncorrectException {
        if(admin.isLogin())
        {
            System.out.println("1. Add a flight");
            System.out.println("2. Delete a flight");
            System.out.println("3. Display all flights");
            System.out.println("4. Exit");
            System.out.print("Enter an option: ");
            Scanner input = new Scanner(System.in);
            int option = input.nextInt();
            input.nextLine();
            if (option == 1)
            {
                System.out.print("Enter the id: ");
                String id = input.nextLine();
                System.out.print("Enter the origin: ");
                String origin = input.nextLine();
                System.out.print("Enter the destination: ");
                String destination = input.nextLine();
                System.out.print("Enter the Capacity: ");
                int capacity = input.nextInt();
                while (capacity < 5) {
                    System.out.println("Number of seats or capacity cannot be less than five");
                    System.out.print("Enter the capacity: ");
                    capacity = input.nextInt();
                }
                System.out.print("Enter the fares: ");
                int fare = input.nextInt();
                input.nextLine();
                System.out.print("Enter the class of flight (business,economy,first): ");
                String classe = input.nextLine();
                System.out.print("Enter the Departure Date (day/month/year): ");
                String dept_date = input.nextLine();
                System.out.print("Enter the Departure Time (hour:minutes) AM/PM: ");
                String dept_time = input.nextLine();
                System.out.print("Enter the type of flight (oneWay,RoundTrip): ");
                String type = input.nextLine();

                while(!(type.equalsIgnoreCase("oneway") || type.equalsIgnoreCase("roundtrip")))
                {
                    System.out.print("Enter the correct type (Oneway, Roundtrip): ");
                    type = input.nextLine();
                }

                Flight newFlight = new Flight();

                if(type.equalsIgnoreCase("oneway"))
                    newFlight = new OneWayFlight(id,origin,destination,capacity,dept_date,dept_time,fare,classe);

                else if(type.equalsIgnoreCase("roundtrip")) {
                    System.out.print("Enter the Arrival Date (day/month/year): ");
                    String arr_date = input.nextLine();
                    System.out.print("Enter the Arrival Time (hour:minutes) AM/PM: ");
                    String arr_time = input.nextLine();
                    newFlight = new TwoWayFlight(id,origin,destination,capacity,dept_date,dept_time,arr_date,arr_time,fare,classe);
                }
                totalflights.AddFlight(newFlight);
            } else if (option == 2) {
                System.out.print("Enter the flight id: ");
                String id = input.nextLine();
                totalflights.deleteflight(id);
            } else if (option == 3) {
                totalflights.display();
            }
            else if (option == 4) {
                return;
            }
        }
        else
        {
            System.out.println("Please login as administrator to manage flight schedules");
        }
    }
    private static void Reservationsmenu() throws SeatNumberIncorrectException, NoFlightsFoundException, LessSeatsAvailableException, AlreadyBookedSeatException, NoTicketFoundException, InvalidBookingReferenceException, NoReservationsFoundException, BookingReferenceNotown {
        if(customers.checkloginofcustomer() || admin.isLogin())
        {
            System.out.println("1. Book a ticket");
            System.out.println("2. Search for flights");
            System.out.println("3. Print ticket");
            System.out.println("4. Cancel Reservation");
            System.out.println("5. Print Reservations");
            System.out.println("6. Exit");
            System.out.print("Enter an option: ");
            Scanner input = new Scanner(System.in);
            int option = input.nextInt();
            if(customers.checkloginofcustomer()){
                if(option == 5) {
                    System.out.println("Please login as adminstrator to print all reservations");
                    return;
                }
            }
            if(admin.isLogin())
            {
                if (option != 5)
                {
                    System.out.println("Please login as customer");
                    return;
                }
            }
            if (option == 1) {
                input.nextLine();
                System.out.print("Enter the origin: ");
                String origin = input.nextLine();
                System.out.print("Enter the destination: ");
                String destination = input.nextLine();
                System.out.print("Enter the type of flight (oneWay,RoundTrip): ");
                String typeflight = input.nextLine();
                while(!(typeflight.equalsIgnoreCase("oneway") || typeflight.equalsIgnoreCase("roundtrip")))
                {
                    System.out.print("Enter the correct type (Oneway, Roundtrip): ");
                    typeflight = input.nextLine();
                }
                if (totalflights.searchFlights(origin, destination,typeflight))
                {
                    System.out.print("Enter the flight Id: ");
                    String id = input.nextLine();
                    String name = customers.getCustomerslist().get(customers.searchcustomerloggedin()).getName();
                    System.out.print("Enter the number of passengers: ");
                    int numberofpassengers = input.nextInt();
                    input.nextLine();
                    int totalfares = numberofpassengers * totalflights.getFlightsschedule().get(totalflights.searchflight(id)).getFares();
                    String type = totalflights.getFlightsschedule().get(totalflights.searchflight(id)).getClasse();
                    ArrayList<Integer> seatnumbers = new ArrayList<Integer>();
                    for (int i=0;i<numberofpassengers;i++)
                    {
                        System.out.print("Enter the seat Number for " + (i + 1) + " passenger: ");
                        int seatnumber = input.nextInt();
                        seatnumbers.add(seatnumber);
                    }
                    if (totalflights.bookaflight(id, numberofpassengers, name,seatnumbers))
                    {
                        Reservation object = new Reservation();
                        object.setCustomername(name);
                        object.setFlightid(id);
                        System.out.print("Enter the cardholder name: ");
                        String holdername = input.nextLine();
                        System.out.print("Enter the card number: ");
                        String cardnum = input.nextLine();
                        System.out.print("Enter the expiry date: ");
                        String expiry = input.nextLine();
                        System.out.print("Enter the CVV: ");
                        int cvv = input.nextInt();
                        Random rnd = new Random();
                        object.setBookingreference(rnd.nextInt(999999));
                        object.getPayment().addpayment(holdername, cardnum, expiry, cvv);
                        object.getTicket().addticket(numberofpassengers, totalfares, type);
                        object.display(1,seatnumbers);
                        reservations.addreservation(object);
                        System.out.println("The seats are booked!!!");
                    }
                }
            } else if (option == 2) {
                input.nextLine();
                System.out.print("Enter the origin: ");
                String origin = input.nextLine();
                System.out.print("Enter the destination: ");
                String destination = input.nextLine();
                System.out.print("Enter the Type of flight(oneway,roundtrip): ");
                String Type = input.nextLine();
                totalflights.searchFlights(origin, destination,Type);
            } else if (option == 3) {
                String name = customers.getCustomerslist().get(customers.searchcustomerloggedin()).getName();
                reservations.displayticket(name,totalflights);
            } else if (option == 4) {
                System.out.print("Enter the booking reference: ");
                int reference = input.nextInt();
                String name = customers.getCustomerslist().get(customers.searchcustomerloggedin()).getName();
                String Flightid = reservations.deletereservation(reference,name);
                totalflights.cancelseats(Flightid, name);
            } else if (option == 5) {
                reservations.display(totalflights);
            } else if (option == 6) {
                return;
            }
        }
        else
        {
            System.out.println("Please login account first to manage reservations");
        }
    }
    private static String Menu() throws NoFlightsFoundException, NoCustomerPresentException, PinUnverifiedException, CustomerAlreadyPresentException, CustomerNameNotFoundException, FlightsDuplicateFoundException, FlightIDIncorrectException, SeatNumberIncorrectException, LessSeatsAvailableException, AlreadyBookedSeatException, NoTicketFoundException, NoReservationsFoundException, InvalidBookingReferenceException, IOException, BookingReferenceNotown {
            System.out.println("1. Manage Accounts.");
            System.out.println("2. Manage Flights Schedule.");
            System.out.println("3. Manage Reservations.");
            System.out.println("4. Exit.");
            System.out.print("Enter an option: ");
            Scanner input = new Scanner(System.in);
            int option = input.nextInt();
                if(option == 1)
                {
                    Accountsmenu();
                }
                else if(option == 2)
                {
                    Flightsschedulemenu();
                }
                else if(option == 3)
                {
                    Reservationsmenu();
                }
                else if (option == 4) {
                    return "break";
                }
                return "";
    }
    public static void main(String [] args)
    {
        FlightReservationSystem object = new FlightReservationSystem();
        while(true) {
            try {
                String status = Menu();
                if (status.equals("break"))
                    return;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
