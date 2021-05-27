-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-05-2021 a las 23:20:12
-- Versión del servidor: 10.4.17-MariaDB
-- Versión de PHP: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `abd-proyecto-bnj`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hijos`
--

CREATE TABLE `hijos` (
  `nombre_completo` varchar(255) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `seccion` varchar(15) NOT NULL,
  `dni_tutor` varchar(9) NOT NULL,
  `dni` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `hijos`
--

INSERT INTO `hijos` (`nombre_completo`, `direccion`, `seccion`, `dni_tutor`, `dni`) VALUES
('Julian', 'Calle Perro', 'castores', '7623404G', '00000222K'),
('Raul Lopez', 'Calle Perro', 'castores', '7623404G', '0222222H'),
('Juanito', 'Calle Dragon', 'castores', '7777777A', '1234223G'),
('Jaime Alvarez', 'Perro 33', 'escultas', '2233333S', '2123345K'),
('Ana', 'Calle Dragon', 'manada', '7777777A', '232564577'),
('Lucas', 'Calle Dragon', 'manada', '7777777A', '234534675'),
('Lucia', 'Calle Dragon', 'manada', '7777777A', '245457587'),
('Jorgito', 'Calle Tortuga', 'escultas', '333599999', '287356482'),
('Margarita', 'Calle Perro', 'clan', '7623404G', '3333333C'),
('Pepito', 'calle test', 'manada', '2233333S', '44f');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `monitores`
--

CREATE TABLE `monitores` (
  `nombre_completo` varchar(255) NOT NULL,
  `seccion` varchar(15) NOT NULL,
  `dni_monitor` varchar(9) NOT NULL,
  `cargo` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `monitores`
--

INSERT INTO `monitores` (`nombre_completo`, `seccion`, `dni_monitor`, `cargo`, `email`, `password`) VALUES
('Pablo Alvarez', 'tropa', '02587404L', 'tesorero', 'pablo@gmail.com', '$2y$10$yI7uykIcmZsr.2QDQNt63evOrp/Iu4hsET3WFxfl0u4ct7gtCFXaW');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `remesa`
--

CREATE TABLE `remesa` (
  `mes` varchar(15) NOT NULL,
  `dni_hijo` varchar(9) NOT NULL,
  `concepto` varchar(255) NOT NULL,
  `cantidad` int(20) NOT NULL,
  `subvencionado` varchar(25) NOT NULL,
  `id_remesa` int(11) NOT NULL,
  `estado` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `remesa`
--

INSERT INTO `remesa` (`mes`, `dni_hijo`, `concepto`, `cantidad`, `subvencionado`, `id_remesa`, `estado`) VALUES
('enero', '00000222K', 'pelota', 33, 'si', 13, 'pendiente'),
('septiembre', '0222222H', 'uniformidad', 33, 'no', 14, 'pendiente'),
('enero', '0222222H', 'uniformidad', 55, 'no', 15, 'pendiente'),
('junio', '0222222H', 'campamento', 333, 'no', 16, 'pendiente'),
('enero', '0222222H', 'uniformidad', 55, 'no', 17, 'pendiente'),
('Abril', '3333333C', 'uniformidad', 66, 'no', 18, 'pendiente'),
('junio', '232564577', 'uniformidad', 55, 'si', 20, 'pagado'),
('febrero', '234534675', 'gafas', 33, 'no', 21, 'pendiente'),
('febrero', '44f', 'regalo san valentin', 232, 'si', 41, 'pendiente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seccion`
--

CREATE TABLE `seccion` (
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `seccion`
--

INSERT INTO `seccion` (`nombre`) VALUES
('castores'),
('clan'),
('escultas'),
('manada'),
('tropa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tutores`
--

CREATE TABLE `tutores` (
  `nombre_completo` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `dni` varchar(9) NOT NULL,
  `telefono` int(25) NOT NULL,
  `numero_cuenta` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tutores`
--

INSERT INTO `tutores` (`nombre_completo`, `email`, `dni`, `telefono`, `numero_cuenta`) VALUES
('Sagrario Gutierrez', 'sagra@hotmail.com', '2233333S', 666904444, 'ES222233334678'),
('Jorge Gonzalez', 'jorgon@mail.com', '333599999', 666777888, 'ES86608974656'),
('Maria Jose Mama', 'mrjose@gmail.com', '7623404G', 387645835, 'ES12353454656'),
('Jose papa', 'josepapa@gmail.com', '7777777A', 666555444, 'ES12345678997873');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `hijos`
--
ALTER TABLE `hijos`
  ADD PRIMARY KEY (`dni`),
  ADD KEY `dni_tutor` (`dni_tutor`),
  ADD KEY `seccion_index` (`seccion`);

--
-- Indices de la tabla `monitores`
--
ALTER TABLE `monitores`
  ADD PRIMARY KEY (`dni_monitor`),
  ADD KEY `seccion_index_monitores` (`seccion`) USING BTREE;

--
-- Indices de la tabla `remesa`
--
ALTER TABLE `remesa`
  ADD PRIMARY KEY (`id_remesa`),
  ADD KEY `dni_hijo_index` (`dni_hijo`);

--
-- Indices de la tabla `seccion`
--
ALTER TABLE `seccion`
  ADD PRIMARY KEY (`nombre`),
  ADD KEY `index_nombre` (`nombre`);

--
-- Indices de la tabla `tutores`
--
ALTER TABLE `tutores`
  ADD PRIMARY KEY (`dni`),
  ADD KEY `email` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `remesa`
--
ALTER TABLE `remesa`
  MODIFY `id_remesa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `hijos`
--
ALTER TABLE `hijos`
  ADD CONSTRAINT `dni_tutor` FOREIGN KEY (`dni_tutor`) REFERENCES `tutores` (`dni`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `seccion_hijos` FOREIGN KEY (`seccion`) REFERENCES `seccion` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `monitores`
--
ALTER TABLE `monitores`
  ADD CONSTRAINT `seccion_monitores` FOREIGN KEY (`seccion`) REFERENCES `seccion` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `remesa`
--
ALTER TABLE `remesa`
  ADD CONSTRAINT `dni_hijo` FOREIGN KEY (`dni_hijo`) REFERENCES `hijos` (`dni`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
