package BusinessLogic;

import java.util.ArrayList;

public class Tickets
{
    public int getNumberofpassengers() {
        return numberofpassengers;
    }

    public int getTotalfares() {
        return totalfares;
    }

    public String getType() {
        return type;
    }

    private int numberofpassengers;
    private int totalfares;
    private String type;

    Tickets()
    {
        numberofpassengers = 0;
        totalfares = 0;
        type = "";
    }
    Tickets(int numberofpassengers,int totalfares,String type)
    {
        this.numberofpassengers = numberofpassengers;
        this.totalfares = totalfares;
        this.type = type;
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
}
