package dam.android.raul.u5_t6.model;

public class Item {
    private String id;
    private String name;
    private String number;
    private String lookup;
    private String rawContact;
    private String type;
    private String photo;

    public Item(String id, String name, String number, String lookup, String rawContact, String type, String photo) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.lookup = lookup;
        this.rawContact = rawContact;
        this.type = type;
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLookup() {
        return lookup;
    }

    public void setLookup(String lookup) {
        this.lookup = lookup;
    }

    public String getRawContact() {
        return rawContact;
    }

    public void setRawContact(String rawContact) {
        this.rawContact = rawContact;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
