package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import model.Card;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;

public class CardSelectionFrame extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardSelectionFrame frame = new CardSelectionFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CardSelectionFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 798, 616);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{104, 0, 0, 0, 0, 73, 0};
		gridBagLayout.rowHeights = new int[]{209, 209, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		CardSelector cardSelector_0 = new CardSelector((Card) null);
		GridBagConstraints gbc_cardSelector_0 = new GridBagConstraints();
		gbc_cardSelector_0.fill = GridBagConstraints.VERTICAL;
		gbc_cardSelector_0.anchor = GridBagConstraints.WEST;
		gbc_cardSelector_0.insets = new Insets(0, 0, 5, 5);
		gbc_cardSelector_0.gridx = 0;
		gbc_cardSelector_0.gridy = 0;
		getContentPane().add(cardSelector_0, gbc_cardSelector_0);
		
		CardSelector cardSelector_1 = new CardSelector((Card) null);
		GridBagConstraints gbc_cardSelector_1 = new GridBagConstraints();
		gbc_cardSelector_1.insets = new Insets(0, 0, 5, 5);
		gbc_cardSelector_1.fill = GridBagConstraints.BOTH;
		gbc_cardSelector_1.gridx = 1;
		gbc_cardSelector_1.gridy = 0;
		getContentPane().add(cardSelector_1, gbc_cardSelector_1);
		
		CardSelector cardSelector_2 = new CardSelector((Card) null);
		GridBagConstraints gbc_cardSelector_2 = new GridBagConstraints();
		gbc_cardSelector_2.insets = new Insets(0, 0, 5, 5);
		gbc_cardSelector_2.fill = GridBagConstraints.BOTH;
		gbc_cardSelector_2.gridx = 2;
		gbc_cardSelector_2.gridy = 0;
		getContentPane().add(cardSelector_2, gbc_cardSelector_2);
		
		CardSelector cardSelector_3 = new CardSelector((Card) null);
		GridBagConstraints gbc_cardSelector_3 = new GridBagConstraints();
		gbc_cardSelector_3.insets = new Insets(0, 0, 5, 5);
		gbc_cardSelector_3.fill = GridBagConstraints.BOTH;
		gbc_cardSelector_3.gridx = 3;
		gbc_cardSelector_3.gridy = 0;
		getContentPane().add(cardSelector_3, gbc_cardSelector_3);
		
		CardSelector cardSelector_4 = new CardSelector((Card) null);
		GridBagConstraints gbc_cardSelector_4 = new GridBagConstraints();
		gbc_cardSelector_4.insets = new Insets(0, 0, 5, 5);
		gbc_cardSelector_4.fill = GridBagConstraints.BOTH;
		gbc_cardSelector_4.gridx = 4;
		gbc_cardSelector_4.gridy = 0;
		getContentPane().add(cardSelector_4, gbc_cardSelector_4);
		
		CardSelector cardSelector_5 = new CardSelector((Card) null);
		GridBagConstraints gbc_cardSelector_5 = new GridBagConstraints();
		gbc_cardSelector_5.insets = new Insets(0, 0, 5, 5);
		gbc_cardSelector_5.fill = GridBagConstraints.BOTH;
		gbc_cardSelector_5.gridx = 0;
		gbc_cardSelector_5.gridy = 1;
		getContentPane().add(cardSelector_5, gbc_cardSelector_5);
		
		CardSelector cardSelector_6 = new CardSelector((Card) null);
		GridBagConstraints gbc_cardSelector_6 = new GridBagConstraints();
		gbc_cardSelector_6.insets = new Insets(0, 0, 5, 5);
		gbc_cardSelector_6.fill = GridBagConstraints.BOTH;
		gbc_cardSelector_6.gridx = 1;
		gbc_cardSelector_6.gridy = 1;
		getContentPane().add(cardSelector_6, gbc_cardSelector_6);
		
		CardSelector cardSelector_7 = new CardSelector((Card) null);
		GridBagConstraints gbc_cardSelector_7 = new GridBagConstraints();
		gbc_cardSelector_7.insets = new Insets(0, 0, 5, 5);
		gbc_cardSelector_7.fill = GridBagConstraints.BOTH;
		gbc_cardSelector_7.gridx = 2;
		gbc_cardSelector_7.gridy = 1;
		getContentPane().add(cardSelector_7, gbc_cardSelector_7);
		
		CardSelector cardSelector_8 = new CardSelector((Card) null);
		GridBagConstraints gbc_cardSelector_8 = new GridBagConstraints();
		gbc_cardSelector_8.insets = new Insets(0, 0, 5, 5);
		gbc_cardSelector_8.fill = GridBagConstraints.BOTH;
		gbc_cardSelector_8.gridx = 3;
		gbc_cardSelector_8.gridy = 1;
		getContentPane().add(cardSelector_8, gbc_cardSelector_8);
		
		CardSelector cardSelector_9 = new CardSelector((Card) null);
		GridBagConstraints gbc_cardSelector_9 = new GridBagConstraints();
		gbc_cardSelector_9.insets = new Insets(0, 0, 5, 5);
		gbc_cardSelector_9.fill = GridBagConstraints.BOTH;
		gbc_cardSelector_9.gridx = 4;
		gbc_cardSelector_9.gridy = 1;
		getContentPane().add(cardSelector_9, gbc_cardSelector_9);
		
		JButton btnNoCard = new JButton("Skip");
		GridBagConstraints gbc_btnNoCard = new GridBagConstraints();
		gbc_btnNoCard.gridx = 5;
		gbc_btnNoCard.gridy = 2;
		getContentPane().add(btnNoCard, gbc_btnNoCard);
	}

}
