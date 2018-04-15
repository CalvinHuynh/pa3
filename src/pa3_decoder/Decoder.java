package pa3_decoder;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Calvin
 */
public class Decoder {

    public static void main(String[] args) throws Exception {
        int n = 23449;
        int e = 3;
        String filePath = "C:\\Users\\Calvin\\Desktop\\pa3_message_encoded_from_paper.txt";
        if(args.length >= 3){
            n = Integer.parseInt(args[0]);
            e = Integer.parseInt(args[1]);
            filePath = args[2];
        }
        
        System.out.println("Output: n is: " + n);
        System.out.println("Output: e is: " + e);
        List<Long> pf = MathHelper.calculatePrimeFactors(n);

        long p = pf.get(0);
        long q = pf.get(1);
        System.out.println("Output: p is: " + p);
        System.out.println("Output: q is: " + q);

        // Get the decryption exponent
        long d = MathHelper.getDecryptionExponent(p, q, e);
        System.out.println("Output: d is: " + d);

        // Initialize the stopwatch to measure decoding time
        Stopwatch stopwatch = new Stopwatch();
        
        // Read from file
        String contentToDecode = Utility.readFile(filePath);
        
        String[] items = contentToDecode.split(",");
        List<Integer> encodedItemList = new ArrayList<>();

        // Convert string array to integer list
        for (String item : items) {
            encodedItemList.add(Integer.parseInt(item));
        }

        // Decodes the integers to chars
        List<Character> characterResultList = Utility.decodeContent(encodedItemList, d, n);

        System.out.print("Output: Message after decoding is: ");
        for (Character character : characterResultList) {
            System.out.print(character);
        }
        
        System.out.println("");
        long elapsedTime = stopwatch.elapsedTime();
        System.out.println("Output: Amount of time busy encoding was:\n\t" +
                TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS) + " s\n\t"
                + TimeUnit.MILLISECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS) + " ms\n\t"
                + TimeUnit.MICROSECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS) + " μs\n\t"
                + elapsedTime + " ns");
    }
}
