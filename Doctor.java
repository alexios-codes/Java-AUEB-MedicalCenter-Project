public class Doctor {

    private static int idCounter = 1;
    private int doctorID;
    private int years;
    private String doctorName;
    private String doctorPhone;
    private String specialty;

    // Basic constructor
    public Doctor(String doctorName, String doctorPhone, String specialty, int years) {
        this.doctorID = idCounter++;
        this.doctorName = doctorName;
        this.doctorPhone = doctorPhone;
        this.specialty = specialty;
        this.years = years;
    }
    // Constructor used for files
    public Doctor(int id,String doctorName,String doctorPhone,String specialty,int years) {
        this.doctorID=id;
        this.doctorName = doctorName;
        this.doctorPhone=doctorPhone;
        this.specialty=specialty;
        this.years=years;
        if (id >= idCounter) idCounter = id + 1;
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
        return specialty;
    }

    public String toString() {
        return "Doctor's ID: " + doctorID + " | Doctor's name: " + doctorName + " | Doctor's telephone number: " +
                doctorPhone + " | Doctor's specialty: " + specialty + "| Doctor's years of experience: " + years;
    }
    
}
