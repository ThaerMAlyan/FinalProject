package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Patient_Booked {

    private int id;
    private String firstName;
    private String lastName;
    private String appointmentDate;
    private String appointmentTime;
    private String status;
    private String doctorCommnet;
    private static int userId;

    public Patient_Booked(int id, String firstName, String lastName, String appointmentDate, String appointmentTime, String status, String doctorCommnet) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.doctorCommnet = doctorCommnet;
    }

    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        Patient_Booked.userId = userId;
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

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDoctorCommnet() {
        return doctorCommnet;
    }

    public void setDoctorCommnet(String doctorCommnet) {
        this.doctorCommnet = doctorCommnet;
    }

    public static ArrayList<Patient_Booked> getAllAppointments() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Patient_Booked> appointments = new ArrayList<>();

        String sql = "SELECT booked_appointments.id , firstName , lastName , appointmentDate , appointmentTime , status , doctorCommnet from booked_appointments INNER JOIN appointments on booked_appointments.appointmentId = appointments.id\n"
                + "INNER JOIN users on booked_appointments.userId = users.id ORDER BY booked_appointments.id;";

        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            Patient_Booked appointment = new Patient_Booked(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
            appointments.add(appointment);

        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return appointments;
    }

    public int delete() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "DELETE FROM booked_appointments WHERE ID=? ";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("The Patient with id : " + this.getId() + " was deleted successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }
}
