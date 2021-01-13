package com.example.nsbm;

public class UserData {

    String vname,vemail,vpassword,vphone;

    public UserData(String vname, String vemail, String vpassword, String vphone) {
        this.vname = vname;
        this.vemail = vemail;
        this.vpassword = vpassword;
        this.vphone = vphone;
    }

    public UserData(){

    }


    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getVemail() {
        return vemail;
    }

    public void setVemail(String vemail) {
        this.vemail = vemail;
    }

    public String getVpassword() {
        return vpassword;
    }

    public void setVpassword(String vpassword) {
        this.vpassword = vpassword;
    }

    public String getVphone() {
        return vphone;
    }

    public void setVphone(String vphone) {
        this.vphone = vphone;
    }
}
