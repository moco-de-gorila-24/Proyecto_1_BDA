USE veterinaria;

INSERT INTO duenio (nombre, apellidoP, apellidoM, direccion, email) VALUES
('Juan', 'García', 'López', 'Av. Sonora #123', 'juan.garcia@gmail.com'),
('María', 'Hernández', 'Torres', 'Calle Hidalgo #456', 'maria.hernandez@gmail.com'),
('Carlos', 'Ramírez', 'Sánchez', 'Blvd. Obregón #789', 'carlos.ramirez@gmail.com'),
('Ana', 'Martínez', 'Flores', 'Calle Guerrero #321', 'ana.martinez@gmail.com'),
('Luis', 'Gómez', 'Valenzuela', 'Av. Tecnológico #555', 'luis.gomez@gmail.com');

INSERT INTO telefono_dueno (telefono, id_duenio) VALUES
('6441234567', 1),
('6449876543', 1),
('6442345678', 2),
('6443456789', 3),
('6444567890', 3),
('6445678901', 4),
('6446789012', 5);

INSERT INTO veterinario (nombre, apellidoP, apellidoM, cedula_profesional, especialidad, telefono) VALUES
('Roberto', 'Mendoza', 'Castro', 'VET123456', 'Medicina general', '6441112233'),
('Laura', 'Sánchez', 'Ramírez', 'VET234567', 'Dermatología', '6442223344'),
('Pedro', 'López', 'García', 'VET345678', 'Cirugía', '6443334455'),
('Sofía', 'Torres', 'Hernández', 'VET456789', 'Medicina interna', '6444445566');

INSERT INTO mascota (nombre, especie, fecha_nacimiento, sexo, raza, id_dueno) VALUES
('Max', 'Perro', '2020-05-15', 'M', 'Labrador', 1),
('Luna', 'Gato', '2021-08-20', 'F', 'Siamés', 1),
('Rocky', 'Perro', '2019-03-10', 'M', 'Pastor alemán', 2),
('Mia', 'Gato', '2022-01-25', 'F', 'Persa', 3),
('Coco', 'Ave', '2023-06-12', 'M', 'Canario', 3),
('Nala', 'Perro', '2020-11-05', 'F', 'Chihuahua', 4),
('Toby', 'Conejo', '2022-09-18', 'M', 'Mini Rex', 5);

INSERT INTO consulta (fecha_hora, motivo, diagnostico, tratamiento, costo, id_mascota, id_veterinario) VALUES
('2026-09-01 10:30:00', 'Vacunación', 'Paciente en buen estado de salud.', 'Aplicación de vacuna antirrábica.', 350.00, 1, 1),
('2026-09-02 12:00:00', 'Problemas en la piel', 'Se observa irritación y enrojecimiento en la piel.', 'Aplicar tratamiento tópico durante 7 días.', 500.00, 3, 2),
('2026-09-03 09:15:00', 'Dolor abdominal', 'Inflamación abdominal leve.', 'Medicamento antiinflamatorio y dieta especial.', 650.50, 4, 4),
('2026-09-04 16:30:00', 'Revisión general', 'Paciente saludable, sin anomalías aparentes.', 'No requiere tratamiento.', 0.00, 5, 1),
('2026-09-05 11:00:00', 'Herida en una pata', 'Herida superficial en la pata delantera.', 'Limpieza de la herida y aplicación de medicamento.', 450.00, 6, 3),
('2026-09-06 14:45:00', 'Problemas digestivos', 'Se presentan síntomas de indigestión.', 'Dieta blanda durante tres días.', 300.00, 7, 4);

