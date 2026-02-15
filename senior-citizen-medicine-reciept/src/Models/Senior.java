package Models;

public class Senior {

    private String seniorName;
    private int age;
    private String address;
    private String medicalHistory;
    private String phoneNumber;
    private String emergencyContactName;
    private String emergencyContact;

    public Senior(String seniorName, int age, String address,
                  String medicalHistory, String phoneNumber,
                  String emergencyContactName, String emergencyContact) {

        this.seniorName = seniorName;
        this.age = age;
        this.address = address;
        this.medicalHistory = medicalHistory;
        this.phoneNumber = phoneNumber;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContact = emergencyContact;
    }

    // GETTERS
    public String getSeniorName() { return seniorName; }
    public int getAge() { return age; }
    public String getAddress() { return address; }
    public String getMedicalHistory() { return medicalHistory; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmergencyContactName() { return emergencyContactName; }
    public String getEmergencyContact() { return emergencyContact; }

    // SETTERS
    public void setSeniorName(String seniorName) { this.seniorName = seniorName; }
    public void setAge(int age) { this.age = age; }
}
