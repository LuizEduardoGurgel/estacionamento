package br.com.luiz.services;

import br.com.luiz.controllers.VehicleController;
import br.com.luiz.data.dto.VehicleDTO;
import br.com.luiz.exception.BusinessException;
import br.com.luiz.exception.ResourceNotFoundException;
import br.com.luiz.model.Vehicle;
import br.com.luiz.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static br.com.luiz.mapper.ObjectMapper.parseListObjects;
import static br.com.luiz.mapper.ObjectMapper.parseObject;
import static br.com.luiz.util.LicensePlateUtils.isValidLicensePlate;


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

        if(!isValidLicensePlate(licensePlate)){
            throw new BusinessException("Invalid license plate format. Expected ABC1234 or ABC1B34 or ABC-1234.");
        }

        logger.info("Finding the Car with license plate: " + licensePlate);

        var entity = repository.findByLicensePlate(licensePlate)
             .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        return parseObject(entity, VehicleDTO.class);
    }

    public VehicleDTO create(VehicleDTO vehicle) {

        if(!isValidLicensePlate(vehicle.getLicensePlate())){
            throw new BusinessException("Invalid license plate format. Expected ABC1234 or ABC1B34 or ABC-1234.");
        }

        logger.info("Creating a new Vehicle");

        var entity = parseObject(vehicle, Vehicle.class);

        return parseObject(repository.save(entity), VehicleDTO.class);
    }

    public VehicleDTO update(VehicleDTO vehicle) {

    }
}
