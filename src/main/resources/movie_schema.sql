DROP TABLE IF EXISTS movie;
DROP TABLE IF EXISTS bridge;
DROP TABLE IF EXISTS director;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS user;

CREATE TABLE user(
user_id INT AUTO_INCREMENT NOT NULL,
firstname VARCHAR(32) NOT NULL,
lastname VARCHAR(32) NOT NULL,
email VARCHAR(128) NOT NULL,
password VARCHAR(128) NOT NULL,
PRIMARY KEY(user_id)
);

CREATE TABLE genre(
genre_id INT AUTO_INCREMENT NOT NULL,
genre_category VARCHAR(32) NOT NULL,
PRIMARY KEY(genre_id)
);

CREATE TABLE director(
 director_id INT AUTO_INCREMENT NOT NULL,
 director_name VARCHAR(32) NOT NULL,
 primary key(director_id)
);

CREATE TABLE bridge (
genre_id INT NOT NULL,
director_id INT NOT NULL,
PRIMARY KEY (genre_id, director_id),
FOREIGN KEY (genre_id) REFERENCES genre (genre_id),
FOREIGN KEY (director_id) REFERENCES director (director_id)
);

CREATE TABLE movie(
movie_id INT AUTO_INCREMENT NOT NULL,
title VARCHAR(128) NOT NULL,
runtime INT NOT NULL,	
release_date YEAR NOT NULL,
director_id INT NOT NULL,
genre_id INT NOT NULL,
PRIMARY KEY(movie_id),
FOREIGN KEY(director_id) REFERENCES director(director_id), 
FOREIGN KEY(genre_id) REFERENCES genre(genre_id)
);