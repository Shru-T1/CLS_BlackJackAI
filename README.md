# CLS_BlackJackAI
AIBlackJack
This game is made by Shruti Sharma and Ben Ruelas. AIBlackJack is a application model for the well-known game of BlackJack made using Aritifcial Intelligence. The Model follows the basic rules:

The goal of blackjack is to beat the dealer’s hand without going over 21. Face cards are worth 10. Aces are worth 11. Player starts with two cards, one of the dealer's cards is hidden until the end. To 'Hit' is to ask for another card. To 'Stand' is to hold your total and end your turn. If you go over 21 you bust, and the system wins regardless of the dealer's hand. If you are dealt 21 from the start (Ace & 10), you got a blackjack. Dealer will hit until his/her cards total 17 or higher.

In the above project, we have followed the CLS or Collective Learning Systems Model to train our system. Initially all the game have equal probabilities which shows an equal chance of hit or stand irrespective of the dealer and player sum and its deviation from the number 21. After 5000 plays, we can see that the game takes on a progressive shape giving us an almost winning desirable output.

Our Observations: First Card: Having a small high-number, i.e. from 8-11 calls for a hit irrespective of the opponent’s card. However, a big high-number from 12-16 calls for a hit only when the dealer has his show card lower than or equal to 7.

Second Card: Having a numbers smaller than 7 with an ace usually call for a hit, depending on the card shown by the dealer. If the dealer has a high card, the probabilities of calling a hit increase.

There are rare and special cases, even within the observed matrix, and so are some deviations.
