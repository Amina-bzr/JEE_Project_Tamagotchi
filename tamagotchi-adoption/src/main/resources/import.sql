
--REPLACE INTO `Tamagotchi` (`idTamagotchi`, `name`, `owner`, `hungry`, `happiness`, `energy`, `health`, `state`)
--VALUES
--(1, 'Tama1', 1, 0, 100, 100, 100, 'good'), --fenn
--(2, 'Tama2', 2, 0, 100, 100, 100, 'good'), --odd
--(3, 'Tama3', NULL, 0, 100, 100, 100, 'good'); --no owner


REPLACE INTO `Owner` (`idOwner`, `fname`, `lname`, `username`)
        VALUES (1, 'Amina', 'Bouzar', 'fenn'), (2, 'Odette', 'Odd', 'odd');