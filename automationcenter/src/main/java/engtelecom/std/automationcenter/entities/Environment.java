package engtelecom.std.automationcenter.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.java.engtelecom.std.automationcenter.entities.Device;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Environment {
    private String local = null;
    private List<Device> devices = new ArrayList<>();

}
