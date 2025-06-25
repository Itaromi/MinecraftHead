# 🧱 MinecraftHead

Une application Android minimaliste et responsive permettant d'afficher les têtes Minecraft de vos joueurs préférés via l'API [mc-heads.net](https://mc-heads.net).

---

## 📱 Aperçu

| Accueil | Favoris |
|--------|--------|
| ![Home](screenshots/home.png) | ![Favorites](screenshots/favorites.png) |

---

## ✨ Fonctionnalités

- 🔍 Rechercher et visualiser une tête Minecraft (pseudo)
- ⭐ Ajouter/retirer des pseudos en favoris (persistés localement)
- 📦 Télécharger les têtes en un clic
- 📱 Interface responsive Material 3
- 🌙 Thème personnalisé

---

## ⚙️ Stack technique

- **Langage** : Kotlin
- **UI** : Jetpack Compose + Material 3
- **Architecture** : MVVM + Repository
- **Persistance** : `DataStore Preferences`
- **Navigation** : Navigation Compose
- **Internationalisation** : 🇫🇷 / 🇬🇧 avec `strings.xml`
- **Design** : Thème couleur personnalisé

---

## 🔌 API utilisée

- 🎮 [mc-heads.net](https://mc-heads.net/)
    - `https://mc-heads.net/avatar/{username}`
    - `https://mc-heads.net/download/{username}`

---

## 🚀 Installation

```bash
git clone https://github.com/tonpseudo/MinecraftHead.git
cd MinecraftHead
./gradlew build
