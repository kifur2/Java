package input;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Input {
    private final Map <String, Integer> priorities = new HashMap<>();

    public Input(){
        priorities.put("+", 1);
        priorities.put("-", 1);
        priorities.put("*", 2);
        priorities.put("/", 2);
        priorities.put("(", 0);
    }

    public Double inputting(String whatToCalculate){
        return calculate(inputToONP(toStringWithSpaces(whatToCalculate)));
    }

    public String toStringWithSpaces(String whatToCalculate){
        String whatToCalculateWithSpaces = "";
        for(int i = 0; i<whatToCalculate.length(); i++){
            int j = i;
            if(whatToCalculate.charAt(j)=='-') {
                j++;
            }
            while(whatToCalculate.charAt(j)=='.' || (whatToCalculate.charAt(j) >= 48 && whatToCalculate.charAt(j) <= 57 )){
                //char symbol = whatToCalculate.charAt(j);
                j++;
            }
            if(i!=j)
                whatToCalculateWithSpaces += whatToCalculate.substring(i,j) + " ";
            whatToCalculateWithSpaces += whatToCalculate.charAt(j) + " ";
            i = j;
        }
        return whatToCalculateWithSpaces;
    }

    public String inputToONP(String whatToCalculate){
        final Stack <String> operandsStack= new Stack<>();
        String result = "";
        for ( String i : whatToCalculate.split(" ")) {

            if( i.length() == 1 && !(i.charAt(0)=='.' || ( i.charAt(0)>= 48 && i.charAt(0) <= 57))){
                if (!operandsStack.empty()) {
                    switch(i){
                        case "=":
                            while (!operandsStack.empty()) {
                                result += operandsStack.pop()+ " ";
                            }
                            break;
                        case ")":
                            while (!operandsStack.empty() && !operandsStack.lastElement().equals("(")) {
                                result += operandsStack.pop()+ " ";
                            }
                            operandsStack.pop();
                            break;
                        case "(":
                            operandsStack.push(i);
                            break;
                        default:
                            if(priorities.get(i) > priorities.get(operandsStack.lastElement()))
                                operandsStack.push(i);
                            else {
                                result += operandsStack.pop()+ " ";
                                operandsStack.push(i);
                            }
                    }
                } else {
                    operandsStack.push(i);
                }
            }
            else {
                result += i + " ";
            }

        }
        return result.substring(0, result.length()-1);

    }

    public static Double calculate(String resultFromONP){
        Stack<Double> values = new Stack<>(); //stos

        for ( String i : resultFromONP.split(" ")) {

            if( i.length() == 1 && !(i.charAt(0)=='.' || ( i.charAt(0)>= 48 && i.charAt(0) <= 57))){
                double b = values.pop();
                double a = values.pop();

                switch (i) {
                    case "+" -> values.push(a + b);
                    case "-" -> values.push(a - b);
                    case "*" -> values.push(a * b);
                    case "/" -> {
                        if(b!=0)
                            values.push(a / b);
                        else
                            throw new IllegalArgumentException();
                    }
                }
            }
            else {
                values.push(Double.parseDouble(i));
            }
        }
        return values.pop();
    }




}
