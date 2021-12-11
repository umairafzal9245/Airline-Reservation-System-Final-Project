package BusinessLogic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "OneWayFlights")
public class OneWayFlight extends Flight
{
    public void setDeparture_date(String departure_date) {
        Departure_date = departure_date;
    }

    public void setDeparture_time(String departure_time) {
        Departure_time = departure_time;
    }

    @Column(name = "Departure_Date")
    private String Departure_date;

    @Column(name = "Departure_Time")
    private String Departure_time;

    @Transient
    private String FlightType = "oneway";

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
    public String getDeparture_date() {
        return Departure_date;
    }

    public String getDeparture_time() {
        return Departure_time;
    }

    public String getType()
    {
        return FlightType;
    }
}
