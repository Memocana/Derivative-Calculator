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
            if(str.charAt(i) != ' ')
                spaceless += str.charAt(i);
        }
        System.out.println(derive(spaceless));
        
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
        if (str.indexOf("(") == -1) { //anti parantez
            if (str.indexOf("+") != -1 ){
                if (str.indexOf("-") == -1 || str.indexOf("-") > str.indexOf("+"))
                    return derive(str.substring(0,str.indexOf("+")))+" + " +derive(str.substring(str.indexOf("+")+1));
            }
            
            if (str.indexOf("-") != -1 ){
                if (str.indexOf("+") == -1 || str.indexOf("-") < str.indexOf("+"))
                    return derive(str.substring(0,str.indexOf("-")))+" - " +derive(str.substring(str.indexOf("-")+1));
            }
            
            if (str.indexOf("*") != -1) { //product
                return derive(str.substring(0,str.indexOf("*")))+"*"+str.substring(str.indexOf("*")+1) +" + " +str.substring(0,str.indexOf("*")) +"*"+derive(str.substring(str.indexOf("*")+1));
            } else if (str.indexOf("/") != -1) { //quotient
                return "("+derive(str.substring(0,str.indexOf("/")))+"*"+str.substring(str.indexOf("/")+1) +" - " +str.substring(0,str.indexOf("/")) +"*"+derive(str.substring(str.indexOf("/")+1))+") / (" +str.substring(str.indexOf("/")+1)+")^2" ;
            } else if (str.indexOf("^") != -1) {
                if (str.indexOf("^") - str.indexOf("x") == 1) {
                    //n X ^(n-1)
                    return (Integer.parseInt(str.substring(0,str.indexOf("x")))*Integer.parseInt((str.substring(str.indexOf("^")+1))) + "x^(" + (Integer.parseInt(str.substring(str.indexOf("^")+1))-1))+")";
                }
            }
        }
        return str;
    }
}
