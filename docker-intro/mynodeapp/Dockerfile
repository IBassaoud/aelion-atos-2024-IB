# Env de base
FROM node:20-alpine3.18

WORKDIR /app

# Copier les fichiers du projet
COPY package.json ./

# Installer les dépendances
RUN npm i

# Copier le reste des fichiers
COPY . .

# Exposer le port sur lequel l'application s'exécute
EXPOSE 80

# Commande pour démarrer l'application
CMD [ "npm", "start" ]