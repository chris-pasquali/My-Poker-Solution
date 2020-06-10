package myPokerSolution;

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
        int player1Won = 0;
        int player2Won = 0;

        Scanner myScanner = new Scanner(System.in);
        System.out.println("Please enter hands and press double tap enter once entered");

        String cards;

        while (!(cards = myScanner.nextLine()).equals("")) {
            String player1Cards = cards.substring(0,14);
            String player2Cards = cards.substring(15);

            PlayerHand player1 = new PlayerHand(player1Cards);
            PlayerHand player2 = new PlayerHand(player2Cards);

            if (player1.handType.ordinal() == player2.handType.ordinal()) {
                for (int i = 0; i < 5; ++i) {
                    if (player1.sortedCards[i] > player2.sortedCards[i]) {
                        player1Won++;
                    } else if (player2.sortedCards[i] > player1.sortedCards[i]) {
                        player2Won++;
                    }
                }
            } 
            else if (player1.handType.ordinal() > player2.handType.ordinal()) {
            	player1Won++;
            } else if (player2.handType.ordinal() > player1.handType.ordinal()) {
            	player2Won++;
            }
        }


        System.out.println("Player 1: " + player1Won);
        System.out.println("Player 2: " + player2Won);
        myScanner.close();
        
	}

}
