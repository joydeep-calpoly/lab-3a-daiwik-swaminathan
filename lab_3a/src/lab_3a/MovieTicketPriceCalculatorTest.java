package lab_3a;

import static org.junit.Assert.*;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

public class MovieTicketPriceCalculatorTest {

	private MovieTicketPriceCalculator calculator;

    @Before
    public void setUp() {
        // Make a sample MovieTicketPriceCalculator to be used for each test case
        LocalTime startMatineeTime = LocalTime.of(12, 0); // Afternoon shows start at noon
        LocalTime endMatineeTime = LocalTime.of(17, 0); // Afternoon shows end at 5pm
        int maxChildAge = 12;
        int minSeniorAge = 65;

        calculator = new MovieTicketPriceCalculator(startMatineeTime, endMatineeTime, maxChildAge, minSeniorAge);
    }

    @Test
    public void matineeChild() {
        // Time is 1 pm, which is during matinee.
        // It is also a child
        // So we should expect there to be some matinee and child discount 
        LocalTime matineeTime = LocalTime.of(13, 0);
        int age = 10;
        int expectedPrice = 2400 - 300;
        assertEquals(expectedPrice, calculator.computePrice(matineeTime, age));
    }

    @Test
    public void matineeSenior() {
        // Time is 3 pm, which is during matinee.
        // It is also a senior
        // So we should expect there to be a matinee price and senior discount
        LocalTime matineeTime = LocalTime.of(15, 0);
        int age = 70; 
        int expectedPrice = 2400 - 400; 
        assertEquals(expectedPrice, calculator.computePrice(matineeTime, age));
    }

    @Test
    public void matineeAdult() {
        // Time is 2 pm, which is during matinee.
        // It is also an adult
        // So we should expect the matinee price with no discount
        LocalTime matineeTime = LocalTime.of(14, 0);
        int age = 30; 
        int expectedPrice = 2400; 
        assertEquals(expectedPrice, calculator.computePrice(matineeTime, age));
    }

    @Test
    public void standardAdult() {
        // Time is 6 pm, which is after matinee.
        // It is also an adult
        // So we should expect the standard price, no discount
        LocalTime regularTime = LocalTime.of(18, 0);
        int age = 30;
        int expectedPrice = 2700; 
        assertEquals(expectedPrice, calculator.computePrice(regularTime, age));
    }

    @Test
    public void standardChild() {
        // Time is 7 pm, which is after matinee.
        // It is also a child
        // So we should expect the standard price and child discount
        LocalTime regularTime = LocalTime.of(19, 0);
        int age = 8; 
        int expectedPrice = 2700 - 300; 
        assertEquals(expectedPrice, calculator.computePrice(regularTime, age));
    }

    @Test
    public void standardSenior() {
        // Time is 8 pm, which is after matinee.
        // It is also a senior
        // So we should expect the standard price and senior discount
        LocalTime regularTime = LocalTime.of(20, 0);
        int age = 68; 
        int expectedPrice = 2700 - 400; 
        assertEquals(expectedPrice, calculator.computePrice(regularTime, age));
    }

    @Test
    public void discount_Child() {
        // The age is 10, which qualifies for a child discount.
        // We expect the child discount amount.
        int age = 10; 
        int expectedDiscount = 300; 
        assertEquals(expectedDiscount, calculator.computeDiscount(age));
    }

    @Test
    public void discount_Senior() {
        // The age is 70, which qualifies for a senior discount.
        // We expect the senior discount amount.
        int age = 70; 
        int expectedDiscount = 400; 
        assertEquals(expectedDiscount, calculator.computeDiscount(age));
    }

    @Test
    public void discount_Adult() {
        // The age is 30, which does not qualify for any discount.
        // We expect no discount.
        int age = 30; 
        int expectedDiscount = 0; 
        assertEquals(expectedDiscount, calculator.computeDiscount(age));
    }
    
    @Test
    public void scheduledTimeAtStartMatinee() {
        // The scheduled time is exactly at the start of the matinee.
        // We expect the matinee price for an adult.
        LocalTime scheduledTime = LocalTime.of(12, 0); 
        int age = 30; 
        int expectedPrice = 2400; 
        assertEquals(expectedPrice, calculator.computePrice(scheduledTime, age));
    }

    @Test
    public void invalidMatineeTimes() {
        // Define the start time after the end time, which should trigger an exception
        LocalTime startMatineeTime = LocalTime.of(17, 0); 
        LocalTime endMatineeTime = LocalTime.of(12, 0); 
        
        assertThrows(IllegalArgumentException.class, () -> {
            new MovieTicketPriceCalculator(startMatineeTime, endMatineeTime, 12, 65);
        });
    }

    @Test
    public void nullStartMatineeTime() {
        // Ensure an NullPointerException is thrown when startMatineeTime is null
        LocalTime endMatineeTime = LocalTime.of(17, 0);
        
        assertThrows(NullPointerException.class, () -> {
            new MovieTicketPriceCalculator(null, endMatineeTime, 12, 65);
        });
    }

    @Test
    public void nullEndMatineeTime() {
        // Ensure an NullPointerException is thrown when endMatineeTime is null
        LocalTime startMatineeTime = LocalTime.of(12, 0);
        
        assertThrows(NullPointerException.class, () -> {
            new MovieTicketPriceCalculator(startMatineeTime, null, 12, 65);
        });
    }

}
