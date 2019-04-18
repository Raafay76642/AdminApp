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

    public Doctor_Model(Map<String, Object> array) {
        this.array = array;
    }

    Map<String, Object> array = new HashMap<>();




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



    public Doctor_Model(String name, String gender, String country, String age, String id, String email, String department, String fee) {
        this.name = name;
        this.gender = gender;
        this.country = country;
        this.age = age;
        this.id = id;
        this.email=email;
        this.department=department;
        this.fee=fee;

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
