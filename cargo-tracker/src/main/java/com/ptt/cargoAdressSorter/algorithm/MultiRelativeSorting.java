package com.ptt.cargoAdressSorter.algorithm;

import com.ptt.cargoAdressSorter.model.entities.cargoEntity.CargoEnt;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;


public class MultiRelativeSorting {

    private static LinkedHashMap<Integer, LinkedHashMap<Integer, CargoEnt> > store = new LinkedHashMap<Integer, LinkedHashMap<Integer, CargoEnt> >();


    public void addToStore(LinkedHashMap<Integer, CargoEnt> cargoList){
        if(store.size() == 0){
            store.put(0,cargoList);
        }else{
            int lastKey = 0;
            for (int key:store.keySet()) {
                lastKey = key;
            }
            lastKey++;
            store.put(lastKey,cargoList);
        }

    }

    public LinkedHashMap<Integer, LinkedHashMap<Integer, CargoEnt> > getStore(){
        return store;
    }

    public void clearStore(){
        store.clear();
    }

    public LinkedHashMap<Integer, CargoEnt>  doRelativeSort(LinkedHashMap<Integer, CargoEnt> unOrdered){

        for (Integer key : store.keySet()) {

            LinkedHashMap<Integer, CargoEnt> orderedPart = store.get(key);
            for (int keyIn :   orderedPart.keySet()) {

                if(containsArrivalAdress(unOrdered,orderedPart.get(keyIn).getArrivalAddress())) {


                    int indexContainsValueAtUnordered = getKeyFromValue(unOrdered,orderedPart.get(keyIn));

                    int indexContainsValueAtOrdered = getKeyFromValue(orderedPart,orderedPart.get(keyIn));

                    for(int i = indexContainsValueAtOrdered+1 ; i<orderedPart.size();i++) {

                        for(int j=0; j<indexContainsValueAtUnordered; j++) {

                            if(unOrdered.get(j).getArrivalAddress().equals(orderedPart.get(i).getArrivalAddress()))
                                unOrdered = changeAfterSelectedKey(unOrdered,indexContainsValueAtUnordered,j);

                        }



                    }


                }


            }

        }



        return unOrdered;

    }

    private boolean containsArrivalAdress(LinkedHashMap<Integer, CargoEnt> unOrdered,String arrivalAddress){
        for (Integer key:unOrdered.keySet()) {
            if(unOrdered.get(key).getArrivalAddress().equals(arrivalAddress))
                return true;
        }
        return false;
    }

    public  LinkedHashMap<Integer, CargoEnt> changeAfterSelectedKey(LinkedHashMap<Integer, CargoEnt> arrayData, int selectedKey,int changingValueIndex){
        LinkedHashMap<Integer, CargoEnt> arrayDataAdded = new LinkedHashMap<Integer, CargoEnt>();
        LinkedHashMap<Integer, CargoEnt> arrayDataTemp = (LinkedHashMap<Integer, CargoEnt>) arrayData.clone();

        for(Integer key : arrayDataTemp.keySet()) {
            arrayDataAdded.put(key, arrayDataTemp.get(key));
            if(changingValueIndex<= key && key < selectedKey ) {
                arrayDataAdded.put(key, arrayDataTemp.get(key+1));

            }
            if(key == selectedKey) {
                arrayDataAdded.put(key, arrayData.get(changingValueIndex));
            }

        }

        return arrayDataAdded;

    }



    public  int getKeyFromValue(LinkedHashMap<Integer, CargoEnt> hashMapData, CargoEnt value) {

        for (Integer key: hashMapData.keySet()) {
            if(hashMapData.get(key).getArrivalAddress().equals(value.getArrivalAddress()))
                return key;

        }
        return -1;

    }

}
