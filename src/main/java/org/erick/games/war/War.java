// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//  http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
package org.erick.games.war;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.erick.games.cards.RankEnum;
import org.erick.games.cards.StandardCard;
import org.erick.games.cards.StandardDeck;

/**
 * Represents the card game War.
 */
@NoArgsConstructor @Data
public class War {
  private List<Player> players = new ArrayList<>();

  /**
   * Plays War.
   *
   * @param args Ignored command line arguments.
   */
  public static void main(String[] args) {
    int ranks = 4;
    int suits = 13;
    int players = 2;

    if (args.length == 3) {
      ranks = Integer.parseInt(args[0]);
      suits = Integer.parseInt(args[1]);
      players = Integer.parseInt(args[2]);
    }

    if (args.length != 0 && args.length != 3) {
      display("Invalid command-line arguments.\n"
          + "Format:\n"
          + "    java -jar war*.jar <number of ranks> <number of suits> <number of players>\nor\n"
          + "    java -jar war*.jar \nfor a standard deck with 2 players.");
    } else {
      War war = new War();
      war.play(ranks, suits, players);
    }
  }

  /**
   * Represents the logic of the game of War.
   *
   * @param numberOfSuits Number of suits to use.
   * @param numberOfRanks Number of ranks to use.
   * @param numberOfPlayers Number of players to use.
   */
  public void play(int numberOfSuits, int numberOfRanks, int numberOfPlayers) {
    int round = 1;
    makePlayers(numberOfPlayers);
    deal(numberOfSuits, numberOfRanks);

    while (players.size() > 1) {
      display("Round " + round);

      playRound();
      for (Player player : players) {
        display(player + ": hand: " + player.getHand().size());
      }
      excludeLosingPlayers();
      round++;

      if (round > 2500) {
        display("Stalemate? No victory after 2500 rounds.");
        break;
      }
    }

    if (players.size() == 1) {
      display(players.get(0) + " wins the game!");
    }
  }

  /**
   * Populates the players in this game.
   *
   * @param numberOfPlayers Number of players to make.
   */
  protected void makePlayers(int numberOfPlayers) {
    for (int i = 1; i <= numberOfPlayers; i++) {
      players.add(Player.builder()
          .name("Player " + Integer.toString(i))
          .hand(new ArrayList<>())
          .table(new ArrayList<>())
          .build());
    }
  }

  /**
   * Creates and shuffles a standard deck and distributes all cards to the players.
   *
   * @param numberOfSuits Number of suits to use.
   * @param numberOfRanks Number of ranks to use.
   */
  protected void deal(int numberOfSuits, int numberOfRanks) {
    StandardDeck deck = new StandardDeck();
    deck.create(numberOfSuits, numberOfRanks);
    deck.shuffle();

    while (deck.size() > 0) {
      for (Player player : players) {
        if (deck.size() > 0) {
          player.takesCard(deck.deal());
        }
      }
    }
  }

  /**
   * Presents text to the user.
   *
   * @param text The text to present to the user.
   */
  protected static void display(String text) {
    System.out.println(text);
  }

  /**
   * Of the current players, removes players from the game who have no cards in their hand.
   */
  protected void excludeLosingPlayers() {
    players = players.stream()
        .filter(player -> player.getHand().size() > 0)
        .collect(Collectors.toList());
  }

  /**
   * Runs one round of War.
   */
  protected void playRound() {
    List<Player> winningPlayers = new ArrayList<>();

    while (winningPlayers.size() != 1) {
      if (winningPlayers.size() > 1) {
        display(winningPlayers.size() + " players are tied. WAR!");
      }

      for (Player player : players) {
        if (winningPlayers.size() > 1 && player.getHand().size() > 2) {
          player.playCardFaceDown();
          display(player + " plays a card face-down.");
          player.playCardFaceDown();
          display(player + " plays a card face-down.");
        }
        if (player.getHand().size() > 0) {
          player.playCardFaceUp();

          display(player + " plays " + player.showTopCardOnTable());
        }
      }

      RankEnum topRank = findHighRank(players);

      winningPlayers = players.stream()
          .filter(player -> ((StandardCard) player.showTopCardOnTable()).getRank()
              .equals(topRank)).collect(Collectors.toList());
    }

    display(winningPlayers.get(0) + " wins the round!");
    try {
      Thread.sleep(10);
    } catch (InterruptedException ex) {
      Logger.getLogger(War.class.getName()).log(Level.SEVERE, null, ex);
    }

    for (Player player : players) {
      winningPlayers.get(0).winsCards(player.losesCardsOnTable());
    }
  }

  /**
   * Of all the cards players have put on the table, find the highest rank.
   *
   * @param players The list of current players.
   * @return The highest rank the players have placed on the table.
   */
  protected RankEnum findHighRank(List<Player> players) {
    List<RankEnum> ranksOnTable = new ArrayList<>();

    players.stream().forEach(player -> {
      ranksOnTable.add(((StandardCard) player.showTopCardOnTable()).getRank());
    });

    return ranksOnTable.stream().max(RankEnum::compareTo).get();
  }
}
