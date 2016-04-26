package com.halaesus.kabasuji.builder.boundary;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
		setBounds(100, 100, 319, 449);
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
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
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
				buttonPane.add(upButton);
			}
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
				buttonPane.add(downButton);
			}
			{
				JButton newButton = new JButton("New");
				newButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// TODO call levelList.newLevel()
					}
				});
				buttonPane.add(newButton);
			}
			{
				JButton editButton = new JButton("Edit");
				editButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// TODO call Application.show()
						levelList.loadLevel(levelListView.getSelectedIndex());
					}
				});
				buttonPane.add(editButton);
				getRootPane().setDefaultButton(editButton);
			}
			{
				JButton deleteButton = new JButton("Delete");
				deleteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						levelList.deleteLevel(levelListView.getSelectedIndex());
						populateLevelListView();
					}
				});
				buttonPane.add(deleteButton);
			}
		}
	}
	
	private void populateLevelListView() {
		levelListView.setListData(levelList.getArray());
	}

}
