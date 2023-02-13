GAILLARD Julian

REECHT Florian

BERNABE Jimmy


# Rapport de projet: Application TOEIC


# Sommaire
I. [Introduction](#introduction)

II. [Architecture du projet](#architecture-du-projet)

III. [Mise en application](#mise-en-application)

III.1. [Connexion](#connexion)
	
III.2. [Leçons](#leçon)
	
III.3. [Questions](#questions)
	
III.4. [Forum](#forum)
	
III.5. [Administrateur](#administrateur)
	
III.6. [Vérification](#vérification)
	
IV. [Conclusion](#conclusion)

## Introduction

Afin d'obtenir un diplôme d'ingénieur, les élèves doivent maîtriser un certain niveau d'anglais, afin de contrôler et valider leurs connaissances en compréhension de l'anglais, le test du TOEIC *listening and writing* est employé, à l'issue de cet examen, une note sur 990 est attribuée. Chaque année certains élèves rencontre des difficultés à l'apprentissage de l'anglais et il en résulte des complications pour la validation de leur diplôme, voilà pourquoi un outil supplémentaire de révision peut s’avérer nécessaire.

Ce projet s'inscrit dans cette démarche en proposant un outil capable de contenir différentes leçons d'anglais ainsi que des exercices et un suivi des résultats de l'utilisateur. Un aspect de communauté est également mis en avant avec l'incorporation d'un forum afin de permettre aux divers utilisateurs d'échanger sur leur difficultés respectives en anglais et de s'entraider.

## Architecture du projet

Pour commencer, il faut définir les différentes classes utilisées par l'application.

<a href="https://imgbb.com/"><img src="https://i.ibb.co/vZkjqLn/Diagramme20de20classe.png" alt="Diagramme20de20classe" border="0"></a>

Le diagramme ci-dessus présente les entités utilisées par l'application. La classe user correspond aux comptes utilisateurs qui peuvent être des administrateurs ou non, les utilisateurs peuvent créer des fils de discussion constitués de plusieurs messages eux-mêmes écrient par d'autres utilisateurs.

L'application repose sur une architecture Clients/Serveurs à deux niveaux. PostgreSQL est utilisé pour la gestion de la base de donnée contenant essentiellement les informations relatives aux comptes des utilisateurs, les différents messages et fils de discussion de la partie forum, ainsi que les questions et leçons disponibles sur l'application.

Le serveur, quant à lui, est initialement généré via Gradle, puis est développé en Java à l'aide de Spring, un framework permettant d’accélérer le développement d'application. Spring permet de concevoir aisément des points d'entrées de type CRUD mais il est également utilisé pour la sécurité de l'application.

Pour finir, la partie client est conçue avec Vite puis repose sur Svelte, un framework permettant d'effectuer une part importante de sa charge de travail lors de la compilation. Cela permet également à l'application d'avoir un faible poids une fois compilée et permet donc de meilleure vitesse de chargement. Combiné à Svelte, le routage est traité via Svelte-kit et le style, par SvelteSrap, une alternative à BootStrap compatible avec Svelte.

## Mise en application

### Connexion
Pour commencer, sur son arrivé sur l'application web, l'utilisateur pourra uniquement se connecter, accéder aux leçons misent à disposition ou consulter un guide pour s'inscrire au test du TOEIC. Pour accéder aux autres fonctionnalités, il lui sera nécessaire de se connecter. Pour se faire, il lui faut rentrer son nom d'utilisateur et son mot de passe, le schéma d'authentification utilisé et la basic authentication. Le nom d'utilisateur et le mot de passe est haché en base 64 et est ajouté à l'header via un token d'authentification "Basic". Lorsque l'utilisateur parvient à se connecter, une variable isConnected est stockée en local ce qui évitera à l'utilisateur d'avoir à se reconnecter durant son utilisation de l'application. Pour que cette variable soit à nouveau fausse, l'utilisateur doit manuellement se déconnecter.

Une fois connecté, l'utilisateur sera redirigé vers la page d’accueil, présentant maintenant ses statistiques personnels tels que le nombre de test effectué ou la moyenne de ses résultats. un calendrier est également présent, il permet d'afficher une notification lors de date importante telle que la prochaine session du TOEIC.

Afin de comprendre la fonctionnement du site, les différentes fonctionnalités vont maintenant être passées en revue.

### Leçons

Tout d'abord les leçons, l'utilisateur a la possibilité de rechercher les leçons disponibles en base de donnée via un recherche sur leur titre ou sur le type de leçon. Il existe plusieurs points d'entrée GET disponibles publiquement pour obtenir une liste de leçons dont le titre comprend une chaîne de caractères spécifique où le type correspond à la valeur d'un menu déroulant.

L' information ainsi renvoyée est un titre qui fait office de lien hypertexte vers la page en question. Seul le lien, le titre et le type de la leçon sont stockés en base de donnée, le contenu de la leçon se trouve directement dans la page adaptée au niveau du serveur. Ce choix a été fait dans un soucis de limiter le poids des informations enregistrées en base de donnée ainsi que pour permettre une personnalisation plus simple de chaque page afin de permettre au contenu d'être plus percutant.

### Questions

Afin de mettre en application les informations des leçons, ou simplement pour s'entrainer en prévision du test du TOEIC, l'utilisateur peut se mettre à l’épreuve via une série de question. il doit pour cela choisir dans un premier temps le type de question sur lequel il veut s'entrainer. Il faut noter qu'actuellement seul les questions de type texte à trous sont disponible, ce système à cependant été conçut avec l'élaboration d'autres types de question à l'esprit.

Lorsque le type de question est sélectionné, une requête GET est envoyée afin d'obtenir au plus 10 questions du type définit. Si il y a moins de 10 questions de ce type présentent en base de données, toutes les questions possibles sont envoyées. Les questions de type texte à trou sont générées ainsi: Deux tableaux sont crées et contiennent une case par question, le premier contient les réponses renvoyées par le serveur, l'autre recevra les résultats de l'utilisateur. 

Pour chaque question, l'intitulé est ensuite affiché, suivi de 4 boutons de réponses. Les propositions sont également stockées en base de données sous la forme d'une chaîne de caractères où toutes les propositions sont séparées par une barre verticale. Cette chaîne est ensuite éclatée puis un bouton est créée par proposition, ce bouton renvoie sa propre valeur lorsqu'il est actionné et si cette valeur correspond à celle enregistrée dans le tableau des réponses, alors un message de bonne réponse est inséré dans la case équivalente du tableau des retours utilisateur. Si il s'agit de la mauvaise réponse, un message d'erreur suivi de la bonne réponse est insérer dans le tableau de retour.

L'utilisateur dispose actuellement d'autant de temps qu'il le souhaite pour répondre, chaque nouvelle réponse à une même question remplace la précédente. Lorsqu'il a terminé, il peut appuyer sur le bouton "valider", et il sera alors redirigé vers une autre page où ses résultat seront affichés. C'est également à ce moment que ses statistiques sont mises à jour.

### Forum

Afin de connecter différentes personnes dans la même situation, un espace de forum a également été implémenté. Lorsque l'utilisateur se rend sur la page du forum, une requête GET va tout d'abord récupérer tous les fils de conversation pour les lister. Les noms de ces fils, les noms d'utilisateur de leur créateurs ainsi que les derniers messages en date seront alors affichés.

Il est également possible de créer un nouveau fil de discussion en spécifiant un titre et en écrivant un premier message introduisant le sujet puis en appelant une méthode POST. Tout fil de conversation commence obligatoirement avec un premier message. D'autres utilisateurs peuvent ensuite se rendre sur ce fil de conversation et écrire à leur tour via une autre méthode POST. Pour le routage, une seule page sera appelée peu importe le chemin d'accès après "/forum/", seul le contenu de la page changera en fonction du fil de conversation appelé.

### Administrateur

Pour finir, certains utilisateurs ont le rang d'administrateur, cette distinction est enregistrée dans la base de donnée. Les administrateurs ont accès à un espace supplémentaire qui leur permet d’accéder à des end points inaccessibles au public.

Ils sont actuellement capable d'ajouter une question à la base de donnée via une méthode POST, seulement si tous les champs ont été correctement remplis et surtout si la bonne réponse figure parmi les quatre propositions.

### Vérification

Comme nous stockons des données utiles dans le LocalStorage, il est possible de les modifier en utilisant les devtools des navigateurs. Pour éviter que ces données puissent être modifiées pour changer son nom ou son état administrateur par exemple, à chaque chargement de page une vérification est faite avec le serveur pour être sûr que l'utilisateur connecté existe bien et qu'aucune donnée n'a été modifiée. 

## Conclusion

L'application ainsi réalisé représente un proof of concept pour un outil permettant aux étudiants en école d'ingénieur de réviser pour le TOEIC dans leur temps libre. Combiné avec les enseignements déjà en place dans le cursus, il peut leur permettre de se préparer au mieux afin de réussir ce test et valider leur diplôme.

Certains points sont encore en cours d'implémentation tels qu'un suivi plus efficace des performances de l'utilisateur ou la possibilité d'effectuer des exercices plus proches du test de compréhension écrite et oral du TOEIC.

Ce projet a permis à notre équipe de parfaire nos acquis en développement fullstack, un point clé de notre formation, et de nous familiariser avec de nouveau outils tels que Svelte et ainsi étendre notre répertoire.
