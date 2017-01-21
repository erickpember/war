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
package org.erick.games.cards;

import java.util.ArrayList;
import java.util.Collections;
import lombok.EqualsAndHashCode;

/**
 * Represents an behavior for a standard 52-card deck.
 */
@EqualsAndHashCode
public class StandardDeck implements Deck {
  private final ArrayList<StandardCard> cards = new ArrayList<>();

  @Override
  public void create(int numberOfSuits, int numberOfRanks) {
    if (numberOfSuits > SuitEnum.values().length || numberOfRanks > RankEnum.values().length
        || numberOfSuits < 1 || numberOfRanks < 1) {
      throw new IllegalArgumentException("An impossible amount was used for either the number of "
          + "ranks or suits.");
    }

    cards.clear();

    ArrayList<SuitEnum> suits = new ArrayList<>();
    ArrayList<RankEnum> ranks = new ArrayList<>();

    for (SuitEnum suit : SuitEnum.values()) {
      if (numberOfSuits > 0) {
        suits.add(suit);
        numberOfSuits--;
      }
    }

    for (RankEnum rank : RankEnum.values()) {
      if (numberOfRanks > 0) {
        ranks.add(rank);
        numberOfRanks--;
      }
    }

    for (RankEnum rank : ranks) {
      for (SuitEnum suit : suits) {
        cards.add(StandardCard.builder().rank(rank).suit(suit).build());
      }
    }
  }

  @Override
  public void shuffle() {
    Collections.shuffle(cards);
  }

  @Override
  public Card deal() {
    return cards.remove(0);
  }

  @Override
  public int size() {
    return cards.size();
  }
}
