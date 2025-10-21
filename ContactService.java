package contactservice;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * In-memory contact service.
 * Provides add, delete, and update operations by contact ID.
 */
public class ContactService {
    private final Map<String, Contact> contacts = new HashMap<>();

    /**
     * Adds a contact to the service.
     * @throws IllegalArgumentException if contact is null, id null, or id already exists.
     */
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null.");
        }
        String id = contact.getContactId();
        if (id == null) {
            throw new IllegalArgumentException("Contact ID cannot be null.");
        }
        if (contacts.containsKey(id)) {
            throw new IllegalArgumentException("Contact with this ID already exists: " + id);
        }
        contacts.put(id, contact);
    }

    /**
     * Deletes a contact by ID.
     * @throws IllegalArgumentException if id is null or contact not found.
     */
    public void deleteContact(String contactId) {
        if (contactId == null) {
            throw new IllegalArgumentException("Contact ID cannot be null.");
        }
        if (!contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("Contact ID not found: " + contactId);
        }
        contacts.remove(contactId);
    }

    /**
     * Update first name for the given contact ID.
     */
    public void updateFirstName(String contactId, String newFirstName) {
        Contact c = getContactForUpdate(contactId);
        c.setFirstName(newFirstName);
    }

    /**
     * Update last name for the given contact ID.
     */
    public void updateLastName(String contactId, String newLastName) {
        Contact c = getContactForUpdate(contactId);
        c.setLastName(newLastName);
    }

    /**
     * Update phone number for the given contact ID.
     */
    public void updatePhone(String contactId, String newPhone) {
        Contact c = getContactForUpdate(contactId);
        c.setPhone(newPhone);
    }

    /**
     * Alias to satisfy spec wording "Number"
     */
    public void updateNumber(String contactId, String newPhone) {
        updatePhone(contactId, newPhone);
    }

    /**
     * Update address for the given contact ID.
     */
    public void updateAddress(String contactId, String newAddress) {
        Contact c = getContactForUpdate(contactId);
        c.setAddress(newAddress);
    }

    /**
     * Returns an unmodifiable view of current contacts (useful for testing/inspection).
     */
    public Map<String, Contact> getAllContacts() {
        return Collections.unmodifiableMap(contacts);
    }

    private Contact getContactForUpdate(String contactId) {
        if (contactId == null) {
            throw new IllegalArgumentException("Contact ID cannot be null.");
        }
        Contact c = contacts.get(contactId);
        if (c == null) {
            throw new IllegalArgumentException("Contact ID not found: " + contactId);
        }
        return c;
    }
}
