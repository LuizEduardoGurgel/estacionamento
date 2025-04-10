package br.com.luiz.data.dto;

import java.time.LocalDateTime;

public class VehicleExitDTO {
    private String licensePlate;
    private LocalDateTime entranceDate;
    private LocalDateTime exitDate;
    private long parkingDuration;
    private double amountToPay;

    public VehicleExitDTO() {}

    public VehicleExitDTO(String licensePlate, LocalDateTime entranceDate, LocalDateTime exitDate, long parkingDuration, double amountToPay) {
        this.licensePlate = licensePlate;
        this.entranceDate = entranceDate;
        this.exitDate = exitDate;
        this.parkingDuration = parkingDuration;
        this.amountToPay = amountToPay;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public LocalDateTime getEntranceDate() {
        return entranceDate;
    }

    public void setEntranceDate(LocalDateTime entranceDate) {
        this.entranceDate = entranceDate;
    }

    public LocalDateTime getExitDate() {
        return exitDate;
    }

    public void setExitDate(LocalDateTime exitDate) {
        this.exitDate = exitDate;
    }

    public long getParkingDuration() {
        return parkingDuration;
    }

    public void setParkingDuration(long parkingDuration) {
        this.parkingDuration = parkingDuration;
    }

    public double getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(double amountToPay) {
        this.amountToPay = amountToPay;
    }
}
