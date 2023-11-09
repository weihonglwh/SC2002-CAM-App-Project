public class StaffAccount extends UserAccount {
    
    public StaffAccount(String name, String userId, String password, String faculty){
        super(name, userId, password, faculty);
    }

    public String getUsername(){
        return super.getUserId();
    }
}
