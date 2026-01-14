# STAGE 1 : construire l'application
FROM node:20-alpine AS build

WORKDIR /app

# Installer Angular CLI (version next / 21)
RUN npm install -g @angular/cli@next

# Copier les fichiers de dépendances
COPY package*.json ./

# Installer les dépendances
RUN npm ci

# Copier le reste du projet
COPY . .

RUN ng build --configuration production

# STAGE 2 : construire le serveur web
FROM nginx:alpine

# Supprimer la config nginx par défaut
RUN rm /etc/nginx/conf.d/default.conf

# Copier la config nginx
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Copier le build Angular
COPY --from=build /app/dist/frontend/browser /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
