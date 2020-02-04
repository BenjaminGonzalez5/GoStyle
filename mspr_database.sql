-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  mar. 04 fév. 2020 à 17:06
-- Version du serveur :  10.1.30-MariaDB
-- Version de PHP :  7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `mspr_database`
--

-- --------------------------------------------------------

--
-- Structure de la table `coupon`
--

CREATE TABLE `coupon` (
  `code` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `coupon`
--

INSERT INTO `coupon` (`code`, `description`) VALUES
('098ZER12', 'Coupon chaussette -10%'),
('123QSD1', 'Coupon pantalon -50%'),
('12AZ4', 'Coupon Bonnet -20% '),
('256ERTD5', 'Coupon Short -20%');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `email`, `password`) VALUES
(1, 'hajar.pro@hotmail.com', 'azerty'),
(2, 'benjamin.gonzalez@epsi.fr', '123'),
(3, 'matthieu.rodrigues@epsi.fr', 'AZERTY'),
(4, 'thibault.nesti@epsi.fr', '123456');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur_coupon`
--

CREATE TABLE `utilisateur_coupon` (
  `id` int(11) NOT NULL,
  `code` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `iduser` int(11) NOT NULL,
  `idcoupon` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `coupon`
--
ALTER TABLE `coupon`
  ADD PRIMARY KEY (`code`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `utilisateur_coupon`
--
ALTER TABLE `utilisateur_coupon`
  ADD PRIMARY KEY (`id`,`code`),
  ADD KEY `code` (`code`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `utilisateur_coupon`
--
ALTER TABLE `utilisateur_coupon`
  ADD CONSTRAINT `utilisateur_coupon_ibfk_1` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `utilisateur_coupon_ibfk_2` FOREIGN KEY (`code`) REFERENCES `coupon` (`code`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
