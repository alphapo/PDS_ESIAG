-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 02, 2016 at 01:47 AM
-- Server version: 5.5.46-0ubuntu0.14.04.2
-- PHP Version: 5.5.9-1ubuntu4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `test_pds`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE IF NOT EXISTS `account` (
  `id_account` int(11) NOT NULL AUTO_INCREMENT,
  `id_agency` int(11) DEFAULT NULL,
  `id_loan` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_account`),
  KEY `FK_id_agence_idx` (`id_agency`),
  KEY `fk_id_pret_idx` (`id_loan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `agency`
--

CREATE TABLE IF NOT EXISTS `agency` (
  `id_agency` int(11) NOT NULL,
  `city` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_agency`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `agency`
--

INSERT INTO `agency` (`id_agency`, `city`, `country`) VALUES
(1, 'Paris', 'France'),
(2, 'Lyon', 'France');

-- --------------------------------------------------------

--
-- Table structure for table `consumer`
--

CREATE TABLE IF NOT EXISTS `consumer` (
  `id_consumer` int(11) NOT NULL,
  `firstname` varchar(45) COLLATE utf8_bin NOT NULL,
  `lastname` varchar(45) COLLATE utf8_bin NOT NULL,
  `id_account` int(11) NOT NULL,
  `id_agency` int(11) NOT NULL,
  `id_employees` varchar(45) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id_consumer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `consumer`
--

INSERT INTO `consumer` (`id_consumer`, `firstname`, `lastname`, `id_account`, `id_agency`, `id_employees`) VALUES
(1, 'Laurent', 'Durand', 123456789, 1, '01');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE IF NOT EXISTS `employees` (
  `id_employees` int(11) NOT NULL,
  `id_agency` int(11) NOT NULL,
  `firstname` varchar(45) COLLATE utf8_bin NOT NULL,
  `lastname` varchar(45) COLLATE utf8_bin NOT NULL,
  `salary` varchar(45) COLLATE utf8_bin NOT NULL,
  `status` varchar(45) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id_employees`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`id_employees`, `id_agency`, `firstname`, `lastname`, `salary`, `status`) VALUES
(1, 1, 'Martin', 'Clemente', '2500', 'EmployÃ©');

-- --------------------------------------------------------

--
-- Table structure for table `loan`
--

CREATE TABLE IF NOT EXISTS `loan` (
  `id_loan` int(11) NOT NULL AUTO_INCREMENT,
  `id_simulation` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_loan`),
  KEY `fk_id_simulation_idx` (`id_simulation`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `loantype`
--

CREATE TABLE IF NOT EXISTS `loantype` (
  `id_loanType` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `id_rate` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_loanType`),
  KEY `FK_id_taux_idx` (`id_rate`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `rate`
--

CREATE TABLE IF NOT EXISTS `rate` (
  `id_rate` int(11) NOT NULL AUTO_INCREMENT,
  `Type` varchar(45) DEFAULT NULL,
  `rate` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_rate`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `rate`
--

INSERT INTO `rate` (`id_rate`, `Type`, `rate`) VALUES
(1, 'Logement', '0.2');

-- --------------------------------------------------------

--
-- Table structure for table `simulation`
--

CREATE TABLE IF NOT EXISTS `simulation` (
  `id_simulation` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) DEFAULT NULL,
  `amount` varchar(45) DEFAULT NULL,
  `id_loanType` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_simulation`),
  KEY `FK_id_loanType_idx` (`id_loanType`),
  KEY `fk_id_user_idx` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id_user` int(11) NOT NULL,
  `login` varchar(45) COLLATE utf8_bin NOT NULL,
  `password` varchar(45) COLLATE utf8_bin NOT NULL,
  `id_consumer` int(11) NOT NULL,
  `id_employees` int(11) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `login`, `password`, `id_consumer`, `id_employees`) VALUES
(123456789, 'Ldurand', 'Azerty123', 1234, 4321),
(987654321, 'Probert', 'Ytreza321', 9876, 6789);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `FK_id_agence` FOREIGN KEY (`id_agency`) REFERENCES `agency` (`id_agency`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_id_pret` FOREIGN KEY (`id_loan`) REFERENCES `loan` (`id_loan`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `loan`
--
ALTER TABLE `loan`
  ADD CONSTRAINT `fk_id_simulation` FOREIGN KEY (`id_simulation`) REFERENCES `simulation` (`id_simulation`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `loantype`
--
ALTER TABLE `loantype`
  ADD CONSTRAINT `FK_id_taux` FOREIGN KEY (`id_rate`) REFERENCES `rate` (`id_rate`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `simulation`
--
ALTER TABLE `simulation`
  ADD CONSTRAINT `FK_id_loanType` FOREIGN KEY (`id_loanType`) REFERENCES `loantype` (`id_loanType`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_id_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
