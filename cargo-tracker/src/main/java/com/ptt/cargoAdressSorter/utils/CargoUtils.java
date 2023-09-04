package com.ptt.cargoAdressSorter.utils;

import com.ptt.cargoAdressSorter.model.entities.cargoEntity.CargoEnt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class CargoUtils {
    public static LinkedHashMap<Integer, CargoEnt> arrayListToLinkedHashMap(List<CargoEnt> cargoEntList){
        LinkedHashMap<Integer, CargoEnt> linkedHashMapData = new LinkedHashMap<>();
        for (int i=0;i<cargoEntList.size();i++) {
            linkedHashMapData.put(i,cargoEntList.get(i));
        }
        return linkedHashMapData;
    }

    public static List<CargoEnt> linkedHashMapToArrayList(LinkedHashMap<Integer, CargoEnt> linkedHashMapData){
        List<CargoEnt> cargoEntList = new ArrayList<>();
        Iterator iterator = linkedHashMapData.keySet().iterator();
        while (iterator.hasNext()){
            cargoEntList.add(linkedHashMapData.get(iterator.next()));
        }
        return cargoEntList;
    }
}
