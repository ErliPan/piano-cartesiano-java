/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafici;

/**
 *
 * @author Erli
 * 
 */
public class Funzione {
    String testo;
    boolean debug = false;
    
    private void println(String testo) {
        if (debug == true) System.out.println(testo);
    }
    
    public Funzione(String testo) {
        this.testo = testo;
    }
    public double calcFun(double x) {
        
        pulisciTesto();
        String tmp = testo;
        
        println(testo);
        testo = testo.replace("X", "x");
        println(testo);
        
        boolean daRisolvere = true;
        while (daRisolvere == true) {
            if (testo.contains("E") || testo.contains("N"))
                break;
            daRisolvere = solveSinCos(x);
            println(testo);
        }
        daRisolvere = true;
        while (daRisolvere == true) {
            if (testo.contains("E") || testo.contains("N"))
                break;
            daRisolvere = solveParenthesis(x);
            println(testo);
        }
        daRisolvere = true;
        while (daRisolvere == true) {
            if (testo.contains("E") || testo.contains("N"))
                break;
            daRisolvere = solvePowX(x);
            println(testo);
        }
        testo = testo.replace("x", x + "");
        daRisolvere = true;
        while (daRisolvere == true) {
            if (testo.contains("E") || testo.contains("N"))
                break;
            daRisolvere = solvePow();
            println(testo);
        }
        daRisolvere = true;
        while (daRisolvere == true) {
            if (testo.contains("E") || testo.contains("N"))
                break;
            daRisolvere = solveDM();
            println(testo);
        }
        daRisolvere = true;
        while (daRisolvere == true) {
            if (testo.contains("E") || testo.contains("N"))
                break;
            daRisolvere = solveSS();
            println(testo);
        }
        double ret;
        if ((testo.contains("E-") || testo.contains("E") || testo.contains("N")) &&
                (testo.contains("^") || testo.contains("*") || testo.contains("/") || testo.contains("+") || testo.contains("-")))
            ret = Double.MAX_VALUE;
        else 
            ret = Double.parseDouble(testo);
        testo = tmp;
        return ret;
    }
    private double calcFun(int x) {
        return calcFun((double) x);
    }
    private boolean pulisciSegni() {
        boolean ret = (testo.contains("--") || testo.contains("++") || testo.contains("+-") || testo.contains("-+"));
        testo = testo.replace("++", "+");
        testo = testo.replace("--", "+");
        testo = testo.replace("+-", "-");
        testo = testo.replace("-+", "-");
        
        if (testo.contains("+")) //rimuove il primo + che e' inutile
            if(testo.indexOf("+") == 0)
                testo = testo.substring(1);
        
        testo = testo.replace("^+", "^");
        testo = testo.replace("*+", "*");
        testo = testo.replace("/+", "/");
        
        return ret;
    }
    private void pulisciTesto() {
        testo = testo.replace(" ", "");
        testo = testo.replace(",", ".");
        
        testo = testo.replace("COS", "cos");
        testo = testo.replace("SIN", "sin");
        testo = testo.replace("Cos", "cos");
        testo = testo.replace("Sin", "sin");
    }
    //ogniuno solve un tipo di problema
    private boolean solvePow() {
        do{}while(pulisciSegni() == true);
        
        if (testo.contains("^")) {
            int i = 1;
            String n1 = "";
            String n2 = "";
            int pos = testo.indexOf("^");
            
            while (pos - i >= 0 && isNumber(testo.charAt(pos - i))) {
                n1 = testo.charAt(pos - i) + n1;
                if (testo.charAt(pos - i) == '-')
                    break;
                i++;
            }
            
            i = 1;
            if (pos + 1 < testo.length() && testo.charAt(pos + i) == '-') {
                n2 = "-";
                i++;
            }
            
            while (pos + i < testo.length() && isNumberNoMinus(testo.charAt(pos + i))) {
                n2 = n2 + testo.charAt(pos + i);
                i++;
            }
            
            //Potenza di un numero pari e' sempre positivo
            if (Double.parseDouble(n2) % 2 == 0) {
                if (testo.contains("-") && testo.indexOf("-") < testo.indexOf("^")) {
                    int tmp = testo.indexOf(n1 + "^" + n2);
                    char[] t1 = testo.toCharArray();
                    //println(t1[tmp - 1]);
                    if (t1[tmp] == '-') {
                        t1[tmp] = '+';
                        testo = String.valueOf(t1);
                    }
                }
            }
            
            String risultato = "" + Math.pow(Double.parseDouble(n1), Double.parseDouble(n2));
            
            testo = testo.replace(n1 + "^" + n2, risultato);
            return true;
        }
        return false;
    }
    private boolean solveMul() {
        do{}while(pulisciSegni() == true);
        if (testo.contains("*")) {
            int i = 1;
            String n1 = "";
            String n2 = "";
            int pos = testo.indexOf("*");
            
            while (pos - i >= 0 && isNumber(testo.charAt(pos - i))) {
                n1 = testo.charAt(pos - i) + n1;
                if (testo.charAt(pos - i) == '-')
                    break;
                i++;
            }
            
            i = 1;
            if (pos + 1 < testo.length() && testo.charAt(pos + i) == '-') {
                n2 = "-";
                i++;
            }
            
            while (pos + i < testo.length() && isNumberNoMinus(testo.charAt(pos + i))) {
                n2 = n2 + testo.charAt(pos + i);
                i++;
            }
            
            String risultato = "" + (Double.parseDouble(n1) * Double.parseDouble(n2));
            
            testo = testo.replace(n1 + "*" + n2, risultato);
            return true;
        }
        return false;
    }
    private boolean solveDiv() {
        do{}while(pulisciSegni() == true);
        if (testo.contains("/")) {
            int i = 1;
            String n1 = "";
            String n2 = "";
            int pos = testo.indexOf("/");
            
            while (pos - i >= 0 && isNumber(testo.charAt(pos - i))) {
                n1 = testo.charAt(pos - i) + n1;
                if (testo.charAt(pos - i) == '-')
                    break;
                i++;
            }
            
            i = 1;
            if (pos + 1 < testo.length() && testo.charAt(pos + i) == '-') {
                n2 = "-";
                i++;
            }
            
            while (pos + i < testo.length() && isNumberNoMinus(testo.charAt(pos + i))) {
                n2 = n2 + testo.charAt(pos + i);
                i++;
            }
            
            String risultato = "" + (Double.parseDouble(n1) / Double.parseDouble(n2));
            
            testo = testo.replace(n1 + "/" + n2, risultato);
            return true;
        }
        return false;
    }
    private boolean solveSum() {
        do{}while(pulisciSegni() == true);
        if (testo.contains("+")) {
            if(testo.indexOf("+") == 0) 
                testo = testo.substring(1);
            
            //rimuove il primo + che e' inutile
            if(testo.substring(1).contains("+") == false) {
                return solveSot();
            }
            
            int i = 1;
            String n1 = "";
            String n2 = "";
            int pos = testo.indexOf("+");
            
            while (pos - i >= 0 && isNumber(testo.charAt(pos - i))) {
                n1 = testo.charAt(pos - i) + n1;
                if (testo.charAt(pos - i) == '-')
                    break;
                i++;
            }
            
            i = 1;
            
            while (pos + i < testo.length() && isNumberNoMinus(testo.charAt(pos + i))) {
                n2 = n2 + testo.charAt(pos + i);
                i++;
            }
            
            String risultato = "" + (Double.parseDouble(n1) + Double.parseDouble(n2));
            
            testo = testo.replace(n1 + "+" + n2, risultato);
            return true;
        }
        return false;
    }
    private boolean solveSot() {
        do{}while(pulisciSegni() == true);
        if (testo.contains("-")) {
            
            if(testo.indexOf("-") == 0) {
                testo = testo.substring(1);
                
                int pos = testo.indexOf("-") + 1;
                
                if (!(testo.contains("-"))) { //in caso di -5 ad esempio
                    testo = "-" + testo;
                    return solveSum();
                }
                else testo = "-" + testo;
            
                int i = 1;
                String n1 = "";
                String n2 = "";
                
                while (pos - i >= 0 && isNumber(testo.charAt(pos - i))) {
                    n1 = testo.charAt(pos - i) + n1;
                    if (testo.charAt(pos - i) == '-')
                        break;
                    i++;
                }
            
                i = 1;
            
                while (pos + i < testo.length() && isNumberNoMinus(testo.charAt(pos + i))) {
                    n2 = n2 + testo.charAt(pos + i);
                    i++;
                }
            
                String risultato = "" + (Double.parseDouble(n1) - Double.parseDouble(n2));
            
                testo = testo.replace(n1 + "-" + n2, risultato);
                return true;
            }
            
            int i = 1;
            String n1 = "";
            String n2 = "";
            int pos = testo.indexOf("-");
            
            while (pos - i >= 0 && isNumber(testo.charAt(pos - i))) {
                n1 = testo.charAt(pos - i) + n1;
                if (testo.charAt(pos - i) == '-')
                    break;
                i++;
            }
            
            i = 1;
            
            while (pos + i < testo.length() && isNumberNoMinus(testo.charAt(pos + i))) {
                n2 = n2 + testo.charAt(pos + i);
                i++;
            }
            
            String risultato = "" + (Double.parseDouble(n1) - Double.parseDouble(n2));
            
            testo = testo.replace(n1 + "-" + n2, risultato);
            return true;
        }
        return false;
    }
    
