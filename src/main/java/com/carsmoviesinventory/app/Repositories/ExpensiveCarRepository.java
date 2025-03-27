package com.carsmoviesinventory.app.Repositories;

import com.carsmoviesinventory.app.Entities.ExpensiveCarEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExpensiveCarRepository extends JpaRepository<ExpensiveCarEntity, UUID>{

    Page<ExpensiveCarEntity> findAllByBrandContaining(String brand, Pageable pageable);

    @Override
    Page<ExpensiveCarEntity> findAll(Pageable pageable);
}