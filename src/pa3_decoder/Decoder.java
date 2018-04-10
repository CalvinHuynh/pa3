package pa3_decoder;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Calvin
 */
public class Decoder {

    public static void main(String[] args) throws Exception {
        //TODO: accept prime (n), optional e (e) and filepath (c) from jar parameters.
        String filePath = "C:\\Users\\VM\\Desktop\\pa3_message_encoded_from_paper.txt";
        int n = 23449;
        int e = 3;

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
