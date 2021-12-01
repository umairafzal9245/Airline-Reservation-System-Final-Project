package BusinessLogic;

import java.util.ArrayList;

public class Flight
{
    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    private String origin;

    public void setDestination(String destination) {
        Destination = destination;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    private String Destination;
    private int capacity;

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public void setSeats(ArrayList<Seats> seats,int capacity) {
        this.seats = new ArrayList<Seats>(capacity);
        for (int i=0;i<capacity;i++)
        {
            Seats object = new Seats(seats.get(i));
            this.seats.add(object);
        }
    }

    private int fares;
    private String classe;
    private ArrayList<Seats> seats;



    public Flight()
    {
        origin = "";
        Destination = "";
        capacity = 0;
        fares = 0;
        id = "";
        seats = new ArrayList<Seats>(capacity);
        for (int i=0;i<capacity;i++)
        {
            Seats object = new Seats(i+1);
            seats.add(object);
        }
    }
    Flight(String id,String origin,String destination,int passenger,int fare,String classe)
    {
        this.id = id;
        this.origin = origin;
        this.Destination = destination;
        this.capacity = passenger;
        this.fares = fare;
        this.classe = classe;
        seats = new ArrayList<Seats>(capacity);
        for (int i=0;i<capacity;i++)
        {
            Seats object = new Seats(i+1);
            seats.add(object);
        }
    }
    public <T> void Replace(T object)
    {

    }
    public int searchseat(int seatnumber)
    {
        int index = -1;
        for (int i=0;i<seats.size();i++)
        {
            if(seats.get(i).getNumber() == seatnumber)
            {
                index = i;
                break;
            }
        }
        return index;
    }

    public ArrayList<Seats> getSeats() {
        return seats;
    }
    public int Availableseats()
    {
        int counter = 0;
        for (int i=0;i<capacity;i++)
        {
            if(seats.get(i).isReserve() == false)
                counter++;
        }
        return counter;
    }
    public void display(int number)
    {

    }

    public String getId()
    {
        return id;
    }
    public String getOrigin()
    {
        return origin;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getDestination() {
        return Destination;
    }

    public String getClasse() {
        return classe;
    }

    public int getFares() {
        return fares;
    }

    public void setFares(int fares) {
        this.fares = fares;
    }

    public String getType()
    {
        return "";
    }

    public String getDeparture_date() {
        return null;
    }
    public String getDeparture_time()
    {
        return null;
    }
    public String getArrivalDate()
    {
        return null;
    }
    public String getArrivalTime()
    {
        return null;
    }
}
