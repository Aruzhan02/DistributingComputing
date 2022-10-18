package org.example;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Encode{
    public static void mainFunction() {
        try {
            System.out.println("Step 1");
            System.out.println("---------------------------------------------------------------------------------");
            File myObj = new File("text.txt");
            Scanner myReader = new Scanner(myObj);
            int m=0;
            int s=0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                data=toLowerCase(data);

                System.out.println(data);
                HashMap<Character, Integer> result = findProbability(data);
                for (Character key : result.keySet()){
                    m=m+result.get(key);
                    s++;
                }
                LinkedHashMap<Character, Integer> reverseSortedMap = new LinkedHashMap<>();
                result.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
                for (Character key : reverseSortedMap.keySet())
                    System.out.println(key + ":" + (float)Math.round(((float)reverseSortedMap.get(key)/m)*1000)/1000);
                Node.buildHuffmanTree(data);
                //Map<Character,String> newMap = transferHashmap(reverseSortedMap);
                //outputData(newMap);
                ratioComparasion(reverseSortedMap);
                //HashMap<Character, Integer> result2 = findCodeword(reverseSortedMap,m);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static String toLowerCase(String data){
        char c = data.charAt(0);
        char[] c_arr = data.toCharArray();
        for(int i=0; i<c_arr.length; i++){
            if ((int) c_arr[i] > 64 && (int) c_arr[i] < 91 ){
                c_arr[i]=(char)(c_arr[i]+32);
            }
        }
        String str = String.valueOf(c_arr);
        return str;
    }
    public static HashMap<Character, Integer> findProbability(String data){
        HashMap<Character, Integer> charCountMap = new HashMap<Character, Integer>();
        char[] strArray =data.toCharArray();
        for (char c : strArray) {
            if (charCountMap.containsKey(c)) {
                charCountMap.put(c, charCountMap.get(c) + 1);
            }
            else {
                charCountMap.put(c, 1);
            }

        }
        return charCountMap;
    }
    public static void outputData(Map<Character, String> send){
        // create new HashMap
        Map<Character, String> map = send;
        // new file object
        File file = new File("outData.txt");

        BufferedWriter bf = null;

        try {

            // create new BufferedWriter for the output file
            bf = new BufferedWriter(new FileWriter(file));

            // iterate map entries
            for (String value : send.values()) {
                // put key and value separated by a colon
                bf.write(value);

            }

            bf.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }  finally {

            try {

                bf.close();
            }
            catch (Exception e) {
            }
        }
    }


    public static Map<Character,String> transferHashmap(HashMap<Character, Integer> send){
        Map<Character, String> hashMap =
                send.entrySet().stream().collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> Integer.toString(entry.getValue()))
                );
        return hashMap;
    }
    public static void ratioComparasion(LinkedHashMap<Character, Integer> reverseSortedMap) throws FileNotFoundException {

        File myObj = new File("text.txt");
        File myObj1 = new File("outData.txt");
        System.out.println("Step 5");
        System.out.println("---------------------------------------------------------------------------------");
        Scanner myReader = new Scanner(myObj);
        Scanner myReader1= new Scanner(myObj1);
        float m=0;
        float s=0;
        int a=0;
        int b=0;
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            float stringLength = data.length();
            System.out.println("Number of bits in the original text:" + stringLength*8);
            m=stringLength*8;}
        while (myReader1.hasNextLine()){
            String data1 = myReader1.nextLine();
            float stringLength1 = data1.length();
            System.out.println("Number of bits in the compressed text:" + stringLength1);
            s=stringLength1;
        }
        for (Character key : reverseSortedMap.keySet()){
            a=a+reverseSortedMap.get(key);
            b++;
        }
        System.out.println("Compression ratio:" + m/s);
        float sum=0;
        for (Integer val : reverseSortedMap.values()){
            for(String var: Node.huffmanCode1.values()) {
                if(reverseSortedMap.keySet().equals(Node.huffmanCode1.keySet())){
                    sum=(float)val/m * var.length();
                }
            }
        }
        System.out.println("Average code length: " + (float)sum);

    }


}
