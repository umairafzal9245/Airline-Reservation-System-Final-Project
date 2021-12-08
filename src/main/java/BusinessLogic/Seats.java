package BusinessLogic;

import javax.persistence.*;

@Entity
@IdClass(SeatsPk.class)
@Table(name = "Seats")
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
        return CustomerName;
    }

    public void setCustomername(String customername) {
        this.CustomerName = customername;
    }

    public boolean isReserve() {
        return reserve;
    }

    public void setReserve(boolean reserve) {
        this.reserve = reserve;
    }

    public String getFlightid() {
        return FlightId;
    }

    public void setFlightid(String flightid) {
        this.FlightId = flightid;
    }

    @Id
    @Column(name = "FlightId",unique = false)
    private String FlightId;

    @Column(name = "Seat_Number")
    private int number;

    @Column(name = "Status")
    private String status;

    @Id
    @Column(name = "CustomerName",unique = false)
    private String CustomerName;

    @Transient
    private boolean reserve;

    public Seats()
    {
        number = 0;
        status = "Free";
        CustomerName = "Not booked Yet";
        reserve = false;
    }
    public Seats(String flightid,int seatnumber,String customername,String status,Boolean reserve)
    {
        this.FlightId = flightid;
        number = seatnumber;
        this.CustomerName = customername;
        this.status = status;
        this.reserve = reserve;
    }
    Seats(Seats obj)
    {
        this.number = obj.number;
        this.status = obj.status;
        this.CustomerName = obj.CustomerName;
        this.reserve = obj.reserve;
    }
    Seats(int seatnumber)
    {
        this.number = seatnumber;
        status = "Free";
        CustomerName = "Not booked Yet";
        reserve = false;
    }
    public void display()
    {
        System.out.println("\tSeat Number: "+number+"\tSeat Status: "+status+"\tCustomer Name: "+CustomerName+"\tReserve Status: "+reserve);
    }
}
