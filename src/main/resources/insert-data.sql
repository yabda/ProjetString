INSERT INTO user(id,name,password,createdAt) VALUES (1,'Admin','7110eda4d09e062aa5e4a390b0a572ac0d2c0220','2018-10-13');
INSERT INTO user(id,name,password,createdAt) VALUES (2,'Charlotte',SHA1('1234'),'2018-10-13');
INSERT INTO user(id,name,password,createdAt) VALUES (3,'Yanis',SHA1('1234'),'2018-10-13');
INSERT INTO user(id,name,password,createdAt) VALUES (4,'Etienne',SHA1('1234'),'2018-10-13');

INSERT INTO Category(id,name) VALUES (1,'Solidaire et citoyen');
INSERT INTO Category(id,name) VALUES (2,'Technologie');
INSERT INTO Category(id,name) VALUES (3,'Sports');
INSERT INTO Category(id,name) VALUES (4,'Film et video');
INSERT INTO Category(id,name) VALUES (5,'Jeux');

INSERT INTO Project(id,title,description,goal,current,deadLine,createdAt,failed,belonguser_id,category_id,updatedAt) VALUES (1,'Parrainez un Panda','Nous accueillons notamment un groupe de panda geant, espèce classée en danger critique d’extinction',10000,0,'2018-12-13','2018-10-13',0,2,1,'2018-10-13');
INSERT INTO Project(id,title,description,goal,current,deadLine,createdAt,failed,belonguser_id,category_id,updatedAt) VALUES (2,'Parrainez une poule','Nous accueillons notamment un groupe de poules geantes, espèce classée en danger critique d’extinction',10000,0,'2018-12-13','2018-10-13',0,2,1,'2018-10-13');
INSERT INTO Project(id,title,description,goal,current,deadLine,createdAt,failed,belonguser_id,category_id,updatedAt) VALUES (3,'Parrainez un python','Nous accueillons notamment un groupe de pythons geants, espèce classée en danger critique d’extinction',10000,0,'2018-12-13','2018-10-13',0,2,1,'2018-10-13');

INSERT INTO CATEGORY_PROJECT(category_id,projects_id) VALUES (1,1);

INSERT INTO COUNTERPART(id,description,name,price,belongproject_id) VALUES (1,'Un grand merci !','Pour 5 € ou plus',5,1);
INSERT INTO COUNTERPART(id,description,name,price,belongproject_id) VALUES (2,'Certificat de parrainage et fiche de présentation avec portrait photo de l''animal','Pour 25 € ou plus',25,1);

INSERT INTO PROJECT_COUNTERPART (project_id,counterparts_id) VALUES (1,1);
INSERT INTO PROJECT_COUNTERPART (project_id,counterparts_id) VALUES (1,2);