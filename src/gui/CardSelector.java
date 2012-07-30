package gui;

import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import model.ActionCard;
import model.ActionCardTypes;
import model.Card;
import model.CardType;
import model.Deck;

public class CardSelector extends JPanel {

	/**
	 * Create the panel.
	 */
	public JButton selectBtn;
	public CardSelector(Card c,CardType t) {
		setLayout(null);
		
		JLabel CardImage = new JLabel("");
		CardImage.setBounds(10, 11, 96, 150);
		String imageLocation;
		try{
			imageLocation = "/Images/"+c.toString()+".png";
		}
		catch(NullPointerException npe){
			imageLocation = "/Images/Back.png";
		}
		CardImage.setIcon(new ImageIcon(DeckPanel.class.getResource(imageLocation)));
		add(CardImage);
		JButton btnSelect = new JButton("Select");
		btnSelect.setBounds(15, 162, 89, 23);
		try{
			btnSelect.setEnabled(t == null || c.getType().equals(t));
		}
		catch(NullPointerException npe){
			btnSelect.setEnabled(false);
		}
		selectBtn = btnSelect;
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
		btnSelect.setActionCommand("");
		add(btnSelect);
	}
	public void changePanel(Card c,String message){
		String imageLocation = "/Images/"+c.toString()+".png";
		((JLabel)this.getComponent(1)).setIcon(new ImageIcon(DeckPanel.class.getResource(imageLocation)));
		((JButton)this.getComponent(2)).setActionCommand(message);
		addButtonListener(message);
	}
	
	public void addButtonListener(String message){
		selectBtn.setActionCommand(message);
		ActionListener myFrame = (ActionListener) SwingUtilities.getWindowAncestor(this);
		selectBtn.addActionListener(myFrame);
	}
	public void setEnabledActionCard(int num){
		selectBtn.setEnabled(selectBtn.isEnabled() && num > 0);
	}
}