package hw1;

/**
 * Class to hold data for backpacking locations
 *
 * @author Nick Vazquez
 */
public class Location
{
    /** Cost of postcard relative to the cost of the location */
    public static final double RELATIVE_COST_OF_POSTCARD= 0.05;
    /** Name of the location. */
    private final String locationName;
    /** How much it costs per night to stay at location. */
    private final int lodgingCost;

    /**
     * Constructor for a backpacking location.
     *
     * @param givenName Name of the hw1.Location
     * @param givenLodgingCost Cost/night of location
     */
    public Location(String givenName, int givenLodgingCost)
    {
        this.locationName = givenName;
        this.lodgingCost = givenLodgingCost;
    }

    /**
     * Get the name of the instance location.
     * @return hw1.Location name
     */
    public String getName()
    {
        return this.locationName;
    }

    /**
     * Getter method for instance lodging cost
     * @return Lodging cost for instance location
     */
    public int lodgingCost()
    {
        return this.lodgingCost;
    }

    /**
     * Calculates the cost to send a postcard from the instance location
     * @return Cost to send a postcard
     */
    public int costToSendPostcard()
    {
        return Math.round( (float) (this.lodgingCost * RELATIVE_COST_OF_POSTCARD) );
    }

    /**
     * Calculates the maximum time a traveler can stay at a location
     * @param funds Amount of money traveler has
     * @return Maximum number of nights traveler can stay
     */
    public int maxLengthOfStay(int funds)
    {
        return funds / this.lodgingCost;
    }

    /**
     * Calculates the maximum number of postcards a traveler can send
     * @param funds Amount of money traveler has
     * @return Maximum amount of postcards available to send
     */
    public int maxNumberOfPostcards(int funds)
    {
        return funds / this.costToSendPostcard();
    }
}
