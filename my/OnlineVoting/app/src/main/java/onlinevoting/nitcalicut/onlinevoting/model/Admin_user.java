package onlinevoting.nitcalicut.onlinevoting.model;

public class Admin_user {
    private String admin_id,admin_name,admin_email,admin_pwd;

    public Admin_user(){

    }

    public Admin_user(String admin_id,String admin_name,String admin_email,String admin_pwd){
        this.admin_id=admin_id;
        this.admin_name = admin_name;
        this.admin_email = admin_email;
        this.admin_pwd = admin_pwd;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public String getAdmin_email() {
        return admin_email;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public String getAdmin_pwd() {
        return admin_pwd;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public void setAdmin_email(String admin_email) {
        this.admin_email = admin_email;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public void setAdmin_pwd(String admin_pwd) {
        this.admin_pwd = admin_pwd;
    }
}
