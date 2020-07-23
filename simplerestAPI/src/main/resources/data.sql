INSERT INTO owner (name) VALUES ('Anna');
INSERT INTO owner (name) VALUES ('Ben');
INSERT INTO owner (name) VALUES ('Victoria');
INSERT INTO owner (name) VALUES ('John');

INSERT INTO property (type, tax_rate ) VALUES ('Flat', 20.5);
INSERT INTO property (type, tax_rate ) VALUES ('Garden House', 50);
INSERT INTO property (type, tax_rate ) VALUES ('Apartment', 65.20);
INSERT INTO property (type, tax_rate ) VALUES ('House', 75.75);

INSERT INTO building_records(address, owner_id, size, value, property_type_id) VALUES('Naugardukas str. 1, Vilnius', 1, 38, 20, 1);
INSERT INTO building_records(address, owner_id, size, value, property_type_id) VALUES('Vilnius str. 11, Vilnius', 1, 200, 2.2, 4);
INSERT INTO building_records(address, owner_id, size, value, property_type_id) VALUES('Vilnius str. 21, Vilnius', 2, 21, 3.2, 3);
INSERT INTO building_records(address, owner_id, size, value, property_type_id) VALUES('Vilnius str. 41, Vilnius', 3, 100, 5.2, 2);
INSERT INTO building_records(address, owner_id, size, value, property_type_id) VALUES('Birute str. 21, Vilnius', 4, 78, 6.2, 1);
INSERT INTO building_records(address, owner_id, size, value, property_type_id) VALUES('Vytauto str. 51, Vilnius', 1, 178, 4.2, 2);
INSERT INTO building_records(address, owner_id, size, value, property_type_id) VALUES('Vytauto str. 51, Vilnius', 3, 100, 10, 1);