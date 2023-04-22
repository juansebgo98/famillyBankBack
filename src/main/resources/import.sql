INSERT INTO almacenamiento (id, nombre, lugar) VALUES (1, 'Frigorífico', 'Cocina');
INSERT INTO almacenamiento (id, nombre, lugar) VALUES (2, 'Pantry', 'Despensa');
INSERT INTO almacenamiento (id, nombre, lugar) VALUES (3, 'Cajón de verduras', 'Frigorífico');

INSERT INTO productos (id, nombre, cantidad, lista_sub_productos_order, imagen, descripcion, fecha_caducidad, precio, almacenamiento_id, producto_id, dtype) VALUES (1, 'Leche entera', 1, 1, 'https://www.ahorramas.com/on/demandware.static/-/Sites-ahorramas-master/default/dw4b983167/Assets/070864_C1C1/large/0/d/8/f/0d8f667331580cbf06bc4ae4446aa9693c685acd_070864_C1C1.jpg', 'Leche fresca de vaca', '2023-04-30', 1.5, 1, 1, "a");
INSERT INTO productos (id, nombre, cantidad, lista_sub_productos_order, imagen, descripcion, fecha_caducidad, precio, almacenamiento_id, producto_id, dtype) VALUES (4, 'Leche Desnatada', 1, 1, 'https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/202204/20/00120912300074____12__600x600.jpg', 'Leche fresca de vaca', '2023-04-30', 1.5, 1, 1, "a");
INSERT INTO productos (id, nombre, cantidad, lista_sub_productos_order, imagen, descripcion, fecha_caducidad, precio, almacenamiento_id, producto_id, dtype)VALUES (2, 'Yogur natural', 4, 2, 'https://mimedia.lidl.es/media/product/0/0/0/0/0/7/6/7/yogur-natural-e-lidl-432x232.png', 'Yogur natural sin azúcares añadidos', '2023-04-15', 0.80, 1, 2, "a");
INSERT INTO productos (id, nombre, cantidad, lista_sub_productos_order, imagen, descripcion, fecha_caducidad, precio, almacenamiento_id, producto_id, dtype) VALUES (3, 'Huevos', 12, 3, 'https://mimedia.lidl.es/media/product/0/0/0/0/0/7/6/7/huevos-l-e-lidl-432x232.png', 'Huevos de gallinas criadas en libertad', '2023-04-20', 1.99, 2, 3, "a");
-- Insertar almacenamientos

