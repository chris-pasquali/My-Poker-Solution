package myPokerSolution;

public class PlayerHand {
	public int[] suits, cardValues;
    public HAND handType;

    public int[] sortedCards;

    public PlayerHand (String hand) {
        suits = new int[4];
        cardValues = new int[13];
        sortedCards = new int[5];
        String[] cards = hand.split(" ");

        loadCards(cards);
        evaluateHand();
    }

    public void loadCards(String[] cards) {
        char tempSuit, tempCardValue;
        for (String card : cards) {

            tempCardValue = card.charAt(0);
            switch (tempCardValue) {
                case '2':
                    ++cardValues[CARDVALUE.TWO.ordinal()];
                    break;
                case '3':
                    ++cardValues[CARDVALUE.THREE.ordinal()];
                    break;
                case '4':
                    ++cardValues[CARDVALUE.FOUR.ordinal()];
                    break;
                case '5':
                    ++cardValues[CARDVALUE.FIVE.ordinal()];
                    break;
                case '6':
                    ++cardValues[CARDVALUE.SIX.ordinal()];
                    break;
                case '7':
                    ++cardValues[CARDVALUE.SEVEN.ordinal()];
                    break;
                case '8':
                    ++cardValues[CARDVALUE.EIGHT.ordinal()];
                    break;
                case '9':
                    ++cardValues[CARDVALUE.NINE.ordinal()];
                    break;
                case 'T':
                    ++cardValues[CARDVALUE.TEN.ordinal()];
                    break;
                case 'J':
                    ++cardValues[CARDVALUE.JACK.ordinal()];
                    break;
                case 'Q':
                    ++cardValues[CARDVALUE.QUEEN.ordinal()];
                    break;
                case 'K':
                    ++cardValues[CARDVALUE.KING.ordinal()];
                    break;
                case 'A':
                    ++cardValues[CARDVALUE.ACE.ordinal()];
                    break;
            }

            tempSuit = card.charAt(1);
            switch (tempSuit) {
                case 'D':
                    ++suits[SUIT.DIAMONDS.ordinal()];
                    break;
                case 'H':
                    ++suits[SUIT.HEARTS.ordinal()];
                    break;
                case 'S':
                    ++suits[SUIT.SPADES.ordinal()];
                    break;
                case 'C':
                    ++suits[SUIT.CLUBS.ordinal()];
                    break;
            }

        }
        
    }

    public void evaluateHand() {
        boolean isFlush = false;
        boolean isStraight = false;

        for (int suit : suits) {
            if (suit == 5) {
                isFlush = true;
            }
        }

        int straightHand = 0;
        for (int value : cardValues) {
            if (value == 1) {
                ++straightHand;
                if (straightHand == 5) {
                    isStraight = true;
                    break;
                }
            } else {
                straightHand = 0;
            }
        }
        if (isFlush) {
            if (isStraight) {
                handType = HAND.STRAIGHTFLUSH;
            } else {
                handType = HAND.FLUSH;
            }
        } else if (isStraight) {
            handType = HAND.STRAIGHT;
        } else {
            int[] repeatedCards =new int[3];
            for (int value : cardValues) {
                switch (value) {
                    case 4:
                        ++repeatedCards[2];
                        break;
                    case 3:
                        ++repeatedCards[1];
                        break;
                    case 2:
                        ++repeatedCards[0];
                        break;
                }
            }

            if (repeatedCards[2] == 1) {
                handType = HAND.FOUROFAKIND;
            } else if (repeatedCards[1] == 1) {
                if (repeatedCards[0] == 1) {
                    handType = HAND.FULLHOUSE;
                } else {
                    handType = HAND.THREEOFAKIND;
                }
            } else if (repeatedCards[0] == 2) {
                handType = HAND.TWOPAIRS;
            } else if (repeatedCards[0] == 1) {
                handType = HAND.ONEPAIR;
            } else {
                handType = HAND.HIGHCARD;
            }
        }
        cardFrequency();
    }

    public void cardFrequency() {
        int counter = -1;
        for (int i = 12; i >= 0; --i) {
            if (cardValues[i] == 4) {
                sortedCards[++counter] = i;
            }
        }

        for (int i = 12; i >= 0; --i) {
            if (cardValues[i] == 3) {
                sortedCards[++counter] = i;
            }
        }

        for (int i = 12; i >= 0; --i) {
            if (cardValues[i] == 2) {
                sortedCards[++counter] = i;
            }
        }

        for (int i = 0; i >= 0; --i) {
            if (cardValues[i] == 1) {
                sortedCards[++counter] = i;
            }
        }
    }

    public enum HAND {
        HIGHCARD, ONEPAIR, TWOPAIRS, THREEOFAKIND, STRAIGHT, FLUSH, FULLHOUSE,
        FOUROFAKIND, STRAIGHTFLUSH
    }

    public enum CARDVALUE {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }

    public enum SUIT {
        SPADES, HEARTS, CLUBS, DIAMONDS
    }

}
