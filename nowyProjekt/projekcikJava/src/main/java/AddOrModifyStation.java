import org.hibernate.Transaction;

import javax.swing.*;

public class AddOrModifyStation {
    JPanel addOrModifyStationPanel;
    JTextField nameTextField;
    JTextField numOfPlatformsTextField;
    private JButton clearAllButton;
    JButton saveButton;
    private JLabel nameLabel;
    private JLabel numOfPlatformsLabel;
    private JButton cancelButton;

    public AddOrModifyStation( JFrame curr, JFrame main){
        clearAllButton.addActionListener(e->{
            nameTextField.setText("");
            numOfPlatformsTextField.setText("");
        });
        cancelButton.addActionListener(e->{
            curr.dispose();
            main.setEnabled(true);
            main.requestFocus();
        });

    }
    public String getName(){
        return nameTextField.getText();
    }
    public int getNum(){
        return Integer.parseInt(numOfPlatformsTextField.getText());
    }
    public void setName(String name){
        nameTextField.setText(name);
    }
    public void setNum(int num){
        numOfPlatformsTextField.setText(Integer.toString(num));
    }
}
