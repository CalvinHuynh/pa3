/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa3_encode;

import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author Calvin
 */
public class PA3_DiscreteMathematics {

    public static void main(String[] args) throws Exception {
        Stopwatch stopwatch = new Stopwatch();
        
        String filePath = "C:\\Users\\Calvin\\Desktop\\pa3_message_to_encode.txt";
        int prime = 20291;
        
        System.out.println("Primefactors of " + prime);
        List<Long> pf = MathHelper.calculatePrimeFactors(prime);
        System.out.println("Prime factors are:");
        System.out.println("p is " + pf.get(0));
        System.out.println("q is " + pf.get(1));
        
        System.out.println("n is the result of " + pf.get(0) + " * " + pf.get(1) +
                " = " + pf.get(0) * pf.get(1));
        System.out.println("Elapsed time: " + stopwatch.elapsedTime() + "ms");
        
        Long pMinusX = MathHelper.calculatePrimeFactorsMinusX(pf.get(0), 1);
        List<Long> devisorsOfP = MathHelper.findListOfDivisors(pMinusX);
        System.out.print("Divisors of " + pMinusX + " = 1");
        for (Long devisorOfP : devisorsOfP) {
             System.out.print(", " + devisorOfP);
        }
        
        System.out.println("");
        Long qMinusX = MathHelper.calculatePrimeFactorsMinusX(pf.get(1), 1);
        List<Long> devisorsOfQ = MathHelper.findListOfDivisors(qMinusX);
        System.out.print("Divisors of " + qMinusX + " = 1");
        for (Long devisorOfQ : devisorsOfQ) {
             System.out.print( ", " + devisorOfQ);
        }
        
        System.out.println("");
        
        List<Long> commonRelativePrimes = MathHelper.findCommonRelativePrimes(devisorsOfP, devisorsOfQ);
        System.out.println("Lowest found e is: " + commonRelativePrimes.get(0));
        
        String content = Utility.readFile(filePath);
        List<Integer> convertedContent = Utility.stringToDecimal(content);
        List<BigInteger> encodedContent = Utility.encodeContent(convertedContent, 
                commonRelativePrimes.get(0).intValue(), prime);
        for (BigInteger encoded : encodedContent) {
            System.out.print(encoded + ",");
        }
        System.out.println("Elapsed time: " + stopwatch.elapsedTime() + "ms");
        
    }

}
