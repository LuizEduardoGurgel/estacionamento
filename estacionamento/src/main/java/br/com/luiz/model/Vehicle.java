package br.com.luiz.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "license_plate",unique = true, nullable = false, length =  9)
    private String licensePlate;

    @Column(name = "model", nullable = false, length =  50)
    private String model;

    @Column(name = "brand", nullable = false, length =  50)
    private String brand;

    @Column(name = "color", nullable = false, length =  50)
    private String color;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

    @Column(name = "entrance_date", nullable = false)
    private LocalDateTime entranceDate;

    @Column(name = "exit_date", nullable = false)
    private LocalDateTime exitDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(id, vehicle.id) && Objects.equals(licensePlate, vehicle.licensePlate) && Objects.equals(model, vehicle.model) && Objects.equals(brand, vehicle.brand) && Objects.equals(color, vehicle.color) && type == vehicle.type && Objects.equals(entranceDate, vehicle.entranceDate) && Objects.equals(exitDate, vehicle.exitDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, licensePlate, model, brand, color, type, entranceDate, exitDate);
    }
}
