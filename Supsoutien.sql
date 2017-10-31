-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:8889
-- Généré le :  Sam 20 Mai 2017 à 16:57
-- Version du serveur :  5.6.33
-- Version de PHP :  5.6.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `Supsoutien`
--

-- --------------------------------------------------------

--
-- Structure de la table `creneau_matiere_coach`
--

CREATE TABLE `creneau_matiere_coach` (
  `id` bigint(20) NOT NULL,
  `commentaire` longtext,
  `date_heure_debut_soutien` datetime DEFAULT NULL,
  `date_heure_fin_soutien` datetime DEFAULT NULL,
  `date_heure_validation_coach` datetime DEFAULT NULL,
  `date_validation_staff` datetime DEFAULT NULL,
  `valider_par_coach_fin_session` int(11) DEFAULT NULL,
  `valider_par_staff` int(11) DEFAULT NULL,
  `coach_id` bigint(20) DEFAULT NULL,
  `matiere_id` bigint(20) NOT NULL,
  `staff_id` bigint(20) DEFAULT NULL,
  `nb_inscrits` int(1) NOT NULL DEFAULT '0',
  `nb_presents` int(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `demande_soutien`
--

CREATE TABLE `demande_soutien` (
  `id` bigint(20) NOT NULL,
  `etudiant_id` bigint(20) NOT NULL,
  `coach_id` bigint(20) DEFAULT NULL,
  `matiere_id` bigint(20) NOT NULL,
  `etat_demande` tinyint(1) NOT NULL COMMENT '0=en cours, 1=traitee'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `inscription_etudiant_creneau`
--

CREATE TABLE `inscription_etudiant_creneau` (
  `id` bigint(20) NOT NULL,
  `confirm_mail_send_by_etudiant` bit(1) DEFAULT NULL,
  `confirm_par_coach` int(11) DEFAULT NULL,
  `contenu_mail_coach` longtext,
  `contenu_mail_etudiant` longtext,
  `date_heure_inscription` datetime DEFAULT NULL,
  `creneau_id` bigint(20) NOT NULL,
  `etudiant_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

CREATE TABLE `matiere` (
  `id` bigint(20) NOT NULL,
  `code_matiere` varchar(255) NOT NULL,
  `lib_matiere` varchar(255) DEFAULT NULL,
  `promotion_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `matiere`
--

INSERT INTO `matiere` (`id`, `code_matiere`, `lib_matiere`, `promotion_id`) VALUES
(1, '1ADS', 'Algorithmic in Python', 1),
(2, '1ARI', 'Arithmetic and Cryptography', 1),
(3, '1CNA', 'CCNA Exploration - Modules 1  & 2-part1 et 2', 1),
(4, '1CPA', 'Computer Architecture', 1),
(5, '1ENG', 'English Debates', 1),
(6, '1GCC', 'C Language', 1),
(7, '1SET', 'Set Theory', 1),
(8, '1LAL', 'Linear Algebra', 1),
(9, '1LAW', 'IT Law - Internet Law and Intellectual Property', 1),
(10, '1LIN', 'Linux Technologies - System Fundamentals', 1),
(11, '1MER', 'Merise modeling for databases', 1),
(12, '1MGT', 'Enter the digital world', 1),
(13, '1MSA', 'Windows Server 2012 Introduction', 1),
(14, '1ORC', 'SQL Fundamentals', 1),
(15, '1OSS', 'Operating Systems Fundamental', 1),
(16, '1SEC', 'Information System Security', 1),
(17, '1WEB', 'HTML & JavaScript - User Interface', 1),
(18, '2ADS', 'Advanced Algorithmics', 2),
(19, '2CMP', 'Compilation - Languages and translators', 2),
(20, '2CNB', 'CCNA Routing & Switching Part 2   (1/2)', 2),
(21, '2MSA', 'Microsoft Windows 2012 Administration', 2),
(22, '2ENG', 'English Debates', 2),
(23, '2GRA', 'Graphs theory', 2),
(24, '2JVA', 'Java Standard Edition', 2),
(25, '2JWB', 'Web Strategy', 2),
(26, '2LAW', 'IT Law -Network Administration and Fraud', 2),
(27, '2LIN', 'Linux Technologies - Edge computing', 2),
(28, '2MGT', 'Modeling for Business Analysis', 2),
(29, '2OOP', 'Object Oriented Programming', 2),
(30, '2ORC', 'PL/SQL Fundamentals', 2),
(31, '2CPP', 'C++ Language', 2),
(32, '2NET', 'Microsoft .NET Foundations and Enterprise Applications', 2),
(33, '2PBS', 'Probabilities & Statistics', 2),
(34, '2UML', 'UML', 2),
(35, '2WEB', 'Web programming with PHP', 2),
(36, '3AIT', 'Artificial Intelligence - Functional programming', 3),
(37, '3AND', 'Android Application Dev & Programming', 3),
(38, '3APL', 'Swift and Cocoa Development', 3),
(39, '3ASP', 'Building Web Applications with ASP.NET MVC ', 3),
(40, '3CNS', 'CCNA Security', 3),
(41, '3ENG', 'English Debates', 3),
(42, '3JVA', 'Enterprise Application Development', 3),
(43, '3LAW', 'Labour Law and IT', 3),
(44, '3LIN', 'Data Center Solutions', 3),
(45, '3MET', 'ITIL Foundations', 3),
(46, '3MGT', 'IT Management 3 - Project Management', 3),
(47, '3MSA', 'Windows Server 2012 Active Directory Domain Services', 3),
(48, '3ORC', 'Oracle Dabatabase Administration part1 et 2', 3),
(49, '3WEB', 'Advanced Web Programming with NodeJS', 3),
(50, '3WIN', 'Microsoft Windows Universal Applications Development', 3),
(51, '4BIS', 'BI fundamentals', 4),
(52, '4CLD', 'Introduction to Cloud Computing Technologies Training', 4),
(53, '4AIT', 'Artificial Intelligence - Logic Programming', 4),
(54, '4ENG', 'English Debates', 4),
(55, '4EPS', 'Digital Enterpreneurship', 4),
(56, '4ERP', 'ERP Solution', 4),
(57, '4JVA', 'Java EE - Enterprise programming', 4),
(58, '4LAW', 'IT Law- Personal Data Protection', 4),
(59, '4MET', 'Agile Software Development with Scrum', 4),
(60, '4MGT', 'Finance & Accounting', 4),
(61, '4MOS', 'Microsoft SharePoint 2013', 4),
(62, '4MSE', 'Planning, deploying , managing Exchange Server 2013', 4),
(63, '4VIP', 'VoIP', 4),
(64, '4VTZ', 'Deploying Vmware vSphere', 4),
(65, '5BCP', 'Disaster Recovery Planning : Ensuring Business Continuity', 5),
(66, '5BIS', 'BI Solutions', 5),
(67, '5DAT', 'Big Data Fundamentals', 5),
(68, '5EMI', 'Emotional Intelligence : Achieving Leadership Success', 5),
(69, '5ENG', 'English Debates', 5),
(70, '5LAW', 'IT Contract Law', 5),
(71, '5VTZ', 'Application Virtualization', 5),
(72, '5MGT', 'IT Management 5 / IT Performance', 5),
(73, '5ORC', 'Datawarehouse', 5),
(74, '5MET', 'CobiT 5 Foundation Training', 5),
(75, '5TGF', 'Preparing for TOGAF Accreditation', 5);

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

CREATE TABLE `promotion` (
  `id` int(11) NOT NULL,
  `code_promotion` varchar(10) NOT NULL,
  `lib_promotion` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `promotion`
--

INSERT INTO `promotion` (`id`, `code_promotion`, `lib_promotion`) VALUES
(1, 'Asc 1', 'Premiere annee'),
(2, 'Asc 2', 'Deuxieme annee'),
(3, 'Bsc', 'Troisieme annee'),
(4, 'Msc 1', 'Quatrieme annee'),
(5, 'Msc 2', 'Cinquieme annee');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `lib_role` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `role`
--

INSERT INTO `role` (`id`, `lib_role`) VALUES
(1, 'ROLE_ADMIN'),
(3, 'ROLE_COACH'),
(2, 'ROLE_ETUDIANT'),
(4, 'ROLE_STAFF');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `date_creation` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `etat` bit(1) DEFAULT NULL,
  `id_booster` bigint(20) DEFAULT NULL,
  `mot_passe` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `promotion_id` int(11) DEFAULT NULL,
  `role_lib` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `date_creation`, `email`, `etat`, `id_booster`, `mot_passe`, `nom`, `prenom`, `promotion_id`, `role_lib`) VALUES
(1, '2017-05-20 16:51:55', '111@supinfo.com', b'1', 111, '$2a$10$DRUe/l7/SqzKCL9R9.JZB.W87RuOzTsLWwSWksWfkMOTA7JKuo.n2', 'ADMINISTRATEUR', 'Admin', NULL, 'ROLE_ADMIN');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `creneau_matiere_coach`
--
ALTER TABLE `creneau_matiere_coach`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKlqnysssnnk34oga18faalffml` (`coach_id`),
  ADD KEY `FKegsfgam0j6luc9cuqucan4vsl` (`matiere_id`),
  ADD KEY `FKopxh9p4s7yowx4u72fysv4d24` (`staff_id`);

--
-- Index pour la table `demande_soutien`
--
ALTER TABLE `demande_soutien`
  ADD PRIMARY KEY (`id`),
  ADD KEY `etudiant_id` (`etudiant_id`),
  ADD KEY `coach_id` (`coach_id`),
  ADD KEY `matiere_id` (`matiere_id`);

--
-- Index pour la table `inscription_etudiant_creneau`
--
ALTER TABLE `inscription_etudiant_creneau`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKn6oue3ts730eqx9ors7o7ekrx` (`creneau_id`),
  ADD KEY `FKen3i4cy4p5xdkxablqcfjjwlg` (`etudiant_id`);

--
-- Index pour la table `matiere`
--
ALTER TABLE `matiere`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5hbljvpih7o0vtcxlnq6oyd0p` (`promotion_id`);

--
-- Index pour la table `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_kb2n2okb1sv7q73fku80mxyo6` (`lib_role`),
  ADD KEY `lib_role` (`lib_role`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqrb6wnuj6ud27fh56h5u6wvik` (`promotion_id`),
  ADD KEY `FKjuf9r7td4bg4r6v4u1st2gwiu` (`role_lib`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `creneau_matiere_coach`
--
ALTER TABLE `creneau_matiere_coach`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `demande_soutien`
--
ALTER TABLE `demande_soutien`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `inscription_etudiant_creneau`
--
ALTER TABLE `inscription_etudiant_creneau`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `matiere`
--
ALTER TABLE `matiere`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;
--
-- AUTO_INCREMENT pour la table `promotion`
--
ALTER TABLE `promotion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `creneau_matiere_coach`
--
ALTER TABLE `creneau_matiere_coach`
  ADD CONSTRAINT `FKegsfgam0j6luc9cuqucan4vsl` FOREIGN KEY (`matiere_id`) REFERENCES `matiere` (`id`),
  ADD CONSTRAINT `FKlqnysssnnk34oga18faalffml` FOREIGN KEY (`coach_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKopxh9p4s7yowx4u72fysv4d24` FOREIGN KEY (`staff_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `demande_soutien`
--
ALTER TABLE `demande_soutien`
  ADD CONSTRAINT `FK8gptn1xknuulvmj2klumek8c1` FOREIGN KEY (`coach_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKghi1f2g35d13mrvic5qjha5f1` FOREIGN KEY (`matiere_id`) REFERENCES `matiere` (`id`),
  ADD CONSTRAINT `FKlcoyvytps0p5rxyq4gqkdi4ym` FOREIGN KEY (`etudiant_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `demande_soutien_ibfk_1` FOREIGN KEY (`etudiant_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `demande_soutien_ibfk_2` FOREIGN KEY (`coach_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `demande_soutien_ibfk_3` FOREIGN KEY (`matiere_id`) REFERENCES `matiere` (`id`);

--
-- Contraintes pour la table `inscription_etudiant_creneau`
--
ALTER TABLE `inscription_etudiant_creneau`
  ADD CONSTRAINT `FKen3i4cy4p5xdkxablqcfjjwlg` FOREIGN KEY (`etudiant_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKn6oue3ts730eqx9ors7o7ekrx` FOREIGN KEY (`creneau_id`) REFERENCES `creneau_matiere_coach` (`id`);

--
-- Contraintes pour la table `matiere`
--
ALTER TABLE `matiere`
  ADD CONSTRAINT `FK5hbljvpih7o0vtcxlnq6oyd0p` FOREIGN KEY (`promotion_id`) REFERENCES `promotion` (`id`);

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKjuf9r7td4bg4r6v4u1st2gwiu` FOREIGN KEY (`role_lib`) REFERENCES `role` (`lib_role`),
  ADD CONSTRAINT `FKqrb6wnuj6ud27fh56h5u6wvik` FOREIGN KEY (`promotion_id`) REFERENCES `promotion` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
