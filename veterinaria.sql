CREATE DATABASE veterinaria;

USE veterinaria;

CREATE TABLE duenio (
    id_duenio INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidoP VARCHAR(50) NOT NULL,
    apellidoM VARCHAR(50),
    direccion VARCHAR(150),
    email VARCHAR(100)
);

CREATE TABLE telefono_duenio (
    id_telefono INT AUTO_INCREMENT PRIMARY KEY,
    telefono VARCHAR(15) NOT NULL,
    id_duenio INT NOT NULL,

    FOREIGN KEY (id_duenio)
	REFERENCES duenio(id_duenio)
);

CREATE TABLE mascota (
    id_mascota INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    especie VARCHAR(50) NOT NULL,
    fecha_nacimiento DATE,
    sexo CHAR(1) NOT NULL,
    raza VARCHAR(50),
    id_duenio INT NOT NULL,

    CHECK (sexo IN ('M', 'F')),

    FOREIGN KEY (id_duenio)
        REFERENCES duenio(id_duenio)
);

CREATE TABLE veterinario (
    id_veterinario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidoP VARCHAR(50) NOT NULL,
    apellidoM VARCHAR(50),
    cedula_profesional VARCHAR(30) NOT NULL UNIQUE,
    especialidad VARCHAR(100),
    telefono VARCHAR(15)
);

CREATE TABLE consulta (
    id_consulta INT AUTO_INCREMENT PRIMARY KEY,
    fecha_hora DATETIME NOT NULL,
    motivo VARCHAR(200) NOT NULL,
    diagnostico TEXT,
    tratamiento TEXT,
    costo DECIMAL(10,2) NOT NULL,

    CHECK (costo >= 0),

    id_mascota INT NOT NULL,
    id_veterinario INT NOT NULL,

    FOREIGN KEY (id_mascota)
        REFERENCES mascota(id_mascota),

    FOREIGN KEY (id_veterinario)
        REFERENCES veterinario(id_veterinario)
);