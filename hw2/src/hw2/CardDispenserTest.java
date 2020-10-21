package hw2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

class CardDispenserTest {

    private TimeClock timeClock;
    private CardDispenser cardDispenser;

    @BeforeEach
    public void setUp()
    {
        this.timeClock = new TimeClock();
        this.cardDispenser = new CardDispenser(this.timeClock);
        assertNotNull(cardDispenser);
        assertThat(cardDispenser, instanceOf(CardDispenser.class));
    }

    @Test
    void takeCard()
    {
        ParkingCard parkingCard = this.cardDispenser.takeCard();
        assertNotNull(parkingCard);
    }
}