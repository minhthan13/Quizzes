-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: Feb 20, 2025 at 03:11 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quizz_app`
--

-- --------------------------------------------------------

--
-- Table structure for table `answers`
--

CREATE TABLE `answers` (
  `answer_id` bigint(20) NOT NULL,
  `question_id` bigint(20) DEFAULT NULL,
  `answer_text` text NOT NULL,
  `is_correct` tinyint(1) DEFAULT 0,
  `order_index` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `deleted_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `category_id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` text DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp(),
  `deleted_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `question_id` bigint(20) NOT NULL,
  `quizz_id` bigint(20) DEFAULT NULL,
  `question_text` text NOT NULL,
  `question_type` varchar(20) NOT NULL COMMENT 'SINGLE_CHOICE, MULTIPLE_CHOICE, TRUE_FALSE',
  `score` int(11) DEFAULT 0,
  `order_index` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `deleted_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `quizzes`
--

CREATE TABLE `quizzes` (
  `quizz_id` bigint(20) NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `description` text DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `time_limit` int(11) DEFAULT NULL COMMENT 'Time limit in minutes',
  `total_score` int(11) DEFAULT 0,
  `status` varchar(20) NOT NULL COMMENT 'DRAFT, PUBLISHED, ARCHIVED',
  `visibility` varchar(20) NOT NULL COMMENT 'PUBLIC, PRIVATE',
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp(),
  `deleted_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `quizz_attempts`
--

CREATE TABLE `quizz_attempts` (
  `attempt_id` bigint(20) NOT NULL,
  `quizz_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime DEFAULT NULL,
  `score` int(11) DEFAULT 0,
  `status` varchar(20) DEFAULT NULL COMMENT 'IN_PROGRESS, COMPLETED, TIMEOUT',
  `created_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `rankings`
--

CREATE TABLE `rankings` (
  `ranking_id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `total_score` int(11) DEFAULT 0,
  `quizzes_completed` int(11) DEFAULT 0,
  `correct_answers` int(11) DEFAULT 0,
  `rank_position` int(11) DEFAULT NULL,
  `updated_at` datetime DEFAULT current_timestamp(),
  `created_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(20) NOT NULL COMMENT 'ADMIN, USER',
  `profile_image` varchar(255) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT 1,
  `created_at` datetime DEFAULT current_timestamp(),
  `deleted_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `email`, `password`, `role`, `profile_image`, `is_active`, `created_at`, `deleted_at`) VALUES
(1, 'test', 'test@gmail.com', '$2a$10$AgcYpi/4sc2DDjGEQF2FSePgd44Lk5uEEP1J7W4Esis01POUppJ5m', 'ROLE_USER', NULL, 1, '2025-02-12 17:17:16', NULL),
(2, 'test1', 'test1@gmail.com', '$2a$10$yLZOkCTAeEn3r6USRxIJoOaJeTGMx7NlFgb1KToS9RKFAyQY2TEPa', 'USER', NULL, 1, '2025-02-12 17:18:48', NULL),
(3, 'test2', 'test2@gmail.com', '$2a$10$VixUMkjh5D.jAF.PFRjZV.pkBjR/HWkEMLJO9WXynCILuJ1d9G3UK', 'USER', NULL, 0, '2025-02-12 17:21:07', NULL),
(4, 'test3', 'test3@gmail.com', '$2a$10$Hz.kWIeutoItsgfY9wbcLul0hJBDE0VjnLSsDb0MsTp3uq4R8GAM.', 'USER', NULL, 1, '2025-02-12 17:24:22', NULL),
(5, 'test4', 'test4@gmail.com', '$2a$10$Lq.Es8qZFyZprnPHaNz7vuFXlCKSs8I241aYpTzWXz5WypGmJ9Nqm', 'USER', NULL, 0, '2025-02-12 17:27:20', NULL),
(6, 'test5', 'test5@gmail.com', '$2a$10$h1eKDpF5ucWdbtMyL1pxOOv8yZ9KnTtkdLiSo44poKJADaYd1vUke', 'USER', NULL, 0, '2025-02-12 17:30:40', NULL),
(7, 'test6', 'test6@gmail.com', '$2a$10$lInas2v8bwWcMByLRjs37.kIa1zoljIRq/SqTkX0sg0Z0Fy4JGJk6', 'USER', NULL, 0, '2025-02-12 17:31:46', NULL),
(10, 'than', 'than@gmail.com', '$2a$10$MzdNik8CDWZ6sbuudHhR6uMXzqm5DPyuQlc7DuIhFreznKQxNlYuq', 'ROLE_ADMIN', NULL, 1, '2025-02-12 22:16:35', NULL),
(11, 'admin', 'admin@gmail.com', '$2a$10$/zKkboZrc5pb6Yl807Z6HOp3JTXRXgT5VljXhy8VhRdTbLQXN94mS', 'ROLE_ADMIN', NULL, 1, '2025-02-12 22:18:31', NULL),
(12, 'user', 'user@gmail.com', '$2a$10$PDsjagw2/8oVSs0/gn8bSOsYKlYjzskIvmbTnTjtdG8bWUOmOx46S', 'ROLE_USER', NULL, 0, '2025-02-13 14:12:57', NULL),
(13, 'than1', 'than1@gmail.com', '$2a$10$meAzetT5DKN4Oz1SW0wsP.y/RAmCN23uxES8Sb0jzxqhjhNawMlCO', 'ROLE_USER', NULL, 0, '2025-02-13 15:17:56', NULL),
(14, 'than123', 'than123@gmail.com', '$2a$10$CIEyZ/ttvv47uZKNlBqN2OTP90asHVGyDAuxETCc6pVrCViSPzgci', 'ROLE_USER', NULL, 1, '2025-02-18 14:35:26', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_answers`
--

CREATE TABLE `user_answers` (
  `user_answer_id` bigint(20) NOT NULL,
  `attempt_id` bigint(20) DEFAULT NULL,
  `question_id` bigint(20) DEFAULT NULL,
  `answer_id` bigint(20) DEFAULT NULL,
  `is_correct` tinyint(1) DEFAULT 0,
  `created_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answers`
--
ALTER TABLE `answers`
  ADD PRIMARY KEY (`answer_id`),
  ADD KEY `question_id` (`question_id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`question_id`),
  ADD KEY `quizz_id` (`quizz_id`);

