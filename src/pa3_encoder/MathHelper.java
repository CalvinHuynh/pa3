package pa3_encoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Calvin
 */
public class MathHelper {

    /**
     * Creates a list of prime numbers using the Sieve of Eratosthenes algorithm
     * implementation from https://www.geeksforgeeks.org/sieve-of-eratosthenes/
     * @param n look for primes until n
     * @return list of primes
     */
    public static List<Long> sieveOfEratosthenes(long n) {
        boolean prime[] = new boolean[(int) n + 1];
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }
        List<Long> primeNumbers = new ArrayList<>();
        for (long i = 2; i <= n; i++) {
            if (prime[(int) i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    /**
     * Calculate the prime factors of the given prime
     * @param prime prime number that you want the factors of
     * @return the two factors that makes the prime
     */
    public static List<Long> calculatePrimeFactors(long prime) {
        List<Long> factors = new ArrayList<>();
        for (long i = 2; i <= prime / i; i++) {
            while (prime % i == 0) {
                factors.add(i);
                prime /= i;
            }
        }
        if (prime > 1) {
            factors.add(prime);
        }
        return factors;
    }

    /**
     * Find the divisors of the given prime
     * @param number number that you want the divisors of
     * @return list of divisors
     */
    public static List<Long> findListOfDivisors(long number) {
        List<Long> divisorsList = new ArrayList<>();

        long index;
        for (index = 2; index <= number / 2; index++) {
            if (number % index == 0) {
                divisorsList.add(index);
            }
        }
        return divisorsList;
    }

    /**
     * Finds the common relative prime of the given lists.
     * @param firstList all divisors from p
     * @param secondList all divisors from q
     * @return List of common relative primes
     */
    public static List<Long> findCommonRelativePrimes(List<Long> firstList, List<Long> secondList) {
        long smallestValue = findLowestValueFromLists(firstList, secondList);
        // Create the prime numbers using sieveOfEratosthenes
        List<Long> listOfPrimes = sieveOfEratosthenes(smallestValue);
        // Add the available primes to the list
        List<Long> availableRelativePrimes = new ArrayList<>();
        for (int i = 0; i < listOfPrimes.size(); i++) {
            availableRelativePrimes.add(listOfPrimes.get(i));
        }
        // Remove the divisors from the hashmap.
        HashMap<Long, Long> map = Utility.toHashMap(availableRelativePrimes);
        map = Utility.removeItemsFromHashMap(map, firstList);
        map = Utility.removeItemsFromHashMap(map, secondList);
        // Converts hashmap to ArrayList.
        List<Long> commonRelativePrimes = new ArrayList<>(map.values());
        return commonRelativePrimes;
    }

    /**
     * Finds the smallest value from an ordered list, sorted from low to high
     * @param firstList
     * @param secondList
     * @return smallest value
     */
    public static Long findLowestValueFromLists(List<Long> firstList, List<Long> secondList){
        long lastValueOfFirst = firstList.get(firstList.size() - 1);
        long lastValueOfSecond = secondList.get(secondList.size() - 1);
        long smallestValue;
        if (lastValueOfFirst > lastValueOfSecond) {
            smallestValue = lastValueOfSecond;
        } else {
            smallestValue = lastValueOfFirst;
        }
        return smallestValue;
    }
}
