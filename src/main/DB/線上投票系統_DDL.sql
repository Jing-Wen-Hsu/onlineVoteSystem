-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- 主機： localhost:3306
-- 產生時間： 2024-12-14 12:01:26
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
DELIMITER $$
--
-- 程序
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_add_vote_item` (IN `vote_item_name` VARCHAR(100))   BEGIN
    INSERT INTO vote_items (vote_item_name, vote_item_count)
    VALUES (vote_item_name, 0);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_add_vote_records` (IN `p_username` VARCHAR(255), IN `p_vote_item_id` BIGINT)   BEGIN
    DECLARE user_id BIGINT;
    -- 根據使用者名稱查找使用者 ID
    SELECT id INTO user_id
    FROM users
    WHERE username = p_username;

    -- 檢查是否已經投過該項目
    IF EXISTS (
        SELECT 1
        FROM votes
        WHERE user_id = user_id AND vote_item_id = p_vote_item_id
    ) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = '已經投過該項目';
    END IF;

    -- 插入投票紀錄
    INSERT INTO votes (user_id, vote_item_id, created_at)
    VALUES (user_id, p_vote_item_id, NOW());
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_check_username_exists` (IN `p_username` VARCHAR(255), OUT `p_exists` BOOLEAN)   BEGIN
    DECLARE v_user_id BIGINT;

    -- 根據 username 查找 user_id
    SELECT user_id INTO v_user_id
    FROM users
    WHERE username = p_username;

    -- 如果沒有找到對應的 user_id，則設置 p_exists 為 FALSE
    IF v_user_id IS NULL THEN
        SET p_exists = FALSE;
    ELSE
        -- 檢查 vote_records 表中是否已經存在該 user_id
        SELECT COUNT(*) INTO p_exists
        FROM vote_records
        WHERE user_id = v_user_id;
        
        -- 如果 count > 0，表示該用戶已經投過票，p_exists 設為 TRUE
        IF p_exists > 0 THEN
            SET p_exists = TRUE;
        ELSE
            SET p_exists = FALSE;
        END IF;
    END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_check_user_exists` (IN `p_username` VARCHAR(500), OUT `p_exists_flag` BOOLEAN)   BEGIN 
    DECLARE username_count INT;
    SELECT COUNT(*) INTO username_count 
    FROM users 
    WHERE username = p_username;
    
    SET p_exists_flag = (username_count > 0);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_check_user_vote` (IN `p_username` VARCHAR(255), IN `p_vote_item` VARCHAR(255), OUT `p_result` BOOLEAN)   BEGIN
    DECLARE vote_exists INT;

    -- 檢查該使用者是否對該投票項目已經投過票
    SELECT COUNT(1)
    INTO vote_exists
    FROM vote_records vr
    JOIN users u ON u.user_id = vr.user_id
    JOIN vote_items vi ON vi.vote_item_id = vr.vote_item_id
    WHERE u.username = p_username
    AND vi.vote_item_name = p_vote_item;

    -- 如果投過票，設置 p_result 為 TRUE，否則設置為 FALSE
    IF vote_exists > 0 THEN
        SET p_result = TRUE; -- 已經投過票
    ELSE
        SET p_result = FALSE; -- 未投過票
    END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_check_vote_exists` (IN `p_username` VARCHAR(255), OUT `p_exists` BOOLEAN)   BEGIN
    DECLARE v_user_id INT;

    -- 根據 username 查找 user_id
    SELECT user_id INTO v_user_id
    FROM users
    WHERE username = p_username;

    -- 檢查 vote_records 表中是否已經存在該 user_id
    IF EXISTS (
        SELECT 1 
        FROM vote_records vr
        WHERE vr.user_id = v_user_id
    ) THEN
        SET p_exists = TRUE;
    ELSE
        SET p_exists = FALSE;
    END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_delete_vote_item` (IN `p_vote_item_name` VARCHAR(255))   BEGIN
    -- 刪除投票項目
    DELETE FROM vote_items WHERE vote_item_name = p_vote_item_name;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_exists_vote_item_name` (IN `p_vote_item_name` VARCHAR(255), OUT `p_exists` INT)   BEGIN 
    DECLARE vote_item_count INT;
    
    -- 查詢投票項目名稱是否存在
    SELECT COUNT(*) INTO vote_item_count
    FROM vote_items
    WHERE vote_item_name = p_vote_item_name;
    
    -- 根據查詢結果設置 p_exists
    SET p_exists = (vote_item_count > 0);  -- 如果投票項目名稱存在，設置 p_exists 為 1，否則為 0
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_find_user_by_username` (IN `p_username` VARCHAR(100))   BEGIN
    SELECT * FROM users WHERE username = p_username;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_get_all_vote_items` ()   BEGIN
    SELECT vote_item_name, vote_item_count
    FROM vote_items
    ORDER BY vote_item_count DESC;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_login_user` (IN `p_username` VARCHAR(255), IN `p_password` VARCHAR(255), OUT `p_is_valid` INT)   BEGIN
    DECLARE stored_password VARCHAR(255);

    -- 查詢用戶的密碼
    SELECT password INTO stored_password
    FROM users
    WHERE username = p_username;

    -- 比對密碼
    IF stored_password IS NULL THEN
        SET p_is_valid = 0; -- 用戶不存在
    ELSE
        IF stored_password = p_password THEN
            SET p_is_valid = 1; -- 登入成功
        ELSE
            SET p_is_valid = 0; -- 密碼錯誤
        END IF;
    END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_process_vote` (IN `p_username` VARCHAR(255), IN `p_vote_items` TEXT)   BEGIN
    -- 在這裡處理投票邏輯，將每個項目插入 vote_records 表格
    DECLARE vote_item VARCHAR(255);
    DECLARE done INT DEFAULT 0;
    DECLARE cur CURSOR FOR
        SELECT value FROM JSON_TABLE(p_vote_items, "$[*]" COLUMNS (value VARCHAR(255) PATH "$")) AS items;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    OPEN cur;

    read_loop: LOOP
        FETCH cur INTO vote_item;
        IF done THEN
            LEAVE read_loop;
        END IF;

        -- 測試輸出投票項目
        SELECT vote_item;  -- 添加這行來檢查獲取的項目

        -- 插入投票記錄
        INSERT INTO vote_records (user_id, vote_item_id)
        SELECT u.user_id, vi.vote_item_id
        FROM users u
        JOIN vote_items vi ON vi.vote_item_name = vote_item
        WHERE u.username = p_username;

        -- 確認是否有成功插入
        SELECT COUNT(*) FROM vote_records WHERE user_id = (SELECT user_id FROM users WHERE username = p_username); 

  -- 更新投票項目的投票次數
        UPDATE vote_items
        SET vote_item_count = vote_item_count + 1
        WHERE vote_item_name = vote_item;

    END LOOP;

    CLOSE cur;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_register_user` (IN `p_username` VARCHAR(255), IN `p_password` VARCHAR(255))   BEGIN
    -- 新增數據到 user 表
    INSERT INTO users (username, password)
    VALUES (p_username, p_password);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_update_vote_item_Counts` ()   BEGIN
    -- 更新每個投票項目的票數
    UPDATE vote_items vi
    JOIN (
        SELECT vote_item_id, COUNT(*) AS vote_count
        FROM vote_records
        GROUP BY vote_item_id
    ) vr ON vi.vote_item_id = vr.vote_item_id
    SET vi.vote_item_count = vr.vote_count;
