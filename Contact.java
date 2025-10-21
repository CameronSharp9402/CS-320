package contactservice;

import java.util.Objects;

/**
 * Contact model that enforces validation rules:
 * - contactId: required, unique (enforced by service), not null, max length 10, not updatable (no setter)
 * - firstName: required, not null, max length 10
 * - lastName: required, not null, max length 10
 * - phone: required, not null, exactly 10 digits
 * - address: required, not null, max length 30
 */
public class Contact {
    private final String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        validateId(contactId);
        validateFirstName(firstName);
        validateLastName(lastName);
        validatePhone(phone);
        validateAddress(address);

        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    /* Validation helpers */
    private void validateId(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Contact ID cannot be null.");
        }
        if (id.length() > 10) {
            throw new IllegalArgumentException("Contact ID cannot be longer than 10 characters.");
        }
    }

    private void validateFirstName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("First name cannot be null.");
        }
        if (name.length() > 10) {
            throw new IllegalArgumentException("First name cannot be longer than 10 characters.");
        }
    }

    private void validateLastName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Last name cannot be null.");
        }
        if (name.length() > 10) {
            throw new IllegalArgumentException("Last name cannot be longer than 10 characters.");
        }
    }

    private void validatePhone(String phone) {
        if (phone == null) {
            throw new IllegalArgumentException("Phone cannot be null.");
        }
        if (!phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("Phone must be exactly 10 digits.");
        }
    }

    private void validateAddress(String address) {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null.");
        }
        if (address.length() > 30) {
            throw new IllegalArgumentException("Address cannot be longer than 30 characters.");
        }
    }

    /* Getters */
    public String getContactId() {
        return contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    /* Setters for updatable fields */
    public void setFirstName(String firstName) {
        validateFirstName(firstName);
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        validateLastName(lastName);
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        validatePhone(phone);
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        validateAddress(address);
        this.address = address;
    }

    /* equals/hashCode for easier testing and collections use */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;
        return Objects.equals(contactId, contact.contactId) &&
                Objects.equals(firstName, contact.firstName) &&
                Objects.equals(lastName, contact.lastName) &&
                Objects.equals(phone, contact.phone) &&
                Objects.equals(address, contact.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, firstName, lastName, phone, address);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId='" + contactId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
