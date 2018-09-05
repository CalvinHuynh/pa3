package pa3_decoder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Calvin
 */
public class Main {

    public static void main(String[] args) throws Exception {
//        String content = "6479,22761,21994,9319,15142,8624,20139,13262,21611,"
//                + "17856,8890,21994,9319,3232,21994,13262,20280,21994,21994,"
//                + "17856,9319,8624,17856,20139,21611,17856,8624,13626,12886,"
//                + "9319,21611,17856,15142,9319,14073,21994,17856,8624,7081,"
//                + "20139,9319,8624,20139,9319,5334,21994,21611,20139,7081,4257"
//                + ",21994,15142,9319,7589,17856,16915,12886,9319,3232,12886,9319,"
//                + "20139,7081,8890,8890,21994,20139,20139,3540,9319,7211,6108,"
//                + "4257,7081,8890,21994,9319,14714,21994,8624,4257,20139,13262,"
//                + "21994,8624,17856";
        String content = ".\\message_to_decode.txt";
        int n = 23449;
        int e = 3;
        if (args.length >= 3) {
            n = Integer.parseInt(args[0]);
            e = Integer.parseInt(args[1]);
            content = args[2];
        } else {
            System.out.println("No/ Not enough arguments provided. "
                    + "\nThe following parameters will be used: "
                    + "\nn = " + n 
                    + "\ne = " + e 
                    + "\nFile/content to read = " + content);
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
        String contentToDecode;
        if (content.contains("\\")) {
            contentToDecode = Utility.readFile(content);
        } else {
            contentToDecode = content;
        }

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
        System.out.println("Output: Time taken to decode the input was:\n\t"
                + TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS) + " seconds or\n\t"
                + TimeUnit.MILLISECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS) + " milliseconds or\n\t"
                + TimeUnit.MICROSECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS) + " microseconds or\n\t"
                + elapsedTime + " nanos");
    }
}
