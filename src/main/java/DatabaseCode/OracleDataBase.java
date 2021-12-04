package DatabaseCode;

import BusinessLogic.*;

import java.sql.*;
import java.util.ArrayList;

public class OracleDataBase extends DataBaseHandler
{
    @Override
    public void AddCustomer(String name,String gender,int age,String address,String passport_number,int loginpin)
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger12345");
            String sql = "INSERT INTO CUSTOMERS (NAME,GENDER,AGE,ADDRESS,PASSPORTNUMBER,LOGINPIN) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1,name);
            statement.setString(2,gender);
            statement.setInt(3,age);
            statement.setString(4,address);
            statement.setString(5,passport_number);
            statement.setInt(6,loginpin);
            if(statement.executeUpdate()>0)
            {
                System.out.println("Record Added to Customer Database Successfully");
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public ArrayList<Customer> GetCustomer()
    {
        ArrayList<Customer> Customerslist = new ArrayList<Customer>();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger12345");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select * FROM customers");
            while(rs.next())
            {
                Customer object = new Customer(rs.getString("NAME"),rs.getString("GENDER"),rs.getInt("AGE"),
                        rs.getString("ADDRESS"),rs.getString("PASSPORTNUMBER"),rs.getInt("LOGINPIN"),false);
                Customerslist.add(object);
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return Customerslist;
    }

    @Override
    public void RemoveCustomer(String name)
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger12345");
            String sql = "DELETE FROM customers WHERE NAME = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1,name);
            if(statement.executeUpdate()>0)
            {
                System.out.println("Deleted Customer From Database");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public <T> void AddFlight(T object)
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger12345");
            PreparedStatement statement = null;
            if(object instanceof TwoWayFlight)
            {
                String sql = "INSERT INTO TWOWAYFLIGHTS (ID,ORIGIN,DESTINATION,PASSENGER,DEPARTUREDATE,DEPARTURETIME,ARRIVALDATE,ARRIVALTIME,FARES,CLASS) VALUES (?,?,?,?,?,?,?,?,?,?)";
                statement = con.prepareStatement(sql);
                statement.setString(1,((TwoWayFlight) object).getId());
                statement.setString(2,((TwoWayFlight) object).getOrigin());
                statement.setString(3,((TwoWayFlight) object).getDestination());
                statement.setInt(4,((TwoWayFlight) object).getCapacity());
                statement.setString(5,((TwoWayFlight) object).getDeparture_date());
                statement.setString(6,((TwoWayFlight) object).getDeparture_time());
                statement.setString(7,((TwoWayFlight) object).getArrivalDate());
                statement.setString(8,((TwoWayFlight) object).getArrivalTime());
                statement.setInt(9,((TwoWayFlight) object).getFares());
                statement.setString(10,((TwoWayFlight) object).getClasse());
            }
            else if(object instanceof OneWayFlight)
            {
                String sql = "INSERT INTO ONEWAYFLIGHTS (ID,ORIGIN,DESTINATION,PASSENGER,DEPARTUREDATE,DEPARTURETIME,FARES,CLASS) VALUES (?,?,?,?,?,?,?,?)";
                statement = con.prepareStatement(sql);
                statement.setString(1,((OneWayFlight) object).getId());
                statement.setString(2,((OneWayFlight) object).getOrigin());
                statement.setString(3,((OneWayFlight) object).getDestination());
                statement.setInt(4,((OneWayFlight) object).getCapacity());
                statement.setString(5,((OneWayFlight) object).getDeparture_date());
                statement.setString(6,((OneWayFlight) object).getDeparture_time());
                statement.setInt(7,((OneWayFlight) object).getFares());
                statement.setString(8,((OneWayFlight) object).getClasse());
            }
            if(statement.executeUpdate()>0)
            {
                System.out.println("Record Added to Flights Database Successfully");
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public ArrayList<Flight> GetFlight()
    {
        ArrayList<Flight> flightlist = new ArrayList<Flight>();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger12345");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select * FROM onewayflights");
            while(rs.next())
            {
                Flight object = new OneWayFlight(rs.getString("ID"),rs.getString("ORIGIN"),rs.getString("DESTINATION"),rs.getInt("PASSENGER"), rs.getString("DEPARTUREDATE"),rs.getString("DEPARTURETIME"),rs.getInt("FARES"), rs.getString("CLASS"));
                flightlist.add(object);
            }
            Statement stmt2 = con.createStatement();
            ResultSet rs1 = stmt2.executeQuery("Select * FROM twowayflights");
            while(rs1.next())
            {
                Flight object = new TwoWayFlight(rs1.getString("ID"),rs1.getString("ORIGIN"),rs1.getString("DESTINATION"),rs1.getInt("PASSENGER"), rs1.getString("DEPARTUREDATE"),rs1.getString("DEPARTURETIME"),rs1.getString("ARRIVALDATE"),rs1.getString("ARRIVALTIME"),rs1.getInt("FARES"), rs1.getString("CLASS"));
                flightlist.add(object);
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return flightlist;
    }
    @Override
    public <T> void RemoveFlight(T flight)
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger12345");
            PreparedStatement statement = null;
            if(flight instanceof OneWayFlight) {
                String sql = "DELETE FROM ONEWAYFLIGHTS WHERE ID = ?";
                statement = con.prepareStatement(sql);
                statement.setString(1,((OneWayFlight) flight).getId());
            }
            else if(flight instanceof TwoWayFlight)
            {
                String sql = "DELETE FROM TWOWAYFLIGHTS WHERE ID = ?";
                statement = con.prepareStatement(sql);
                statement.setString(1,((TwoWayFlight) flight).getId());
            }
            if(statement.executeUpdate()>0)
            {
                System.out.println("Deleted Flight From Database");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
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
