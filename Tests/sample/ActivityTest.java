package sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {

    Activity testObject = new Activity(5, 6, "Dancing", 8);

    @Test
    void getWeek() {
        assertEquals(5, testObject.getWeek()); //Getting Week value
    }

    @Test
    void setWeek() {
        testObject.setWeek(2);
        assertEquals(2, testObject.getWeek()); //changing week value and testing if it worked
    }

    @Test
    void getDate() {
        assertEquals(6, testObject.getDate());
    }

    @Test
    void setDate() {
        testObject.setDate(13);
        assertEquals(13, testObject.getDate());
    }

    @Test
    void getActivity() {
        assertEquals("Dancing", testObject.getActivity());
    }

    @Test
    void setActivity() {
        testObject.setActivity("Swimming");
        assertEquals("Swimming", testObject.getActivity());
    }

    @Test
    void getPoints() {
        assertEquals(8, testObject.getPoints());
    }

    @Test
    void setPoints() {
        testObject.setPoints(4);
        assertEquals(4, testObject.getPoints());
    }
}