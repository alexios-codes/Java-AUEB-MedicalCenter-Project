public class MicrobiologicalExam extends Exam {
    private String sampleType;
    private final double costIncreaseRate = 0.30;
 //constructor gia dilosis
    public MicrobiologicalExam(int cost, String ExamName, int maxSlotsPerDay, int leadDoctorID, String sampleType) {
        super(cost, "Microbiological", ExamName, maxSlotsPerDay, leadDoctorID);
        this.sampleType = sampleType;
    }
    //constructor gia arxeia
    public MicrobiologicalExam(int id, int cost, String ExamName, int maxSlotsPerDay, int leadDoctorID, String sampleType) {
        super(id, cost, "Microbiological", ExamName, maxSlotsPerDay, leadDoctorID);
        this.sampleType = sampleType;
    }

    // getters
    public String getSampleType() {
        return sampleType;
    }
    public double getFinalCost(boolean fastResults) {
        if (fastResults) {
            return getCost() + getCost()*costIncreaseRate;
        } else {
            return getCost();
        }
    }

    public String toString() {
        return super.toString() + "| Sample type: " + sampleType;

    }
}
