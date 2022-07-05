import javax.swing.*;

public class AddOrModifyTrain {
    JPanel addOrModifyTrainPanel;
    private JButton cancelButton;
    JButton saveButton;
    JTextField weightTextField;
    JTextField nameTextField;
    private JLabel nameLabel;
    private JLabel maxWeightLabel;
    JComboBox typeComboBox;
    private JButton clearAllButton;
    private JLabel typeLabel;

    public AddOrModifyTrain( JFrame curr, JFrame main){

        typeComboBox.addItem("Passenger Train");
        typeComboBox.addItem("Freight Train");

        clearAllButton.addActionListener(e->{
            nameTextField.setText("");
            weightTextField.setText("");
            typeComboBox.setSelectedIndex(0);
        });
        cancelButton.addActionListener(e->{
            curr.dispose();
            main.setEnabled(true);
            main.requestFocus();
        });

    }

    public int getWeight() {
        return Integer.parseInt(weightTextField.getText());
    }

    public String getName() {
        return nameTextField.getText();
    }

    public String getType() {
        return (String) typeComboBox.getSelectedItem();
    }

    public void setType(String type) {
        typeComboBox.setSelectedItem(type);
    }

    public void setWeight(int weight) {
        weightTextField.setText(Integer.toString(weight));
    }

    public void setName(String name) {
        nameTextField.setText(name);
    }

}
