public class SpecializedExam extends Exam {
    private String examSpecialty;
    private final double costIncreaseRate = 0.30;
    // Basic constructor
    public SpecializedExam(int cost, String ExamName, int maxSlotsPerDay, int leadDoctorID, String examSpecialty) {
        super(cost, "Specialized", ExamName, maxSlotsPerDay, leadDoctorID);
        this.examSpecialty = examSpecialty;
    }
    // Constructor used for files
    public SpecializedExam(int id, int cost, String ExamName, int maxSlotsPerDay, int leadDoctorID, String examSpecialty) {
        super(id, cost, "Specialized", ExamName, maxSlotsPerDay, leadDoctorID);
        this.examSpecialty = examSpecialty;
    }

    // getters
    public String getExamSpecialty() {
        return examSpecialty;
    }
    public double getFinalCost(boolean fastResults) {
        if (fastResults) {
            return getCost() + getCost()*costIncreaseRate;
        } else {
            return getCost();
        }
    }


    public String toString() {
        return super.toString() + "| Exam specialty: " + examSpecialty;

    }
}
