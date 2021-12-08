package BusinessLogic;

import java.io.Serializable;

public class SeatsPk implements Serializable {
    protected String FlightId;
    protected String CustomerName;

    public SeatsPk() {}

    public SeatsPk(String FlightId, String CustomerName) {
        this.FlightId = FlightId;
        this.CustomerName = CustomerName;
    }
}
