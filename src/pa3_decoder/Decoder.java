/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa3_decoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Calvin
 */
public class Decoder {

    public static void main(String[] args) throws Exception {
        Stopwatch stopwatch = new Stopwatch();

        String filePath = "C:\\Users\\Calvin\\Desktop\\pa3_message_encoded.txt";
        int prime = 23449;
        int e = 3;

        System.out.println("Primefactors of " + prime);
        List<Long> pf = MathHelper.calculatePrimeFactors(prime);
        System.out.println("are:");
        System.out.println("p is " + pf.get(0));
        System.out.println("q is " + pf.get(1));

        String contentToDecode = Utility.readFile(filePath);
        System.out.println(contentToDecode);

        System.out.println("n is the result of " + pf.get(0) + " * " + pf.get(1)
                + " = " + pf.get(0) * pf.get(1));
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
            System.out.print(", " + devisorOfQ);
        }
        
        System.out.println("");
        long phiN = pMinusX * qMinusX;
        long d = 15427;
        System.out.println(phiN);

        List<Long> commonRelativePrimes = MathHelper.findCommonRelativePrimes(devisorsOfP, devisorsOfQ);
        System.out.println("Lowest found e is: " + commonRelativePrimes.get(0));

//        String testString = "test";
//        System.out.println("input is " + testString);
//        char[] charArray = testString.toCharArray();
//        List<Integer> list = new ArrayList<>();
//        for (char c : charArray) {
//            list.add((int) c);
//        }
        
        
        
        String[] items = contentToDecode.split(",");
        List<Integer> encodedItemList = new ArrayList<>();
        
        for (String item : items) {
            encodedItemList.add(Integer.parseInt(item));
        }
        
        List<Character> characterResultList = new ArrayList<>();
        characterResultList = Utility.decodeContent(encodedItemList, e, (int) d);
        for (Character character : characterResultList) {
            System.out.print(character);
        }

    }
}
