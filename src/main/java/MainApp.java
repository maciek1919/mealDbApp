import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.SQLException;

public class MainApp extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JSpinner spinner1;
    private JButton zapiszPosiłekButton;
    private JButton usuńPosiłekButton;
    private JPanel mainPanel;
    private JScrollPane scrollPane1;
    private JTable table1;



    public MainApp() {
        LoginGUI l = new LoginGUI();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        System.out.println(l.id);

    }

    private void createUIComponents() throws SQLException {
        // TODO: place custom component creation code here
        LoginGUI l = new LoginGUI();
        TableModel model = new mealTable(arrayFromDb.getAllMeals(l.id));
        table1 = new JTable(model);
        table1.setAutoCreateRowSorter(true);
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table1.getTableHeader().setReorderingAllowed(false);

        this.scrollPane1 = new JScrollPane(table1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);




    }
}
