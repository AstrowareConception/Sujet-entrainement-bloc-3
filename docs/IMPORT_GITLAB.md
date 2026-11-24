# Importer LogiTemp dans GitLab pour l'épreuve

Le dépôt GitHub public est le **socle de travail commun** ; l'épreuve demande ensuite des **issues, un Kanban, des branches, des commits et une pipeline GitLab**.

## Option A — Import par URL

Dans GitLab, créez un projet à partir de l'URL :
`https://github.com/AstrowareConception/Sujet-entrainement-bloc-3.git`

La disponibilité de l'option *Importer par URL* dépend des droits et paramètres de l'instance GitLab de l'établissement.

## Option B — Clonage, puis publication dans un projet GitLab vide

Après avoir créé **un projet GitLab vide**, sans README initial :

```bash
git clone https://github.com/AstrowareConception/Sujet-entrainement-bloc-3.git
cd Sujet-entrainement-bloc-3
git remote rename origin upstream
git remote add origin https://gitlab.example.org/VOTRE_GROUPE/logitemp-bloc3.git
git push -u origin main
```

Remplacez l'URL `gitlab.example.org/...` par **l'adresse réelle fournie par votre établissement**. Ne laissez jamais des identifiants personnels dans les fichiers du dépôt.

### Traçabilité évaluée

- Un tableau Kanban au minimum : À faire, En cours, En revue, Terminé.
- Quatre issues correspondant aux remontées **LT-01 à LT-04**.
- Une branche par issue et des commits de développement traçables.
- Des tests démontrant la correction des quatre exigences, puis Docker et la pipeline.
- La documentation `DEPLOY.md` complétée et reproductible.

### Important

La présence du dépôt sur GitHub **ne remplace pas la pipeline GitLab**. Le fichier `.gitlab-ci.yml` est volontairement incomplet et ne sera pas exécuté par GitHub Actions. Le résultat du déploiement attendu doit être démontré dans l'environnement GitLab de l'épreuve.

**Matériel fictif de formation : n'intégrez aucune donnée de santé ou de transport réelle dans votre dépôt étudiant.**