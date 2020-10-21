package hw2;

/**
 * This class represents a machine at the entrance to a parking garage that
 * dispenses a card, or ticket, with a magnetic stripe encoded with the time
 * that card was dispensed. The time is assumed to be an integer in minutes.
 *
 * @author nmv
 */
public class CardDispenser {
    /** An object that the Card Dispenser uses to set the card's time dispensed */
    private final TimeClock timeClock;

    /**
     * Constructs a CardDispenser with the given TimeClock object.
     * @param givenClock TimeClock object that is used for encoding tickets.
     */
    public CardDispenser(TimeClock givenClock)
    {
        this.timeClock = givenClock;
    }

    /**
     * Creates and returns a {@link ParkingCard} object with time
     * time dispensed encoded on the magnetic stripe.
     * @return New {@link ParkingCard} object.
     */
    public ParkingCard takeCard()
    {
        return new ParkingCard(timeClock.getTime());
    }
}
