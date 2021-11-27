package BusinessLogic;

public class Customer
{
    private String name;
    private String gender;
    private int age;
    private String address;
    private String passport_number;
    private int loginpin;
    public boolean logincheck;

    public Customer()
    {
        name = "";
        gender = "";
        age = 0;
        address = "";
        passport_number = "";
        loginpin = 0;
        logincheck = false;
    }
    public Customer(String name,String gender,int age,String address,String passport_number,int loginpin)
    {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.passport_number = passport_number;
        this.loginpin = loginpin;
        logincheck = false;
    }
    public void checkpin(int loginpin) throws PinUnverifiedException {
        if(this.loginpin == loginpin)
            logincheck = true;
        else
            throw new PinUnverifiedException("\n\tLogin pin is not correct");
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

    public String getPassport_number() {
        return passport_number;
    }

}
