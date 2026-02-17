# BUCKSHOT ROULETTE SIMULATOR
This program contains three Java classes that compose a simulator for different Buckshot Roulette strategy pairings. 

# BUCKSHOT ROULETTE
**Buckshot Roulette** is a 2023 turn-based indie horror strategy game developed by Mike Klubnika. In the game, a shotgun is loaded with a certain number of live shells and blank shells in a randomized order. 
The player and the dealer then take turns with the shotgun (the player begins). On each turn, the current player can choose to either shoot themselves or shoot their opponent. If the current player shoots themselves and the round is live, they lose 1 health 
and the opposing player's turn begins. If the current player shoots themselves and the round is blank, the player takes no damage and gets an extra turn. If the current player shoots the opposing player, the opposing player either takes damage or not (depending on if the
round was live or blank), but regardless the current player's turn ends.

This creates an interesting strategic dynamic, and raises the question of what the optimal strategy is (which is the purpose of this simulator). Of course, in the inital game there are varying numbers of live/blank rounds, and there are also items, 
but for simplicity this simulator is based on the final round with 4 live rounds and 4 blank rounds, and disregards items (again for simplicity).

The game is available for purchase on itch.io and Steam: https://mikeklubnika.itch.io/buckshot-roulette

# THE SIMULATOR
This simulator contains three java files for simulating strategy matchups. 

[Strategies.java](src/com/company/Strategies.java) contains algorithms for the following decision-making algorithms: default dealer AI, 
always shooting the opponent, probabilistic, inverse probabilistic,
aggressive, conservative, alternating, and cheater (perfect knowledge). These all implement the Strategy interface so they can be passed as parameters into the simulate function in [Simulation.java](src/com/company/Simulation.java)

[Simulation.java](src/com/company/Simulation.java) contains two methods: one for shuffling and resetting an ArrayList containing the shells, and one for simulating a matchup. The simulate() method takes two implementations of Strategy from 
[Strategies.java](src/com/company/Strategies.java) and plays through rounds according to those two strategies until a player wins (reaches 0 health from the starting 4). The method returns an array containing the winner (player 0 or 1), the amount of damage dealt
by each player, and the amount of rounds it took (1-2). 

[Main.java](src/com/company/Main.java) contains two methods as well: the run() helper method which simulates a large number of games and congregates + prints the data, and main() which currently has a few example matchups. The run() method takes a name for printing,
two implementations of Strategy, and a number of trials to repeat, and then prints averages from those trials (average number of loops required, average winrate, average damaqge per round)

**How to run:** By calling run() and passing in a name, a player strategy, a dealer strategy, and a number of trials, the simulator will play out games with that strategy matchup for as many trials as told, and print the results.
For a list of strategies, see [Strategies.java](src/com/company/Strategies.java). For an example, see the main() method in [Main.java](src/com/company/Main.java)
