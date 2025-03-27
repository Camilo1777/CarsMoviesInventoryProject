package com.carsmoviesinventory.app.Controllers;

import com.carsmoviesinventory.app.Entities.ExpensiveCarEntity;
import com.carsmoviesinventory.app.Services.ExpensiveCarService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/expensivecars")
@Validated
public class ExpensiveCarController{

    private final ExpensiveCarService expensiveCarService;

    public ExpensiveCarController(ExpensiveCarService expensiveCarService) {
        this.expensiveCarService = expensiveCarService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCars(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "brand,asc") String[] sort) {
        try {
                Pageable pageable = PageRequest.of(page, size, Sort.by(parseSort(sort)));
                return expensiveCarService.getAllCars(pageable);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("Innvalid sorting direction. Use 'asc' or 'desc'.");
            }
        }

    private Sort.Order parseSort(String[] sort) {
        if (sort.length < 2) {
            throw new IllegalArgumentException("Sort parameter must have both field and direction (e.g., 'brand,desc').");
        }

        String property = sort[0];
        String direction = sort[1].toLowerCase();

        List<String> validDirections = Arrays.asList("asc", "desc");
        if (!validDirections.contains(direction)) {
            throw new IllegalArgumentException("Invalid sort direction. Use 'asc' or 'desc'.");
        }

        return new Sort.Order(Sort.Direction.fromString(direction), property);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getCarById(@PathVariable UUID id){
        return expensiveCarService.getCarById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getCarsByBrand(
            @RequestParam String brand,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "brand,asc") String[] sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(parseSort(sort)));
        return expensiveCarService.getCarsByBrand (brand, pageable);
    }

    @PostMapping
    public ResponseEntity<?> insertCar(@Valid @RequestBody ExpensiveCarEntity expensiveCarEntity){
        return expensiveCarService.addCar(expensiveCarEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCar(@PathVariable UUID id, @Valid @RequestBody ExpensiveCarEntity expensiveCarEntity){
        return expensiveCarService.updateCar(id,expensiveCarEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable UUID id){
        return expensiveCarService.deleteCar(id);
    }

}