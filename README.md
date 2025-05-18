# Projet programmation distribué

## Description
Deux microservices : 
- Une todolist
- Générateur de mot de passe sécurisé

## Technologies utilisées
- Springboot
- Docker
- kubernetes
- Istio

# Utilisation

## installations pré-requises
- Docker
- Minikube
- kubectl
- Istio

## Etapes
Pour plus de facilité mettez dans votre PATH les chemins menant aux commandes :
- istioctl
- minikube
- kubectl
- docker

Démarrer Docker et connectez-vous

Démarrer minikube
```
minikube start --driver=docker
```
Placer vous dans le dossier istio (istio-x.x.x) et executez les commandes :
```
istioctl install --set profile=demo -y
```
```
kubectl label namespace default istio-injection=enabled
```
```
kubectl apply -f samples/addons
```
Vous pouvez ensuite sortir du dossier
```
cd ..
```
Et executez la commande:
- Window (powershell !):
```
minikube -p minikube docker-env | Invoke-Expression
```
- Linux :
```
minikube docker-env
eval $(minikube -p minikube docker-env)
eval $(minikube docker-env)  
```
Maintenant récupérez le projet en local, placez-vous à la racine du projet et executez :
```
kubectl apply -f deployment.yml
```
Pour finir vous obtenez l'adresse avec :
```
kubectl -n istio-system port-forward deployment/istio-ingressgateway 31380:8080
```
Vous pouvez ensuite accéder aux microservices via les routes seront expliqué aux racines des deux microservice, le démarrage peut prendre quelques dizaines de secondes:

http://localhost:31380/todolist/

http://localhost:31380/passwdgeneration/



