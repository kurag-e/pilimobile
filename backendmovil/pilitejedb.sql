-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-11-2025 a las 20:38:48
-- Versión del servidor: 10.1.25-MariaDB
-- Versión de PHP: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `pilitejedb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `boleta`
--

CREATE TABLE `boleta` (
  `id` bigint(20) NOT NULL,
  `numero` bigint(20) NOT NULL,
  `fecha` datetime NOT NULL,
  `subtotal` int(11) NOT NULL,
  `iva` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `usuario_id` bigint(20) NOT NULL,
  `nombre` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `region` varchar(80) COLLATE utf8mb4_unicode_ci NOT NULL,
  `comuna` varchar(80) COLLATE utf8mb4_unicode_ci NOT NULL,
  `direccion` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `estado_pago` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `boleta`
--

INSERT INTO `boleta` (`id`, `numero`, `fecha`, `subtotal`, `iva`, `total`, `usuario_id`, `nombre`, `email`, `region`, `comuna`, `direccion`, `estado_pago`) VALUES
(3, 1, '2025-11-20 22:23:44', 20000, 3800, 23800, 1, 'Tu Nombre', 'correo@algo.cl', 'RM', 'Santiago', 'Av Siempre Viva', 'PAGADO'),
(4, 2, '2025-11-21 01:46:19', 36000, 6840, 42840, 2, 'Bale Fdez', 'bale@duoc.cl', 'RM', 'Santiago', 'Calle Falsa 123', 'PAGADO'),
(5, 3, '2025-11-21 01:50:26', 12000, 2280, 14280, 2, 'bale fdez', 'bale@duoc.cl', 'Región Metropolitana de Santiago', 'pudahuel', 'Yumbel 1386', 'PAGADO'),
(6, 4, '2025-11-21 12:36:43', 10000, 1900, 11900, 4, '', '', '', '', '', 'PAGADO'),
(7, 5, '2025-11-21 12:46:21', 12000, 2280, 14280, 4, 'able sable', 'able@gmail.com', '', '', '', 'PAGADO'),
(8, 6, '2025-11-21 12:53:24', 24000, 4560, 28560, 4, 'able sable', 'able@gmail.com', '', '', '', 'PAGADO'),
(9, 7, '2025-11-21 12:54:10', 12000, 2280, 14280, 4, 'able sable', 'able@gmail.com', '', '', '', 'PAGADO'),
(10, 8, '2025-11-21 12:56:14', 24000, 4560, 28560, 4, 'able sable', 'able@gmail.com', '', '', '', 'PAGADO'),
(11, 9, '2025-11-21 12:59:21', 36000, 6840, 42840, 4, 'able sable', 'able@gmail.com', 'rm', 'stgo', 'si', 'PAGADO'),
(12, 10, '2025-11-21 13:11:00', 12000, 2280, 14280, 4, 'able sable', 'able@gmail.com', 'Maule', 'San Clemente', 'ola 123', 'PAGADO'),
(13, 11, '2025-11-21 19:19:18', 24000, 4560, 28560, 4, 'able sable', 'able@gmail.com', 'Los Ríos', 'Máfil', 'nose124', 'PAGADO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `boleta_detalle`
--

CREATE TABLE `boleta_detalle` (
  `id` bigint(20) NOT NULL,
  `boleta_id` bigint(20) NOT NULL,
  `producto_id` bigint(20) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio_unit` int(11) NOT NULL,
  `total_linea` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `boleta_detalle`
--

