import javax.swing.table.AbstractTableModel;
import java.util.List;

public class mealTable extends AbstractTableModel{
    private List<Meal> meals;
    private String[] columns;



    public mealTable(List<Meal> aMealList) {
        super();
        meals = aMealList;
        columns = new String[]{"Nazwa", "Kcal", "Godzina"};
    }

    @Override
    public int getRowCount() {
        return meals.size();
    }
    @Override
    public int getColumnCount() {
        return columns.length;
    }

//    @Override
//    public Class<?> getColumnClass(int columnIndex) {
//        if (meals.isEmpty()) {
//            return Object.class;
//        }
//        return getValueAt(0, columnIndex).getClass();
//    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < meals.size()) {
            Meal c = meals.get(rowIndex);
            if (columnIndex == 0) {
                return (c.getName());
            } else if (columnIndex == 1) {
                return (c.getKcal());
            } else if (columnIndex == 2){
                return (c.getTime());
            }
        }
        return null;
    }
    public String getColumnName(int col) {
        return columns[col];
    }
}

