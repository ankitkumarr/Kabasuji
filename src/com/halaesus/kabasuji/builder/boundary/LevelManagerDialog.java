package com.halaesus.kabasuji.builder.boundary;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.halaesus.kabasuji.player.entity.LevelData;
import com.halaesus.kabasuji.player.entity.LevelList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

@SuppressWarnings("serial")
public class LevelManagerDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	JList<LevelData> levelListView;
	LevelList levelList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LevelManagerDialog dialog = new LevelManagerDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LevelManagerDialog() {
		levelList = LevelList.loadList();
		setBounds(100, 100, 380, 430);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		{
			levelListView = new JList<LevelData>();
			populateLevelListView();
			contentPanel.add(levelListView);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				GridBagLayout gbl_buttonPane = new GridBagLayout();
				gbl_buttonPane.columnWidths = new int[]{60, 60, 60, 60, 60, 0};
				gbl_buttonPane.rowHeights = new int[]{23, 0};
				gbl_buttonPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
				gbl_buttonPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
				buttonPane.setLayout(gbl_buttonPane);
				{
					JButton deleteButton = new JButton("Delete");
					deleteButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							levelList.deleteLevel(levelListView.getSelectedIndex());
							populateLevelListView();
						}
					});
					{
						JButton editButton = new JButton("Edit");
						editButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								// TODO call Application.show()
								levelList.loadLevel(levelListView.getSelectedIndex());
							}
						});
						JButton upButton = new JButton("Up");
						upButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								int selectedIndex = levelListView.getSelectedIndex();
								if(selectedIndex > 0) {
									levelList.swapIndexes(selectedIndex, selectedIndex - 1);
									populateLevelListView();
								}
							}
						});
						GridBagConstraints gbc_upButton = new GridBagConstraints();
						gbc_upButton.fill = GridBagConstraints.BOTH;
						gbc_upButton.insets = new Insets(0, 0, 0, 5);
						gbc_upButton.gridx = 0;
						gbc_upButton.gridy = 0;
						buttonPane.add(upButton, gbc_upButton);
						{
							JButton newButton = new JButton("New");
							newButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									// TODO call levelList.newLevel()
									NewLevelDialog.main(null);
								}
							});
							{
								JButton downButton = new JButton("Down");
								downButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										int selectedIndex = levelListView.getSelectedIndex();
										if(selectedIndex < levelListView.getMaxSelectionIndex()) {
											levelList.swapIndexes(selectedIndex, selectedIndex + 1);
											populateLevelListView();
										}
									}
								});
								GridBagConstraints gbc_downButton = new GridBagConstraints();
								gbc_downButton.fill = GridBagConstraints.BOTH;
								gbc_downButton.insets = new Insets(0, 0, 0, 5);
								gbc_downButton.gridx = 1;
								gbc_downButton.gridy = 0;
								buttonPane.add(downButton, gbc_downButton);
							}
							GridBagConstraints gbc_newButton = new GridBagConstraints();
							gbc_newButton.fill = GridBagConstraints.BOTH;
							gbc_newButton.insets = new Insets(0, 0, 0, 5);
							gbc_newButton.gridx = 2;
							gbc_newButton.gridy = 0;
							buttonPane.add(newButton, gbc_newButton);
						}
						GridBagConstraints gbc_editButton = new GridBagConstraints();
						gbc_editButton.fill = GridBagConstraints.BOTH;
						gbc_editButton.insets = new Insets(0, 0, 0, 5);
						gbc_editButton.gridx = 3;
						gbc_editButton.gridy = 0;
						buttonPane.add(editButton, gbc_editButton);
						getRootPane().setDefaultButton(editButton);
					}
					GridBagConstraints gbc_deleteButton = new GridBagConstraints();
					gbc_deleteButton.fill = GridBagConstraints.BOTH;
					gbc_deleteButton.gridx = 4;
					gbc_deleteButton.gridy = 0;
					buttonPane.add(deleteButton, gbc_deleteButton);
				}
			}
		}
	}
	
	private void populateLevelListView() {
		levelListView.setListData(levelList.getArray());
	}

}
