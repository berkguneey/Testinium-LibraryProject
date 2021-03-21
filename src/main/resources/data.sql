INSERT INTO book (id, insert_date, update_date, name, category_id) VALUES
(1, '2020-06-16 10:48:27', NULL, 'Ayşegül Tatilde', 3);
INSERT INTO book (id, insert_date, update_date, name, category_id) VALUES
(2, '2020-06-16 10:49:38', NULL, 'Pia Mater', 1);
INSERT INTO book (id, insert_date, update_date, name, category_id) VALUES
(3, '2020-06-16 10:49:59', NULL, 'Aklından Bir Sayı Tut', 4);
INSERT INTO book (id, insert_date, update_date, name, category_id) VALUES
(4, '2020-06-16 10:50:30', '2020-06-16 10:52:56', 'Sapiens', 1);
INSERT INTO book (id, insert_date, update_date, name, category_id) VALUES
(5, '2020-06-16 10:50:36', NULL, 'Homo Deus', 4);
INSERT INTO book (id, insert_date, update_date, name, category_id) VALUES
(6, '2020-06-16 10:50:52', NULL, 'Tiryaki Sözler', 3);


INSERT INTO category (id, insert_date, update_date, name) VALUES
(1, '2020-06-16 10:45:44', NULL, 'Macera');
INSERT INTO `category` (id, insert_date, update_date, name) VALUES
(2, '2020-06-16 10:45:51', NULL, 'Romantik');
INSERT INTO `category` (id, insert_date, update_date, name) VALUES
(3, '2020-06-16 10:45:56', NULL, 'Çocuk');
INSERT INTO `category` (id, insert_date, update_date, name) VALUES
(4, '2020-06-16 10:46:16', NULL, 'Aksiyon');


INSERT INTO store (id, insert_date, update_date, city, name) VALUES
(1, '2020-06-16 10:53:55', NULL, 'İstanbul', 'Şehir Kütüphanesi');
INSERT INTO store (id, insert_date, update_date, city, name) VALUES
(2, '2020-06-16 10:54:06', NULL, 'Ankara', 'Şehir Kütüphanesi');

INSERT INTO store_book (id, insert_date, update_date, price, book_id, store_id) VALUES
(5, '2020-06-16 10:57:18', NULL, '34.20', 4, 1);
INSERT INTO store_book (id, insert_date, update_date, price, book_id, store_id) VALUES
(2, '2020-06-16 10:55:27', NULL, '28.80', 4, 2);
INSERT INTO store_book (id, insert_date, update_date, price, book_id, store_id) VALUES
(3, '2020-06-16 10:55:50', NULL, '20.00', 3, 1);
INSERT INTO store_book (id, insert_date, update_date, price, book_id, store_id) VALUES
(4, '2020-06-16 10:56:06', NULL, '25.00', 1, 1);

COMMIT;


