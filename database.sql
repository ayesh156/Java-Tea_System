-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.29 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.0.0.6468
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for tea_system
CREATE DATABASE IF NOT EXISTS `tea_system` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tea_system`;

-- Dumping structure for table tea_system.advance
CREATE TABLE IF NOT EXISTS `advance` (
  `id` int NOT NULL AUTO_INCREMENT,
  `supplier_name` varchar(100) DEFAULT NULL,
  `supplier_id` varchar(10) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `price` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table tea_system.advance: ~2 rows (approximately)
INSERT INTO `advance` (`id`, `supplier_name`, `supplier_id`, `date`, `price`) VALUES
	(1, 'Mr. Kiri Aiya - 02', '119', '2024-09-17', '3000'),
	(2, 'Mr. M.G. Jayasekara', '12', '2024-10-16', '120'),
	(3, 'Mr. J. Karolis', '2', '2024-10-31', '200'),
	(4, 'Mr. M.G. Martin', '3', '2024-10-31', '320'),
	(5, 'Mr. M.G. Martin', '3', '2024-10-31', '200'),
	(6, 'Mr. M.G. Martin', '3', '2024-10-31', '23'),
	(7, 'Mr. J. Karolis', '2', '2024-10-31', '2000'),
	(8, 'Mr. L. Chandrasiri', '22', '2024-10-31', '3000'),
	(9, 'Mr. W.A. Wimalasiri', '55', '2024-10-31', '550'),
	(10, 'Mr. Chuti', '88', '2024-10-31', '8000'),
	(11, 'Mr. Botheju', '99', '2024-10-31', '9000'),
	(12, 'Mr. 31 Aiya', '121', '2024-10-31', '10000'),
	(13, 'Ms. P. Somawathi', '154', '2024-10-31', '5000');

-- Dumping structure for table tea_system.daily_leaf
CREATE TABLE IF NOT EXISTS `daily_leaf` (
  `id` int NOT NULL AUTO_INCREMENT,
  `supplier_name` varchar(100) DEFAULT NULL,
  `supplier_id` varchar(10) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `gross_qty` varchar(20) DEFAULT NULL,
  `net_qty` varchar(20) DEFAULT NULL,
  `transport_rate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=179 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table tea_system.daily_leaf: ~38 rows (approximately)
