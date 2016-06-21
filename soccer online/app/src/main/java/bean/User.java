package bean;

/**
 * Created by Thanh Than on 27/04/2016.
 */
public class User {
    private int IDUser;
    private String email;
    private String password;
    private String Name;
    private String Phone;
    private String Address;

    public User() {
    }

   /* public User(String address, String email, int iDUser, String name, String password, String phone) {
        this.address = address;
        this.email = email;
        this.iDUser = iDUser;
        this.name = name;
        this.password = password;
        this.phone = phone;
    }*/

    public int getIDUser() {
        return IDUser;
    }

    public void setIDUser(int IDUser) {
        this.IDUser = IDUser;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
