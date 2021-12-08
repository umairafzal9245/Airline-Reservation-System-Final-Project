package BusinessLogic;


import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "Tickets")
public class Tickets
{
    @Id
    @Column(name = "Booking_Reference")
    public int bookingreference;

    @Column(name = "Numberofpassengers")
    private int numberofpassengers;

    @Column(name = "TotalFares")
    private int totalfares;

    @Column(name = "Type")
    private String type;

    public Tickets()
    {
        numberofpassengers = 0;
        totalfares = 0;
        type = "";
    }
    public void addticket(int numberofpassengers,int totalfares,String type)
    {
        this.numberofpassengers = numberofpassengers;
        this.totalfares = totalfares;
        this.type = type;
    }
    public void Display(String flightid,ArrayList<Integer> seatnumbers)
    {
        System.out.println("\n\tTicket Information: ");
        System.out.println("\tFlight Id: "+flightid+"\t\tNumber of passengers: "+numberofpassengers);
        System.out.println("\tTotal Fares: "+totalfares+"\t\tTicket Class: "+type);
        System.out.print("\tSeat Numbers: ");
        for (int i=0;i<seatnumbers.size();i++)
        {
            System.out.print(seatnumbers.get(i)+" ");
        }
    }
    public int getNumberofpassengers() {
        return numberofpassengers;
    }

    public int getTotalfares() {
        return totalfares;
    }

    public String getType() {
        return type;
    }

    public int getBookingreference() {
        return bookingreference;
    }

    public void setBookingreference(int bookingreference) {
        this.bookingreference = bookingreference;
    }
}
