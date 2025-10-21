package contactservice;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    @Test
    void createValidContact() {
        Contact c = new Contact("id123", "Alice", "Smith", "1234567890", "123 Main St");
        assertEquals("id123", c.getContactId());
        assertEquals("Alice", c.getFirstName());
        assertEquals("Smith", c.getLastName());
        assertEquals("1234567890", c.getPhone());
        assertEquals("123 Main St", c.getAddress());
    }

    @Test
    void idCannotBeNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact(null, "A", "B", "1234567890", "Addr"));
    }

    @Test
    void idMaxLength() {
        String longId = "ABCDEFGHIJK"; // 11 chars
        assertThrows(IllegalArgumentException.class,
                () -> new Contact(longId, "A", "B", "1234567890", "Addr"));
    }

    @Test
    void firstNameValidation() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("id1", null, "B", "1234567890", "Addr"));
        String longName = "abcdefghijkl"; // 12 chars
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("id2", longName, "B", "1234567890", "Addr"));
    }

    @Test
    void lastNameValidation() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("id3", "A", null, "1234567890", "Addr"));
        String longName = "abcdefghijkl"; // 12 chars
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("id4", "A", longName, "1234567890", "Addr"));
    }

    @Test
    void phoneValidation() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("id5", "A", "B", null, "Addr"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("id6", "A", "B", "123", "Addr")); // too short
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("id7", "A", "B", "12345678901", "Addr")); // too long
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("id8", "A", "B", "12345abcde", "Addr")); // non-digits
    }

    @Test
    void addressValidation() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("id9", "A", "B", "1234567890", null));
        String longAddress = "This address is definitely longer than thirty characters.";
        assertTrue(longAddress.length() > 30);
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("id10", "A", "B", "1234567890", longAddress));
    }

    @Test
    void contactIdNotUpdatable_noSetter() {
        // Ensure there's no public setContactId method
        Method[] methods = Contact.class.getMethods();
        for (Method m : methods) {
            assertFalse(m.getName().equals("setContactId"),
                    "Contact class should not have setContactId method.");
        }
    }

    @Test
    void settersWorkForUpdatableFields() {
        Contact c = new Contact("id11", "A", "B", "1234567890", "Addr");
        c.setFirstName("Bob");
        c.setLastName("Jones");
        c.setPhone("0987654321");
        c.setAddress("New Address");
        assertEquals("Bob", c.getFirstName());
        assertEquals("Jones", c.getLastName());
        assertEquals("0987654321", c.getPhone());
        assertEquals("New Address", c.getAddress());
    }
}
