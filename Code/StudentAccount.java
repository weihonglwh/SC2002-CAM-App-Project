import java.util.ArrayList;

public class StudentAccount extends UserAccount {
    private ArrayList<String> campsRegistered;
    private String campCommOf;
    private int points;
    private IdGenerator idGenerator = new IdGenerator();
    
    public StudentAccount(String name, String userId, String password, String faculty){
        super(name, userId, password, faculty);
        this.campsRegistered = new ArrayList<>();
        this.points = 0;
        this.campCommOf = "";
    }

    public StudentAccount(String name, String userId, String password, String faculty, int points, String campCommOf, ArrayList<String> registered) {
        super(name, userId, password, faculty);
        this.campsRegistered = registered;
        this.points = points;
        this.campCommOf = campCommOf;
    }

    public int getPoints(){
        return points;
    }

    public void addPoints(){
        this.points++;
    }

    public String getCampCommOf() {
        return campCommOf;
    }

    public void setCampCommOf(String camp) {
        campCommOf = camp;
    }

    public ArrayList<String> getCampsRegistered() {
        return campsRegistered;
    }

    public void addCamps(String campName){
        campsRegistered.add(campName);
    }

    public ArrayList<String> getCamps()
    {
        return campsRegistered;
    }

    public boolean removeCamps(String campName)
    {
            int index = campsRegistered.indexOf(campName);
            if (index >= 0){
                campsRegistered.remove(index);
                return true;
            }
            return false;
    }

    public void printProfile(){
        System.out.println("Name: " + super. getName());
        System.out.println("UserID: " + super.getUserId());
        System.out.println("Faculty: " + super.getFaculty());
        if(!campCommOf.isEmpty())
            System.out.println("Points as Camp-Comm: " + points + "pts");
        System.out.println("-----------------------------------------------");
        System.out.println("|           List of registered camps          |");
        System.out.printf("| %-20s | %-20s |%n", "Camp Name", "Role");
        if(!campCommOf.isEmpty())
            System.out.printf("| %-20s | %-20s |%n", campCommOf, "Camp Committee");
        for (String tarCamp: campsRegistered)
            System.out.printf("| %-20s | %-20s |%n", tarCamp, "Participant");
        System.out.println("-----------------------------------------------");

    }
}