INSERT INTO `boleta_detalle` (`id`, `boleta_id`, `producto_id`, `cantidad`, `precio_unit`, `total_linea`) VALUES
(3, 3, 1, 2, 10000, 20000),
(4, 4, 2, 3, 12000, 36000),
(5, 5, 2, 1, 12000, 12000),
(6, 6, 1, 1, 10000, 10000),
(7, 7, 2, 1, 12000, 12000),
(8, 8, 2, 1, 12000, 12000),
(9, 8, 3, 1, 12000, 12000),
(10, 9, 2, 1, 12000, 12000),
(11, 10, 2, 2, 12000, 24000),
(12, 11, 2, 1, 12000, 12000),
(13, 11, 3, 1, 12000, 12000),
(14, 11, 6, 1, 12000, 12000),
(15, 12, 2, 1, 12000, 12000),
(16, 13, 2, 2, 12000, 24000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrito`
--

CREATE TABLE `carrito` (
  `id` bigint(20) NOT NULL,
  `usuario_id` bigint(20) NOT NULL,
  `creado_en` datetime NOT NULL,
  `actualizado_en` datetime NOT NULL,
  `session_id` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `carrito`
--

INSERT INTO `carrito` (`id`, `usuario_id`, `creado_en`, `actualizado_en`, `session_id`) VALUES
(1, 1, '2025-11-20 21:56:16', '2025-11-20 22:23:44', NULL),
(2, 2, '2025-11-21 01:17:49', '2025-11-21 01:50:26', NULL),
(3, 4, '2025-11-21 12:36:10', '2025-11-21 19:20:36', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrito_item`
--

CREATE TABLE `carrito_item` (
  `id` bigint(20) NOT NULL,
  `carrito_id` bigint(20) NOT NULL,
  `producto_id` bigint(20) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio_unit` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `carrito_item`
--

INSERT INTO `carrito_item` (`id`, `carrito_id`, `producto_id`, `cantidad`, `precio_unit`) VALUES
(2, 3, 3, 1, 12000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `descripcion` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `nombre`, `descripcion`) VALUES
(1, 'BT21', 'Colección BT21'),
(2, 'Idols', 'Amigurumis de idols'),
(3, 'Otros', 'Otros amigurumis');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contacto`
--

CREATE TABLE `contacto` (
  `id` bigint(20) NOT NULL,
  `run` varchar(12) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nombre` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `apellidos` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `comentario` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `fecha_envio` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `contacto`
--

INSERT INTO `contacto` (`id`, `run`, `nombre`, `apellidos`, `email`, `comentario`, `fecha_envio`) VALUES
(1, '21799444-1', 'Bale', 'Fernández', 'bale@duoc.cl', 'Quiero un amigurumi personalizado de Kookie, ¿me puedes dar más info?', '2025-11-21 05:56:17'),
(2, '22222222', 'able', 'sable', 'able@gmail.com', 'a', '2025-11-21 12:52:54'),
(3, '22222222', 'bale', 'si', 'able@gmail.com', 'olasdafsdf', '2025-11-21 19:18:57');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `descripcion` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `precio` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `stock_critico` int(11) DEFAULT NULL,
  `imagen` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `estado` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fecha_creacion` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `categoria_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `nombre`, `descripcion`, `precio`, `stock`, `stock_critico`, `imagen`, `estado`, `fecha_creacion`, `categoria_id`) VALUES
(1, 'Amigurumi Hobi', 'Amigurumi de Hobi tejido a mano.', 10000, 8, 9, '', 'ACTIVO', '2025-10-22', 1),
(2, 'Amigurumi Kookie', 'Hecho a mano con algodón suave.', 12000, 15, 0, '../img/kookie.jpeg', 'ACTIVO', '2025-10-22', 1),
(3, 'Amigurumi Koya', 'Koya tejido a crochet, suave y decorativo.', 12000, 5, NULL, '../img/rapmonster.jpeg', 'ACTIVO', '2025-10-22', 1),
(4, 'Amigurumi Van', 'Amigurumi de Van tejido a mano.', 14000, 4, NULL, '../img/coso.jpeg', 'ACTIVO', '2025-10-22', 1),
(5, 'Amigurumi Jhope', 'Hecho a mano con algodón suave.', 10000, 6, 8, '../img/jhope.png', 'ACTIVO', '2025-10-22', 2),
(6, 'Amigurumi RJ', 'Amigurumi de RJ tejido a mano.', 12000, 7, NULL, '../img/ovejita.jpeg', 'ACTIVO', '2025-10-22', 1),
(7, 'Amigurumi Chimmy', 'Muñeco tejido a crochet inspirado en Chimmy de BT21.', 12000, 8, NULL, '../img/perrito.jpeg', 'ACTIVO', '2025-10-22', 1),
(8, 'Amigurumi Suga', 'Muñeco tejido a crochet inspirado en Suga.', 12000, 8, NULL, '../img/suga.png', 'ACTIVO', '2025-10-22', 2),
(9, 'Amigurumi Tata', 'Muñeco tejido a crochet inspirado en Tata de BT21.', 12000, 8, NULL, '../img/tata.jpeg', 'ACTIVO', '2025-10-22', 1),
(10, 'Amigurumi Jimin', 'Muñeco tejido a crochet inspirado en Jimin.', 10000, 8, NULL, '../img/jiminchimy.png', 'ACTIVO', '2025-10-22', 1),
(11, 'Amigurumi Jin', 'Muñeco tejido a crochet inspirado en Jin.', 10000, 8, NULL, '../img/jin.png', 'ACTIVO', '2025-10-22', 2),
(12, 'Amigurumi Tae', 'Muñeco tejido a crochet inspirado en V (Taehyung).', 10000, 8, NULL, '../img/taetata.png', 'ACTIVO', '2025-10-22', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `rol` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `estado` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fecha_creacion` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `region` varchar(80) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `comuna` varchar(80) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `direccion` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `email`, `password`, `rol`, `estado`, `fecha_creacion`, `region`, `comuna`, `direccion`) VALUES
(1, 'pruebaaaaa', 'ayudasafds@gmail.com', 'naurnaur', 'VENDEDOR', 'ACTIVO', '', 'Metropolitana de Santiago', 'Santiago', 'dgfdfgdf'),
(2, 'bale fdez', 'bale@duoc.cl', '$2a$10$7dz54qtyH8gtnWHaEfFNoO5eeZEetxMa60VdA1qKpEVeEQzlH7jLK', 'CLIENTE', 'ACTIVO', '', 'Antofagasta', 'Antofagasta', 'adssads1223132'),
(3, 'Admin chadpapu', 'adminpapu@duoc.cl', '$2a$10$3JporKDcgltdxuZybp1/g.EnrXaqpkN82Dk/KV285tZBjjJ42ywqa', 'ADMIN', 'ACTIVO', '', 'RM', 'Santiago', 'casa del chad admin papu 1'),
(4, 'able sable', 'able@gmail.com', '$2a$10$rgQ2Jt1QMJjJ20yfnrJDLOL1u/trqCrlQjz8W7pgQrgpIGjPL/kb.', 'CLIENTE', 'ACTIVO', NULL, 'rm', 'stgo', 'nose124'),
(5, 'non si', 'no@gmail.com', '$2a$10$aHYo4K6K8pD9tl3WZQnl.uISiXOekGBI20TDKTOKoOK8TzNxNoMeG', 'CLIENTE', 'ACTIVO', '', 'papaya', 'papaya', 'papoy123'),
(6, 'keso', 'keso@duoc.cl', 'contra', 'ADMIN', 'ACTIVO', '2025-11-22', 'Antofagasta', 'Mejillones', 'mejilo0nessadf');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `boleta`
--
ALTER TABLE `boleta`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uq_boleta_numero` (`numero`),
  ADD KEY `idx_boleta_usuario` (`usuario_id`);

--
-- Indices de la tabla `boleta_detalle`
--
ALTER TABLE `boleta_detalle`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_detalle_boleta` (`boleta_id`),
  ADD KEY `idx_detalle_producto` (`producto_id`);

--
-- Indices de la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_carrito_usuario` (`usuario_id`);

--
-- Indices de la tabla `carrito_item`
--
ALTER TABLE `carrito_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_item_carrito` (`carrito_id`),
  ADD KEY `idx_item_producto` (`producto_id`);

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `contacto`
--
ALTER TABLE `contacto`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_producto_categoria` (`categoria_id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uq_usuario_email` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `boleta`
--
ALTER TABLE `boleta`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT de la tabla `boleta_detalle`
--
ALTER TABLE `boleta_detalle`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT de la tabla `carrito`
--
ALTER TABLE `carrito`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `carrito_item`
--
ALTER TABLE `carrito_item`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `contacto`
--
ALTER TABLE `contacto`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `boleta`
--
ALTER TABLE `boleta`
  ADD CONSTRAINT `fk_boleta_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `boleta_detalle`
--
ALTER TABLE `boleta_detalle`
  ADD CONSTRAINT `fk_detalle_boleta` FOREIGN KEY (`boleta_id`) REFERENCES `boleta` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_detalle_producto` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`);

--
-- Filtros para la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD CONSTRAINT `fk_carrito_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `carrito_item`
--
ALTER TABLE `carrito_item`
  ADD CONSTRAINT `fk_item_carrito` FOREIGN KEY (`carrito_id`) REFERENCES `carrito` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_item_producto` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`);

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `fk_producto_categoria` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
