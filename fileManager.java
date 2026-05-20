import java.io.*;
import java.util.HashMap;

public class fileManager{

    public void loadDoctors(HashMap<Integer, Doctor> doctorsMap){
        try (BufferedReader reader = new BufferedReader(new FileReader("Doctor.txt"))) { // In order
        // to automatically close the file after

            String line;

            while ((line = reader.readLine()) != null) { // Read the file line by line

                String[] tokens = line.split(","); // Split the lines using commas

                for (int i = 0; i < tokens.length; i++) {
                    tokens[i] = tokens[i].trim();
                } // Remove excess characters (such as spaces etc.)

                Doctor doctor = new Doctor( // Create a new doctor object using the data in the file
                        Integer.parseInt(tokens[0]), //ID
                        tokens[1], // Name
                        tokens[2], // Phone
                        tokens[3], // Specialty
                        Integer.parseInt(tokens[4]) // Years of experience
                );
                doctorsMap.put(doctor.getDoctorID(), doctor); // Put the previously created object in the doctors hashmap
        } // while
    } catch (IOException e) {
        System.err.println("Error reading file: " + e.getMessage());
        }
    } //loadDoctors

    public void loadPatients(HashMap<Integer, Patient> patientsMap){
        try (BufferedReader reader = new BufferedReader(new FileReader("Patient.txt"))) { // In order
        // to automatically close the file after

            String line;

            while ((line = reader.readLine()) != null) { // Read the file line by line

                String[] tokens = line.split(","); // Split the lines using commas

                for (int i = 0; i < tokens.length; i++) {
                    tokens[i] = tokens[i].trim();
                } // Remove excess characters (such as spaces etc.)

                Patient patient = new Patient( // Create a new patient object using the data in the file
                        Integer.parseInt(tokens[0]), // ID
                        tokens[1], // Name
                        tokens[2], // Phone
                        tokens[3] // Email
                );
                patientsMap.put(patient.getPatientID(), patient); // Put the previously created object in the patients hashmap
            } // while

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    } // loadPatients

    public void loadExams(HashMap<Integer, Exam> examsMap) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Exam.txt"))) { // In order
            // to automatically close the file after
            String line;

            while ((line = reader.readLine()) != null) { // Read the file line by line

                String[] tokens = line.split(","); // Split the lines using commas

                for (int i = 0; i < tokens.length; i++) {
                    tokens[i] = tokens[i].trim();
                } // Remove excess characters (such as spaces etc.)

                String category = tokens[0];
                int id = Integer.parseInt(tokens[1]);
                String examName = tokens[2];
                String specificAttribute = tokens[3]; // Machine, sample, or specialty
                int maxSlots = Integer.parseInt(tokens[4]);
                int cost = Integer.parseInt(tokens[5]);
                int leadDoctorID = Integer.parseInt(tokens[6]);

                Exam exam = null;

                switch (category) { // Check the exam's category, and creating the object according to it
                    case "Imaging":
                        exam = new ImagingExam(id, cost, examName, maxSlots, leadDoctorID, specificAttribute);
                        break;
                    case "Microbiological":
                        exam = new MicrobiologicalExam(id, cost, examName, maxSlots, leadDoctorID, specificAttribute);
                        break;
                    case "Specialized":
                        exam = new SpecializedExam(id, cost, examName, maxSlots, leadDoctorID, specificAttribute);
                        break;
                }

                if (exam != null) {
                    examsMap.put(exam.getExamID(), exam); // Put the object in the hashmap only if the category is valid
                }
            } // while

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    } // loadExams


