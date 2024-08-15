package engtelecom.std.automationcenter.controller;

import engtelecom.std.automationcenter.entities.*;
import main.java.engtelecom.std.automationcenter.entities.Device;
import main.java.engtelecom.std.automationcenter.models.EnvironmentInputModel;
import engtelecom.std.automationcenter.exceptions.*;
import main.java.engtelecom.std.automationcenter.service.DeviceUpdatePublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class AutomationCenterController {

    private AutomationCenter automationCenter = new AutomationCenter();
        
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("API is up and running");
    }
    
    @PostMapping("/device")
    public Device registerDevice(@RequestBody Device device) {
        return automationCenter.registerDevice(device);
    }

    @GetMapping("/devices/{deviceId}")
    public Device getDevice(@PathVariable String deviceId) {
        try {
            return automationCenter.getDevice(deviceId);
        } catch (DeviceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/devices")
    public Collection<Device> getAllDevices() {
        return automationCenter.getAllDevices();
    }

    @PutMapping("/devices/{deviceId}/settings/{functionality}")
    public void updateDeviceFunctionality(
            @PathVariable String deviceId,
            @PathVariable String functionality,
            @RequestBody Object newValue) {
        automationCenter.updateDeviceFunctionality(deviceId, functionality, newValue);
    }    

    @GetMapping("/environments")
    public List<Environment> listEnvironments() 
    {
        return automationCenter.listEnvironments();
    }


    @PostMapping("/environments")
    public Environment createEnvironment(@RequestBody Environment environment) 
    {
        return automationCenter.createEnvironment(environment.getLocal());
    }

    @PutMapping("/environments/{local}/devices")
    public void addDeviceToEnvironment(@PathVariable String local, @RequestBody Device device) {
        try 
        {
            automationCenter.addDeviceToEnvironment(local, device);
        } 
        catch (Exception e) 
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/environments/{local}")
    public void deleteEnvironment(@PathVariable String local) {
        try {
            automationCenter.deleteEnvironment(local);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/environments/devices")
    public Environment createEnvironmentWithDevices(@RequestBody EnvironmentInputModel inputModel) {
        try {
            return automationCenter.createEnvironmentWithDevices(inputModel.getLocal(), inputModel.getDeviceIds());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/environments/{local}/turn-off")
    public void turnOffAllDevicesInEnvironment(@PathVariable String local) 
    {
        try 
        {
            automationCenter.turnOffAllDevicesInEnvironment(local);
        } 
        catch (EnvironmentNotFoundException e) 
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } 
        catch (Exception e) 
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ControllerAdvice
    class EnvironmentNotFound 
    {
        @ResponseBody
        @ExceptionHandler(EnvironmentNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String environmentNotFound(EnvironmentNotFoundException a)
        {
            return a.getMessage();
        }
    }

    @ControllerAdvice
    class DeviceNotFound 
    {
        @ResponseBody
        @ExceptionHandler(DeviceNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String deviceNotFound(DeviceNotFoundException a)
        {
            return a.getMessage();
        }
    }

    @ControllerAdvice
    class DeviceNotExists 
    {
        @ResponseBody
        @ExceptionHandler(DeviceNotExistsException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String deviceNotExists(DeviceNotExistsException a)
        {
            return a.getMessage();
        }
    }
    
}
