import BusinessLogic.*;
import org.junit.*;

public class FlightReservationSystemTest
{
    private static FlightReservationSystem object;

    @BeforeClass
    public static void Initialize()
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
}
