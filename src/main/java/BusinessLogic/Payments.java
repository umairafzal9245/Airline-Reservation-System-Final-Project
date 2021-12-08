package BusinessLogic;

import javafx.beans.value.ObservableValue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Payments")
public class Payments
{

    public int getBookingreference() {
        return bookingreference;
    }

    public void setBookingreference(int bookingreference) {
        this.bookingreference = bookingreference;
    }

    @Id
    @Column(name = "Booking_Reference")
    public int bookingreference;

    @Column(name = "CardHolder_Name")
    private String cardholdername;

    @Column(name = "Card_Number")
    private String cardnumber;

    @Column(name = "Expiry_Date")
    private String expirydate;

    @Column(name = "CVV")
    private int cvv;

    public Payments()
    {
        cardholdername = "";
        cardnumber = "";
        expirydate = "";
        cvv = 0;
    }
    Payments(String cardholder,String cardnumber,String expiry,int cvv)
    {
        this.cardholdername = cardholder;
        this.cardnumber = cardnumber;
        this.expirydate = expiry;
        this.cvv = cvv;
    }

    public void addpayment(String cardholder,String cardnumber,String expiry,int cvv)
    {
        this.cardholdername = cardholder;
        this.cardnumber = cardnumber;
        this.expirydate = expiry;
        this.cvv = cvv;
    }
    public void display()
    {
        System.out.println("\n\tPayment Information: ");
        System.out.println("\tCardHolder Name: "+cardholdername+"\t\tCard Number: "+cardnumber);
        System.out.println("\tValid Till: "+expirydate+"\t\tCVV: "+cvv);
    }
    public String getCardholdername() {
        return cardholdername;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public Integer getCvv() {
        return cvv;
    }
}
