package BusinessLogic;

import javafx.beans.value.ObservableValue;

public class Payments
{


    private String cardholdername;
    private String cardnumber;
    private String expirydate;
    private int cvv;

    Payments()
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
