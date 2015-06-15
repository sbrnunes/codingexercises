package other;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class CombinationOfCards
{
    public static void main(String[] args) {

        Stack<Card> cards = new Stack<>();

        for(int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 10; j++) {
                cards.push(new Card(j));
            }

            for (int j = 1; j <= 3; j++ ) {
                cards.push(new Card(10));
            }
        }


        Set<Card> combination = getCombinationOfCards(cards, 24, 0, new HashSet<>());

        combination.forEach(card -> System.out.print(card.value + " "));

    }

    private static Set<Card> getCombinationOfCards(Stack<Card> cards, int target, int current, Set<Card> currentCombination)
    {
        if(current == target) {
            return currentCombination;
        }

        Card card = cards.pop();

        if(current + card.value < target) {
            currentCombination.add(card);

            return getCombinationOfCards(cards, target, current + card.value, currentCombination);
        }
        else if(current + card.value == target && currentCombination.size() + 1 == 4) {
            currentCombination.add(card);
            return getCombinationOfCards(cards, target, current + card.value, currentCombination);
        }
        else {
            return getCombinationOfCards(cards, target, current, currentCombination);
        }
    }

    private static class Card {
        private int value;

        public Card(int value) {
            this.value = value;
        }
    }
}
