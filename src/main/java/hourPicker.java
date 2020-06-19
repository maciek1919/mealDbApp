import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.util.Calendar;

public class hourPicker {

    SpinnerDateModel spinnerModel() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 24); // 24 == 12 PM == 00:00:00
        calendar.set(Calendar.MINUTE, 0);


        SpinnerDateModel model = new SpinnerDateModel();
        model.setValue(calendar.getTime());
        return model;
    }

//        JSpinner spinner = new JSpinner(model);
//
//        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm");
//        DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();
//        formatter.setAllowsInvalid(false); // this makes what you want
//        formatter.setOverwriteMode(true);
//
//        spinner.setEditor(editor);
//        return model;

}

