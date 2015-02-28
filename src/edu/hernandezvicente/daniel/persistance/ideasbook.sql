-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generaci�n: 31-01-2015 a las 20:52:47
-- Versi�n del servidor: 5.5.8
-- Versi�n de PHP: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Base de datos: `ideasbook`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `friendship`
--

DROP TABLE IF EXISTS `friendship`;
CREATE TABLE IF NOT EXISTS `friendship` (
  `id` bigint(20) NOT NULL,
  `user1` bigint(20) NOT NULL,
  `user2` bigint(20) NOT NULL,
  `type1` enum('friend','familiar','collegue') NOT NULL DEFAULT 'friend',
  `type2` enum('friend','familiar','collegue') NOT NULL DEFAULT 'friend',
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fr_unique_fk_1` (`user1`,`user2`),
  KEY `fr_u2_fk_1` (`user2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- (Evento) desencadenante `friendship`
--
DROP TRIGGER IF EXISTS `request_to_friendship`;
DELIMITER //
CREATE TRIGGER `request_to_friendship` AFTER INSERT ON `friendship`
 FOR EACH ROW DELETE FROM  `ideasbook`.`request` WHERE (
NEW.user1 = user1 AND NEW.user2 = user2
) OR (
NEW.user1 = user2 AND NEW.user2 = user1)
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `likes`
--

DROP TABLE IF EXISTS `likes`;
CREATE TABLE IF NOT EXISTS `likes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user` bigint(20) NOT NULL,
  `publication` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_like_fk_1` (`user`),
  KEY `pub_like_fk_1` (`publication`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `publication`
--

DROP TABLE IF EXISTS `publication`;
CREATE TABLE IF NOT EXISTS `publication` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `text` longtext CHARACTER SET ucs2 COLLATE ucs2_spanish_ci,
  `photo` longblob,
  `type` enum('P','T') NOT NULL DEFAULT 'T',
  `publication_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `user` bigint(20) NOT NULL,
  `comment_of` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_fk_1` (`user`),
  KEY `comment_fk_1` (`comment_of`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- (Evento) desencadenante `publication`
--
DROP TRIGGER IF EXISTS `init_pub_date`;
DELIMITER //
CREATE TRIGGER `init_pub_date` BEFORE INSERT ON `publication`
 FOR EACH ROW SET NEW.update_date = NOW()
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `request`
--

DROP TABLE IF EXISTS `request`;
CREATE TABLE IF NOT EXISTS `request` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user1` bigint(20) NOT NULL,
  `user2` bigint(20) NOT NULL,
  `ignored` tinyint(1) NOT NULL DEFAULT '0',
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `rq_unique_fk_1` (`user1`,`user2`),
  KEY `req_u2_fk_1` (`user2`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` longtext CHARACTER SET ucs2 COLLATE ucs2_spanish_ci NOT NULL,
  `email` longtext CHARACTER SET ucs2 COLLATE ucs2_spanish_ci NOT NULL,
  `password` longtext CHARACTER SET ucs2 COLLATE ucs2_spanish_ci NOT NULL,
  `photo` longblob NOT NULL,
  `age` int(11) DEFAULT NULL,
  `since` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha desde la que el usuario est� en el sistema',
  `last_connection` date DEFAULT NULL COMMENT 'Fecha de la �ltima conexi�n del usuario',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Filtros para las tablas descargadas (dump)
--

--
-- Filtros para la tabla `friendship`
--
ALTER TABLE `friendship`
  ADD CONSTRAINT `fr_u1_fk_1` FOREIGN KEY (`user1`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `fr_u2_fk_1` FOREIGN KEY (`user2`) REFERENCES `user` (`id`);

--
-- Filtros para la tabla `likes`
--
ALTER TABLE `likes`
  ADD CONSTRAINT `pub_like_fk_1` FOREIGN KEY (`publication`) REFERENCES `publication` (`id`),
  ADD CONSTRAINT `user_like_fk_1` FOREIGN KEY (`user`) REFERENCES `user` (`id`);

--
-- Filtros para la tabla `publication`
--
ALTER TABLE `publication`
  ADD CONSTRAINT `comment_fk_1` FOREIGN KEY (`comment_of`) REFERENCES `publication` (`id`),
  ADD CONSTRAINT `user_fk_1` FOREIGN KEY (`user`) REFERENCES `user` (`id`);

--
-- Filtros para la tabla `request`
--
ALTER TABLE `request`
  ADD CONSTRAINT `req_u2_fk_1` FOREIGN KEY (`user2`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `req_u1_fk_1` FOREIGN KEY (`user1`) REFERENCES `user` (`id`);
