## Introduction
Bienvenue dans notre projet de Tamagotchi Collectif, une réinvention moderne et virtuelle du concept classique des Tamagotchis, avec une dimension collaborative et communautaire.

## Objectif du Projet 
Ce projet propose un système innovant permettant d’adopter et de gérer des Tamagotchis virtuels. Ces petits compagnons numériques peuvent interagir entre eux, participer à diverses activités (individuelles ou collectives) et recevoir des soins adaptés pour assurer leur bien-être. 


## Architecture Technique
API REST
Les interactions entre les utilisateurs et leurs Tamagotchis passent par des API REST.
Communication Interservices avec JMS
Les échanges internes entre les différents modules du système s’effectuent via JMS (Java Message Service).

## Acteurs 
* Utilisateur
* Tamagotchi
* La fée magique


## Fonctionnalités

UTILISATEUR
* Un utilisateur peut adopter et personnaliser son Tamagotchi
* Un utilisateur peut nourrir, soigner et jouer avec son Tamagotchi
* Un utilisateur peut recevoir des notifications sur l’état de son Tamagotchi.
* Un utilisateur peut acheter des biens pour son Tamagotchi.

TAMAGOTCHI
* Un Tamagochi possède les attributs : santé, faim, bonheur et énergie.
* Un Tamagotchi peut rencontrer et parler avec d’autres Tamagotchis
* Un Tamagotchi peut tomber malade pour négligence
* Un Tamagotchi peut participer à des activités et remporter des prix
* Un Tamagotchi peut échanger des cadeaux avec d’autres Tamagotchis

LA FÉE MAGIQUE  Lizzy
* La fée magique peut accorder un cadeau à un Tamagotchi dès qu’ils se rencontrent.
* La fée magique peut donner un prix à un Tamagochi qui remporte une activité.
* La fée magique peut retirer la garde d’un Tamagotchi a un utilisateur pour négligence (niveau de bonheur, faim, santé ou énergie en dessous du seuil minimal).
* La fée magique peut consulter la liste des Tamagotchis.

## Schéma relationnel

## Exigences Fonctionnelles 
* **Le service adoption** doit permettre la création, la suppression et la gestion d’un tamagotchi.
* **Le service de soin** doit gérer les maladies et soins ,et ajuster les attributs en fonction des actions de l’utilisateur et du temps écoulé
* **Le service social** doit gérer les interactions entre Tamagotchis
* **Le service culturel** doit permettre la gestion des activités, des prix et des rangs des Tamagotchis
* **Le service bancaire** doit permettre la gestion des comptes et des prêts des utilisateurs
* L’état du Tamagotchi doit être sauvegardé


