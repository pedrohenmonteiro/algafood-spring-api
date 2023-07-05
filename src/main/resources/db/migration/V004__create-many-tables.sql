CREATE TABLE restaurant (
  id BIGINT NOT NULL AUTO_INCREMENT,
  cuisine_id BIGINT NOT NULL,
  address_city_id BIGINT,
  name VARCHAR(100),
  address_street VARCHAR(100),
  address_number VARCHAR(20),
  address_complement VARCHAR(100),
  address_neighbourhood VARCHAR(100),
  address_zipcode VARCHAR(8),
  delivery_fee DECIMAL(10, 2),
  date_creation DATETIME(6) NOT NULL,
  date_last_update DATETIME(6) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_restaurant_cuisine_id FOREIGN KEY (cuisine_id) REFERENCES cuisine (id),
  CONSTRAINT FK_restaurant_address_city_id FOREIGN KEY (address_city_id) REFERENCES city (id)
) ENGINE=InnoDB;

CREATE TABLE permission (
  id BIGINT NOT NULL AUTO_INCREMENT,
  description VARCHAR(80),
  name VARCHAR(80),
  PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE payment_method (
  id BIGINT NOT NULL AUTO_INCREMENT,
  description VARCHAR(60),
  PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE product (
  id BIGINT NOT NULL AUTO_INCREMENT,
  restaurant_id BIGINT,
  description VARCHAR(100),
  name VARCHAR(50),
  active BIT,
  price DECIMAL(10, 2),
  PRIMARY KEY (id),
  CONSTRAINT FK_product_restaurant_id FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
) ENGINE=InnoDB;

CREATE TABLE tb_group (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100),
  PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE user (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100),
  email VARCHAR(100),
  password VARCHAR(100),
  creation_date DATETIME(6),
  PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE user_group (
  tb_group_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  CONSTRAINT FK_user_group_tb_group_id FOREIGN KEY (tb_group_id) REFERENCES tb_group (id),
  CONSTRAINT FK_user_group_user_id FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=InnoDB;

CREATE TABLE group_permission (
  permission_id BIGINT NOT NULL,
  tb_group_id BIGINT NOT NULL,
  CONSTRAINT FK_group_permission_permission_id FOREIGN KEY (permission_id) REFERENCES permission (id),
  CONSTRAINT FK_group_permission_group_id FOREIGN KEY (tb_group_id) REFERENCES tb_group (id)
) ENGINE=InnoDB;

CREATE TABLE restaurant_payment_method (
  payment_method_id BIGINT NOT NULL,
  restaurant_id BIGINT NOT NULL,
  CONSTRAINT FK_restaurant_payment_method_payment_method_id FOREIGN KEY (payment_method_id) REFERENCES payment_method (id),
  CONSTRAINT FK_restaurant_payment_method_restaurant_id FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
) ENGINE=InnoDB;
