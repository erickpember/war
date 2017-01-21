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
import static org.testng.Assert.assertNotEquals;

/**
 * {@link StandardCard} test
 */
public class StandardCardTest {
  /**
   * Test of getRank method, of class StandardCard.
   */
  @Test
  public void testGetRank() {
    StandardCard card = new StandardCard();
    card.setRank(RankEnum.ACE);
    assertEquals(card.getRank(), RankEnum.ACE);
  }

  /**
   * Test of getSuit method, of class StandardCard.
   */
  @Test
  public void testGetSuit() {
    StandardCard card = new StandardCard();
    card.setSuit(SuitEnum.CLUBS);
    assertEquals(card.getSuit(), SuitEnum.CLUBS);
  }

  /**
   * Test of equals method, of class StandardCard.
   */
  @Test
  public void testEquals() {
    StandardCard card1 = StandardCard.builder().rank(RankEnum.JACK).suit(SuitEnum.CLUBS).build();
    StandardCard card2 = StandardCard.builder().rank(RankEnum.JACK).suit(SuitEnum.CLUBS).build();
    StandardCard card3 = StandardCard.builder().rank(RankEnum.QUEEN).suit(SuitEnum.HEARTS).build();
    assertEquals(card1, card2);
    assertNotEquals(card1, card3);

    // We are not using face-up status in equals method.
    card2.flip();
    assertEquals(card1, card2);
  }

  /**
   * Test of hashCode method, of class StandardCard.
   */
  @Test
  public void testHashCode() {
    StandardCard card1 = StandardCard.builder().rank(RankEnum.JACK).suit(SuitEnum.CLUBS).build();
    StandardCard card2 = StandardCard.builder().rank(RankEnum.JACK).suit(SuitEnum.CLUBS).build();
    StandardCard card3 = StandardCard.builder().rank(RankEnum.QUEEN).suit(SuitEnum.HEARTS).build();
    assertEquals(card1.hashCode(), card2.hashCode());
    assertNotEquals(card1.hashCode(), card3.hashCode());
  }
}
