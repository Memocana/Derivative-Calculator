/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package derivative.calculator;
import java.util.Scanner;
/**
 *
 * @author Memo
 */
public class DerivativeCalculator {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String str=console.nextLine();
        String spaceless = "";
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == 'x') {
                if (i == 0 || !isNumeric(str.charAt(i-1)+"")){
                    spaceless += "1";
                }
            }
            if(str.charAt(i) != ' ')
                spaceless += str.charAt(i);
            if(str.charAt(i) == 'x')
                if (i == str.length() - 1 || str.charAt(i+1) != '^')
                    spaceless += "^1";
        }
        String unFormatted = derive(spaceless);
        System.out.println(unFormatted);
        unFormatted = format(unFormatted);
        System.out.println(unFormatted);
    }  
    public static boolean isNumeric(String str)  {  
        try  {  
            int d = Integer.parseInt(str);  
        }  
        catch(NumberFormatException nfe) {  
            return false;  
        }  
        return true;  
    }
    public static int findSign(String start, char end, String str) {
        for (int i = str.indexOf(start); i >= str.indexOf(end); i--) {
            if (str.charAt(i) == end)
                return i;
        }
        return str.indexOf(end);
    }
    public static String derive(String str){
        System.out.println(str);
        if (str.indexOf("x") == -1)
            return "0";
        if (str.indexOf("+") != -1 && (str.indexOf("(")== -1 || (str.indexOf("(") > str.indexOf("+") || findMatch(str,str.indexOf("(")) < str.indexOf("+"))) )
            if (str.indexOf("-") <= 0 || str.indexOf("-") > str.indexOf("+"))
                return derive(str.substring(0,str.indexOf("+")))+" + " +derive(str.substring(str.indexOf("+")+1));
        if (str.indexOf("-") != -1 && !(str.indexOf("-") == 0) &&(str.indexOf("(")== -1 || (str.indexOf("(") > str.indexOf("-") || findMatch(str,str.indexOf("(")) < str.indexOf("-"))))
            return derive(str.substring(0,str.indexOf("-")))+" - " +derive(str.substring(str.indexOf("-")+1));
        if (str.indexOf("*") != -1 && (str.indexOf("(")== -1 || (str.indexOf("(") > str.indexOf("*") || findMatch(str,str.indexOf("(")) < str.indexOf("*")))) //product
            return derive(str.substring(0,str.indexOf("*")))+"*"+str.substring(str.indexOf("*")+1) +" + " +str.substring(0,str.indexOf("*")) +"*"+derive(str.substring(str.indexOf("*")+1));
        else if (str.indexOf("/") != -1 && (str.indexOf("(")== -1 || (str.indexOf("(") > str.indexOf("/") || findMatch(str,str.indexOf("(")) < str.indexOf("/")))) //quotient
            return "("+derive(str.substring(0,str.indexOf("/")))+"*"+str.substring(str.indexOf("/")+1) +" - " +str.substring(0,str.indexOf("/")) +"*"+derive(str.substring(str.indexOf("/")+1))+") / (" +str.substring(str.indexOf("/")+1)+")^2" ;
        else if (str.indexOf("sin(") != -1 && (str.indexOf("(")== -1 || str.indexOf("(") > str.indexOf("sin("))) //sin
            return "cos("+(str.substring(str.indexOf("(")+1,findMatch(str,str.indexOf("(")))) +") * (" +derive(str.substring(str.indexOf("(")+1,findMatch(str,str.indexOf("("))))+")";
        else if (str.indexOf("cos(") != -1 && (str.indexOf("(")== -1 || str.indexOf("(") > str.indexOf("cos("))) //cos
            return "-sin("+(str.substring(str.indexOf("(")+1,findMatch(str,str.indexOf("(")))) +") * (" +derive(str.substring(str.indexOf("(")+1,findMatch(str,str.indexOf("("))))+")";
        else if (str.indexOf("tan(") != -1 && (str.indexOf("(")== -1 || str.indexOf("(") > str.indexOf("tan("))) //tan
            return "sec^2("+(str.substring(str.indexOf("(")+1,findMatch(str,str.indexOf("(")))) +") * (" +derive(str.substring(str.indexOf("(")+1,findMatch(str,str.indexOf("("))))+")";
        else if (str.indexOf("cot(") != -1 && (str.indexOf("(")== -1 || str.indexOf("(") > str.indexOf("cot("))) //cot
            return "-csc^2("+(str.substring(str.indexOf("(")+1,findMatch(str,str.indexOf("(")))) +") * (" +derive(str.substring(str.indexOf("(")+1,findMatch(str,str.indexOf("("))))+")";
        else if (str.indexOf("e^(") != -1 && (str.indexOf("(")== -1 || str.indexOf("(") > str.indexOf("e")))
            return str.substring(0,findMatch(str,str.indexOf("("))+1)+" * ("+derive(str.substring(str.indexOf("(")+1,findMatch(str, str.indexOf("("))))+")";
        else if (str.indexOf("ln(") != -1 && (str.indexOf("(")== -1 || str.indexOf("(") > str.indexOf("ln("))) /* ln x */
            return "1 / ("+str.substring(str.indexOf("ln(") + 3,findMatch(str,str.indexOf("(")))+") * ("+derive(str.substring(str.indexOf("(")+1,findMatch(str,str.indexOf("("))))+")";
        else if (str.indexOf("sqrt(") != -1 && (str.indexOf("(")== -1 || str.indexOf("(") > str.indexOf("sqrt("))) /* ln x */
            return "("+str.substring(str.indexOf("(") + 1,findMatch(str,str.indexOf("(")))+")^(-1/2) * ("+derive(str.substring(str.indexOf("(")+1,findMatch(str,str.indexOf("(")))) +")";
        else if(str.indexOf("(") != -1)
            return derive(str.substring(str.indexOf("(")+1,findMatch(str,str.indexOf("("))));
        else if (str.indexOf("^") != -1 && str.indexOf("^") - str.indexOf("x") == 1) //n X ^(n-1)
            return (Integer.parseInt(str.substring(0,str.indexOf("x")))*Integer.parseInt((str.substring(str.indexOf("^")+1))) + "x^(" + (Integer.parseInt(str.substring(str.indexOf("^")+1))-1))+")";
        return str;
    }
    public static String format(String str) {
        String formatted = "";
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '1') {
                if (str.charAt(i+1) == 'x' && (i - 1 < 0 || (i - 1 >= 0 && !isNumeric(str.substring(i-1,i))))) {
                    i++;
                }
            }
            if (str.charAt(i) == '^' && (i+2 < str.length() && str.charAt(i+1) == '1' && !isNumeric(str.substring(i+2,i+3)))) {
                i+=2;
            }
            if (str.charAt(i) == '^' && (i+2 < str.length() && str.charAt(i+2) == '1' && str.charAt(i+3) == ')')) {
                i+=4;
            }
            if (str.charAt(i) == 'x' && (i+3 < str.length() && str.charAt(i+1) == '^' && str.charAt(i+3) == '0')) {
                if (!((i - 1 < 0 || (i - 1 >= 0 && isNumeric(str.substring(i-1,i)) && str.charAt(i-1) != '1'))))
                    formatted += "1";
                i+=5;
                
            }
            if(i < str.length()) {
                formatted += str.charAt(i);
            }
        }
        return formatted;
    }
    public static int findMatch(String str, int pos) {
        int open = 1;
        for (int i = pos + 1; i < str.length(); i++) {
            if (str.charAt(i) == '(')
                open++;
            if (str.charAt(i) == ')')
                open--;
            if (open == 0)
                return i;
        }
        return pos + 2;
    }
}