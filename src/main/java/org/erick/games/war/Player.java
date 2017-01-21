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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.erick.games.cards.Card;

/**
 * Represents a card player.
 */
@Data @AllArgsConstructor @Builder @NoArgsConstructor
public class Player {
  private List<Card> hand = new ArrayList<>();
  private List<Card> table = new ArrayList<>();
  private String name = new String();

  /**
   * Takes a card from the player and places it on the table face up.
   */
  public void playCardFaceUp() {
    Card card = hand.remove(0);
    card.setFaceUp(true);
    table.add(card);
  }

  /**
   * Takes a card from the player and places it on the table face down.
   */
  public void playCardFaceDown() {
    Card card = hand.remove(0);
    card.setFaceUp(false);
    table.add(card);
  }

  /**
   * Shows the last card played on the table.
   *
   * @return The last card played on the table.
   */
  public Card showTopCardOnTable() {
    return table.get(table.size() - 1);
  }

  /**
   * Takes a players cards off the table and returns them.
   *
   * @return Cards that were formally on the table.
   */
  public List<Card> losesCardsOnTable() {
    List<Card> lostCards = new ArrayList<>();
    while (!table.isEmpty()) {
      lostCards.add(table.remove(0));
    }

    return lostCards;
  }

  /**
   * Gives a player cards.
   *
   * @param winnings Cards going to the player's hand.
   */
  public void winsCards(List<Card> winnings) {
    hand.addAll(winnings);
  }

  /**
   * The player takes one card and adds it to their hand.
   *
   * @param card A card that is given to the player.
   */
  public void takesCard(Card card) {
    hand.add(card);
  }

  @Override
  public String toString() {
    return name;
  }
}
