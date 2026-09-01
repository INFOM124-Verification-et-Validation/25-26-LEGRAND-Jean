# INFOM124 Vérification et validation logicielle : Examen (septembre 2026)

- Nom : *indiquez ici votre nom*
- Prénom : *indiquez ici votre prénom*

## Consignes générales

- La durée maximale de l’examen est de **3 heures**.
- L'examen est à cours ouvert.
- L'examen est individuel, la communication avec autrui est interdite. En particulier l'utilisation de messagerie instantanée, de mails ou de tout autre système de partage d'information avec d'autres étudiant.e.s passant l'examen seront considérés comme une tricherie.
- Les réponses (aussi appelés rapports) doivent être indiquées dans les espaces prévus à cet effet dans le présent document, ainsi qu'éventuellement dans le code source remis au début de l'examen.

## Méthode de cotation

Le but de l'examen est de démontrer votre capacité à analyser la qualité globale d'une codebase aux moyens des outils et techniques vues en cours.

Pour vous aider à adopter une approche structurée, des "pistes guidées" vous sont fournies plus loin dans ce document. Ces pistes, comme leur nom l'indique, ont pour but de vous orienter vers des zones de la codebase dans lesquels des problèmes sont assurément présents. Elles sont également divisées en sous-questions ayant pour but de vous aider à structurer votre manière de rapporter les soucis de qualité en question.

**Il n'est pas nécessaire de répondre à toutes les sous-questions afin d'engranger des points pour cette question.** Au plus vous allez loin, au plus la piste vous rapportera de points. Très concrètement, le rapport que vous faites sous chaque piste guidée sera évalué comme suit :

- *Insuffisant* : le rapport est vide ou totalement erroné (0 points).
- *Insuffisfaisant* : le rapport est parcellaire ou "à côté de la plaque", mais comporte des éléments factuels **et** originaux(*) (0.25 point).
- *Satisfaisant* : le diagnostic et la correction proposés dans le rapport sont corrects et étayés (0.5 point).
- *Bon* : le diagnostic, la correction et l'action préventive proposés dans le rapport sont corrects et étayés (0.75 point).
- *Très bon* : le diagnostic, la correction et l'action préventive proposés dans le rapport sont corrects et étayés et la correction a été appliquées dans le code source (1 point).

(*) Par "originaux", on entend un élément ayant fait l'objet d'une réflexion ou interprétation par l'étudiant ; recopier textuellement un morceau du rapport de SonarQube, Checkstyle ou autre ne vous accordera pas de points.

Les pistes guidées sont réparties en 3 catégories liées au type de problème abordé (code smells, defects, bad development practices). Le seuil minimum de réussite (10/20) est atteint si vous répondez de manière satisfaisante à 4 questions, **avec au moins 1 question par catégorie**.

Votre score final sera alors :

- 4/20 (si vous avez répondu de manière satisfaisante à une seule catégorie).
- 7/20 (si vous avez répondu de manière satisfaisante à deux catégories)
- 10 + X(**) (si vous avez répondu de manière satisfaisante à trois catégories)

(**) X étant une "marge de réussite" comprise entre 0 et 10 et calculée sur base de la qualité des réponses fournies aux différentes pistes.

Veuillez noter qu'une piste additionnelle (*Analyse complémentaire*) est ouverte et vous demande de faire une analyse globale de la qualité du projet sur base des différents rapports d'analyse qui vous sont fournis. Celle-ci compte dans la marge de réussite ci-dessus.

## Projet à analyser

Le projet à tester est un jeu d'échec où les joueurs s'affrontent sur un plateau de 64 cases alternées en noir et blanc. L'échiquier est orienté avec une case blanche en bas à droite. Chaque joueur commence avec 16 pièces : 8 pions (pawns), 2 tours (rooks), 2 cavaliers (knights), 2 fous (bishops), 1 dame (queen), 1 roi (king). Les pièces blanches sont placées sur les deux premières rangées, les noires sur les deux dernières.

