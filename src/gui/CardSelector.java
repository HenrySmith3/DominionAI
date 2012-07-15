package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import model.Card;
import model.Deck;

public class CardSelector extends JPanel {

	/**
	 * Create the panel.
	 */
	public CardSelector(Card c) {
		setLayout(null);
		
		JLabel CardImage = new JLabel("");
		CardImage.setBounds(10, 11, 96, 150);
		String imageLocation = "/Images/"+c.toString()+".png";
		CardImage.setIcon(new ImageIcon(DeckPanel.class.getResource(imageLocation)));
		add(CardImage);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.setBounds(15, 162, 89, 23);
		add(btnSelect);
	}
	/**
	 * Default Constructor should never be called 
	 */
	public CardSelector() {
		setLayout(null);
		JLabel CardImage = new JLabel("");
		CardImage.setBounds(10, 11, 96, 150);
		CardImage.setIcon(new ImageIcon("/Images/Back.png"));
		add(CardImage);
		JButton btnSelect = new JButton("Select");
		btnSelect.setBounds(15, 162, 89, 23);
		add(btnSelect);
	}
}