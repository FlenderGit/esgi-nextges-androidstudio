# NextGes

Version 1.0 - Par DELOEIL Tristan

## Description

NextGes-AndroidStudio est l'application Web écrite en Kotlin de NextGes. Celle-ci est une application servant d'intranet pour un établissement scolaire. Sur cette application, il est possible de voir ses cours, les notes, les événements à venir sur le campus.
## Description structure

Ce project utilise l'architecture standardisé de Android Studio pour ce qui touche à la partie design/visuelle. (res/...)
Concernant la partie technique, ce projet utilise la structure de fichier suivantes.


- app/src/main/java/fr/flender/nextges
	- models: Les différents models de données (ici, des data class)
	- repositories: Les repositories et les Data source de chaque entité
		- /source/firestore: Les fichiers servant à utiliser Firestore comme une base de donnée
		- ***DataSource.kt: La source de donnée de l'entité
		- ***Repository.kt: Le repository associé à l'entité
	- ui: Les pages et les adapters:
		- adapter: Les différents adapters pour les entités (dans un dossier propre si un adapter doit être utilisé dans plusieurs pages)
		- /***
			- ***Fragment.kt: Le fragment de la page (initialisation et events)
			- ***ViewModel.kt: Lien entre les données et les composants du fragment
			- ***ViewModelFactory.kt: Aide pour créer des ViewModel nécessitant un constructeur particulier
	- ***Activity.kt: Les différentes Activity/Pages principales du projet