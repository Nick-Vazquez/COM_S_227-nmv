import org.junit.Test;

import static org.junit.Assert.*;

public class BasketballTests
{
    // margin of error for floating-point comparisons
    private static final double EPSILON = 10e-07;

    @Test
    public void testInitial()
    {
        Basketball b = new Basketball(5);
        assertFalse(b.isDribbleable());
    }

    @Test
    public void testInitialDiameter()
    {
        Basketball b = new Basketball(5);
        assertEquals(5.0, b.getDiameter(), EPSILON);
    }

    @Test
    public void testInflate()
    {
        Basketball b = new Basketball(5);
        b.inflate();
        assertTrue(b.isDribbleable());
    }

    @Test
    public void testDiameterAfterInflation()
    {
        Basketball b = new Basketball(5);
        b.inflate();
        assertEquals(5.0, b.getDiameter(), EPSILON);
    }

}
