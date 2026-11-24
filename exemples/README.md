# Fichiers de recette LogiTemp — cas d'entraînement

Ces fichiers sont des **entrées fictives** correspondant aux contrats et critères d'acceptation du sujet. Ils ne contiennent **aucune implémentation** ni correction.

| Fichier | Endpoint | Ce que doit vérifier le candidat |
|---|---|---|
| `releve_pointe_a_pitre.json` | `POST /api/telemetry` | La source `09:15-04:00` représente `13:15Z` ; la lecture `GET /api/telemetry/{shipmentId}` doit restituer le même instant. |
| `releve_avec_offset.json` | `POST /api/telemetry` | Un deuxième fuseau : `11:30+02:00` représente `09:30Z`. |
| `manifest_valide.json` | `POST /api/shipments/import` | Deux expéditions valides ajoutées ensemble. |
| `manifest_bornes_invalides.json` | `POST /api/shipments/import` | Une entrée valide suivie d'une entrée où `minTempC > maxTempC` : **aucune** des deux ne doit être enregistrée. |
| `manifest_rejet.json` | `POST /api/shipments/import` | Doublon par rapport à une expédition initiale `LT-VAL-0182` : refus du lot, sans écriture partielle. |

**Conditions de recette :** base PostgreSQL initialisée, token JWT obtenu par `POST /api/auth/login`, et identifiants des expéditions récupérés par `GET /api/shipments`. N'assumez pas que `shipmentId=1` existe si la base a déjà été utilisée.

**Point important :** les tests JUnit initialement présents ne valident pas encore, à eux seuls, toute l'atomicité SQL ou les refus HTTP. Les candidats doivent étendre la couverture avec des tests d'intégration et de sécurité pertinents.

Pour la libération qualité, vérifier **401** sans authentification, **403** avec un compte `ROLE_OPERATOR` et le succès sous `ROLE_QUALITY`. L'existence d'une annotation seule n'est pas une preuve fonctionnelle suffisante.

La soumission des données dans un ordre différent et la reprise d'une base propre doivent également être testées pour éviter de confondre un état résiduel avec un comportement correct.
