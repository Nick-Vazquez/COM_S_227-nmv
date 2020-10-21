package hw2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class ExitGateTest
{
    ExitGate exitGate;
    TimeClock timeClock;
    ParkingCard parkingCard;

    @BeforeEach
    void setUp()
    {
        this.timeClock = new TimeClock();
        this.timeClock.timePasses(127);
        this.parkingCard = new ParkingCard(timeClock.getTime());
        this.exitGate = new ExitGate(timeClock);
    }

    @Test
    void simpleValidation()
    {
        this.parkingCard.setPaymentTime(this.timeClock.getTime());
        timeClock.timePasses(8);
        assertTrue(this.exitGate.insertCard(this.parkingCard));
    }

    @Test
    void expiredValidation()
    {
        this.parkingCard.setPaymentTime(timeClock.getTime());
        timeClock.timePasses(16);
        assertFalse(exitGate.insertCard(this.parkingCard));
    }

    @Test
    void testValidExitCount()
    {
        this.parkingCard.setPaymentTime(timeClock.getTime());
        this.timeClock.timePasses(8);
        this.exitGate.insertCard(this.parkingCard);
        assertEquals(1, this.exitGate.getExitCount());
    }

    @Test
    void testInvalidExit()
    {
        this.parkingCard.setPaymentTime(timeClock.getTime());
        timeClock.timePasses(23);
        this.exitGate.insertCard(this.parkingCard);
        assertEquals(0, exitGate.getExitCount());
    }
}