public class Driver {
    public static void main(String [] args) {
        double[] c1 = {1, 1, 1};
        int[] e1 = {2, 3, 4};

        double[] c2 = {1, 1, 1};
        int[] e2 = {0, 1, 5};
        Polynomial p1 = new Polynomial(c1, e1);
        Polynomial p2 = new Polynomial(c2, e2);
        
        Polynomial p3 = p1.add(p2);
        

        //Testing Multiply
        double[] c3 = {1, 1};
        int[] e3 = {0, 1};

        double[] c4 = {1, 1};
        int[] e4 = {1, 0};
        
        Polynomial p4 = new Polynomial(c3, e3);
        Polynomial p5 = new Polynomial(c4, e4);

        Polynomial mult = p4.multiply(p5);

        System.out.println("printing the exponents: ");
        for (int i = 0; i < mult.exponents.length; i++){
            System.out.println(mult.exponents[i]);
        }
        System.out.println("printing the coefficients: ");
        for (int i = 0; i < mult.coefficients.length; i++){
            System.out.println(mult.coefficients[i]);
        }

        p4.saveToFile("idk");




        
        
    }
}