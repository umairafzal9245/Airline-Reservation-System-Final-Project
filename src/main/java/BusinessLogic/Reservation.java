package BusinessLogic;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "Reservations")
public class Reservation
{
    public int getBookingreference() {
        return bookingreference;
    }

    public void setBookingreference(int bookingreference) {
        this.bookingreference = bookingreference;
    }

    public String getFlightid() {
        return flightid;
    }

    public void setFlightid(String flightid) {
        this.flightid = flightid;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public Payments getPayment() {
        return payment;
    }

    public void setPayment(Payments payment) {
        this.payment = payment;
    }

    public Tickets getTicket() {
        return ticket;
    }

    public void setTicket(Tickets ticket) {
        this.ticket = ticket;
    }


    @Id
    @Column(name = "Booking_Reference")
    private int bookingreference;

    @Column(name = "Flight_Id")
    private String flightid;

    @Column(name = "Customer_Name")
    private String customername;

    @OneToOne(cascade = CascadeType.ALL)
    private Payments payment;

    @OneToOne(cascade = CascadeType.ALL)
    private Tickets ticket;

    public Reservation()
    {
        payment = new Payments();
        ticket = new Tickets();
        bookingreference = 0;
    }
    public void display(int serial, ArrayList<Integer> seatnumbers)
    {
        System.out.println("\nReservations "+serial+" Information:");
        System.out.println("\tBooking Reference: "+bookingreference+"\t\tCustomer Name: "+customername);
        ticket.Display(flightid,seatnumbers);
        payment.display();

    }

}
