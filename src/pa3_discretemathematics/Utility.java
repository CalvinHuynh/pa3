/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa3_discretemathematics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

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
    
    public static List<Integer> stringToHex(String content){
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
}
