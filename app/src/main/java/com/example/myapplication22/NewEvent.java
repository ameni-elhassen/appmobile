package com.example.myapplication22;

public class NewEvent {

    String name;
    String date;
    String time;
    String description;
    String url;

    public NewEvent(){
        //this constructor is required
    }
    public NewEvent(String neweventname, String neweventdate, String neweventtime, String neweventdescription ,String neweventpic) {

        this.name = neweventname;
        this.date = neweventdate;
        this.time = neweventtime;
        this.url = neweventpic;
        this.description = neweventdescription;
    }
//getter

    public String getNeweventname() {
        return name;
    }
    public String getNeweventdate() {
        return date;
    }
    public String getNeweventtime() {
        return time;
    }
    public String getNeweventdescription() {
        return description;
    }
    public String getNeweventpic() {return url;}

    //setter

    public void setNeweventname(String neweventname) {this.name = neweventname;}
    public void setNeweventdate(String neweventdate) {this.date = neweventdate;}
    public void setNeweventtime(String neweventtime) {time = neweventtime;}
    public void setNeweventdescription(String neweventdescription) {description = neweventdescription;}
    public void setNeweventpic(String neweventpic) {this.url = neweventpic;}
}