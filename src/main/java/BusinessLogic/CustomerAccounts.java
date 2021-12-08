package BusinessLogic;

import java.util.ArrayList;

public class CustomerAccounts
{
    private ArrayList<Customer> Customerslist;

    CustomerAccounts()
    {
        Customerslist = new ArrayList<Customer>();
    }
    public void ReadCustomersFromDatabase() throws NoCustomerPresentException
    {
        ArrayList<Customer> data = FlightReservationSystem.database.GetCustomer();
        if(data.isEmpty())
        {
            throw new NoCustomerPresentException("No Customer Record Present in Database");
        }
        Customerslist.addAll(data);
        System.out.println("Successfully Retreived Customers From Database");
    }
    public void RegisteranAccount(String name, String gender, int age, String address, Integer passport_number, int loginpin, Boolean login) throws CustomerAlreadyPresentException
    {
        Customer object = new Customer(name,gender,age,address,passport_number,loginpin,login);
        if(searchCustomer(name) != -1)
        {
            throw new CustomerAlreadyPresentException("\nCustomer already registered\n");
        }
        Customerslist.add(object);
        FlightReservationSystem.database.AddCustomer(name, gender, age, address, passport_number, loginpin);
        System.out.println("\n\tCustomer Registered Succefully!!!");
    }
    public void DeleteCustomer() throws CustomerNameNotFoundException {
        String name = getCustomerslist().get(searchcustomerloggedin()).getName();
        DeleteAccount(name);
    }
    public void DeleteAccount(String name) throws CustomerNameNotFoundException
    {
        int index = searchCustomer(name);
        if(index == -1)
        {
            throw new CustomerNameNotFoundException("\nCustomer not found\n");
        }
        FlightReservationSystem.database.RemoveCustomer(Customerslist.get(index));
        Customerslist.remove(index);
    }
    public int searchCustomer(String name)
    {
        int index = -1;
        for (int i=0;i<Customerslist.size();i++)
        {
            if(Customerslist.get(i).getName().equalsIgnoreCase(name))
            {
                index = i;
                break;
            }
        }
        return index;
    }
    public void loginanaccount(String name, int loginpin) throws PinUnverifiedException, CustomerNameNotFoundException
    {
        int index = searchCustomer(name);
        if(index == -1)
        {
            throw new CustomerNameNotFoundException("\n\tThe customer with this name is not present");
        }
        Customerslist.get(index).checkpin(loginpin);
        if(Customerslist.get(index).isLogin())
        {
            System.out.println("\n\tThe customers succefully logged in ");
        }
    }
    public int searchcustomerloggedin()
    {
        int index = -1;
        for (int i=0;i<Customerslist.size();i++)
        {
            if(Customerslist.get(i).isLogin())
                index = i;
        }
        return index;
    }
    public Customer getLoggedInCustomer()
    {
        for (int i = 0; i < Customerslist.size(); i++) {
            if (Customerslist.get(i).logincheck)
                return Customerslist.get(i);
        }
        return null;
    }
    public boolean checkloginofcustomer()
    {
        for (int i=0;i<Customerslist.size();i++)
        {
            if(Customerslist.get(i).isLogin())
                return true;
        }
        return false;
    }
    public void logoutallcustomer()
    {
        boolean logged = false;
        for (int i=0;i<Customerslist.size();i++)
        {
            if(Customerslist.get(i).isLogin())
            {
                Customerslist.get(i).logincheck = false;
                logged = true;
            }
        }
        if (logged)
        System.out.println("Customer account logged out");
    }
    public void display() throws NoCustomerPresentException
    {
        if(Customerslist.isEmpty())
        {
            throw new NoCustomerPresentException("\n\tCustomers list is empty");
        }
        for (int i =0;i<Customerslist.size();i++)
        {
            Customerslist.get(i).display();
        }
    }
    public ArrayList<Customer> getCustomerslist() {
        return Customerslist;
    }
}
