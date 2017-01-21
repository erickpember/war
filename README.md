# war
The game of war, pre-employment exercise.

This game runs on its own and be customized with different
amounts of ranks, suits and players. 

A game with a 52 card deck usually concludes around 1000 turns
but occasionally will run long. Stalemate will be declared at
turn 2500.

Usage:
mvn clean install

Format:
    java -jar target/war*.jar <number of ranks> <number of suits> <number of players>
or
    java -jar target/war*.jar 
for a standard deck with 2 players.

