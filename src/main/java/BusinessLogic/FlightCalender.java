package BusinessLogic;

import DatabaseCode.DataBaseHandler;
import DatabaseCode.OracleDataBase;

import java.util.ArrayList;
import java.util.Scanner;


public class FlightCalender
{
    public void setFlightsschedule(ArrayList<Flight> flightsschedule) {
        this.flightsschedule = flightsschedule;
    }

    private ArrayList<Flight> flightsschedule;
    private DataBaseHandler Database;

    public ArrayList<Flight> getFlightsschedule()
    {
        return flightsschedule;
    }

    FlightCalender()
    {
        flightsschedule = new ArrayList<Flight>();
        Database = new OracleDataBase();
    }
    public ArrayList<Integer> getSeats(String flightId,String name)
    {
        ArrayList<Integer> seatnumbers = new ArrayList<Integer>();
        for (int i=0;i<flightsschedule.size();i++)
        {
            if(flightsschedule.get(i).getId().equalsIgnoreCase(flightId))
            {
                for (int j=0;j<flightsschedule.get(i).getSeats().size();j++)
                {
                    if(flightsschedule.get(i).getSeats().get(j).getCustomername().equalsIgnoreCase(name))
                    {
                        seatnumbers.add(flightsschedule.get(i).getSeats().get(j).getNumber());
                    }
                }
            }
        }
        return seatnumbers;
    }
    public void ReadFlightsFromDatabase() throws NoFlightsFoundException {
        ArrayList<Flight> data = Database.GetFlight();
        if(data.isEmpty())
        {
            throw new NoFlightsFoundException("No Flights Record Present in Database");
        }
        for (int i=0;i<data.size();i++)
        {
            flightsschedule.add(data.get(i));
        }
        System.out.println("Successfully Retreived Flights From Database");
    }
    public void ReadSeatsfromdatabase()
    {
        ArrayList<Seats> data = Database.GetSeat();
        if(data.isEmpty())
        {
            throw new RuntimeException("No seats booked yet");
        }
        for (int i=0;i<data.size();i++)
        {
            int index = searchflight(data.get(i).getFlightid());
            flightsschedule.get(index).getSeats().get(data.get(i).getNumber()-1).setReserve(true);
            flightsschedule.get(index).getSeats().get(data.get(i).getNumber()-1).setStatus("Booked");
            flightsschedule.get(index).getSeats().get(data.get(i).getNumber()-1).setCustomername(data.get(i).getCustomername());
        }
        System.out.println("Successfully Retreived Seats From Database");
    }
    public void AddFlight(Flight object) throws FlightsDuplicateFoundException
    {
        if(searchflight(object.getId()) != -1)
            throw new FlightsDuplicateFoundException("\nFlight with this id already present\n");

        flightsschedule.add(object);
        Database.AddFlight(object);
        System.out.println("\n\tFlight Added Succefully\n");
    }
    public int searchflight(String id)
    {
        int index = -1;
        for (int i=0;i<flightsschedule.size();i++)
        {
            if(flightsschedule.get(i).getId().equalsIgnoreCase(id))
            {
                index = i;
                break;
            }
        }
        return index;
    }
    public ArrayList<Seats> GetFlightSeats(String Flightid) throws FlightIDIncorrectException {
        int index = searchflight(Flightid);
        if(index == -1)
            throw new FlightIDIncorrectException("Flight with this id not found");

        return flightsschedule.get(index).getSeats();
    }
    public boolean bookaflight(String id,int Numberofpassengers,String customername,ArrayList<Integer> seatnumbers) throws NoFlightsFoundException, LessSeatsAvailableException, SeatNumberIncorrectException, AlreadyBookedSeatException
    {
        boolean flag = false;
        if(flightsschedule.isEmpty())
            throw new NoFlightsFoundException("Flight list is empty\n");

        int index = searchflight(id);
        int availabeseats = flightsschedule.get(index).Availableseats();

        if(Numberofpassengers > availabeseats)
            throw new LessSeatsAvailableException("Number of passengers greater than available seats\n");

        for (int i=0;i<seatnumbers.size();i++)
        {
            int seatindex = flightsschedule.get(index).searchseat(seatnumbers.get(i));

            if(seatindex == -1)
                throw new SeatNumberIncorrectException("Seat number "+seatnumbers.get(i)+" is not valid\n");

            if(flightsschedule.get(index).getSeats().get(seatindex).isReserve() == true)
                throw new AlreadyBookedSeatException("Seat number "+ seatnumbers.get(i)+" is already booked\n");
        }
        flag = true;
        for (int i=0;i<seatnumbers.size();i++)
        {
            int seatindex = flightsschedule.get(index).searchseat(seatnumbers.get(i));
            flightsschedule.get(index).getSeats().get(seatindex).setReserve(true);
            flightsschedule.get(index).getSeats().get(seatindex).setCustomername(customername);
            flightsschedule.get(index).getSeats().get(seatindex).setStatus("Booked");
            Seats object = new Seats(id,seatnumbers.get(i),customername,"Booked",true);
            Database.AddSeats(object);
        }
        return flag;
    }
    public void cancelseats(String id,String name)
    {
        int index = searchflight(id);
        for (int i=0;i<flightsschedule.get(index).getSeats().size();i++)
        {
            if(flightsschedule.get(index).getSeats().get(i).getCustomername().equalsIgnoreCase(name))
            {
                flightsschedule.get(index).getSeats().get(i).setReserve(false);
                flightsschedule.get(index).getSeats().get(i).setStatus("Free");
                flightsschedule.get(index).getSeats().get(i).setCustomername("Not booked Yet");
            }
            Database.CancelSeats(id,name);
        }
        System.out.println("Seats have been cancelled!!!");
    }
    public void deleteflight(String id) throws NoFlightsFoundException, FlightIDIncorrectException
    {
        if(flightsschedule.isEmpty())
            throw new NoFlightsFoundException("Flight list is empty\n");

        int index = searchflight(id);
        if(index == -1)
            throw new FlightIDIncorrectException("Flight with this id not found\n");

        Database.RemoveFlight(flightsschedule.get(index));
        Database.CancelSeats(id);
        flightsschedule.remove(index);
        System.out.println("\n\tFlight removed succefully");
    }
    public void display() throws NoFlightsFoundException
    {
        if(flightsschedule.isEmpty())
            throw new NoFlightsFoundException("Flight list is empty\n");

        for (int i=0;i<flightsschedule.size();i++)
            flightsschedule.get(i).display(i+1);

    }
    public boolean searchFlights(String origin,String destination,String type) throws NoFlightsFoundException
    {
        ArrayList<Flight> duplicatelist = new ArrayList<Flight>();
        if(flightsschedule.isEmpty())
        {
            throw new NoFlightsFoundException("\nNo flights available\n");
        }
        boolean found = false;
        int m =0;
        for (int i=0;i<flightsschedule.size();i++)
        {
            if (flightsschedule.get(i).getOrigin().equalsIgnoreCase(origin) && flightsschedule.get(i).getDestination().equalsIgnoreCase(destination)
                && flightsschedule.get(i).getType().equalsIgnoreCase(type))
            {
                found = true;
                duplicatelist.add(flightsschedule.get(i));
                flightsschedule.get(i).display(m + 1);
                m++;
            }
        }
        if(found == false)
        {
            System.out.println("\nNo flights available\n");
        }
        else
        {
            System.out.println("\n1. Sort by fares");
            System.out.println("2. Sort by seats");
            System.out.println("3. GO back");
            System.out.print("Select an option: ");
            Scanner input = new Scanner(System.in);
            int option = input.nextInt();
            if(option == 1)
            {
                int n = duplicatelist.size();
                for (int i = 0; i < n-1; i++) {
                    for (int j = 0; j < n - i - 1; j++) {
                        if (duplicatelist.get(j).getFares() > duplicatelist.get(j + 1).getFares())
                        {
                            Flight temp = new Flight();
                            if(duplicatelist.get(j) instanceof OneWayFlight)
                            {
                                temp = new OneWayFlight(((OneWayFlight) duplicatelist.get(j)));
                            }
                            else if(duplicatelist.get(j) instanceof TwoWayFlight)
                            {
                                temp = new TwoWayFlight(((TwoWayFlight) duplicatelist.get(j)));
                            }
                            duplicatelist.get(j).Replace(duplicatelist.get(j + 1));
                            duplicatelist.get(j + 1).Replace(temp);
                        }
                    }
                }

                for (int i=0;i<duplicatelist.size();i++)
                {
                    duplicatelist.get(i).display(i+1);
                }
            }
            else if(option == 2)
            {
                int n = duplicatelist.size();
                for (int i = 0; i < n-1; i++) {
                    for (int j = 0; j < n - i - 1; j++) {
                        if (duplicatelist.get(j).getCapacity() > duplicatelist.get(j + 1).getCapacity())
                        {
                            Flight temp = new Flight();
                            if(duplicatelist.get(j) instanceof OneWayFlight)
                            {
                                temp = new OneWayFlight(((OneWayFlight) duplicatelist.get(j)));
                            }
                            else if(duplicatelist.get(j) instanceof TwoWayFlight)
                            {
                                temp = new TwoWayFlight(((TwoWayFlight) duplicatelist.get(j)));
                            }
                            duplicatelist.get(j).Replace(duplicatelist.get(j + 1));
                            duplicatelist.get(j + 1).Replace(temp);
                        }
                    }
                }
                for (int i=0;i<duplicatelist.size();i++)
                {
                    duplicatelist.get(i).display(i+1);
                }
            }
        }
        return found;
    }
}