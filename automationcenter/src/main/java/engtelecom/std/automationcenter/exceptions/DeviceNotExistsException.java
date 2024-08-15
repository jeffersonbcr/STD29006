package engtelecom.std.automationcenter.exceptions;

public class DeviceNotExistsException extends RuntimeException{
    public DeviceNotExistsException ()
    {
        super("One of the devices in this list could not be found.");
    }
}
