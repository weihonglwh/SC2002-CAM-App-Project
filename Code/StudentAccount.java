import java.util.ArrayList;

public class StudentAccount extends UserAccount {
    private ArrayList<String> campsRegistered;
    
    public StudentAccount(String name, String userId, String password, String faculty){
        super(name, userId, password, faculty);
        this.campsRegistered = new ArrayList<>();
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
            return  false;
    }

}
