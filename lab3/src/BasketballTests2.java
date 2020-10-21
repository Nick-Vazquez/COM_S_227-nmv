import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
//import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class BasketballTests2
{
    // margin of error for floating-point comparisons
    private static final double EPSILON = 10e-07;

    private Basketball bb;

    @Before
    public void setup()  // runs before every test case
    {
        bb = new Basketball(5);
    }

    @Test
    public void testInitial()
    {
        Assert.assertFalse(bb.isDribbleable());
    }

    @Test
    public void testInitialDiameter()
    {
        assertEquals(5.0, bb.getDiameter(), EPSILON);
    }

    @Test
    public void testInflate()
    {
        bb.inflate();
        Assert.assertTrue(bb.isDribbleable());
    }

    @Test
    public void testDiameterAfterInflation()
    {
        bb.inflate();
        assertEquals(5.0, bb.getDiameter(), EPSILON);
    }

}