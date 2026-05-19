import java.io.*;
import java.util.HashMap;

public class fileManager{

public void loadDoctors(HashMap<Integer, Doctor> doctorsMap){
    try (BufferedReader reader = new BufferedReader(new FileReader("Doctor.txt"))) {

        String line;

        while ((line = reader.readLine()) != null) {

            String[] tokens = line.split(",");

            for (int i = 0; i < tokens.length; i++) {
                tokens[i] = tokens[i].trim();
            }

            String type = tokens[0];



                Doctor doctor = new Doctor(
                        Integer.parseInt(tokens[0]),
                        tokens[1],
                        tokens[2],
                        tokens[3],
                        Integer.parseInt(tokens[4])
                );

                doctorsMap.put(doctor.getDoctorID(), doctor);

        }

        reader.close(); // κλείσιμο αρχείου

    } catch (IOException e) {
        System.err.println("Error reading file: " + e.getMessage());
    }
}
public void loadPatients(HashMap<Integer, Patient> patientsMap){
    try (BufferedReader reader = new BufferedReader(new FileReader("Patient.txt"))) {

        String line;

        while ((line = reader.readLine()) != null) {

            String[] tokens = line.split(",");

            for (int i = 0; i < tokens.length; i++) {
                tokens[i] = tokens[i].trim();
            }

            String type = tokens[0];



            Patient patient = new Patient(
                    Integer.parseInt(tokens[0]),
                    tokens[1],
                    tokens[2],
                    tokens[3]
            );

            patientsMap.put(patient.getPatientID(), patient);

        }

        reader.close(); // κλείσιμο αρχείου

    } catch (IOException e) {
        System.err.println("Error reading file: " + e.getMessage());
    }

}
/*
public void loadExams(HashMap<Integer, Exam> examsMap) {
    try (BufferedReader reader = new BufferedReader(new FileReader("Exam.txt"))) {

        String line;

        while ((line = reader.readLine()) != null) {

            String[] tokens = line.split(",");

            for (int i = 0; i < tokens.length; i++) {
                tokens[i] = tokens[i].trim();
            }

            String type = tokens[0];



            Exam exam = new Exam(
                    Integer.parseInt(tokens[1]),
                    Integer.parseInt(tokens[2]),
                    tokens[3],
                    tokens[4],
                    Integer.parseInt(tokens[5]),
                    Integer.parseInt(tokens[6])
            );

            examsMap.put(Exam.getExamID(), Exam);

        }

        reader.close(); // κλείσιμο αρχείου

    } catch (IOException e) {
        System.err.println("Error reading file: " + e.getMessage());
    }


}*/
public void loadAppointments(HashMap<Integer, Appointment> appointmentsMap){
    try (BufferedReader reader = new BufferedReader(new FileReader("Appointment.txt"))) {

        String line;

        while ((line = reader.readLine()) != null) {

            String[] tokens = line.split(",");

            for (int i = 0; i < tokens.length; i++) {
                tokens[i] = tokens[i].trim();
            }

            String type = tokens[0];



            Appointment appointment = new Appointment(
                    Integer.parseInt(tokens[0]),
                    Integer.parseInt(tokens[1]),
                    Integer.parseInt(tokens[2]),
                    Boolean.parseBoolean(tokens[3]),
                    tokens[4]
            );

            appointmentsMap.put(appointment.getAppointmentID(), appointment);

        }

        reader.close(); // κλείσιμο αρχείου

    } catch (IOException e) {
        System.err.println("Error reading file: " + e.getMessage());
    }
}

 public void saveData(HashMap<Integer, Doctor> doctorsMap,
                      HashMap<Integer, Patient> patientsMap,
                      HashMap<Integer, Exam> examsMap,
                      HashMap<Integer, Appointment> appointmentsMap){
     saveDoctors(doctorsMap);
     savePatients(patientsMap);
     saveExams(examsMap);
     saveAppointments(appointmentsMap);

 }
 private void saveDoctors(HashMap<Integer, Doctor> doctorsMap){
     try (BufferedWriter writer = new BufferedWriter(new FileWriter("Doctor.txt"))) {

         for (Doctor doctor: doctorsMap.values()) {

                 writer.write("Doctor,"
                         + doctor.getDoctorID() + ","
                         + doctor.getDoctorName() + ","
                         + doctor.getDoctorPhone() + ","
                         + doctor.getSpeciality() + ","
                         + doctor.getYears());

             writer.newLine();
         }

         writer.close(); // κλείσιμο αρχείου

     } catch (IOException e) {
         System.err.println("Error writing file: " + e.getMessage());
     }}
private void savePatients(HashMap<Integer, Patient> patientsMap){
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("Patient.txt"))) {

        for (Patient patient: patientsMap.values()) {

            writer.write("Patient,"
                    + patient.getPatientID() + ","
                    + patient.getPatientName()+ ","
                    + patient.getPatientPhone()+ ","
                    + patient.getEmail());

            writer.newLine();
        }

        writer.close(); // κλείσιμο αρχείου

    } catch (IOException e) {
        System.err.println("Error writing file: " + e.getMessage());
    }
}
private void saveExams(HashMap<Integer, Exam> examsMap){

}
private void saveAppointments(HashMap<Integer, Appointment> appointmentsMap){
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("Appointment.txt"))) {

        for (Appointment appointment: appointmentsMap.values()) {

            writer.write("Appointment,"
                    + appointment.getAppointmentID() + ","
                    + appointment.getPatientID() + ","
                    + appointment.getExamID()+ ","
                    + appointment.getFastResults() + ","
                    + appointment.getExamDate());

            writer.newLine();
        }

        writer.close(); // κλείσιμο αρχείου

    } catch (IOException e) {
        System.err.println("Error writing file: " + e.getMessage());
    }
}

}
