package DatabaseCode;

import BusinessLogic.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class OracleDataBase extends DataBaseHandler
{
    Configuration con;
    SessionFactory sf;
    Session session;
    Transaction trans;
    private void createconnection()
    {
        con = new Configuration();
        con.configure();
        sf = con.buildSessionFactory();
        session = sf.openSession();
        trans = session.beginTransaction();
    }
    @Override
    public void AddCustomer(String name, String gender, int age, String address, Integer passport_number, int loginpin)
    {
        createconnection();
        Customer object = new Customer(name,gender,age,address,passport_number,loginpin,true);
        session.save(object);
        trans.commit();
    }
    @Override
    public ArrayList<Customer> GetCustomer()
    {
        ArrayList<Customer> Customerslist = new ArrayList<Customer>();
        createconnection();
        Customerslist = (ArrayList<Customer>) session.createQuery("FROM Customer").list();
        return Customerslist;
    }

    @Override
    public void RemoveCustomer(Customer object)
    {
        createconnection();
        session.delete(object);
        trans.commit();
    }

    @Override
    public void AddFlight(Flight object)
    {
        createconnection();
        if(object instanceof OneWayFlight)
        session.save(((OneWayFlight) object));
        else if(object instanceof TwoWayFlight)
            session.save((TwoWayFlight)object);
        trans.commit();
    }
    @Override
    public ArrayList<Flight> GetFlight()
    {
        createconnection();
        ArrayList<Flight> flightlist = new ArrayList<Flight>();
        List<OneWayFlight>  one = session.createQuery("FROM OneWayFlight ").list();
        List<TwoWayFlight> two = session.createQuery("FROM TwoWayFlight ").list();

        for (int i=0;i<one.size();i++)
        {
            OneWayFlight object = new OneWayFlight(one.get(i).getId(),one.get(i).getOrigin(),one.get(i).getDestination(),one.get(i).getCapacity(),one.get(i).getDeparture_date(),one.get(i).getDeparture_time(),one.get(i).getFares(),one.get(i).getClasse());
            flightlist.add(object);
        }
        for (int i=0;i<two.size();i++)
        {
            TwoWayFlight object = new TwoWayFlight(two.get(i).getId(),two.get(i).getOrigin(),two.get(i).getDestination(),two.get(i).getCapacity(),two.get(i).getDeparture_date(),two.get(i).getDeparture_time(),two.get(i).getArrivalDate(),two.get(i).getArrivalTime(),two.get(i).getFares(),two.get(i).getClasse());
            flightlist.add(object);
        }
        return flightlist;
    }
    @Override
    public <T> void RemoveFlight(T flight)
    {
        createconnection();
        if(flight instanceof OneWayFlight)
            session.delete(((OneWayFlight) flight));
        else if(flight instanceof TwoWayFlight)
            session.delete((TwoWayFlight)flight);
        trans.commit();
    }
    @Override
    public void AddReservations(Reservation object)
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger12345");

            String sql = "INSERT INTO TICKETS (BOOKINGREFERENCE,NUMBEROFPASSENGERS,TOTALFARES,CLASS) VALUES (?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1,object.getBookingreference());
            statement.setInt(2,object.getTicket().getNumberofpassengers());
            statement.setInt(3,object.getTicket().getTotalfares());
            statement.setString(4,object.getTicket().getType());
            if(statement.executeUpdate() > 0)
            {
                String sql2 = "INSERT INTO PAYMENTS (BOOKINGREFERENCE,CARDHOLDERNAME,CARDNUMBER,EXPIRYDATE,CVV) VALUES (?,?,?,?,?)";
                PreparedStatement statement2 = con.prepareStatement(sql2);
                statement2.setInt(1,object.getBookingreference());
                statement2.setString(2,object.getPayment().getCardholdername());
                statement2.setString(3,object.getPayment().getCardnumber());
                statement2.setString(4,object.getPayment().getExpirydate());
                statement2.setInt(5,object.getPayment().getCvv());
                if(statement2.executeUpdate() > 0)
                {
                    String sql3 = "INSERT INTO RESERVATIONS (BOOKINGREFERENCE,FLIGHTID,CUSTOMERNAME) VALUES (?,?,?)";
                    PreparedStatement statement3 = con.prepareStatement(sql3);
                    statement3.setInt(1,object.getBookingreference());
                    statement3.setString(2,object.getFlightid());
                    statement3.setString(3,object.getCustomername());
                    if(statement3.executeUpdate() > 0)
                    {
                        System.out.println("Record Added to Reservations tickets payments Database Successfully");
                    }
                }
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public ArrayList<Reservation> GetReservation()
    {
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger12345");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM reservations");
            while(rs.next())
            {
                Reservation object = new Reservation();
                object.setBookingreference(rs.getInt("BOOKINGREFERENCE"));
                object.setFlightid(rs.getString("FLIGHTID"));
                object.setCustomername(rs.getString("CUSTOMERNAME"));
                Statement stmt2 = con.createStatement();
                ResultSet rs2 = stmt2.executeQuery("SELECT * FROM payments WHERE bookingreference = "+rs.getInt("BOOKINGREFERENCE"));
                if(rs2.next() == false)
                {
                    throw new RuntimeException("No payment present for reservation reference "+rs.getInt("BOOKINGREFERENCE"));
                }
                object.getPayment().addpayment(rs2.getString("CARDHOLDERNAME"),rs2.getString("CARDNUMBER"),rs2.getString("EXPIRYDATE"),rs2.getInt("CVV"));
                Statement stmt3 = con.createStatement();
                ResultSet rs3 = stmt3.executeQuery("SELECT * FROM tickets WHERE bookingreference = "+rs.getInt("BOOKINGREFERENCE"));
                if(rs3.next() == false)
                {
                    throw new RuntimeException("No ticket present for reservation reference "+rs.getInt("BOOKINGREFERENCE"));
                }
                object.getTicket().addticket(rs3.getInt("NUMBEROFPASSENGERS"), rs3.getInt("TOTALFARES"),rs3.getString("CLASS") );
                reservations.add(object);
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return reservations;
    }
    @Override
    public void AddSeats(Seats object)
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger12345");
            String sql = "INSERT INTO SEATS (FLIGHTID,SEATNUMBER,CUSTOMERNAME) VALUES (?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1,object.getFlightid());
            statement.setInt(2,object.getNumber());
            statement.setString(3,object.getCustomername());
            if(statement.executeUpdate()>0)
            {
                System.out.println("Record Added To Seats Database Successfully");
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public ArrayList<Seats> GetSeat()
    {
        ArrayList<Seats> seatsslist = new ArrayList<Seats>();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger12345");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select * FROM seats");
            while(rs.next())
            {
                Seats object = new Seats(rs.getString("FLIGHTID"), rs.getInt("SEATNUMBER"), rs.getString("CUSTOMERNAME"),"Booked",true);
                seatsslist.add(object);
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return seatsslist;
    }
    public void CancelSeats(String flightid,String customername)
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger12345");
            String sql = "DELETE FROM seats WHERE FLIGHTID = ? AND CUSTOMERNAME = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1,flightid);
            statement.setString(2,customername);
            if(statement.executeUpdate()>0)
            {
                System.out.println("Deleted Seats From Database Successfully");
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void CancelSeats(String flightid)
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger12345");
            String sql = "DELETE FROM seats WHERE FLIGHTID = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1,flightid);
            if(statement.executeUpdate()>0)
            {
                System.out.println("Deleted Seats From Database Successfully");
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void DeleteReservation(int bookingreference)
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger12345");
            String sql = "DELETE FROM reservations WHERE bookingreference = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1,bookingreference);
            if(statement.executeUpdate()>0)
            {
                String sql2 = "DELETE FROM payments WHERE bookingreference = ?";
                PreparedStatement statement2 = con.prepareStatement(sql2);
                statement2.setInt(1,bookingreference);
                if (statement2.executeUpdate() > 0)
                {
                    String sql3 = "DELETE FROM tickets WHERE bookingreference = ?";
                    PreparedStatement statement3 = con.prepareStatement(sql3);
                    statement3.setInt(1,bookingreference);
                    if (statement3.executeUpdate()>0)
                    {
                        System.out.println("Deleted Reservations From Database Successfully");
                    }
                }
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
