DROP TABLE IF EXISTS `whereismyspot`.`LOT`;

CREATE TABLE IF NOT EXISTS `whereismyspot`.`LOT` (
`lot_id` VARCHAR(45) NOT NULL,
  `hours` VARCHAR(255) NOT NULL,
  `image` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`lot_id`));
  
INSERT INTO lot values ('Vago North', '7am - 11pm', 'vagoNorth.png');
INSERT INTO lot values ('Vago South', '7am - 11pm', 'vagoSouth.png');
INSERT INTO lot values ('STEM', '7am - 11pm', 'stem.png');
INSERT INTO lot values ('Institute', '7am - 11pm', 'institute.png');
INSERT INTO lot values ('Southlawn', '7am - 11pm', 'southlawn.png');
INSERT INTO lot values ('Eckhart', '7am - 11pm', 'eckhart.png');
INSERT INTO lot values ('Dunham', '7am - 11pm', 'dunham.png');
INSERT INTO lot values ('UBH', '7am - 11pm', 'ubh.png');
INSERT INTO lot values ('Parolini', '7am - 11pm', 'parolini.png');