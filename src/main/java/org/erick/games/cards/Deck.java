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

/**
 * Represents an behavior for a generic set of cards.
 */
public interface Deck {
  /**
   * Create the deck of cards
   *
   * @param numberOfSuits The number of suits to be created in the deck.
   * @param numberOfRanks The number of ranks to be created in the deck.
   */
  public void create(int numberOfSuits, int numberOfRanks);

  /**
   * Shuffles the deck.
   */
  public void shuffle();

  /**
   * Deals one card from the deck.
   *
   * @return The 0th card of the deck.
   */
  public Card deal();

  /**
   * Returns the size of the deck.
   *
   * @return The size of the deck.
   */
  public int size();
}
