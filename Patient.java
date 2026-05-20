public class Patient {
    private static int idCounter = 1 ;

    private int patientID;
    private String patientName ;
    private String patientPhone;
    private String email;

    // Basic constructor
    public Patient(String patientName, String patientPhone, String email) {
        this.patientID = idCounter++;
        this.patientName = patientName;
        this.patientPhone = patientPhone;
        this.email = email;
    }
    // Constructor used for files
    public Patient(int id,String patientName,String patientPhone,String email){
        this.patientID = id;
        this.patientName = patientName;
        this.patientPhone=patientPhone;
        this.email=email;
        if (id >= idCounter) idCounter = id + 1;

    }

    //getters
    public int getPatientID() {
        return patientID;
    }
    public String getPatientName() {
        return patientName;
    }
    public String getPatientPhone() {
        return patientPhone;
    }
    public String getEmail() {
        return email;
    }



    public String toString() {
        return "Patient's ID: " + patientID + " | Patient's name: " + patientName + " | Patient's phone number: " + patientPhone
                + " | Patient's email: " + email;
    }
    
}
