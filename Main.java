import java.util.HashMap;
import java.util.Scanner;

public class Main {
    // Sylloges se hashmaps
    private static HashMap<Integer, Doctor> doctorsMap = new HashMap<>();
    private static HashMap<Integer, Patient> patientsMap = new HashMap<>();
    private static HashMap<Integer, Exam> examsMap = new HashMap<>();
    private static HashMap<Integer, Appointment> appointmentsMap = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    // Methodoi gia to menu iatrwn
    public static void addDoctor() { // Dhmiourgia neou iatrou
        System.out.println("Doctor's name: ");
        String name = scanner.nextLine();

        System.out.println("Doctor's phone number: ");
        String phone = scanner.nextLine();

        System.out.println("Available doctor specialties: ");
        System.out.println("1. Cardiology\n2. Radiology\n3. Microbiology\n4. Neurology\n5. Pulmonology");
        System.out.println("Choose a specialty, iputting the corresponding number: ");
        String specialtyInput = scanner.nextLine();

        String specialty = "";
        if (specialtyInput == "1") {
            specialty = "Cardiology";
        } else if (specialty == "2") {
            specialty = "Radiology";
        } else if (specialtyInput == "3") {
            specialty = "Microbiology";
        } else if (specialtyInput == "4") {
            specialty = "Neurology";
        } else if (specialtyInput == "5") {
            specialty = "Pulmonology";
        } else {
            System.out.println("Invalid specialty!");
            return; // Return xwris na dhmiourgithei neos iatros
        }
        System.out.println("Doctor's years of experience: ");
        String years = scanner.nextLine();
        if (years < 0) {
            System.out.println("Invalid experience years number!");
        }

        Doctor newDoc = new Doctor(name, phone, specialty, years); // Dhmiourgia neou iatrou me bash ta dedomena
        // pou edwse o xrhsths
        doctorsMap.put(newDoc.getDoctorID(), newDoc); // Eisagwgh tou parapanw neou antikeimenou sthn syllogh iatrwn
    }

    public static void showAllDoctors() { // Emfanish olwn twn iatrwn
        for (Doctor d : doctorsMap.values()) {
            System.out.println(d.toString());
        }
    }

    public static Doctor chooseDoctor() { // Methodos mono gia mh epanaxrhsimopoihsh kwdika. Xrhsimopoihtai
        // se 3 methodous.
        if (doctorsMap.isEmpty()) {
            System.out.println("No doctors available.");
            return null;
        }
        showAllDoctors();
        System.out.println("Choose a doctor by inputting their ID: ");
        int chosenDoctorID = scanner.nextInt();
        scanner.nextLine(); // "Katharismos" grammhs logw tou nextInt
        if (!doctorsMap.containsKey(chosenDoctorID)) {
            System.out.println("There's no doctor with the given ID!");
            return null;
        }
        Doctor chosenDoctor = doctorsMap.get(chosenDoctorID);
        return chosenDoctor;
    } // chooseDoctor

    public static void showOneDoctor() { // Emfanish enos iatrou
        Doctor doctor = chooseDoctor();
        if (doctor == null) { // An yphrxe sfalma kata thn epilogh iatrou, o xrhsths epistrefetai
            return;
        }
        int doctorID = doctor.getDoctorID();
        System.out.println("Doctor's information: " + doctor.toString());
        System.out.println("The doctor is in charge of the exams: ");
        boolean isInExam = false;
        for (Exam e : examsMap.values()) {
            if (e.getLeadDoctor() == doctorID) {
                System.out.println(e.toString());
                isInExam = true;
            }
        } // for
        if (!isInExam) {
            System.out.println("The doctor is not in charge of any exams.");
        }
    } // showOneDoctor

    public static void docAppointment() { // Emfanish rantevou enos iatrou
        Doctor doctor = chooseDoctor();
        if (doctor == null) { // An yphrxe sfalma kata thn epilogh iatrou, o xrhsths epistrefetai
            return;
        }
        int doctorID = doctor.getDoctorID();
        System.out.println("Doctor" + doctor.getDoctorName() + "'s appointments: ");
        boolean hasAppointment = false;
        for (Appointment a : appointmentsMap.values()) {
            Exam exam = examsMap.get(a.getExamID());
            if (exam.getLeadDoctor() == doctorID) {
                System.out.println(a.toString());
                hasAppointment = true;
            }
        } // for

        if (!hasAppointment) {
            System.out.println("The doctor does not have any appointments.");
        }
    } // docAppointment

