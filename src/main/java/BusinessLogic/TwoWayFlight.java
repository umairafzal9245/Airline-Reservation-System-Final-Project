package BusinessLogic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TwoWayFlights")
public class TwoWayFlight extends Flight
{
    public void setDeparture_date(String departure_date) {
        Departure_Date = departure_date;
    }

    public void setDeparture_time(String departure_time) {
        Departure_Time = departure_time;
    }

    public void setArrivalTime(String arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    @Column(name = "Departure_Date")
    private String Departure_Date;

    @Column(name = "Departure_Time")
    private String Departure_Time;

    @Column(name = "Arrival_Date")
    private String ArrivalDate;

    @Column(name = "Arrival_Time")
    private String ArrivalTime;

    @Transient
    private String FlightType = "Roundtrip";

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
    public String getDeparture_date() {
        return Departure_Date;
    }
    public String getDeparture_time() {
        return Departure_Time;
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
}
