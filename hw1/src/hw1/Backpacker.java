package hw1;

public class Backpacker {

    /** How much the backpacker receives whenever they call home */
    public static final int SYMPATHY_FACTOR = 30;
    /** Amount of funds available to backpacker */
    private int currentFunds;
    /** Number of nights backpacker has spent in train station */
    private int nightsInTrainStation;
    /** Amount of postcards sent by backpacker */
    private int postcardsSent;
    /** The current location of the backpacker */
    private Location currentLocation;
    /** Journal of places backpacker has visited */
    private String journal;

    /**
     * Constructor for a hw1.Backpacker, who can move around to different locations.
     * hw1.Backpacker performs transactions that alter location and current money held.
     *
     * @param initialFunds Funds available to backpacker at start of trip
     * @param initialLocation hw1.Location of backpacker at start of trip
     */
    public Backpacker(int initialFunds, Location initialLocation)
    {
        this.currentFunds = initialFunds;
        this.currentLocation = initialLocation;
        this.journal = String.format("%s(start)", currentLocation.getName());
    }

    /** Getter method to retrieve the current location of the backpacker */
    public String getCurrentLocation()
    {
        return this.currentLocation.getName();
    }

    /** Getter method to retrieve the current amount of funds held by hw1.Backpacker */
    public int getCurrentFunds()
    {
        return this.currentFunds;
    }

    /** Getter method to retrieve the journal of locations backpacker has traveled to */
    public String getJournal()
    {
        return this.journal;
    }

    /** Checks to see if the backpacker has enough funds to send a postcard */
    public boolean isSOL()
    {
        return currentLocation.costToSendPostcard() > this.currentFunds;
    }

    /** Getter method to retrieve the amount of nights the backpacker has spent in train station */
    public int getTotalNightsInTrainStation()
    {
        return this.nightsInTrainStation;
    }

    /** Method to visit a location.
     * hw1.Backpacker visits the location, and either spends the most nights available in lodging.
     * If backpacker does not have enough funds for lodging for the night, that night is spent in the train station.
     * @param givenLocation hw1.Location the backpacker travels to
     * @param numNights Amount of nights backpacker spends at location
     */
    public void visit(Location givenLocation, int numNights)
    {
        this.journal = this.journal.concat(String.format(",%s(%d)", givenLocation.getName(), numNights));

        this.currentLocation = givenLocation;

        for (int night = 0; night < numNights; night++ )
        {
            if (currentFunds > givenLocation.lodgingCost())
            {
                this.currentFunds -= givenLocation.lodgingCost();
            }
            else
            {
                this.nightsInTrainStation += 1;
            }
        }
    }

    /** Send a post card home
     * Checks to see if backpacker has enough money to send {@code howMany}
     * postcards home, and sends as many as possible up to {@code howMany}
     * @param howMany How many cards to send home
     */
    public void sendPostcardsHome(int howMany)
    {
        for ( int card = 0; card < howMany; card++ )
        {
            if ( !isSOL() )
            {
                this.currentFunds -= this.currentLocation.costToSendPostcard();
                this.postcardsSent += 1;
            }
        }
    }

    /** hw1.Backpacker calls home for money
     * Current funds are increased by {@code SYMPATHY_FACTOR} multiplied by number of cards sent home
     * Resets postcardsSent to 0
     */
    public void callHomeForMoney()
    {
        this.currentFunds += SYMPATHY_FACTOR * this.postcardsSent;
        this.postcardsSent = 0;
    }
}
