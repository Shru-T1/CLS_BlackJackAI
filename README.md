# CLS_BlackJackAI
AIBlackJack
This game is made by Shruti Sharma and Ben Ruelas. AIBlackJack is a application model for the well-known game of BlackJack made using Aritifcial Intelligence. The Model follows the basic rules:


A reinforcement learning-based AI player for Blackjack, featuring an adaptive state transition model (STM) that learns and improves decision-making over time.

## 📋 Overview

This is a Java-based Blackjack game implementation with an integrated AI opponent that uses **state-transition reinforcement learning** to make intelligent Hit/Stand decisions. The AI learns from gameplay outcomes and adjusts its strategy probability values dynamically.

## 🎯 Key Features

- **Interactive GUI** - Built with Java Swing for user-friendly gameplay
- **AI Learning System** - Reinforcement learning algorithm that adapts to game states
- **State Machine** - State Transition Model (STM) tracking player sum vs. dealer's visible card
- **Adjustable Strategy** - Win/loss outcomes modify the AI's decision probabilities
- **Persistent Training** - Save and load learned models to/from disk
- **Batch AI Training** - Run 100 games automatically to rapidly train the model

## 📁 Project Structure

```
CLS_BlackJackAI/
├── Card.java          # Card representation (name, value)
├── Deck.java          # Standard 52-card deck management
├── Controller.java    # Main application controller (event handling)
├── Model.java         # Game logic & AI learning engine
├── View.java          # GUI components and layout
├── Blackjack.pptx     # Presentation/documentation
└── README.md          # This file
```

## 🏗️ Architecture

### **MVC Pattern**
The project follows Model-View-Controller architecture:

- **Model.java** - Core game logic, AI decision engine, STM management
- **View.java** - Swing GUI with buttons, panels, and card displays
- **Controller.java** - Bridges user input to model actions

### **Core Classes**

#### `Card.java`
Represents a single playing card with a name (e.g., "K", "5") and numeric value.

```java
Card card = new Card("K", 10);
int value = card.getValue();  // Returns 10
```

#### `Deck.java`
Manages a standard 52-card deck with draw functionality.

#### `Model.java` (Brain of the AI)
**Key Responsibilities:**
- Game initialization and state tracking
- Hit/Stand decision logic
- AI learning via State Transition Model
- Win/loss outcome processing
- File I/O for saving/loading models

**AI Strategy:**
- States are represented as: `playerSum/dealerCard/%probability`
  - Example: `15/7/%65` means "when player has 15 and dealer shows 7, hit 65% of the time"
- After each game, probabilities are updated:
  - Win: `probability *= 1.07` (reward)
  - Loss from hit: `probability *= 0.95` (punishment)
  - Loss from stand: `probability *= 1.05` (penalty)

#### `View.java`
GUI components including:
- Player and dealer card display areas
- Game action buttons (Play, Hit, Stand)
- AI control buttons (AI Play, AI Play 100)
- State management display

#### `Controller.java`
Connects user interactions to game logic.

## 🎮 How to Play

### Manual Gameplay
1. Click **Play** to start a new hand
2. Click **Hit** to draw another card
3. Click **Stand** to hold your sum and reveal dealer's cards
4. Goal: Beat the dealer without going over 21

### AI Training
- **AI Play** - Single AI game with learning
- **AI Play 100** - Run 100 consecutive games (rapid training)
- **Make STM** - Initialize a fresh State Transition Model
- **Save STM** - Export learned model to a text file
- **Open STM** - Load a previously trained model

## 📊 State Transition Model (STM)

The STM tracks all possible game states and their learned probabilities:

```
States range from 4/2 to 22/11:
- Player sum: 4-22
- Dealer's visible card: 2-11 (Ace=11)
- Hit probability: starts at 50% for unknown states

Format: playerSum/dealerCard/%hitProbability
Example: 16/7/%75 (when you have 16 and dealer shows 7, hit 75% of the time)
```

## 🤖 Learning Algorithm

### State Representation
Each game state is: `[Player Sum] / [Dealer Card] / [Hit Probability]`

### Reward/Punishment System
After each game outcome:
- **Hit and Won**: Hit% *= 1.07 (7% reward)
- **Hit and Lost**: Hit% *= 0.95 (5% punishment)
- **Stand and Won**: Hit% *= 0.93 (7% penalty for hitting - discourage future hits)
- **Stand and Lost**: Hit% *= 1.05 (5% reward for hitting - encourage future hits)

This creates a self-correcting strategy that learns optimal play over time.

## 🚀 Getting Started

### Prerequisites
- Java 8 or higher
- Swing library (included in JDK)

### Compilation
```bash
javac *.java
```

### Running the Application
```bash
java Controller
```

Or if no Controller main method exists, run:
```bash
java View
```

## 📝 Usage Examples

### Load and Train on an Existing Model
```
1. Open STM (load previously trained model)
2. Click "AI Play 100" multiple times to continue training
3. Click "Save STM" to persist improvements
```

### Train from Scratch
```
1. Click "Make STM" to initialize fresh model
2. Click "AI Play 100" (or multiple times)
3. Observe probability changes in console output
4. Save with "Save STM"
```

### Manual vs AI Comparison
```
- Play manual games to develop intuition
- Run AI games to see optimal strategy
- Compare win rates
```

## 📈 Sample Output

When running AI games, console shows:
```
15/10/%50
HIT P = 0.5
16/10/%47
15/10/%50: WON!
Setting 15/10/%50 to: 15/10/%54 (updated probability)
```

## 🔧 Configuration

Adjust AI learning rates by modifying these constants in `Model.java`:

```java
double punishment = 0.95;      // Loss penalty multiplier
double reward = 1.07;          // Win reward multiplier
double standReward = 0.93;     // Stand win reward
double standPunishment = 1.05; // Stand loss penalty
```

## 📚 Game Rules

- Blackjack (21 on first two cards) wins immediately
- Player can draw up to 5 cards maximum
- Dealer must hit on <17, stand on ≥17
- Highest hand ≤21 wins
- Going over 21 loses instantly

## 🐛 Known Limitations

- AI can draw maximum 5 cards (hard-coded switch statement)
- No Ace flexibility (all Aces treated as 11)
- Single-deck game (no shoe mechanics)
- GUI rendering is basic (no modern styling)

## 🔮 Future Enhancements

- [ ] Neural network AI instead of table-based STM
- [ ] Doubling down and splitting
- [ ] Soft hand handling (Ace as 1 or 11)
- [ ] Multi-deck shoe simulation
- [ ] Statistical analysis and win-rate tracking
- [ ] Modern JavaFX GUI
- [ ] Monte Carlo tree search decision-making

## 👨‍💻 Contributing

Feel free to fork and submit pull requests for:
- Bug fixes
- Performance optimizations
- Enhanced AI algorithms
- UI improvements

## 📄 License

This project is provided as-is for educational purposes.

## ✨ Acknowledgments

Developed as part of the Computer Language Systems (CLS) course project.

---

**Happy learning! May the odds be ever in your AI's favor.** 🎲

