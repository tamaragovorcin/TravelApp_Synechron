

INSERT INTO authority (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO admin (username, password) VALUES ('admin', '$2a$10$SFsH23cE.bPHhP7JMJKa1OVSKFAVp7iD1XmzT1gUDzfVFK3xpNo/C');
INSERT INTO user_authority (username, authority_id) VALUES ('admin', 1);




INSERT INTO country_and_city (id, country, city, drzava, grad) VALUES
   (1, 'France', 'Paris', 'Francuska', 'Pariz'),
   (2, 'USA', 'Charlotte', 'SAD', 'Šarlot'),
   (3, 'USA', 'New York', 'SAD', 'Njujork'),
   (4, 'USA', 'Dallas', 'SAD', 'Dalas'),
   (5, 'United States of America', 'Charlotte', 'SAD', 'Šarlot'),
   (6, 'United States of America', 'New York', 'SAD', 'Njujork'),
   (7, 'United States of America', 'Dallas', 'SAD', 'Dalas'),
   (8, 'Serbia', 'Belgrade', "Srbija", "Beograd"),
   (9, 'India', 'Mumbai','Indija', 'Mumbaji'),
   (10, 'Dubai', 'Dubai','Dubai', 'Dubai'),
   (11, 'United Kingdom', 'London','Velika Britanija', 'London'),
   (12, 'UK', 'London','Velika Britanija', 'London'),
   (13, 'Switzerland', 'Bern','Švajcarska', 'Bern'),
   (14, 'Switzerland', 'Zurich','Švajcarska', 'Cirih'),
   (15, 'Switzerland', 'Geneva','Švajcarska', 'Ženeva'),
   (16, 'Portugal', 'Lisbon','Portugal', 'Lisabon'),
   (17, 'Hungary', 'Budapest','Mađarska', 'Budimpešta'),
   (18, 'Denmark', 'Billund','Danska', 'Bilund'),
   (19, 'Denmark', 'Copenhagen','Danska', 'Kopenhagen'),
   (20, 'Netherlands', 'Amsterdam','Holandija', 'Amsterdam'),
   (21, 'Netherlands', 'Maastricht','Holandija', 'Maastricht');


INSERT INTO position (id, name) VALUES
    (1,'DIREKTOR'),
    (2,'MENADZER_LJUDSKIH_RESURSA/REGRUTMENTA'),
    (3,'LEAD_HR'),(4,'HR_OFFICER'),
    (5,'LEAD_REGRUTER'),
    (6,'REGRUTER_ZA_SRBIJU'),
    (7,'REGRUTER_ZA_FRANCUSKU'),
    (8,'REGRUTER_ZA_SVAJCARSKU'),
    (9,'REGRUTER_ZA_LUKSEMBURG'),
    (10,'MENADZER_ZA_ADMINISTRACIJU'),
    (11,'FINANSIJSKI_ANALITICAR'),
    (12,'ADMINISTRATIVNI_ASISTENT'),
    (13,'OFFICE_MENADZER_ASISTENT'),
    (14,'SISTEM_ADMINISTRATOR'),
    (15,'DOMAR'),
    (16,'DIREKTOR_SEKTORA_ZA_SISTEMSKU_INTEGRACIJU'),
    (17,'MENADZER_ZA_SISTEMSKU_INTEGRACIJU'),
    (18,'POMOCNIK_MENADZERA_ZA_SISTEMSKU_INTEGRACIJU'),
    (19,'LEAD_CALYPSO_PROGRAMER'),
    (20,'CALYPSO_PROGRAMER'),
    (21,'LEAD_CALYPSO_BIZNIS_ANALITICAR'),
    (22,'CALYPSO_BIZNIS_ANALITICAR'),
    (23,'LEAD_CALYPSO_TESTER'),
    (24,'CALYPSO_TESTER'),
    (25,'LEAD_CALYPSO_DEVOPS_INZENJER'),
    (26,'CALYPSO_DEVOPS_INZENJER'),
    (27,'LEAD_CALYPSO_APLIKATIVNA_PODRSKA'),
    (28,'CALYPSO_APLIKATIVNA_PODRSKA'),
    (29,'LEAD_MUREX_TEST_KONSULTANT'),
    (30,'MUERX_TEST_KONSULTANT'),
    (31,'LEAD_MUREX_INTEGRACIONI_KONSULTANT'),
    (32,'MUREX_INTEGRACIONI_KONSULTANT'),
    (33,'LEAD_MUREX_BIZNIS_ANALITICAR'),
    (34,'MUREX_BIZNIS_ANALITICAR'),
    (35,'LEAD_MUREX_KONSULTANT_ZA_INFRASTRUKTURU'),
    (36,'DIREKTOR_SEKTORA_ZA_TEHNOLOGIJU'),
    (37,'MENADZER_ZA_TEHNOLOGIJU'),
    (38,'POMOCNIK_MENADZERA_ZA_TEHNOLOGIJU'),
    (39,'LEAD_MANUELNI_TESTER'),
    (40,'MANUELNI_TESTER'),
    (41,'LEAD_AUTOMATSKI_TESTER'),
    (42,'AUTOMATSKI_TESTER'),
    (43,'LEAD_BIZNIS_ANALITICAR'),
    (44,'BIZNIS_ANALITICAR'),
    (45,'LEAD_PROGRAMER'),
    (46,'PROGRAMER'),
    (47,'LEAD_DIZAJNER'),
    (48,'DIZAJNER'),
    (49,'LEAD_PROJEKTNI_MENADZER'),
    (50,'PROJEKTNI_MENADZER'),
    (51,'LEAD_DEVOPS_INZENJER'),
    (52,'DEVOPS_INZENJER'),
    (53,'PRAKTIKANT'),
    (54,'MUREX_KONSULTANT_ZA_INFRASTRUKTURU'),
    (55,'STRUCNJAK_ZA_MARKETING'),
    (56,'UNQORK_PROGRAMER');