L’objectif est de *mater* le roi adverse (échec et mat), c’est-à-dire de le mettre dans une position où il est attaqué et ne peut pas s’échapper. Une partie peut aussi se terminer par un nul (match nul) dans certains cas.

Chaque type de pièce se déplace selon des règles spécifiques. Le roi peut se déplacer d'une case dans toutes les directions. La dame se déplace horizontalement, verticalement ou en diagonale sur n'importe quelle distance. La tour se déplace horizontalement ou verticalement sur n'importe quelle distance, tandis que le fou se déplace uniquement en diagonale. Le cavalier suit un mouvement particulier en "L" (deux cases dans une direction, puis une case perpendiculaire) et peut sauter par-dessus d'autres pièces. Le pion avance d'une case vers l'avant (ou de deux cases lors de son premier mouvement) et capture en diagonale. Lorsqu'un pion atteint la dernière rangée, il est promu en dame, tour, fou ou cavalier au choix du joueur.

Lorsqu'un roi est attaqué, il est en échec. Le joueur doit alors réagir immédiatement en déplaçant le roi, en bloquant l'attaque avec une autre pièce ou en capturant la pièce menaçante. Si aucune de ces options n'est possible, le roi est en échec et mat, et la partie est perdue. La promotion d’un pion se produit lorsqu'il atteint la dernière rangée et peut être transformé en une autre pièce. La partie se termine de plusieurs façons. Une victoire est obtenue par échec et mat, abandon de l'adversaire ou dépassement de temps dans une partie chronométrée. Un nul peut survenir dans divers cas, tels que le pat (lorsque le joueur au trait n’a aucun coup légal et que son roi n’est pas en échec), un accord mutuel entre les joueurs, ou lorsque le matériel restant ne permet pas de mater. *À noter que la détection d'un échec ou de la fin de partie ne sont pas implémentés dans le projet.*

### Structure du projet

Le projet utilise Maven. Pour rappel, la structure d'un projet Maven est la suivante :

