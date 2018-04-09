/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa3_discretemathematics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Calvin
 */
public class MathHelper {
    /**
     * Calculate the prime factors of the given number
     * @param numbers prime number that you want the factors of
     * @return the two factors that makes the prime
     */
    public static List<Long> calculatePrimeFactors(long numbers) {
        long n = numbers;
        List<Long> factors = new ArrayList<>();
        for (long i = 2; i <= n / i; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        if (n > 1) {
            factors.add(n);
        }
        return factors;
    }
    
    /**
     * Subtract x from the primes
     * @param primes  
     * @param substractBy
     * @return
     */
    public static List<Long> calculatePrimeFactorsMinusX(List<Long> primes, int substractBy) {
        List<Long> factorsMinusX = new ArrayList<>();
        for (Long prime : primes) {
            factorsMinusX.add(prime - substractBy);
        }
        return factorsMinusX;
    }
    
    /**
     * Find the divisors of the given prime 
     * @param prime number that you want the divisors of
     * @return list of divisors
     */
    public static List<Long> findListOfDivisors(long prime){
        List<Long> divisorsList = new ArrayList<>();
        
        long index;
        for (index = 2; index <= prime / 2; index++) {
            if (prime % index == 0) {
                divisorsList.add(index);
            }
        }
        return divisorsList;
    }
    
    public static List<Long> findCommonRelativePrimes(List<Long> firstList, List<Long> secondList){
        List<Long> commonRelativePrimes;
        long lastValueOfFirst = firstList.get(firstList.size() - 1);
        long lastValueOfSecond = secondList.get(secondList.size() - 1);
        long smallestValue;
        if(lastValueOfFirst > lastValueOfSecond){
            smallestValue = lastValueOfSecond;
        } else {
            smallestValue = lastValueOfFirst;
        }
        List<Long> availableRelativePrimes = new ArrayList<>();
        for (long i = 2; i < smallestValue; i++) {
            availableRelativePrimes.add(i);
        }
        
        HashMap<Long, Long> map = Utility.toHashMap(availableRelativePrimes);
        map = Utility.removeItemsFromHashMap(map, firstList);
        map = Utility.removeItemsFromHashMap(map, secondList);
        
        commonRelativePrimes = new ArrayList<>(map.values());
        return commonRelativePrimes;
    }
}
