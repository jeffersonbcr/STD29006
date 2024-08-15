package main.java.engtelecom.std.automationcenter.exceptions;

public class EnvironmentExistException extends RuntimeException{
    public EnvironmentExistException ()
    {
        super("Environtmnet already exists");
    }
}
