# Console PlantsVsZombies Game

> University project developed in Java to simulate the popular "Plants vs Zombies" game using a Command Line Interface (CLI) and strict Object-Oriented Programming principles.

## Overview
This application is a turn-based strategy game where players must strategically place plants to defend against waves of zombies. It implements a robust **Model-View-Controller (MVC)** architecture and uses the console as a GUI to render the board, handle user commands, and track game state.

## Key Features
* **MVC Architecture:** clearly separates the Game Logic (`logic`), User Input (`control`), and Visualization (`view`).
* **Command Pattern:** Interactions are handled via specific commands (e.g., `AddPlant`, `List`, `Reset`) parsed by a central controller.
* **Difficulty & Seeds:** Supports custom difficulty levels (EASY, HARD, INSANE) and Random Number Generation (RNG) seeds for reproducible games.
* **High Scores:** Persists record scores to a file (`record.txt`), loading and saving them automatically.
* **OOP Design:** Utilizes polymorphism, interfaces (`GameAction`), and abstract classes (`Zombie`, `Command`) to manage game entities.

## Tech Stack
* **Language:** Java
* **Paradigm:** Object-Oriented Programming (OOP)
* **Design Patterns:** Factory (for Zombies/Plants), Command, MVC.

## How to Run
1.  Clone the repository.
2.  Open your terminal in the root source folder.
3.  Compile and run:

    ```bash
    # Compile all java files (ensure to include subdirectories)
    javac tp1/p2/*.java tp1/p2/**/*.java  # Use javac (Get-ChildItem -Recurse *.java).FullName on Windows PowerShell

    # Run (Requires Level argument: EASY, HARD, INSANE)
    # Format: java tp1.p2.PlantsVsZombies <LEVEL> [SEED]
    java tp1.p2.PlantsVsZombies EASY 1234
    ```

## Authors and Collaboration
This project was developed as a team effort for the **TECNOLOGÍA DE LA PROGRAMACIÓN I** course.

* **Cameron Sánchez** - https://github.com/cameronsanchezb
* **Carlos Doval** - https://github.com/cakedov3-svg

---
Licensed under the [MIT License](LICENSE).