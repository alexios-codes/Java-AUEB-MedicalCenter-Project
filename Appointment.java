
    public class Appointment {
 
    private static int idCounter = 1;
    private int appointmentID;
    private int patientID;
    private int examID;
    private boolean fastResults;
    private String examDate;

    // Basic constructor
    public Appointment(int patientID, int examID, boolean fastResults, String examDate) {
            this.appointmentID = idCounter++;
            this.patientID = patientID;
            this.examID = examID;
            this.fastResults = fastResults;
            this.examDate = examDate;
    }

 // Constructor used for files
    public Appointment(int id,int patientID, int examID, boolean fastResults, String examDate) {
        this.appointmentID = id;
        this.patientID = patientID;
        this.examID = examID;
        this.fastResults = fastResults;
        this.examDate = examDate;
        if (id >= idCounter) idCounter = id + 1;

    }

    // getters
    public int getAppointmentID() {
        return appointmentID;
    }
    public int getPatientID() {
        return patientID;
    }
    public int getExamID() {
        return examID;
    }
    public String getExamDate() {
        return examDate;
    }
    public boolean getFastResults() {
        return fastResults;
    }


    public String toString() {
        return "Appointment ID: " + appointmentID + " | Patient's ID: " +
                patientID + " | Exam ID: " + examID + " | Fast results status: " + fastResults + "| Exam date: " + examDate;
    }
}

