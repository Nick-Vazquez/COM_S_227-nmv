package hw2;

/**
 * This class represents an exit gate located at the exit of the garage.
 * When a card is inserted into the machine, the machine checks to see
 * if the card has been validated within the
 * {@value hw2.RateUtil#EXIT_TIME_LIMIT} minute validation period.
 * If it has been, the car is allowed to exit. If not, the customer has
 * to pay more at the pay station.
 * @author nmv
 */
public class ExitGate {
    /** Class variable to hold the TimeClock object passed when constructed */
    private final TimeClock timeClock;
    /** Amount of cars that have exited the garage */
    private int exitCount = 0;

    /**
     * Constructs a new {@link ExitGate} object.
     * @param givenClock {@link ExitGate} object to be used by the object.
     */
    public ExitGate(TimeClock givenClock)
    {
        this.timeClock = givenClock;
    }

    /**
     * Determines if a car is allowed to leave the garage based on
     * the current time, when the card was validated, and the
     * value of {@link RateUtil#EXIT_TIME_LIMIT}.
     *
     * @param parkingCard {@link ParkingCard} object to check.
     */
    public boolean insertCard(ParkingCard parkingCard)
    {
        if (this.timeClock.getTime() - parkingCard.getPaymentTime() < RateUtil.EXIT_TIME_LIMIT)
        {
            exitCount += 1;
            return true;
        }

        return false;
    }

    /**
     * Returns the {@link #exitCount} of the {@link ExitGate} object.
     * @return Number of cars exited.
     */
    public int getExitCount()
    {
        return this.exitCount;
    }
}
