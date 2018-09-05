package pa3_decoder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Calvin
 */
public class Utility {

      /**
     * Read content from filename
     * @param fileName name of the file including the absolute path
     * @return string content
     * @throws IOException
     */
    public static String readFile(String fileName) throws IOException {
        try {
            String content;
            FileReader fileReader
                    = new FileReader(fileName);

            BufferedReader bufferedReader
                    = new BufferedReader(fileReader);

            content = bufferedReader.readLine();

            bufferedReader.close();
            return content;
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '"
                            + fileName + "'");
            throw new FileNotFoundException(ex.getMessage());
        }
    }

    /**
     * Converts the string to the decimal representation
     * @param content string to convert
     * @return List of decimal representation
     */
    public static List<Integer> stringToDecimal(String content) {
        char[] characterArray = content.toCharArray();
        List<Integer> list = new ArrayList<>();
        for (char c : characterArray) {
            list.add((int) c);
        }
        return list;
    }

    /**
     * Creates a hashMap
     * @param <T> type
     * @param listToMap list to convert
     * @return hashMap
     */
    public static <T> HashMap toHashMap(List<T> listToMap) {
        HashMap<T, T> hashMap = new HashMap<>();
        for (T t : listToMap) {
            hashMap.put(t, t);
        }
        return hashMap;
    }

    /**
     * Remove items from hashMap
     * @param <T> Type
     * @param hashMap
     * @param listToRemove list of items to remove
     * @return hashMap with items from the list removed.
     */
    public static <T> HashMap removeItemsFromHashMap(HashMap<T, T> hashMap, List<T> listToRemove) {
        for (T item : listToRemove) {
            hashMap.remove(item);
        }
        return hashMap;
    }

    /**
     * Encodes the decimals
     * @param integers list of integers to encode
     * @param exponent calculated e
     * @param number prime number n
     * @return List of encoded long values
     */
    public static List<Long> encodeContent(List<Integer> integers, int exponent, int number){
        List<BigInteger> intermediateContents = new ArrayList<>();
        BigInteger e = new BigInteger(Integer.toString(exponent));
        BigInteger n = new BigInteger(Integer.toString(number));

        for (Integer decimalItem : integers) {
            BigInteger b1 = new BigInteger(Integer.toString(decimalItem));
            intermediateContents.add(b1.modPow(e, n));
        }

        List<Long> encodedContents = new ArrayList<>();
        for (BigInteger encodedItem : intermediateContents){
            encodedContents.add(encodedItem.longValue());
        }
        return encodedContents;
    }

    /**
     * Decodes the content of the list of integers
     * @param integers list of integers to decode
     * @param d decryption exponent
     * @param number prime number
     * @return returns the decoded characters
     */
    public static List<Character> decodeContent(List<Integer> integers, long d, int number) {
        List<Character> decodedContent = new ArrayList<>();
        for (Integer encodedInt : integers) {
            char decoded = decodeInt(encodedInt.intValue(), d, number);
            decodedContent.add(decoded);
        }
        return decodedContent;
    }

    /**
     * Decodes the integer to a character
     * @param encrypedInput the encrypted numeric input
     * @param d decryption exponent
     * @param n prime number
     * @return the decoded character
     */
    public static char decodeInt(int encrypedInput, long d, int n) {
        return (char) Integer.parseInt(new BigInteger(Integer.toString(encrypedInput))
                .modPow(new BigInteger(Integer.toString(Long.valueOf(d).intValue())),
                new BigInteger(Integer.toString(n))).toString());
    }
}
