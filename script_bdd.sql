-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Dim 03 Avril 2016 à 15:41
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `test_pds`
--

-- --------------------------------------------------------

--
-- Structure de la table `account`
--

CREATE TABLE IF NOT EXISTS `account` (
  `id_account` int(11) NOT NULL AUTO_INCREMENT,
  `capital` varchar(45) NOT NULL,
  `id_Consumer` int(11) DEFAULT NULL,
  `id_loan` int(11) NOT NULL,
  PRIMARY KEY (`id_account`,`id_loan`),
  KEY `fk_Account_loan1_idx` (`id_loan`),
  KEY `fk_Account_Id` (`id_Consumer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `agency`
--

CREATE TABLE IF NOT EXISTS `agency` (
  `id_Agency` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  PRIMARY KEY (`id_Agency`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `agency`
--

INSERT INTO `agency` (`id_Agency`, `city`, `country`) VALUES
(1, 'Lyon', 'France'),
(2, 'Paris', 'France');

-- --------------------------------------------------------

--
-- Structure de la table `consumer`
--

CREATE TABLE IF NOT EXISTS `consumer` (
  `id_Consumer` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `firstname` varchar(45) NOT NULL,
  `dateOfBirth` date NOT NULL,
  `address` varchar(45) NOT NULL,
  `postCode` varchar(5) NOT NULL,
  `country` varchar(45) DEFAULT NULL,
  `phoneNumber` varchar(10) NOT NULL,
  `City` varchar(45) NOT NULL,
  `id_Agency` int(11) NOT NULL,
  `nationality` varchar(45) NOT NULL,
  PRIMARY KEY (`id_Consumer`,`id_Agency`),
  KEY `fk_Consumers_Agency1_idx` (`id_Agency`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `consumer`
--

INSERT INTO `consumer` (`id_Consumer`, `name`, `firstname`, `dateOfBirth`, `address`, `postCode`, `country`, `phoneNumber`, `City`, `id_Agency`, `nationality`) VALUES
(1, '', '', '1991-12-12', '', '', '', '', '', 2, '');

-- --------------------------------------------------------

--
-- Structure de la table `historique`
--

CREATE TABLE IF NOT EXISTS `historique` (
  `id_Historique` int(11) NOT NULL AUTO_INCREMENT,
  `id_Simulation` int(11) NOT NULL,
  PRIMARY KEY (`id_Historique`,`id_Simulation`),
  KEY `fk_Historique_Simulation1_idx` (`id_Simulation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `insurance`
--

CREATE TABLE IF NOT EXISTS `insurance` (
  `id_Insurance` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `rate` mediumtext NOT NULL,
  PRIMARY KEY (`id_Insurance`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `loan`
--

CREATE TABLE IF NOT EXISTS `loan` (
  `id_loan` int(11) NOT NULL AUTO_INCREMENT,
  `id_Simulation` int(11) NOT NULL,
  `id_loanType` int(11) NOT NULL,
  PRIMARY KEY (`id_loan`,`id_Simulation`,`id_loanType`),
  KEY `fk_loan_Simulation1_idx` (`id_Simulation`),
  KEY `fk_loan_LoanType1_idx` (`id_loanType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `loantype`
--

CREATE TABLE IF NOT EXISTS `loantype` (
  `id_loanType` int(11) NOT NULL AUTO_INCREMENT,
  `id_Insurance` int(11) NOT NULL,
  PRIMARY KEY (`id_loanType`,`id_Insurance`),
  KEY `fk_LoanType_Insurance1_idx` (`id_Insurance`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `simulation`
--

CREATE TABLE IF NOT EXISTS `simulation` (
  `id_Simulation` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `monthly` varchar(45) NOT NULL,
  `simulationDate` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `duration` varchar(45) NOT NULL,
  `amount` varchar(45) NOT NULL,
  PRIMARY KEY (`id_Simulation`,`id_user`),
  KEY `fk_Simulation_user1_idx` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `staff`
--

CREATE TABLE IF NOT EXISTS `staff` (
  `id_staff` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_Agency` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `salary` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_staff`,`id_user`,`id_Agency`),
  KEY `fk_Staff_user1_idx` (`id_user`),
  KEY `fk_Staff_Agency1_idx` (`id_Agency`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `id_Consumer` int(11) NOT NULL,
  PRIMARY KEY (`id_user`,`id_Consumer`),
  KEY `fk_user_Consumer1_idx` (`id_Consumer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `fk_Account_Id` FOREIGN KEY (`id_Consumer`) REFERENCES `consumer` (`id_Consumer`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Account_loan1` FOREIGN KEY (`id_loan`) REFERENCES `loan` (`id_loan`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `consumer`
--
ALTER TABLE `consumer`
  ADD CONSTRAINT `fk_Consumers_Agency1` FOREIGN KEY (`id_Agency`) REFERENCES `agency` (`id_Agency`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `historique`
--
ALTER TABLE `historique`
  ADD CONSTRAINT `fk_Historique_Simulation1` FOREIGN KEY (`id_Simulation`) REFERENCES `simulation` (`id_Simulation`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `loan`
--
ALTER TABLE `loan`
  ADD CONSTRAINT `fk_loan_Simulation1` FOREIGN KEY (`id_Simulation`) REFERENCES `simulation` (`id_Simulation`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_loan_LoanType1` FOREIGN KEY (`id_loanType`) REFERENCES `loantype` (`id_loanType`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `loantype`
--
ALTER TABLE `loantype`
  ADD CONSTRAINT `fk_LoanType_Insurance1` FOREIGN KEY (`id_Insurance`) REFERENCES `insurance` (`id_Insurance`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `simulation`
--
ALTER TABLE `simulation`
  ADD CONSTRAINT `fk_Simulation_user1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `staff`
--
ALTER TABLE `staff`
  ADD CONSTRAINT `fk_Staff_user1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Staff_Agency1` FOREIGN KEY (`id_Agency`) REFERENCES `agency` (`id_Agency`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_user_Consumer1` FOREIGN KEY (`id_Consumer`) REFERENCES `consumer` (`id_Consumer`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
