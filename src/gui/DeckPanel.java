package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import model.Deck;

public class DeckPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public DeckPanel(Deck d) {
		setLayout(null);
		
		String imageLocation = "/Images/"+d.getCardAt(0).toString()+".png";
		JLabel NumberRemainingLabel = new JLabel("Number Remain");
		NumberRemainingLabel.setLabelFor(this);
		NumberRemainingLabel.setBounds(42, 0, 27, 14);
		add(NumberRemainingLabel);
		JLabel CardImage = new JLabel("");
		CardImage.setBounds(10, 11, 96, 150);
		CardImage.setIcon(new ImageIcon(DeckPanel.class.getResource(imageLocation)));
		add(CardImage);
		JButton btnBuy = new JButton("Buy");
		btnBuy.setBounds(15, 162, 89, 23);
		add(btnBuy);

	}
}