INSERT INTO `daily_leaf` (`id`, `supplier_name`, `supplier_id`, `date`, `gross_qty`, `net_qty`, `transport_rate`) VALUES
	(2, 'Mr. K.A.L. Priyantha', '87', '2024-08-22', '10', '10', '3.5'),
	(3, 'Mr. K.A.L. Priyantha', '87', '2024-09-19', '20', '20', '3.5'),
	(4, 'Mr. Kiri Aiya - 02', '119', '2024-09-18', '21', '20', '3.5'),
	(5, 'Mr. Kiri Aiya - 02', '119', '2024-10-14', '21', '20', '3.5'),
	(6, 'Mr. M.G. Jayasekara', '12', '2024-10-07', '20', '20', '3.5'),
	(7, 'Mr. M.G. Jayasekara', '12', '2024-11-04', '20', '20', '3.5'),
	(8, 'Mr. Albart', '13', '2024-10-14', '20', '20', '3.5'),
	(9, 'Eshara', '201', '2024-10-31', '5', '5', '3.5'),
	(10, 'Mr. Ekanayaka Iskole', '25', '2024-10-14', '20', '20', '3.5'),
	(11, 'Mr. Jayasiri', '97', '2024-10-14', '22', '22', '3.5'),
	(12, 'Mr. W.H.G. Premathssa', '7', '2024-10-29', '32', '32', '3.5'),
	(13, 'Mr. M.G. Jayasekara', '12', '2024-10-29', '42', '42', '3.5'),
	(14, 'Mr. Dayarathna', '16', '2024-10-29', '22', '22', '3.5'),
	(15, 'Mr. L. Chandrasiri', '22', '2024-10-29', '31', '31', '3.5'),
	(16, 'Mr. S.D. Dahanayaka', '29', '2024-10-29', '25', '25', '3.5'),
	(17, 'Mr. Sirisena', '37', '2024-10-29', '32', '30', '3.5'),
	(18, 'Ms. Yasawathi', '44', '2024-10-29', '30', '25', '3.5'),
	(19, 'Mr. Palitha', '46', '2024-10-29', '36', '35', '3.5'),
	(20, 'Mr. K.G. Nihal', '47', '2024-10-29', '34', '33', '3.5'),
	(21, 'Mr. Hinni', '53', '2024-10-29', '28', '26', '3.5'),
	(22, 'Mr. Pradeep', '66', '2024-10-29', '24', '24', '3.5'),
	(23, 'Mr. Vikramapala', '75', '2024-10-29', '19', '19', '3.5'),
	(24, 'Mr. Nandasena', '78', '2024-10-29', '18', '18', '3.5'),
	(25, 'Ms. C. Rathnayaka', '81', '2024-10-29', '18', '17', '3.5'),
	(26, 'Mr. L. Pradeep', '86', '2024-10-29', '31', '31', '3.5'),
	(27, 'Mr. K.A.L. Priyantha', '87', '2024-10-29', '22', '19', '3.5'),
	(28, 'Mr. Upul Indika', '98', '2024-10-29', '57', '57', '3.5'),
	(29, 'Mr. Somapala', '108', '2024-10-29', '22', '22', '3.5'),
	(30, 'Mr. 31 Aiya', '121', '2024-10-29', '44', '40', '3.5'),
	(31, 'Mr. M. Nandasena', '124', '2024-10-29', '27', '27', '3.5'),
	(32, 'Mr. Siril', '125', '2024-10-29', '27', '27', '3.5'),
	(33, 'Ms. Chandrani', '129', '2024-10-29', '23', '23', '3.5'),
	(34, 'Ms. Amarawathi', '141', '2024-10-29', '22', '22', '3.5'),
	(35, 'Mr. R.W. Wamaranayaka', '158', '2024-10-29', '26', '26', '3.5'),
	(36, 'Mr. S. Jayawardana', '164', '2024-10-29', '55', '55', '3.5'),
	(37, 'Ms. S.K. Ranjani', '171', '2024-10-29', '13', '12', '3.5'),
	(38, 'Mr. W.W. Samaranayaka', '175', '2024-10-29', '55', '51', '3.5'),
	(39, 'Ms. S.K. Punchi Hami', '183', '2024-10-29', '35', '35', '3.5'),
	(40, 'Ms. V. Premawathi', '186', '2024-10-29', '46', '46', '3.5'),
	(41, 'Mr. Thusitha Munasinha', '189', '2024-10-29', '44', '42', '3.5'),
	(42, 'Mr. J. Karolis', '2', '2024-10-31', '2', '2', '3.5'),
	(43, 'Mr. J. Karolis', '2', '2024-10-31', '20', '20', '3.5'),
	(44, 'Mr. A.D. Dahanayaka', '1', '2024-10-31', '20', '20', '3.5'),
	(45, 'Mr. J. Karolis', '2', '2024-10-31', '25', '25', '3.5'),
	(46, 'Mr. M.G. Martin', '3', '2024-10-31', '25', '25', '3.5'),
	(47, 'Mr. B.G. Asanka Kumara', '4', '2024-10-31', '27', '27', '3.5'),
	(48, 'Mr. Chathura', '5', '2024-10-31', '30', '30', '3.5'),
	(49, 'Mr. D.S. Rathnayaka', '6', '2024-10-31', '32', '32', '3.5'),
	(50, 'Mr. Gamage Ginadasa', '8', '2024-10-31', '21', '21', '3.5'),
	(51, 'Mr. O.N.D. Dahanayaka', '9', '2024-10-31', '28', '28', '3.5'),
	(52, 'Ms. J.K. Kusuma', '10', '2024-10-31', '28', '28', '3.5'),
	(53, 'Mr. N. Jayawardhana', '11', '2024-10-31', '25', '25', '3.5'),
	(54, 'Mr. M.G. Jayasekara', '12', '2024-10-31', '28', '28', '3.5'),
	(55, 'Mr. Albart', '13', '2024-10-31', '28', '28', '3.5'),
	(56, 'Mr. Saman Kumara', '15', '2024-10-31', '25', '25', '3.5'),
	(57, 'Mr. Dayarathna', '16', '2024-10-31', '38', '38', '3.5'),
	(58, 'Mr. Vilsan Jayavardana', '18', '2024-10-31', '32', '32', '3.5'),
	(59, 'Mr. Kalu', '19', '2024-10-31', '20', '20', '3.5'),
	(60, 'Mr. Mandi', '20', '2024-10-31', '29', '29', '3.5'),
	(61, 'Mr. B.L. Gunapala', '21', '2024-10-31', '51', '51', '3.5'),
	(62, 'Mr. L. Chandrasiri', '22', '2024-10-31', '22', '22', '3.5'),
	(63, 'Mr. Sumudu', '23', '2024-10-31', '23', '23', '3.5'),
	(64, 'Mr. Gamini', '24', '2024-10-31', '24', '24', '3.5'),
	(65, 'Mr. Ekanayaka Iskole', '25', '2024-10-31', '25', '25', '3.5'),
	(66, 'Mr. Sunil', '26', '2024-10-31', '25', '25', '3.5'),
	(67, 'Mr. W.A.Sirisena', '27', '2024-10-31', '27', '27', '3.5'),
	(68, 'Mr. W.A. Jayawardana', '28', '2024-10-31', '28', '28', '3.5'),
	(69, 'Mr. S.D. Dahanayaka', '29', '2024-10-31', '29', '29', '3.5'),
	(70, 'Mr. L Sudu Aiya', '30', '2024-10-31', '30', '30', '3.5'),
	(71, 'Mr. Peti Mahatha', '31', '2024-10-31', '31', '31', '3.5'),
	(72, 'Mr. L.A. Munasinha', '32', '2024-10-31', '32', '32', '3.5'),
	(73, 'Mr. K.K.G. Sirisena', '33', '2024-10-31', '33', '33', '3.5'),
	(74, 'Mr. Vile Peti Aiya', '34', '2024-10-31', '34', '34', '3.5'),
	(75, 'Mr. Piyadasa', '35', '2024-10-31', '35', '35', '3.5'),
	(76, 'Mr. Vijith', '36', '2024-10-31', '36', '36', '3.5'),
	(77, 'Mr. Sirisena', '37', '2024-10-31', '37', '37', '3.5'),
	(78, 'Mr. Maddu', '38', '2024-10-31', '38', '38', '3.5'),
	(79, 'Mr. M.G. Jinadasa', '40', '2024-10-31', '40', '40', '3.5'),
	(80, 'Ms. Dahanayaka', '41', '2024-10-31', '41', '41', '3.5'),
	(81, 'Mr. Bebi', '42', '2024-10-31', '42', '42', '3.5'),
	(82, 'Mr. Andarayas', '43', '2024-10-31', '43', '43', '3.5'),
	(83, 'Ms. Yasawathi', '44', '2024-10-31', '44', '44', '3.5'),
	(84, 'Mr. Mahesha Malli', '45', '2024-10-31', '45', '45', '3.5'),
	(85, 'Mr. Palitha', '46', '2024-10-31', '46', '46', '3.5'),
	(86, 'Mr. K.G. Nihal', '47', '2024-10-31', '47', '47', '3.5'),
	(87, 'Pelawaththe Gedara', '48', '2024-10-31', '48', '48', '3.5'),
	(88, 'Mr. Ranchagoda Aiya', '49', '2024-10-31', '49', '49', '3.5'),
	(89, 'Mr. Nihal', '50', '2024-10-31', '50', '50', '3.5'),
	(90, 'Mr. R. Amarabandu', '51', '2024-10-31', '51', '51', '3.5'),
	(91, 'Mr. W.P. Weerasinha', '52', '2024-10-31', '52', '52', '3.5'),
	(92, 'Mr. Hinni', '53', '2024-10-31', '53', '53', '3.5'),
	(93, 'Mr. Laal Aiya', '54', '2024-10-31', '54', '54', '3.5'),
	(94, 'Mr. W.A. Wimalasiri', '55', '2024-10-31', '55', '55', '3.5'),
	(95, 'Mr. Kumara', '56', '2024-10-31', '56', '56', '3.5'),
	(96, 'Mr. Ranjith', '57', '2024-10-31', '57', '57', '3.5'),
	(97, 'Mr. R.N. Samaranayaka', '58', '2024-10-31', '58', '58', '3.5'),
	(98, 'Mr. Thisara', '59', '2024-10-31', '59', '59', '3.5'),
	(99, 'A-10 Mr. Priyantha', '60', '2024-10-31', '60', '60', '3.5'),
	(100, 'Mr. Jinasiri', '62', '2024-10-31', '62', '62', '3.5'),
	(101, 'Mr. Gunadasa', '63', '2024-10-31', '63', '63', '3.5'),
	(102, 'Mr. S. Rathnayaka', '64', '2024-10-31', '64', '64', '3.5'),
	(103, 'Mr. L. Heri', '65', '2024-10-31', '65', '65', '3.5'),
	(104, 'Mr. Pradeep', '66', '2024-10-31', '66', '66', '3.5'),
	(105, 'Mr. Indika', '67', '2024-10-31', '67', '67', '3.5'),
	(106, 'Mr. Ananda Rathnayaka', '68', '2024-10-31', '68', '68', '3.5'),
	(107, 'Mr. K.A. Sarath', '69', '2024-10-31', '69', '69', '3.5'),
	(108, 'Ms. Seetha Rathnayaka', '70', '2024-10-31', '70', '70', '3.5'),
	(109, 'Mr. T. Rathnayaka', '71', '2024-10-31', '71', '71', '3.5'),
	(110, 'Mr. Vakkadapahala hena Ajith', '73', '2024-10-31', '73', '73', '3.5'),
	(111, 'Ms. Chandra Gamage', '74', '2024-10-31', '74', '74', '3.5'),
	(112, 'Mr. Vikramapala', '75', '2024-10-31', '75', '75', '3.5'),
	(113, 'Mr. Kiri Aiya', '76', '2024-10-31', '76', '76', '3.5'),
	(114, 'Ms. Deepika', '77', '2024-10-31', '77', '77', '3.5'),
	(115, 'Mr. Nandasena', '78', '2024-10-31', '78', '78', '3.5'),
	(116, 'Mr. R.K. Premadasa', '79', '2024-10-31', '79', '79', '3.5'),
	(117, 'Ms. Malani', '80', '2024-10-31', '80', '80', '3.5'),
	(118, 'Ms. C. Rathnayaka', '81', '2024-10-31', '81', '81', '3.5'),
	(119, 'Ms. R. Malani', '82', '2024-10-31', '82', '82', '3.5'),
	(120, 'Mr. Aiyadasa', '83', '2024-10-31', '83', '83', '3.5'),
	(121, 'Ms. Anurada', '85', '2024-10-31', '85', '85', '3.5'),
	(122, 'Mr. L. Pradeep', '86', '2024-10-31', '86', '86', '3.5'),
	(123, 'Mr. K.A.L. Priyantha', '87', '2024-10-31', '87', '87', '3.5'),
	(124, 'Mr. Chuti', '88', '2024-10-31', '88', '88', '3.5'),
	(125, 'Mr. P. Vijethunga', '91', '2024-10-31', '91', '91', '3.5'),
	(126, 'Ms. G.G. Sisiliyana', '92', '2024-10-31', '92', '92', '3.5'),
	(127, 'Ms. L. Virasinha', '93', '2024-10-31', '93', '93', '3.5'),
	(128, 'Mr. Thennahena Ajith', '94', '2024-10-31', '94', '94', '3.5'),
	(129, 'Ms. K. Rathnayaka', '95', '2024-10-31', '95', '95', '3.5'),
	(130, 'Dolagawe Gedara', '96', '2024-10-31', '96', '96', '3.5'),
	(131, 'Mr. Jayasiri', '97', '2024-10-31', '97', '97', '3.5'),
	(132, 'Mr. Upul Indika', '98', '2024-10-31', '98', '98', '3.5'),
	(133, 'Mr. Botheju', '99', '2024-10-31', '99', '99', '3.5'),
	(134, 'Ms. Anula Akka', '100', '2024-10-31', '100', '100', '3.5'),
	(135, 'Ms. Amara', '101', '2024-10-31', '11', '11', '3.5'),
	(136, 'Mr. M.A. Munasinha', '102', '2024-10-31', '102', '102', '3.5'),
	(137, 'Mr. Darmasiri', '103', '2024-10-31', '13', '13', '3.5'),
	(138, 'Ms. Chandra Gamage', '104', '2024-10-31', '10', '10', '3.5'),
	(139, 'Ms. H.K. Somawathi', '105', '2024-10-31', '15', '15', '3.5'),
	(140, 'Mr. Maddu - 02', '107', '2024-10-31', '70', '70', '3.5'),
	(141, 'Mr. Somapala', '108', '2024-10-31', '18', '18', '3.5'),
	(142, 'Mr. H.H. Semi', '110', '2024-10-31', '15', '15', '3.5'),
	(143, 'Mr. B.L. Sumith', '112', '2024-10-31', '112', '112', '3.5'),
	(144, 'Mr. Ariyapala', '113', '2024-10-31', '41', '41', '3.5'),
	(145, 'Mr. Harsh', '114', '2024-10-31', '41', '41', '3.5'),
	(146, 'Mr. S.K. Pradeep', '115', '2024-10-31', '51', '51', '3.5'),
	(147, 'Ms. W.A. Soma', '116', '2024-10-31', '116', '116', '3.5'),
	(148, 'Mr. Idunil', '117', '2024-10-31', '17', '17', '3.5'),
	(149, 'Mr. Channa Jayasiri', '118', '2024-10-31', '81', '81', '3.5'),
	(150, 'Mr. Kiri Aiya - 02', '119', '2024-10-31', '31', '31', '3.5'),
	(151, 'Mr. Samantha', '120', '2024-10-31', '12', '12', '3.5'),
	(152, 'Mr. 31 Aiya', '121', '2024-10-31', '121', '121', '3.5'),
	(153, 'Ms. Chandralatha', '122', '2024-10-31', '22', '22', '3.5'),
	(154, 'Mr. B.L. Santha', '123', '2024-10-31', '23', '23', '3.5'),
	(155, 'Mr. M. Nandasena', '124', '2024-10-31', '24', '24', '3.5'),
	(156, 'Mr. Siril', '125', '2024-10-31', '25', '25', '3.5'),
	(157, 'Mr. Nuwan Chamara', '126', '2024-10-31', '25', '25', '3.5'),
	(158, 'Mr. S.D. Upali', '128', '2024-10-31', '28', '28', '3.5'),
	(159, 'Ms. Chandrani', '129', '2024-10-31', '29', '29', '3.5'),
	(160, 'Mr. R.D. Premadasa', '130', '2024-10-31', '30', '30', '3.5'),
	(161, 'Ms. Lalitha', '131', '2024-10-31', '31', '31', '3.5'),
	(162, 'Mr. I.G. Samarawickrama', '132', '2024-10-31', '32', '32', '3.5'),
	(163, 'Ms. Damayanthi', '133', '2024-10-31', '33', '33', '3.5'),
	(164, 'Mr. S.K. Ralahami', '134', '2024-10-31', '34', '34', '3.5'),
	(165, 'Mr. Anil', '136', '2024-10-31', '36', '36', '3.5'),
	(166, 'Ms. M.G. Dayawathi', '139', '2024-10-31', '39', '39', '3.5'),
	(167, 'Ms. Amarawathi', '141', '2024-10-31', '41', '41', '3.5'),
	(168, 'Mr. W. Piyasena', '142', '2024-10-31', '42', '42', '3.5'),
	(169, 'Mr. L. Bandu', '143', '2024-10-31', '43', '43', '3.5'),
	(170, 'Mr. B.L. Suneth', '144', '2024-10-31', '144', '144', '3.5'),
	(171, 'Mr. Jagath', '145', '2024-10-31', '145', '145', '3.5'),
	(172, 'Mr. Tharindu', '146', '2024-10-31', '146', '146', '3.5'),
	(173, 'Mr. Thilak', '150', '2024-10-31', '150', '150', '3.5'),
	(174, 'Ms. B.S.D. Dahanayaka', '151', '2024-10-31', '151', '151', '3.5'),
	(175, 'Ms. P. Somawathi', '154', '2024-10-31', '45', '45', '3.5'),
	(176, 'Mr. Bebi - 02', '157', '2024-10-31', '51', '51', '3.5'),
	(177, 'Mr. R.W. Wamaranayaka', '158', '2024-10-31', '58', '58', '3.5'),
	(178, 'Ms. K.G. Nilmini', '159', '2024-10-31', '55', '55', '3.5');

