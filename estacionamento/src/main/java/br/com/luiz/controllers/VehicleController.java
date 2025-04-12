package br.com.luiz.controllers;

import br.com.luiz.data.dto.VehicleDTO;
import br.com.luiz.data.dto.VehicleExitDTO;
import br.com.luiz.services.VehicleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleServices service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehicleDTO> findAll(@RequestParam(value = "licensePlate", required = false) String licensePlate) {
        if(licensePlate != null && !licensePlate.isBlank()) {
            return service.findAllByFilter(licensePlate);
        }
        return service.findAll();
    }

    @GetMapping(value = "/{licensePlate}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleDTO findByLicensePlate(@PathVariable("licensePlate") String licensePlate)
    { return service.findByLicensePlate(licensePlate); }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public VehicleDTO create(@RequestBody VehicleDTO vehicle) { return service.create(vehicle); }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public VehicleDTO update(@RequestBody VehicleDTO vehicle) { return service.update(vehicle); }

    @DeleteMapping(value = "/{licensePlate}")
    public ResponseEntity<VehicleExitDTO> delete(@PathVariable("licensePlate") String licensePlate) {
        VehicleExitDTO exitDTO = service.delete(licensePlate);
        return ResponseEntity.ok(exitDTO);
    }

}
