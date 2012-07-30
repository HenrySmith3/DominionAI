package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingUtilities;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;

import model.ActionCard;
import model.BlankCard;
import model.Card;
import model.CardType;
import model.Deck;
import model.GameState;
import model.HumanPlayer;

import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class CardSelectionFrame extends JFrame implements ActionListener{

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public int viewIndex;
	public Deck viewingDeck;
	public HumanPlayer player;
	public CardSelectionFrame(Deck d, HumanPlayer p,CardType t) {
		setResizable(false);
		viewingDeck = d;
		player = p;
		viewIndex = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1210, 750);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{120, 120, 120, 120, 120, 120, 120};
		gridBagLayout.rowHeights = new int[]{209, 209, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		CardSelector cardSelector_0;
		if(viewingDeck.size()<=viewIndex)
			cardSelector_0 = new CardSelector((Card) null,t);
		else{
			cardSelector_0 = new CardSelector(viewingDeck.getCardAt(viewIndex),t);
			if(viewingDeck.getCardAt(viewIndex) instanceof ActionCard)
				cardSelector_0.setEnabledActionCard(p.numActions);
		}
		GridBagConstraints gbc_cardSelector_0 = new GridBagConstraints();
		gbc_cardSelector_0.insets = new Insets(0, 0, 5, 5);
		gbc_cardSelector_0.fill = GridBagConstraints.BOTH;
		gbc_cardSelector_0.gridx = 0;
		gbc_cardSelector_0.gridy = 0;
		getContentPane().add(cardSelector_0, gbc_cardSelector_0);
		viewIndex++;
		
		
		CardSelector cardSelector_1;
		if(viewingDeck.size()<=viewIndex)
			cardSelector_1 = new CardSelector((Card) null,t);
		else{
			cardSelector_1 = new CardSelector(viewingDeck.getCardAt(viewIndex),t);
			if(viewingDeck.getCardAt(viewIndex) instanceof ActionCard)
				cardSelector_1.setEnabledActionCard(p.numActions);
		}
		GridBagConstraints gbc_cardSelector_1 = new GridBagConstraints();
		gbc_cardSelector_1.insets = new Insets(0, 0, 5, 5);
		gbc_cardSelector_1.fill = GridBagConstraints.BOTH;
		gbc_cardSelector_1.gridx = 1;
		gbc_cardSelector_1.gridy = 0;
		getContentPane().add(cardSelector_1, gbc_cardSelector_1);
		viewIndex++;
		
		CardSelector cardSelector_2;
		if(viewingDeck.size()<=viewIndex)
			cardSelector_2 = new CardSelector((Card) null,t);
		else{
			cardSelector_2 = new CardSelector(viewingDeck.getCardAt(viewIndex),t);
			if(viewingDeck.getCardAt(viewIndex) instanceof ActionCard)
				cardSelector_2.setEnabledActionCard(p.numActions);
		}
		GridBagConstraints gbc_cardSelector_2 = new GridBagConstraints();
		gbc_cardSelector_2.insets = new Insets(0, 0, 5, 5);
		gbc_cardSelector_2.fill = GridBagConstraints.BOTH;
		gbc_cardSelector_2.gridx = 2;
		gbc_cardSelector_2.gridy = 0;
		getContentPane().add(cardSelector_2, gbc_cardSelector_2);
		viewIndex++;
		
		CardSelector cardSelector_3;
		if(viewingDeck.size()<=viewIndex)
			cardSelector_3 = new CardSelector((Card) null,t);
		else{
			cardSelector_3 = new CardSelector(viewingDeck.getCardAt(viewIndex),t);
			if(viewingDeck.getCardAt(viewIndex) instanceof ActionCard)
				cardSelector_3.setEnabledActionCard(p.numActions);
		}
		GridBagConstraints gbc_cardSelector_3 = new GridBagConstraints();
		gbc_cardSelector_3.insets = new Insets(0, 0, 5, 5);
		gbc_cardSelector_3.fill = GridBagConstraints.BOTH;
		gbc_cardSelector_3.gridx = 3;
		gbc_cardSelector_3.gridy = 0;
		getContentPane().add(cardSelector_3, gbc_cardSelector_3);
		viewIndex++;
		
		CardSelector cardSelector_4;
		if(viewingDeck.size()<=viewIndex)
			cardSelector_4 = new CardSelector((Card) null,t);
		else{
			cardSelector_4 = new CardSelector(viewingDeck.getCardAt(viewIndex),t);
			if(viewingDeck.getCardAt(viewIndex) instanceof ActionCard)
				cardSelector_4.setEnabledActionCard(p.numActions);
		}
		GridBagConstraints gbc_cardSelector_4 = new GridBagConstraints();
		gbc_cardSelector_4.insets = new Insets(0, 0, 5, 5);
		gbc_cardSelector_4.fill = GridBagConstraints.BOTH;
		gbc_cardSelector_4.gridx = 4;
		gbc_cardSelector_4.gridy = 0;
		getContentPane().add(cardSelector_4, gbc_cardSelector_4);
		viewIndex++;
		
		CardSelector cardSelector_5;
		if(viewingDeck.size()<=viewIndex)
			cardSelector_5 = new CardSelector((Card) null,t);
		else{
			cardSelector_5 = new CardSelector(viewingDeck.getCardAt(viewIndex),t);
			if(viewingDeck.getCardAt(viewIndex) instanceof ActionCard)
				cardSelector_5.setEnabledActionCard(p.numActions);
		}
		GridBagConstraints gbc_cardSelector_5 = new GridBagConstraints();
		gbc_cardSelector_5.insets = new Insets(0, 0, 5, 5);
		gbc_cardSelector_5.fill = GridBagConstraints.BOTH;
		gbc_cardSelector_5.gridx = 0;
		gbc_cardSelector_5.gridy = 1;
		getContentPane().add(cardSelector_5, gbc_cardSelector_5);
		viewIndex++;
		
		CardSelector cardSelector_6;
		if(viewingDeck.size()<=viewIndex)
			cardSelector_6 = new CardSelector((Card) null,t);
		else{
			cardSelector_6 = new CardSelector(viewingDeck.getCardAt(viewIndex),t);
			if(viewingDeck.getCardAt(viewIndex) instanceof ActionCard)
				cardSelector_6.setEnabledActionCard(p.numActions);
		}
		GridBagConstraints gbc_cardSelector_6 = new GridBagConstraints();
		gbc_cardSelector_6.insets = new Insets(0, 0, 5, 5);
		gbc_cardSelector_6.fill = GridBagConstraints.BOTH;
		gbc_cardSelector_6.gridx = 1;
		gbc_cardSelector_6.gridy = 1;
		getContentPane().add(cardSelector_6, gbc_cardSelector_6);
		viewIndex++;
		
		CardSelector cardSelector_7;
		if(viewingDeck.size()<=viewIndex)
			cardSelector_7 = new CardSelector((Card) null,t);
		else{
			cardSelector_7 = new CardSelector(viewingDeck.getCardAt(viewIndex),t);
			if(viewingDeck.getCardAt(viewIndex) instanceof ActionCard)
				cardSelector_7.setEnabledActionCard(p.numActions);
		}
		GridBagConstraints gbc_cardSelector_7 = new GridBagConstraints();
		gbc_cardSelector_7.insets = new Insets(0, 0, 5, 5);
		gbc_cardSelector_7.fill = GridBagConstraints.BOTH;
		gbc_cardSelector_7.gridx = 2;
		gbc_cardSelector_7.gridy = 1;
		getContentPane().add(cardSelector_7, gbc_cardSelector_7);
		viewIndex++;
		
		CardSelector cardSelector_8;
		if(viewingDeck.size()<=viewIndex)
			cardSelector_8 = new CardSelector((Card) null,t);
		else{
			cardSelector_8 = new CardSelector(viewingDeck.getCardAt(viewIndex),t);
			if(viewingDeck.getCardAt(viewIndex) instanceof ActionCard)
				cardSelector_8.setEnabledActionCard(p.numActions);
		}
		GridBagConstraints gbc_cardSelector_8 = new GridBagConstraints();
		gbc_cardSelector_8.insets = new Insets(0, 0, 5, 5);
		gbc_cardSelector_8.fill = GridBagConstraints.BOTH;
		gbc_cardSelector_8.gridx = 3;
		gbc_cardSelector_8.gridy = 1;
		getContentPane().add(cardSelector_8, gbc_cardSelector_8);
		viewIndex++;
		
		CardSelector cardSelector_9;
		if(viewingDeck.size()<=viewIndex)
			cardSelector_9 = new CardSelector((Card) null,t);
		else{
			cardSelector_9 = new CardSelector(viewingDeck.getCardAt(viewIndex),t);
			if(viewingDeck.getCardAt(viewIndex) instanceof ActionCard)
				cardSelector_9.setEnabledActionCard(p.numActions);
		}
		GridBagConstraints gbc_cardSelector_9 = new GridBagConstraints();
		gbc_cardSelector_9.insets = new Insets(0, 0, 5, 5);
		gbc_cardSelector_9.fill = GridBagConstraints.BOTH;
		gbc_cardSelector_9.gridx = 4;
		gbc_cardSelector_9.gridy = 1;
		getContentPane().add(cardSelector_9, gbc_cardSelector_9);
		
		viewIndex = viewIndex-9;
		
		JButton upButton = new JButton("Up");
		upButton.setActionCommand("Up");
		upButton.addActionListener(this);
		GridBagConstraints gbc_upButton = new GridBagConstraints();
		gbc_upButton.insets = new Insets(0, 0, 5, 0);
		gbc_upButton.gridx = 5;
		gbc_upButton.gridy = 0;
		getContentPane().add(upButton, gbc_upButton);
		
		JButton downButton = new JButton("Down");
		downButton.setActionCommand("Down");
		downButton.addActionListener(this);
		GridBagConstraints gbc_downButton = new GridBagConstraints();
		gbc_downButton.insets = new Insets(0, 0, 5, 0);
		gbc_downButton.gridx = 5;
		gbc_downButton.gridy = 1;
		getContentPane().add(downButton, gbc_downButton);
		
		JLabel actionNumLabel = new JLabel("Actions:"+player.numActions);
		GridBagConstraints gbc_actionNumLabel = new GridBagConstraints();
		gbc_actionNumLabel.insets = new Insets(0, 0, 0, 5);
		gbc_actionNumLabel.gridx = 3;
		gbc_actionNumLabel.gridy = 2;
		getContentPane().add(actionNumLabel, gbc_actionNumLabel);
		
		JLabel nameLabel = new JLabel("Player:"+player.name);
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.insets = new Insets(0, 0, 0, 5);
		gbc_nameLabel.gridx = 4;
		gbc_nameLabel.gridy = 2;
		getContentPane().add(nameLabel, gbc_nameLabel);
		
		JButton btnNoCard = new JButton("Skip");
		GridBagConstraints gbc_btnNoCard = new GridBagConstraints();
		gbc_btnNoCard.gridx = 5;
		gbc_btnNoCard.gridy = 2;
		getContentPane().add(btnNoCard, gbc_btnNoCard);
		btnNoCard.setActionCommand("Skip");
		btnNoCard.addActionListener(this);
		
		cardSelector_0.addButtonListener(Integer.toString(viewIndex));
		cardSelector_1.addButtonListener(Integer.toString(viewIndex+1));
		cardSelector_2.addButtonListener(Integer.toString(viewIndex+2));
		cardSelector_3.addButtonListener(Integer.toString(viewIndex+3));
		cardSelector_4.addButtonListener(Integer.toString(viewIndex+4));
		cardSelector_5.addButtonListener(Integer.toString(viewIndex+5));
		cardSelector_6.addButtonListener(Integer.toString(viewIndex+6));
		cardSelector_7.addButtonListener(Integer.toString(viewIndex+7));
		cardSelector_8.addButtonListener(Integer.toString(viewIndex+8));
		cardSelector_9.addButtonListener(Integer.toString(viewIndex+9));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	public void updateCardView(int offset){
		viewIndex += offset;
		if(viewIndex<0)
			viewIndex = 0;
		if(viewIndex>=viewingDeck.size())
			viewIndex = viewingDeck.size()-5; 
		for(int i=0;i<10;i++){ //Make sure to add the jPanels first
			if(viewingDeck.getCardAt(i+viewIndex) == null)
				((CardSelector)this.getComponent(i)).changePanel(null, "");
			else
				((CardSelector)this.getComponent(i)).changePanel(viewingDeck.getCardAt(i+viewIndex), ""+(i+viewIndex));
		}
		SwingUtilities.updateComponentTreeUI(this);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand() == "")
			return;
		if(e.getActionCommand() == "Up"){
			updateCardView(-5);
			return;
		}
		if(e.getActionCommand() == "Down"){
			updateCardView(5);
			return;
		}
		synchronized(player){
			if(e.getActionCommand() == "Skip"){
				HumanPlayer.selectedCardGUI = new BlankCard();
				player.notify();
				dispose();
				return;
			}
			int cardIndex = Integer.parseInt(e.getActionCommand());
			HumanPlayer.selectedCardGUI = viewingDeck.getCardAt(cardIndex);
			//Need to select appropriatly here
			player.notify();
			dispose();
			//What do we do now? Now that we have the card we need	
		}
	}
}