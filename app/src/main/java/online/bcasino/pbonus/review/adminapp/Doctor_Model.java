package online.bcasino.pbonus.review.adminapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Doctor_Model {
    String name;
    String gender;
    String country;
    String age;
    String id;
    String email;
    String department;
    String role;
    String profilePic;
    String slot1,slot2,slot3,slot4,slot5,date;

    public Doctor_Model(String slot1, String slot2, String slot3, String slot4, String slot5, String date) {
        this.slot1 = slot1;
        this.slot2 = slot2;
        this.slot3 = slot3;
        this.slot4 = slot4;
        this.slot5 = slot5;
        this.date = date;
    }







    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    String fee;

    public Doctor_Model() {
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }





    public Doctor_Model(String name, String gender, String country, String age, String department) {
        this.name = name;
        this.gender = gender;
        this.country = country;
        this.age = age;
        this.department=department;

    }



    public Doctor_Model(String name, String gender, String country, String age, String id, String email, String department, String fee ,String role,String profilePic) {
        this.name = name;
        this.gender = gender;
        this.country = country;
        this.age = age;
        this.id = id;
        this.email=email;
        this.department=department;
        this.fee=fee;
        this.role=role;
        this.profilePic=profilePic;

    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }


    public String getCountry() {
        return country;
    }

    public String getAge() {
        return age;
    }
    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public void setCountry(String country) {
        this.country = country;
    }

    public void setAge(String age) {
        this.age = age;
    }
    public void setId(String id) {
        this.id = id;
    }
}