    //fare un modo che sia da sinistra verso destra
    private boolean solveDM() {
        if (testo.contains("*") && testo.contains("/")) {
            int firstMul = testo.indexOf("*");
            int firstDiv = testo.indexOf("/");
            
            if (firstMul < firstDiv) 
                return solveMul();
            else 
                return solveDiv();
        }
        if (solveMul() == false)
            return solveDiv();
        else
            return true;
    }
    private boolean solveSS() {
        if(testo.indexOf("+") == 0)
            testo = testo.substring(1);
        if (testo.contains("+") && testo.contains("-")) {
            
            int firstSum = testo.indexOf("+");
            int firstSot = testo.indexOf("-");
            
            if (firstSum < firstSot) 
                return solveSum();
            else 
                return solveSot();
        }
        if (solveSum() == false)
            return solveSot();
        else
            return true;
    }
    private boolean solveSinCos(double x) {
        if (testo.contains("sin") && testo.contains("cos")) {
            int firstSin = testo.indexOf("sin");
            int firstCos = testo.indexOf("cos");
            
            if (firstSin < firstCos) 
                return solveSin(x);
            else 
                return solveCos(x);
        }
        if (solveSin(x) == false)
            return solveCos(x);
        else
            return true;
    }
    
    //risolve le potenze collegati a x
    private boolean solvePowX(double n1) {
        do{}while(pulisciSegni() == true);
        if (testo.contains("x^")) {
            
            convertiAltriX(n1);
            
            do{}while(pulisciSegni() == true);
            
            int i = 1;
            String n2 = "";
            int pos = testo.indexOf("x^");
            pos++;
            
            if (pos + 1 < testo.length() && testo.charAt(pos + i) == '-') {
                n2 = "-";
                i++;
            }
            
            while (pos + i < testo.length() && isNumberNoMinus(testo.charAt(pos + i))) {
                n2 = n2 + testo.charAt(pos + i);
                i++;
            }
            
            String risultato = "" + Math.pow(n1, Double.parseDouble(n2));
            
            testo = testo.replace("x^" + n2, risultato);
            return true;
        }
        return false;
    }
    private void convertiAltriX(double x) {
        
        testo = testo.replace("x^", "EE");
        testo = testo.replace("x", x + "");
        testo = testo.replace("EE", "x^");
    }
    
