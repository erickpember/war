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
import org.erick.games.cards.Card;
import org.erick.games.cards.RankEnum;
import org.erick.games.cards.StandardCard;
import org.erick.games.cards.SuitEnum;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * {@link Player} test
 */
public class PlayerTest {

  /**
   * Test of playCardFaceUp method, of class Player.
   */
  @Test
  public void testPlayCardFaceUp() {
    Card card = StandardCard.builder().rank(RankEnum.JACK).suit(SuitEnum.CLUBS).build();
    List<Card> cards = new ArrayList<>();
    cards.add(card);
    Player player = new Player();
    player.setHand(cards);

    assertEquals(player.getHand().size(), 1);
    assertEquals(player.getTable().size(), 0);

    player.playCardFaceUp();

    assertEquals(player.getHand().size(), 0);
    assertEquals(player.getTable().size(), 1);

    assertEquals(player.showTopCardOnTable(), card);
    assertTrue(player.showTopCardOnTable().isFaceUp());

  }

  /**
   * Test of playCardFaceDown method, of class Player.
   */
  @Test
  public void testPlayCardFaceDown() {
    Card card = StandardCard.builder().rank(RankEnum.JACK).suit(SuitEnum.CLUBS).build();
    List<Card> cards = new ArrayList<>();
    cards.add(card);
    Player player = new Player();
    player.setHand(cards);

    assertEquals(player.getHand().size(), 1);
    assertEquals(player.getTable().size(), 0);

    player.playCardFaceDown();

    assertEquals(player.getHand().size(), 0);
    assertEquals(player.getTable().size(), 1);

    assertEquals(player.showTopCardOnTable(), card);
    assertFalse(player.showTopCardOnTable().isFaceUp());
  }

  /**
   * Test of showTopCardOnTable method, of class Player.
   */
  @Test
  public void testShowTopCardOnTable() {
    Card card = StandardCard.builder().rank(RankEnum.JACK).suit(SuitEnum.CLUBS).build();
    List<Card> cards = new ArrayList<>();
    cards.add(card);
    Player player = new Player();
    player.setHand(cards);

    assertEquals(player.getHand().size(), 1);
    assertEquals(player.getTable().size(), 0);

    player.playCardFaceUp();

    assertEquals(player.getHand().size(), 0);
    assertEquals(player.getTable().size(), 1);

    assertEquals(player.showTopCardOnTable(), card);
    assertTrue(player.showTopCardOnTable().isFaceUp());
  }

  /**
   * Test of losesCardsOnTable method, of class Player.
   */
  @Test
  public void testLosesCardsOnTable() {
    Card card = StandardCard.builder().rank(RankEnum.JACK).suit(SuitEnum.CLUBS).build();
    List<Card> cards = new ArrayList<>();
    cards.add(card);
    Player player = new Player();
    player.setHand(cards);

    assertEquals(player.getHand().size(), 1);
    assertEquals(player.getTable().size(), 0);

    player.playCardFaceUp();

    assertEquals(player.getTable().size(), 1);
    List<Card> lostCards = player.losesCardsOnTable();
    assertEquals(lostCards.size(), 1);

    assertEquals(player.getTable().size(), 0);
    assertEquals(player.getHand().size(), 0);
  }

  /**
   * Test of winsCards method, of class Player.
   */
  @Test
  public void testWinsCards() {
    Card card = StandardCard.builder().rank(RankEnum.JACK).suit(SuitEnum.CLUBS).build();
    List<Card> cards = new ArrayList<>();
    cards.add(card);
    Player player1 = new Player();

    player1.winsCards(cards);
    assertEquals(player1.getHand().size(), 1);
  }

  /**
   * Test of takesCard method, of class Player.
   */
  @Test
  public void testTakesCard() {
    Card card = StandardCard.builder().rank(RankEnum.JACK).suit(SuitEnum.CLUBS).build();
    Player player1 = new Player();

    assertEquals(player1.getHand().size(), 0);
    player1.takesCard(card);
    assertEquals(player1.getHand().size(), 1);
  }

  /**
   * Test of toString method, of class Player.
   */
  @Test
  public void testToString() {
    Player player = Player.builder().name("Erick").build();
    assertEquals(player.toString(), "Erick");
  }
}
