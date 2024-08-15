package main.java.engtelecom.std.automationcenter.entities;

import engtelecom.std.automationcenter.entities.*;
import java.util.HashMap;
import java.util.Map;

public class Device {
    private String id;
    private String type;
    private int minValue;
    private int maxValue;
    private Map<String, Object> functionalities;

    public Device(String id, String type, int minValue, int maxValue) {
        this.id = id;
        this.type = type;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.functionalities = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Map<String, Object> getFunctionalities() {
        return functionalities;
    }

    public Object getFunctionality(String functionalityName) {
        return functionalities.get(functionalityName);
    }

    public void setFunctionality(String functionalityName, Object value) 
    {
        
        if (value instanceof Number) 
        {
            Number numberValue = (Number) value;
            if (numberValue.doubleValue() >= minValue && numberValue.doubleValue() <= maxValue) 
            {
                functionalities.put(functionalityName, value);
            } 
        }
        else if (value instanceof String) 
        {
            if (functionalityName.equals("power") && (value.equals("on") || value.equals("off"))) 
            {
                functionalities.put(functionalityName, value);
            }
            else if (functionalityName.equals("open") && (value.equals("on") || value.equals("off"))) 
            {
                functionalities.put(functionalityName, value);
            }  
        } 
    }
}
