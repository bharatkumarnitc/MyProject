package onlinevoting.nitcalicut.onlinevoting.model;

public class Vote_User {
    private int vote_user_id;
    private String username,rollno,dept_name,email;
    public Vote_User(){

    }
    public Vote_User(int vote_user_id,String username,String rollno,String dept_name,String email){
        this.vote_user_id = vote_user_id;
        this.username = username;
        this.email = email;
        this.dept_name = dept_name;
        this.rollno = rollno;
    }

    public String getEmail() {
        return email;
    }

    public String getDept_name() {
        return dept_name;
    }

    public String getRollno() {
        return rollno;
    }

    public String getUsername() {
        return username;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getVote_user_id() {
        return vote_user_id;
    }

    public void setVote_user_id(int vote_user_id) {
        this.vote_user_id = vote_user_id;
    }
}
