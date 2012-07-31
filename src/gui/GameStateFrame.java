package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import model.BlankCard;
import model.Deck;
import model.GameState;
import model.HumanPlayer;

import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import javax.swing.JLabel;
import javax.swing.JButton;

public class GameStateFrame extends JFrame implements ActionListener{
	private GameState game;
	private JPanel contentPane;
	/**
	 * Launch the application.
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */

	/**
	 * Create the frame.
	 */
	public GameStateFrame(GameState g) {
		setBounds(new Rectangle(0, 0, 10000, 10000));
		game = g;
		setMaximumSize(new Dimension(1000, 750));
		setMinimumSize(new Dimension(1000, 750));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1210, 750);
		contentPane = new JPanel();
		contentPane.setBounds(new Rectangle(0, 0, 1600, 900));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DeckPanel copperPanel = new DeckPanel(game.coppers);
		copperPanel.setMaximumSize(new Dimension(172, 314));
		copperPanel.setMinimumSize(new Dimension(172, 314));
		copperPanel.setPreferredSize(new Dimension(172, 314));
		copperPanel.setBounds(10, 27, 100, 214);
		contentPane.add(copperPanel);
		copperPanel.addButtonListener("coppers");
		
		DeckPanel silverPanel = new DeckPanel(game.silvers);
		silverPanel.setBounds(new Rectangle(0, 0, 100, 214));
		silverPanel.setPreferredSize(new Dimension(172, 314));
		silverPanel.setBounds(170, 27, 100, 214);
		contentPane.add(silverPanel);
		silverPanel.addButtonListener("silvers");
		
		DeckPanel goldPanel = new DeckPanel(game.golds);
		goldPanel.setBounds(320, 27, 100, 214);
		contentPane.add(goldPanel);
		goldPanel.addButtonListener("golds");
		
		DeckPanel estatePanel = new DeckPanel(game.estates);
		estatePanel.setBounds(470, 27, 100, 214);
		contentPane.add(estatePanel);
		estatePanel.addButtonListener("estates");
		
		DeckPanel duchyPanel = new DeckPanel((Deck) game.duchies);
		duchyPanel.setBounds(620, 27, 100, 214);
		contentPane.add(duchyPanel);
		duchyPanel.addButtonListener("duchies");
		
		DeckPanel provincePanel = new DeckPanel(game.provinces);
		provincePanel.setBounds(770, 27, 100, 214);
		contentPane.add(provincePanel);
		provincePanel.addButtonListener("provinces");
		
		DeckPanel community0Panel = new DeckPanel(game.communityPiles.get(0));
		community0Panel.setBounds(110, 252, 100, 214);
		contentPane.add(community0Panel);
		community0Panel.addButtonListener("0");
		
		DeckPanel community1Panel = new DeckPanel(game.communityPiles.get(1));
		community1Panel.setBounds(260, 252, 100, 214);
		contentPane.add(community1Panel);
		community1Panel.addButtonListener("1");
		
		DeckPanel community2Panel = new DeckPanel(game.communityPiles.get(2));
		community2Panel.setBounds(410, 252, 100, 214);
		contentPane.add(community2Panel);
		community2Panel.addButtonListener("2");
		
		DeckPanel community3Panel = new DeckPanel(game.communityPiles.get(3));
		community3Panel.setBounds(560, 252, 100, 214);
		contentPane.add(community3Panel);
		community3Panel.addButtonListener("3");
		
		DeckPanel community4Panel = new DeckPanel(game.communityPiles.get(4));
		community4Panel.setBounds(710, 252, 100, 214);
		contentPane.add(community4Panel);
		community4Panel.addButtonListener("4");
		
		DeckPanel community5Panel = new DeckPanel(game.communityPiles.get(5));
		community5Panel.setBounds(110, 490, 100, 214);
		contentPane.add(community5Panel);
		community5Panel.addButtonListener("5");
		
		DeckPanel community6Panel = new DeckPanel(game.communityPiles.get(6));
		community6Panel.setBounds(260, 490, 100, 214);
		contentPane.add(community6Panel);
		community6Panel.addButtonListener("6");
		
		DeckPanel community7Panel = new DeckPanel(game.communityPiles.get(7));
		community7Panel.setBounds(410, 490, 100, 214);
		contentPane.add(community7Panel);
		community7Panel.addButtonListener("7");
		
		DeckPanel community8Panel = new DeckPanel(game.communityPiles.get(8));
		community8Panel.setBounds(560, 490, 100, 214);
		contentPane.add(community8Panel);
		community8Panel.addButtonListener("8");
		
		DeckPanel community9Panel = new DeckPanel(game.communityPiles.get(9));
		community9Panel.setBounds(710, 490, 100, 214);
		contentPane.add(community9Panel);
		community9Panel.addButtonListener("9");
		
		
		JLabel playerNameLabel = new JLabel("Current Player: "+game.currentPlayer.name);
		playerNameLabel.setBounds(822, 399, 140, 14);
		contentPane.add(playerNameLabel);
		
		JLabel lblBuysLeft = new JLabel("Buys Left: "+game.currentPlayer.totalBuys);
		lblBuysLeft.setBounds(822, 526, 140, 14);
		contentPane.add(lblBuysLeft);
		
		JLabel totalMoneyLabel = new JLabel("Total Gold: "+game.currentPlayer.totalWorth);
		totalMoneyLabel.setBounds(822, 460, 140, 14);
		contentPane.add(totalMoneyLabel);
		
		JButton endTurnButton = new JButton("Finish Turn");
		endTurnButton.setBounds(822, 610, 89, 23);
		endTurnButton.setActionCommand("EndTurn");
		endTurnButton.addActionListener(this);
		contentPane.add(endTurnButton);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		String message = e.getActionCommand();
		if(message.contentEquals(""))
			return;
		synchronized(game.currentPlayer){
			if(message.contentEquals("EndTurn")){
				game.currentPlayer.selectedCardGUI = new BlankCard();
				game.currentPlayer.notify();
				dispose();
				return;
			}
			Deck d = null;
			//If it's a number
			try{
				int commNumber = Integer.parseInt(message);
				d = game.communityPiles.get(commNumber);
			}
			//If it's a normal deck
			catch(NumberFormatException notCommunityMessage){
				Field f = null;
				try {
					f = GameState.class.getField(e.getActionCommand());
				} catch (SecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchFieldException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					d = (Deck) f.get(game);
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			game.currentPlayer.selectedCardGUI = d.getCardAt(0);
			game.currentPlayer.notify();
			dispose();
		}
	}
}
