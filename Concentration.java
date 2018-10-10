import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import objectdraw.*;
import java.awt.*;
/**
 * This class displays the original screen and handles all the user interactions.
 * 
 * @author Nikki Wei
 * @version 4/10/2017
 */
public class Concentration extends WindowController implements ActionListener
{
    // The margin at the top, bottom, left, and right of the canvas
    private static final int MARGIN = 25;
    
    // Collection of cards
    private CardCollection cards = new CardCollection ();
    
    // The face-up cards
    private Card faceUpCard1 = null;
    private Card faceUpCard2 = null;
    
    // The two buttons
    private JButton newButton;
    private JButton cheatButton;
    
    // Determine is the cheat method has been called
    private boolean cheated = false;
    
    /**
     * Display the original screen, with two buttons and all the cards facing down.
     * Assign symbols randomly to cards.
     */
    public void begin () {
        // Create the two buttons
        newButton = new JButton ("New game");
        cheatButton = new JButton ("Cheat");
        
        // Put the two buttons above the canvas
        JPanel northPanel = new JPanel ();
        northPanel.add (newButton);
        northPanel.add (cheatButton);
        add (northPanel, BorderLayout.NORTH);
        
        // Tell the two buttons that this class is providing the event handlers for them
        newButton.addActionListener (this);
        cheatButton.addActionListener (this);
       
        // Display the collection of facedown cards
        shuffle ();
        
        // Resize the window to display all the cards and the buttons
        resize (400, 480);
    }
    
    /**
     * Display all the cards with randomly placed symbols
     */
    private void shuffle () {
        char c = 'a';
        cards.addCards (MARGIN, MARGIN, c, canvas);
    }
    
    /**
     * Determine which cards have been clicked on.
     */
    public void onMouseClick (Location point) {
        // If the user clicks on a facedown card and there are currently 0 or 1 face- up cards,
        // turn the card faceup
        if (faceUpCard1 == null && faceUpCard2 == null) {
            faceUpCard1 = cards.getSelectedCard (point);
            if (faceUpCard1 != null) {
                faceUpCard1.showSymbol ();
            }
        }
        
        else if (faceUpCard1 != null && faceUpCard2 == null) {
            faceUpCard2 = cards.getSelectedCard (point);
            if (faceUpCard1 == faceUpCard2) {
                faceUpCard2 = null;
            }
            else if (faceUpCard2 != null) {
                faceUpCard2.showSymbol ();
            }
        }
        
        // If the user clicks anywhere with 2 faceup cards, if the cards have the same symbol,
        // remove both of them from the display.
        // If the 2 faceup cards have different symbols, turn both of them facedown.
        else if (faceUpCard1 != null && faceUpCard2 != null) {
            if (faceUpCard1.getSymbol () == faceUpCard2.getSymbol ()) {
                faceUpCard1.removeFromCanvas ();
                faceUpCard2.removeFromCanvas ();
                cards.remove (faceUpCard1);
                cards.remove (faceUpCard2);
            }
            else if (cheated == false) {
                faceUpCard1.hideSymbol ();
                faceUpCard2.hideSymbol ();
            }
            faceUpCard1 = null;
            faceUpCard2 = null;
        }
        
    }
    
    /**
     * Handle events caused by the user clicking the button
     * @param event information about the user action being handled
     */
    public void actionPerformed (ActionEvent event) {
        // Find out which Swing component the user interacted with
        Object source = event.getSource ();
        
        // When the user clicks on the cheat button, turn all the cards faceup.
        // When the user clicks on the newgame button, recreate a new collection of cards.
        if (source == cheatButton) {
            cards.cheat ();
            cheated = true;
        }
        
        else if (source == newButton) {
            canvas.clear ();
            shuffle ();
            cheated = false;
        }
    }
}
