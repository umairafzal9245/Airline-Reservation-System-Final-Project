package BusinessLogic;

import javax.persistence.*;

@Entity
@IdClass(SeatsPk.class)
@Table(name = "Seats")
public class Seats
{
    @Id
    @Column(name = "FlightId")
    private String FlightId;

    @Column(name = "Seat_Number")
    private int number;

    @Column(name = "Status")
    private String status;

    @Id
    @Column(name = "CustomerPassport")
    private Integer CustomerPassport;

    @Transient
    private boolean reserve;

    public Seats()
    {
        number = 0;
        status = "Free";
        CustomerPassport = 0;
        reserve = false;
    }
    public Seats(String flightid,int seatnumber,Integer Passport,String status,Boolean reserve)
    {
        this.FlightId = flightid;
        number = seatnumber;
        this.CustomerPassport = Passport;
        this.status = status;
        this.reserve = reserve;
    }
    Seats(Seats obj)
    {
        this.number = obj.number;
        this.status = obj.status;
        this.CustomerPassport = obj.CustomerPassport;
        this.reserve = obj.reserve;
    }
    Seats(int seatnumber)
    {
        this.number = seatnumber;
        status = "Free";
        CustomerPassport = 0;
        reserve = false;
    }
    public void display()
    {
        System.out.println("\tSeat Number: "+number+"\tSeat Status: "+status+"\tCustomer Passport: "+CustomerPassport+"\tReserve Status: "+reserve);
    }
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

    public Integer getCustomerpassport() {
        return CustomerPassport;
    }

    public void setCustomerpassport(Integer Passport) {
        this.CustomerPassport = Passport;
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
}
