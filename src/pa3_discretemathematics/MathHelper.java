/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa3_discretemathematics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Calvin
 */
public class MathHelper {

    /**
     * Creates a list of prime numbers using the Sieve of Eratosthenes algorithm
     * 
     * @param n look for primes until n
     * @return list of primes
     */
    public static List<Integer> sieveOfEratosthenes(int n) {
        boolean prime[] = new boolean[n + 1];
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }
        List<Integer> primeNumbers = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (prime[i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    /**
     * Calculate the prime factors of the given number
     *
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
     * 
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
     * 
     * @param prime number that you want the divisors of
     * @return list of divisors
     */
    public static List<Long> findListOfDivisors(long prime) {
        List<Long> divisorsList = new ArrayList<>();

        long index;
        for (index = 2; index <= prime / 2; index++) {
            if (prime % index == 0) {
                divisorsList.add(index);
            }
        }
        return divisorsList;
    }

    /**
     * Finds the common relative prime of the given lists.
     * 
     * @param firstList q
     * @param secondList p
     * @return List of common relative primes
     */
    public static List<Long> findCommonRelativePrimes(List<Long> firstList, List<Long> secondList) {
        List<Long> commonRelativePrimes;
        long lastValueOfFirst = firstList.get(firstList.size() - 1);
        long lastValueOfSecond = secondList.get(secondList.size() - 1);
        long smallestValue;
        if (lastValueOfFirst > lastValueOfSecond) {
            smallestValue = lastValueOfSecond;
        } else {
            smallestValue = lastValueOfFirst;
        }
        
        // Create the prime numbers using sieveOfEratosthenes
        List<Integer> listOfPrimes = sieveOfEratosthenes((int) smallestValue);
        
        // Converts the Integer list to Long list.
        List<Long> availableRelativePrimes = new ArrayList<>();
        for (int i = 0; i < listOfPrimes.size(); i++) {
            availableRelativePrimes.add(listOfPrimes.get(i).longValue());
        }
        
        HashMap<Long, Long> map = Utility.toHashMap(availableRelativePrimes);
        map = Utility.removeItemsFromHashMap(map, firstList);
        map = Utility.removeItemsFromHashMap(map, secondList);

        // Converts hashmap to ArrayList.
        commonRelativePrimes = new ArrayList<>(map.values());
        return commonRelativePrimes;
    }
}
