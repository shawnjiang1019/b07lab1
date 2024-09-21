import java.lang.Math;


public class Polynomial{
    
    double[] coefficients;

    public Polynomial(){
        this.coefficients = new double[0];
    }

    public Polynomial(double[] arr){
        this.coefficients = new double[arr.length];

        for (int i =0; i < arr.length; i++){
            this.coefficients[i] = arr[i];
        }  
    }   

    
    public Polynomial add(Polynomial p2){
        int len1 =this.coefficients.length;
        
        int len2 =p2.coefficients.length;

        int smallest = 0;
        double [] longArray;
        double [] smallArray;
        if (len1 < len2){
            smallest = len1;
            longArray = p2.coefficients;
            smallArray = this.coefficients;
        } else {
            smallest = len2;
            longArray = this.coefficients;
            smallArray = p2.coefficients;
        }


        for (int i =0; i < smallest; i++){
            longArray[i] = longArray[i] + smallArray[i];
        }
        
        Polynomial newPoly = new Polynomial(longArray);

        return newPoly;
    }

    public double evaluate(double val){
        double answer = 0;

        for (int i = 0; i < this.coefficients.length; i++){
            answer = answer + Math.pow(val, i) * this.coefficients[i];
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
}