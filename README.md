# LogiTemp — Sujet d'entraînement Bloc 3

**Expert en ingénierie du logiciel · RNCP35419 · C10 à C13 · sujet non officiel.**

Ce dépôt contient le **code de départ** de LogiTemp, un outil fictif de suivi des expéditions réfrigérées. Les anomalies et fichiers d'industrialisation incomplets sont **intentionnels** : ce n'est pas une application de référence à déployer en production.

## Périmètre

- Java 21 / Spring Boot 3 (backend REST), Spring Security + JWT et PostgreSQL ;
- Angular 17 (frontend fourni **en lecture seule pour les candidats**) ;
- quatre travaux : `LT-01` horodatage, `LT-02` CORS, `LT-03` import atomique, `LT-04` autorisation de libération ;
- tests JUnit de non-régression, Dockerfile backend/frontend, Docker Compose, GitLab CI et `DEPLOY.md` à compléter.

## Démarrage de l'exercice

1. Importez ce dépôt GitHub dans **votre propre projet GitLab** (l'épreuve évalue la traçabilité, le Kanban et la **pipeline GitLab**, non GitHub Actions).
2. Créez un Kanban GitLab avec les quatre tickets LT-01 à LT-04 ; une branche et des commits référencés par ticket.
3. Travaillez dans `backend/` et dans les fichiers d'infrastructure uniquement. **Ne modifiez pas `frontend/src/`**.
4. Les tests de validation initialement rouges sont attendus : ils doivent devenir verts grâce aux corrections du code, pas grâce à un affaiblissement des assertions.

### Lancement manuel (hors Docker Compose, à compléter durant l'exercice)

Prérequis : JDK 21, Maven 3.9+, Node 20+, npm, PostgreSQL 16.

```bash
docker run -d --name logitemp-db \
  -e POSTGRES_DB=logitemp -e POSTGRES_USER=logitemp \
  -e POSTGRES_PASSWORD=logitemp_dev -p 5432:5432 postgres:16-alpine
cd backend && mvn spring-boot:run
# Autre terminal :
cd frontend && npm install && npm start
```

- Interface Angular : http://localhost:4200
- API : http://localhost:8080/api
- Opérateur (local) : `operateur@logitemp.test` / `Demo1234!`
- Qualité (local) : `qualite@logitemp.test` / `Demo1234!`

L'interface rencontre volontairement une erreur CORS initiale. Les identifiants fournis sont fictifs ; ne les réutilisez jamais hors du TP.

## Contrats métier

| Méthode | Route | Attendu |
|---|---|---|
| POST | `/api/auth/login` | JWT |
| GET | `/api/shipments` | Liste des expéditions |
| GET | `/api/telemetry/{shipmentId}` | Relevés |
| POST | `/api/telemetry` | Enregistrement d'un relevé |
| POST | `/api/shipments/import` | Import groupé transactionnel (à faire) |
| PATCH | `/api/shipments/{id}/release` | Contrôle de rôle QUALITY (à faire) |

Voir `SUJET.md` pour l'énoncé, les cas détaillés et les critères d'acceptation. Les fichiers `exemples/` contiennent des données fictives.

**Attention :** l'installation de base ne doit pas être présentée comme validée par une compilation CI complète tant qu'elle n'a pas été exécutée dans l'environnement de l'établissement.
## Reproductibilité de l'environnement initial

Les dépendances frontend sont décrites dans `frontend/package.json`, mais aucun `package-lock.json` n'est livré dans cette première version. Les commandes `npm install`/`npm run build` ne constituent donc **pas encore une installation strictement déterministe**. Un verrouillage des dépendances et une recette CI complète devront être validés avant utilisation en certification blanche à grande échelle. La correction de ce point ne doit pas conduire à modifier les composants Angular imposés.
