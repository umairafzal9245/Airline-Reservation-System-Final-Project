package BusinessLogic;

public class TwoWayFlight extends Flight
{
    public String getDeparture_date() {
        return Departure_Date;
    }

    public void setDeparture_Date(String departure_Date) {
        Departure_Date = departure_Date;
    }

    public String getDeparture_time() {
        return Departure_Time;
    }

    public void setDeparture_Time(String departure_Time) {
        Departure_Time = departure_Time;
    }

    public String getArrivalDate() {
        return ArrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        ArrivalDate = arrivalDate;
    }

    public String getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    public String getFlightType() {
        return FlightType;
    }

    public void setFlightType(String flightType) {
        FlightType = flightType;
    }

    String Departure_Date;
    String Departure_Time;
    String ArrivalDate;
    String ArrivalTime;
    String FlightType = "Roundtrip";
    public TwoWayFlight()
    {
        super();
        Departure_Date = "";
        Departure_Time = "";
        ArrivalDate = "";
        ArrivalTime = "";
    }
    public TwoWayFlight(String id, String origin, String destination, int passenger, String Dept_date, String Dept_time, String arr_date, String arr_time, int fare, String classe)
    {
        super(id,origin,destination,passenger,fare,classe);
        this.Departure_Time = Dept_time;
        this.Departure_Date = Dept_date;
        this.ArrivalDate = arr_date;
        this.ArrivalTime = arr_time;
    }
    TwoWayFlight(TwoWayFlight object)
    {
        super.setId(object.getId());
        super.setOrigin(object.getOrigin());
        super.setDestination(object.getDestination());
        super.setCapacity(object.getCapacity());
        super.setFares(object.getFares());
        super.setClasse(object.getClasse());
        super.setSeats(object.getSeats(),object.getCapacity());
        this.Departure_Date = object.Departure_Date;
        this.Departure_Time =  object.Departure_Time;
        this.ArrivalTime = object.ArrivalTime;
        this.ArrivalDate = object.ArrivalDate;
    }
    public <T> void Replace(T object)
    {
        super.setId(((TwoWayFlight) object).getId());
        super.setOrigin(((TwoWayFlight) object).getOrigin());
        super.setDestination(((TwoWayFlight) object).getDestination());
        super.setCapacity(((TwoWayFlight) object).getCapacity());
        super.setFares(((TwoWayFlight) object).getFares());
        super.setClasse(((TwoWayFlight) object).getClasse());
        super.setSeats(((TwoWayFlight) object).getSeats(), ((TwoWayFlight) object).getCapacity());
        this.Departure_Date = ((TwoWayFlight) object).Departure_Date;
        this.Departure_Time = ((TwoWayFlight) object).Departure_Time;
        this.ArrivalTime = ((TwoWayFlight) object).ArrivalTime;
        this.ArrivalDate = ((TwoWayFlight) object).ArrivalDate;
    }
    @Override
    public void display(int number)
    {
        System.out.println("\nFlight "+number+" Information:");
        System.out.println("\tFlight Id: "+super.getId()+"\t\tOrigin: "+super.getOrigin()+"\t\tDestination: "+super.getDestination());
        System.out.println("\tCapacity: "+super.getCapacity()+"\t\tDeparture Date: "+Departure_Date+"\t\tTime: "+Departure_Time);
        System.out.println("\tArrival Date: "+ArrivalDate+"\t\tArrival Time: "+ArrivalTime+"\t\tRoundTrip Flight");
        System.out.println("\tFares: "+super.getFares()+"$\t\tClass of Flight: "+super.getClasse());
        System.out.println("\n\tSeats Information: ");
        for (int i=0;i<super.getSeats().size();i++)
        {
            super.getSeats().get(i).display();
        }
        System.out.println("\n");
    }
    public String getType()
    {
        return FlightType;
    }
}
