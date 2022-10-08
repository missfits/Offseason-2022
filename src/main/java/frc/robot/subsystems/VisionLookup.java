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
    public SortedMap<Double, Double> angleMap;
    public SortedMap<Double, Double> angleToPositionMap;
    public SortedMap<Double, Double> velocityMap;
    public SortedMap<Double, Double> testMap;
    public VisionLookup(){
        
        //Change to JSON file
        //Use 2d array or array of objects instead
        //Distance in meters
        //Distance: how far away from front of robot to where ball lands
        angleMap = new TreeMap<Double, Double>();
        //Closest Shot, against hub
        angleMap.put(1.2446, 5.0);
        angleMap.put(3.0, 15.0);
        //Shoot from tape corner on tarmac
        angleMap.put(4.65, 20.0);
        //Shoot from safe zone
        angleMap.put(6.0, 23.0);
        

        // //Translate angle to encoder position
        // angleToPositionMap = new TreeMap<Double, Double>();
        // angleToPositionMap.put(0.0, 0.0);
        // angleToPositionMap.put(0.0, 0.0);
        // angleToPositionMap.put(0.0, 0.0);
        // angleToPositionMap.put(0.0, 0.0);
        // angleToPositionMap.put(0.0, 0.0);
        // angleToPositionMap.put(0.0, 0.0);

        //Distance in meters
        velocityMap = new TreeMap<Double, Double>();
        velocityMap.put(1.2446, 0.6);
        velocityMap.put(3.0, 0.65);
        velocityMap.put(4.65, 0.75);
        velocityMap.put(6.0, 0.825);
       

        testMap = new TreeMap<Double, Double>();
        testMap.put(0.0, 0.0);
        testMap.put(1.0, 15.0);
        testMap.put(2.0, 30.0);
        testMap.put(3.0, 45.0);
        testMap.put(4.0, 60.0);
        testMap.put(5.0, 75.0);
        testMap.put(6.0, 90.0);
        testMap.put(7.0, 105.0);
        testMap.put(8.0, 120.0);
        testMap.put(9.0, 135.0);
        testMap.put(10.0,150.0);




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

     /** Generalized interpolation code for shooter */
     public double shooterInterpolation(SortedMap<Double, Double> map, double d){
        //double distance = SHOOTER_FROM_TARGET; //in meters
        double distance = d; //in meters
        double originalDistance = distance;
        double closestKeyBelow = -1;  
        //Find lower distance in range by going through keys in map
        for (double x : map.keySet()){
            if(x < originalDistance && x > closestKeyBelow){
                closestKeyBelow = x;
            }
        }
    
        //if either of the int values are higher than the highest lookup table value,
        //set the values to the highest lookup table value
        if(closestKeyBelow > largestKey(map)){
            closestKeyBelow = largestKey(map);
        }

        double closestKeyAbove = closestKeyBelow + 1;

        //gets angle from the lookup table
        double lowerVal = map.get(closestKeyBelow);
        double upperVal = map.get(closestKeyAbove);
    
        //multiply the difference in the distance and floored value by the slope to get desired position of hood for that small distance 
        //then add that to the desired position of the lower floored value
        double desiredVal = ((upperVal - lowerVal)*(originalDistance - closestKeyBelow)  + lowerVal);
        return desiredVal;
    }

    

}