-- Dumping structure for table tea_system.debits
CREATE TABLE IF NOT EXISTS `debits` (
  `id` int NOT NULL AUTO_INCREMENT,
  `supplier_name` varchar(100) DEFAULT NULL,
  `supplier_id` varchar(10) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `price` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table tea_system.debits: ~0 rows (approximately)
INSERT INTO `debits` (`id`, `supplier_name`, `supplier_id`, `date`, `price`) VALUES
	(1, 'Mr. M.G. Jayasekara', '12', '2024-10-07', '200'),
	(2, 'Mr. J. Karolis', '2', '2024-10-31', '200'),
	(3, 'Mr. 31 Aiya', '121', '2024-10-31', '200'),
	(4, 'Mr. Peti Mahatha', '31', '2024-10-31', '3000'),
	(5, 'Mr. Jayasiri', '97', '2024-10-31', '99'),
	(6, 'Dolagawe Gedara', '96', '2024-10-31', '9000'),
	(7, 'Ms. Amarawathi', '141', '2024-10-31', '1500');

-- Dumping structure for table tea_system.dolomite
CREATE TABLE IF NOT EXISTS `dolomite` (
  `id` int NOT NULL AUTO_INCREMENT,
  `supplier_name` varchar(100) DEFAULT NULL,
  `supplier_id` varchar(10) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `qty` varchar(20) DEFAULT NULL,
  `price` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table tea_system.dolomite: ~2 rows (approximately)
INSERT INTO `dolomite` (`id`, `supplier_name`, `supplier_id`, `date`, `qty`, `price`) VALUES
	(1, 'Mr. Kiri Aiya - 02', '119', '2024-09-17', '15', '120'),
	(2, 'Mr. M.G. Jayasekara', '12', '2024-11-13', '2', '200'),
	(3, 'Mr. J. Karolis', '2', '2024-10-31', '20', '200'),
	(4, 'Mr. Mandi', '20', '2024-10-31', '10', '150'),
	(5, 'Mr. N. Jayawardhana', '11', '2024-10-31', '2', '600'),
	(6, 'Ms. G.G. Sisiliyana', '92', '2024-10-31', '92', '700'),
	(7, 'Mr. B.L. Sumith', '112', '2024-10-31', '12', '20'),
	(8, 'Mr. 31 Aiya', '121', '2024-10-31', '21', '700');

-- Dumping structure for table tea_system.leaf_rate
CREATE TABLE IF NOT EXISTS `leaf_rate` (
  `id` int NOT NULL AUTO_INCREMENT,
  `leaf_rate` varchar(20) DEFAULT NULL,
  `month_id` int NOT NULL,
  `year_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_leaf_rate_month_idx` (`month_id`),
  KEY `fk_leaf_rate_year1_idx` (`year_id`),
  CONSTRAINT `fk_leaf_rate_month` FOREIGN KEY (`month_id`) REFERENCES `month` (`id`),
  CONSTRAINT `fk_leaf_rate_year1` FOREIGN KEY (`year_id`) REFERENCES `year` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table tea_system.leaf_rate: ~2 rows (approximately)
INSERT INTO `leaf_rate` (`id`, `leaf_rate`, `month_id`, `year_id`) VALUES
	(1, '100', 9, 5),
	(2, '120', 8, 5),
	(3, '120', 10, 5),
	(4, '200', 11, 5);

-- Dumping structure for table tea_system.manure
CREATE TABLE IF NOT EXISTS `manure` (
  `id` int NOT NULL AUTO_INCREMENT,
  `supplier_name` varchar(100) DEFAULT NULL,
  `supplier_id` varchar(10) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `qty` varchar(20) DEFAULT NULL,
  `price` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table tea_system.manure: ~2 rows (approximately)
INSERT INTO `manure` (`id`, `supplier_name`, `supplier_id`, `date`, `qty`, `price`) VALUES
	(1, 'Mr. Kiri Aiya - 02', '119', '2024-09-17', '10', '150'),
	(2, 'Mr. Kiri Aiya - 02', '119', '2024-10-30', '15', '150'),
	(3, 'Mr. Nuwan Madusanka', '169', '2024-10-14', '2', '200'),
	(4, 'Mr. L Sudu Aiya', '30', '2024-10-31', '20', '200'),
	(5, 'Mr. Gamage Ginadasa', '8', '2024-10-31', '20', '300'),
	(6, 'Mr. O.N.D. Dahanayaka', '9', '2024-10-31', '90', '500'),
	(7, 'Mr. R. Amarabandu', '51', '2024-10-31', '2', '250'),
	(8, 'Ms. K.G. Nilmini', '159', '2024-10-31', '5', '500'),
	(9, 'Ms. Chandrani', '129', '2024-10-31', '3', '500'),
	(10, 'Mr. Botheju', '99', '2024-10-31', '25', '500');

-- Dumping structure for table tea_system.month
CREATE TABLE IF NOT EXISTS `month` (
  `id` int NOT NULL,
  `month` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table tea_system.month: ~12 rows (approximately)
INSERT INTO `month` (`id`, `month`) VALUES
	(1, '1 - ජනවාරි'),
	(2, '2 - පෙබරවාරි'),
	(3, '3 - මාර්තු'),
	(4, '4 - අප්‍රේල්'),
	(5, '5 - මැයි'),
	(6, '6 - ජූනි'),
	(7, '7 - ජූලි'),
	(8, '8 - අගෝස්තු'),
	(9, '9 - සැප්තැම්බර්'),
	(10, '10 - ඔක්තෝබර්'),
	(11, '11 - නොවැම්බර්'),
	(12, '12 - දෙසැම්බර්');

-- Dumping structure for table tea_system.suppliers
CREATE TABLE IF NOT EXISTS `suppliers` (
  `id` int NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `address` text,
  `doc_rate` varchar(20) DEFAULT NULL,
  `new_arrears` varchar(40) DEFAULT NULL,
  `arrears` varchar(40) DEFAULT NULL,
  `transport_id` int DEFAULT NULL,
  `last_modify` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table tea_system.suppliers: ~173 rows (approximately)
INSERT INTO `suppliers` (`id`, `name`, `address`, `doc_rate`, `new_arrears`, `arrears`, `transport_id`, `last_modify`) VALUES
	(1, 'Mr. A.D. Dahanayaka', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(2, 'Mr. J. Karolis', 'Diddenipotha', '10', '0', '0', 1, '2024-10-31'),
	(3, 'Mr. M.G. Martin', 'Diddenipotha', '10', '3640.50', '0', 1, '2024-10-16'),
	(4, 'Mr. B.G. Asanka Kumara', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(5, 'Mr. Chathura', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(6, 'Mr. D.S. Rathnayaka', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(7, 'Mr. W.H.G. Premathssa', 'Diddenipotha', '10', '0', '4772.08', 1, '2024-10-31'),
	(8, 'Mr. Gamage Ginadasa', 'Diddenipotha', '10', '3563.50', '0', 1, '2024-10-16'),
	(9, 'Mr. O.N.D. Dahanayaka', 'Diddenipotha', '10', '41748.00', '0', 1, '2024-10-16'),
	(10, 'Ms. J.K. Kusuma', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(11, 'Mr. N. Jayawardhana', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(12, 'Mr. M.G. Jayasekara', 'Diddenipotha', '10', '0', '0', 1, '2024-10-31'),
	(13, 'Mr. Albart', 'Diddenipotha', '10', '0', '0', 1, '2024-10-31'),
	(15, 'Mr. Saman Kumara', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(16, 'Mr. Dayarathna', 'Kadewaththa,\nDiddenipotha', '10', '0', '0', 1, '2024-10-31'),
	(18, 'Mr. Vilsan Jayavardana', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(19, 'Mr. Kalu', 'Balagamawaththa,\nDiddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(20, 'Mr. Mandi', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(21, 'Mr. B.L. Gunapala', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(22, 'Mr. L. Chandrasiri', 'Diddenipotha', '10', '0', '0', 1, '2024-10-31'),
	(23, 'Mr. Sumudu', 'Redimahana gedara,\nDiddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(24, 'Mr. Gamini', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(25, 'Mr. Ekanayaka Iskole', 'Diddenipotha', '10', '0', '0', 1, '2024-10-31'),
	(26, 'Mr. Sunil', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(27, 'Mr. W.A.Sirisena', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(28, 'Mr. W.A. Jayawardana', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(29, 'Mr. S.D. Dahanayaka', 'Diddenipotha', '10', '0', '4886.48', 1, '2024-10-31'),
	(30, 'Mr. L Sudu Aiya', 'Diddenipotha', '10', '515.00', '0', 1, '2024-10-16'),
	(31, 'Mr. Peti Mahatha', 'Oviti Gedara,\nDiddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(32, 'Mr. L.A. Munasinha', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(33, 'Mr. K.K.G. Sirisena', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(34, 'Mr. Vile Peti Aiya', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(35, 'Mr. Piyadasa', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(36, 'Mr. Vijith', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(37, 'Mr. Sirisena', 'Kone Koratuwa,\nDiddenipotha', '10', '0', '12029.40', 1, '2024-10-31'),
	(38, 'Mr. Maddu', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(40, 'Mr. M.G. Jinadasa', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(41, 'Ms. Dahanayaka', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(42, 'Mr. Bebi', 'Dellava hena,\nDiddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(43, 'Mr. Andarayas', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(44, 'Ms. Yasawathi', 'Diddenipotha', '10', '0', '0', 1, '2024-10-31'),
	(45, 'Mr. Mahesha Malli', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(46, 'Mr. Palitha', 'Diddenipotha', '10', '0', '0', 1, '2024-10-31'),
	(47, 'Mr. K.G. Nihal', 'Diddenipotha', '10', '0', '6948.50', 1, '2024-10-31'),
	(48, 'Pelawaththe Gedara', 'Roipura,\nDiddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(49, 'Mr. Ranchagoda Aiya', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(50, 'Mr. Nihal', 'Rabara,\nDiddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(51, 'Mr. R. Amarabandu', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(52, 'Mr. W.P. Weerasinha', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(53, 'Mr. Hinni', 'Diddenipotha', '10', '0', '0', 1, '2024-10-31'),
	(54, 'Mr. Laal Aiya', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(55, 'Mr. W.A. Wimalasiri', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(56, 'Mr. Kumara', 'Roipura,\nDiddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(57, 'Mr. Ranjith', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(58, 'Mr. R.N. Samaranayaka', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(59, 'Mr. Thisara', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(60, 'A-10 Mr. Priyantha', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(62, 'Mr. Jinasiri', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(63, 'Mr. Gunadasa', 'Roipura,\nDiddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(64, 'Mr. S. Rathnayaka', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(65, 'Mr. L. Heri', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(66, 'Mr. Pradeep', 'Diddenipotha', '10', '0', '0', 1, '2024-10-31'),
	(67, 'Mr. Indika', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(68, 'Mr. Ananda Rathnayaka', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(69, 'Mr. K.A. Sarath', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(70, 'Ms. Seetha Rathnayaka', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(71, 'Mr. T. Rathnayaka', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(73, 'Mr. Vakkadapahala hena Ajith', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(74, 'Ms. Chandra Gamage', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(75, 'Mr. Vikramapala', 'Diddenipotha', '10', '0', '302.62', 1, '2024-10-31'),
	(76, 'Mr. Kiri Aiya', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(77, 'Ms. Deepika', 'Roipura,\nDiddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(78, 'Mr. Nandasena', 'Diddenipotha', '10', '0', '0', 1, '2024-10-31'),
	(79, 'Mr. R.K. Premadasa', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(80, 'Ms. Malani', 'Roipura,\nDiddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(81, 'Ms. C. Rathnayaka', 'Pegirihena,\nDiddenipotha', '10', '0', '0', 1, '2024-10-31'),
	(82, 'Ms. R. Malani', 'Pegirihena,\nDiddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(83, 'Mr. Aiyadasa', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(85, 'Ms. Anurada', 'Roipura,\nDiddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(86, 'Mr. L. Pradeep', 'Diddenipotha', '10', '0', '48049.28', 1, '2024-10-31'),
	(87, 'Mr. K.A.L. Priyantha', 'Diddenipotha', '10', '0', '79.18', 1, '2024-10-31'),
	(88, 'Mr. Chuti', 'Galagawa,\nDiddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(91, 'Mr. P. Vijethunga', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(92, 'Ms. G.G. Sisiliyana', 'Diddenipotha', '10', '53692.00', '0', 1, '2024-10-16'),
	(93, 'Ms. L. Virasinha', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(94, 'Mr. Thennahena Ajith', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(95, 'Ms. K. Rathnayaka', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(96, 'Dolagawe Gedara', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(97, 'Mr. Jayasiri', 'Roipura,\nDiddenipotha', '10', '0', '1447.00', 1, '2024-10-31'),
	(98, 'Mr. Upul Indika', 'Diddenipotha', '10', '0', '0', 1, '2024-10-31'),
	(99, 'Mr. Botheju', 'Diddenipotha', '10', '9976.50', '0', 1, '2024-10-16'),
	(100, 'Ms. Anula Akka', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(101, 'Ms. Amara', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(102, 'Mr. M.A. Munasinha', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(103, 'Mr. Darmasiri', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(104, 'Ms. Chandra Gamage', 'Valagawaththa,\nDiddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(105, 'Ms. H.K. Somawathi', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(107, 'Mr. Maddu - 02', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(108, 'Mr. Somapala', 'Diddenipotha', '10', '0', '0', 1, '2024-10-31'),
	(110, 'Mr. H.H. Semi', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(112, 'Mr. B.L. Sumith', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(113, 'Mr. Ariyapala', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(114, 'Mr. Harsh', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(115, 'Mr. S.K. Pradeep', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(116, 'Ms. W.A. Soma', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(117, 'Mr. Idunil', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(118, 'Mr. Channa Jayasiri', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(119, 'Mr. Kiri Aiya - 02', 'Diddenipotha', '10', '0', '470.18', 1, '2024-10-31'),
	(120, 'Mr. Samantha', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(121, 'Mr. 31 Aiya', 'Diddenipotha', '10', '0', '0', 1, '2024-10-31'),
	(122, 'Ms. Chandralatha', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(123, 'Mr. B.L. Santha', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(124, 'Mr. M. Nandasena', 'Diddenipotha', '10', '0', '0', 1, '2024-10-31'),
	(125, 'Mr. Siril', 'Diddenipotha', '10', '0', '641.62', 1, '2024-10-31'),
	(126, 'Mr. Nuwan Chamara', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(128, 'Mr. S.D. Upali', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(129, 'Ms. Chandrani', 'Diddenipotha', '10', '0', '11231.55', 1, '2024-10-31'),
	(130, 'Mr. R.D. Premadasa', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(131, 'Ms. Lalitha', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(132, 'Mr. I.G. Samarawickrama', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(133, 'Ms. Damayanthi', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(134, 'Mr. S.K. Ralahami', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(136, 'Mr. Anil', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(139, 'Ms. M.G. Dayawathi', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(141, 'Ms. Amarawathi', 'Diddenipotha', '10', '0', '18985.33', 1, '2024-10-31'),
	(142, 'Mr. W. Piyasena', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(143, 'Mr. L. Bandu', 'Gindaalawaththa,\nDiddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(144, 'Mr. B.L. Suneth', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(145, 'Mr. Jagath', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(146, 'Mr. Tharindu', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(150, 'Mr. Thilak', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(151, 'Ms. B.S.D. Dahanayaka', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(154, 'Ms. P. Somawathi', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(157, 'Mr. Bebi - 02', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(158, 'Mr. R.W. Wamaranayaka', 'Diddenipotha', '10', '0', '0', 1, '2024-10-31'),
	(159, 'Ms. K.G. Nilmini', 'Diddenipotha', '10', '0', '0', 1, '2024-10-16'),
	(160, 'Mr. B.A. Sirisena', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(161, 'Mr. Sumudu - 02', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(164, 'Mr. S. Jayawardana', 'Diddenipotha', '10', '0', '0', 1, '2024-10-31'),
	(165, 'Ms. W.A. Shriyani', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(166, 'Mr. Jina Aiya', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(167, 'Mr. R.L. Resan', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(168, 'Mr. Tharaka', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(169, 'Mr. Nuwan Madusanka', 'Miris Hena,\nDiddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(171, 'Ms. S.K. Ranjani', 'Diddenipotha', '10', '0', '0', 1, '2024-10-31'),
	(172, 'Mr. Tharindu', 'Vidana gedara,\nDiddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(173, 'Mr. B.L. Weerasinha', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(174, 'Mr. Rimal', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(175, 'Mr. W.W. Samaranayaka', 'Diddenipotha', '10', '0', '0', 1, '2024-10-31'),
	(176, 'Bodiyanganaramaya', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(177, 'Mr. Darshana', 'Valagawaththa,\nDiddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(178, 'Mr. S.D. Sirisena', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(179, 'Ms. Dilrukshi', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(180, 'Ms. M.G. Mallika', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(181, 'Ms. Sagarika Rathnayaka', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(182, 'Mr. Sarath', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(183, 'Ms. S.K. Punchi Hami', 'Diddenipotha', '10', '0', '0', 1, '2024-10-31'),
	(184, 'Mr. M.G. Gemunu', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(185, 'Mr. Kasun Vijethunga', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(186, 'Ms. V. Premawathi', 'Diddenipotha', '10', '0', '0', 1, '2024-10-31'),
	(187, 'Mr. Sudhira Rathnayaka', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(188, 'Mr. A. Samaranayaka', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(189, 'Mr. Thusitha Munasinha', 'Diddenipotha', '10', '0', '0', 1, '2024-10-31'),
	(190, 'Mr. Wasantha Dayas Dahanayaka', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(191, 'Mr. W.A. Ranjith', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(192, 'Mr. W.H.G. Mdusanka', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(193, 'Mr. Y.S. Amarasiri', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(194, 'Mr. Thushara', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(195, 'Mr. Sadaruwan', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(196, 'Ms. Nishanthi', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(197, 'Mr. Narada Himi', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(198, 'Mr. M.T. Piyumal', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30'),
	(200, 'Ms. Sanuki Savinija Rathnayaka', 'Diddenipotha', '10', '0', '0', 1, '2024-09-30');

-- Dumping structure for table tea_system.tea
CREATE TABLE IF NOT EXISTS `tea` (
  `id` int NOT NULL AUTO_INCREMENT,
  `supplier_name` varchar(100) DEFAULT NULL,
  `supplier_id` varchar(10) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `qty` varchar(20) DEFAULT NULL,
  `price` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table tea_system.tea: ~4 rows (approximately)
INSERT INTO `tea` (`id`, `supplier_name`, `supplier_id`, `date`, `qty`, `price`) VALUES
	(1, 'Mr. Kiri Aiya - 02', '119', '2024-09-18', '20', '100'),
	(2, 'Mr. Jayasiri', '97', '2024-10-14', '20', '200'),
	(3, 'Mr. Ekanayaka Iskole', '25', '2024-10-30', '5', '120'),
	(4, 'Ms. J.K. Kusuma', '10', '2024-10-30', '2', '3'),
	(5, 'Mr. M.G. Martin', '3', '2024-10-31', '20', '300'),
	(6, 'Mr. W.A. Wimalasiri', '55', '2024-10-31', '1', '200'),
	(7, 'A-10 Mr. Priyantha', '60', '2024-10-31', '2', '200'),
	(8, 'Ms. Malani', '80', '2024-10-31', '2', '200'),
	(9, 'Ms. Anula Akka', '100', '2024-10-31', '3', '200'),
	(10, 'Mr. Thilak', '150', '2024-10-31', '5', '200');

-- Dumping structure for table tea_system.transport
CREATE TABLE IF NOT EXISTS `transport` (
  `id` int NOT NULL AUTO_INCREMENT,
  `root_id` varchar(10) DEFAULT NULL,
  `road_name` varchar(100) DEFAULT NULL,
  `transport_rate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table tea_system.transport: ~1 rows (approximately)
INSERT INTO `transport` (`id`, `root_id`, `road_name`, `transport_rate`) VALUES
	(1, '1', 'Diddenipotha', '3.5'),
	(2, '20', 'Road', '2.5');

-- Dumping structure for table tea_system.year
CREATE TABLE IF NOT EXISTS `year` (
  `id` int NOT NULL AUTO_INCREMENT,
  `year` year DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table tea_system.year: ~11 rows (approximately)
INSERT INTO `year` (`id`, `year`) VALUES
	(1, '2020'),
	(2, '2021'),
	(3, '2022'),
	(4, '2023'),
	(5, '2024'),
	(6, '2025'),
	(7, '2026'),
	(8, '2027'),
	(9, '2028'),
	(10, '2029'),
	(11, '2030');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
