package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import model.Deck;
import model.Card;
import java.awt.Color;
import java.awt.Dimension;

public class DeckPanel extends JPanel{

	/**
	 * Create the panel.
	 */
	public Deck storedDeck;
	public JButton theBuyButton;
	public DeckPanel() {
		setPreferredSize(new Dimension(108, 216));
		setLayout(null);
		
		String imageLocation = "/Images/"+"back.png";
		JLabel NumberRemainingLabel = new JLabel("Number Remain");
		NumberRemainingLabel.setLabelFor(this);
		NumberRemainingLabel.setBounds(34, 11, 27, 14);
		add(NumberRemainingLabel);
		JLabel CardImage = new JLabel("");
		CardImage.setPreferredSize(new Dimension(167, 245));
		CardImage.setBackground(new Color(240, 240, 240));
		CardImage.setBounds(0, 24, 96, 150);
		CardImage.setIcon(new ImageIcon(DeckPanel.class.getResource(imageLocation)));
		add(CardImage);
		JButton btnBuy = new JButton("Buy");
		btnBuy.setBounds(0, 185, 96, 23);
		theBuyButton = btnBuy;
		add(btnBuy);
		
		JLabel costLabel = new JLabel("Cost:0");
		costLabel.setBounds(23, 171, 46, 14);
		add(costLabel);
	}
	public DeckPanel(Deck d) {
		storedDeck = d;
		setLayout(null);
		String imageLocation = "/Images/"+d.getCardAt(0).toString()+".png";
		JLabel NumberRemainingLabel = new JLabel(Integer.toString(d.size()));
		NumberRemainingLabel.setLabelFor(this);
		NumberRemainingLabel.setBounds(34, 11, 27, 14);
		add(NumberRemainingLabel);
		JLabel CardImage = new JLabel("");
		CardImage.setBounds(0, 24, 96, 150);
		CardImage.setIcon(new ImageIcon(DeckPanel.class.getResource(imageLocation)));
		add(CardImage);
		JButton btnBuy = new JButton("Buy"+d.getCardAt(0).toString());
		btnBuy.setBounds(0, 185, 96, 23);
		theBuyButton = btnBuy;
		add(btnBuy);
		JLabel costLabel = new JLabel("Cost:"+d.getCardAt(0).getCost());
		costLabel.setBounds(23, 171, 46, 14);
		add(costLabel);
	}
	public void addButtonListener(String message){
		theBuyButton.setActionCommand(message);
		ActionListener myFrame = (ActionListener) SwingUtilities.getWindowAncestor(this);
		theBuyButton.addActionListener(myFrame);
	}
}