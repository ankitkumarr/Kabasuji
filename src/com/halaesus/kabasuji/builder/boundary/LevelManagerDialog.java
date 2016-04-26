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
		setBounds(100, 100, 450, 300);
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
				buttonPane.add(upButton);
			}
			{
				JButton downButton = new JButton("Down");
				buttonPane.add(downButton);
			}
			{
				JButton deleteButton = new JButton("Delete");
				buttonPane.add(deleteButton);
			}
			{
				JButton newButton = new JButton("New");
				buttonPane.add(newButton);
			}
			{
				JButton openButton = new JButton("Open");
				openButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// TODO call Application.show()
						levelList.loadLevel(levelListView.getSelectedIndex());
					}
				});
				openButton.setActionCommand("OPEN");
				buttonPane.add(openButton);
				getRootPane().setDefaultButton(openButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void populateLevelListView() {
		levelListView.setListData(levelList.getArray());
	}

}
