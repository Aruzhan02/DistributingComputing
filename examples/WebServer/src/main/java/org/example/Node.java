package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Node {
    Character ch;
    Integer freq;
    Node left = null, right = null;

    Node(Character ch, Integer freq)
    {
        this.ch = ch;
        this.freq = freq;
    }

    public Node(Character ch, Integer freq, Node left, Node right)
    {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    public static PriorityQueue<Node> buildHuffmanTree(String text) throws IOException {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : text.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Node> pq;
        pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.freq));
        for (var entry : freq.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }
        while (pq.size() != 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            int sum = left.freq + right.freq;
            pq.add(new Node(null, sum, left, right));
        }
        Node root = pq.peek();
        Map<Character, String> huffmanCode = new HashMap<>();
        encode(root, "", huffmanCode);
        /*System.out.println("Step 3");
        System.out.println("---------------------------------------------------------------------------------");
        for (Character key : huffmanCode.keySet())
            System.out.println(key + ":" + huffmanCode.get(key));*/
        //outputData(huffmanCode);
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append(huffmanCode.get(c));
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("outData.txt"));
        writer.write(String.valueOf(sb));
        writer.close();
        huffmanCode1 = huffmanCode;
        return pq;
    }

    public static Map<Character, String> huffmanCode1;

    // Utility function to check if Huffman Tree contains only a single node
    public static boolean isLeaf(Node root) {
        return root.left == null && root.right == null;
    }

    public static void encode(Node root, String str, Map<Character, String> huffmanCode) {
        if (root == null) {
            return;
        }

        if (isLeaf(root)) {
            huffmanCode.put(root.ch, str.length() > 0 ? str : "1");
        }

        encode(root.left, str + '0', huffmanCode);
        encode(root.right, str + '1', huffmanCode);
    }
}




