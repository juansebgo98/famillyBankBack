DROP DATABASE despensa;

CREATE DATABASE despensa;

use despensa;

CREATE TABLE almacenamientos (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(255),
  lugar VARCHAR(255)
);

CREATE TABLE productos (
  id INT PRIMARY KEY,
  imagen TEXT NOT NULL,
  nombre VARCHAR(255)
);

CREATE TABLE inventario (
  id INT PRIMARY KEY,
  almacenamiento_id INT,
  producto_id INT,
  cantidad INT,
  precio DECIMAL(10,2) NOT NULL,
  fecha_caducidad DATE,
  FOREIGN KEY (almacenamiento_id) REFERENCES almacenamientos(id),
  FOREIGN KEY (producto_id) REFERENCES productos(id)
);

INSERT INTO almacenamientos (id, nombre, lugar) VALUES 
(1, 'Almacen 1', 'Lugar 1'),
(2, 'Almacen 2', 'Lugar 2'),
(3, 'Almacen 3', 'Lugar 3');

INSERT INTO productos (id, imagen, nombre) VALUES 
(1, 'https://url-imagen.com/imagen1.jpg', 'Producto 1'),
(2, 'https://url-imagen.com/imagen2.jpg', 'Producto 2'),
(3, 'https://url-imagen.com/imagen3.jpg', 'Producto 3'),
(4, 'https://url-imagen.com/imagen4.jpg', 'Producto 4');

INSERT INTO inventario (id, almacenamiento_id, producto_id, cantidad, precio, fecha_caducidad) VALUES 
(1, 1, 1, 10, 50.0, '2023-03-23'),
(2, 1, 2, 5, 75.0, '2023-03-31'),
(3, 2, 1, 20, 45.0, '2023-04-10'),
(4, 2, 3, 7, 100.0, '2023-04-15'),
(5, 3, 2, 15, 60.0, '2023-04-30'),
(6, 3, 3, 3, 90.0, '2023-05-10'),
(7, 3, 4, 1, 90.0, '2023-05-10');
