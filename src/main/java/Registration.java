import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Registration extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JTextField usernameTF;
    private JTextField emailTF;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton cancelButton;
    private JButton registerButton;
    private JButton clickHereToLoginButton;

    public Registration() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setResizable(false);
        this.setSize(new Dimension(500, 230));
        cancelButton.addActionListener(this);
        clickHereToLoginButton.addActionListener(this);
        registerButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        globalFunction g = new globalFunction();
        if(source == cancelButton){
            g.clearTF(this);
        }
        if(source == clickHereToLoginButton){

            JFrame r = new LoginGUI();

            r.setVisible(true);
            this.setVisible(false);
        }
        if(source == registerButton){
            try {
                g.registerUser(usernameTF, passwordField1,passwordField2, emailTF, this);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
