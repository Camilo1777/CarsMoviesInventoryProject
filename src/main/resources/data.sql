MERGE INTO EXPENSIVE_CAR_ENTITY (id, brand, model, max_speed)
    KEY(id)
    VALUES
        ('550e8400-e29b-41d4-a716-446655440000', 'Ferrari', '488 GTB', 330),
        ('6fa459ea-ee8a-3ca4-894e-db77e160355e', 'Lamborghini', 'Huracán', 325),
        ('92b5b0b3-8c50-4f2a-b4c1-43da4f1a9dc4', 'Bugatti', 'Chiron', 420),
        ('8d60a7c7-fcd7-4512-95f1-621f8dd71127', 'McLaren', 'P1', 350),
        ('d5a3f1e3-6e93-4f65-9758-35d3bc26d53c', 'Aston Martin', 'DB11', 300),
        ('1e3a7c65-4b27-4031-bb2f-95b9f251cf18', 'Porsche', '911 Turbo S', 330),
        ('f97e4d9b-d4ae-4f62-92b8-5eb36fa5adbb', 'Koenigsegg', 'Agera RS', 447),
        ('111e2222-e89b-12d3-a456-426614174010', 'Pagani', 'Huayra', 357),
        ('222e3333-e89b-12d3-a456-426614174011', 'Mercedes-AMG', 'GT R', 318),
        ('333e4444-e89b-12d3-a456-426614174012', 'BMW', 'M8 Competition', 305),
        ('444e5555-e89b-12d3-a456-426614174013', 'Audi', 'R8 V10', 330),
        ('555e6666-e89b-12d3-a456-426614174014', 'Jaguar', 'F-Type SVR', 322),
        ('666e7777-e89b-12d3-a456-426614174015', 'Maserati', 'MC20', 325),
        ('777e8888-e89b-12d3-a456-426614174016', 'Tesla', 'Roadster', 400),
        ('888e9999-e89b-12d3-a456-426614174017', 'Lotus', 'Evora GT', 290);