//package com.ptt.cargoAdressSorter.algorithm;
//
//import com.ptt.cargoAdressSorter.graph.Graph;
//import com.ptt.cargoAdressSorter.services.dailyDistribution.DailyDistributionFromDistrict;
//
//import java.util.Arrays;
//
//public class NearestNeighbour  {
//
//    public static int[] NN(int[][] distances, int start) {
//        int size = distances.length;
//        boolean[] copy = new boolean[size];
//        int[] shortestPath = new int[size + 1];
//        double bestDistance = Double.MAX_VALUE;
//        int length = 0;
//
//
//
//        // nearest neighbour thingy
//        final int firstStart = start;
//        int town = start;
//        for (int a = 0; a < size; a++) {
//            // reset distance array
//            Arrays.fill(copy, true);
//            int shortest, dist = 0;
//            int[] temp = new int[size + 1];
//            int index = 0;
//            temp[index++] = a + 1;
//
//
//            for (int c = 0; c < size - 1; c++) {
//                shortest = Integer.MAX_VALUE; // reset closest
//
//                for (int i = 0; i < size; i++) {
//                    if (i == start) continue;
//                    if (copy[i] && (distances[start][i] < shortest)) {
//                        town = i;
//                        shortest = distances[start][i];
//                    }
//                }
//
//                copy[start] = false;
//                temp[index++] = town + 1;
//                start = town;
//                dist += shortest;
//            }
//
//            temp[0] = firstStart + 1;
//            temp[81] = firstStart + 1;
//            dist += distances[temp[index - 1] - 1][temp[0] - 1];
//            if (dist < bestDistance) {
//                shortestPath = Arrays.copyOf(temp, temp.length);
//                bestDistance = dist;
//            }
//        }
//        System.out.println("Nearest Neighbour: " + bestDistance);
//        return shortestPath;
//    }
//
//    public static void printPath(int[] path) {
//        int size = path.length;
//        for (int i = 0; i < size - 1; ++i) {
//            System.out.print((path[i] - 1 )+ ", ");
//        }
//        System.out.println(path[size - 1] - 1);
//    }
//
//}