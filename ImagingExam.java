public class ImagingExam extends Exam {
    private String machineType;
    private final double costIncreaseRate = 0.10;
//constructor gia dilosis
    public ImagingExam(int cost, String ExamName, int maxSlotsPerDay, int leadDoctorID, String machineType) {
        super(cost, "Imaging", ExamName, maxSlotsPerDay, leadDoctorID);
        this.machineType = machineType;
    }
//constructor gia arxeia
    public ImagingExam(int id,int cost,String ExamName,int maxSlotsPerDay,int leadDoctorID,String machineType) {
        super(id,cost,"Imaging",ExamName,maxSlotsPerDay,leadDoctorID);
        this.machineType=machineType;
    }

    // getters
    public String getMachineType() {
        return machineType;
    }
    public double getFinalCost(boolean fastResults) {
        if (fastResults) {
            return getCost() + getCost() * costIncreaseRate;
        } else {
            return getCost();
        }
    }

    public String toString() {
        return super.toString() + "| Machine type: " + machineType;

    }
}
