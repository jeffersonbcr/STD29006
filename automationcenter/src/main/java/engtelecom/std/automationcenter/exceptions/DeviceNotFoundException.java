package engtelecom.std.automationcenter.exceptions;

public class DeviceNotFoundException extends RuntimeException{
    public DeviceNotFoundException ( String id )
    {
        super("Unable to find an device with this name: " + id );
    }
}
