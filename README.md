# Pokedex App

## Overview
This project is a Pokedex application built using the MVI (Model-View-Intent) pattern, CLEAN architecture, and is modularized. The app leverages modern Android development practices including Jetpack Compose for UI, Kotlin coroutines for asynchronous programming, and Hilt for dependency injection.

## Architecture
The application follows the CLEAN architecture principles, which separates the code into different layers:
- **Presentation Layer**: Contains UI components and ViewModels.
- **Domain Layer**: Contains business logic and use cases.
- **Data Layer**: Manages data sources and repositories.

### MVI Pattern
The MVI pattern is used to manage the state and events in the application. It consists of:
- **Model**: Represents the state of the UI.
- **View**: Displays the state and sends user actions to the ViewModel.
- **Intent**: Represents user actions and events.

### Modularization
The project is divided into multiple modules to improve scalability and maintainability. Each module has a specific responsibility and can be developed and tested independently.

## Key Concepts and Libraries
- **Single Event Redux**: Ensures that events are handled only once, preventing issues like multiple navigation events.
    - [Single Event Redux Gist](https://gist.github.com/gmk57/330a7d214f5d710811c6b5ce27ceedaa)
    - [Single Event Redux with Kotlin Flow](https://proandroiddev.com/android-singleliveevent-redux-with-kotlin-flow-b755c70bb055)
- **Resilient Use Cases**: Use cases are designed to be resilient using `kotlin.Result`, coroutines, and annotations.
    - [Resilient Use Cases](https://proandroiddev.com/android-singleliveevent-redux-with-kotlin-flow-b755c70bb055)
- **Modularization and CLEAN MVI**: The project is structured to follow CLEAN architecture and MVI pattern.
    - [Domain Layer](https://developer.android.com/topic/architecture/domain-layer?hl=pt-br)
    - [Architecting Android the CLEAN Way](https://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/)
- **Scalable Jetpack Compose Navigation**: Implements scalable navigation using Jetpack Compose.
    - [Scalable Jetpack Compose Navigation](https://medium.com/bumble-tech/scalable-jetpack-compose-navigation-9c0659f7c912)

## Project Structure
```
pokedex/
├── app/                    # Application module
├── core/                   # Core module for shared utilities and base classes
├── basic-feature/          # Module for the basic feature
└── build.gradle            # Project-level Gradle configuration
```

## Getting Started
1. **Clone the repository**:
   ```sh
   git clone https://github.com/CapitalCoding/pokedex.git
   cd pokedex
   ```

2. **Open the project in Android Studio**:
    - Open Android Studio.
    - Select "Open an existing project".
    - Navigate to the cloned repository and select it.

3. **Build the project**:
    - Click on "Build" in the top menu.
    - Select "Make Project".

4. **Run the app**:
    - Connect an Android device or start an emulator.
    - Click on "Run" in the top menu and select "Run 'app'".

## Contributing
Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.

## License
This project is licensed under the MIT License. See the `LICENSE` file for more details.