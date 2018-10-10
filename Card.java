import java.awt.*;
import objectdraw.*;
/**
 * Define an individual card, consist of a rectangle and a symbol.
 * 
 * @author Nikki Wei
 * @version 4/10/2017
 */
public class Card
{
    // This determines the size of each individual card and the size of the text.
    private static final int CARD_SIZE = 50;
    private static final int FONT_SIZE = 30;
    
    // This is each individual card.
    private FilledRect card;
    
    // This is the symbol on each card.
    private Text text;
    
    // Use to store the parametor passed from controller to the constructor
    private char character;
    
    /**
     * Construct an individual card and the symbol on it
     * @param left where the card left is
     * @param top where the card top is
     * @param symbol what symbol the card has
     * @param canvas portion of the screen to draw on
     */
    public Card (int left, int top, char symbol, DrawingCanvas canvas) {
        // Create an individual card and set its color.
        card = new FilledRect (left, top, CARD_SIZE, CARD_SIZE, canvas);
        card.setColor (new Color (145, 245, 245));
        
        // Set the location of the symbol.
        text = new Text (symbol, left, top, canvas);
        text.setFontSize (FONT_SIZE);
        text.moveTo (left + CARD_SIZE/2 - text.getWidth()/2,
        top + CARD_SIZE/2 - text.getHeight()/2);
        text.hide ();
        
        // Remember for later use.
        character = symbol;
    }
    
    /**
     * Determine if the user has clicked on a card.
     * @param point the location where the user clikcs.
     */
    public boolean contains (Location point) {
        // If the user clicks on the card, return true.
        if (card.contains (point)) {
            return true;
        }
        return false;
    }
    
    /**
     * Return the symbol displayed on the card.
     */
    public char getSymbol () {
        return character;
    }
    
    /**
     * Display the symbol on the card.
     */
    public void showSymbol () {
        text.show ();
        text.sendToFront ();
    }
    
    /**
     * Hide the symbol on the card.
     */
    public void hideSymbol () {
        text.hide ();
    }
    
    /**
     * Remove the card and the symbol from the canvas.
     */
    public void removeFromCanvas () {
        card.removeFromCanvas ();
        text.removeFromCanvas ();
    }
}
