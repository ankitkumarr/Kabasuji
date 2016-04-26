package com.halaesus.kabasuji.builder.boundary;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.halaesus.kabasuji.player.entity.LevelList;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

@SuppressWarnings("serial")
public class NewLevelDialog extends JDialog {
	LevelList levelList; // passed in from the LevelManagerDialog
	String selectedType;

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNamefield;

	/**
	 * Launch the application.
	 */
	public static void main(LevelList ll) {
		try {
			NewLevelDialog dialog = new NewLevelDialog(ll);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewLevelDialog(LevelList ll) {
		levelList = ll;
		selectedType = "Puzzle";
		
		setTitle("New Level");
		setBounds(100, 100, 300, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblLevelName = new JLabel("Level Name");
			GridBagConstraints gbc_lblLevelName = new GridBagConstraints();
			gbc_lblLevelName.insets = new Insets(0, 0, 5, 5);
			gbc_lblLevelName.gridx = 1;
			gbc_lblLevelName.gridy = 1;
			contentPanel.add(lblLevelName, gbc_lblLevelName);
		}
		{
			txtNamefield = new JTextField();
			GridBagConstraints gbc_txtNamefield = new GridBagConstraints();
			gbc_txtNamefield.insets = new Insets(0, 0, 5, 5);
			gbc_txtNamefield.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtNamefield.gridx = 3;
			gbc_txtNamefield.gridy = 1;
			contentPanel.add(txtNamefield, gbc_txtNamefield);
			txtNamefield.setColumns(10);
		}
		{
			JLabel lblLevelType = new JLabel("Level Type");
			GridBagConstraints gbc_lblLevelType = new GridBagConstraints();
			gbc_lblLevelType.insets = new Insets(0, 0, 0, 5);
			gbc_lblLevelType.gridx = 1;
			gbc_lblLevelType.gridy = 2;
			contentPanel.add(lblLevelType, gbc_lblLevelType);
		}
		{
			JComboBox<String> comboBox = new JComboBox<String>();
			comboBox.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent arg0) {
					selectedType = (String) comboBox.getSelectedItem();
				}
			});
			comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Puzzle", "Lightning", "Release"}));
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 0, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 3;
			gbc_comboBox.gridy = 2;
			contentPanel.add(comboBox, gbc_comboBox);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String name = txtNamefield.getText();
						if (name == null || name.length() == 0) return; // user must enter a name
						
						int idx = levelList.newLevel(name, selectedType);
						Application.instance().show(levelList.loadLevel(idx), selectedType);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
