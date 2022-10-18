package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Decode {
    public static void main() {
        try {
            System.out.println("Step 1");
            System.out.println("---------------------------------------------------------------------------------");
            File myObj = new File("outData.txt");
            File myObj2 = new File("text.txt");
            Scanner myReader = new Scanner(myObj);
            Scanner myReader2 = new Scanner(myObj2);
            int m=0;
            int s=0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String data2 = myReader2.nextLine();
                System.out.println("Our code");
                System.out.println(data);
                PriorityQueue<Node> pq=Node.buildHuffmanTree(data2);
                decodehuffmantree(data,pq,data2);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int decode(Node node, int i, StringBuilder stringBuilder)
    {
        if (node == null) {
            return i;
        }

        // Found a leaf node
        if (Node.isLeaf(node))
        {
            System.out.print(node.ch);
            return i;
        }

        i++;

        node = (stringBuilder.charAt(i) == '0') ? node.left : node.right;
        i = decode(node, i, stringBuilder);
        return i;
    }

    private static void decodehuffmantree(String data, PriorityQueue<Node> priorityQueue, String data2) {
        System.out.println("Step 3");
        Node root = priorityQueue.peek();
        //System.out.println("Original text: " + data2);
        //System.out.print("Decoded text:  ");

        if (Node.isLeaf(root))
        {

            while (root.freq-- > 0) {
                System.out.print(root.ch);
            }

        }
        else {

            int index = -1;
            while (index < data.length() - 1) {
                index = decode(root, index, new StringBuilder(data));
            }
        }

    }
}

