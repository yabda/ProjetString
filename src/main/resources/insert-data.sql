INSERT INTO user(id,name,password,createdAt) VALUES (1,'Admin','7110eda4d09e062aa5e4a390b0a572ac0d2c0220','2018-10-13');
INSERT INTO user(id,name,password,createdAt) VALUES (2,'Charlotte','7110eda4d09e062aa5e4a390b0a572ac0d2c0220','2018-10-13');
INSERT INTO user(id,name,password,createdAt) VALUES (3,'Yanis','7110eda4d09e062aa5e4a390b0a572ac0d2c0220','2018-10-13');
INSERT INTO user(id,name,password,createdAt) VALUES (4,'Etienne','7110eda4d09e062aa5e4a390b0a572ac0d2c0220','2018-10-13');

INSERT INTO Category(id,name) VALUES (1,'Solidaire et citoyen');
INSERT INTO Category(id,name) VALUES (2,'Technologie');
INSERT INTO Category(id,name) VALUES (3,'Sports');
INSERT INTO Category(id,name) VALUES (4,'Film et video');
INSERT INTO Category(id,name) VALUES (5,'Jeux');

INSERT INTO Project(id,title,description,goal,current,deadLine,createdAt,failed,belonguser_id,category_id,updatedAt) VALUES (10,'Parrainez un Panda','Nous accueillons notamment un groupe de panda geant, espèce classée en danger critique d’extinction',10000,0,'2018-12-13','2018-10-13',0,3,1,'2018-10-13');
INSERT INTO Project(id,title,description,goal,current,deadLine,createdAt,failed,belonguser_id,category_id,updatedAt) VALUES (11,'Parrainez une Poule','Nous accueillons notamment un groupe de poules geantes, espèce classée en danger critique d’extinction',10000,0,'2018-12-13','2018-10-13',0,3,1,'2018-10-13');
INSERT INTO Project(id,title,description,goal,current,deadLine,createdAt,failed,belonguser_id,category_id,updatedAt) VALUES (12,'Parrainez un Yanis','Nous accueillons notamment un groupe de pythons geants, espèce classée en danger critique d’extinction',10000,0,'2018-12-13','2018-10-13',0,3,1,'2018-10-13');
INSERT INTO Project(id,title,description,goal,current,deadLine,createdAt,failed,belonguser_id,category_id,updatedAt) VALUES (13,'Achetez moi une switch','Sans le sou, le jeune Yanis cherche un moyen de se divertir, pouvez vous lui venir en aide',10000,0,'2020-12-13','2017-10-13',0,1,2,'2018-10-13');

INSERT INTO COUNTERPART(id,description,name,price,belongproject_id) VALUES (10,'Un grand merci !','Baby',5,10);
INSERT INTO COUNTERPART(id,description,name,price,belongproject_id) VALUES (11,'Certificat de parrainage et fiche de présentation avec portrait photo de l''animal','Papa',25,10);
INSERT INTO COUNTERPART(id,description,name,price,belongproject_id) VALUES (12,'Ramener votre panda chez vous!','Moma',500,10);

INSERT INTO PROJECT_COUNTERPART(PROJECT_ID ,COUNTERPARTS_ID) VALUES (10,10);
INSERT INTO PROJECT_COUNTERPART(PROJECT_ID ,COUNTERPARTS_ID) VALUES (10,11);
INSERT INTO PROJECT_COUNTERPART(PROJECT_ID ,COUNTERPARTS_ID) VALUES (10,12);