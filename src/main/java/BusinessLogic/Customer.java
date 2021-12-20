package BusinessLogic;

import javax.persistence.*;

@Entity
@Table(name = "Customers")
public class Customer
{
    @Column(name = "Name")
    private String name;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "Age")
    private int age;

    @Column(name = "Address")
    private String address;

    @Id
    @Column(name = "Passport_Number")
    private Integer passport_number;

    @Column(name = "Login_Pin")
    private int loginpin;

    private Double balance;

    @Transient
    private boolean logincheck;

    public Customer()
    {
        name = "";
        gender = "";
        age = 0;
        address = "";
        passport_number = 0;
        loginpin = 0;
        logincheck = false;
    }
    public Customer(String name, String gender, int age, String address, Integer passport_number, int loginpin, boolean login,Double bal)
    {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.passport_number = passport_number;
        this.loginpin = loginpin;
        this.balance = bal;
        logincheck = login;
    }
    public void checkpin(int loginpin) throws PinUnverifiedException {
        if(this.loginpin == loginpin)
            logincheck = true;
        else
            throw new PinUnverifiedException("Login pin is not correct");
    }
    public boolean isLogin()
    {
        return logincheck;
    }
    public void display()
    {
        System.out.println("\n\tName: "+name);
        System.out.println("\tGender: "+gender);
        System.out.println("\tAge: "+age);
        System.out.println("\tAddress: "+address);
        System.out.println("\tPassport Number: "+passport_number);
        System.out.println("\tLogin Pin: "+loginpin);
        System.out.println("\tLogin Status: "+logincheck+"\n");
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public int getLoginpin() {
        return loginpin;
    }

    public String getAddress() {
        return address;
    }

    public Integer getPassport_number() {
        return passport_number;
    }

    public void setlogin(boolean login)
    {
        this.logincheck = login;
    }
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
