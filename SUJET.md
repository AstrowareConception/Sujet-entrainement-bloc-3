# Épreuve individuelle d'entraînement — Bloc 3 « LogiTemp »

**C10, C11, C12 et C13 • RNCP35419 • Niveau 7 • sujet fictif non officiel**

## Contexte

LogiTemp est une plateforme de traçabilité des expéditions réfrigérées exploitées par un groupement d'établissements de santé et de transporteurs. Les sondes IoT transmettent température et humidité ; le portail web permet de suivre les expéditions, leurs états et les relevés. Vous reprenez l'application Spring Boot + Angular + PostgreSQL livrée par un prestataire. La partie frontend est fournie : **vous ne devez pas la modifier**.

## Quatre remontées métier à traiter

### LT-01 — Horodatage décalé

Une mesure prise à Pointe-à-Pitre à `2026-10-10T09:15:00-04:00` ressort de l'API comme `09:15Z`, soit **05:15 à Pointe-à-Pitre**, quatre heures trop tôt. Elle doit être enregistrée comme l'instant absolu `2026-10-10T13:15:00Z`. Vérifiez aussi un autre fuseau et la persistance UTC en PostgreSQL.

### LT-02 — CORS depuis Angular

Le navigateur refuse les appels alors qu'un client API fonctionne. Autoriser explicitement `http://localhost:4200` et `http://localhost:8081` (pré-requêtes OPTIONS incluses), mais **pas toutes les origines**. Conserver la protection JWT des autres routes.

### LT-03 — Import de plusieurs expéditions

Implémentez `POST /api/shipments/import` acceptant `{"shipments":[{"trackingCode":"LT-ANT-1001","productName":"Produits biologiques","minTempC":2,"maxTempC":8}]}`. Contrôler codes uniques, produit renseigné et bornes cohérentes ; l'opération est **atomique**, sans persistance partielle en cas d'erreur. Définir des réponses HTTP cohérentes et les tester.

### LT-04 — Libération par un utilisateur non habilité

`PATCH /api/shipments/{id}/release` : seuls les utilisateurs `ROLE_QUALITY` peuvent faire passer le statut de `CREATED` à `RELEASED`. Pour `ROLE_OPERATOR`, réponse 403, état inchangé. Appel anonyme : 401. La méthode du service doit être protégée par `@PreAuthorize` (configuration déjà activée) ; tester le cas nominal et les refus. Cette exigence correspond au test de départ.

## Travail attendu

1. **Organisation GitLab** : importer ce dépôt Github dans un projet GitLab personnel ; créer un tableau Kanban avec À faire, En cours, En revue, Terminé ; consigner **quatre issues**, créer une branche par demande et référencer les issues dans les commits ; suivre l'avancement.
2. **Développement** : corriger les trois anomalies et réaliser l'évolution demandée **exclusivement dans le backend**, sans neutraliser l'authentification ; structurer domaine, service, contrôleur et validation.
3. **Tests** : exécuter les tests initiaux ; les compléter avec vérification des cas d'erreur et de la non-régression. Ne pas remplacer les vérifications par des assertions triviales.
4. **Docker** : compléter les Dockerfile backend multi-stage et frontend Node→Nginx, orchestrer PostgreSQL / API / UI dans `docker-compose.yml`, ajouter un volume de données et le healthcheck de PostgreSQL ; l'API doit attendre une base saine.
5. **CI/CD et documentation** : compléter `.gitlab-ci.yml` (build, tests, lint, puis publication conditionnelle des images dans le registre GitLab) ; compléter `DEPLOY.md` (prérequis, démarrage, diagnostic, persistance).

## Livrables

Dépôt GitLab avec suivi des issues, branches et historique ; code backend corrigé et testé ; Docker Compose opérationnel ; pipeline documentée ; `DEPLOY.md` utilisable par un tiers. En cas de remise par archive, utiliser `NOM_Prenom_Bloc3_LogiTemp.zip`.

## Contrats API de départ

| Méthode | Route | Rôle |
|---|---|---|
| POST | `/api/auth/login` | Connexion et JWT |
| GET | `/api/shipments` | Liste des expéditions |
| GET | `/api/telemetry/{shipmentId}` | Historique d'une expédition |
| POST | `/api/telemetry` | Nouveau relevé IoT |
| POST | `/api/shipments/import` | **À implémenter** |
| PATCH | `/api/shipments/{id}/release` | **À sécuriser** |

## Compétences évaluées

- **C10** — Développement des fonctionnalités, modèle métier, composantes externes, qualité du code.
- **C11** — Suivi des demandes utilisateurs, anomalies, corrections et évolutions.
- **C12** — Construction automatisée et tests pour préparer le déploiement continu.
- **C13** — Industrialisation, déploiement reproductible et documentation de reprise.

*Ce dépôt est un support d'entraînement non officiel ; les conditions, la durée et les outils autorisés dépendent de l'établissement.*
