public class Patient {
    private static int idCounter = 1 ;

    private int patientID;
    private String patientName ;
    private String patientPhone;
    private String email;
    public Patient(String patientName,String patientPhone,String email){
        this.patientID = idCounter++;
        this.patientName = patientName;
        this.patientPhone=patientPhone;
        this.email=email;

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
        return "Kwdikos asthenh: " + patientID + " | Onoma asthenh: " + patientName + " | Thlefwno asthenh: " + patientPhone
                + " | Email asthenh: " + email;
    }
    
}
