import javax.swing.*;
import java.sql.Time;

public class AddOrModifyTime {
    JPanel addOrModifyTimePanel;
    private JTextField stationNameTextField;
    JButton saveButton;
    private JButton cancelButton;
    private JButton clearAllButton;
    private JTextField trainNameTextField;
    private JTextField arrivalHourTextField;
    private JTextField arrivalMinuteTextField;
    private JTextField departureHourTextField;
    private JTextField departureMinuteTextField;

    public AddOrModifyTime( JFrame curr, JFrame main){
        clearAllButton.addActionListener(e->{
            trainNameTextField.setText("");
            stationNameTextField.setText("");
            arrivalHourTextField.setText("");
            arrivalMinuteTextField.setText("");
            departureHourTextField.setText("");
            departureMinuteTextField.setText("");
        });
        cancelButton.addActionListener(e->{
            curr.dispose();
            main.setEnabled(true);
            main.requestFocus();
        });

    }
    public String getStationName(){
        return stationNameTextField.getText();
    }
    public String getTrainName(){
        return trainNameTextField.getText();
    }
    public void setStationName(String name){
         stationNameTextField.setText(name);
    }
    public void setTrainName(String name){
         trainNameTextField.setText(name);
    }

    public void setArrivalHourTextField(int arrivalHourTextField) {
        this.arrivalHourTextField.setText(Integer.toString(arrivalHourTextField));
    }

    public void setArrivalMinuteTextField(int arrivalMinuteTextField) {
        this.arrivalMinuteTextField.setText(Integer.toString(arrivalMinuteTextField));
    }

    public void setDepartureHourTextField(int departureHourTextField) {
        this.departureHourTextField.setText(Integer.toString(departureHourTextField));
    }

    public void setDepartureMinuteTextField(int departureMinuteTextField) {
        this.departureMinuteTextField.setText(Integer.toString(departureMinuteTextField));
    }

    public Time getArrival(){
        int hh = Integer.parseInt(arrivalHourTextField.getText());
        int mm = Integer.parseInt(arrivalMinuteTextField.getText());
        if(hh<0 || hh>23 || mm<0 || mm>59){
            throw new IllegalArgumentException();
        }

        return Time.valueOf(hh+":"+mm+":00");
    }
    public Time getDeparture(){
        int hh = Integer.parseInt(departureHourTextField.getText());
        int mm = Integer.parseInt(departureMinuteTextField.getText());
        if(hh<0 || hh>23 || mm<0 || mm>59){
            throw new IllegalArgumentException();
        }

        return Time.valueOf(hh+":"+mm+":00");
    }
}
