package pa3_encoder;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Calvin
 */
public class Main {

    public static void main(String[] args) throws Exception {
        String content = "The starting point of all achievement is desire. ~Napolean Hill";
        int n = 20291;
        Long e = null;
        switch (args.length) {
            case 2:
                n = Integer.parseInt(args[0]);
                content = args[1];
                break;
            case 3:
                n = Integer.parseInt(args[0]);
                e = Long.parseLong(args[1]);
                content = args[2];
                break;
            default:
                System.out.println("Not enough arguments provided");
                break;
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

        // Check whether the given e is a common relative prime
        if (e != null){
            if(!possibleExponentValues.contains(e)){
                e = possibleExponentValues.get(0);
                System.out.println("The given e is not a common relative prime.");
                System.out.println("Assigning a new e value.");
            }
        } else {
            e = possibleExponentValues.get(0);
        }
        
        // Use lowest E value for a lower execution time.
        System.out.println("Output of e is: " + e);

        // Read the content to encode
        String contentToEncode;
        if(content.contains("\\")){
            contentToEncode = Utility.readFile(content);
        } else {
            contentToEncode = content;
        }
        
        // Start the stopwatch to measure encoding time.
        Stopwatch stopwatch = new Stopwatch();

        // Converts to each letter to a decimal
        List<Integer> convertedContent = Utility.stringToDecimal(contentToEncode);

        // Calculate encoded value
        List<Long> encodedContent = Utility.encodeContent(convertedContent,
                e.intValue(), n);
        System.out.println("Output: Message after encryption is: ");
        System.out.println(Utility.printEncodedMessage(encodedContent));

        // Display duration of time busy encoding message
        long elapsedTime = stopwatch.elapsedTime();
        System.out.println("Output: Time taken to encode the input was:\n\t" +
                TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS) + " seconds or\n\t"
        + TimeUnit.MILLISECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS) + " milliseconds or\n\t"
        + TimeUnit.MICROSECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS) + " microseconds or\n\t"
        + elapsedTime + " nanoseconds");
    }
}
