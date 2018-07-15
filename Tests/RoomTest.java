import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    Room room = new Room(101,4,0);

    @Test
    void getSituation() {
        assertEquals(true,room.getSituation());
    }

    @Test
    void bookRoom() {
        assertEquals(true,room.bookRoom());
    }

    @Test
    void cancelRoom() {
        assertEquals(false,room.cancelRoom());
    }

    @Test
    void getCapacity() {
        assertEquals(4,room.getCapacity());
    }

    @Test
    void getRoomNo() {
        assertEquals(101,room.getRoomNo());
    }
}