DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS albums;

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
  price INT NOT NULL,
  img_id INT NULL
  );

INSERT INTO accounts (login, password, email, role) VALUES ('nimda', 'myrules123', 'sergio@mail.ru', 2);

INSERT  INTO albums (band_name, album_name, description, quantity, price, img_id)
 VALUES ('The Beatles', 'Abbey Road', 'Old good album from legendary British band', 5, 20, 1);
INSERT  INTO albums (band_name, album_name, description, quantity, price, img_id)
 VALUES ('Red Hot Chilli Peppers', 'Californication', 'One of the best RHCP`s albums', 10, 15, 2);
INSERT  INTO albums (band_name, album_name, description, quantity, price, img_id)
 VALUES ('Nirvana', 'Nevermind', 'Great album from popular band `Nirvana`', 10, 20, 3);
INSERT  INTO albums (band_name, album_name, description, quantity, price, img_id)
 VALUES ('Red Hot Chilli Peppers', 'By the Way', 'One more album from RHCP!', 15, 17, 4);