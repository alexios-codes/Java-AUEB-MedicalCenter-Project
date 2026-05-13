import java.util.HashMap;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
    // Sylloges se hashmaps
    private static HashMap<Integer, Doctor> doctorsMap = new HashMap<>();
    private static HashMap<Integer, Patient> patientsMap = new HashMap<>();
    private static HashMap<Integer, Exam> examsMap = new HashMap<>();
    private static HashMap<Integer, Appointment> appointmentsMap = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    // Doctor menu methods
    public static void addDoctor() { // Create a new doctor and add to the hashmap
        System.out.println("Doctor's name: ");
        String name = scanner.nextLine();

        System.out.println("Doctor's phone number: ");
        String phone = scanner.nextLine();

        System.out.println("Available doctor specialties: ");
        System.out.println("1. Cardiology\n2. Radiology\n3. Microbiology\n4. Neurology\n5. Pulmonology");
        System.out.println("Choose a specialty by inputting the corresponding number: ");
        String specialtyInput = scanner.nextLine();

        String specialty;
        switch(specialtyInput) {
            case "1":
                specialty = "Cardiology";
                break;
            case "2":
                specialty = "Radiology";
                break;
            case  "3":
                specialty = "Microbiology";
                break;
            case  "4":
                specialty = "Neurology";
                break;
            case "5":
                specialty = "Pulmonology";
                break;
            default:
                System.out.println("Invalid specialty!");
                return; // return to the main menu since we can't create a doctor without specialty
        } // switch

        System.out.println("Doctor's years of experience: ");
        int years = scanner.nextInt();
        if (years < 0) {
            System.out.println("Invalid years of experience!");
            return; // return to the main menu since we can't create a doctor without years of experience
        }

        Doctor newDoc = new Doctor(name, phone, specialty, years); // Create a new doctor using the user's
        // previously given data
        doctorsMap.put(newDoc.getDoctorID(), newDoc); // Add the created doctor in the doctors hashmap
    }

    public static void showAllDoctors() {
        for (Doctor d : doctorsMap.values()) {
            System.out.println(d.toString());
        }
    }

    public static Doctor chooseDoctor() { // Additional method created to avoid re-using code
        if (doctorsMap.isEmpty()) {
            System.out.println("No doctors available.");
            return null;
        }
        for (Doctor d : doctorsMap.values()) {
            System.out.println("Name: " + d.getDoctorName() + " ID: " + d.getDoctorID()); // Showing just the names
            // and ID's of each doctor, not all the information
        }
        System.out.println("Choose a doctor by inputting their ID: ");
        int chosenDoctorID = scanner.nextInt();
        scanner.nextLine(); // "Clean" the new line left by the nextInt
        if (!doctorsMap.containsKey(chosenDoctorID)) {
            System.out.println("There's no doctor with the given ID!");
            return null;
        } // if
        return doctorsMap.get(chosenDoctorID); // return the doctor who has the given ID
    } // chooseDoctor

    public static void showOneDoctor() { //
        Doctor doctor = chooseDoctor();
        if (doctor == null) { // return to the main menu if an error occurred during the choice of doctor
            return;
        }
        int doctorID = doctor.getDoctorID();
        System.out.println("Doctor's detailed information:");
        System.out.println(doctor);
        System.out.println("The doctor is in charge of the exams: ");
        boolean isInExam = false;
        for (Exam e : examsMap.values()) {
            if (e.getLeadDoctor() == doctorID) {
                System.out.println(e.getExamName()); // Show just the exam names and not all the details
                isInExam = true;
            } // if
        } // for
        if (!isInExam) {
            System.out.println("The doctor is not in charge of any exams.");
        }
    } // showOneDoctor

    public static void docAppointment() { // Show a doctor's appointments
        Doctor doctor = chooseDoctor();
        if (doctor == null) { // return to the main menu if an error occurred during the choice of doctor
            return;
        }
        int doctorID = doctor.getDoctorID();
        System.out.println("Doctor " + doctor.getDoctorName() + "'s appointments: ");
        boolean hasAppointment = false;
        for (Appointment a : appointmentsMap.values()) {
            Exam exam = examsMap.get(a.getExamID());
            if (exam.getLeadDoctor() == doctorID) {
                System.out.println(a);
                hasAppointment = true;
            }
        } // for

        if (!hasAppointment) {
            System.out.println("The doctor does not have any appointments.");
        }
    } // docAppointment

    // Doctor menu
    public static void doctorMenu() {
        boolean done = false;
        while (!done) {
            System.out.println("1. Add doctor");
            System.out.println("2. Show all doctors");
            System.out.println("3. Show one doctor");
            System.out.println("4. A doctor's appointments");
            System.out.println("0. Return to the main menu");
            System.out.println("> ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addDoctor();
                    break;
                case "2":
                    showAllDoctors();
                    break;
                case "3":
                    showOneDoctor();
                    break;
                case "4":
                    docAppointment();
                    break;
                case "0":
                    done = true;
                    break;
            } // switch
        } // while
    } // doctorMenu

    // Patients menu methods
    public static void addPatient() { // Create a new patient and add to the hashmap
        System.out.println("Patient's name: ");
        String name = scanner.nextLine();

        System.out.println("Patient's phone number: ");
        String phone = scanner.nextLine();

        System.out.println("Patient's email address: ");
        String mail = scanner.nextLine();

        Patient newPatient = new Patient(name, phone, mail); // Create a new patient using the user's
        // previously given data
        patientsMap.put(newPatient.getPatientID(), newPatient); // Add the created patient to the patients hashmap
    }

    public static void showAllPatients() {
        for (Patient p : patientsMap.values()) {
            System.out.println(p.toString());
        }
    }

    public static void showOnePatient() {
        if (patientsMap.isEmpty()) {
            System.out.println("There are no patients.");
            return;
        }
        for (Patient p : patientsMap.values()) {
            System.out.println("Name: " + p.getPatientName() + " ID: " + p.getPatientID());// Showing just the names
            // and ID's of each patient, not all the information
        }
        System.out.println("Choose a patient by inputting his ID: ");
        int patientID = scanner.nextInt();
        scanner.nextLine(); // "Clean" the new line left by the nextInt
        Patient patient = patientsMap.get(patientID);
        if (!patientsMap.containsKey(patientID)) {
            System.out.println("There is no patient with the given ID!");
            return;
        }

        System.out.println("Patient's detailed information:");
        System.out.println(patient);
        System.out.println("The patient has the following appointments: ");
        boolean isInAppointment = false;
        for (Appointment a : appointmentsMap.values()) {
            if (a.getPatientID() == patientID) {
                System.out.println(a);
                isInAppointment= true;
            }
        } // for

        if (!isInAppointment) {
            System.out.println("The patient does not have any appointments.");
        }
    } // showOnePatient

    // Patients menu
    public static void patientsMenu() {
        boolean done = false;
        while (!done) {
            System.out.println("1. Add patient");
            System.out.println("2. Show all patients");
            System.out.println("3. Show one patient");
            System.out.println("0. Return to the main menu");
            System.out.println("> ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addPatient();
                    break;
                case "2":
                    showAllPatients();
                    break;
                case "3":
                    showOnePatient();
                    break;
                case "0":
                    done = true;
                    break;
            } // switch
        } // while
    } // patientsMenu

    // Exam menu methods
    public static int examCost() {
        System.out.println("Enter the exam's cost:");
        int cost = scanner.nextInt();
        scanner.nextLine();
        return cost;
    }

    public static void addExam() {
        System.out.println("First choose the lead doctor:");
        Doctor doctor = chooseDoctor();
        if  (doctor == null) {
            return;
        }

        System.out.println("Give a name for the exam:");
        String name =  scanner.nextLine();

        System.out.println("Enter max slots per day:");
        int maxSlots = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Available exam categories:");
        System.out.println("1. Imaging\n2. Microbiological\n3. Specialized");
        System.out.println("Choose an exam category by inputting the corresponding number: ");
        String examCategoryInput = scanner.nextLine();
        int cost;
        Exam newExam = null;

        switch (examCategoryInput) {
            case "1":
                System.out.println("Machine types:");
                System.out.println("1. MRI\n2. CT\n3. X-RAY");
                System.out.println("Choose a machine type by inputting the corresponding number:");
                String machineTypeInput = scanner.nextLine();
                String machineType;

                switch(machineTypeInput) {
                    case "1":
                        machineType = "MRI";
                        break;
                    case "2":
                        machineType = "CT";
                        break;
                    case "3":
                        machineType = "X-RAY";
                        break;
                    default:
                        System.out.println("Invalid machine type!");
                        return;
                }

                cost  = examCost();
                newExam = new ImagingExam(cost, name, maxSlots, doctor.getDoctorID(), machineType);
                break;

            case "2":
                System.out.println("Available sample types:");
                System.out.println("1. Blood\n2. Urine\n3. Swab");
                System.out.println("Choose a sample type by inputting the corresponding number:");
                String sampleTypeInput = scanner.nextLine();
                String sampleType;

                switch(sampleTypeInput) {
                    case "1":
                        sampleType = "Blood";
                        break;
                    case "2":
                        sampleType = "Urine";
                        break;
                    case "3":
                        sampleType = "Swab";
                        break;
                    default:
                        System.out.println("Invalid sample type!");
                        return;
                }

                cost = examCost();
                newExam = new MicrobiologicalExam(cost, name, maxSlots, doctor.getDoctorID(), sampleType);
                break;

            case "3":
                System.out.println("Available exam specialties:");
                System.out.println("1. Cardiology\n2. Neurology\n3. Pulmonology");
                System.out.println("Choose an exam specialty by inputting the corresponding number:");
                String examSpecialtyInput =  scanner.nextLine();
                String examSpecialty;

                switch(examSpecialtyInput) {
                    case "1":
                        examSpecialty = "Cardiology";
                        break;
                    case "2":
                        examSpecialty = "Neurology";
                        break;
                    case "3":
                        examSpecialty = "Pulmonology";
                        break;
                    default:
                        System.out.println("Invalid exam specialty!");
                        return;
                }

                cost  = examCost();
                newExam = new SpecializedExam(cost, name,  maxSlots, doctor.getDoctorID(), examSpecialty);
                break;
            default:
                System.out.println("Invalid input!");
                return; // return to the main menu since an exam can't be created without a category
        } // main switch
        if (newExam != null) {
            examsMap.put(newExam.getExamID(), newExam); // Add the new exam to the exams hashmap
        }
    } // addExam

    public static void showAllExams() {
        for (Exam e : examsMap.values()) {
            System.out.println(e);
        }
    }

    //TODO DEN TREXH
    public static void showOneExam(){
        if (examsMap.isEmpty()) {
            System.out.println("There are no Exams.");
            return;
        }
        showAllExams();
        System.out.println("Choose a exam by inputting his ID: ");
        int ExamID = scanner.nextInt();
        scanner.nextLine(); // "Katharismos" grammhs logw tou nextInt
        Exam exam = examsMap.get(ExamID);
        if (!examsMap.containsKey(ExamID)) {
            System.out.println("There is no examwith the given ID!");
            return;
        }
        System.out.println("Exams information: " + exam.toString());
      

    }
    public static void examsMenu() {
        boolean done = false;
        while (!done) {
            System.out.println("1. Eisagwgh exetashs");
            System.out.println("2. Emfanish olwn twn exetasewn");
            System.out.println("3. Emfanish mias exetashs");
            System.out.println("0. Epistrofh sto arxiko menu");
            System.out.println("> ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                   // addExam();
                    break;
                case"2":
                    showAllExams();
                    break;
                case"3":
                    showOneExam();
                    break;
                case "0":
                    done = true;
                    break;
            }
        }
    }
    public static void addAppointment(){
        System.out.println("Choose patient");
        if (patientsMap.isEmpty()) {
            System.out.println("No patients available.");
            return;
        }
        showAllPatients();
        System.out.println("Choose a patient by inputting their ID: ");
        int chosenPatientID = scanner.nextInt();
        scanner.nextLine(); // "Katharismos" grammhs logw tou nextInt
        if (!patientsMap.containsKey(chosenPatientID)) {
            System.out.println("There's no patient with the given ID!");
            return;
        }
        Patient chosenPatient = patientsMap.get(chosenPatientID);
        System.out.println("Choose Exam");
        if(examsMap.isEmpty()){
            System.out.println("There are no exams");
            return;
        }
        showAllExams();
        System.out.println("Choose a exam by inputting their ID: ");
        int chosenExamId = scanner.nextInt();
        scanner.nextLine();
        if(!examsMap.containsKey(chosenExamId)){
            System.out.println("There is no exam with this id");
            return;
        }
        Exam chosenExam = examsMap.get(chosenExamId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        boolean validDate = false;
        while (!validDate) {
            System.out.println("Ημερομηνία (DD/MM/YYYY): ");
            String examDate = scanner.nextLine();
            try {
                LocalDate.parse(examDate, formatter); // an einai lathos -> exception
                validDate = true;
            } catch (DateTimeParseException e) {
                System.out.println("Λάθος μορφή ημερομηνίας. Δοκιμάστε ξανά.");
            }
        }
    }

    public static void showAllAppointments(){
         for (Appointment a : appointmentsMap.values()) {
            System.out.println(a);
        }
    }

    public static void showPatientsAppointment(){}

    public static void deleteAppointment(){}

    public static void appointmentMenu() {
        boolean done = false;
        while (!done) {
            System.out.println("1. Add appointment");
            System.out.println("2. Show all appointments");
            System.out.println("3. Show patients appointment");
            System.out.println("4.Delete appointment");
            System.out.println("0. Return to main menu");
            System.out.println("> ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addAppointment();
                    break;
                case "2":
                    showAllAppointments();
                    break;
                case "3":
                    showPatientsAppointment();
                    break;
                case "4":
                    deleteAppointment();
                    break;
                case "0":
                    done = true;
                    break;
            } // switch
        } // while
    } // appointmentMenu

    public static void statisticsMenu () {}

    public static void main (String[]args){
        boolean done = false;
        while (!done) {
            System.out.println("\n1. Doctors");
            System.out.println("2. Patients");
            System.out.println("3. Exams");
            System.out.println("4. Appointments");
            System.out.println("5. Statistics");
            System.out.println("0. Exit");
            System.out.print("> ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    doctorMenu();
                    break;
                case "2":
                    patientsMenu();
                    break;
                case "3":
                    examsMenu();
                    break;
                case "4":
                    appointmentMenu();
                    break;
                case "5":
                    statisticsMenu();
                    break;
                case "0":
                    done = true;
                    break;
            } // switch
        } // while
    } // main
} // Class

