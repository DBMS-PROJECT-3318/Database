BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `User` (
	`PricePoint`	REAL,
	`UserName`	TEXT,
	`Password`	TEXT
);
INSERT INTO `User` VALUES (NULL,NULL,NULL);
CREATE TABLE IF NOT EXISTS `AptCharacteristics` (
	`Price`	REAL,
	`Rooms`	INTEGER,
	`Floor`	INTEGER
);
CREATE TABLE IF NOT EXISTS `Apartment` (
	`Price`	REAL,
	`AptName`	TEXT,
	`AptAddress`	TEXT
);
COMMIT;
