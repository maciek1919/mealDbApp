
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class globalFunction {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\."+
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";;
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static void clearTF(JFrame intFrame)
    {
        if (intFrame == null)
            return;
        Container con = intFrame.getContentPane();
        for (Component c : con.getComponents())
        {
            if (c instanceof JTextField)
            {
                JTextField j = (JTextField)c;
                j.setText("");
            }
        }
    }


    public static boolean emailValidator(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }





    public void registerUser(JTextField username, JPasswordField password1, JPasswordField password2, JTextField email, JFrame frame) throws SQLException {
        if((Arrays.equals(password1.getPassword() ,password2.getPassword())) && !username.getText().isEmpty() && emailValidator(email.getText())){
            System.out.println(password1.getPassword().toString());
            InsertUsersToPG i = new InsertUsersToPG();
            i.insertUsers(username.getText(), Arrays.toString(password1.getPassword()), email.getText(), frame);
        }
        else{
            System.out.println("nie weszło ;c");
        }
    }

}
