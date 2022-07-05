package pl.retsuz.collections;

import pl.retsuz.currency.ICurrency;

import java.util.*;

public class CurrencyDataCollection implements IDataCollection{
    List<ICurrency> currencyList;
    public CurrencyDataCollection(){currencyList= new ArrayList<>();}

    @Override
    public String ToString()
    {
        String tmpStr = "";
        for (ICurrency x : currencyList)
        {
            tmpStr += "Code = " + x.getCode() + " | Factor = " + x.getFactor() + " | Rate = " + x.getRate() + "\n";
        }
        return tmpStr;
    }
    @Override
    public List<ICurrency> getCurrencyList()
    {
        return currencyList;
    }
    @Override
    public ICurrency getCurrencyByCode(ICurrency currency)
    {
        for(ICurrency el : currencyList){
            if(currency.equals(el))
                return el;
        }
        return null;
    }
}
