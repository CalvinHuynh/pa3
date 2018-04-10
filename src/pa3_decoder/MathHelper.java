package pa3_decoder;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Calvin
 */
public class MathHelper {
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
     * Tries to retrieve the decryption exponent from given p and q
     * @param p prime 1
     * @param q prime 2
     * @param e exponent
     * @return decryption exponent
     */
    public static Long getDecryptionExponent(long p, long q, long e) {
        long phiN = (p - 1) * (q  - 1);
        long decryptionExponent = -1;
        for (long i = 0; i < phiN; i++) {
            long inverseModulo = (e * i) % phiN;
            if (inverseModulo == 1) {
                decryptionExponent = i;
            }
        }
        return decryptionExponent;
    }
}
