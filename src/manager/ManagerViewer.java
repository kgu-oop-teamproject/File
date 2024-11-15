package manager;

import java.util.Map;
import java.util.Properties;

public class ManagerViewer {
    public void showChecking() {
        System.out.println("Checking setting file...");
    }

    public void showPropertyList(Properties settings) {
        System.out.println("##############################");
        for(Map.Entry<Object, Object> entry : settings.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        System.out.println("\n1. setBasic");
        System.out.println("2. Exit");
    }
}