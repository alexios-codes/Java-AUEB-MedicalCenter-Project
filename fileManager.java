import java.io.*;
import java.util.HashMap;

public class fileManager{
public void loadData(HashMap<Integer, Doctor> doctorsMap,
                     HashMap<Integer, Patient> patientsMap,
                     HashMap<Integer, Exam> examsMap,
                     HashMap<Integer, Appointment> appointmentsMap) {
    loadDoctors(doctorsMap);
    loadPatients(patientsMap);
    loadExams(examsMap);
    loadAppointments(appointmentsMap);
}
private void loadDoctors(HashMap<Integer, Doctor> doctorsMap){

}
private void loadPatients(HashMap<Integer, Patient> patientsMap){

}
private void loadExams(HashMap<Integer, Exam> examsMap) {


}
private void loadAppointments(HashMap<Integer, Appointment> appointmentsMap){

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

}
private void savePatients(HashMap<Integer, Patient> patientsMap){

}
private void saveExams(HashMap<Integer, Exam> examsMap){

}
private void saveAppointments(HashMap<Integer, Appointment> appointmentsMap){

}

}
