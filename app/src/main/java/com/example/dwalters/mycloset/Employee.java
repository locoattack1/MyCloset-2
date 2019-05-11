package com.example.dwalters.mycloset;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class Employee {

    public String brand, typeOfItem, nameOfItem, desc;
    public static String jsonString;
    public static Boolean loaded = false;
    public static JSONObject json;
    public static JSONArray employees;

    public static ArrayList<Employee> getEmployeesFromFile(String filename, Context context){
        ArrayList<Employee> employeeList = new ArrayList<>();

        if (!loaded) {
            File file = context.getFileStreamPath(filename);

            if(file == null || !file.exists()) {
                saveJsonToFile(loadJsonFromAsset("employees.json", context), context);
                loaded = true;
            }
        }

        try {
            setJsonParent(filename, context);

            for(int i = 0; i < employees.length(); i++){
                Employee employee = new Employee();

                employee.brand = employees.getJSONObject(i).getString("brand");
                employee.typeOfItem = employees.getJSONObject(i).getString("typeOfItem");
                employee.nameOfItem = employees.getJSONObject(i).getString("nameOfItem");
                employee.desc = employees.getJSONObject(i).getString("desc");

                employeeList.add(employee);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return employeeList;
    }

    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

    public static void saveJsonToFile(String fileContents, Context context) {
        try {
            FileOutputStream outputStream = context.openFileOutput("employeeslocal.json", context.MODE_PRIVATE);

            outputStream.write(fileContents.getBytes());
            outputStream.close();

            loaded = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readFromFile(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.openFileInput(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

    private static void setJsonParent(String filename, Context context) {
        try {
            jsonString = readFromFile(filename, context);
            json = new JSONObject(jsonString);
            employees = json.getJSONArray("employees");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void updateJSONFile(Context context, ArrayList<Employee> empList) {
        JSONObject mainJSON = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        try {
            for (int i=0; i < empList.size(); i++) {
                JSONObject newItem = new JSONObject();
                newItem.put("brand", empList.get(i).brand);
                newItem.put("typeOfItem", empList.get(i).typeOfItem);
                newItem.put("nameOfItem", empList.get(i).nameOfItem);
                newItem.put("desc", empList.get(i).desc);

                jsonArray.put(newItem);
            }

            mainJSON.put("employees", jsonArray);
            Employee.saveJsonToFile(mainJSON.toString(), context);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.brand + " - " + this.nameOfItem + "\n" + this.typeOfItem + "\n" + this.desc;
    }
}