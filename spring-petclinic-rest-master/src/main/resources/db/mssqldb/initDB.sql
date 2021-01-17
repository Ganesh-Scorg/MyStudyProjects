USE petclinic;

CREATE TABLE vets (
  id INT NOT NULL IDENTITY PRIMARY KEY,
  first_name VARCHAR(30),
  last_name VARCHAR(30)
); 

CREATE TABLE specialties (
  id INT NOT NULL IDENTITY PRIMARY KEY,
  name VARCHAR(80)
);

CREATE TABLE vet_specialties (
  vet_id INT NOT NULL,
  specialty_id INT NOT NULL,
  FOREIGN KEY (vet_id) REFERENCES vets(id),
  FOREIGN KEY (specialty_id) REFERENCES specialties(id),
  UNIQUE (vet_id,specialty_id)
);

CREATE TABLE types (
  id INT NOT NULL IDENTITY PRIMARY KEY,
  name VARCHAR(80)
);

CREATE TABLE owners (
  id INT NOT NULL IDENTITY PRIMARY KEY,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  address VARCHAR(255),
  city VARCHAR(80),
  telephone VARCHAR(20)
);

CREATE TABLE pets (
  id INT NOT NULL IDENTITY PRIMARY KEY,
  name VARCHAR(30),
  birth_date DATE,
  type_id INT NOT NULL,
  owner_id INT NOT NULL,
  FOREIGN KEY (owner_id) REFERENCES owners(id),
  FOREIGN KEY (type_id) REFERENCES types(id)
);

CREATE TABLE visits (
  id INT NOT NULL IDENTITY PRIMARY KEY,
  pet_id INT NOT NULL,
  visit_date DATE,
  description VARCHAR(255),
  FOREIGN KEY (pet_id) REFERENCES pets(id)
);

CREATE TABLE users (
  username VARCHAR(20) NOT NULL ,
  password VARCHAR(20) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username)
);

CREATE TABLE roles (
  id int NOT NULL IDENTITY,
  username varchar(20) NOT NULL,
  role varchar(20) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username)
);