package org.erick.games.cards;

/**
 * Implements the suits in a deck of cards.
 */
public enum SuitEnum {
  CLUBS("Clubs"),
  DIAMONDS("Diamonds"),
  HEARTS("Hearts"),
  SPADES("Spades");

  private final String suit;

  private SuitEnum(final String suit) {
    this.suit = suit;
  }

  @Override
  public String toString() {
    return suit;
  }
}
