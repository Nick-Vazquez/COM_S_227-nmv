import balloon4.Balloon;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BalloonTests {

    private Balloon balloon;

    /** Method to be executed before running any tests in class.
     *
     * Before annotated method is ran before all tests in class.
     * This method creates a balloon object to be used in testing
     */
    @Before
    public void setup()
    {
        balloon = new Balloon(10);
    }

    /** Test a negative constructor
     *
     * Construct a Balloon object with a maxRadius of -5.
     * This value should be changed by constructor method.
     * Access to this variable is private, so the best we can
     * test is the current radius.
     */
    @Test
    public void testNegConstructor()
    {
        String nonZeroRadius = "A balloon constructed with a max radius of -5 should have a radius of 0.";
        Balloon balloonTest = new Balloon(-5);
        Assert.assertEquals(nonZeroRadius, 0, balloonTest.getRadius());
    }

    /** Test the initial state of the balloon
     *
     * A newly created balloon of any maxRadius shall have a
     * radius of 0.
     */
    @Test
    public void testInitialRadius()
    {
        String radiusNotZero = "The radius of a newly created balloon should be 0.";
        String balloonNotPopped = "The popped state of a newly created balloon should be false.";
        Assert.assertEquals(radiusNotZero, 0, balloon.getRadius());
        Assert.assertFalse(balloonNotPopped, balloon.isPopped());
    }

    /** Test the initial popped state
     *
     * The popped state of a newly created balloon shall be false.
     */
    @Test
    public void testInitialPop()
    {
        String balloonNotPopped = "The popped state of a newly created balloon should be false.";
        Assert.assertFalse(balloonNotPopped, balloon.isPopped());
    }

    /** Test the blow method
     *
     * A newly created balloon that is blown within its' maxRadius
     * shall have a radius of the amount blown.
     */
    @Test
    public void testBlow()
    {
        String radiusNotCorrect = "The radius of a balloon blown with 5 should be 5.";
        balloon.blow(5);
        Assert.assertEquals(radiusNotCorrect, 5, balloon.getRadius());
    }

    /** Test blowing the balloon twice
     *
     * A balloon blown twice shall have a radius of the sum of the blow amounts.
     */
    @Test
    public void testBlowTwice()
//    balloon3 fails: blow does not add amount to radius, but sets it to the parameter.
    {
        String radiusNotCorrect = "The radius of a balloon blown twice with 5 should be 10.";
        balloon.blow(5);
        balloon.blow(5);
        Assert.assertEquals(radiusNotCorrect, 10, balloon.getRadius());
    }

    /** Test blowing a popped balloon
     *
     * A balloon that has been popped shall not be inflated.
     */
    @Test
    public void testBlowPopped()
//    balloon1 & balloon2 fail: Method inflates balloon even if popped
    {
        String poppedBlown = "A popped balloon should not inflate!";
        balloon.blow(5);
        balloon.pop();
        balloon.blow(5);
        Assert.assertEquals(poppedBlown, 0, balloon.getRadius());
    }

    /** Test the deflation of a balloon
     *
     * A balloon that has been inflated shall have a non-zero radius.
     * This is tested in a previous case. After deflation, this balloon
     * shall have a radius of 0, and shall not be popped.
     */
    @Test
    public void testDeflate()
//    balloon2 & balloon4 fail: a deflated balloon is set to popped
    {
        String deflatedNotZero = "The radius of a deflated balloon should be Zero.";
        String deflatedPopped = "A balloon that has been deflated should not be popped.";
        balloon.blow(3);
        balloon.deflate();
        Assert.assertEquals(deflatedNotZero, 0, balloon.getRadius());
        Assert.assertFalse(deflatedPopped, balloon.isPopped());
    }

    /** Test the blow method with a negative value.
     *
     * A Balloon that is blown with a value <= 0 shall have a radius
     * of 0.
     */
    @Test
    public void testNegativeBlow()
    {
        String radiusNotCorrect = "The radius of a balloon blown with -5 should be 0.";
        balloon.blow(-5);
        Assert.assertEquals(radiusNotCorrect, 0, balloon.getRadius());
    }

    /** Test the Balloon pop method.
     *
     * A Balloon that is inflated and then popped shall have a radius
     * of 0, and should return that it is popped.
     */
    @Test
    public void testPop()
    {
        String poppedRadiusNotZero = "The radius of a popped balloon should be 0.";
        String poppedBalloonTrue = "A popped balloon isPopped() should return True.";
        balloon.blow(3);
        balloon.pop();
        Assert.assertEquals(poppedRadiusNotZero, 0, balloon.getRadius());
        Assert.assertTrue(poppedBalloonTrue, balloon.isPopped());
    }

    /** Test the blow method for a blow too large
     *
     * A call to blow with an {@param amount} that exceed the current
     * capacity of the balloon shall pop the balloon.
     */
    @Test
    public void lungsTooBig()
//    balloon1 fails: balloon over max cap. does not pop
    {
        String balloonNotPopped = "A balloon blown larger than its' max radius should pop";
        balloon.blow(20);
        Assert.assertTrue(balloonNotPopped, balloon.isPopped());
    }
}
