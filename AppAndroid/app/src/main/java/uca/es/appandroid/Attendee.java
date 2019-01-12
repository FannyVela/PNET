package uca.es.appandroid;

public class Attendee {

    private String id;
    private String name;
    private String surname1;
    private String dni;
    private String birthdate; //cambiar
    private String regDate; //cambiar
    private String email;
    private int phone;

    public Attendee(String id,String name, String surname1, String dni, String birthdate, String regDate, String email, int phone) {
        this.id = id;
        this.name = name;
        this.surname1 = surname1;
        this.dni = dni;
        this.birthdate = birthdate;
        this.regDate = regDate;
        this.email = email;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname1() {
        return surname1;
    }

    public void setSurname1(String surname1) {
        this.surname1 = surname1;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