END$$

DELIMITER ;

-- --------------------------------------------------------

--

-- --------------------------------------------------------

--
-- 資料表結構 `users`
--

CREATE TABLE `users` (
  `user_id` bigint NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- 資料表結構 `vote_items`
--

CREATE TABLE `vote_items` (
  `vote_item_id` bigint NOT NULL,
  `vote_item_count` int NOT NULL,
  `vote_item_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- 資料表結構 `vote_records`
--

CREATE TABLE `vote_records` (
  `vote_record_id` bigint NOT NULL,
  `vote_item_id` bigint NOT NULL,
  `user_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- 資料表索引 `vote_items`
--
ALTER TABLE `vote_items`
  ADD PRIMARY KEY (`vote_item_id`),
  ADD UNIQUE KEY `vote_item_name` (`vote_item_name`);

--
-- 資料表索引 `vote_records`
--
ALTER TABLE `vote_records`
  ADD PRIMARY KEY (`vote_record_id`),
  ADD KEY `FK6kyf19dkj4syonyct98cijflt` (`user_id`),
  ADD KEY `FKpl65en0u82mseltdyc3nruww1` (`vote_item_id`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `users`
--
ALTER TABLE `users`
  MODIFY `user_id` bigint NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `vote_items`
--
ALTER TABLE `vote_items`
  MODIFY `vote_item_id` bigint NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `vote_records`
--
ALTER TABLE `vote_records`
  MODIFY `vote_record_id` bigint NOT NULL AUTO_INCREMENT;

--
-- 已傾印資料表的限制式
--

--
-- 資料表的限制式 `vote_records`
--
ALTER TABLE `vote_records`
  ADD CONSTRAINT `FK6kyf19dkj4syonyct98cijflt` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `FKpl65en0u82mseltdyc3nruww1` FOREIGN KEY (`vote_item_id`) REFERENCES `vote_items` (`vote_item_id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
