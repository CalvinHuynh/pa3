package pa3_encoder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
        }
        return "";
    }

    /**
     * Converts the string to the decimal representation
     * @param content string to convert
     * @return List of Integers with an int representation of the string value
     */
    public static List<Integer> stringToDecimal(String content){
        char[] characterArray = content.toCharArray();
        List<Integer> list= new ArrayList<>();
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
     * Creates a string from a list of items
     * @param encodedList List of items
     * @param <T> Type
     * @return String of all the items separated with a ","
     */
    public static <T>String printEncodedMessage(List<T> encodedList){
        StringBuilder sb = new StringBuilder();
        for (Iterator<T> it = encodedList.iterator(); it.hasNext();) {
            T encoded = it.next();
            sb.append(encoded);
            if(it.hasNext()){
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
