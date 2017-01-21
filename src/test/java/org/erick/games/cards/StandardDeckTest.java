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

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;

/**
 * {@link StandardDeck} test
 */
public class StandardDeckTest {

  /**
   * Test of create method, of class StandardDeck.
   */
  @Test
  public void testCreate() {
    StandardDeck deck = new StandardDeck();
    deck.create(4, 13);

    assertEquals(deck.size(), 52);

    deck.create(2, 2);
    assertEquals(deck.size(), 4);
  }

  /**
   *
   */
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testCreateWithBadArgs() {
    StandardDeck deck = new StandardDeck();
    deck.create(-1, -1);
  }

  /**
   * Test of shuffle method, of class StandardDeck.
   */
  @Test
  public void testShuffle() {
    StandardDeck deck1 = new StandardDeck();
    StandardDeck deck2 = new StandardDeck();

    deck1.create(4, 13);
    deck2.create(4, 13);

    assertEquals(deck1, deck2);

    deck2.shuffle();
    assertEquals(deck2.size(), 52);
    assertNotEquals(deck1, deck2);
  }

  /**
   * Test of deal method, of class StandardDeck.
   */
  @Test
  public void testDeal() {
    StandardDeck deck = new StandardDeck();
    deck.create(4, 13);

    StandardCard card = (StandardCard) deck.deal();
    assertFalse(card.isFaceUp());

    assertEquals(deck.size(), 51);
  }
}
