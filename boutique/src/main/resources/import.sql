
--REPLACE INTO `Tamagotchi` (`idTamagotchi`, `name`, `idOwner`, `hungry`, `happiness`, `energy`, `health`, `state`)
--VALUES
--(1, 'Tama1', 1, 0, 100, 100, 100, 'good'), --fenn
--(2, 'Tama2', 2, 0, 100, 100, 100, 'good'), --odd
--(3, 'Tama3', NULL, 0, 100, 100, 100, 'good'); --no owner

REPLACE INTO `Product` (`name`, `category`, `price`, `quantityAvailable`)
VALUES
    ('Bonnet Rouge', 'Vetements', 3, 50),
    ('Robe à Volants', 'Vetements', 5, 50),
    ('T-Shirt Noir', 'Vetements', 4, 50),
    ('Pantalon Gris', 'Vetements', 4.49, 50),
    ('Chaussettes', 'Vetements', 2.12, 50),
    ('Chapeau Grenouille', 'Vetements', 2.99, 50),
    ('Ballon', 'Jouets', 4.49, 50),
    ('Corde', 'Jouets', 3.99, 50),
    ('Frisbee', 'Jouets', 4.99, 50),
    ('Barette', 'Accessoires', 2, 50),
    ('Lunettes de soleil', 'Accessoires', 3.11, 50);


-- Création de la table Product


-- Insertion de données dans la table Product

-- Catégorie : Vêtements
