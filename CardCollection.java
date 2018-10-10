import java.awt.*;
import objectdraw.*;
/**
 * This class manages the entire collection of cards.
 * 
 * @author Nikki Wei
 * @version 4/10/2017
 */
public class CardCollection
{
    // Maximum number of rows and columns
    private static final int MAX_X = 6;
    private static final int MAX_Y = 6;
    
    // The margin between two cards and the size of a card
    private static final int MARGIN = 10;
    private static final int CARD_SIZE = 50;
    
    // Array to hold all the cards
    private Card[] cards;
    
    // Array to hold all the characters on cards
    private char[] symbol;
    
    // Number of cards currently in the array
    private int numCards = 0;

    
    /**
     * Construct an empty array to hold all the cards and an empty array to hold all the symbols.
     */
    public CardCollection () {
        // Assign an index to the array
        cards = new Card[MAX_X*MAX_Y];
        symbol = new char[MAX_X*MAX_Y];
    }
    
    /**
     * Add all the cards and all the symbols to the collection and randomly place the symbols.
     * @param cardLeft left of the card
     * @param cardTop top of the card
     * @param character symbol on the card
     * @param canvas the portion of the screen to draw on
     */
    public void addCards (int cardLeft, int cardTop, char character, DrawingCanvas canvas) {
        // Add all the symbols to the array
        for (numCards = 0; numCards < symbol.length; numCards++) {
            symbol[numCards] = character;
            // if the same symbol has already been assigned twice, use the next symbol.
            if (numCards%2 == 1) {
                character ++;
            }
        }
        
        // Randomly swap the symbo
        RandomIntGenerator swap = new RandomIntGenerator (0, 35);
        for (int i = 0; i < 100; i++) {
            int a = swap.nextValue ();
            int b = swap.nextValue ();
            char storage = symbol[a];
            symbol[a] = symbol[b];
            symbol[b] = storage;
        }
        
        // Create a card and put it into the next slot of the array
        numCards = 0;
        for (int row = 0; row < MAX_Y; row++) {
            for (int col = 0; col < MAX_X; col++) {
                cards[numCards] = new Card (cardLeft + col*(MARGIN + CARD_SIZE),
                cardTop + row*(MARGIN + CARD_SIZE), symbol[numCards], canvas);
                numCards++;
            }
        }
    }
    
    /**
     * Find the card that has been selected by the user
     */
    public Card getSelectedCard (Location point) {
        for (int numCards = 0; numCards < cards.length; numCards++) {
            if (cards[numCards] != null) {
                if (cards[numCards].contains (point)) {
                    return cards[numCards];
                }
            }
        }
        return null;
    }
    
    /**
     * Remove the card from the collection that has been removed from canvas.
     */
    public void remove (Card removedCard) {
        for (int numCards = 0; numCards < cards.length; numCards++) {
            // if the card has been removed from canvas, remove it from the collection
            if (cards[numCards] == removedCard) {
                cards[numCards] = null;
            }
        }
    }
    
    /**
     * Reveal the symbol of every card that is on canvas.
     */
    public void cheat () {
        for (numCards = 0; numCards < cards.length; numCards++) {
            // if the card has not been removed, show its symbol.
            if (cards[numCards] != null) {
                cards[numCards].showSymbol ();
            }
        }
    }
}