    public void loadAppointments(HashMap<Integer, Appointment> appointmentsMap){
        try (BufferedReader reader = new BufferedReader(new FileReader("Appointment.txt"))) { // In order
            // to automatically close the file after

            String line;

            while ((line = reader.readLine()) != null) { // Read the file line by line

                String[] tokens = line.split(","); // Split the lines using commas

                for (int i = 0; i < tokens.length; i++) {
                    tokens[i] = tokens[i].trim(); // Remove excess characters (such as spaces etc.)
                }

                Appointment appointment = new Appointment( // Create a new appointment object using the data in the file
                        Integer.parseInt(tokens[0]),
                        Integer.parseInt(tokens[1]),
                        Integer.parseInt(tokens[2]),
                        Boolean.parseBoolean(tokens[3]),
                        tokens[4]
                );
                appointmentsMap.put(appointment.getAppointmentID(), appointment); // Put the previously created object
                // in the appointments hashmap
            } // while

    } catch (IOException e) {
        System.err.println("Error reading file: " + e.getMessage());
        }
    } // loadAppointments

    public void saveData(HashMap<Integer, Doctor> doctorsMap, // Save all the data from the hashmaps into the
                         // corresponding files
                         HashMap<Integer, Patient> patientsMap,
                         HashMap<Integer, Exam> examsMap,
                         HashMap<Integer, Appointment> appointmentsMap){
        saveDoctors(doctorsMap);
        savePatients(patientsMap);
        saveExams(examsMap);
        saveAppointments(appointmentsMap);
    } // saveData

    private void saveDoctors(HashMap<Integer, Doctor> doctorsMap){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Doctor.txt"))) { // In order to automatically
            // close the file after
            for (Doctor doctor: doctorsMap.values()) { // Write each doctor's details in the file

                writer.write(doctor.getDoctorID() + ","
                        + doctor.getDoctorName() + ","
                        + doctor.getDoctorPhone() + ","
                        + doctor.getSpeciality() + ","
                        + doctor.getYears());
                writer.newLine(); // Go to the next line to write the next doctor's details

            } // for

        } catch (IOException e) {
         System.err.println("Error writing file: " + e.getMessage());
        }
    } // saveDoctors

    private void savePatients(HashMap<Integer, Patient> patientsMap){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Patient.txt"))) { // In order to automatically
            // close the file after
            for (Patient patient: patientsMap.values()) { // Write each patient's details in the file

                writer.write(patient.getPatientID() + ","
                        + patient.getPatientName()+ ","
                        + patient.getPatientPhone()+ ","
                        + patient.getEmail());
                writer.newLine(); // Go to the next line to write the next patient's details

            } // for

        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    } // savePatients

    private void saveExams(HashMap<Integer, Exam> examsMap) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Exam.txt"))) { // In order to automatically
            // close the file after
            for (Exam exam : examsMap.values()) { // Write each exam's details in the file

                String specificAttribute = ""; // Get the exam's category and then it's specific attribute (machine, sample, specialty)
                if (exam instanceof ImagingExam) {
                    specificAttribute = ((ImagingExam) exam).getMachineType();
                } else if (exam instanceof MicrobiologicalExam) {
                    specificAttribute = ((MicrobiologicalExam) exam).getSampleType();
                } else if (exam instanceof SpecializedExam) {
                    specificAttribute = ((SpecializedExam) exam).getExamSpecialty();
                }

                writer.write(exam.getExamCategory() + ","
                        + exam.getExamID() + ","
                        + exam.getExamName() + ","
                        + specificAttribute + ","
                        + exam.getMaxSlotsPerDay() + ","
                        + exam.getCost() + ","
                        + exam.getLeadDoctor());

                writer.newLine();// Go to the next line to write the next exam's details

            } // for

        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    } // saveExams

    private void saveAppointments(HashMap<Integer, Appointment> appointmentsMap){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Appointment.txt"))) { // In order to
            // automatically close the file after

            for (Appointment appointment: appointmentsMap.values()) { // Write each appointment's details in the file

                writer.write(appointment.getAppointmentID() + ","
                    + appointment.getPatientID() + ","
                    + appointment.getExamID()+ ","
                    + appointment.getFastResults() + ","
                    + appointment.getExamDate());

                writer.newLine();

            } // for

        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    } // saveAppointments

} // Class
