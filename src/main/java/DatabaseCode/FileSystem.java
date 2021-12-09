package DatabaseCode;

import BusinessLogic.Customer;
import BusinessLogic.Flight;
import BusinessLogic.Reservation;
import BusinessLogic.Seats;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileSystem extends DataBaseHandler
{
    public static FileSystem db;
    private FileSystem()
    {

    }

    public static FileSystem getDb() {
        if(db == null)
            db = new FileSystem();
        return db;
    }

    public void AddCustomer(String name, String gender, int age, String address, Integer passport_number, int loginpin)
    {
        try {
            FileOutputStream fout = new FileOutputStream("Customers.txt");
            String line = passport_number + "," + address + "," + age + "," + gender + "," + loginpin + "," + name + "\n";
            fout.write(line.getBytes());
            fout.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    Customer tokenizecustomer(String line)
    {
        StringTokenizer tokens = new StringTokenizer(line,",");
        int passport_number = Integer.parseInt(tokens.nextElement().toString());
        String address = tokens.nextElement().toString();
        int age = Integer.parseInt(tokens.nextElement().toString());
        String gender = tokens.nextElement().toString();
        int loginpin = Integer.parseInt(tokens.nextElement().toString());
        String name = tokens.nextElement().toString();
        Customer object = new Customer(name,gender,age,address,passport_number,loginpin,false);
        return object;
    }
    public ArrayList<Customer> GetCustomer()
    {
        ArrayList<Customer> readset = null;
        try {
            readset = new ArrayList<Customer>();
            File f = new File("Customers.txt");
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine = "";
            while ((readLine = b.readLine()) != null) {
                readset.add(tokenizecustomer(readLine));
            }
            b.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return readset;
    }
    public void RemoveCustomer(Customer object)
    {
        ArrayList<Customer> readset = null;
        try {
            readset = new ArrayList<Customer>();
            File f = new File("Customers.txt");
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine = "";
            while ((readLine = b.readLine()) != null) {
                readset.add(tokenizecustomer(readLine));
            }
            b.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        for (int i=0;i<readset.size();i++)
        {
            if(readset.get(i).getPassport_number().equals(object.getPassport_number()))
                readset.remove(i);
        }

        try {
            FileOutputStream fout = new FileOutputStream("Customers.txt");
            for (int i=0;i<readset.size();i++)
            {
                Customer written = readset.get(i);
                String line = written.getPassport_number() + "," + written.getAddress() + "," + written.getAge() + "," + written.getGender() + "," + written.getLoginpin() + "," + written.getName() + "\n";
                fout.write(line.getBytes());
            }
            fout.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void AddFlight(Flight object)
    {

    }
    public ArrayList<Flight> GetFlight()
    {
        return new ArrayList<Flight>();
    }
    public <T> void RemoveFlight(T object)
    {

    }
    public void AddReservations(Reservation object)
    {

    }
    public ArrayList<Reservation> GetReservation()
    {
        return new ArrayList<Reservation>();
    }
    public void DeleteReservation(Reservation object)
    {

    }
    public void AddSeats(Seats object)
    {

    }
    public void CancelSeats(String flightid)
    {

    }
    public ArrayList<Seats> GetSeat()
    {
        return new ArrayList<Seats>();
    }
}
