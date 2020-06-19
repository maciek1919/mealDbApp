import org.apache.commons.codec.digest.DigestUtils;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InsertUsersToPG {

    public static Properties readProperties() {

        Properties props = new Properties();
        Path myPath = Paths.get("src/main/resources/database.properties");

        try {
            BufferedReader bf = Files.newBufferedReader(myPath,
                    StandardCharsets.UTF_8);

            props.load(bf);
        } catch (IOException ex) {
            Logger.getLogger(InsertUsersToPG.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        return props;
    }


    public int loginUser(String username, JPasswordField password1) throws SQLException {
        Properties props = readProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");
        Connection con = DriverManager.getConnection(url, user, password);


        try (PreparedStatement checkUserExists = con.prepareStatement(
            "SELECT id FROM users WHERE username = ? AND password = ? ")) {
            checkUserExists.setString(1, username);
            String password2 = DigestUtils.sha256Hex(Arrays.toString(password1.getPassword()));
            checkUserExists.setString(2, password2);
            try (ResultSet rs = checkUserExists.executeQuery()) {
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "zalogowany!");
                    return rs.getInt("id");
                } else {
                    JOptionPane.showMessageDialog(null, "Bledne haslo lub nazwa uzytkownika");
                    return 0;

                }
            }
        }
    }

    public void insertUsers(String username, String password1, String email, JFrame frame) throws SQLException {
        Properties props = readProperties();

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");
        Connection con = DriverManager.getConnection(url, user, password);

        String query = "INSERT INTO users(username, password, email) VALUES(?, ?, ?)";
        //In Java a PreparedStatement is an object which represents a precompiled SQL statement
        try (PreparedStatement checkAccountExists = con.prepareStatement(
                "SELECT 1 FROM users WHERE username = ? OR email = ?")) {
            checkAccountExists.setString(1, username);
            checkAccountExists.setString(2, email);
            try (ResultSet rs = checkAccountExists.executeQuery()) {
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Nazwa użytkownika lub email jest w użyciu!");

                } else {
                    try (PreparedStatement pst = con.prepareStatement(query)) {
                        pst.setString(1, username);
                        String password2 = DigestUtils.sha256Hex(password1);
                        pst.setString(2, password2);
                        pst.setString(3, email);
                        pst.executeUpdate();

                    } catch (SQLException ex) {

                        Logger lgr = Logger.getLogger(InsertUsersToPG.class.getName());
                        lgr.log(Level.SEVERE, ex.getMessage(), ex);
                    }
                }
            }
        }
        globalFunction.clearTF(frame);
    }
}