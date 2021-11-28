package BusinessLogic;

public class OneWayFlight extends Flight
{
    public String getDeparture_date() {
        return Departure_date;
    }

    public void setDeparture_date(String departure_date) {
        Departure_date = departure_date;
    }

    public String getDeparture_time() {
        return Departure_time;
    }

    public void setDeparture_time(String departure_time) {
        Departure_time = departure_time;
    }

    public String getFlightType() {
        return FlightType;
    }

    public void setFlightType(String flightType) {
        FlightType = flightType;
    }

    private String Departure_date;
    private String Departure_time;
    String FlightType = "oneway";
    public OneWayFlight()
    {
        super();
        Departure_date = "";
        Departure_time = "";
    }
    public OneWayFlight(String id,String origin,String destination,int passenger,String Dept_date,String Dept_time,int fare,String classe)
    {
        super(id,origin,destination,passenger,fare,classe);
        this.Departure_time = Dept_time;
        this.Departure_date = Dept_date;
    }
    OneWayFlight(OneWayFlight object)
    {
        super.setId(object.getId());
        super.setOrigin(object.getOrigin());
        super.setDestination(object.getDestination());
        super.setCapacity(object.getCapacity());
        super.setFares(object.getFares());
        super.setClasse(object.getClasse());
        super.setSeats(object.getSeats(),object.getCapacity());
        this.Departure_time = object.Departure_time;
        this.Departure_date = object.Departure_date;
    }
    public <T> void Replace(T object)
    {
        super.setId(((OneWayFlight) object).getId());
        super.setOrigin(((OneWayFlight) object).getOrigin());
        super.setDestination(((OneWayFlight) object).getDestination());
        super.setCapacity(((OneWayFlight) object).getCapacity());
        super.setFares(((OneWayFlight) object).getFares());
        super.setClasse(((OneWayFlight) object).getClasse());
        super.setSeats(((OneWayFlight) object).getSeats(), ((OneWayFlight) object).getCapacity());
        this.Departure_time = ((OneWayFlight) object).Departure_time;
        this.Departure_date = ((OneWayFlight) object).Departure_date;
    }

    @Override
    public void display(int number) {
        System.out.println("\nFlight "+number+" Information:");
        System.out.println("\tFlight Id: "+super.getId()+"\t\tOrigin: "+super.getOrigin()+"\t\tDestination: "+super.getDestination());
        System.out.println("\tCapacity: "+super.getCapacity()+"\t\tDeparture Date: "+Departure_date+"\t\tDeparture Time: "+Departure_time);
        System.out.println("\tFares: "+super.getFares()+"$\t\tClass of Flight: "+super.getClasse()+"\t\tOneway Flight");
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