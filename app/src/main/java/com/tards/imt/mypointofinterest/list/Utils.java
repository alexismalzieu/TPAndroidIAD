package com.tards.imt.mypointofinterest.list;

import com.tards.imt.mypointofinterest.model.Poi;

import java.util.Date;
import java.util.List;

public class Utils {

    public static Poi getPoiByDate(List<Poi> poiList, Date date){
        for (Poi poi : poiList){
            if(poi.getCreatedAt().equals(date)){
                return poi;
            }
        }
        return null;
    }

}
