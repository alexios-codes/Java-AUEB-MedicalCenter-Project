/*
Ismail Alexios, 3250072, p3250072@aueb.gr
Daniil Kohut, 3250097, p3250097@aueb.gr
Michail Koukourempas, 3250259, p3250259@aueb.gr
*/
import java.util.HashMap;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.io.*;
import java.util.ArrayList;

public class Main {
    private static HashMap<Integer, Doctor> doctorsMap = new HashMap<>();
    private static HashMap<Integer, Patient> patientsMap = new HashMap<>();
    private static HashMap<Integer, Exam> examsMap = new HashMap<>();
    private static HashMap<Integer, Appointment> appointmentsMap = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);
    static fileManager fm = new fileManager();


    // Additional method created to avoid re-using code
    public static void showAll(HashMap<Integer, ?> map, String type) {
        if (map.isEmpty()) {
            System.out.println("There are no " + type + ".");
            return;
        }
        for (Object obj : map.values()) {
            System.out.println(obj.toString());
        }
    }

    // Doctor menu methods
    public static void addDoctor() {
        System.out.println("Doctor's name: ");
        String name = scanner.nextLine();

        System.out.println("Doctor's phone number: ");
        String phone = scanner.nextLine();

        System.out.println("Available doctor specialties: ");
        System.out.println("1. Cardiology\n2. Radiology\n3. Microbiology\n4. Neurology\n5. Pulmonology");
        System.out.println("Choose a specialty by inputting the corresponding number: ");
        String specialtyInput = scanner.nextLine();

        String specialty;
        switch (specialtyInput) {
            case "1":
                specialty = "Cardiology";
                break;
            case "2":
                specialty = "Radiology";
                break;
            case "3":
                specialty = "Microbiology";
                break;
            case "4":
                specialty = "Neurology";
                break;
            case "5":
                specialty = "Pulmonology";
                break;
            default:
                System.out.println("Invalid specialty!");
                return; // Return to the main menu since we can't create a doctor without specialty
        } // switch

        System.out.println("Doctor's years of experience: ");
        int years = scanner.nextInt();
        scanner.nextLine();
        if (years < 0) {
            System.out.println("Invalid years of experience!");
            return; // Return to the main menu since we can't create a doctor without years of experience
        }

        Doctor newDoc = new Doctor(name, phone, specialty, years); // Create a new doctor using the user's previously given data
        doctorsMap.put(newDoc.getDoctorID(), newDoc); // Add the created doctor in the doctors hashmap
    } //addDoctor

    public static Doctor chooseDoctor() { // Additional method created to avoid re-using code
        if (doctorsMap.isEmpty()) {
            System.out.println("No doctors available.");
            return null;
        }
        for (Doctor d : doctorsMap.values()) {
            // Showing just the names and ID's of each doctor, not all the information
            System.out.println("Name: " + d.getDoctorName() + " ID: " + d.getDoctorID());
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

    public static void showOneDoctor() {
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
                    showAll(doctorsMap, "doctors");
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
    public static void addPatient() {
        System.out.println("Patient's name: ");
        String name = scanner.nextLine();

        System.out.println("Patient's phone number: ");
        String phone = scanner.nextLine();

        System.out.println("Patient's email address: ");
        String mail = scanner.nextLine();

        Patient newPatient = new Patient(name, phone, mail); // Create a new patient using the user's previously given data
        patientsMap.put(newPatient.getPatientID(), newPatient); // Add the created patient to the patients hashmap
    }

    public static void showOnePatient() {
        if (patientsMap.isEmpty()) {
            System.out.println("There are no patients.");
            return;
        }
        for (Patient p : patientsMap.values()) {
            // Showing just the names and ID's of each patient, not all the information
            System.out.println("Name: " + p.getPatientName() + " ID: " + p.getPatientID());
        }
        System.out.println("Choose a patient by inputting his ID: ");
        int patientID = Integer.parseInt(scanner.nextLine());
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
                isInAppointment = true;
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
                    showAll(patientsMap, "patients");
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
    public static int examCost() { // Additional method created to avoid re-using code
        System.out.println("Enter the exam's cost:");
        int cost = scanner.nextInt();
        scanner.nextLine();
        return cost;
    }

    public static void addExam() {
        System.out.println("First choose the lead doctor:");
        Doctor doctor = chooseDoctor();
        if (doctor == null) {
            return;
        }

        System.out.println("Give a name for the exam:");
        String name = scanner.nextLine();

        System.out.println("Enter max slots per day:");
        int maxSlots = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Available exam categories:");
        System.out.println("1. Imaging\n2. Microbiological\n3. Specialized");
        System.out.println("Choose an exam category by inputting the corresponding number: ");
        String examCategoryInput = scanner.nextLine();
        int cost;
        Exam newExam = null;

        switch (examCategoryInput) { // Choose the additional attributes based on what category the exam is
            case "1":
                System.out.println("Machine types:");
                System.out.println("1. MRI\n2. CT\n3. X-RAY");
                System.out.println("Choose a machine type by inputting the corresponding number:");
                String machineTypeInput = scanner.nextLine();
                String machineType;

                switch (machineTypeInput) {
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

                cost = examCost();
                newExam = new ImagingExam(cost, name, maxSlots, doctor.getDoctorID(), machineType);
                break;

            case "2":
                System.out.println("Available sample types:");
                System.out.println("1. Blood\n2. Urine\n3. Swab");
                System.out.println("Choose a sample type by inputting the corresponding number:");
                String sampleTypeInput = scanner.nextLine();
                String sampleType;

                switch (sampleTypeInput) {
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
                String examSpecialtyInput = scanner.nextLine();
                String examSpecialty;

                switch (examSpecialtyInput) {
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

                cost = examCost();
                newExam = new SpecializedExam(cost, name, maxSlots, doctor.getDoctorID(), examSpecialty);
                break;
            default:
                System.out.println("Invalid input!");
                return; // return to the main menu since an exam can't be created without a category
        } // main switch
        if (newExam != null) {
            examsMap.put(newExam.getExamID(), newExam); // Add the new exam to the exams hashmap
        }
    } // addExam

    // Additional showAll, since the existing one can't be used to show alphabetically sorted items
    public static void showAllExamsSorted() {
        if (examsMap.isEmpty()) {
            System.out.println("There are no exams.");
            return;
        }

        // Move all the exams from the hashmap to an arraylist in order to sort them
        ArrayList<Exam> examList = new ArrayList<>(examsMap.values());

        // Sort alphabetically (based on the exam's name) without caring about upper/lower case letters
        examList.sort((e1, e2) -> e1.getExamName().compareToIgnoreCase(e2.getExamName()));


        for (Exam exam : examList) {
            System.out.println(exam.toString()); // Finally show all the exams, sorted
        }
    } // showAllExamsSorted

    public static void showOneExam() {
        if (examsMap.isEmpty()) {
            System.out.println("No exams available.");
            return;
        }
        showAll(examsMap, "exams");
        System.out.println("Choose a exam by inputting his ID: ");
        int ExamID = Integer.parseInt(scanner.nextLine());
        Exam exam = examsMap.get(ExamID);
        if (!examsMap.containsKey(ExamID)) {
            System.out.println("There is no exam with the given ID!");
            return;
        }
        System.out.println("Exams information: " + exam.toString());
    } // showOneExam

    public static void examsMenu() {
        boolean done = false;
        while (!done) {
            System.out.println("1. Enter exam");
            System.out.println("2. See all exams");
            System.out.println("3. See one exam");
            System.out.println("0. Return to main menu");
            System.out.println("> ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addExam();
                    break;
                case "2":
                    showAllExamsSorted();
                    break;
                case "3":
                    showOneExam();
                    break;
                case "0":
                    done = true;
                    break;
            } // switch
        } // while
    } // examsMenu

    // Appointment menu methods
    public static void addAppointment() {

        if (patientsMap.isEmpty()) {
            System.out.println("No patients available.");
            return;
        }

        System.out.println("Choose patient");
        showAll(patientsMap, "patients");
        System.out.println("Choose a patient by inputting their ID: ");
        int chosenPatientID = Integer.parseInt(scanner.nextLine());
        if (!patientsMap.containsKey(chosenPatientID)) {
            System.out.println("There's no patient with the given ID!");
            return;
        }
        Patient chosenPatient = patientsMap.get(chosenPatientID);
        if (examsMap.isEmpty()) {
            System.out.println("No exams available.");
            return;
        }
        showAll(examsMap, "exams");
        System.out.println("Choose a exam by inputting their ID: ");
        int chosenExamId = Integer.parseInt(scanner.nextLine());
        if (!examsMap.containsKey(chosenExamId)) {
            System.out.println("There is no exam with the given ID!");
            return;
        }
        Exam chosenExam = examsMap.get(chosenExamId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy"); // Set the date format
        String examDate = "";
        boolean validDate = false;
        while (!validDate) {
            System.out.println("Date (DD:MM:YYYY) : ");
            examDate = scanner.nextLine();
            try {
                LocalDate.parse(examDate, formatter); // If the date format is correct
                validDate = true;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again."); // If the date format is incorrect
            }
        }
        int count = 0; // How many slots have been filled
        for (Appointment a : appointmentsMap.values()) {
            if (a.getExamID() == chosenExam.getExamID() && a.getExamDate().equals(examDate)) {
                count++;
            }
        }
        if (count >= chosenExam.getMaxSlotsPerDay()) {
            System.out.println("There are no available slots for this date");
            return;
        }

        System.out.println("Fast results? (Press 1 for Yes / Press 2 for No)");
        String fastInput = scanner.nextLine();
        boolean fastResults = fastInput.equals("1"); // Fast results status based on the user's input

        Appointment newAppointment = new Appointment(chosenPatient.getPatientID(), chosenExam.getExamID(), fastResults,
                examDate);
        appointmentsMap.put(newAppointment.getAppointmentID(), newAppointment);
        // Create the appointment and put it in the appointments hashmap
    }

    public static void showPatientsAppointment() { // Shows a patient's appointments
        if (patientsMap.isEmpty()) {
            System.out.println("There are no patients.");
            return;
        }
        for (Patient p : patientsMap.values()) {
            System.out.println("Name: " + p.getPatientName() + " ID: " + p.getPatientID());// Showing just the names
            // and ID's of each patient, not all the information
        }
        System.out.println("Choose a patient by inputting his ID: ");
        int chosenPatientID = Integer.parseInt(scanner.nextLine());
        if (!patientsMap.containsKey(chosenPatientID)) {
            System.out.println("There is no patient with the given ID!");
            return;
        }
        System.out.println("The patient has the following appointments: ");
        boolean isInAppointment = false;
        for (Appointment a : appointmentsMap.values()) {
            if (a.getPatientID() == chosenPatientID) {
                System.out.println(a);
                isInAppointment = true;
            }
        } // for

        if (!isInAppointment) {
            System.out.println("The patient does not have any appointments.");
        }
    } // showPatientsAppointment

    public static void deleteAppointment() {
        if (appointmentsMap.isEmpty()) {
            System.out.println("There are no appointments.");
            return;
        }
        showAll(appointmentsMap, "appointments");
        System.out.println("Chose which appointment you want to delete by giving its ID");
        int deleteID = Integer.parseInt(scanner.nextLine());
        if (!appointmentsMap.containsKey(deleteID)) {
            System.out.println("There is no appointment with this ID!");
        }
        System.out.println("Are you sure you want to delete this appointment 1.YES/2.NO");
        String choice = scanner.nextLine();
        if (choice.equals("1") || choice.equals("YES") || choice.equals("yes")) {
            appointmentsMap.remove(deleteID);
            System.out.println("Appointment deleted!");

        }else if(choice.equals("2") || choice.equals("NO") || choice.equals("no")){
            System.out.println("Deletion cancelled");
        }
    } // deleteAppointment

    public static void showOneDaysExam() { // Show all the exams on a specific day

        if (appointmentsMap.isEmpty()) {
            System.out.println("There are no appointments.");
            return;
        }

        System.out.println("Enter date (DD:MM:YYYY): ");
        String searchDate = scanner.nextLine();

        boolean found = false;
        System.out.println("\nAppointments for " + searchDate + ":");

        for (Appointment a : appointmentsMap.values()) {
            if (a.getExamDate().equals(searchDate)) {
                Patient patient = patientsMap.get(a.getPatientID());
                Exam exam = examsMap.get(a.getExamID());

                String patientName = patient.getPatientName();
                String examName = exam.getExamName();

                System.out.println(a + " | Patient Name: " + patientName + " | Exam Name: " + examName);
                found = true;
            } // if
        } // for

        if (!found) {
            System.out.println("No appointments found for this date.");
        }
    } // showOneDaysExam

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
                    showAll(appointmentsMap, "appointments");
                    break;
                case "3":
                    showPatientsAppointment();
                    break;
                case "4":
                    deleteAppointment();
                    break;
                case "5":
                    showOneDaysExam();
                    break;
                case "0":
                    done = true;
                    break;
            } // switch
        } // while
    } // appointmentMenu

    // Statistics menu methods
    public static void totalIncomePerPatient() {
        showAll(patientsMap, "patients");
        double totalIncomeFromAllPatients = 0;

        for (Patient patient : patientsMap.values()) {
            double totalIncomeFromPatient = 0;
            for (Appointment appointment : appointmentsMap.values()) {
                if (appointment.getPatientID() == patient.getPatientID()) {
                    Exam exam = examsMap.get(appointment.getExamID());
                    double finalCost = exam.getFinalCost(appointment.getFastResults());
                    System.out.println("Exam: " + exam.getExamName() + " Cost: " + finalCost);
                    totalIncomeFromPatient += finalCost;

                    System.out.println(appointment);
                } // check if the appointment belongs to the patient
            } // for loop for appointments
            System.out.println("Total income from patient " + patient.getPatientName() + ": " + totalIncomeFromPatient);
            totalIncomeFromAllPatients += totalIncomeFromPatient;
        } // for loop for patients
        System.out.println("Total income from all patients: " + totalIncomeFromAllPatients);
    } // totalIncomePerPatient

    public static void totalIncomePerExam() {
        showAll(examsMap, "exams");
        double totalIncomeFromAllExams = 0;

        for (Exam exam : examsMap.values()) {
            double totalIncomeFromExam = 0;
            int totalAppointmentsForExam = 0;
            int examCost = exam.getCost();
            double totalIncomeFromExamAppointments = 0;
            for (Appointment appointment : appointmentsMap.values()) {
                if (appointment.getExamID() == exam.getExamID()) {
                    totalIncomeFromExam += exam.getFinalCost(appointment.getFastResults());
                    totalAppointmentsForExam++;

                    System.out.println(appointment);
                }
            }
            totalIncomeFromExamAppointments = totalAppointmentsForExam * examCost;
            System.out.println("Total income from exam " + exam.getExamName() + ": " + totalIncomeFromExam);
            System.out.println("Total income from appointments for exam " + exam.getExamName() + ": "
                    + totalIncomeFromExamAppointments);
            totalIncomeFromAllExams += totalIncomeFromExam;
        }
        System.out.println("Total income from all exams: " + totalIncomeFromAllExams);
    } // totalIncomePerExam


    public static HashSet<String> getUniqueExamCategories() { // Helper method to find all unique exam categories
        HashSet<String> categories = new HashSet<>();
        for (Exam e : examsMap.values()) {
            categories.add(e.getExamCategory());
        }
        return categories;
    }

    public static void showAllExamCategories() { // Helper method used in totalIncomePerExamCategory
        HashSet<String> categories = getUniqueExamCategories();

        for (String category : categories) {
            System.out.println(category); // Finally show all categories
        }
    } // showAllExamCategories

    public static void totalIncomePerExamCategory() {
        showAllExamCategories();
        double totalIncomeFromAllCategories = 0;

        HashSet<String> categories = getUniqueExamCategories();

        for (String category : categories) { // Go through each unique category
            double totalIncomeFromCategory = 0;
            for (Exam exam : examsMap.values()) { // Find all the exams under that category
                if (exam.getExamCategory().equals(category)) {
                    // Search for the appointments with this specific exam
                    for (Appointment appointment : appointmentsMap.values()) {
                        if (appointment.getExamID() == exam.getExamID()) {
                            // Add the exam's cost to the total income
                            totalIncomeFromCategory += exam.getFinalCost(appointment.getFastResults());

                            System.out.println(appointment);
                        }
                    }
                }
            }
            System.out.println("Total income from exam category " + category + ": " + totalIncomeFromCategory);
            totalIncomeFromAllCategories += totalIncomeFromCategory;
        } // for
        System.out.println("Total income from all exam categories: " + totalIncomeFromAllCategories);
    } // totalIncomePerExamCategory

    public static void statisticsMenu() {
        boolean done = false;
        while (!done) {
            System.out.println("1. Total income per patient");
            System.out.println("2. Total income per exam");
            System.out.println("3. Total income per exam category");
            System.out.println("0. Return to main menu");
            System.out.println("> ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    totalIncomePerPatient();
                    break;
                case "2":
                    totalIncomePerExam();
                    break;
                case "3":
                    totalIncomePerExamCategory();
                    break;
                case "0":
                    done = true;
                    break;
            } // switch
        } // while
    } // statsMenu


    public static void main(String[] args) {
        // Initiate the file paths for each "category"
        String filePathDoc = "Doctor.txt";
        String filePathPatient = "Patient.txt";
        String filePathAppointment = "Appointment.txt";
        String filePathExam = "Exam.txt";

        // Initiate the actual files
        File fileDoc = new File(filePathDoc);
        File filePatient = new File(filePathPatient);
        File fileAppointment = new File(filePathAppointment);
        File fileExam = new File(filePathExam);
        if (!fileDoc.exists()) {
            // Default doctors, if there's no existing doctor file
            Doctor d1 = new Doctor("D1", "111", "Cardiology", 10);
            Doctor d2 = new Doctor("D2", "222", "Radiology", 8);
            Doctor d3 = new Doctor("D3", "333", "Microbiology", 3);
            Doctor d4 = new Doctor("D4", "444", "Neurology", 2);
            doctorsMap.put(d1.getDoctorID(), d1);
            doctorsMap.put(d2.getDoctorID(), d2);
            doctorsMap.put(d3.getDoctorID(), d3);
            doctorsMap.put(d4.getDoctorID(), d4); // Load the default doctors to the doctors hashmap
        } else {
            fm.loadDoctors(doctorsMap); // If there is an existing file, load the data from the file to the hashmap
        }

        if (!filePatient.exists()) {
            // Default patients, if there's no existing patients file
            Patient p1 = new Patient("P1", "6901", "a@a.com");
            Patient p2 = new Patient("P2", "6902", "b@b.com");
            Patient p3 = new Patient("P3", "6903", "c@c.com");
            patientsMap.put(p1.getPatientID(), p1);
            patientsMap.put(p2.getPatientID(), p2);
            patientsMap.put(p3.getPatientID(), p3); // Load the default patients to the patients hashmap
        } else {
            fm.loadPatients(patientsMap); // If there is an existing file, load the data from the file to the hashmap
        }

        if (!fileExam.exists()) {
            // Default exams, if there's no existing exams file
            Exam e1 = new ImagingExam(30, "XRAY", 10, 2, "X-Ray");
            Exam e2 = new MicrobiologicalExam(20, "PCR", 20, 3, "Blood");
            Exam e3 = new SpecializedExam(80, "Stress", 3, 1, "Cardiology");
            Exam e4 = new SpecializedExam(50, "Holter", 5, 1, "Cardiology");
            Exam e5 = new SpecializedExam(20, "EEG", 10, 4, "Neurology");
            examsMap.put(e1.getExamID(), e1);
            examsMap.put(e2.getExamID(), e2);
            examsMap.put(e3.getExamID(), e3);
            examsMap.put(e4.getExamID(), e4);
            examsMap.put(e5.getExamID(), e5); // Load the default exams to the exams hashmap
        } else {
            fm.loadExams(examsMap); // If there is an existing file, load the data from the file to the hashmap
        }

        if (!fileAppointment.exists()) {
            // Default appointments, if there's no existing appointments file
            Appointment a1 = new Appointment(1, 1, true, "10:05:2026");
            Appointment a2 = new Appointment(2, 2, false, "16:05:2026");
            Appointment a3 = new Appointment(1, 2, false, "11:05:2026");
            Appointment a4 = new Appointment(1, 3, true, "16:05:2026");
            appointmentsMap.put(a1.getAppointmentID(), a1);
            appointmentsMap.put(a2.getAppointmentID(), a2);
            appointmentsMap.put(a3.getAppointmentID(), a3);
            appointmentsMap.put(a4.getAppointmentID(), a4); // Load the default appointments to the appointments hashmap
        } else {
            fm.loadAppointments(appointmentsMap); // If there is an existing file, load the data from the file to the hashmap
        }

        // Main menu
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
                    // When exiting from the program save and load the new data to the files
                    fm.saveData(doctorsMap, patientsMap, examsMap, appointmentsMap);
                    done = true;
                    break;
            } // switch
        } // while
    } // main
} // Class
