-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 16 Haz 2020, 10:39:19
-- Sunucu sürümü: 10.1.33-MariaDB
-- PHP Sürümü: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `library`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `book`
--

CREATE TABLE `book` (
  `id` bigint(20) NOT NULL,
  `insert_date` datetime NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `category_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `book`
--

INSERT INTO `book` (`id`, `insert_date`, `update_date`, `name`, `category_id`) VALUES
(1, '2020-06-16 10:48:27', NULL, 'Ayşegül Tatilde', 3),
(2, '2020-06-16 10:49:38', NULL, 'Pia Mater', 1),
(3, '2020-06-16 10:49:59', NULL, 'Aklından Bir Sayı Tut', 4),
(4, '2020-06-16 10:50:30', '2020-06-16 10:52:56', 'Sapiens', 1),
(5, '2020-06-16 10:50:36', NULL, 'Homo Deus', 4),
(6, '2020-06-16 10:50:52', NULL, 'Tiryaki Sözler', 3);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `category`
--

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `insert_date` datetime NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `category`
--

INSERT INTO `category` (`id`, `insert_date`, `update_date`, `name`) VALUES
(1, '2020-06-16 10:45:44', NULL, 'Macera'),
(2, '2020-06-16 10:45:51', NULL, 'Romantik'),
(3, '2020-06-16 10:45:56', NULL, 'Çocuk'),
(4, '2020-06-16 10:46:16', NULL, 'Aksiyon');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `store`
--

CREATE TABLE `store` (
  `id` bigint(20) NOT NULL,
  `insert_date` datetime NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `city` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `store`
--

INSERT INTO `store` (`id`, `insert_date`, `update_date`, `city`, `name`) VALUES
(1, '2020-06-16 10:53:55', NULL, 'İstanbul', 'Şehir Kütüphanesi'),
(2, '2020-06-16 10:54:06', NULL, 'Ankara', 'Şehir Kütüphanesi');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `store_book`
--

CREATE TABLE `store_book` (
  `id` bigint(20) NOT NULL,
  `insert_date` datetime NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `price` decimal(19,2) NOT NULL,
  `book_id` bigint(20) DEFAULT NULL,
  `store_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `store_book`
--

INSERT INTO `store_book` (`id`, `insert_date`, `update_date`, `price`, `book_id`, `store_id`) VALUES
(5, '2020-06-16 10:57:18', NULL, '34.20', 4, 1),
(2, '2020-06-16 10:55:27', NULL, '28.80', 4, 2),
(3, '2020-06-16 10:55:50', NULL, '20.00', 3, 1),
(4, '2020-06-16 10:56:06', NULL, '25.00', 1, 1);

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_wugryet8mf6oi28n00x2eoc4` (`name`),
  ADD KEY `FKam9riv8y6rjwkua1gapdfew4j` (`category_id`);

--
-- Tablo için indeksler `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_46ccwnsi9409t36lurvtyljak` (`name`);

--
-- Tablo için indeksler `store`
--
ALTER TABLE `store`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `store_book`
--
ALTER TABLE `store_book`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgarcb88cd123rebg4x88c5cbx` (`book_id`),
  ADD KEY `FKo13g0vggee4dek55er1vpha9e` (`store_id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `book`
--
ALTER TABLE `book`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Tablo için AUTO_INCREMENT değeri `category`
--
ALTER TABLE `category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Tablo için AUTO_INCREMENT değeri `store`
--
ALTER TABLE `store`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Tablo için AUTO_INCREMENT değeri `store_book`
--
ALTER TABLE `store_book`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
