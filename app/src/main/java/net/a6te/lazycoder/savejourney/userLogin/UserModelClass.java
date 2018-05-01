package net.a6te.lazycoder.savejourney.userLogin;

/**
 * Created by ashraf on 18/5/17.
 */

public class UserModelClass {

    private int id;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String userPsss;
    private String EmergencyContact;
    private String address;

    public UserModelClass() {
    }

    public UserModelClass(String userName, String userEmail,String userPhone, String userPsss, String emergencyContact, String address) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userPsss = userPsss;
        EmergencyContact = emergencyContact;
        this.address = address;
    }

    public UserModelClass(int id, String userName, String userEmail, String userPhone, String userPsss, String emergencyContact, String address) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userPsss = userPsss;
        EmergencyContact = emergencyContact;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPsss() {
        return userPsss;
    }

    public void setUserPsss(String userPsss) {
        this.userPsss = userPsss;
    }

    public String getEmergencyContact() {
        return EmergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        EmergencyContact = emergencyContact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
