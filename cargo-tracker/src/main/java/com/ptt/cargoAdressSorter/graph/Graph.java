package com.ptt.cargoAdressSorter.graph;

import java.util.HashMap;
import java.util.Map;

public class Graph {
    private Map<String, Integer> vertexIndices;
    private int[][] adjacencyMatrix;

    public Graph(int numVertices) {
        vertexIndices = new HashMap<>();
        adjacencyMatrix = new int[numVertices][numVertices];
    }

    public void addVertex(String label) {
        if (!vertexIndices.containsKey(label)) {
            int index = vertexIndices.size();
            vertexIndices.put(label, index);
        }
    }

    public void addEdge(String source, String destination, int distance) {
        int sourceIndex = vertexIndices.get(source);
        int destIndex = vertexIndices.get(destination);
        adjacencyMatrix[sourceIndex][destIndex] = distance;
        adjacencyMatrix[destIndex][sourceIndex] = distance; // assuming symmetric distances
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public String getVertexLabel(int index) {
        for (Map.Entry<String, Integer> entry : vertexIndices.entrySet()) {
            if (entry.getValue() == index) {
                return entry.getKey();
            }
        }
        return null;
    }
    public static void main(String[] args) {
        Graph graph = new Graph(26); // Toplam 26 adres için bir Graph oluşturdum

        String[] addresses = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        for (String address : addresses) {
            graph.addVertex(address);
        }


    }







        }
