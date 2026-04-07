# 🛒 ShopEasy — Spring Boot Online Shopping App

A full-stack Online Shopping application built with **Spring Boot 3**, **MySQL**, **Thymeleaf**, and deployed on **Kubernetes (kubeadm)**.

---

## ✨ Features

| Role      | Features |
|-----------|----------|
| Customer  | Register, Login, Browse products by category, Add to Cart, Place Orders, View Order History |
| Admin     | Dashboard (stats), Add/Delete Products (with image), View/Delete Customers, Manage Order Status |

---

## 🏗️ Tech Stack

- **Backend**: Spring Boot 3.3.4, Spring Data JPA, Lombok
- **Frontend**: Thymeleaf, Bootstrap 5
- **Database**: MySQL 8.0 (StatefulSet in K8s)
- **Containerization**: Docker (multi-stage build)
- **Orchestration**: Kubernetes via kubeadm, NodePort service

---

## 🚀 Getting Started

### 1. Build & Push Docker Image

> Edit `docker_build_push.sh` — replace `yourdockerhubuser` with your Docker Hub username.

```bash
chmod +x docker_build_push.sh
./docker_build_push.sh
```

### 2. Update Image Name in K8s Manifest

Edit `kube_scripts/app-deploy-svc.yml`:
```yaml
image: yourdockerhubuser/spring-shop-app:latest
```

### 3. Deploy to Kubernetes

```bash
chmod +x k8s-deploy.sh
./k8s-deploy.sh
```

Use the interactive menu:
- **Option 1** → Deploy MySQL first
- **Option 2** → Deploy the Spring Boot app

### 4. Access the App

```
http://<NODE_IP>:30087
```

---

## 🔐 Admin Login

| Username | Password |
|----------|----------|
| admin    | admin    |

---

## 📁 Project Structure

```
spring_shop_app/
├── src/main/java/com/shop/
│   ├── controller/         # HomeController, AdminController, CustomerController
│   ├── entity/             # Customer, Product, CartItem, Order
│   ├── repository/         # JPA Repositories
│   └── service/            # Service interfaces + implementations
├── src/main/resources/
│   ├── templates/          # Thymeleaf HTML pages
│   └── application.properties
├── kube_scripts/
│   ├── db-statefulset-svc.yml   # MySQL StatefulSet + ClusterIP Service
│   ├── app-deploy-svc.yml       # App Deployment + NodePort Service
│   └── setup-storage.sh         # local-path-provisioner setup
├── Dockerfile              # Multi-stage Docker build
├── docker_build_push.sh    # Build & push to Docker Hub
├── k8s-deploy.sh           # Interactive K8s deploy menu
└── pom.xml
```

---

## ⚙️ Configuration

`application.properties` is pre-configured for Kubernetes (uses `mysql-service` hostname).  
For local development, update the datasource URL to point to `localhost:3306`.
