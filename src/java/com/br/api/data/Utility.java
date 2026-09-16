/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.data;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Wattana
 */
public class Utility {
    
    
     public static String getLocation(String cono, String divi) throws JSONException {

        JSONObject mJsonObj = new JSONObject();
        JSONArray mJsonArr = new JSONArray();

        try {
            mJsonArr = SelectDB2.Location(cono, divi);
            //System.out.println(mJsonArr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mJsonArr.length() > 0) {
            mJsonObj.put("data", mJsonArr);
            return mJsonObj.toString();
        } else {
            mJsonObj.put("status", "404");
            mJsonObj.put("message", "Data not found.");
            return mJsonObj.toString();
        }

    }

    public static String getCompany() throws JSONException {

        JSONObject mJsonObj = new JSONObject();
        JSONArray mJsonArr = new JSONArray();

        try {
            mJsonArr = SelectDB2.Company();
            //System.out.println(mJsonArr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mJsonArr.length() > 0) {
            mJsonObj.put("data", mJsonArr);
            return mJsonObj.toString();
        } else {
            mJsonObj.put("status", "404");
            mJsonObj.put("message", "Data not found.");
            return mJsonObj.toString();
        }
        
        

    }
    
      public static String List_Bank(String type, String code, String cono, String divi) throws JSONException {

        JSONObject mJsonObj = new JSONObject();
        JSONArray mJsonArr = new JSONArray();

        try {
            mJsonArr = SelectDB2.List_Bank(type, code, cono, divi);
            //System.out.println(mJsonArr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mJsonArr.length() > 0) {
            mJsonObj.put("data", mJsonArr);
            return mJsonObj.toString();
        } else {
            mJsonObj.put("status", "404");
            mJsonObj.put("message", "Data not found.");
            return mJsonObj.toString();
        }

    }


}
