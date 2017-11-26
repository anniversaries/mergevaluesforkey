package com.anniversaries.greeknamedays;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String jsonString = null;

        if (0 < args.length) {

            try (BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
                 PrintWriter out = new PrintWriter("namedays.txt")) {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    line = br.readLine();
                }
                jsonString = sb.toString();
                jsonString = jsonString.substring(2,jsonString.length() - 2);

                Map<String,List<String>> map = new HashMap<>();
                for (String values : jsonString.split("\",\"")) {
                    String[] keyValue = values.split("\": \"");
                    String key = keyValue[0];
                    String value = keyValue[1];

                    if (!map.containsKey(key)) {
                        map.put(key,new ArrayList<String>());
                    }
                    map.get(key).add(value);
                }

                StringBuilder json = new StringBuilder();

                for (Map.Entry<String, List<String>> en : map.entrySet()) {
                    json
                            .append("\"")
                            .append(en.getKey())
                            .append("\"")
                            .append(":")
                            .append("\"")
                            .append(en.getValue().toString().replace("[","").replace("]",""))
                            .append("\",");
                }
                out.println(json);
                System.out.println("Values for same keys have been merged at namedays.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Invalid arguments count:" + args.length);
            System.exit(0);
        }

    }
}
