/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Alexia C. Durá
 */
public class Frame extends JFrame {
    
    //_______________________________
    
    
                //RESPONSIVE
                //INTERFACE
    
    
    //_________________________

    //VARIABLES
    //string array for changing images
    private String[] images = new String[9];
    //Declare panels to use
    private JPanel panel1, player1Panel, buttonSPanel, buttonGPanel, wordPanel, player2Panel, playerPanel, middlePanel, footerPanel, imagePanel, titlePanel;
    //label images and cite footer
    private JLabel cite, image, result, guessText, letterText, progressText, title,error;
    //JtextField 
    private JTextField word, letter;
    //Jbuttons
    private JButton saveW, saveL;

    //CONSTRUCTOR
    //============================
    public Frame() {
        //set size
        setSize(800, 550);
        setMinimumSize(new Dimension(620, 450));
        //set title frame
        setTitle("Ahorcado");
        //set window buttons
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //initiailize panels and layouts
        panel1 = new JPanel(new BorderLayout());
        titlePanel = new JPanel(new FlowLayout());
        playerPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        player1Panel = new JPanel(new GridLayout(3, 1, 30, 20));
        player2Panel = new JPanel(new GridLayout(3, 3, 30, 20));
        middlePanel = new JPanel(new BorderLayout());
        imagePanel = new JPanel(new BorderLayout());
        buttonSPanel = new JPanel(new FlowLayout());
        buttonGPanel = new JPanel(new FlowLayout());
        wordPanel = new JPanel(new FlowLayout());
        footerPanel = new JPanel(new FlowLayout());

        //FILL ARRAY IMAGES
        //=======================
        for (int i = 0; i < 8; i++) {
            images[i] = "img/hangman" + (i+2) + ".png";
        }

        // JTEXTFIELD
        //=====================
        word = new JTextField();
        letter = new JTextField();

        //JLABEL
        //-------------------------------------
        //image label
        image = new JLabel();
        image.setPreferredSize(new Dimension(400, 300));
        imagePanel.setPreferredSize(new Dimension(400, 300));
        //player one word
        guessText = new JLabel("Insert Word");
        //player 2 letter
        letterText = new JLabel("letter: ");
        //player 2 play word
        progressText = new JLabel("Guess word: ");
        //player 2 result
        result = new JLabel("");
        //player 2 errors
        error= new JLabel("lifes: 7");
        //footer cire
        cite = new JLabel("Alexia C. Durá");
        footerPanel.add(cite);
        //titles
        title = new JLabel("Hangman");
        //Set font to titles
        title.setFont(new Font("Serif", Font.BOLD, 35));
        titlePanel.add(title);

        //JBUTTON
        //===================
        //player 1
        saveW = new JButton("Save");
        buttonSPanel.add(saveW);
        //plaey2
        saveL = new JButton("Guess");
        buttonGPanel.add(saveL);
        
        //LISTENERS
        //==============
        Listener player = new Listener(word,letter, saveW,saveL, images, image,result, guessText, letterText, progressText,player1Panel,player2Panel,error);
          saveW.addActionListener(player);
          saveL.addActionListener(player);
          
             //Disable player2
        //============================
        letter.setEnabled(false);
        letterText.setEnabled(false);
        progressText.setEnabled(false);
        result.setEnabled(false);
        saveL.setEnabled(false);
        player2Panel.setEnabled(false);
        error.setEnabled(false);

        //BORDERS
        //-------------------------------------
        Border bordert = new TitledBorder(new EtchedBorder());
        titlePanel.setBorder(bordert);

        Border borderi = new TitledBorder(new EtchedBorder(), "You");
        imagePanel.setBorder(borderi);

        Border borders = new TitledBorder(new EtchedBorder(), "Player 1");
        player1Panel.setBorder(borders);

        Border borderp = new TitledBorder(new EtchedBorder(), "Player 2");
        player2Panel.setBorder(borderp);

        Border borderf = new TitledBorder(new EtchedBorder(), "By");
        footerPanel.setBorder(borderf);

    
        //COLOURS
        //============================
        titlePanel.setBackground(Color.GRAY);
        title.setForeground(Color.WHITE);
        cite.setForeground(Color.WHITE);
        footerPanel.setBackground(Color.GRAY);
        imagePanel.setBackground(Color.LIGHT_GRAY);
        player1Panel.setBackground(Color.LIGHT_GRAY);
        player2Panel.setBackground(Color.LIGHT_GRAY);
        playerPanel.setBackground(Color.GRAY);
        wordPanel.setBackground(Color.LIGHT_GRAY);
        buttonSPanel.setBackground(Color.LIGHT_GRAY);
        buttonGPanel.setBackground(Color.LIGHT_GRAY);
        
       //ADD PANELS
        //=================
        //add panels to mainPanels
        imagePanel.add(image,BorderLayout.CENTER);
         //set margin to imagePanel
        Border borderI = imagePanel.getBorder();
        Border marginp = new EmptyBorder(0, 40, 0, 0);
        imagePanel.setBorder(new CompoundBorder(borderI, marginp));
        wordPanel.add(guessText);
        player1Panel.add(wordPanel);
        player1Panel.add(word);
        player1Panel.add(buttonSPanel);
        player2Panel.add(letterText);
        player2Panel.add(letter);
        player2Panel.add(progressText);
        player2Panel.add(result);
        player2Panel.add(buttonGPanel);
         player2Panel.add(error);
        playerPanel.add(player1Panel);
        playerPanel.add(player2Panel);
        middlePanel.add(imagePanel, BorderLayout.WEST);
        middlePanel.add(playerPanel, BorderLayout.CENTER);

        //add panels to frame
        panel1.add(titlePanel, BorderLayout.NORTH);
        panel1.add(middlePanel, BorderLayout.CENTER);
        panel1.add(footerPanel, BorderLayout.SOUTH);
        add(panel1);
    }

}
