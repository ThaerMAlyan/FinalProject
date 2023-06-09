package View;

import java.io.IOException;

public class ViewMangeer {

    public static Login login;
    public static SingIN singIN;
    public static DashBordAdmin dashBordAdmin;
    public static DrCoumment drCoumment;
    public static DashBordUser dashBordUser;

    private ViewMangeer() {

    }

    public static void openLoginPage() throws IOException {
        if (login == null) {
            login = new Login();
            login.show();
        } else {
            login.show();
        }

    }

    public static void closeLoginPage() {
        if (login != null) {
            login.close();
        }
    }

    public static void openSingInPage() throws IOException {
        if (singIN == null) {
            singIN = new SingIN();
            singIN.show();
        } else {
            singIN.show();
        }

    }

    public static void closeSingInPage() {
        if (singIN != null) {
            singIN.close();
        }
    }

    public static void openDashBordAdminPage() throws IOException {
        if (dashBordAdmin == null) {
            dashBordAdmin = new DashBordAdmin();
            dashBordAdmin.show();
        } else {
            dashBordAdmin.show();
        }

    }

    public static void closeDashBordAdminInPage() {
        if (dashBordAdmin != null) {
            dashBordAdmin.close();
        }
    }

    public static void openDrCoumment() throws IOException {
        if (drCoumment == null) {
            drCoumment = new DrCoumment();
            drCoumment.show();
        } else {
            drCoumment.show();
        }

    }

    public static void closeDrCoumment() {
        if (drCoumment != null) {
            drCoumment.close();
        }
    }

    
        public static void openDashBordUserPage() throws IOException {
        if (dashBordUser == null) {
            dashBordUser = new DashBordUser();
            dashBordUser.show();
        } else {
            dashBordUser.show();
        }

    }

    public static void closeDashBordUserPage() {
        if (dashBordUser != null) {
            dashBordUser.close();
        }
    }
    
}
