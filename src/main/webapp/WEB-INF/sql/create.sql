DROP TABLE IF EXISTS accounts CASCADE ;
DROP TABLE IF EXISTS comments CASCADE ;
DROP TABLE IF EXISTS albums CASCADE ;

CREATE TABLE accounts (
  id serial PRIMARY KEY NOT NULL,
  login VARCHAR(45) NOT NULL UNIQUE,
  password VARCHAR(60) NOT NULL,
  email VARCHAR (60) NOT NULL UNIQUE,
  role INT DEFAULT 1
  );

CREATE TABLE albums (
  id serial PRIMARY KEY NOT NULL,
  band_name VARCHAR (50) NOT NULL,
  album_name VARCHAR (50) NOT NULL,
  description VARCHAR (255) NULL,
  quantity INT NOT NULL DEFAULT 0,
  price INT NOT NULL
  );

CREATE TABLE comments (
  id serial PRIMARY KEY NOT NULL,
  author VARCHAR (50) NOT NULL,
  text VARCHAR (255) NOT NULL,
  product_id serial;
);

INSERT INTO accounts (login, password, email, role) VALUES ('nimda', 'myrules123', 'sergio@mail.ru', 2);

INSERT  INTO albums (band_name, album_name, description, quantity, price)
 VALUES ('The Beatles', 'Abbey Road', 'Old good album from legendary British band', 5, 20);
INSERT  INTO albums (band_name, album_name, description, quantity, price)
 VALUES ('Red Hot Chilli Peppers', 'Californication', 'One of the best RHCP`s albums', 10, 15);
INSERT  INTO albums (band_name, album_name, description, quantity, price)
 VALUES ('Nirvana', 'Nevermind', 'Great album from popular band `Nirvana`', 10, 20);
INSERT  INTO albums (band_name, album_name, description, quantity, price)
 VALUES ('Red Hot Chilli Peppers', 'By the Way', 'One more album from RHCP!', 15, 17);
INSERT  INTO albums (band_name, album_name, description, quantity, price)
 VALUES ('Queen', 'Jazz', 'Beautiful rock from Freddie Mercury and his group `Quenn`', 15, 20);
 INSERT  INTO albums (band_name, album_name, description, quantity, price)
 VALUES ('The Doors', 'Waiting For The Sun', 'Album of legendary band "The Doors"', 12, 25);
INSERT  INTO albums (band_name, album_name, description, quantity, price)
VALUES ('AC-DC','Black Ice', 'Cool guys AC-DC and theirs album "Black Ice"', 20, 10);
INSERT  INTO albums (band_name, album_name, description, quantity, price)
VALUES ('Deep Purple','The Book Of Taliesyn', 'Deep purple with their psychodelic rock album', 18, 13);
INSERT  INTO albums (band_name, album_name, description, quantity, price)
VALUES ('Rolling Stones', 'Grrr!', 'Mega popular band "Rolling Stones" with one of most popular rock albums', 16, 23);
INSERT  INTO albums (band_name, album_name, description, quantity, price)
VALUES ('Iron Maiden', 'No Prayer For The Dying', 'Hardrock for lovers', 20, 17);
INSERT  INTO albums (band_name, album_name, description, quantity, price)
VALUES ('Aerosmith', 'Permanent Vacation', 'Aerosmith great album', 23, 16);
INSERT  INTO albums (band_name, album_name, description, quantity, price)
VALUES ('Guns`n`Roses', 'Appetite For Destruction', 'Real rock from super band', 19, 20);
INSERT  INTO albums (band_name, album_name, description, quantity, price)
VALUES ('KISS', 'KISS', 'Creepy monsters `Kiss` and theirs album', 24, 14);
