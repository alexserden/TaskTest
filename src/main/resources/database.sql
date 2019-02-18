-- Table: users
CREATE TABLE users (
  id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
)
  ENGINE = InnoDB;

-- Table: roles
CREATE TABLE roles (
  id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL
)
  ENGINE = InnoDB;

-- Table for mapping user and roles: user_roles
CREATE TABLE user_roles (
  user_id INT NOT NULL,
  role_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id),

  UNIQUE (user_id, role_id)
)
  ENGINE = InnoDB;

-- Table : person
CREATE TABLE  person(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR (255) NOT NULL,
  last_name VARCHAR (255) NOT NULL ,
  address VARCHAR (255) NOT NULL,
  description VARCHAR (255)
)
ENGINE = InnoDB;
-- Insert data

INSERT INTO users VALUES (1, 'alexserden', '$2a$11$h5LAGbRnp1mGKEqrfj.zhusMGgOv1wevGqWYZlAwpMOpCXnZEM6wq');

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_roles VALUES (1, 2);

INSERT INTO persons (id, first_name, last_name, address, description)
VALUES (6,'Alexander','Denysenko','Topol-1','украл булочку')