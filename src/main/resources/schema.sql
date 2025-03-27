CREATE TABLE IF NOT EXISTS EXPENSIVE_CAR_ENTITY (
                                    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
                                    brand VARCHAR(255) NOT NULL,
                                    model VARCHAR(255) NOT NULL,
                                    max_speed INTEGER NOT NULL
);
