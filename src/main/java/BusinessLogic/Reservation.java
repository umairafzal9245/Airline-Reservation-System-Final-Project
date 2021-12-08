package BusinessLogic;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "Reservations")
public class Reservation
{
    @Id
    @Column(name = "Booking_Reference")
    private int bookingreference;

    @Column(name = "Flight_Id")
    private String flightid;

    @Column(name = "Customer_Passport")
    private Integer customerpassport;

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
        System.out.println("\tBooking Reference: "+bookingreference+"\t\tCustomer Passport: "+customerpassport);
        ticket.Display(flightid,seatnumbers);
        payment.display();

    }
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

    public Integer getCustomerPassport() {
        return customerpassport;
    }

    public void setCustomerPassport(Integer customerpass) {
        this.customerpassport = customerpass;
    }

    public Payments getPayment() {
        return payment;
    }

    public Tickets getTicket() {
        return ticket;
    }
}