--
-- Indexes for table `quizzes`
--
ALTER TABLE `quizzes`
  ADD PRIMARY KEY (`quizz_id`),
  ADD KEY `created_by` (`created_by`),
  ADD KEY `idx_quiz_status` (`status`),
  ADD KEY `idx_quiz_visibility` (`visibility`),
  ADD KEY `idx_quiz_category` (`category_id`);

--
-- Indexes for table `quizz_attempts`
--
ALTER TABLE `quizz_attempts`
  ADD PRIMARY KEY (`attempt_id`),
  ADD KEY `quizz_id` (`quizz_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `rankings`
--
ALTER TABLE `rankings`
  ADD PRIMARY KEY (`ranking_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `idx_ranking_score` (`total_score`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `idx_user_email` (`email`),
  ADD KEY `idx_user_username` (`username`);

--
-- Indexes for table `user_answers`
--
ALTER TABLE `user_answers`
  ADD PRIMARY KEY (`user_answer_id`),
  ADD KEY `attempt_id` (`attempt_id`),
  ADD KEY `question_id` (`question_id`),
  ADD KEY `answer_id` (`answer_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answers`
--
ALTER TABLE `answers`
  MODIFY `answer_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `category_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `question_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `quizzes`
--
ALTER TABLE `quizzes`
  MODIFY `quizz_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `quizz_attempts`
--
ALTER TABLE `quizz_attempts`
  MODIFY `attempt_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `rankings`
--
ALTER TABLE `rankings`
  MODIFY `ranking_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `user_answers`
--
ALTER TABLE `user_answers`
  MODIFY `user_answer_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `answers`
--
ALTER TABLE `answers`
  ADD CONSTRAINT `answers_ibfk_1` FOREIGN KEY (`question_id`) REFERENCES `questions` (`question_id`);

--
-- Constraints for table `questions`
--
ALTER TABLE `questions`
  ADD CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`quizz_id`) REFERENCES `quizzes` (`quizz_id`);

--
-- Constraints for table `quizzes`
--
ALTER TABLE `quizzes`
  ADD CONSTRAINT `quizzes_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `quizzes_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`);

--
-- Constraints for table `quizz_attempts`
--
ALTER TABLE `quizz_attempts`
  ADD CONSTRAINT `quizz_attempts_ibfk_1` FOREIGN KEY (`quizz_id`) REFERENCES `quizzes` (`quizz_id`),
  ADD CONSTRAINT `quizz_attempts_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `rankings`
--
ALTER TABLE `rankings`
  ADD CONSTRAINT `rankings_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `user_answers`
--
ALTER TABLE `user_answers`
  ADD CONSTRAINT `user_answers_ibfk_1` FOREIGN KEY (`attempt_id`) REFERENCES `quizz_attempts` (`attempt_id`),
  ADD CONSTRAINT `user_answers_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `questions` (`question_id`),
  ADD CONSTRAINT `user_answers_ibfk_3` FOREIGN KEY (`answer_id`) REFERENCES `answers` (`answer_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
