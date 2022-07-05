package pl.retsuz.view;

import pl.retsuz.collections.IDataCollection;
import pl.retsuz.currency.Currency;
import pl.retsuz.currency.ICurrency;
import pl.retsuz.exchange.IExchange;
import java.util.Scanner;

public class StandardView implements ICurrencyView{
    private IExchange ex;
    private IDataCollection dat;
    private Scanner scan = new Scanner(System.in);
    @Override
    public void setExchange(IExchange exchange){
        this.ex = exchange;
    } //Ustawia referencję do obiektu typu implementującego IExchange
    @Override
    public void setDataCollection(IDataCollection collection){
        this.dat = collection;
    } //Ustawia referencję do obiektu typu implementującego IDataCollection
    public void ViewAll(IDataCollection coll){
        System.out.println(coll.ToString());
    } //Wyświetla wszystkie waluty
    @Override
    public ICurrency StringToCurrency(String code){
        Currency cur = new Currency();
        cur.setCode(code);
        return dat.getCurrencyByCode(cur);
    } //Po podaniu kodu tworzy obiekt Currency z ustawionym kodem, a następnie z jego wykorzystaniem przeszukuje kolekcję typu IDataCollection
    private String parseString(String label)
    {
        System.out.println(label);
        String tmpStr;
        try
        {
            tmpStr = scan.next();
        } catch (Exception e)
        {
            System.err.print("Invalid values!");
            tmpStr = parseString(label);
        }
        return tmpStr;
    }

    private double parseDouble(String label)
    {
        System.out.println(label);
        double tmpStr;
        try
        {
            tmpStr = scan.nextDouble();
        } catch (Exception e)
        {
            System.err.print("Invalid values!");
            tmpStr = parseDouble(label);
        }
        return tmpStr;
    }
    @Override
    public ICurrency ChooseCurrency(String label){
        return StringToCurrency(parseString(label));
    }
    public void exchange(){
        ICurrency source = ChooseCurrency("Choose starting currency: ");
        ICurrency target = ChooseCurrency("Choose end currency: ");
        double amount = parseDouble("How much money do you want to egchange: ");
        System.out.println("After exchange: " + ex.exchange(source, target, amount) + " " + target.getCode());
    } //Interakcyjnie na bazie metody ChooseCurrency dokonuje przeliczenia walut na bazie IExchange
    @Override
    public void menu(){
        char option;
        while (true)
        {
            System.out.println("**************************");
            System.out.println("0. EXIT");
            System.out.println("1. Exchange currency");
            System.out.println("2. Show all currencies");
            System.out.println("**************************");
            System.out.print("Choose option: ");
            option = scan.next().charAt(0);
            switch (option)
            {
                case '0':
                    System.out.println("Initializing EXIT");
                    System.exit(0);
                    break;
                case '1':
                    exchange();
                    break;
                case '2':
                    ViewAll(dat);
                    break;
                default:
                    System.out.println("Invalid option");
                    System.out.println("Try again!");
            }
        }
    } //Interakcyjnie wyświetla menu
}
