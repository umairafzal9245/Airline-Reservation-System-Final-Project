import BusinessLogic.*;
import org.junit.*;

import java.util.ArrayList;
import java.util.Optional;

public class FlightReservationSystemTest
{
    private FlightReservationSystem object;

    @Before
    public  void Initialize()
    {
        object = new FlightReservationSystem();
    }
    @Test
    public void RegisterCustomerPositive()
    {
        try {
            object.getCustomers().RegisteranAccount("Umair","Male",22,"Dab no 2",12345678,1234,true,5000.0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            object.getCustomers().loginanaccount(12345678,1234);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Customer loggedin = object.getCustomers().getLoggedInCustomer();
        int passport = loggedin.getPassport_number();
        Assert.assertEquals(passport, 12345678);
        Assert.assertEquals(loggedin.getLoginpin(), 1234);

    }
    @Test
    public void RegisterCustomerNegative()
    {
        try {
            object.getCustomers().RegisteranAccount("Umair","Male",22,"Dab no 2",12345678,1234,true,5000.0);
        } catch (Exception e) {
            Assert.assertEquals("Customer already registered",e.getMessage());
        }
        try {
            object.getCustomers().loginanaccount(12345678,123456);
        } catch (Exception e) {
            Assert.assertEquals("Login pin is not correct",e.getMessage());
        }
    }

    @Test
    public void RegisterAdmin()
    {
        try {
            object.getAdmin().registeraccount("Admin", 1234);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        try{
            object.getAdmin().loginanaccount(1234);

        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(true,object.getAdmin().isLogin());
    }
    @Test
    public void LoginAdminNegativeTest(){
        try{

            object.getAdmin().checkpin(1111);
        }catch (Exception e)
        {
            Assert.assertEquals("\n\tLogin pin 1111 is not correct",e.getMessage());
        }
    }
    @Test
    public void addFlightTest(){
        try {
            object.getTotalflights().ChooseandAddFlight("5", "Pakistan", "Austrailia", 40, 2000, "Economy", "RoundTrip", "24-december-2021", "10:30 am", "1st january-2022", "9:00 pm");
        }catch(Exception e)
        {

            System.out.println(e.getMessage());
        }
        try {
            object.getTotalflights().ChooseandAddFlight("6", "Pakistan", "Austrailia", 40, 2000, "Economy", "RoundTrip", "24-december-2021", "10:30 am", "1st january-2022", "9:00 pm");
        }catch(Exception e) {

            System.out.println(e.getMessage());
        }
        Assert.assertEquals(0,object.getTotalflights().searchflight("5"));


    }
    @Test
    public void AddFlightNegativeTest(){
        try {
            object.getTotalflights().ChooseandAddFlight("1", "Pakistan", "Austrailia", 40, 2000, "Economy", "RoundTrip", "24-december-2021", "10:30 am", "1st january-2022", "9:00 pm");
        }catch(Exception e)
        {

            System.out.println(e.getMessage());
        }
        try {
            object.getTotalflights().ChooseandAddFlight("2", "Pakistan", "Austrailia", 40, 2000, "Economy", "RoundTrip", "24-december-2021", "10:30 am", "1st january-2022", "9:00 pm");
        }catch(Exception e)
        {

            System.out.println(e.getMessage());
        }
        try {
            object.getTotalflights().ChooseandAddFlight("1", "Pakistan", "Austrailia", 40, 2000, "Economy", "RoundTrip", "24-december-2021", "10:30 am", "1st january-2022", "9:00 pm");
        }catch(Exception e)
        {

            Assert.assertEquals("\nFlight with this id already present\n",e.getMessage());
        }

        Assert.assertEquals(-1,object.getTotalflights().searchflight("6"));


    }

    @Test
    public void DeleteFlightTest(){
        try {
            object.getTotalflights().ChooseandAddFlight("1", "Pakistan", "Austrailia", 40, 2000, "Economy", "RoundTrip", "24-december-2021", "10:30 am", "1st january-2022", "9:00 pm");
        }catch(Exception e)
        {

            System.out.println(e.getMessage());
        }
        try {
            object.getTotalflights().ChooseandAddFlight("2", "Pakistan", "Austrailia", 40, 2000, "Economy", "RoundTrip", "24-december-2021", "10:30 am", "1st january-2022", "9:00 pm");
        }catch(Exception e)
        {

            System.out.println(e.getMessage());
        }
        try{
            object.getTotalflights().deleteflight("2");

        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(-1,object.getTotalflights().searchflight("2"));
    }

    @Test
    public void DeleteFlightNegativeTest(){
        try{

            object.getTotalflights().deleteflight("1");
        }
        catch (Exception e)
        {
            Assert.assertEquals("Flight list is empty\n",e.getMessage());

        }
        try {
            object.getTotalflights().ChooseandAddFlight("1", "Pakistan", "Austrailia", 40, 2000, "Economy", "RoundTrip", "24-december-2021", "10:30 am", "1st january-2022", "9:00 pm");
        }catch(Exception e)
        {

            System.out.println(e.getMessage());
        }
        try {
            object.getTotalflights().ChooseandAddFlight("2", "Pakistan", "Austrailia", 40, 2000, "Economy", "RoundTrip", "24-december-2021", "10:30 am", "1st january-2022", "9:00 pm");
        }catch(Exception e)
        {

            System.out.println(e.getMessage());
        }
        try{

            object.getTotalflights().deleteflight("3");
        }
        catch (Exception e)
        {
            Assert.assertEquals("Flight with this id not found\n",e.getMessage());

        }



    }
    @Test
    public void BookFlight(){
        try {
            object.getTotalflights().ChooseandAddFlight("1", "Pakistan", "Austrailia", 40, 2000, "Economy", "RoundTrip", "24-december-2021", "10:30 am", "1st january-2022", "9:00 pm");
        }catch(Exception e)
        {

            System.out.println(e.getMessage());
            System.out.println("this???");

        }
        try {
            object.getCustomers().RegisteranAccount("Sameet","Female",21,"Chakwal",12345678,1234,true,5000.0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            object.getTotalflights().searchFlights("Pakistan", "Austrailia", "RoundTrip");
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("this?");

        }
        ArrayList<Integer> seats=new ArrayList<Integer>();
        seats.add(1);
        try {
            object.getTotalflights().bookaflight("1", 1,12345678,seats);
        }
        catch(Exception e)
        {

            System.out.println(e.getMessage());
        }


    }
    @Test
    public void BookFlightNegativeTest() {
        ArrayList<Integer> seats = new ArrayList<Integer>();
        seats.add(1);
        try {
            object.getTotalflights().ChooseandAddFlight("1", "Pakistan", "Austrailia", 4, 2000, "Economy", "RoundTrip", "24-december-2021", "10:30 am", "1st january-2022", "9:00 pm");
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        try {
            object.getCustomers().RegisteranAccount("Sameet", "Female", 21, "Chakwal", 12345678, 1234, true, 5000.0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            object.getCustomers().RegisteranAccount("Umair", "Male", 22, "Mansehra", 11111111, 1111, true, 5000.0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            object.getTotalflights().searchFlights("Pakistan", "Austrailia", "RoundTrip");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("this?");
        }
        try {
            object.getTotalflights().bookaflight("1", 1, 12345678, seats);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            object.getTotalflights().bookaflight("1", 1, 11111111, seats);
        } catch (Exception e) {
            Assert.assertEquals("Seat number 1 is already booked\n", e.getMessage());
        }
    }

}
