INSERT INTO user(id,name,password,createdAt) VALUES (5,'Admin','7110eda4d09e062aa5e4a390b0a572ac0d2c0220','2018-10-13');
INSERT INTO user(id,name,password,createdAt) VALUES (6,'Charlotte','7110eda4d09e062aa5e4a390b0a572ac0d2c0220','2018-10-13');
INSERT INTO user(id,name,password,createdAt) VALUES (7,'Yanis','7110eda4d09e062aa5e4a390b0a572ac0d2c0220','2018-10-13');
INSERT INTO user(id,name,password,createdAt) VALUES (8,'Etienne','7110eda4d09e062aa5e4a390b0a572ac0d2c0220','2018-10-13');

INSERT INTO Category(id,name) VALUES (1,'Solidaire et citoyen');
INSERT INTO Category(id,name) VALUES (2,'Technologie');
INSERT INTO Category(id,name) VALUES (3,'Sports');
INSERT INTO Category(id,name) VALUES (4,'Film et video');
INSERT INTO Category(id,name) VALUES (5,'Jeux');

INSERT INTO Project(id,title,description,goal,current,deadLine,createdAt,failed,belonguser_id,category_id,updatedAt) VALUES (5,'Parrainez un Panda','Nous accueillons notamment un groupe de panda geant, espèce classée en danger critique d’extinction',10000,0,'2018-12-13','2018-10-13',0,6,1,'2018-10-13');
INSERT INTO Project(id,title,description,goal,current,deadLine,createdAt,failed,belonguser_id,category_id,updatedAt) VALUES (6,'Parrainez une Poule','Nous accueillons notamment un groupe de poules geantes, espèce classée en danger critique d’extinction',10000,0,'2018-12-13','2018-10-13',0,6,1,'2018-10-13');
INSERT INTO Project(id,title,description,goal,current,deadLine,createdAt,failed,belonguser_id,category_id,updatedAt) VALUES (7,'Parrainez un Yanis','Nous accueillons notamment un groupe de pythons geants, espèce classée en danger critique d’extinction',10000,0,'2018-12-13','2018-10-13',0,6,1,'2018-10-13');
INSERT INTO Project(id,title,description,goal,current,deadLine,createdAt,failed,belonguser_id,category_id,updatedAt) VALUES (8,'Achetez moi une switch','Sans le sou, le jeune Yanis cherche un moyen de se divertir, pouvez vous lui venir en aide',10000,0,'2020-12-13','2017-10-13',0,7,2,'2018-10-13');

INSERT INTO COUNTERPART(id,description,name,price,belongproject_id) VALUES (1,'Un grand merci !','Baby',5,5);
INSERT INTO COUNTERPART(id,description,name,price,belongproject_id) VALUES (2,'Certificat de parrainage et fiche de présentation avec portrait photo de l''animal','Papa',25,5);
INSERT INTO COUNTERPART(id,description,name,price,belongproject_id) VALUES (3,'Ramener votre panda chez vous!','Moma',500,5);