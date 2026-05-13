
    public class Appointment {
 
    private static int idCounter = 1;
 
    private int appointmentID;
    private int patientID;
    private int examID;
    private boolean fastResults;
    private String examDate;
 
    public Appointment(int patientID, int examID, boolean fastResults, String examDate) {
        this.appointmentID = idCounter++;
        this.patientID = patientID;
        this.examID = examID;
        this.fastResults = fastResults;
        this.examDate = examDate;
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
        return "Kwdikos rantevou: " + appointmentID + " | Kwdikos asthenh: " +
                patientID + " | Kwdikos exetashs: " + examID + " | Epeigousa exetash: " + fastResults + "| Hmeromhnia exetashs: " + examDate;
    }
}

