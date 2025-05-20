# Asteroids Game

A modern implementation of the classic Asteroids arcade game using LibGDX framework.

## Overview

This project is a recreation of the iconic Asteroids arcade game, featuring:
- Smooth player movement with thrust and rotation
- Dynamic asteroid spawning from screen edges
- Collision detection and screen wrapping
- Score tracking and life system
- UI with game over and main menu screens

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Gradle 7.0 or higher
- LibGDX framework

## Project Structure

```
core/
├── src/
│   ├── main/
│   │   ├── java/io/github/asteroids_ta/
│   │   │   ├── constants/      # Game constants and configurations
│   │   │   ├── controllers/    # Game object controllers
│   │   │   ├── factory/        # Object creation factories
│   │   │   ├── managers/       # Game state and system managers
│   │   │   ├── models/         # Game object models
│   │   │   ├── screens/        # Game screens and UI
│   │   │   ├── systems/        # Core game systems
│   │   │   └── AsteroidsGame.java
│   │   └── assets/            # Game assets and resources
│   └── test/                  # Test files
└── build.gradle              # Gradle build configuration
```

## Key Features

### Player System
- Smooth movement with thrust and rotation
- Physics-based movement with damping
- Screen wrapping
- Collision detection with circular bounds
- Life system with multiple lives

### Asteroid System
- Dynamic spawning from screen edges with safe offset
- Random movement patterns and speeds
- Multiple asteroid textures
- Collision detection with circular bounds
- Screen wrapping
- Maximum asteroid limit

### Game Management
- Score tracking with time-based points
- Life management system
- Game state handling
- Screen transitions
- Background tiling system

### UI System
- Main menu with title and instructions
- In-game HUD with lives and score
- Game over screen with final score
- Clean and modern interface

## Controls

- **Arrow Keys** or **WASD**: Rotate ship
- **Up Arrow** or **W**: Thrust
- **Space**: Start game / Confirm
- **R**: Restart game
- **Escape**: Return to menu

## Game Mechanics

### Player Movement
- Rotation speed: 220 degrees/second
- Maximum speed: 400 units/second
- Thrust force: 30 units/second²
- Damping factor: 0.985

### Asteroid System
- Spawn interval: 2 seconds
- Maximum asteroids: 10
- Spawn offset from edges: 100 units
- Random speed range: 50-150 units/second

### Scoring
- Time-based scoring: 10 points per second
- Score interval: 1 second

### Lives
- Initial lives: 3
- Life lost on asteroid collision
- Game over when all lives are lost

## Development

### Automated Builds

The project uses GitHub Actions for continuous integration. Every push to the `develop` branch and pull requests automatically trigger builds:

- **Develop or Master Branch**: Builds and deploys a new jar file as an artifact
- **Pull Requests**: Builds and runs tests to ensure code quality
- **Build Status**: Check the [Actions](https://github.com/srgrmsh/asteroids_ta/actions) tab for current build status

### Running from IntelliJ IDEA

1. Open IntelliJ IDEA
2. Select "Open" and navigate to the project directory
3. Wait for the project to sync and index
4. Navigate to `alwjgl3/src/main/java/io/github/asteroids_ta/lwjgl3/Lwjgl3Launcher.java`
5. Click the green "Run" button or press Shift+F10
6. The game should launch in a new window

### Downloading from GitHub Actions and Running the Game

1. Go to the [Actions](https://github.com/srgrmsh/asteroids_ta) tab of the repository
2. Click on the latest successful workflow run
3. Scroll down to the "Artifacts" section
4. Download the `AsteroidsDesktopJar.zip` file
5. Extract from archive `Asteroids_TA-1.0.0.jar`

#### Running the Game
1. Install Java Runtime Environment (JRE) if not already installed:
   - Windows: Download from [Oracle](https://www.oracle.com/java/technologies/downloads/) or use [OpenJDK](https://adoptium.net/)
   - macOS: Use Homebrew: `brew install openjdk`
   - Linux: Use package manager: `sudo apt install default-jre` (Ubuntu/Debian)

2. Run the jar file:
   ```bash
   java -jar Asteroids_TA-1.0.0.jar
   ```

#### Troubleshooting
- If the game doesn't start, ensure Java is installed by running `java -version` in terminal/command prompt
- For graphics issues, update your graphics drivers
- If you get "Unable to access jarfile" error, ensure you're in the correct directory

## Acknowledgments

- LibGDX framework
- Original Asteroids game by Atari

## Contact

Project Link: [https://github.com/srgrmsh/asteroids_ta/tree/develop](https://github.com/yourusername/asteroids_ta)
