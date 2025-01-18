--inserting some products to the product table

REPLACE INTO `Product` (`name`, `limitedEdition`, `category`, `price`, `quantityAvailable`)
VALUES
    ('Bonnet Rouge', false, 'Vetements', 3, null),
    ('Skin Superstar Tamagotchi', true, 'Skins', 45.85, 10),
    ('Skin Fighter Tamagotchi', true, 'Skins', 75.2, 10),
    ('Robe à Volants', false, 'Vetements', 5, null),
    ('T-Shirt Noir', false, 'Vetements', 4, null),
    ('Pantalon Gris', false, 'Vetements', 4.49, null),
    ('Chaussettes', false, 'Vetements', 2.12, null),
    ('Chapeau Grenouille', true, 'Vetements', 2.99, 1), --very rare product
    ('Ballon', false, 'Jouets', 4.49, null),
    ('Corde', false, 'Jouets', 3.99, null),
    ('Frisbee', false, 'Jouets', 4.99, null),
    ('Barette', false, 'Accessoires', 2, null),
    ('Lunettes de soleil', false, 'Accessoires', 3.11, null);

