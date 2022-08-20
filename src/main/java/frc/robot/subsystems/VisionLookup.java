package frc.robot.subsystems;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import edu.wpi.first.wpilibj2.command.PrintCommand;
import frc.robot.Constants.*;

import java.io.*;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;




public class VisionLookup{
    Map<Double, Double> angleMap;
    Map<Double, Double> velocityMap;
    public HashMap<String, Object> shootingDataMap;
    public VisionLookup() throws Exception{
        
        //Change to JSON file
        angleMap = new HashMap<>();
        angleMap.put(4.0, 23.31);
        angleMap.put(5.0, 23.5);
        angleMap.put(6.0, 25.07);
        angleMap.put(7.0, 26.21);
        angleMap.put(8.0, 27.37);
        angleMap.put(9.0, 29.62);

        velocityMap = new HashMap<>();
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

    //Returns highest distance from angle map
    double highestDistanceAngle(){
        double highest = 0;
        for (double x : angleMap.keySet()){
            if(x > highest){
                highest = x;
            }
        }
        return highest;
    }
    
    //Returns smallest distance from angle map
    double lowestDistanceAngle(){
        double lowest = 25;
        for (double x : angleMap.keySet()){
            if(x > lowest){
                lowest = x;
            }
        }
        return lowest;
    }

    //Returns highest angle from angle map
    double highestAngle(){
        double highest = 0;
        for (double x : angleMap.values()){
            if(x > highest){
                highest = x;
            }
        }
        return highest;
    }
    
    //Returns smallest angle from angle map
    double lowestAngle(){
        double lowest = 0;
        for (double x : angleMap.values()){
            if(x > lowest){
                lowest = x;
            }
        }
        return lowest;
    }

    //Returns highest distance from velocity map
    double highestDistanceVelocity(){
        double highest = 0;
        for (double x : velocityMap.keySet()){
            if(x > highest){
                highest = x;
            }
        }
        return highest;
    }
    
    //Returns smallest distance from velocity map
    double lowestDistanceVelocity(){
        double lowest = 25;
        for (double x : velocityMap.keySet()){
            if(x > lowest){
                lowest = x;
            }
        }
        return lowest;
    }

    //Returns highest velocity from velocity map
    double highestVelocity(){
        double highest = 0;
        for (double x : velocityMap.values()){
            if(x > highest){
                highest = x;
            }
        }
        return highest;
    }
    
    //Returns smallest velocity from velocity map
    double lowestVelocity(){
        double lowest = 25;
        for (double x : velocityMap.values()){
            if(x > lowest){
                lowest = x;
            }
        }
        return lowest;
    }

    

}
