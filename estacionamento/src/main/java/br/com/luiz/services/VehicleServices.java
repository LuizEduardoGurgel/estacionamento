package br.com.luiz.services;

import br.com.luiz.controllers.VehicleController;
import br.com.luiz.data.dto.VehicleDTO;
import br.com.luiz.data.dto.VehicleExitDTO;
import br.com.luiz.exception.BusinessException;
import br.com.luiz.exception.DuplicateLicensePlateException;
import br.com.luiz.exception.ResourceNotFoundException;
import br.com.luiz.exception.IllegalArgumentException;
import br.com.luiz.model.Vehicle;
import br.com.luiz.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static br.com.luiz.mapper.ObjectMapper.parseListObjects;
import static br.com.luiz.mapper.ObjectMapper.parseObject;
import static br.com.luiz.util.LicensePlateUtils.isValidLicensePlate;
import static br.com.luiz.util.LicensePlateUtils.normalizeLicensePlate;


@Service
public class VehicleServices {

    private Logger logger = LoggerFactory.getLogger(VehicleServices.class.getName());

    @Autowired
    VehicleRepository repository;

    private final AtomicLong counter = new AtomicLong();
    public List<VehicleDTO> findAll(){

        logger.info("Finding All the Vehicles");

        return parseListObjects(repository.findAll(), VehicleDTO.class);
    }

    public VehicleDTO findByLicensePlate(String licensePlate) {

        String normalizedLicensePlate = normalizeLicensePlate(licensePlate);

        if(!isValidLicensePlate(normalizedLicensePlate)){
            throw new IllegalArgumentException("Invalid license plate format. Expected ABC1234 or ABC1B34 or ABC-1234.");
        }

        logger.info("Finding the Car with license plate: " + normalizedLicensePlate);

        var entity = repository.findByLicensePlate(normalizedLicensePlate)
             .orElseThrow(() -> new ResourceNotFoundException("No records found for this license plate"));
        return parseObject(entity, VehicleDTO.class);
    }

    public List<VehicleDTO> findAllByFilter(String filter) {

        String normalizedFilter = normalizeLicensePlate(filter);

        logger.info("Finding All the Vehicles with the filter: " + filter);

        return parseListObjects(repository.findAllByLicensePlateContaining(normalizedFilter), VehicleDTO.class);
    }

    public VehicleDTO create(VehicleDTO vehicle) {

        if(!isValidLicensePlate(vehicle.getLicensePlate())){
            throw new IllegalArgumentException("Invalid license plate format. Expected ABC1234 or ABC1B34 or ABC-1234.");
        }

        if(existsByLicensePlate(vehicle.getLicensePlate())){
            throw new DuplicateLicensePlateException("This license plate already exists.");
        }

        vehicle.setLicensePlate(normalizeLicensePlate(vehicle.getLicensePlate()));

        logger.info("Creating a new Vehicle");

        var entity = parseObject(vehicle, Vehicle.class);
        entity.setEntranceDate(LocalDateTime.now());

        return parseObject(repository.save(entity), VehicleDTO.class);
    }

    public VehicleDTO update(String licensePlate, VehicleDTO vehicle) {

        if(!isValidLicensePlate(licensePlate)){
            throw new IllegalArgumentException("Invalid license plate format. Expected ABC1234 or ABC1B34 or ABC-1234.");
        }

        Vehicle entity = repository.findByLicensePlate(licensePlate)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this license plate"));

        entity.setBrand(vehicle.getBrand());
        entity.setModel(vehicle.getModel());
        entity.setType(vehicle.getType());
        entity.setColor(vehicle.getColor());

        return parseObject(repository.save(entity), VehicleDTO.class);
    }

    public VehicleExitDTO delete (String licensePlate){

        if(!isValidLicensePlate(licensePlate)){
            throw new IllegalArgumentException("Invalid license plate format. Expected ABC1234 or ABC1B34 or ABC-1234.");
        }

        logger.info("Deleting the Vehicle with license plate: " + licensePlate);

        var entity = repository.findByLicensePlate(licensePlate)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this license plate"));

        LocalDateTime exitDate = LocalDateTime.now();
        entity.setExitDate(exitDate);

        long duration = Duration.between(entity.getEntranceDate(), exitDate).toMinutes();
        double amount = (duration / 60.0) * 10.0;

        repository.delete(entity);

        return new VehicleExitDTO(
                entity.getLicensePlate(),
                entity.getEntranceDate(),
                exitDate,
                duration,
                amount
        );
    }

    public boolean existsByLicensePlate(String licensePlate) {
        String normalized = normalizeLicensePlate(licensePlate);
        return repository.existsByLicensePlate(normalized);
    }
}
