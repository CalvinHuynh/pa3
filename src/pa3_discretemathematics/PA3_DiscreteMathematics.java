/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa3_discretemathematics;

import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author Calvin
 */
public class PA3_DiscreteMathematics {

    public static void main(String[] args) throws Exception {
        Stopwatch stopwatch = new Stopwatch();
        
        String filePath = "C:\\Users\\Calvin\\Desktop\\simple_text.txt";
        int prime = 23449;
        int e;
        
        System.out.println("Primefactors of " + prime);
        List<Long> pf = MathHelper.calculatePrimeFactors(prime);
        System.out.println("Prime factors are:");
        for (Long number : pf) {
            System.out.println(number);
        }
        System.out.println("result of the " + pf.get(0) + "*" + pf.get(1) +
                " is: " + pf.get(0) * pf.get(1));
        System.out.println("Elapsed time: " + stopwatch.elapsedTime() + "ms");
        
        List<Long> pfMinusX = MathHelper.calculatePrimeFactorsMinusX(pf, 1);
        List<Long> devisorsOfP = MathHelper.findListOfDivisors(pfMinusX.get(0));
        System.out.print("Divisors of " + pfMinusX.get(0) + " = 1");
        for (Long long1 : devisorsOfP) {
             System.out.print(", " + long1);
        }
        System.out.println("");
        List<Long> devisorsOfQ = MathHelper.findListOfDivisors(pfMinusX.get(1));
        System.out.print("Divisors of " + pfMinusX.get(1) + " = 1");
        for (Long long1 : devisorsOfQ) {
             System.out.print( ", " + long1);
        }
        
        System.out.println("");
        
        List<Long> temp = MathHelper.findCommonRelativePrimes(devisorsOfP, devisorsOfQ);
        System.out.println("Lowest e is: " + temp.get(0));
        
        String content = Utility.readFile(filePath);
        List<Integer> convertedContent = Utility.stringToHex(content);
        List<BigInteger> encodedContent = Utility.encodeContent(convertedContent, temp.get(0).intValue(), prime);
        for (BigInteger encoded : encodedContent) {
            System.out.print(encoded + ",");
        }
        
    }

}
