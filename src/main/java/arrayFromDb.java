import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class arrayFromDb {

    public static ArrayList<Meal> getAllMeals(int id) throws SQLException {
        Properties props = InsertUsersToPG.readProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement checkAccountExists = conn.prepareStatement(
                "SELECT * FROM meals WHERE user_id = ? ");
        checkAccountExists.setInt(1, id);
        ResultSet rst;
        rst = checkAccountExists.executeQuery();
        ArrayList<Meal> mealsList = new ArrayList<>();
        while (rst.next()) {
            String name = rst.getString("name");
            String kcal = rst.getString("kcal");
            String time = rst.getString("time");
            Meal meal = new Meal(rst.getString("name"), rst.getString("kcal"), rst.getString("time"));
            mealsList.add(meal);
        }
        return mealsList;
    }
}
