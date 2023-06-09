package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Booked_Appointments {

    private int id;
    private int appointmentId;
    private int userId;
    private String status;
    private String doctorCommnet;

    public Booked_Appointments(int appointmentId, int userId, String status, String doctorCommnet) {
        this.appointmentId = appointmentId;
        this.userId = userId;
        this.status = status;
        this.doctorCommnet = doctorCommnet;
    }

    public Booked_Appointments(String status, String doctorCommnet) {
        this.status = status;
        this.doctorCommnet = doctorCommnet;
    }

    public Booked_Appointments() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDoctorCommnet() {
        return doctorCommnet;
    }

    public void setDoctorCommnet(String doctorCommnet) {
        this.doctorCommnet = doctorCommnet;
    }

    public int save() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "INSERT INTO booked_appointments(id,appointmentId,userId,status,doctorCommnet) VALUES (?, ?, ?, ?,?)";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        ps.setInt(2, this.getAppointmentId());
        ps.setInt(3, this.getUserId());
        ps.setString(4, this.getStatus());
        ps.setString(5, this.getDoctorCommnet());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("Booked Appointments was added successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

    public static ArrayList<Booked_Appointments> getAllAppointments() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Booked_Appointments> appointments = new ArrayList<>();
        String sql = "SELECT * FROM booked_appointments";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            Booked_Appointments appointment = new Booked_Appointments(rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5));
            appointment.setId(rs.getInt(1));
            appointments.add(appointment);

        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return appointments;
    }

    public int countBookedAppointment() throws SQLException {
        int count = 0;
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Booked_Appointments> appointments = new ArrayList<>();
        String sql = "SELECT * FROM booked_appointments";
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

    public int update() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "UPDATE booked_appointments SET status=?, doctorCommnet=? WHERE ID=?";
        ps = c.prepareStatement(sql);
        ps.setString(1, this.getStatus());
        ps.setString(2, this.getDoctorCommnet());
        ps.setInt(3, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("Patient with id : " + this.id + " was add comment successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

}
