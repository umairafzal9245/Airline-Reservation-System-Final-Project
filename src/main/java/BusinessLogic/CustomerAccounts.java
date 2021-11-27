package BusinessLogic;

import DatabaseCode.DataBaseHandler;
import DatabaseCode.OracleDataBase;

import java.util.ArrayList;

public class CustomerAccounts
{
    public ArrayList<Customer> getCustomerslist() {
        return Customerslist;
    }

    public void setCustomerslist(ArrayList<Customer> customerslist) {
        Customerslist = customerslist;
    }

    private ArrayList<Customer> Customerslist;
    private DataBaseHandler Database;
    CustomerAccounts()
    {
        Customerslist = new ArrayList<Customer>();
        Database = new OracleDataBase();
    }
    public void ReadCustomersFromDatabase() throws NoCustomerPresentException
    {
        ArrayList<Customer> data = Database.GetCustomer();
        if(data.isEmpty())
        {
            throw new NoCustomerPresentException("No Customer Record Present in Database");
        }
        for (int i=0;i<data.size();i++)
        {
            Customerslist.add(data.get(i));
        }
        System.out.println("Successfully Retreived Customers From Database");
    }
    public boolean RegisteranAccount(String name, String gender, int age, String address, String passport_number, int loginpin) throws CustomerAlreadyPresentException
    {
        Customer object = new Customer(name,gender,age,address,passport_number,loginpin);
        if(searchCustomer(name) != -1)
        {
            throw new CustomerAlreadyPresentException("\nCustomer already registered\n");
        }
        Customerslist.add(object);
        Database.AddCustomer(name, gender, age, address, passport_number, loginpin);
        System.out.println("\n\tCustomer Registered Succefully!!!");
        return true;
    }
    public void DeleteAccount(String name) throws CustomerNameNotFoundException
    {
        int index = searchCustomer(name);
        if(index == -1)
        {
            throw new CustomerNameNotFoundException("\nCustomer not found\n");
        }
        Customerslist.remove(index);
        Database.RemoveCustomer(name);
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
    public boolean loginanaccount(String name,int loginpin) throws PinUnverifiedException, CustomerNameNotFoundException
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
        return true;
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
    public void diplaylogincustomer()
    {
        for (int i = 0; i < Customerslist.size(); i++) {
            if (Customerslist.get(i).logincheck)
                Customerslist.get(i).display();
        }
    }
}
