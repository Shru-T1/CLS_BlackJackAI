# BlackJack AI — Collective Learning Systems (CLS)

An AI agent that learns to play Blackjack using a Collective Learning Systems (CLS) model, built from scratch in Java.

## Overview

This project implements a Blackjack game where the AI player learns optimal strategy through simulated play. Rather than using a pre-trained model or hardcoded rules, the agent starts with equal probabilities for all actions and progressively refines its strategy over 5,000 simulated games.

Built by Shruti Sharma and Ben Ruelas.

## How It Works

The CLS model assigns equal hit/stand probabilities at the start. Over 5,000 plays, the system updates its probability matrix based on outcomes, converging toward an optimal strategy. The result is an agent that learns Blackjack strategy the same way a human would — through experience.

## Key Observations

After training, the agent converges on the following strategy:

- **Small high numbers (8–11):** Always hit, regardless of the dealer's card
- **Large high numbers (12–16):** Hit only when the dealer's shown card is 7 or lower
- **Ace + low card:** Hit in most cases, with the dealer's card as a deciding factor

## Tech Stack

- **Language:** Java
- **Architecture:** MVC (Model, View, Controller)
- **AI Model:** Collective Learning Systems (CLS)

## Project Structure

```
CLS_BlackJackAI/
├── Card.java          # Card representation
├── Deck.java          # Deck logic
├── Model.java         # CLS learning model
├── View.java          # Game display
├── Controller.java    # Game flow controller
└── Blackjack.pptx     # Project presentation
```

## Running the Project

1. Clone the repository
2. Compile all `.java` files
3. Run `Controller.java` to start the game

```bash
javac *.java
java Controller
```

## Context

This was one of two parallel implementations built to compare how different AI learning paradigms converge on optimal Blackjack strategy. See the companion repo [NN_BlackJackAI](https://github.com/Shru-T1/NN_BlackJackAI) for the Neural Network implementation.
