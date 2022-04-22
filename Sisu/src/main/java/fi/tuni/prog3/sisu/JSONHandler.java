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

    public static void readDegrees() throws MalformedURLException, IOException{
        String sUrl = "https://sis-tuni.funidata.fi/kori/api/module-search?"
                + "curriculumPeriodId=uta-lvv-2021&universityId=tuni-university"
                + "-root-id&moduleType=DegreeProgramme&limit=1000";
        
        URL url = new URL(sUrl);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(url.openStream()));
        
        Gson gson = new Gson();
        JsonObject response = gson.fromJson(in,JsonObject.class);
        JsonElement rootElement = response.get("searchResults");
        JsonArray degrees = rootElement.getAsJsonArray();
        
        for(var object : degrees){
            JsonObject degree = (JsonObject) object;
            String degreeName = degree.get("name").toString();
            String groupId = degree.get("groupId").getAsString();
            //String Id = degree.get("Id").toString();
            String code = degree.get("code").toString();
            String mUrl = "https://sis-tuni.funidata.fi/kori/api/modules/"
                    + "by-group-id?groupId=" + groupId + 
                    "&universityId=tuni-university-root-id";
            readDegree(mUrl);
        }
        
        
        
    }
    
   public static void readDegree(String sUrl) throws MalformedURLException, IOException{
       
       URL url = new URL(sUrl);
       BufferedReader input = new BufferedReader(
            new InputStreamReader(url.openStream()));
      Gson gson = new Gson();
      JsonArray response = gson.fromJson(input,JsonArray.class);
      for(var object : response){
          JsonObject degree = (JsonObject) object;
          String name = degree.get("name").toString();
          System.out.println(name);
      }
   } 
    
    public static void main(String args[]) throws IOException {
        readDegrees();
    }
}

