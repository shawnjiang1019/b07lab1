import java.util.Arrays;
import java.util.Collections;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException; 
import java.io.FileWriter;
import java.io.IOException;

public class Polynomial{
    
    public double[] coefficients;
    public int[] exponents;

    public Polynomial(){
        this.coefficients = new double[0];
        this.exponents = new int[0];
    }

    public Polynomial(double[] c, int[] e){
        this.coefficients = new double[c.length];
        this.exponents = new int[e.length];
        for (int i = 0; i < c.length; i++){
            this.coefficients[i] = c[i];
            this.exponents[i] = e[i];
        }
    } 

    public Polynomial add(Polynomial p2){
        int p1Max = 0;
        for (int i = 0; i < this.exponents.length; i++){
            if (this.exponents[i] > p1Max){
                p1Max = this.exponents[i];
            }
        }

        int p2Max = 0;
        for (int i =0; i < p2.exponents.length; i++){
            if (p2.exponents[i] > p2Max){
                p2Max = p2.exponents[i];
            }
        }

        int num = Math.max(p1Max, p2Max);
        int[] newExponents = new int[num + 1];
        double[] newCoefficients = new double[num + 1];
        for (int i = 0; i < newExponents.length; i++){
            newExponents[i] = i;
        }
        
        int index = 0;
        for (int i = 0; i < this.exponents.length; i++){
            index = this.exponents[i];
            newCoefficients[index] = newCoefficients[index] + this.coefficients[i];
        }
        index = 0;
        for (int i = 0; i < p2.exponents.length; i++){
            index = p2.exponents[i];

            newCoefficients[index] = newCoefficients[index] + p2.coefficients[i];
        }

        
        //Get rid of coefficients with 0
        int nonZero = 0;
        for (int i = 0; i < newCoefficients.length; i++){
            if (newCoefficients[i] != 0){
                nonZero++;
            }
        }


        double[] finalCoefficients = new double[nonZero];
        int[] finalExponents = new int[nonZero];


        int counter = 0;

        for (int i = 0; i < newCoefficients.length; i++){
            if (newCoefficients[i] != 0){
                finalCoefficients[counter] = newCoefficients[i];
                finalExponents[counter] = newExponents[i];
                counter++;
            }
        }

        Polynomial result = new Polynomial(finalCoefficients, finalExponents);

        return result;
    }

    public double evaluate(double val){
        double answer = 0;
        int index = 0;
        for (int i = 0; i < this.exponents.length; i++){
            index = exponents[i];
            answer = answer + Math.pow(val, i) * this.coefficients[index];
        }

        return answer;
    }

    public boolean hasRoot(double val){
        double answer = this.evaluate(val);
        if (answer == 0){
            return true;
        }

        return false;
    }

    public Polynomial multiply(Polynomial polynomial){
        double[] newCoefficients = new double[this.exponents.length * polynomial.exponents.length];
        int[] newExponents = new int[this.coefficients.length * polynomial.coefficients.length];
        
        Polynomial answer = new Polynomial(newCoefficients, newExponents);


        double c;
        int e;
        for (int i = 0; i < this.coefficients.length; i++){
            for (int j = 0; j < polynomial.coefficients.length; j++){
                c = this.coefficients[i] * polynomial.coefficients[j];
                e = this.exponents[i] + polynomial.exponents[j];
                newExponents[j] = e;
                newCoefficients[j] = c;
            }
            Polynomial temp = new Polynomial(newCoefficients, newExponents);
            answer = answer.add(temp);
        }
        return answer;
    }

    public Polynomial(File file){
        try{
            Scanner scanner = new Scanner(file);
            String input = scanner.nextLine();

            int operations = 0;

            String[] terms = input.split("[+-]");

            double[] coefficients = new double[terms.length];
            int[] exponents = new int[terms.length];

            
            for (int i = 0; i > terms.length; i++){
                String[] temp = terms[i].split("x");
                if (temp.length == 2){
                    
                    coefficients[i] = Double.parseDouble(temp[0]);
                    exponents[i] = Integer.parseInt(temp[1]);
                }

                if (temp.length == 1){
                    coefficients[i] = Double.parseDouble(temp[0]);
                    exponents[i] = 0;
                }
            }

            this.exponents = exponents;
            this.coefficients = coefficients;


        }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void saveToFile(String fileName){
        String result = "";
        for (int i = 0; i < this.coefficients.length; i++){
            System.out.println(this.coefficients[i]);
            if (this.exponents[i] == 0){
                if (this.coefficients[i] < 0){
                    result = result + String.valueOf(this.coefficients[i]);
                } else{
                    result = result + String.valueOf(this.coefficients[i]) + "+";
                }
            } else {
                if (this.coefficients[i] > 0 && this.exponents[i] != 1){
                    result = result + String.valueOf(this.coefficients[i]) + "x" + String.valueOf(this.exponents[i]) + "+";
                } else if (this.coefficients[i] > 0 && this.exponents[i] == 1){
                    result = result + String.valueOf(this.coefficients[i]) + "x" + "+";
                }
                else {
                    result = result + String.valueOf(this.coefficients[i]) + "x" + String.valueOf(this.exponents[i]);
                }
            }
        }
        if (result.endsWith("+")){
            result = result.substring(0, result.length() - 1);
        }

        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(result);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


