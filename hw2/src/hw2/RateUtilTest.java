package hw2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RateUtilTest
{
    @Test
    public void tenMinutes()
    {
        double cost = RateUtil.calculateCost(10);
        assertEquals(1.00, cost, 0.001);
    }

    @Test
    public void thirtyMinutes()
    {
        double cost = RateUtil.calculateCost(30);
        assertEquals(1.00, cost, 0.001);
    }

    @Test
    public void thirtyOneMinutes()
    {
        double cost = RateUtil.calculateCost(31);
        assertEquals(2.00, cost, 0.001);
    }

    @Test
    public void twoFiftyMinutes()
    {
        double cost = RateUtil.calculateCost(250);
        assertEquals(8.00, cost, 0.001);
    }

    @Test
    public void oneThousandMinutes()
    {
        double cost = RateUtil.calculateCost(1000);
        assertEquals(13.00, cost, 0.001);
    }

    @Test
    public void threeThousandMinutes()
    {
        double cost = RateUtil.calculateCost(3000);
        assertEquals(29.5, cost, 0.001);
    }
}
