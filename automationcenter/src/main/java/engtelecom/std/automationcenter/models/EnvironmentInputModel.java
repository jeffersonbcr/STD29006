package main.java.engtelecom.std.automationcenter.models;

import java.util.List;

public class EnvironmentInputModel {
    private String local;
    private List<String> deviceIds;

    public String getLocal() 
    {
        return local;
    }

    public void setLocal(String local) 
    {
        this.local = local;
    }

    public List<String> getDeviceIds() 
    {
        return deviceIds;
    }

    public void setDeviceIds(List<String> deviceIds) 
    {
        this.deviceIds = deviceIds;
    }
}
