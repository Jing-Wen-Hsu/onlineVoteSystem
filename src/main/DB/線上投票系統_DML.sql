-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- 主機： localhost:3306
-- 產生時間： 2024-12-14 12:02:02
-- 伺服器版本： 8.0.39
-- PHP 版本： 8.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫: `online_vote_system`
--

--
-- 傾印資料表的資料 `users`
--

INSERT INTO `users` (`user_id`, `password`, `username`) VALUES
(38, '$2a$10$fYKni4W4d1BHD5acekJTz.DGLImv2NPol8lL8fbVeMenAZmCrK0Tq', 'TEST'),
(43, '$2a$10$FGMVdW0BvgN5/KNXeksZ0eW8RP7nLOMESDbL7U71KrxwjKfxtafbi', 'BBB'),
(61, '$2a$10$ru2m1cptErSX2suXZ8SbVOafc8JbofFHJvywXZ.bIFaJpVmWYZ/7a', '測試員1號'),
(62, '$2a$10$NdsW94s9VQnR8ZGdpe44V..l7vTliijQY9BeMOWLW6ozRQ0gFqzra', '測試員2號');

--
-- 傾印資料表的資料 `vote_items`
--

INSERT INTO `vote_items` (`vote_item_id`, `vote_item_count`, `vote_item_name`) VALUES
(17, 2, '電腦'),
(18, 1, '主機'),
(22, 0, '滑鼠');

--
-- 傾印資料表的資料 `vote_records`
--

INSERT INTO `vote_records` (`vote_record_id`, `vote_item_id`, `user_id`) VALUES
(53, 17, 62),
(54, 17, 38),
(56, 18, 43);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
