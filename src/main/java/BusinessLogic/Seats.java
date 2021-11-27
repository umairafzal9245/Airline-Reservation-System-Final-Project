package BusinessLogic;

public class Seats
{
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public boolean isReserve() {
        return reserve;
    }

    public void setReserve(boolean reserve) {
        this.reserve = reserve;
    }

    public String getFlightid() {
        return flightid;
    }

    public void setFlightid(String flightid) {
        this.flightid = flightid;
    }

    private String flightid;
    private int number;
    private String status;
    private String customername;
    private boolean reserve;

    public Seats()
    {
        number = 0;
        status = "Free";
        customername = "Not booked Yet";
        reserve = false;
    }
    public Seats(String flightid,int seatnumber,String customername,String status,Boolean reserve)
    {
        this.flightid = flightid;
        number = seatnumber;
        this.customername = customername;
        this.status = status;
        this.reserve = reserve;
    }
    Seats(Seats obj)
    {
        this.number = obj.number;
        this.status = obj.status;
        this.customername = obj.customername;
        this.reserve = obj.reserve;
    }
    Seats(int seatnumber)
    {
        this.number = seatnumber;
        status = "Free";
        customername = "Not booked Yet";
        reserve = false;
    }
    public void display()
    {
        System.out.println("\tSeat Number: "+number+"\tSeat Status: "+status+"\tCustomer Name: "+customername+"\tReserve Status: "+reserve);
    }
}
