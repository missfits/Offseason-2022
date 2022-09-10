package frc.robot.subsystems;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

// import com.google.gson.Gson;

// import org.json.simple.JSONArray;
// import org.json.simple.JSONObject;
// import org.json.simple.parser.JSONParser;

import edu.wpi.first.wpilibj2.command.PrintCommand;
import frc.robot.Constants.*;

import java.io.*;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Iterator;




public class VisionLookup{
    SortedMap<Double, Double> angleMap;
    SortedMap<Double, Double> velocityMap;
    public VisionLookup() throws Exception{
        
        //Change to JSON file
        //Use 2d array or array of objects instead
        angleMap = new TreeMap<Double, Double>();
        angleMap.put(4.0, 23.31);
        angleMap.put(5.0, 23.5);
        angleMap.put(6.0, 25.07);
        angleMap.put(7.0, 26.21);
        angleMap.put(8.0, 27.37);
        angleMap.put(9.0, 29.62);

        velocityMap = new TreeMap<Double, Double>();
        velocityMap.put(4.0, 1250.0);
        velocityMap.put(5.0, 1250.0);
        velocityMap.put(6.0, 1300.0);
        velocityMap.put(7.0, 1325.0);
        velocityMap.put(8.0, 1370.0);
        velocityMap.put(9.0, 1430.0);



        // Object obj = new JSONParser().parse(new FileReader("/Users/karinaanders/Documents/GitHub/Offseason-2022/src/main/java/frc/robot/Constants/TestData.json"));
        // JSONObject shootingData = (JSONObject) obj;
        // shootingDataMap = (HashMap<String, Object>)(new Gson().fromJson(shootingData.toString(), HashMap.class));
        // for (String i : shootingDataMap.keySet()) {
        //     System.out.println(i);
        //   }
        //Map<String, Integer> data = ((Map)shootingData.get("data"));
          
        // // // iterating
        // Iterator<Map.Entry<String, Integer>> itr1 = data.entrySet().iterator();
        // while (itr1.hasNext()) {
        //     Map.Entry<String, Integer> pair = itr1.next();
        //     //System.out.println(pair.getKey() + " : " + pair.getValue());
        //     System.out.print("hi");
        // }
    }

    double getAngle(double key){
        return angleMap.get(key);
    }

    double getVelocity(double key){
        return velocityMap.get(key);
    }

    //Could call sort function on keys
    //Sort keys at beggining and save values
    //Binary search
    double largestKey(SortedMap<Double, Double> map){
        return map.lastKey();
    }

    double smallestKey(SortedMap<Double, Double> map){
        return map.firstKey();
    }

    

}
