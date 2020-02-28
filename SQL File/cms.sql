-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Feb 28, 2020 at 07:11 AM
-- Server version: 5.7.26
-- PHP Version: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cms`
--
CREATE DATABASE IF NOT EXISTS `cms` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `cms`;

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
CREATE TABLE IF NOT EXISTS `event` (
  `eId` int(11) NOT NULL,
  `eEventName` varchar(20) NOT NULL,
  `eDescription` varchar(100) NOT NULL,
  `eTime` time NOT NULL,
  `eDate` date NOT NULL,
  `ePrizeMoney` int(20) NOT NULL,
  `eGoodies` varchar(20) NOT NULL,
  `eVouchers` varchar(50) NOT NULL,
  PRIMARY KEY (`eId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `judge`
--

DROP TABLE IF EXISTS `judge`;
CREATE TABLE IF NOT EXISTS `judge` (
  `jId` int(11) NOT NULL,
  `jFirstName` varchar(20) NOT NULL,
  `jLastName` varchar(20) NOT NULL,
  `jPhone` bigint(20) NOT NULL,
  `jEmail` varchar(50) NOT NULL,
  `jSpecialization` varchar(50) NOT NULL,
  PRIMARY KEY (`jId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `judges`
--

DROP TABLE IF EXISTS `judges`;
CREATE TABLE IF NOT EXISTS `judges` (
  `eId` int(20) NOT NULL,
  `jId` int(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `organizer`
--

DROP TABLE IF EXISTS `organizer`;
CREATE TABLE IF NOT EXISTS `organizer` (
  `oId` int(11) NOT NULL,
  `oFirstName` varchar(20) NOT NULL,
  `oLastName` varchar(20) NOT NULL,
  `oPhone` bigint(20) NOT NULL,
  `oEmail` varchar(50) NOT NULL,
  `oPassword` varchar(20) NOT NULL,
  PRIMARY KEY (`oId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `participant`
--

DROP TABLE IF EXISTS `participant`;
CREATE TABLE IF NOT EXISTS `participant` (
  `pId` int(11) NOT NULL,
  `pFirstName` varchar(20) NOT NULL,
  `pLastName` varchar(20) NOT NULL,
  `pEmail` varchar(50) NOT NULL,
  `pPhone` bigint(20) NOT NULL,
  `pCollege` varchar(50) NOT NULL,
  `pPassword` varchar(20) NOT NULL,
  PRIMARY KEY (`pId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `participantevents`
--

DROP TABLE IF EXISTS `participantevents`;
CREATE TABLE IF NOT EXISTS `participantevents` (
  `pId` int(11) NOT NULL,
  `eId` int(11) NOT NULL,
  `eScore` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sponsor`
--

DROP TABLE IF EXISTS `sponsor`;
CREATE TABLE IF NOT EXISTS `sponsor` (
  `sId` int(11) NOT NULL,
  `sName` varchar(20) NOT NULL,
  PRIMARY KEY (`sId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sponsors`
--

DROP TABLE IF EXISTS `sponsors`;
CREATE TABLE IF NOT EXISTS `sponsors` (
  `eId` int(20) NOT NULL,
  `sId` int(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `winner`
--

DROP TABLE IF EXISTS `winner`;
CREATE TABLE IF NOT EXISTS `winner` (
  `eId` int(11) NOT NULL,
  `pIdWon` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
