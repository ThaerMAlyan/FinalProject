package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Appointments {

    private int id;
    private String appointmentDate;
    private String appointmentDay;
    private String appointmentTime;
    private String state;

    public Appointments(String appointmentDate, String appointmentDay, String appointmentTime, String state) {
        this.appointmentDate = appointmentDate;
        this.appointmentDay = appointmentDay;
        this.appointmentTime = appointmentTime;
        this.state = state;
    }

    public Appointments() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentDay() {
        return appointmentDay;
    }

    public void setAppointmentDay(String appointmentDay) {
        this.appointmentDay = appointmentDay;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int save() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "INSERT INTO appointments(id,appointmentDate,appointmentDay,appointmentTime,state) VALUES (?, ?, ?, ?,?)";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        ps.setString(2, this.getAppointmentDate());
        ps.setString(3, this.getAppointmentDay());
        ps.setString(4, this.getAppointmentTime());
        ps.setString(5, this.getState());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("Appointments was added successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

    public static ArrayList<Appointments> getAllAppointments() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Appointments> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            Appointments appointment = new Appointments(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            appointment.setId(rs.getInt(1));
            appointments.add(appointment);

        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return appointments;
    }

    public static ArrayList<Appointments> getAllFreeAppointments() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Appointments> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments WHERE state='free'";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            Appointments appointment = new Appointments(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            appointment.setId(rs.getInt(1));
            appointments.add(appointment);

        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return appointments;
    }

    public int update() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "UPDATE appointments SET appointmentDate=?, appointmentDay=?, appointmentTime=? , state=? WHERE ID=?";
        ps = c.prepareStatement(sql);
        ps.setString(1, this.getAppointmentDate());
        ps.setString(2, this.getAppointmentDay());
        ps.setString(3, this.getAppointmentTime());
        ps.setString(4, this.getState());
        ps.setInt(5, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("Appointments with id : " + this.getId() + " was updated successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

    public int delete() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "DELETE FROM appointments WHERE ID=? ";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("The appointments with id : " + this.getId() + " was deleted successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

    public int countFreeAppointment() throws SQLException {
        int count = 0;
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Appointments> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments WHERE state='free'";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            count++;
        }
        if (ps != null) {
            ps.close();
        }
        c.close();

        return count;
    }

}
