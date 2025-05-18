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

# Résultat attendu

Les fonctionnalités des routes sont précisées dans la racine de chaque microservice, dans cette section je montre les routes et leurs résultats attendus, les GET sont fait sur navigateur et les PUT/POST/DELETE sont fait via l'application Postman

## /passwdgeneration/

Ce microservice n'a qu'une seule fonctionnalité, il génère des mot de passe sécurisé généré avec un random securisé (classe SecureRandom en java) et utilisant un shuffle (algo fishers-yates) pour augmenter l'aléatoire

### Routes :

![image](https://github.com/user-attachments/assets/e4cdebd3-940a-4a14-acf8-dae3e638fe6f)

---

**On génère des mots de passe**

![image](https://github.com/user-attachments/assets/235ca68f-0501-4270-8a3e-581b0f04261f)
![image](https://github.com/user-attachments/assets/337c3c9d-6212-43bc-a11d-593b867adfda)
![image](https://github.com/user-attachments/assets/93f4d8e8-96bb-41a4-90b3-2b352cc5c260)

---

## /todolist/

Ce microservice permet de créer, modifier, supprimer, consulter et lister des todolist

Il fait aussi appel au service passwdgeneration pour fournir un mot de passe lui aussi, cette fonctionnalité est la uniquement pour tester le servie mesh

Pour ce microservice je vais lister les routes dans l'ordre pour ajouter, consulter, lister, mettre à jour, supprimer des todolists en commençant par bien sûr montrer la racine en premier lieu et en finissant avec l'appel au microservice passwdgeneration pour fournir un mot de passe sécurisé

### Routes :

![image](https://github.com/user-attachments/assets/7d313724-4120-4297-8d47-5ede55da27cb)

---

#### On mets une valeur au paramètre title pour créer une liste ayant cette valeur pour titre

![image](https://github.com/user-attachments/assets/9e60e1fa-11a4-4c84-ad53-f45e10da32e8)
![image](https://github.com/user-attachments/assets/d71c4967-ad1d-4594-b902-17f0fb960780)

---

#### On liste les todolists et on constate l'ajout des deux todolists

![image](https://github.com/user-attachments/assets/dbbfdf0e-5535-4412-924b-60c7463956cb)

---

#### On consulte une todolist en particulier, pour l'instant elle est encore vide hormis le titre

![image](https://github.com/user-attachments/assets/d374351a-0d67-4032-87d5-67bb545a37f6)

---

#### On modifie le titre et ajoute des taches, pour l'update d'une todolist j'ai fait le choix de réécrire complètement la ressource, ainsi quand on veut l'update il faut renvoyer la ressours entière avec les partie modifiées et non modifiées

![image](https://github.com/user-attachments/assets/353595be-3ffe-4afa-bfd3-76ba3782f6de)

---

#### On reconsulte pour constater les changements

![image](https://github.com/user-attachments/assets/b7d13384-ad99-493e-bd63-27fb92caf561)

---

#### On re-update et afin de retirer une tache

![image](https://github.com/user-attachments/assets/172768d5-3810-4a54-997d-6c718da16923)

---

#### On reconstate les changements

![image](https://github.com/user-attachments/assets/d57179bb-e87e-493e-be7d-edfa3e9db467)

---

#### On choisis de delete cette todolist

![image](https://github.com/user-attachments/assets/954b2685-04dd-4c23-9771-06c1a4861fe5)

---

#### On vérifie la suppression

![image](https://github.com/user-attachments/assets/a4ed5eab-af10-4ae2-b028-4a3ec1ef204f)

---

#### Pour finir on fait appel au microservice passwd depuis notre service afin tester le service mesh, hormis tester le service mesh pour le montrer au prof cette route n'a pas d'utilité particulière

![image](https://github.com/user-attachments/assets/61523668-acb4-4b09-85ce-5109b4faa9bf)