    //risolve Cos e Sin
    private boolean solveCos(double x) {
        if(testo.contains("cos(")) {
            if (testo.contains(")")) {
                
                String nucleo = testo.substring(testo.indexOf("cos(") + 4, nextCloseParenthesis(testo.indexOf("cos(") + 3));
                
                println(testo.substring(testo.indexOf("cos(") + 4, nextCloseParenthesis(testo.indexOf("cos(") + 3)));
                
                Funzione f = new Funzione(nucleo);
                testo = testo.replace("cos(" + nucleo + ")", Math.cos(f.calcFun(x)) + "");
                
                return true;
            }
            else println("Errore: controllare le parentesi");
        }
        return false;
    }
    private boolean solveSin(double x) {
        if(testo.contains("sin(")) {
            if (testo.contains(")")) {
                
                String nucleo = testo.substring(testo.indexOf("sin(") + 4, nextCloseParenthesis(testo.indexOf("sin(") + 3));
                
                println(testo.substring(testo.indexOf("sin(") + 4, nextCloseParenthesis(testo.indexOf("sin(") + 3)));
                
                Funzione f = new Funzione(nucleo);
                testo = testo.replace("sin(" + nucleo + ")", Math.sin(f.calcFun(x)) + "");
                
                return true;
            }
            else println("Errore: controllare le parentesi");
        }
        return false;
    }
    