- `src/main/java` contient le code source de l'application.
- `src/test/java` contient les tests de l'application.
- `target/` contient les résultats du *build* (code compilé, résultats des tests, rapports d'analyse, etc.).

La classe `be.unamur.chess.ChessGame` est la classe principale qui gère le lancement d'une partie.

### Configuration du build

La commande suivante permet de lancer le *build* du projet, à savoir la compilation et l'exécution des tests :

```
mvn clean package
```

Si Maven n'est pas installé sur votre machine, vous pouvez également exécuter depuis la racine du projet (sur Linux et MacOS) :

```
./mvnw clean package
```

Ou sur Windows :

```
mvnw.cmd clean package
```

La configuration du build inclus déjà toute une série d'outils d'analyse :

- Checkstyle, un outil d'analyse statique permettant de vérifier que les conventions d'écriture de code sont respectées. L'outil offre une configuration par défaut qui peut être adaptée et customisée selon les besoins.
- PMD, un outil d'analyse statique permettant de repérer les erreurs de programmation courantes.
- SpotBugs, un outil d'analyse statique permettant de repérer des bugs.
- JaCoCo, un outil d'analyse dynamique fournissant des indications sur la couverture structurelle des tests.
- PIT, un outil d'analyse dynamique permettant d'effectuer une analyse de mutation.

**Note :** L'archive .zip qui vous a été remise contient déjà une copie des différents rapports pour la version du projet dans le dossier `rapports/`. **Ces rapports sont suffisants pour répondre à la plus grande partie des pistes ci-après.** Si vous voulez aller plus loin, il est également possible de re-générer ces rapports via la commande suivante (ou `./mvnw` ou `mvnw.cmd`) :

```
./mvnw clean test org.pitest:pitest-maven:1.21.1:mutationCoverage site
```

Une fois la commande exécutée, les rapports générés sont disponibles dans `target/site/index.html` (sous le menu à gauche *Project Reports*).

Le rapport SonarQube est disponible sur le site de [SonarCloud](https://sonarcloud.io/project/overview?id=INFOM124-Verification-et-Validation_25-26-PETIT-Michael)

****************************************************************************************

## Pistes guidées - Mauvaises pratiques de développement

### Umbrae Surgant!

Lors de la dernière sprint review, un développeur de l'équipe a fait remonter le problème suivant :

> J’ai essayé d’échanger les positions de départ du roi et de la reine pour un test rapide, mais j’ai failli casser toute l’initialisation. La méthode `ChessModel.initializeBoard` n’est qu’une longue liste répétitive d’affectations de coordonnées comme `boardState[0][3]` et `boardState[7][4]`. Une petite erreur dans un nombre, et une pièce se retrouve sur la mauvaise case ou en écrase accidentellement une autre. Il est également impossible de voir d’un coup d’oeil si toutes les pièces sont correctement placées sans compter soigneusement les lignes. Si nous voulons un jour prendre en charge différentes tailles de plateau ou variantes, nous devrons réécrire toute cette méthode à partir de zéro, car elle est trop étroitement liée à des indices spécifiques.

#### Technique(s) utilisée(s) pour poser le diagnostic

*Expliquez comment vous avez identifié le problème. Pensez à indiquer sur quoi vous vous basez pour confirmer la nature du problème (mesures, rapport d'analyse, etc.).*

#### Diagnostic

*Indiquez ci-dessous le problème identifié et les impacts qu'il pourrait avoir sur la maintenabilité du code.*

#### Action(s) corrective(s)

*Indiquez comment corriger le problème. Alternativement, effectuez la correction dans le code source et indiquez-le ci-dessous, nous irons alors voir dans le code directement.*

#### Pratique(s) de développement à modifier

*Indiquez ci-dessous la ou les pratiques de développement à mettre en place pour empêcher que cette mauvaise pratique ne se répande dans le projet. Indiquez les techniques et/ou outils à utiliser pour prévenir ce genre de mauvaise pratique. Nous attendons plus ici qu'une réponse générique de type "il faut faire de la review de code" ou "il faut utiliser SonarQube". Par exemple, indiquez ce qu'il faut prendre en compte dans la review et ce sur quoi le reviewer peut se baser pour identifier la mauvaise pratique.*


******************************************

### Piece of Cake

Lors de la dernière sprint review, un développeur de l'équipe a fait remonter le problème suivant :

> J’ai fait confiance à la documentation de la classe `Piece` qui indique que la méthode `getValidMoves` filtre automatiquement les coups mettant le roi en échec. J'ai passé des heures à chercher pourquoi mon IA jouait des coups suicidaires, pour finalement m'apercevoir qu'aucune sous-classe (Pion, Cavalier, etc.) n'implémente réellement cette vérification.

#### Technique(s) utilisée(s) pour poser le diagnostic

*Expliquez comment vous avez identifié le problème. Pensez à indiquer sur quoi vous vous basez pour confirmer la nature du problème (mesures, rapport d'analyse, etc.).*

#### Diagnostic

*Indiquez ci-dessous le problème identifié et les impacts qu'il pourrait avoir sur la maintenabilité du code.*

#### Action(s) corrective(s)

*Indiquez comment corriger le problème. Alternativement, effectuez la correction dans le code source et indiquez-le ci-dessous, nous irons alors voir dans le code directement.*

#### Pratique(s) de développement à modifier

*Indiquez ci-dessous la ou les pratiques de développement à mettre en place pour empêcher que cette mauvaise pratique ne se répande dans le projet. Indiquez les techniques et/ou outils à utiliser pour prévenir ce genre de défauts. Nous attendons plus ici qu'une réponse générique de type "il faut faire de la review de code" ou "il faut utiliser SonarQube". Par exemple, indiquez ce qu'il faut prendre en compte dans la review et ce sur quoi le reviewer peut se baser pour identifier la mauvaise pratique.*

****************************************************************************************

## Pistes guidées - Code smells

Les codes smells sont issus de la liste [Refactoring Guru](https://refactoring.guru/refactoring/smells/).

### Modern Times

Lors de la dernière sprint review, un développeur de l'équipe a fait remonter le problème suivant :

> J’ai passé beaucoup trop de temps à corriger une erreur de limite dans la logique de déplacement de la Tour, pour me rendre compte plus tard que le Fou avait exactement le même bug. J’ai dû copier-coller manuellement la correction et ajuster soigneusement les incréments de direction à plusieurs endroits. C’est frustrant, parce que la logique principale est presque identique dans les deux fichiers. Je crains que si nous ajoutons d’autres pièces avec un comportement de déplacement similaire, comme la Dame, nous nous retrouvions avec le même code répétitif dispersé partout, ce qui rendrait sa synchronisation très difficile.

#### Technique(s) utilisée(s) pour poser le diagnostic

*Expliquez comment vous avez identifié le ou les code smells. Pensez à indiquer sur quoi vous vous basez pour confirmer la nature du problème (mesures, rapport d'analyse, etc.).*

#### Diagnostic

*Indiquez ci-dessous le ou les code smells identifiés et les impacts qu'ils pourraient avoir sur la maintenabilité du code.*

#### Action(s) corrective(s)

*Indiquez comment corriger le problème. Alternativement, effectuez la correction dans le code source et indiquez-le ci-dessous, nous irons alors voir dans le code directement.*

#### Pratique(s) de développement à modifier

*Indiquez ci-dessous la ou les pratiques de développement à mettre en place pour empêcher que ce ou ces codes smells ne réapparaissent dans le project à l'avenir. Indiquez les techniques et/ou outils à utiliser pour prévenir ce genre de code smells. Nous attendons plus ici qu'une réponse générique de type "il faut faire de la review de code" ou "il faut utiliser SonarQube". Par exemple, indiquez ce qu'il faut prendre en compte dans la review et ce sur quoi le reviewer peut se baser pour identifier les code smells.*

******************************************

### I Want It All

Lors de la dernière sprint review, un développeur de l'équipe a fait remonter le problème suivant :

> Je voulais écrire quelques tests unitaires pour vérifier des séquences de coups complexes, mais j’ai découvert que la logique de validation et d’exécution des coups est enfouie dans le gestionnaire de clics du contrôleur de l’interface utilisateur. Je ne peux pas facilement tester si un coup est valide ni mettre à jour l’état de l’échiquier sans impliquer le `ChessController` et ses dépendances.

#### Technique(s) utilisée(s) pour poser le diagnostic

*Expliquez comment vous avez identifié le ou les code smells. Pensez à indiquer sur quoi vous vous basez pour confirmer la nature du problème (mesures, rapport d'analyse, etc.).*

#### Diagnostic

*Indiquez ci-dessous le ou les code smells identifiés et les impacts qu'ils pourraient avoir sur la maintenabilité du code.*

#### Action(s) corrective(s)

*Indiquez comment corriger le problème. Alternativement, effectuez la correction dans le code source et indiquez-le ci-dessous, nous irons alors voir dans le code directement.*

#### Pratique(s) de développement à modifier

*Indiquez ci-dessous la ou les pratiques de développement à mettre en place pour empêcher que ce ou ces codes smells ne réapparaissent dans le project à l'avenir. Indiquez les techniques et/ou outils à utiliser pour prévenir ce genre de code smells. Nous attendons plus ici qu'une réponse générique de type "il faut faire de la review de code" ou "il faut utiliser SonarQube". Par exemple, indiquez ce qu'il faut prendre en compte dans la review et ce sur quoi le reviewer peut se baser pour identifier les code smells.*

****************************************************************************************

## Pistes guidées - Defects

### Always Two There Are

Suite à la dernière release du projet, l'équipe de développement a reçu le rapport de bug suivant :

> Les pions peuvent toujours avancer de deux cases. Cela enfreint la règle selon laquelle l’avance initiale de deux cases n’est autorisée qu’à partir de la rangée de départ.

Pour reproduire le problème :

1. Démarrer une nouvelle partie.
2. Déplacer un pion blanc de e2 à e4 (ligne 6 à ligne 4 dans les coordonnées internes).
3. Les noirs effectuent n’importe quel coup.
4. Tenter de déplacer le pion blanc de e4 à e6 (ligne 4 à ligne 2).

#### Technique(s) utilisée(s) pour poser le diagnostic

*Comment pourriez-vous vous y prendre pour trouver ce defect de manière systématique ?*

#### Diagnostic

*Indiquez ci-dessous le défaut identifié et **ajouter le test unitaire permettant d'isoler le défaut au code source**.*

#### Action(s) corrective(s)

*Indiquez comment corriger le défaut. Alternativement, effectuez la correction dans le code source et indiquez-le ci-dessous, nous irons alors voir dans le code directement.*

#### Pratique(s) de développement à modifier

*La défaillance se traduit ici par une erreur de logique dans le jeu. Indiquez ci-dessous la ou les pratiques de développement à mettre en place pour empêcher qu'un problème similaire ne survienne à nouveau à l'avenir dans cette classe ou dans une autre classe. Indiquez les techniques et/ou outils à utiliser pour prévenir ce genre de défauts. Nous attendons plus ici qu'une réponse générique de type "il faut écrire des tests" ou "il faut faire de la review de code". Par exemple, pour du test, précisez le ou les critères de couverture qui auraient mené à la découverte du défaut.*

******************************************

### The Legend of Sleepy Hollow

Suite à la dernière release du projet, l'équipe de développement a reçu le rapport de bug suivant :

> La pièce du cavalier se déplace occasionnellement de deux cases en diagonale, comme un fou, au lieu de suivre son déplacement en L. De plus, le jeu plante ou se fige lorsqu’un cavalier tente de se déplacer près des bords inférieur ou droit de l’échiquier.

Pour reproduire le problème :

1. Placez un cavalier sur une case centrale, par exemple d4.
2. Essayez de le déplacer de deux cases en diagonale, par exemple vers f6.
3. Déplacez un cavalier vers le bord de l’échiquier, par exemple sur la rangée 7 ou la colonne g.
4. Essayez d’effectuer un mouvement qui arriverait sur le bord ou juste à l’extérieur, par exemple un mouvement ciblant un hypothétique "index 8".

#### Technique(s) utilisée(s) pour poser le diagnostic

*Décrivez la manière dont vous vous y prennez pour trouver le défaut (defect) sous-jacent qui déclenche la défaillance (failure) décrite dans ce rapport de bug lors de l'exécution. Concentrez-vous ici sur ce défaut et cette défaillance en particulier.*

#### Diagnostic

*Indiquez ci-dessous le défaut identifié et **ajouter le test unitaire permettant d'isoler le défaut au code source**.*

#### Action(s) corrective(s)

*Indiquez comment corriger le défaut. Alternativement, effectuez la correction dans le code source et indiquez-le ci-dessous, nous irons alors voir dans le code directement.*

#### Pratique(s) de développement à modifier

*La défaillance se traduit ici par une RuntimeException. Il s'agit d'une erreur courante en Java. Indiquez ci-dessous la ou les pratiques de développement à mettre en place pour empêcher qu'un problème similaire ne survienne à nouveau à l'avenir dans cette classe ou dans une autre classe. Indiquez les techniques et/ou outils à utiliser pour prévenir ce genre de défauts. Nous attendons plus ici qu'une réponse générique de type "il faut écrire des tests" ou "il faut faire de la review de code". Par exemple, pour du test, précisez le ou les critères de couverture qui auraient mené à la découverte du défaut.*

****************************************************************************************

## Analyse complémentaire

*Cette dernière question est ouverte et vous demande de faire une analyse globale de la qualité du projet sur base des différents rapports d'analyse qui vous sont fournis. Vous pouvez également indiquer des recommandations à mettre en place (pratiques de développement à modifier) afin d'améliorer et de maintenir le niveau de qualité du projet.*
