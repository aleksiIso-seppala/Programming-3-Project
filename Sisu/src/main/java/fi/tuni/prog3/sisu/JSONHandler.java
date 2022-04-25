/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package fi.tuni.prog3.sisu;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;
import java.nio.charset.*;
import java.util.ArrayList;
import com.google.gson.*;

/**
 *
 * @author aleks
 */
public class JSONHandler {

    public static ArrayList<Degree> readDegrees() throws MalformedURLException, IOException{
        
        ArrayList<Degree> degrees = new ArrayList<>();
        
        String sUrl = "https://sis-tuni.funidata.fi/kori/api/module-search?"
                + "curriculumPeriodId=uta-lvv-2021&universityId=tuni-university"
                + "-root-id&moduleType=DegreeProgramme&limit=1000";
        
        URL url = new URL(sUrl);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(url.openStream()));

        Gson gson = new Gson();
        JsonObject response = gson.fromJson(in,JsonObject.class);
        JsonElement rootElement = response.get("searchResults");
        JsonArray degreesJson = rootElement.getAsJsonArray();
        
        for(var object : degreesJson){
            JsonObject degree = (JsonObject) object;
            String groupId = degree.get("groupId").getAsString();
            String mUrl = "https://sis-tuni.funidata.fi/kori/api/modules/"
                    + "by-group-id?groupId=" + groupId + 
                    "&universityId=tuni-university-root-id";
            readDegree(mUrl);
        }
        return degrees;
    }
    
    public static void readDegree(String sUrl) throws MalformedURLException, IOException{
       
        URL url = new URL(sUrl);
        BufferedReader input = new BufferedReader(
            new InputStreamReader(url.openStream()));
        Gson gson = new Gson();
        JsonArray response = gson.fromJson(input,JsonArray.class);
        

        for(var object : response){
            JsonObject degree = (JsonObject) object;
            if(degree == null){
                continue;
            }
            
            String name;
            JsonObject nameObject = degree.getAsJsonObject("name");
            if(nameObject.get("fi") == null){
                name = null;
            }
            else{
                name = nameObject.get("fi").toString();               
            }
            
            Degree degreeObject = new Degree();
            
            JsonObject rules = degree.getAsJsonObject("rule");
            var innerRule = rules.getAsJsonArray("rules");
            if(innerRule == null){
                var tempRule = rules.getAsJsonObject("rule");
                innerRule = tempRule.getAsJsonArray("rules");
            }
            for(var rule : innerRule){
                JsonObject object1 = rule.getAsJsonObject();
                if(object1.get("type").getAsString().equals("ModuleRule")){
                    String groupId = object1.get("moduleGroupId").getAsString();
                    readModule(groupId);
                }
            }
            return ; 
        }

    } 
    
    public static void readModule(String groupId) throws MalformedURLException, IOException{
        
        String sUrl = "https://sis-tuni.funidata.fi/kori/api/modules/by-group-id?groupId="
                + groupId + "&universityId=tuni-university-root-id";
        URL url = new URL(sUrl);
        BufferedReader input = new BufferedReader (
                new InputStreamReader(url.openStream()));
        Gson gson = new Gson();
        JsonArray response = gson.fromJson(input, JsonArray.class);
        
        for (var object : response){
            
            JsonObject module = (JsonObject) object;
            var rules = module.getAsJsonObject("rule");
            
            while(rules.get("type").getAsString().equals("CreditsRule")){
                rules = rules.getAsJsonObject("rule");
            }
            
            var innerRules = rules.getAsJsonArray("rules");
            for(var rule : innerRules){
                JsonObject object1 = rule.getAsJsonObject();
                if(object1.get("type").getAsString().equals("CompositeRule")){
                    object1.getAsJsonArray("rules");
                }
                switch (object1.get("type").getAsString()) {
                    case "ModuleRule":
                        String moduleId = object1.get("moduleGroupId").getAsString();
                        readModule(moduleId);
                        break;
                    case "CourseUnitRule":
                        String courseId = object1.get("courseUnitGroupId").getAsString();
                        readCourse(courseId);
                        break;
                    case "CompositeRule":
                        var innerArray = object1.getAsJsonArray("rules");  
                        for(var inner : innerArray){
                            JsonObject innerO = inner.getAsJsonObject();
                            if(innerO.get("type").getAsString().equals("CompositeRule")){
                                var finalArray = innerO.getAsJsonArray("rules");
                                
                                for(var finalO : finalArray){
                                    JsonObject objectfinal =  finalO.getAsJsonObject();
                                    if(innerO.get("type").getAsString().equals("CompositeRule")){
                                        var jaiwdj = objectfinal.getAsJsonArray("rules");
                                        for(var awfda : jaiwdj){
                                            var awfk = awfda.getAsJsonObject();
                                        }
                                    }
                                }
                            }
                        }
                        
                    case "AnyCourseUnitRule":
                        break;
                    case "AnyModuleRule":
                        break;
                    default:
                        System.out.println(groupId);
                        break;
                }
            }
        }
        
    }
    
    public static void readCourse(String courseId) throws MalformedURLException, IOException{
        
        String sUrl = "https://sis-tuni.funidata.fi/kori/api/course-units/by-group-id?groupId="
                + courseId + "&universityId=tuni-university-root-id";
        URL url = new URL(sUrl);
        BufferedReader input = new BufferedReader (
                new InputStreamReader(url.openStream()));
        Gson gson = new Gson();
        JsonArray response = gson.fromJson(input, JsonArray.class);
        
        for(var course : response){
            JsonObject courseO = (JsonObject) course;
            
        }
    }
    
    public static void main(String args[]) throws IOException {
        readDegrees();
    }
    
    
}

