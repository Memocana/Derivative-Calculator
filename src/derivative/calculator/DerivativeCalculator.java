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
        return 0;
    }
    
    public static String derive(String str){
        System.out.println(str);
        if (str.indexOf("(") == -1) {
            
            if (str.indexOf("*") != -1) {
                if (str.indexOf("+")>-1) { 
                    if (str.indexOf("*") < str.indexOf("+"))
                        return derive(str.substring(0,str.indexOf("+")))+" + " +derive(str.substring(str.indexOf("+")+1));
                    else if (str.substring(str.indexOf("*")).indexOf("+") == -1)  {
                        System.out.print("here");
                        int pos = findSign("*",'+',str);
                        return derive(str.substring(0,pos))+" + "+derive(str.substring(pos+1));
                    
                    } else {
                        int pos = findSign("*",'+',str);
                        return derive(str.substring(0,pos))+derive(str.substring(pos+1, str.substring(str.indexOf("*")).indexOf("+"))) + " + " +derive(str.substring(str.substring(str.indexOf("*")).indexOf("+")));
                    }
                } else if (str.indexOf("-")>-1)
                    return (Integer.parseInt(str.substring(0,str.indexOf("x")))*Integer.parseInt((str.substring(str.indexOf("^")+1,str.indexOf("-")))) + "x^(" + (Integer.parseInt(str.substring(str.indexOf("^")+1,str.indexOf("-")))-1))+")" +" - " +derive(str.substring(str.indexOf("-")+1));
                else    
                    return derive(str.substring(0,str.indexOf("*")))+"*"+str.substring(str.indexOf("*")+1) +" + " +str.substring(0,str.indexOf("*")) +"*"+derive(str.substring(str.indexOf("*")+1));
                    
            } else if (str.indexOf("/") != -1) {
                if (str.indexOf("+")>-1) 
                    return (Integer.parseInt(str.substring(0,str.indexOf("x")))*Integer.parseInt((str.substring(str.indexOf("^")+1,str.indexOf("+")))) + "x^(" + (Integer.parseInt(str.substring(str.indexOf("^")+1,str.indexOf("+")))-1))+")" +" + " +derive(str.substring(str.indexOf("+")+1));
                else if (str.indexOf("-")>-1)
                    return (Integer.parseInt(str.substring(0,str.indexOf("x")))*Integer.parseInt((str.substring(str.indexOf("^")+1,str.indexOf("-")))) + "x^(" + (Integer.parseInt(str.substring(str.indexOf("^")+1,str.indexOf("-")))-1))+")" +" - " +derive(str.substring(str.indexOf("-")+1));
                else    
                    return "("+(derive(str.substring(0,str.indexOf("/")))+"*"+str.substring(str.indexOf("/")+1) +" + " +str.substring(0,str.indexOf("/")) +"*"+derive(str.substring(str.indexOf("/")+1))) +") / 2("+str.substring(str.indexOf("/")+1)+")";
                    
            } else if (str.indexOf("^") != -1) {
                if (str.indexOf("^") - str.indexOf("x") == 1) {
                    //must add support for constants such as π
                    if (str.indexOf("+")>-1) 
                        return (Integer.parseInt(str.substring(0,str.indexOf("x")))*Integer.parseInt((str.substring(str.indexOf("^")+1,str.indexOf("+")))) + "x^(" + (Integer.parseInt(str.substring(str.indexOf("^")+1,str.indexOf("+")))-1))+")" +" + " +derive(str.substring(str.indexOf("+")+1));
                    else if (str.indexOf("-")>-1)
                        return (Integer.parseInt(str.substring(0,str.indexOf("x")))*Integer.parseInt((str.substring(str.indexOf("^")+1,str.indexOf("-")))) + "x^(" + (Integer.parseInt(str.substring(str.indexOf("^")+1,str.indexOf("-")))-1))+")" +" - " +derive(str.substring(str.indexOf("-")+1));
                    else
                        return (Integer.parseInt(str.substring(0,str.indexOf("x")))*Integer.parseInt((str.substring(str.indexOf("^")+1))) + "x^(" + (Integer.parseInt(str.substring(str.indexOf("^")+1))-1))+")";
                }
            }
            
        }
        return str;
    }
}
