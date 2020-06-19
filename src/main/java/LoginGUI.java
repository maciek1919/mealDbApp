import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginGUI extends JFrame implements ActionListener {
    private JTextField textField1;
    private JPanel mainPanel;
    private JPasswordField passwordField1;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton cancelButton;
    private JButton loginButton;
    private JButton clickHereToCreateButton;
    public int id = 0;



    public static void main(String[] args) throws SQLException {
        JFrame frame = new LoginGUI();
        frame.setVisible(true);
    }
    public LoginGUI(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setResizable(false);
        this.setSize(new Dimension(500, 170));
        cancelButton.addActionListener(this);
        clickHereToCreateButton.addActionListener(this);
        loginButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if(source == cancelButton){
            globalFunction g = new globalFunction();
            g.clearTF(this);
        }
        if(source == clickHereToCreateButton){
            JFrame r = new Registration();
            r.setVisible(true);
            this.setVisible(false);

        }
        if(source == loginButton){
            try {
                InsertUsersToPG i = new InsertUsersToPG();
                LoginGUI.this.id = i.loginUser(textField1.getText(), passwordField1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(id!= 0 ){
                   globalFunction.clearTF(this);
                   this.setVisible(false);
                   JFrame r = new MainApp();
                   r.setVisible(true);
                   System.out.println(id);}
               else{System.out.println("nie zalogowano");}

        }
    }
}
