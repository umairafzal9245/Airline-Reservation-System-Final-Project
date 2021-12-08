package BusinessLogic;

public class Admin
{
    private String name;
    private int loginpin;
    private boolean logincheck;

    Admin()
    {
        name = "admin";
        loginpin = 1234;
        logincheck = false;
    }
    public void registeraccount(String name,int loginpin)
    {
        this.name = name;
        this.loginpin = loginpin;
        System.out.println("\n\tAdmin got registered!!!");
    }
    public void checkpin(int loginpin) throws PinUnverifiedException {
        if(this.loginpin == loginpin)
            logincheck = true;
        else
            throw new PinUnverifiedException("\n\tLogin pin "+loginpin+" is not correct");
    }
    public boolean isLogin()
    {
        return logincheck;
    }

    public void loginanaccount(int loginpin) throws PinUnverifiedException {
        checkpin(loginpin);
        if(isLogin())
        {
            System.out.println("\n\tThe admin succefully logged in ");
        }
    }
    public void logout()
    {
        if(logincheck)
            System.out.println("Admin account succesfully logged out");
        logincheck = false;
    }
    public void display() {
        System.out.println("\n\tName: "+this.name);
        System.out.println("\tLogin pin: "+this.loginpin);
        System.out.println("\tLogin Status: "+this.logincheck);
        System.out.print("\n");
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLoginpin() {
        return loginpin;
    }

}
