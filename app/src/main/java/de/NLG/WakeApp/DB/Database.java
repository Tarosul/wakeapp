package de.NLG.WakeApp.DB;


import androidx.appcompat.app.AppCompatActivity;

import android.widget.Toast;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;

public class Database extends AppCompatActivity {
    private static final String URL     = "jdbc:mysql://pma.lima-city.de:3306/db_369035_1";
    private static final String USER    = "USER369035_wake";
    private static final String PASSW   = "wakeapp1806";
//    public  static final String CLASSES = "net.sourceforge.jtds.jdbc.Driver";
    public  static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";


    /*public void main(String[] args) {
        JSONArray schedules = this.getSchedules();
    }*/

    @Nullable
    /**
     * create Database connection and return con
     * @return Connection con - Database connection
     */
    public static Connection getDb() {
        try {
            Class.forName(JDBC_DRIVER).newInstance();
//            Connection con = DriverManager.getConnection(URL, USER, PASSW);
            Connection con = DriverManager.getConnection("jdbc:mysql://nikolai12.lima-db.de:3306/db_369035_1", "USER369035_wake", "wakeapp1806");
            String test = "sds";

            return DriverManager.getConnection(URL, USER, PASSW);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get all schedule rows
     * @return JSONArray schedules
     */
    public JSONArray getSchedules() {
        Connection con = getDb();

        try {
            JSONArray schedules = new JSONArray();

            if (con == null) {
                Toast.makeText(getApplicationContext(), "This is my Toast message!", Toast.LENGTH_LONG).show();
            }

            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM wake_schedule");

            while (rs.next()) {
                JSONObject        schedule = new JSONObject();
                ResultSetMetaData columns  = rs.getMetaData();

                int i = 0;
                while (i < columns.getColumnCount()) {
                    i++;
                    String col = columns.getColumnName(i);
                    schedule.put(col, rs.getString(col));
                }
                schedules.put(schedule);
            }

            return schedules;

        } catch (SQLException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
