//package com.ptt.cargoAdressSorter.services.dailyDistribution;
//
//import com.ptt.cargoAdressSorter.algorithm.NearestNeighbour;
//import com.ptt.cargoAdressSorter.services.addressCoordinatesService.AddressCoordinatesService;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class DailyDistributionOptimizer {
//
//    public static List<List<String>> findShortestRoutes(String dailySchedulesFilePath) {
//        List<List<String>> shortestRoutes = new ArrayList<>();
//
//        List<List<String>> dailySchedules = readDailySchedulesFromFile(dailySchedulesFilePath);
//
//        for (List<String> daySchedule : dailySchedules) {
//            int[][] distances = calculateDistancesBetweenAddresses(daySchedule);
//            int[] shortestPath = NearestNeighbour.NN(distances, 0);
//            List<String> shortestRoute = getAddressesFromIndices(shortestPath, daySchedule);
//            shortestRoutes.add(shortestRoute);
//        }
//
//        return shortestRoutes;
//    }
//
//    private static List<List<String>> readDailySchedulesFromFile(String dailySchedulesFilePath) {
//        List<List<String>> dailySchedules = new ArrayList<>();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(dailySchedulesFilePath))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] addresses = line.split(",");
//                List<String> daySchedule = new ArrayList<>();
//                for (String address : addresses) {
//                    daySchedule.add(address.trim());
//                }
//
//                dailySchedules.add(daySchedule);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return dailySchedules;
//    }
//
//    private static int[][] calculateDistancesBetweenAddresses(List<String> addresses) {
//        int numAddresses = addresses.size();
//        int[][] distances = new int[numAddresses][numAddresses];
//
//        for (int i = 0; i < numAddresses; i++) {
//            for (int j = 0; j < numAddresses; j++) {
//                if (i == j) {
//                    distances[i][j] = 0;
//                } else {
//                    distances[i][j] = (int) calculateDistanceBetweenAddresses(addresses.get(i), addresses.get(j));
//                }
//            }
//        }
//
//        return distances;
//    }
//
//    private static double calculateDistanceBetweenAddresses(String source, String destination) {
//        double sourceLatitude = AddressCoordinatesService.getAddressLatitude(source);
//        double sourceLongitude = AddressCoordinatesService.getAddressLongitude(source);
//        double destLatitude = AddressCoordinatesService.getAddressLatitude(destination);
//        double destLongitude = AddressCoordinatesService.getAddressLongitude(destination);
//
//        double earthRadius = 6371; //dünyanın kilometre biçiminden yarı çapı
//        double dLat = Math.toRadians(destLatitude - sourceLatitude);
//        double dLon = Math.toRadians(destLongitude - sourceLongitude);
//        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
//                Math.cos(Math.toRadians(sourceLatitude)) * Math.cos(Math.toRadians(destLatitude)) *
//                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//        double distance = earthRadius * c;
//
//        return distance;
//    }
//
//    public static void main(String[] args) {
//        String dailySchedulesFilePath = "C:\\Users\\rootshn\\Desktop\\daily_schedules.txt";
//        List<List<String>> shortestRoutes = findShortestRoutes(dailySchedulesFilePath);
//
//        for (List<String> route : shortestRoutes) {
//            System.out.println("Optimize Edilmiş Rota: " + route);
//        }
//    }
//}
