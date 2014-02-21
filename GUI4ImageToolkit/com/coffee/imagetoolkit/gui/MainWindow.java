package com.coffee.imagetoolkit.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	
	public MainWindow() {
		JPanel pnInput = new JPanel(new GridBagLayout());
		this.getContentPane().add(pnInput);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.gridwidth = 3;
		pnInput.add(new JLabel("This is the title", JLabel.CENTER), gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		pnInput.add(new JSeparator(), gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0.0;
		gbc.gridwidth = 1;
		pnInput.add(new JLabel("Name:"), gbc);
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.gridwidth = 2;
		pnInput.add(new JTextField("default value"), gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 0.0;
		gbc.gridwidth = 1;
		pnInput.add(new JLabel("Grade:"), gbc);
		ButtonGroup group = new ButtonGroup();
		JRadioButton rb1 = new JRadioButton("L1");
		group.add(rb1);
		JRadioButton rb2 = new JRadioButton("L2");
		group.add(rb2);
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		pnInput.add(rb1, gbc);
		gbc.gridx = 2;
		pnInput.add(rb2, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.weightx = 0.0;
		gbc.gridwidth = 1;
		pnInput.add(new JLabel("Performance Criteria:"), gbc);
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.gridwidth = 2;
		pnInput.add(new JComboBox<Object>(new Object[] { "Select 1", "Select 2", "Select 3" }), gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.weightx = 1.0;
		gbc.gridwidth = 3;
		pnInput.add(new JSeparator(), gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		pnInput.add(new JScrollPane(new JList<Object>(new Object[] { "List 1", "List 2", "List 3" })), gbc);

		this.setSize(300, 200); // 窗口大小为640*500
		this.setVisible(true); // 窗口可见
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		System.out.println(System.getProperty("os.name"));
		MainWindow m = new MainWindow();
	}
}
