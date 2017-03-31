package com.contrastsecurity.tools.forensics.mysql;

import java.util.Scanner;

public class Agent extends DefaultConfiguration {

    public static void main(String[] args) throws Exception {

        if (getContrastHome() == null) {
            System.out.println("Please define JVM argument -Dcontrast.home to point "
                    + "toward the installation directory of Contrast");
            System.exit(1);
        }

        Metrics.generate();

        String performanceEnabled = null;
        try {
            performanceEnabled = System.getProperty("performance.enabled");
        } catch (Exception e) {
            // system variable isn't defined
        }

        if (performanceEnabled != null && performanceEnabled.equals("true")) {
            Migration migration = new Migration();
            migration.apply();
            AdvancedMetrics.generate();
        }

        System.exit(0);
    }
}
