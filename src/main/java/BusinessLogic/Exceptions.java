package BusinessLogic;

class CustomerAlreadyPresentException extends Exception
{
    public CustomerAlreadyPresentException(String message)
    {
        super(message);
    }
}

class CustomerPassportNumberNotFoundException extends Exception {
    public CustomerPassportNumberNotFoundException(String message) {
        super(message);
    }
}

class NoFlightsFoundException extends Exception
{
    public NoFlightsFoundException(String message)
    {
        super(message);
    }
}

class FlightsDuplicateFoundException extends Exception
{
    public FlightsDuplicateFoundException(String message)
    {
        super(message);
    }
}

class LessSeatsAvailableException extends Exception
{
    public LessSeatsAvailableException(String message)
    {
        super(message);
    }
}

class SeatNumberIncorrectException extends Exception
{
    public SeatNumberIncorrectException(String message)
    {
        super(message);
    }
}

class NoCustomerPresentException extends Exception {
    public NoCustomerPresentException(String message) {
        super(message);
    }
}

class PinUnverifiedException extends Exception {
    public PinUnverifiedException(String message) {
        super(message);
    }
}

class FlightIDIncorrectException extends Exception {
    public FlightIDIncorrectException(String message) {
        super(message);
    }
}

class  AlreadyBookedSeatException extends Exception
{
    public AlreadyBookedSeatException(String message)
    {
        super(message);
    }
}

class  LessBalanceException extends Exception
{
    public LessBalanceException(String message)
    {
        super(message);
    }
}

class  NoTicketFoundException extends Exception
{
    public NoTicketFoundException(String message)
    {
        super(message);
    }
}

class InvalidBookingReferenceException extends Exception
{
    public InvalidBookingReferenceException(String message)
    {
        super(message);
    }
}

class NoReservationsFoundException extends Exception
{
    public NoReservationsFoundException(String message)
    {
        super(message);
    }
}

class BookingReferenceNotown extends Exception
{
    public BookingReferenceNotown(String message)
    {
        super(message);
    }
}
