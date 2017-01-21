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

/**
 * {@link War} test
 */
public class WarTest extends War {
  /**
   * Test of play method, of class War.
   */
  @Test
  public void testPlay() {
    War war = new War();
    war.play(1, 2, 2);
    assertEquals(war.getPlayers().size(), 1);
  }

  /**
   * Test of makePlayers method, of class War.
   */
  @Test
  public void testMakePlayers() {
    War war = new War();
    war.makePlayers(2);
    assertEquals(war.getPlayers().size(), 2);
  }

  /**
   * Test of deal method, of class War.
   */
  @Test
  public void testDeal() {
    War war = new War();
    war.makePlayers(2);
    war.deal(4, 13);
    assertEquals(war.getPlayers().get(0).getHand().size(), 26);
  }

  /**
   * Test of display method, of class War.
   */
  @Test
  public void testDisplay() {
    War war = new War();
    war.display("testing display");
  }

  /**
   * Test of excludeLosingPlayers method, of class War.
   */
  @Test
  public void testExcludeLosingPlayers() {
    Card card = StandardCard.builder().rank(RankEnum.JACK).suit(SuitEnum.CLUBS).build();
    Player player1 = new Player();
    Player player2 = new Player();
    player1.takesCard(card);

    List<Player> players = new ArrayList<>();
    players.add(player1);
    players.add(player2);

    War war = new War();
    war.setPlayers(players);
    war.excludeLosingPlayers();
    assertEquals(war.getPlayers().size(), 1);
  }

  /**
   * Test of playRound method, of class War.
   */
  @Test
  public void testPlayRound() {
    Card eight = StandardCard.builder().rank(RankEnum.EIGHT).suit(SuitEnum.CLUBS).build();
    Card ace = StandardCard.builder().rank(RankEnum.ACE).suit(SuitEnum.CLUBS).build();
    Player player1 = Player.builder().name("player1").hand(new ArrayList<>()).table(
        new ArrayList<>()).build();
    Player player2 = Player.builder().name("player2").hand(new ArrayList<>()).table(
        new ArrayList<>()).build();
    player1.takesCard(ace);
    player2.takesCard(eight);
    List<Player> players = new ArrayList<>();
    players.add(player1);
    players.add(player2);

    War war = new War();
    war.setPlayers(players);
    war.playRound();
    war.excludeLosingPlayers();
    assertEquals(war.getPlayers().size(), 1);
  }

  /**
   * Test of findHighRank method, of class War.
   */
  @Test
  public void testFindHighRank() {
    War war = new War();
    war.makePlayers(2);
    war.getPlayers().get(0).takesCard(StandardCard.builder().suit(SuitEnum.CLUBS)
        .rank(RankEnum.JACK).build());
    war.getPlayers().get(0).playCardFaceUp();
    war.getPlayers().get(1).takesCard(StandardCard.builder().suit(SuitEnum.CLUBS).rank(RankEnum.ACE)
        .build());
    war.getPlayers().get(1).playCardFaceUp();
    assertEquals(war.findHighRank(war.getPlayers()), RankEnum.ACE);
  }
}