    //risolve le parentesi
    private boolean solveParenthesis(double x) {

        if (testo.contains("(")) {
            //Se prima della parentesi c'e' un numero allora aggiunge un "*"
            if ((testo.indexOf("(") != 0) && isNumberNoMinusIncludeX(testo.charAt(testo.indexOf("(") - 1))) {
                testo = testo.replace("(", "*(");
            }
            /* Simile ma chiuse parentesi
            Rallenta un pelino ma e' piu' veloce da implementare */
            if ((nextCloseParenthesis(testo.indexOf("(")) != testo.length() - 1) && 
                    isNumberNoMinusIncludeX(testo.charAt(nextCloseParenthesis(testo.indexOf("(")) + 1))) {
                
                StringBuilder myName = new StringBuilder(testo);
                myName.setCharAt(nextCloseParenthesis(testo.indexOf("(")), 'z');
                testo = myName.toString();
                testo = testo.replace("z", ")*");
            }
            
            testo = testo.replace(")(", ")*(");
            testo = testo.replace("(x)", "x");
            testo = testo.replace("**", "*");
            
            if (testo.contains(")")) {

                String nucleo = testo.substring(testo.indexOf("(") + 1, nextCloseParenthesis(testo.indexOf("(")));

                println(testo.substring(testo.indexOf("(") + 1, nextCloseParenthesis(testo.indexOf("("))));

                Funzione f = new Funzione(nucleo);
                testo = testo.replace("(" + nucleo + ")", f.calcFun(x) + "");

                return true;
            } else {
                println("Errore: controllare le parentesi");
            }
        }
        return false;
    }
    
    //trova il nucleo tra 2 parentesi
    private int nextCloseParenthesis(int start) {
        if (testo.charAt(start) != '(') 
            println("Errore: impossibile trovare la parentesi da chiudere-" + testo.charAt(start));
        
        int contatore = 1;
        
        String tmp = testo.substring(start);
        
        for (int i = 1; i < tmp.length();i++) {
            if (tmp.charAt(i) == '(') {
                contatore++;
            }
            if (tmp.charAt(i) == ')') {
                contatore--;
                if (contatore == 0) {
                    contatore = i + start;
                    break;
                }
            }
        }
        return contatore;
    }
    
    //Controlla se e' un numero 0-9, "." e "-"
    public static boolean isNumber(char car) {
        return (car >= 48 && car <= 57) || car == 46 || car == 45; //0 a 9, "." e "-" 
    }
    public static boolean isNumberNoMinus(char car) {
        return (car >= 48 && car <= 57) || car == 46; //0 a 9, "."
    }
    public static boolean isNumberNoMinusIncludeX(char car) { //Caratteri che potrebbero nascondere un "*"
        return (car >= 48 && car <= 57) || car == 46 || car == 'x' || car == '(' || car == ')';
    }
}
