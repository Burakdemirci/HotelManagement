import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReceptionistTest {

    Receptionist receptionist = new Receptionist("burak",12345,"burak123");

    @Test
    void getName() {
        assertEquals("burak",receptionist.getName());
    }

    @Test
    void getId() {
        assertEquals(12345,receptionist.getId());
    }

    @Test
    void getPassword() {
        assertEquals("burak123",receptionist.getPassword());
    }

    @Test
    void setPassword() {
        assertEquals(true,receptionist.setPassword("burak1354"));
    }
}