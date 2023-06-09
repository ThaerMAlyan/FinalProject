package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SingnIn {

    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private int age;
    private String phone;
    private String gender;
    private String role;

    public SingnIn(String firstName, String lastName, String password, String email, int age, String phone, String gender, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.age = age;
        this.phone = phone;
        this.gender = gender;
        this.role = role;
    }

    public SingnIn() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int save() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "INSERT INTO USERS (id,firstName,lastName,password,email,age,phone,gender,role) VALUES (?, ?, ?, ?,?,?,?,?,?)";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        ps.setString(2, this.getFirstName());
        ps.setString(3, this.getLastName());
        ps.setString(4, this.getPassword());
        ps.setString(5, this.getEmail());
        ps.setInt(6, this.getAge());
        ps.setString(7, this.getPhone());
        ps.setString(8, this.getGender());
        ps.setString(9, this.getRole());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println(this.getFirstName()
                    + " User was added successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

  public static ArrayList<SingnIn> getAllPatientUser() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<SingnIn> patients = new ArrayList<>();
        String sql = "SELECT * FROM USERS WHERE role='user'";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            SingnIn patient = new SingnIn(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6),
                    rs.getString(7), rs.getString(8), rs.getString(9));
            patient.setId(rs.getInt(1));
            patients.add(patient);

        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return patients;
    }
  
    public static ArrayList<SingnIn> getAllAdmin() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<SingnIn> patients = new ArrayList<>();
        String sql = "SELECT * FROM USERS WHERE role='admin'";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            SingnIn patient = new SingnIn(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6),
                    rs.getString(7), rs.getString(8), rs.getString(9));
            patient.setId(rs.getInt(1));
            patients.add(patient);

        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return patients;
    }
    
    
     public static ArrayList<SingnIn> getAll() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<SingnIn> patients = new ArrayList<>();
        String sql = "SELECT * FROM USERS";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            SingnIn patient = new SingnIn(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6),
                    rs.getString(7), rs.getString(8), rs.getString(9));
            patient.setId(rs.getInt(1));
            patients.add(patient);

        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return patients;
    }

}
