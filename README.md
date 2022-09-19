# Poker Deck of Cards Game

REST API that represents a deck of poker-style playing cards for a game between multiple players holding cards. 

A deck is defined as follows: 52 playing cards in four suits: hearts, spades, clubs, and diamonds, with face values of Ace, 2-10, Jack, Queen, and King.

## Api endpoints

## GET

Create a new game : /game/new

Display game data : /game/{id}

Create a new deck : /game/deck/new

Get undealt cards per suit in a game deck   : /game/{id}/undealtcardpersuit

Get sorted remaining cards in a game deck   : /game/{id}/sortedleftcards

Deal Cards to a player                      : /game/{id}/player/{player_id}/deal?nbrCards=1

Get the list of cards of a player           : /game/{id}/player/{player_id}/cards

Get the list of players ordered by score    : /game/{id}/players


## POST

Add a deck to a game   : /game/{id}/deck/add/{deck_id}

Shuffle a game deck    : /game/{id}/shuffle

Add a player to a game : /game/{id}/player/add/{player_id}


## DELETE

Delete a game                 : /game/{id}

Remove a player from a game : /game/{id}/player/remove/{player_id}
