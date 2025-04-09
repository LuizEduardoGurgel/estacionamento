package br.com.luiz.util;

public class LicensePlateUtils {

    private static final String BRAZILIAN_PLATE_REGEX = "^[A-Z]{3}[0-9][A-Z0-9][0-9]{2}$";

    public static boolean isValidLicensePlate(String licensePlate) {
        if(licensePlate == null || licensePlate.isEmpty()) return false;

        String normalized = licensePlate.replaceAll("-", "")
                                         .replaceAll("\\s+", "")
                                         .toUpperCase();
        return normalized.matches(BRAZILIAN_PLATE_REGEX);
    }
}
