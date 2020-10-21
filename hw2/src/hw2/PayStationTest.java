package hw2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PayStationTest
{
    TimeClock timeClock;
    PayStation payStation;
    ParkingCard parkingCard;

    @BeforeEach
    void setUp()
    {
        this.timeClock = new TimeClock();
        this.timeClock.timePasses(47);
        this.payStation = new PayStation(timeClock);
        this.parkingCard = new ParkingCard(this.timeClock.getTime());
        this.timeClock.timePasses(28);
    }

    @Test
    void insertCard()
    {
        assertFalse(payStation.inProgress());
        payStation.insertCard(this.parkingCard);
        assertTrue(payStation.inProgress());
    }

    @Test
    void getCurrentCard()
    {
        assertNull(payStation.getCurrentCard());
        payStation.insertCard(parkingCard);
        assertThat(payStation.getCurrentCard(), instanceOf(ParkingCard.class));
    }

    @Test
    void inProgress()
    {
        assertFalse(payStation.inProgress());
        payStation.insertCard(parkingCard);
        assertTrue(payStation.inProgress());
    }

    @Test
    void getPaymentDue()
    {
        assertEquals(0.00, payStation.getPaymentDue());
        payStation.insertCard(parkingCard);
        assertEquals(1.00, payStation.getPaymentDue());
        this.timeClock.timePasses(47);
        assertEquals(3.50, payStation.getPaymentDue());
    }

    @Test
    void makePayment()
    {
        assertEquals(0, payStation.getTotalPayments());
        this.payStation.insertCard(parkingCard);
        this.payStation.makePayment();
        assertEquals(1.00, this.payStation.getTotalPayments());
        assertEquals(this.timeClock.getTime(),
                this.parkingCard.getPaymentTime());
    }

    @Test
    void ejectCard()
    {
        this.payStation.ejectCard();
        assertFalse(this.payStation.inProgress());
        this.payStation.insertCard(parkingCard);
        assertTrue(this.payStation.inProgress());
        this.payStation.ejectCard();
        assertFalse(this.payStation.inProgress());
    }

    @Test
    void getTotalPayments()
    {
        assertEquals(0.00, this.payStation.getTotalPayments());
        this.payStation.insertCard(parkingCard);
        this.payStation.makePayment();
        assertEquals(1.00, this.payStation.getTotalPayments());
    }
}