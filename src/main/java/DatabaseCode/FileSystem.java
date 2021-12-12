package DatabaseCode;

import BusinessLogic.*;

import javax.persistence.Query;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class FileSystem extends DataBaseHandler
{
    boolean flag = true;
    boolean flightflag1 = true;
    boolean flightflag2 = true;
    boolean Reservationflag = true;
    boolean Seatsflag = true;

    public static FileSystem db;

    private FileSystem()
    {

    }

    public static FileSystem getDb() {
        if(db == null)
            db = new FileSystem();
        return db;
    }

    public void AddCustomer(String name, String gender, int age, String address, Integer passport_number, int loginpin,Double bal)
    {
        try {
            FileOutputStream fout = new FileOutputStream("Customers.txt",flag);
            String line = passport_number + "," + address + "," + age + "," + gender + "," + loginpin + "," + name + "," + bal + "\n";
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
        Double bal = Double.parseDouble(tokens.nextElement().toString());
        Customer object = new Customer(name,gender,age,address,passport_number,loginpin,false,bal);
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
        if(readset.size() == 0)
        {
            try {
                FileOutputStream fout = new FileOutputStream("Customers.txt",false);
                fout.write("".getBytes());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else {
            flag = false;
            try {
                for (int i = 0; i < readset.size(); i++) {
                    Customer written = readset.get(i);
                    AddCustomer(written.getName(), written.getGender(), written.getAge(), written.getAddress(), written.getPassport_number(), written.getLoginpin(), written.getBalance());
                    flag = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void AddFlight(Flight object)
    {
        FileOutputStream fout = null;
        if(object instanceof OneWayFlight)
        {
        try {
            fout = new FileOutputStream("OneWayFlight.txt",flightflag1);
            String line = object.getId() + "," + object.getDestination() + "," + object.getCapacity() + "," + object.getClasse() + "," + object.getFares() + "," + object.getOrigin() + "," + object.getDeparture_date() + "," + object.getDeparture_time() + "\n";
            fout.write(line.getBytes());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        }
        else if(object instanceof TwoWayFlight)
        {
            try {
                fout = new FileOutputStream("TwoWayFlight.txt",flightflag2);
                String line = object.getId() + "," + object.getDestination() + "," + object.getCapacity() + "," + object.getClasse() + "," + object.getFares() + "," + object.getOrigin() + "," + object.getArrivalDate() + "," + object.getArrivalTime() +"," + object.getDeparture_date() + "," + object.getDeparture_time() + "\n";
                fout.write(line.getBytes());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    public ArrayList<Flight> GetFlight()
    {
        ArrayList<Flight> readset = new ArrayList<>();
        try {
            File f = new File("OneWayFlight.txt");
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine = "";
            while ((readLine = b.readLine()) != null) {
                readset.add(tokenizeoneway(readLine));
            }
            b.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try {
            File f = new File("TwoWayFlight.txt");
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine = "";
            while ((readLine = b.readLine()) != null) {
                readset.add(tokenizetwoway(readLine));
            }
            b.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return readset;
    }
    public Flight tokenizeoneway(String line)
    {
        Flight oneway = new OneWayFlight();
        try {
            StringTokenizer tokens = new StringTokenizer(line, ",");
            String id = tokens.nextElement().toString();
            String dest = tokens.nextElement().toString();
            Integer cap = Integer.parseInt(tokens.nextElement().toString());
            String cl = tokens.nextElement().toString();
            Integer far = Integer.parseInt(tokens.nextElement().toString());
            String orgg = tokens.nextElement().toString();
            String depdate = tokens.nextElement().toString();
            String deptime = tokens.nextElement().toString();
            oneway.setId(id);
            oneway.setDestination(dest);
            oneway.setCapacity(cap);
            oneway.setClasse(cl);
            oneway.setFares(far);
            oneway.setOrigin(orgg);
            oneway.setDeparture_date(depdate);
            oneway.setDeparture_time(deptime);
            oneway.setSeatsId(id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return oneway;
    }
    public Flight tokenizetwoway(String line)
    {
        Flight twoway = new TwoWayFlight();
        try {
            StringTokenizer tokens = new StringTokenizer(line, ",");
            String id = tokens.nextElement().toString();
            String dest = tokens.nextElement().toString();
            Integer cap = Integer.parseInt(tokens.nextElement().toString());
            String cl = tokens.nextElement().toString();
            Integer far = Integer.parseInt(tokens.nextElement().toString());
            String orgg = tokens.nextElement().toString();
            String depdate = tokens.nextElement().toString();
            String deptime = tokens.nextElement().toString();
            String arrdate = tokens.nextElement().toString();
            String arrtime = tokens.nextElement().toString();
            twoway.setId(id);
            twoway.setDestination(dest);
            twoway.setCapacity(cap);
            twoway.setClasse(cl);
            twoway.setFares(far);
            twoway.setOrigin(orgg);
            twoway.setDeparture_date(depdate);
            twoway.setDeparture_time(deptime);
            twoway.setArrivalTime(arrtime);
            twoway.setArrivalDate(arrdate);
            twoway.setSeatsId(id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return twoway;
    }
    public <T> void RemoveFlight(T object)
    {
        ArrayList<Flight> oneway = new ArrayList<>();
        ArrayList<Flight> twoway = new ArrayList<>();
        try {
            File f = new File("OneWayFlight.txt");
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine = "";
            while ((readLine = b.readLine()) != null) {
                oneway.add(tokenizeoneway(readLine));
            }
            b.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try {
            File f = new File("TwoWayFlight.txt");
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine = "";
            while ((readLine = b.readLine()) != null) {
                twoway.add(tokenizetwoway(readLine));
            }
            b.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if(object instanceof OneWayFlight)
        {
            for (int i=0;i< oneway.size();i++)
            {
                if(oneway.get(i).getId().equalsIgnoreCase(((OneWayFlight) object).getId()))
                    oneway.remove(i);
            }
            if(oneway.size() == 0)
            {
                try {
                    FileOutputStream fout = new FileOutputStream("OneWayFlight.txt", false);
                    fout.write("".getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                flightflag1 = false;
                for (int i = 0; i < oneway.size(); i++) {
                    AddFlight(oneway.get(i));
                    flightflag1 = true;
                }
            }
        }
        else if(object instanceof TwoWayFlight)
        {
            for (int i=0;i< twoway.size();i++)
            {
                if(twoway.get(i).getId().equalsIgnoreCase(((TwoWayFlight) object).getId()))
                    twoway.remove(i);
            }
            if(twoway.size() == 0)
            {
                try {
                    FileOutputStream fout2 = new FileOutputStream("TwoWayFlight.txt", false);
                    fout2.write("".getBytes());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else {
                flightflag2 = false;
                for (int i = 0; i < twoway.size(); i++) {
                    AddFlight(twoway.get(i));
                    flightflag2 = true;
                }
            }
        }
    }
    public void AddReservations(Reservation object)
    {
        try {
            FileOutputStream fout = new FileOutputStream("Reservations.txt",Reservationflag);
            String line = object.getBookingreference() + "," + object.getCustomerPassport() + "," + object.getFlightid() + "\n";
            fout.write(line.getBytes());
            fout.close();
            fout = new FileOutputStream("Payments.txt",Reservationflag);
            line = object.getPayment().getCardholdername() + "," + object.getPayment().getCardnumber() + "," + object.getPayment().getCvv() + "," + object.getPayment().getExpirydate() + "\n";
            fout.write(line.getBytes());
            fout.close();
            fout = new FileOutputStream("Tickets.txt",Reservationflag);
            line = object.getTicket().getNumberofpassengers() + "," + object.getTicket().getTotalfares() + "," + object.getTicket().getType() + "," + object.getTicket().getBookingdateandtime() + "\n";
            fout.write(line.getBytes());
            fout.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public ArrayList<Reservation> GetReservation()
    {
        ArrayList<Reservation> readset = new ArrayList<>();
        try {
            File f = new File("Reservations.txt");
            File f1 = new File("Payments.txt");
            File f2 = new File("Tickets.txt");
            BufferedReader b = new BufferedReader(new FileReader(f));
            BufferedReader c = new BufferedReader(new FileReader(f1));
            BufferedReader d = new BufferedReader(new FileReader(f2));
            String readres = "";
            String readpay = "";
            String readtick = "";
            while ((readres = b.readLine()) != null && (readpay = c.readLine()) != null && (readtick = d.readLine()) != null) {
                readset.add(tokenizereservation(readres,readpay,readtick));
            }
            b.close();
            c.close();
            d.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return readset;
    }
    public Reservation tokenizereservation(String reser,String pay,String tick)
    {
        Reservation object = new Reservation();
        try
        {
            StringTokenizer tokens = new StringTokenizer(reser, ",");
            String br = tokens.nextElement().toString();
            object.setBookingreference(Integer.parseInt(br));
            String cp = tokens.nextElement().toString();
            object.setCustomerPassport(Integer.parseInt(cp));
            String flid = tokens.nextElement().toString();
            object.setFlightid(flid);
            StringTokenizer tokens2 = new StringTokenizer(pay, ",");
            String cardname = tokens2.nextElement().toString();
            String cardnum = tokens2.nextElement().toString();
            Integer cv = Integer.parseInt(tokens2.nextElement().toString());
            String exp = tokens2.nextElement().toString();
            object.getPayment().addpayment(cardname,cardnum,exp,cv);
            StringTokenizer tokens3 = new StringTokenizer(tick, ",");
            Integer pass = Integer.parseInt(tokens3.nextElement().toString());
            Integer far = Integer.parseInt(tokens3.nextElement().toString());
            String cl = tokens3.nextElement().toString();
            String dt = tokens3.nextElement().toString();
            object.getTicket().addticket(pass,far,cl);
            object.getTicket().setBookingdateandtime(dt);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return object;
    }
    public void DeleteReservation(Reservation object)
    {
        ArrayList<Reservation> readset = new ArrayList<>();
        try {
            File f = new File("Reservations.txt");
            File f1 = new File("Payments.txt");
            File f2 = new File("Tickets.txt");
            BufferedReader b = new BufferedReader(new FileReader(f));
            BufferedReader c = new BufferedReader(new FileReader(f1));
            BufferedReader d = new BufferedReader(new FileReader(f2));
            String readres = "";
            String readpay = "";
            String readtick = "";
            while ((readres = b.readLine()) != null && (readpay = c.readLine()) != null && (readtick = d.readLine()) != null) {
                readset.add(tokenizereservation(readres,readpay,readtick));
            }
            b.close();
            c.close();
            d.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        for (int i=0;i< readset.size();i++)
        {
            if(readset.get(i).getBookingreference() == object.getBookingreference())
            {
                readset.remove(i);
            }
        }
        if(readset.size() == 0)
        {
            try {
                FileOutputStream fout = new FileOutputStream("Reservations.txt", false);
                FileOutputStream fout1 = new FileOutputStream("Payments.txt", false);
                FileOutputStream fout2 = new FileOutputStream("Tickets.txt", false);
                fout.write("".getBytes());
                fout1.write("".getBytes());
                fout2.write("".getBytes());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else {
            Reservationflag = false;
            for (int i = 0; i < readset.size(); i++) {
                AddReservations(readset.get(i));
                Reservationflag = true;
            }
        }
    }
    public void AddSeats(Seats object)
    {
        try {
            FileOutputStream fout = new FileOutputStream("Seats.txt",Seatsflag);
            String line = object.getCustomerpassport() + "," + object.getFlightid() + "," + object.getNumber() + "," + object.getStatus() + "\n";
            fout.write(line.getBytes());
            fout.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void CancelSeats(String flightid,Integer customerpassport)
    {
        ArrayList<Seats> readset = new ArrayList<>();
        try {
            File f = new File("Seats.txt");
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine = "";
            while ((readLine = b.readLine()) != null) {
                readset.add(tokenizeseats(readLine));
            }
            b.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        for (Iterator<Seats> iter = readset.iterator();iter.hasNext();)
        {
            Seats element = iter.next();
            if(element.getFlightid().equalsIgnoreCase(flightid) && element.getCustomerpassport().equals(customerpassport))
            {
                iter.remove();
            }
        }
        if(readset.size() == 0)
        {
            try {
                FileOutputStream fout = new FileOutputStream("Seats.txt", false);
                fout.write("".getBytes());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else {
            Seatsflag = false;
            for (int i = 0; i < readset.size(); i++) {
                AddSeats(readset.get(i));
                Seatsflag = true;
            }
        }
    }
    public void CancelSeats(String flightid)
    {
        ArrayList<Seats> readset = new ArrayList<>();
        try {
            File f = new File("Seats.txt");
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine = "";
            while ((readLine = b.readLine()) != null) {
                readset.add(tokenizeseats(readLine));
            }
            b.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        for (Iterator<Seats> iter = readset.iterator();iter.hasNext();)
        {
            Seats element = iter.next();
            if(element.getFlightid().equalsIgnoreCase(flightid))
            {
                iter.remove();
            }
        }
        if(readset.size() == 0)
        {
            try {
                FileOutputStream fout = new FileOutputStream("Seats.txt", false);
                fout.write("".getBytes());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else {
            Seatsflag = false;
            for (int i = 0; i < readset.size(); i++) {
                AddSeats(readset.get(i));
                Seatsflag = true;
            }
        }
    }
    public ArrayList<Seats> GetSeat()
    {
        ArrayList<Seats> readset = new ArrayList<>();
        try {
            File f = new File("Seats.txt");
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine = "";
            while ((readLine = b.readLine()) != null) {
                if(!readLine.equalsIgnoreCase(""))
                readset.add(tokenizeseats(readLine));
            }
            b.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return readset;
    }
    public Seats tokenizeseats(String line)
    {
        Seats st = new Seats();
        try
        {
            StringTokenizer tokens = new StringTokenizer(line,",");
            Integer passport = Integer.parseInt(tokens.nextElement().toString());
            String flid = tokens.nextElement().toString();
            Integer seatn = Integer.parseInt(tokens.nextElement().toString());
            String stat = tokens.nextElement().toString();
            st.setCustomerpassport(passport);
            st.setReserve(true);
            st.setStatus(stat);
            st.setNumber(seatn);
            st.setFlightid(flid);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return st;
    }
}
