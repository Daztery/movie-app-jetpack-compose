# 🎬 Movie App – Jetpack Compose

A modern Android app that consumes the TMDB API to display genres, movie listings, detailed info with trailers, and more. Built using **Kotlin**, **Jetpack Compose**, and a clean, scalable architecture.

---

## 🚀 Main Features

- Browse movies by genre, popularity, top-rated, now playing, and more.
- Movie details screen with description, rating, cast, and **YouTube trailer**.
- Real-time search functionality.
- Supports **light and dark mode**.
- Modular architecture with MVVM, repositories, and clear separation of concerns.
- Dependency injection using **Hilt**.
- Efficient image loading with **Coil**.
- Smooth data pagination with **Paging 3**.
- (Optional) local persistence using Room or DataStore.
- Unit tests for domain and data layers.
- Integrated static code analysis tools (Detekt, lint) and test coverage (Jacoco).

---

## 🧩 Tech Stack & Architecture

- **Language:** Kotlin  
- **UI:** Jetpack Compose (Material 3)  
- **Architecture:** MVVM + Clean Architecture  
- **Libraries:** Hilt (DI), Retrofit + OkHttp, Coil, Paging 3  
- **Async:** Coroutines + Flow  
- **Persistence:** Room / DataStore (optional)  
- **Testing:** Unit tests (run with `./gradlew test`)  
- **Code quality:** Detekt, Ktlint, Jacoco

---

## 🛠️ Getting Started

1. Clone the repository:  
   ```bash
   git clone https://github.com/Daztery/movie-app-jetpack-compose.git
   ```
2. Open the project in **Android Studio**.
3. Add your TMDB API key to `local.properties`:  
   ```
   TMDB_API_KEY="YourKeyHere"
   ```
4. Sync Gradle and run the app from Android Studio.

---

## 🧪 How to Test

- To run unit tests for the data layer:  
  ```bash
  ./gradlew :data:test
  ```
- You can check test coverage under `build/reports`.

---

## 🧑‍💻 Portfolio Highlights

This project demonstrates:

- Clean, modular architecture (MVVM / Repository Pattern).
- Modern UI with Jetpack Compose and Material 3.
- Integration with remote APIs and async data flow.
- Scalable codebase with testing and quality tools.

---

## 🧭 Future Improvements

- Advanced filtering (by year, genre, etc.).
- Add favorites with local persistence.
- Expand test coverage: UI instrumentation, mocking, CI reports.
- Offline support and local caching.
- Full CI/CD pipeline: build, test, lint, coverage, deploy.

