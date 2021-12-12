package BusinessLogic;


import java.util.ArrayList;


public class FlightCalender
{
    private final ArrayList<Flight> flightsschedule;

    FlightCalender()
    {
        flightsschedule = new ArrayList<Flight>();
    }
    public Flight getFlight(String flightid)
    {
        int index = searchflight(flightid);
        return flightsschedule.get(index);
    }

    public void ReadFlightsFromDatabase() throws NoFlightsFoundException {
        ArrayList<Flight> data = FlightReservationSystem.database.GetFlight();
        if(data.isEmpty())
        {
            throw new NoFlightsFoundException("No Flights Record Present in Database");
        }
        flightsschedule.addAll(data);
        System.out.println("Successfully Retreived Flights From Database");
    }
    public void ReadSeatsfromdatabase()
    {
        ArrayList<Seats> data = FlightReservationSystem.database.GetSeat();
        if(data.isEmpty())
        {
            throw new RuntimeException("No seats booked yet");
        }
        for (int i=0;i<data.size();i++)
        {
            int index = searchflight(data.get(i).getFlightid());
            flightsschedule.get(index).getSeats().get(data.get(i).getNumber()-1).setReserve(true);
            flightsschedule.get(index).getSeats().get(data.get(i).getNumber()-1).setStatus("Booked");
            flightsschedule.get(index).getSeats().get(data.get(i).getNumber()-1).setCustomerpassport(data.get(i).getCustomerpassport());
        }
        System.out.println("Successfully Retreived Seats From Database");
    }
    public void ChooseandAddFlight(String id,String origin,String Destination,int Capacity,int fare,String classe,String type,String depdate,String deptime,String arrivaldate,String arrivaltime) throws FlightsDuplicateFoundException {
        Flight newFlight = new Flight();

        if(type.equalsIgnoreCase("oneway"))
            newFlight = new OneWayFlight(id,origin,Destination,Capacity,depdate,deptime,fare,classe);

        else if(type.equalsIgnoreCase("roundtrip")) {
            newFlight = new TwoWayFlight(id,origin,Destination,Capacity,depdate,deptime,arrivaldate,arrivaltime,fare,classe);
        }
        AddFlight(newFlight);
    }
    public void AddFlight(Flight object) throws FlightsDuplicateFoundException
    {
        if(searchflight(object.getId()) != -1)
            throw new FlightsDuplicateFoundException("\nFlight with this id already present\n");

        flightsschedule.add(object);
        FlightReservationSystem.database.AddFlight(object);
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
    public void bookaflight(String id, int Numberofpassengers, Integer customerpassport, ArrayList<Integer> seatnumbers) throws NoFlightsFoundException, LessSeatsAvailableException, SeatNumberIncorrectException, AlreadyBookedSeatException
    {
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
        for (int i=0;i<seatnumbers.size();i++)
        {
            int seatindex = flightsschedule.get(index).searchseat(seatnumbers.get(i));
            flightsschedule.get(index).getSeats().get(seatindex).setReserve(true);
            flightsschedule.get(index).getSeats().get(seatindex).setCustomerpassport(customerpassport);
            flightsschedule.get(index).getSeats().get(seatindex).setStatus("Booked");
            Seats object = new Seats(id,seatnumbers.get(i),customerpassport,"Booked",true);
            FlightReservationSystem.database.AddSeats(object);
        }
    }
    public void cancelseats(String id,Integer Passport)
    {
        int index = searchflight(id);
        for (int i=0;i<flightsschedule.get(index).getSeats().size();i++)
        {
            if(flightsschedule.get(index).getSeats().get(i).getCustomerpassport().equals(Passport))
            {
                flightsschedule.get(index).getSeats().get(i).setReserve(false);
                flightsschedule.get(index).getSeats().get(i).setStatus("Free");
                flightsschedule.get(index).getSeats().get(i).setCustomerpassport(0);
            }
            FlightReservationSystem.database.CancelSeats(id,Passport);
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

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                    FlightReservationSystem.database.RemoveFlight(flightsschedule.get(index));
            }
        });
         t.start();

       Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                    FlightReservationSystem.database.CancelSeats(id);
            }
        });
        t2.start();
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
    public ArrayList<Flight> searchFlights(String origin,String destination,String type) throws NoFlightsFoundException
    {
        ArrayList<Flight> duplicatelist = new ArrayList<Flight>();
        if(flightsschedule.isEmpty())
        {
            throw new NoFlightsFoundException("\nNo flights available\n");
        }
        boolean found = false;
        for (int i=0;i<flightsschedule.size();i++)
        {
            if (flightsschedule.get(i).getOrigin().equalsIgnoreCase(origin) && flightsschedule.get(i).getDestination().equalsIgnoreCase(destination)
                && flightsschedule.get(i).getType().equalsIgnoreCase(type))
            {
                found = true;
                duplicatelist.add(flightsschedule.get(i));
            }
        }
        if(found == false)
        {
            throw new NoFlightsFoundException("No flights Present");
        }
        return duplicatelist;
    }
    public ArrayList<Flight> getFlightsschedule()
    {
        return flightsschedule;
    }
}