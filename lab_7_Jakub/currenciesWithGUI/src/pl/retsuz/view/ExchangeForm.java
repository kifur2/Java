package pl.retsuz.view;

import org.xml.sax.SAXException;
import pl.retsuz.collections.CurrencyDataCollection;
import pl.retsuz.collections.IDataCollection;
import pl.retsuz.collections.providers.IStringCurrencyCollectionProvider;
import pl.retsuz.collections.providers.XMLCurrencyCollectionProvider;
import pl.retsuz.currency.ICurrency;
import pl.retsuz.data.IRemoteDataProvider;
import pl.retsuz.data.RemoteDataProvider;
import pl.retsuz.exchange.Exchange;
import pl.retsuz.exchange.IExchange;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.Remote;
import java.util.List;

public class ExchangeForm {
    private JPanel mainPanel;
    private JComboBox sourceCurrencyComboBox;
    private JComboBox destinationCurrencyComboBox;
    private JTextField quantityTextField;
    private JTextField resultTextField;
    private JButton exchangeButton;

    private IRemoteDataProvider provider;
    private IDataCollection LastA;
    private IStringCurrencyCollectionProvider xmlProvider;
    private IExchange exchange;

    private static ExchangeForm form;

    public ExchangeForm() {
        exchangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double value;
                    String sVal = quantityTextField.getText();
                    value = Math.abs(Double.parseDouble(sVal));
                    ICurrency from = (ICurrency) sourceCurrencyComboBox.getSelectedItem();
                    ICurrency to = (ICurrency) destinationCurrencyComboBox.getSelectedItem();
                    double result = exchange.exchange(from, to, value);
                    resultTextField.setText(Double.toString(result));
                    quantityTextField.setText(Double.toString(value));
                }
                catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"Zły format liczby!");
                }
            }
        });
    }

    public static void initRates(ExchangeForm frm) {
        frm.provider = new RemoteDataProvider();
        frm.LastA = new CurrencyDataCollection();
        frm.xmlProvider = new XMLCurrencyCollectionProvider();
        frm.exchange = new Exchange();
    }

    public static void requireDefault(ExchangeForm frm) {
        try {
            String result = frm.provider.acquireRemoteData("https://www.nbp.pl/kursy/xml/LastA.xml");
            frm.xmlProvider.provide(result, frm.LastA);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (SAXException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (ParserConfigurationException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public static void initForm(ExchangeForm frm) {
        int i = 0;
        List<ICurrency> cList = frm.LastA.getCurrencyList();
        for (i = 0; i < cList.size(); i++) {
            frm.sourceCurrencyComboBox.addItem(cList.get(i));
            frm.destinationCurrencyComboBox.addItem(cList.get(i));
        }
    }

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Kantor wymiany walut");
        form = new ExchangeForm();
        mainFrame.setContentPane(form.mainPanel);
        initRates(form);
        requireDefault(form);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
        initForm(form);
    }
}
