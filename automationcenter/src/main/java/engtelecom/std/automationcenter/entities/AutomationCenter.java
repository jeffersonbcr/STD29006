package engtelecom.std.automationcenter.entities;

import engtelecom.std.automationcenter.exceptions.EnvironmentNotFoundException;
import engtelecom.std.automationcenter.exceptions.DeviceNotExistsException;
import engtelecom.std.automationcenter.exceptions.DeviceNotFoundException;
import main.java.engtelecom.std.automationcenter.entities.Device;
import main.java.engtelecom.std.automationcenter.models.*;
import main.java.engtelecom.std.automationcenter.exceptions.EnvironmentExistException;
import main.java.engtelecom.std.automationcenter.exceptions.FuncionalityExistExcepetion;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class AutomationCenter {
    //List of devices
    HashMap<String, Device> devices;

    //Environments in Automation Center
    private List<Environment> environments;

    public AutomationCenter()
    {
        this.devices = new HashMap<>();
        this.environments = new ArrayList<>();
    }

    // POST register device
    public Device registerDevice(Device device) 
    {
        this.devices.put(device.getId(), device);
        return device;
    }

    // GET device by id
    public Device getDevice(String deviceId) 
    {
        Device device = devices.get(deviceId);
        if (device == null) 
        {
            throw new DeviceNotFoundException(deviceId);
        }
        return device;
    }

    // Get all devices
    public Collection<Device> getAllDevices() 
    {
        return devices.values();
    }

    // Put settings on device
    public void updateDeviceFunctionality(String deviceId, String functionality, Object newValue) 
    {
        Device device = getDevice(deviceId);
        if (!device.getFunctionalities().containsKey(functionality)) 
        {
            throw new FuncionalityExistExcepetion();
        }
        device.getFunctionalities().put(functionality, newValue);
    }

    // POST create an envinronment
    public Environment createEnvironment(String local) {
        if (environments.stream().anyMatch(env -> env.getLocal().equals(local))) 
        {
            throw new EnvironmentExistException();
        }
        Environment environment = new Environment(local, new ArrayList<>());
        environments.add(environment);
        return environment;
    }

    // GET environment by local
    private Environment getEnvironment(String local) {
        return environments.stream()
                .filter(env -> env.getLocal().equals(local))
                .findFirst()
                .orElseThrow(() -> new EnvironmentNotFoundException(local));
    }

    // POST add device to envinronment
    public void addDeviceToEnvironment(String local, Device device) {
        Environment environment = getEnvironment(local);
        environment.getDevices().add(device);
    }

    // DELETE device from envinroement
    public void removeDeviceFromEnvironment(String local, String deviceId) 
    {
        Environment environment = getEnvironment(local);
        if (!environment.getDevices().removeIf(device -> device.getId().equals(deviceId))) 
        {
            throw new DeviceNotFoundException(deviceId);
        }
    }

    // Get all envinroment
    public List<Environment> listEnvironments() 
    {
        return new ArrayList<>(environments); 
    }

    // DELETE envinroment
    public void deleteEnvironment(String local) {
        Environment environment = getEnvironment(local);
        environments.remove(environment);
    }

    // POST Create environment with devices
    public Environment createEnvironmentWithDevices(String local, List<String> deviceIds) 
    {
        if (environments.stream().anyMatch(env -> env.getLocal().equals(local))) 
        {
            throw new EnvironmentNotFoundException(local);
        }

        List<Device> deviceList = deviceIds.stream()
            .map(this::getDevice) 
            .collect(Collectors.toList());
        Environment newEnvironment = new Environment(local, deviceList);
        environments.add(newEnvironment);
        return newEnvironment;
    }

    public void turnOffAllDevicesInEnvironment(String local) 
    {
        Environment environment = environments.stream()
                                              .filter(env -> env.getLocal().equals(local))
                                              .findFirst()
                                              .orElseThrow(() -> new EnvironmentNotFoundException(local));
    
        for (Device device : environment.getDevices()) {
            switch (device.getType()) {
                case "curtain":
                case "eletronicgate":
                    device.setFunctionality("open", 0);
                    break;
                default:
                    device.setFunctionality("power", "off");
            }
        }
    }

}

