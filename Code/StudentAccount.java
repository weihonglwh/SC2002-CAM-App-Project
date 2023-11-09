public class StudentAccount extends UserAccount {
    
    public StudentAccount(String name, String userId, String password, String faculty){
        super(name, userId, password, faculty);
    }

    public String getUsername(){
        return super.getUserId();
    }
}
