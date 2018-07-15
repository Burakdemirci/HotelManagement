import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuestTest {

    Guest guest =new Guest("burak",12345,3,101);

    @Test
    void getId() {
        assertEquals(12345,guest.getId());
    }

    @Test
    void getName() {
        assertEquals("burak",guest.getName());
    }

    @Test
    void getCapacity() {
        assertEquals(3,guest.getCapacity());
    }

    @Test
    void getRoomNo() {
        assertEquals(101,guest.getRoomNo());
    }

    @Test
    void setName() {
        assertEquals(true,guest.setName("burakT"));
    }

    @Test
    void setRoomNo() {

    }

    @Test
    void setID() {
        assertEquals(true,guest.setID(1011));
    }
}