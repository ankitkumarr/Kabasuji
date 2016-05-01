package com.halaesus.kabasuji.builder.boundary;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.halaesus.kabasuji.player.entity.LevelData;
import com.halaesus.kabasuji.player.entity.LevelList;

/**
 * A dialog that allows for creating, editing and deleting levels in addition to
 * changing level indexes.
 * 
 * @author Corey Dixon
 * 
 *
 */
@SuppressWarnings("serial")
public class LevelManagerDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	JList<LevelData> levelListView;
	LevelList levelList;

	/**
	 * <p>
	 * Creates a LevelManagerDialog.
	 * </p>
	 * 
	 * @param args
	 *            unused
	 */
	public static void main(String[] args) {
		try {
			LevelManagerDialog dialog = new LevelManagerDialog();
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * Constructs a LevelManagerDialog.
	 * </p>
	 */
	public LevelManagerDialog() {
		Font font = new Font("Arial", Font.PLAIN, 18);
		levelList = LevelList.loadList();

		setBounds(100, 100, 400, 430);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		{
			levelListView = new JList<LevelData>();
			levelListView.setFont(font);
			levelListView.setFixedCellWidth(this.getWidth());
			populateLevelListView();
			levelListView.setSelectedIndex(0);
			contentPanel.add(levelListView);
		}
		// force main application to close on exit
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		});

		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				GridBagLayout gbl_buttonPane = new GridBagLayout();
				gbl_buttonPane.columnWidths = new int[] { 60, 60, 60, 60, 60, 0 };
				gbl_buttonPane.rowHeights = new int[] { 23, 0 };
				gbl_buttonPane.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
				gbl_buttonPane.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
				buttonPane.setLayout(gbl_buttonPane);
				{
					JButton deleteButton = new JButton("Delete");
					deleteButton.setFont(font);
					deleteButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (levelListView.getSelectedIndex() >= 0) {
								levelList.deleteLevel(levelListView.getSelectedIndex());
								populateLevelListView();
							}
						}
					});
					{
						JButton editButton = new JButton("Edit");
						editButton.setFont(font);
						editButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (levelListView.getSelectedIndex() >= 0) {
									Application.instance().showLevel(levelList.loadLevel(levelListView.getSelectedIndex()),
											levelList.getLevelType(levelListView.getSelectedIndex()));
									dispose();
								}
							}
						});
						JButton upButton = new JButton("Up");
						upButton.setFont(font);
						upButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								int selectedIndex = levelListView.getSelectedIndex();
								if (selectedIndex > 0) {
									levelList.swapIndexes(selectedIndex, selectedIndex - 1);
									populateLevelListView();
									levelListView.setSelectedIndex(selectedIndex - 1);
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
							newButton.setFont(font);
							newButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									dispose();
									NewLevelDialog.main(LevelManagerDialog.this);
									// can't just say "this" b/c we're inside
									// the ActionListener
								}
							});
							{
								JButton downButton = new JButton("Down");
								downButton.setFont(font);
								downButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										int selectedIndex = levelListView.getSelectedIndex();
										if (selectedIndex < levelListView.getModel().getSize()) {
											levelList.swapIndexes(selectedIndex, selectedIndex + 1);
											populateLevelListView();
											levelListView.setSelectedIndex(selectedIndex + 1);
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

	/**
	 * <p>
	 * Reloads the contents of the LevelListView.
	 * </p>
	 * gets the contents from the LevelList class.
	 * 
	 * @see LevelList
	 */
	private void populateLevelListView() {
		levelListView.setListData(levelList.getArray());
	}
}
