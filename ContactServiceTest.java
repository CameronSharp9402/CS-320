package contactservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactServiceTest {
    private ContactService service;
    private Contact contact1;
    private Contact contact2;

    @BeforeEach
    void setUp() {
        service = new ContactService();
        contact1 = new Contact("c1", "Alice", "Smith", "1234567890", "1 Road");
        contact2 = new Contact("c2", "Bob", "Jones", "0987654321", "2 Lane");
    }

    @Test
    void addContactSuccessfully() {
        service.addContact(contact1);
        assertTrue(service.getAllContacts().containsKey("c1"));
        assertEquals(contact1, service.getAllContacts().get("c1"));
    }

    @Test
    void addingNullContactThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.addContact(null));
    }

    @Test
    void addingDuplicateIdThrows() {
        service.addContact(contact1);
        Contact duplicate = new Contact("c1", "X", "Y", "1112223333", "Addr");
        assertThrows(IllegalArgumentException.class, () -> service.addContact(duplicate));
    }

    @Test
    void deleteContactSuccessfully() {
        service.addContact(contact1);
        service.deleteContact("c1");
        assertFalse(service.getAllContacts().containsKey("c1"));
    }

    @Test
    void deleteWithNullIdThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact(null));
    }

    @Test
    void deleteNonExistentThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("nope"));
    }

    @Test
    void updateFirstName() {
        service.addContact(contact1);
        service.updateFirstName("c1", "Zoe");
        assertEquals("Zoe", service.getAllContacts().get("c1").getFirstName());
    }

    @Test
    void updateLastName() {
        service.addContact(contact1);
        service.updateLastName("c1", "NewLast");
        assertEquals("NewLast", service.getAllContacts().get("c1").getLastName());
    }

    @Test
    void updatePhoneValidAndInvalid() {
        service.addContact(contact1);
        service.updatePhone("c1", "1112223333");
        assertEquals("1112223333", service.getAllContacts().get("c1").getPhone());

        // invalid phone
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("c1", "badphone"));
    }

    @Test
    void updateAddress() {
        service.addContact(contact1);
        service.updateAddress("c1", "New Street 123");
        assertEquals("New Street 123", service.getAllContacts().get("c1").getAddress());
    }

    @Test
    void updateNonExistentContactThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("no", "X"));
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("no", "Y"));
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("no", "1234567890"));
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress("no", "Addr"));
    }

    @Test
    void updateNumberAliasWorks() {
        service.addContact(contact2);
        service.updateNumber("c2", "2223334444");
        assertEquals("2223334444", service.getAllContacts().get("c2").getPhone());
    }
}
