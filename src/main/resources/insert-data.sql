INSERT INTO user(id,name,password,createdAt) VALUES (10,'Admin','7110eda4d09e062aa5e4a390b0a572ac0d2c0220','2018-10-13');
INSERT INTO user(id,name,password,createdAt) VALUES (11,'Charlotte','7110eda4d09e062aa5e4a390b0a572ac0d2c0220','2018-10-13');
INSERT INTO user(id,name,password,createdAt) VALUES (12,'Yanis','7110eda4d09e062aa5e4a390b0a572ac0d2c0220','2018-10-13');
INSERT INTO user(id,name,password,createdAt) VALUES (13,'Etienne','7110eda4d09e062aa5e4a390b0a572ac0d2c0220','2018-10-13');

INSERT INTO Category(id,name) VALUES (1,'Solidaire et citoyen');
INSERT INTO Category(id,name) VALUES (2,'Technologie');
INSERT INTO Category(id,name) VALUES (3,'Sports');
INSERT INTO Category(id,name) VALUES (4,'Film et video');
INSERT INTO Category(id,name) VALUES (5,'Jeux');

INSERT INTO Project(id,title,description,goal,current,deadLine,createdAt,failed,belonguser_id,category_id,updatedAt) VALUES (10,'Parrainez un Panda','Nous accueillons notamment un groupe de panda geant, espece classee en danger critique d’extinction',10000,500,'2018-12-13','2018-10-13',0,13,1,null);
INSERT INTO Project(id,title,description,goal,current,deadLine,createdAt,failed,belonguser_id,category_id,updatedAt) VALUES (11,'Album de jean-Michel Jarre','Aider a financer le nouveau album de Jean-Michel Jarre intitule "Crissements imfames"',50000,2200,'2018-12-25','2018-10-25',0,12,2,null);
INSERT INTO Project(id,title,description,goal,current,deadLine,createdAt,failed,belonguser_id,category_id,updatedAt) VALUES (12,'Une montre connectee revolutionnaire','Montre GPS possedant un ecran Geant 4K de 25" de diagonnal, le futur est a porter de main ',25000,100,'2018-12-13','2018-10-13',0,10,3,null);
INSERT INTO Project(id,title,description,goal,current,deadLine,createdAt,failed,belonguser_id,category_id,updatedAt) VALUES (13,'Financez ma switch','Sans le sou, le jeune Yanis cherche un moyen de se divertir, pouvez vous lui venir en aide en investissant dans sa nouvelle console Nintendo Switch ?',500,0,'2018-12-18','2018-10-20',0,11,2,null);

INSERT INTO COUNTERPART(id,description,name,price,belongproject_id) VALUES (10,'Un grand merci !','Baby',5,10);
INSERT INTO COUNTERPART(id,description,name,price,belongproject_id) VALUES (11,'Certificat de parrainage et fiche de presentation avec portrait photo de l''animal','Papa',25,10);
INSERT INTO COUNTERPART(id,description,name,price,belongproject_id) VALUES (12,'Ramener votre panda chez vous!','Moma',500,10);

INSERT INTO COUNTERPART(id,description,name,price,belongproject_id) VALUES (13,'Un disque en avant premiere','Disque',25,11);
INSERT INTO COUNTERPART(id,description,name,price,belongproject_id) VALUES (14,'Un disque dedicace des sa sortie','Dedicace',100,11);
INSERT INTO COUNTERPART(id,description,name,price,belongproject_id) VALUES (15,'Un concert prive pour votre marriage','concert',1500,11);

INSERT INTO COUNTERPART(id,description,name,price,belongproject_id) VALUES (16,'Un partie de Smash Bros avec moi','Smash',50,13);
INSERT INTO COUNTERPART(id,description,name,price,belongproject_id) VALUES (17,'Je deviens votre ami le plus sincere' ,'Amitie',200,13);


INSERT INTO PROJECT_COUNTERPART(PROJECT_ID ,COUNTERPARTS_ID) VALUES (10,10);
INSERT INTO PROJECT_COUNTERPART(PROJECT_ID ,COUNTERPARTS_ID) VALUES (10,11);
INSERT INTO PROJECT_COUNTERPART(PROJECT_ID ,COUNTERPARTS_ID) VALUES (10,12);

INSERT INTO PROJECT_COUNTERPART(PROJECT_ID ,COUNTERPARTS_ID) VALUES (11,13);
INSERT INTO PROJECT_COUNTERPART(PROJECT_ID ,COUNTERPARTS_ID) VALUES (11,14);
INSERT INTO PROJECT_COUNTERPART(PROJECT_ID ,COUNTERPARTS_ID) VALUES (11,15);

INSERT INTO PROJECT_COUNTERPART(PROJECT_ID ,COUNTERPARTS_ID) VALUES (13,16);
INSERT INTO PROJECT_COUNTERPART(PROJECT_ID ,COUNTERPARTS_ID) VALUES (13,17);


INSERT INTO MESSAGE(id,content,belongproject_id,belonguser_id) VALUES (100,'Je te soutiens, je suis impatiente de pouvoir jouer a Smash Bros',13,11);

INSERT INTO MESSAGE(id,content,belongproject_id,belonguser_id) VALUES (101,'Bon courage pour le disque, combien de pistes sont prevue ? ',11,13);
INSERT INTO ANSWER(id,content,belongmessage_id,belonguser_id) VALUES (100,'Merci, pour le moment 8 chansons ont prevues, plus une bonus dans la version collector',101,12);

INSERT INTO PROJECT_PARTICIPATIONS(project_id,participations,participations_key) VALUES (10,500,11);
INSERT INTO PROJECT_PARTICIPATIONS(project_id,participations,participations_key) VALUES (13,100,11);
INSERT INTO PROJECT_PARTICIPATIONS(project_id,participations,participations_key) VALUES (11,1000,13);
INSERT INTO PROJECT_PARTICIPATIONS(project_id,participations,participations_key) VALUES (11,1200,12);

INSERT INTO PROJECT_USER(participeprojects_id,usersparticipation_id) VALUES (10,11);
INSERT INTO PROJECT_USER(participeprojects_id,usersparticipation_id) VALUES (13,11);
INSERT INTO PROJECT_USER(participeprojects_id,usersparticipation_id) VALUES (11,13);
INSERT INTO PROJECT_USER(participeprojects_id,usersparticipation_id) VALUES (11,12);

