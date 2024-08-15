package engtelecom.std.automationcenter.exceptions;

public class EnvironmentNotFoundException extends RuntimeException{
    public EnvironmentNotFoundException( String id )
    {
        super("Unable to find an environment with this name: " + id );
    }
}
