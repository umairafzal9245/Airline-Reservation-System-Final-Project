package DatabaseCode;

import BusinessLogic.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;

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
        createconnection();
        session.save(object);
        trans.commit();
    }
    @Override
    public ArrayList<Reservation> GetReservation()
    {
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        List<Reservation>  one = session.createQuery("FROM Reservation ").list();
        for (int i=0;i<one.size();i++)
        {
            reservations.add(one.get(i));
        }
        return reservations;
    }
    @Override
    public void AddSeats(Seats object)
    {
        createconnection();
        session.save(object);
        trans.commit();
    }
    @Override
    public ArrayList<Seats> GetSeat()
    {
        ArrayList<Seats> seatsslist = new ArrayList<Seats>();
        List<Seats>  one = session.createQuery("FROM Reservation ").list();
        for (int i=0;i<one.size();i++)
            seatsslist.add(one.get(i));

        return seatsslist;
    }
    public void CancelSeats(String flightid,String customername)
    {
        createconnection();
        Seats object = new Seats();
        object.setFlightid(flightid);
        object.setCustomername(customername);
        session.delete(object);
        trans.commit();
    }
    public void CancelSeats(String flightid)
    {
        createconnection();
        Query query = session.createQuery("delete from Seats where FlightId = :value");
        query.setParameter("value", flightid);
        query.executeUpdate();
        trans.commit();
    }
    public void DeleteReservation(Reservation object)
    {
        createconnection();
        session.delete(object);
        trans.commit();
    }
}