    // Menu iatrwn
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
            }
        } // while
    } // doctorMenu

    // Methodoi gia to menu asthenwn
    public static void addPatient() { // Dhmiourgia neou asthenous
        System.out.println("Patient's name: ");
        String name = scanner.nextLine();

        System.out.println("Patient's phone number: ");
        String phone = scanner.nextLine();

        System.out.println("Patient's email address: ");
        String mail = scanner.nextLine();

        Patient newPatient = new Patient(name, phone, mail); // Dhmiourgia neou asthenh me bash ta dedomena pou edwse
        // o xrhsths
        patientsMap.put(patient.getPatientID(), newPatient); // Prosthkh tou parapanw neou antikeimenou sthn syllogh asthenwn
    }

    public static void showAllPatients() { // Emfanish olwn twn asthenwn
        for (Patient p : patientsMap.values()) {
            System.out.println(p.toString());
        }
    }

    public static void showOnePatient() { // Emfanish enos asthenous
        if (patientsMap.isEmpty()) {
            System.out.println("There are no patients.");
            return;
        }
        showAllPatients();
        System.out.println("Choose a patient by inputting his ID: ");
        int patientID = scanner.nextInt();
        scanner.nextLine(); // "Katharismos" grammhs logw tou nextInt
        Patient patient = patientsMap.get(patientID);
        if (!patientsMap.containsKey(patientID)) {
            System.out.println("There is no patient with the given ID!");
            return;
        }
        System.out.println("Patient's information: " + patient.toString());
        System.out.println("The patient has these appointments: ");
        boolean isInAppointment = false;
        for (Appointment a : appointmentsMap.values()) {
            if (a.getPatientID() == patientID) {
                System.out.println(a.toString());
                isInAppointment= true;
            }
        } // for
        if (!isInAppointment) {
            System.out.println("The patient does not have any appointments.");
        }
    } // showOnePatient

    // Menu asthenwn
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
            }
        } // while
    } // patientsMenu

    // Methodoi gia to menu exetasewn
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

        System.out.println("Available exam categories:");
        System.out.println("1. Imaging\n2. Micorbiological\n3. Special exams");
        System.out.println("Choose an exam category by inputting the corresponding number: ");
        String examCategoryInput = scanner.nextLine();

        System.out.println("Enter max slots per day:");
        int maxSlots = scanner.nextInt();
        scanner.nextLine();

        String examCategory = "";
        if (examCategoryInput == "1") {
            examCategory = "Imaging";
            System.out.println("Machine types:");
            System.out.println("1. Magnhtikos tomografos\n2. Axonikos tomografos\n3. Aktinografia");
            System.out.println("Choose a machine type by inputting the corresponding number:");
            String machineTypeInput = scanner.nextLine();
            String machineType = "";
            if (machineTypeInput == "1") {
                machineType = "MRI";
            }else if (machineTypeInput == "2") {
                machineType = "CT";
            }else if (machineTypeInput == "3") {
                machineType = "X-Ray";
            }else {
                System.out.println("Invalid machine type!");
                return;
            }

            int cost =  examCost();

            Exam newExam = Exam(cost, name, examCategory, maxSlots, doctor, machineType);
        }
        else if (examCategoryInput == "2") {
            examCategory = "Microbiological";
            System.out.println("Available sample types:");
            System.out.println("1. Blood\n2. Urine\n3. Swab");
            System.out.println("Choose a sample type by inputting the corresponding number:);
            String sampleTypeInput = scanner.nextLine();
            String sampleType = "";
            if (sampleTypeInput == "1") {
                sampleType = "Blood";
            }else if (sampleTypeInput == "2") {
                sampleType = "Urine";
            }else if (sampleTypeInput == "3") {
                sampleType = "Swab"
            }else{
                System.out.println("Invalid sample type!");
                return;
            }

            cost = examCost();

            Exam newExam = Exam(cost, name, maxSlots, doctor, sampleType);
        } else if (examTypeInput == "3") {
            examType = "Special";
            System.out.println("Available exam specialties:");
            System.out.println("1. Cardiology\n2. Neurology\n3. Pulmonology");
            System.out.println("Choose an exam specialty by inputting the corresponding number:);
            String examSpecialtyInput =  scanner.nextLine();
            String examSpecialty = "";
            if (examSpecialtyInput == "1") {
                examSpecialty = "Cardiology";
            }else if (examSpecialtyInput == "2") {
                examSpecialty = "Neurology";
            }else if (examSpecialtyInput == "3") {
                examSpecialty = "Pulmomology";
            }else {
                System.out.println("Invalid exam specialty!");
                return;
            }
        } else {
            System.out.println("Invalid input!");
            return; // Return xwris na dhmiourgithei nea exetash
        }
    } // addExam

    // Menu exetasewn
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
                    addExam()
            }
        }
    }
    public static void appointmentMenu(){

    }
    public static void statisticsMenu(){

    }

    public static void main (String[]args){
        boolean done = false;
        while (!done) {
            System.out.println("\n1. Iatroi");
            System.out.println("2. Astheneis");
            System.out.println("3. Exetaseis");
            System.out.println("4. Rantevou");
            System.out.println("5. Statistika");
            System.out.println("0. Exodos");
            System.out.print("> ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    doctorMenu();
                    break;
                case "2":
                    PatientsMenu();
                    break;
                case "3":
                    ExamsMenu();
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
            }
        } // while
    } // main
} // class

