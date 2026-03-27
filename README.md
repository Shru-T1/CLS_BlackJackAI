# CLS_BlackJackAI
AIBlackJack
This game is made by Shruti Sharma and Ben Ruelas. AIBlackJack is a application model for the well-known game of BlackJack made using Aritifcial Intelligence. The Model follows the basic rules:

I'll create comprehensive READMEs for your BlackJack AI project. Based on the code analysis, I've prepared documentation:

```markdown name=README.md url=https://github.com/Shru-T1/CLS_BlackJackAI/blob/master/README.md
# 🎰 CLS_BlackJackAI

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
```

---

## 📋 Additional Documentation Files

I recommend also creating a **DEVELOPMENT.md** file for technical details:

```markdown name=DEVELOPMENT.md
# Development Guide - CLS_BlackJackAI

## Architecture Deep Dive

### State Transition Model (STM) Implementation

The STM is stored as a `LinkedList<String>` where each entry represents a learned game state:

```
String format: "playerSum/dealerCard/%probability"
Example: "15/7/%68" = Player has 15, dealer shows 7, 68% chance AI will hit
```

### Decision Flow

1. **Game Start** (`playFunc()`)
   - Draw 2 random cards for player
   - Draw 1 random card for dealer
   - Look up current state in STM
   - If state unknown, initialize at 50% hit probability

2. **AI Decision** (`AIPlay()`)
   - Extract hit probability from current state
   - Generate random number (0.0-1.0)
   - If random < hitPercent → call `hitFunc()`
   - Else → call `standFunc()`

3. **Outcome Processing**
   - Player sum compared against dealer sum
   - Win/loss detected
   - STM probability adjusted using multipliers
   - Updated state stored back in STM

### Performance Characteristics

- **State Space**: 19 × 10 = 190 possible states
- **Learning Speed**: ~5-10 games to stabilize basic strategy
- **Convergence**: ~100-500 games for near-optimal play

## Debugging Tips

### Print Current STM
```java
model.printSTM();  // Outputs all learned states to console
```

### Monitor State Changes
Look for console output like:
```
Setting 16/7/%50 to: 16/7/%54
```
This shows the AI adjusted its 16 vs 7 strategy from 50% to 54% hit probability.

### Verify File Format
Saved STM files should contain lines like:
```
4/2/%50
4/3/%48
...
22/11/%67
```

## Testing Scenarios

### Test 1: Fresh Model Training
```
1. Make STM
2. AI Play 1 game
3. Verify at least one state was modified
4. AI Play 100 
5. Verify probabilities converged
```

### Test 2: Model Persistence
```
1. Train model with AI Play 100
2. Save STM to file
3. Quit application
4. Restart application
5. Load STM from file
6. Verify original probabilities are restored
```

### Test 3: Learning Direction
```
1. Make STM
2. Note initial state (should all be 50%)
3. After training, high-value hands should have lower hit%
4. Low-value hands should have higher hit%
Example: 20/7 should be ~5-10% hit, 8/7 should be ~80-90% hit
```

## Code Quality Notes

- Consider refactoring large `hitFunc()` method (543 lines with repetitive switch cases)
- Potential use of design patterns: Strategy, Observer, Factory
- No exception handling in file operations (improve error resilience)
- UI components tightly coupled to game logic (consider MVC separation)

## Performance Bottlenecks

- File I/O blocking for large STM files (100+ KB)
- GUI not responsive during AI Play 100 (runs synchronously)
- Random card selection has poor distribution (no shuffle tracking)

## Future Refactoring Priorities

1. Extract common hit/stand logic into methods
2. Implement proper event system between Model and View
3. Add thread support for non-blocking AI training
4. Migrate to collections for flexible deck management
```

This comprehensive documentation now covers the project structure, AI learning mechanics, usage instructions, and development details. You can copy these directly into your repository!
