package online.bcasino.pbonus.review.adminapp;

public class ProfileModel {
    String name;
    String gender;
    String country;
    String age;
    String id;
    String email;
    String department;

    public ProfileModel() {
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





    public ProfileModel(String name, String gender, String country, String age, String department) {
        this.name = name;
        this.gender = gender;
        this.country = country;
        this.age = age;
        this.department=department;

    }



    public ProfileModel(String name, String gender, String country, String age, String id, String email, String department) {
        this.name = name;
        this.gender = gender;
        this.country = country;
        this.age = age;
        this.id = id;
        this.email=email;
        this.department=department;

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
