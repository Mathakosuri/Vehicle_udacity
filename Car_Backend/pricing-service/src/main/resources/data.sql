  CREATE TABLE IF NOT EXISTS PRICE(
  vehicleId INT PRIMARY KEY auto_increment,
  name VARCHAR(20),
  currency VARCHAR(20)
 );

INSERT INTO price(currency, price, vehicleId) VALUES ('dollar', 10000,1);
INSERT INTO price(currency, price, vehicleId) VALUES ('dollar', 20000,2);
INSERT INTO price(currency, price, vehicleId) VALUES ('dollar', 30000,3);
INSERT INTO price(currency, price, vehicleId) VALUES ('dollar', 50000,4);
INSERT INTO price(currency, price, vehicleId) VALUES ('dollar', 70000,5);