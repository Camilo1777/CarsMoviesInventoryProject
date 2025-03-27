package com.carsmoviesinventory.app.Entities;

import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ExpensiveCarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("brand")
    @NotBlank(message = "Brand is required")
    @Size(min = 3, max = 100, message = "Brand must be between 3 and 100 characters")
    private String brand;

    @JsonProperty("model")
    @NotBlank(message = "Model is required")
    private String model;

    @JsonProperty("maxSpeed")
    @NotNull(message = "Max speed is required")
    @Min(value = 1, message = "Max speed must be at least 1 km/h")
    private Integer maxSpeed;

    @PrePersist
    public void generateUUID() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    @Override
    public String toString() {
        return "ExpensiveCarEntity{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", maxSpeed=" + maxSpeed +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

}
