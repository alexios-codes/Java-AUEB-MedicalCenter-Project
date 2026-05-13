public class Doctor {

    private static int idCounter = 1;

    private int doctorID;
    private int years;
    private String doctorName;
    private String doctorPhone;
    private String speciality ;

    public Doctor(String doctorName,String doctorPhone,String speciality,int years) {
        this.doctorID = idCounter++;
        this.doctorName = doctorName;
        this.doctorPhone=doctorPhone;
        this.speciality=speciality;
        this.years=years;
    }

    // getters
    public int getDoctorID() {
        return doctorID;
    }
    public int getYears() {
        return years;
    }
    public String getDoctorName() {
        return doctorName;
    }
    public String getDoctorPhone() {
        return doctorPhone;
    }
    public String getSpeciality() {
        return speciality;
    }

    public String toString() {
        return "Kwdikos iatrou: " + doctorID + " | Onoma iatrou: " + doctorName + " | Thlefwno iatrou: " +
                doctorPhone + " | Eidikothta iatrou: " + speciality + "| Xronia empeirias: " + years;
    }
    
}
