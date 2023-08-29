//package com.ptt.cargoAdressSorter.services.dailyDistribution;
//
//import com.ptt.cargoAdressSorter.graph.Graph;
//import com.ptt.cargoAdressSorter.algorithm.NearestNeighbour;
//import lombok.Data;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class DailyDistributionFromDistrict {
//    public static void main(String[] args) {
//        String readerPath = "C:\\Users\\rootshn\\Desktop\\daily_schedules.txt";
//        List<List<String>> dailySchedules = readDailySchedulesFromFile(readerPath);
//
//        Graph graph = new Graph(dailySchedules.size()); // Create an instance of the Graph class
//
//        for (List<String> daySchedule : dailySchedules) {
//            for (int i = 0; i < daySchedule.size() - 1; i++) {
//                String source = daySchedule.get(i);
//                String destination = daySchedule.get(i + 1);
//                double distance = calculateDistanceBetweenAddresses(source, destination);
//                graph.addEdge(source, destination, (int) distance);
//            }
//        }
//
//        int[][] distances = graph.getAdjacencyMatrix();
//
//        // Usage of NearestNeighbour algorithm
//        int[] shortestPath = NearestNeighbour.NN(distances, 0);
//        NearestNeighbour.printPath(shortestPath);
//    }
//
//    public static List<List<String>> readDailySchedulesFromFile(String daily_schedules) {
//        List<List<String>> dailySchedules = new ArrayList<>();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(daily_schedules))) {
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
//    public static double calculateDistanceBetweenAddresses(String source, String destination) {
//        // Burada gerçek koordinatlarınıza göre hesaplama yapmam gerekiyor.
//        double sourceLatitude = getAddressLatitude(source);
//        double sourceLongitude = getAddressLongitude(source);
//        double destLatitude = getAddressLatitude(destination);
//        double destLongitude = getAddressLongitude(destination);
//
//        // Haversine formula to calculate distance between two coordinates
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
//    // Burada adresin koordinatlarını veritabanından veya harici bir kaynaktan almamız gerekiyor.
//    private static double getAddressLatitude(String address) {
//        // Adresin enlem (latitude) koordinatını döndürülecek.
//        return 0.0;//şimdilik default olarak 0 döndürüyorum.
//    }
//
//    private static double getAddressLongitude(String address) {
//        // Adresin boylam (longitude) koordinatı döndürülecek
//        return 0.0;//şimdilik default olarak 0 döndürüyorum.
//    }
//}
