package hw2;

/**
 * Utility to determine the cost of parking in the parking garage.
 * This utility calculates the payment due on a parking card based
 * on the time that the card has been active, and also determines
 * the time frame where the card is able to exit the garage.
 * @author nmv
 */
public class RateUtil {
    /** Amount of time a car has to get out of the garage. */
    public static final int EXIT_TIME_LIMIT = 15;

    /** Base payment in dollars per hour due for each time frame. */
    private static final double BASE_FIRST_30_MINUTES = 1.00;
    private static final double BASE_HOUR_1_TO_5 = 2.00;
    private static final double BASE_HOUR_6_TO_8 = 8.00;

    /** Parking Rate in dollars per hour for each time frame. */
    private static final double RATE_HOUR_1_TO_5 = 1.50;
    private static final double RATE_HOUR_6_TO_8 = 1.25;

    /** Variables to be used in ease of calculation */
    private static final int MINUTES_IN_HOUR = 60;
    private static final int HOURS_IN_DAY = 24;
    private static final int MINUTES_IN_DAY = HOURS_IN_DAY * MINUTES_IN_HOUR;

    /**
     * Constructor for RateUtil.
     * This constructor does not perform any actions as the
     * object should never be instantiated, but rather used
     * as a utility.
     */
    private RateUtil() {}

    /**
     * Return the number of complete hours that the car has
     * stayed in the parking garage.
     * @param minutes Number of minutes the car has been in the garage.
     * @return Number of complete (60 minutes) hours the car has been in garage
     */
    private static double getFullHours(int minutes)
    {
        return Math.ceil( (double) minutes / MINUTES_IN_HOUR );
    }

    /**
     * Return the number of days that the cars has stayed in the
     * the parking garage. Days are calculated as any time period
     * that exceeds 8 hours. Once this has happened, the car is
     * charged for the whole day.
     * @param minutes Number of minutes the car has been in the garage.
     * @return Number of days (all days after 8 hours)
     */
    private static int getDays(int minutes)
    {
        // Check if car has been parked over 8 hours for the current day.
        if (minutes % MINUTES_IN_DAY > 8 * MINUTES_IN_HOUR)
        {
            // Return the value for the time as well as the rest of the day.
            return (int) Math.ceil( (double) minutes / MINUTES_IN_DAY );
        }

        else
        {
            // Return just the value for the time.
            return (int) Math.floor( (double) minutes / MINUTES_IN_DAY );
        }
    }

    /**
     * Calculates the cost of parking for the specified time.
     * @param minutes Amount of time in minutes that the car has been parked.
     * @return The cost to the car for parking.
     */
    public static double calculateCost(int minutes)
    {
        if (minutes <= 30)
        {
            return BASE_FIRST_30_MINUTES;
        }
        else if (minutes <= 5 * MINUTES_IN_HOUR)
        {
            return BASE_HOUR_1_TO_5 + (getFullHours(minutes) - 1) * RATE_HOUR_1_TO_5;
        }
        else if (minutes <= 8 * MINUTES_IN_HOUR)
        {
            return BASE_HOUR_6_TO_8 + (getFullHours(minutes) - 5) * RATE_HOUR_6_TO_8;
        }
        else
        {
            int days = getDays(minutes);
            int minutesAccountedFor = days * MINUTES_IN_DAY;
            int currentDayMinutes;

            if (minutes - minutesAccountedFor > 0)
            {
                currentDayMinutes = minutes - ( days * MINUTES_IN_DAY );
                return ( 13.00 * days ) + calculateCost(currentDayMinutes);
            }

            return 13.00 * days;
        }
    }
}
