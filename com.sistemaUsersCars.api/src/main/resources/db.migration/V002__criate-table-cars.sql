CREATE TABLE Cars (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    year INT,
    licensePlate VARCHAR(20),
    model VARCHAR(255),
    color VARCHAR(255),
    id_user BIGINT,
    FOREIGN KEY (id_user) REFERENCES Users(id)
);