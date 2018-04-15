package pa3_encoder;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Calvin
 */
public class Encoder {

    public static void main(String[] args) throws Exception {
        //TODO: accept prime (n), optional e (e) and filepath (m) from jar parameters.
        String filePath = "C:\\Users\\Calvin\\Desktop\\pa3_message_to_encode.txt";
        int n = 20291;
        int e = 0;
          if(args.length >= 3){
            n = Integer.parseInt(args[0]);
            e = Integer.parseInt(args[1]);
            filePath = args[2];
        }

        // Calculate prime factors from given prime
        List<Long> pf = MathHelper.calculatePrimeFactors(n);
        long p = pf.get(0);
        long q = pf.get(1);
        System.out.println("Output of p is: " + p);
        System.out.println("Output of q is: " + q);

        // Get all the divisors of p - 1
        List<Long> divisorsOfP = MathHelper.findListOfDivisors(p - 1);

        // Get all the divisors of q - 1
        List<Long> divisorsOfQ = MathHelper.findListOfDivisors(q - 1);

        // Find all the possible E values
        List<Long> possibleExponentValues = MathHelper.findCommonRelativePrimes(divisorsOfP, divisorsOfQ);

        // Use lowest E value for a lower execution time.
        System.out.println("Output of e is: " + possibleExponentValues.get(0));

        // Read the content to encode
        String contentToEncode = Utility.readFile(filePath);

        // Start the stopwatch to measure encoding time.
        Stopwatch stopwatch = new Stopwatch();

        // Converts to each letter to a decimal
        List<Integer> convertedContent = Utility.stringToDecimal(contentToEncode);

        // Calculate encoded value
        List<Long> encodedContent = Utility.encodeContent(convertedContent,
                possibleExponentValues.get(0).intValue(), n);
        System.out.println("Output: Message after encryption is: ");
        System.out.println(Utility.printEncodedMessage(encodedContent));

        // Display duration of time busy encoding message
        long elapsedTime = stopwatch.elapsedTime();
        System.out.println("Output: Amount of time busy encoding was:\n\t" +
                TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS) + " s\n\t"
        + TimeUnit.MILLISECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS) + " ms\n\t"
        + TimeUnit.MICROSECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS) + " μs\n\t"
        + elapsedTime + " ns");
    }
}
