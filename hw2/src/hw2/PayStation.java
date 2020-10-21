package hw2;

/**
 * This class models as an object where a person will pay their fee for parking
 * When a user inserts a {@link ParkingCard}, the system will check the balance
 * on the object and the user will pay this. Once the fee is paid, the system
 * will validate the card.
 *
 * @author nmv
 */
public class PayStation {
    /** Total amount of payments the object has collected */
    private double totalPayments;
    /** Determines if a transaction is currently active (car in machine). */
    private boolean inProgress = false;
    /** Reference to the {@link ParkingCard} object held in the machine. */
    private ParkingCard currentCard;
    /** Hold the reference to the machine {@link TimeClock}. */
    private final TimeClock timeClock;

    /**
     * Constructs a new {@link PayStation} object with the given
     * {@link TimeClock} object, and $0.00 money collected.
     * @param givenClock {@link TimeClock} object used by the machine.
     */
    public PayStation(TimeClock givenClock)
    {
        this.timeClock = givenClock;
        this.totalPayments = 0.00;
    }

    /**
     * Inserts the passed {@link ParkingCard} object into the machine and
     * starts a transaction.
     * @param parkingCard {@link ParkingCard} object to be paid.
     */
    public void insertCard(ParkingCard parkingCard)
    {
        if (!this.inProgress)
        {
            this.inProgress = true;
            this.currentCard = parkingCard;
        }
    }

    /**
     * Returns the {@link ParkingCard} currently held in the machine.
     * @return current {@link ParkingCard} object in the machine.
     */
    public ParkingCard getCurrentCard()
    {
        if (!this.inProgress)
        {
            return null;
        }

        return this.currentCard;
    }

    /**
     * Returns the state of the current transaction.
     * @return Whether there is a transaction currently in progress.
     */
    public boolean inProgress()
    {
        return this.inProgress;
    }

    /**
     * Returns the amount of time that has passed since the current
     * {@link ParkingCard} object was created.
     * @return Time elapsed since creation.
     */
    private int timeElapsedSinceInit()
    {
        if (this.currentCard == null)
        {
            return 0;
        }

        return this.timeClock.getTime() - this.currentCard.getStartTime();
    }

    /**
     * Returns the dollar amount that has already been paid on
     * the {@link ParkingCard}.
     * @return Amount already paid
     */
    private double getAmountPaid()
    {
        if (this.currentCard.getPaymentTime() != 0)
        {
            return RateUtil.calculateCost(this.currentCard.getPaymentTime() - this.currentCard.getStartTime());
        }

        return 0.00;
    }

    /**
     * Returns the dollar amount that is currently due on
     * the {@link ParkingCard} object
     * @return Current amount due on {@link ParkingCard}
     */
    public double getPaymentDue()
    {
        if (!this.inProgress)
        {
            return 0.00;
        }

        return RateUtil.calculateCost(timeElapsedSinceInit()) - getAmountPaid();
    }

    /**
     * Simulate the making of a payment on the {@link ParkingCard}.
     * Making a payment will pay off the entirety of the cards' debt.
     */
    public void makePayment()
    {
        if (this.inProgress)
        {
            this.totalPayments += getPaymentDue();
            this.currentCard.setPaymentTime(this.timeClock.getTime());
        }
    }

    /**
     * Ejects the card from the payment terminal, but only if there is
     * a transaction currently in progress.
     */
    public void ejectCard()
    {
        if (this.inProgress)
        {
            this.inProgress = false;
        }
    }

    /**
     * Returns the total payments that the payment terminal has collected
     * in the form of dollar.cents (typical currency) format.
     * @return Total payments the terminal has collected.
     */
    public double getTotalPayments()
    {
        return this.totalPayments;
    }
}
