public abstract class Exam{
    private static int idCounter = 1 ;

    private int ExamID;
    private String ExamCategory;
    private String ExamName;
    private int maxSlotsPerDay;
    private int cost;
    private int leadDoctorID;


    public Exam(int cost,String ExamCategory,String ExamName,int maxSlotsPerDay,int leadDoctorID){
        this.ExamID = idCounter++;
        this.ExamCategory = ExamCategory;
        this.ExamName=ExamName;
        this.maxSlotsPerDay=maxSlotsPerDay;
        this.leadDoctorID=leadDoctorID;
        this.cost=cost;
    }

    // getters
    public int getExamID() {
        return ExamID;
    }
    public String getExamCategory() {
        return ExamCategory;
    }
    public String getExamName() {
        return ExamName;
    }
    public int getMaxSlotsPerDay() {
        return maxSlotsPerDay;
    }
    public int getCost() {
        return cost;
    }
    public int getLeadDoctor(){
        return leadDoctorID;
    }

    public abstract double getFinalCost(boolean fastResults); // για να υπολογίζεται το τελικό κόστος στις υποκλάσεις

    public String toString() {
        return "Exam ID: " + ExamID + " | Exam name: " + ExamName + " | Exam category: " +
                ExamCategory + " | Max slots per day: " + maxSlotsPerDay + " | Cost: "
                + cost + " | Lead doctor: " + leadDoctorID;
    }
}
