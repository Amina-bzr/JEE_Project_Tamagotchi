REPLACE INTO `Owner` (`idOwner`, `fname`, `lname`, `username`)
        VALUES (1, 'Amina', 'Bouzar', 'fenn'), (2, 'Odette', 'Odd', 'odd');

REPLACE INTO `Tamagotchi` (`idTamagotchi`, `name`, `idOwner`, `hungry`, `happiness`, `energy`,`thirst`, `state`,`disease`,
        `lastUpdateTime`,`thresholdMin`,`thresholdMax`)
        VALUES
        (1, 'Tama1', 1, 0, 0, 0,0,'dead',NULL,NOW(),10,100), --mort
        (2, 'Tama2', 2, 15, 15, 15,15,'sick','malnutrition',NOW(),10,100), --malade malnutrition
        (3, 'Tama3', 2, 100, 110, 110,110,'sick','obesity',NOW(),10,100), --malade obesity
        (4, 'Tama4', NULL, 20, 20, 20,20,'good',NULL,NOW(),10,100); --no owner

REPLACE INTO `Treatment` (`idTreatment`, `name`, `disease`, `cost`, `effect`)
        VALUES
        (1, 'Alimentation équilibrée', 'malnutrition', 20.0, 15),
        (2, 'Complément alimentaire', 'malnutrition', 10.0, 10),
        (3, 'Hydratation renforcée', 'malnutrition', 15.0, 20),
        (4, 'Régime strict', 'obesity', 30.0, -15),
        (5, 'Réduction hydrique', 'obesity', 20.0, -10),
        (6, 'Stabilisation énergétique', 'obesity', 25.0, -20);

REPLACE INTO `Account` (`accountNumber`, `balance`, `tamagotchiId`)
        VALUES
        ('ACC001', 500.0, 1),
        ('ACC002', 100.0, 2),
        ('ACC003', 0, 3),
        ('ACC004', 20, 4);