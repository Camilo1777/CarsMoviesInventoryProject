package com.carsmoviesinventory.app.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.carsmoviesinventory.app.Repositories.ExpensiveCarRepository;
import com.carsmoviesinventory.app.Entities.ExpensiveCarEntity;

import java.util.*;

@Service
public class ExpensiveCarService{

    private final ExpensiveCarRepository expensiveCarRepository;

    public ExpensiveCarService(ExpensiveCarRepository expensiveCarRepository) {
        this.expensiveCarRepository = expensiveCarRepository;
    }

    public ResponseEntity<?> getAllCars(Pageable pageable) {
        Page<ExpensiveCarEntity> cars = expensiveCarRepository.findAll(pageable);
        return getResponseEntity(cars);
    }

    public ResponseEntity<?> getCarById(UUID id) {
        Optional<ExpensiveCarEntity> car = expensiveCarRepository.findById(id);
        if (car.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("Status", String.format("Car with ID %s not found.", id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(Collections.singletonMap("Car", car.get()));
    }


    public ResponseEntity<?> getCarsByBrand(String brand , Pageable pageable) {
        Page<ExpensiveCarEntity> cars = expensiveCarRepository.findAllByBrandContaining (brand, pageable);
        return getResponseEntity(cars);
    }

    private ResponseEntity<?> getResponseEntity(Page<ExpensiveCarEntity> cars) {
        Map<String, Object> response = new HashMap<>();
        response.put("TotalElements", cars.getTotalElements());
        response.put("TotalPages", cars.getTotalPages());
        response.put("CurrentPage", cars.getNumber());
        response.put("NumberOfElements", cars.getNumberOfElements());
        response.put("Cars", cars.getContent());
        return ResponseEntity.ok(response);
    }
//Agregar
    public ResponseEntity<?> addCar(ExpensiveCarEntity carToAdd) {
        Page<ExpensiveCarEntity> car = expensiveCarRepository.findAllByBrandContaining(
                carToAdd.getBrand(),
                Pageable.unpaged());
        if (car.getTotalElements() > 0) {
            return new ResponseEntity<>(Collections.singletonMap("Status", String.format("Car already exists with %d coincidences.", car.getTotalElements())), HttpStatus.CONFLICT);
        } else {
            ExpensiveCarEntity savedCar = expensiveCarRepository.save(carToAdd);
            return new ResponseEntity<>(Collections.singletonMap("Status", String.format("Added Car with ID %s", savedCar.getId())), HttpStatus.CREATED);
        }
    }

    public ResponseEntity<?> updateCar(UUID id, ExpensiveCarEntity carToUpdate) {
        Optional<ExpensiveCarEntity> car = expensiveCarRepository.findById(id);
        if (car.isEmpty()) {
            return new ResponseEntity<>(Collections.singletonMap("Status", String.format("Car with ID %s not found.", id)), HttpStatus.NOT_FOUND);
        }
        ExpensiveCarEntity existingCar = car.get();

        existingCar.setBrand(carToUpdate.getBrand());
        existingCar.setModel(carToUpdate.getModel());
        existingCar.setMaxSpeed(carToUpdate.getMaxSpeed());

        expensiveCarRepository.save(existingCar);

        return ResponseEntity.ok(Collections.singletonMap("Status", String.format("Updated Car with ID %s", existingCar.getId())));
    }

    public ResponseEntity<?> deleteCar(UUID id) {
        Optional<ExpensiveCarEntity> car = expensiveCarRepository.findById(id);
        if (car.isEmpty()) {
            return new ResponseEntity<>(Collections.singletonMap("Status", String.format("Car with ID %s doesn't exist.", id)),HttpStatus.NOT_FOUND);
        }
        expensiveCarRepository.deleteById(id);
        return ResponseEntity.ok(Collections.singletonMap("Status", String.format("Deleted Car with ID %s", id)));
    }

}