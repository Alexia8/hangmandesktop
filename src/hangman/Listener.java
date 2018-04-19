/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Alexia C. Durá
 */
public class Listener implements ActionListener {

    //VARIABLES
    //==============================
    private String wordOk, playWord = "", cryptWord = "";
    private JTextField word, letter;
    private JButton saveW, saveL;
    private String[] images;
    private JLabel image, result, guessText, letterText, progressText, count;
    private JPanel player1Panel, player2Panel;
    private char letterGuessed;
    private boolean match = false, done = false;
    //initialize to -1 one for first round start in 0
    private int error = -1;

    //CONSTRUCTOR
    //================================
    public Listener(JTextField word, JTextField letter, JButton saveW, JButton saveL, String[] images, JLabel image, JLabel result, JLabel guessText, JLabel letterText, JLabel progressText, JPanel player1Panel, JPanel player2Panel, JLabel count) {
        this.word = word;
        this.letter = letter;
        this.saveW = saveW;
        this.saveL = saveL;
        this.images = images;
        this.image = image;
        this.result = result;
        this.guessText = guessText;
        this.letterText = letterText;
        this.progressText = progressText;
        this.player1Panel = player1Panel;
        this.player2Panel = player2Panel;
        this.count = count;
    }

    //Method controls Game
    @Override
    public void actionPerformed(ActionEvent e) {

        //PLAYER 1
        //---------------------------------
        //if word is isnserted
        if (e.getSource() == saveW) {
            //store word in game
            wordOk = word.getText();
            //set initial image position
            image.setIcon(new ImageIcon(images[1]));

            //Disable player 1
            word.setEditable(false);
            saveW.setEnabled(false);
            guessText.setEnabled(false);
            player1Panel.setEnabled(false);

            //crypt word and relpace word to play for player 2
            for (int i = 0; i < wordOk.length(); i++) {
                playWord += "-";
                cryptWord += " * ";
            }

            //Activate player2
            letter.setEnabled(true);
            letterText.setEnabled(true);
            progressText.setEnabled(true);
            result.setEnabled(true);
            saveL.setEnabled(true);
            player2Panel.setEnabled(true);
            count.setEnabled(true);
            //set playword
            result.setText(playWord);
            //set crypt word
            word.setText(cryptWord);

            if (word.getText().isEmpty()) {
                //if word not inserted show message
                JOptionPane.showMessageDialog(null, "Insert word to start game!!", "WARNING", JOptionPane.WARNING_MESSAGE);
            }
        }

        //PLAYER2
        //--------------------------------------
        //if letter is inserted
        if (e.getSource() == saveL) {
            //set match pff;
            match = false;
            //build play word to replace
            StringBuilder playWordG = new StringBuilder(playWord);
            //store guessed leter
            letterGuessed = letter.getText().charAt(0);
            //if guessed letter not already in play word
            if (playWord.indexOf(letterGuessed) == -1) {
                //for char length in word inserted
                for (int i = 0; i < wordOk.length(); i++) {
                    // match chosenWord char array to input letter
                    if (Character.toLowerCase(wordOk.charAt(i)) == letterGuessed) {
                        // switch match on
                        match = true;
                        // replace playWord position with letter value
                        playWordG.setCharAt(i, letterGuessed);
                        //replace playword
                        playWord = playWordG.toString();
                        //set result to new playword
                        result.setText(playWord);
                    }
                }
            } else {
                //if letter already in playword show repeat
                JOptionPane.showMessageDialog(null, "Repeated letter", "WARNING", JOptionPane.WARNING_MESSAGE);
            }
            //reset letter guesed after every round
            letter.setText("");
        }

        //lf letter match
        if (match == true) {
            //if playword equal to word inserted
            if (playWord.equalsIgnoreCase(wordOk)) {
                // switch for WIN game on
                done = true;
            }
        } else {
            //count errors for images array
            error++;
            //set lives to errors
            String aux = Integer.toString(7 - error);
            //show lives to player 2
            count.setText("lifes: " + aux);
            //if no letter match change hangman image
            image.setIcon(new ImageIcon(images[error]));

        }

        //if WIN
        if (done) {
            //show win
            JOptionPane.showMessageDialog(null, "YOU WIN!!", "WINNER", JOptionPane.INFORMATION_MESSAGE);
            //show new game
            int dialogResult = JOptionPane.showConfirmDialog(null, "Start new game?", "Message", JOptionPane.YES_NO_OPTION);
            //if yes
            if (dialogResult == JOptionPane.YES_OPTION) {
                //CALL RESET
                ResetGame();
            } else {
                //CALL END
                endGame();
            }
        }

        if (error == 7) {
            //show loose 
            JOptionPane.showMessageDialog(null, "YOU LOOSE!!", "LOOSER", JOptionPane.ERROR_MESSAGE);
            //show new game
            int dialogResult = JOptionPane.showConfirmDialog(null, "Start new game?", "Message", JOptionPane.YES_NO_OPTION);
            //if yes
            if (dialogResult == JOptionPane.YES_OPTION) {
                //CALL RESET
                ResetGame();
            } else {
                //CALL END
                endGame();
            }
        }

    }

    public void ResetGame() {
        //RESET GAME
        //==================================
        //Disable player2
        letter.setEnabled(false);
        letterText.setEnabled(false);
        progressText.setEnabled(false);
        result.setEnabled(false);
        saveL.setEnabled(false);
        player2Panel.setEnabled(false);
        count.setEnabled(false);
        //reset all fields
        //-----------------------
        wordOk = "";
        playWord = "";
        cryptWord = "";
        letter.setText("");
        count.setText("lifes: 7");
          //set playword
        result.setText("");
        //set crypt word
        word.setText("");

        //turn all switches to false
        match = false;
        done = false;
        //reset count
        error = 0;

        //Activate player 1
        word.setEditable(true);
        saveW.setEnabled(true);
        guessText.setEnabled(true);
        player1Panel.setEnabled(true);
        image.setIcon(new ImageIcon());

    }

    public void endGame() {
        System.exit(0);
    }

}
