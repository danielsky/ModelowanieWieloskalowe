package com.skimina.daniel.modelowanie;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

public class AskDialog extends JDialog {
	
	
	private HashSet<String> result;
	
	public static Set<String> przygotujPrzestrzen(JFrame parent, PreparedPrzestrzenAutomatow przestrzen, List<PreparedMyCell> initialCells){
		AskDialog dialog = new AskDialog(parent);
		dialog.initialCells = initialCells;
		dialog.przestrzen = przestrzen;
		dialog.prepareDialog();
		dialog.setVisible(true);
		return dialog.result;
	}
	
	
	private JPanel contentPane;
	private JList<PreparedMyCell> list;
	private JLabel lblPreview;
	private List<PreparedMyCell> initialCells;
	private PreparedPrzestrzenAutomatow przestrzen;
	
	private Set<String> selectedIds = new HashSet<String>();
	
	public AskDialog(JFrame parent) {
		super(parent);
		setTitle("Wybierz ziarna");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(786, 564);
		setLocationRelativeTo(parent);
		setModal(true);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.WEST);
		
		
		
		list = new JList<PreparedMyCell>();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PreparedMyCell cell = list.getSelectedValue();
				if(cell!= null){
					list.clearSelection();
					cell.toogleSelection();
					refreshPreview();
				}
			}
		});
		list.setCellRenderer(new PreparedMyCellRenderer());
		scrollPane.setViewportView(list);
		
		lblPreview = new JLabel("");
		lblPreview.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblPreview, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnAnuluj = new JButton("Anuluj");
		btnAnuluj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result = null;
				AskDialog.this.setVisible(false);
			}
		});
		panel.add(btnAnuluj);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result = new HashSet<String>();
				for(PreparedMyCell cell : initialCells){
					if(cell.isSelected()) result.add(cell.getId());
				}
				AskDialog.this.setVisible(false);
			}
		});
		panel.add(btnOk);
		
		
		
		
	}
	
	
	private void prepareDialog(){
		DefaultListModel<PreparedMyCell> model = new DefaultListModel<PreparedMyCell>();
		for(PreparedMyCell cell : initialCells){
			model.addElement(cell);
		}
		
		list.setModel(model);
		
		lblPreview.setIcon(new ImageIcon(przestrzen.getImage()));
		
		
		refreshPreview();
	}
	
	
	private void refreshPreview(){
		selectedIds.clear();
		for(PreparedMyCell cell : initialCells){
			if(cell.isSelected()) selectedIds.add(cell.getId());
		}
		przestrzen.wizualizuj(selectedIds);
		SwingUtilities.invokeLater(refresh);
	}
	
	
	private Runnable refresh = new Runnable() {
		
		public void run() {
			//panelWork.repaint();
			lblPreview.repaint();
		}
	};
	
	
	
	
	
	private class PreparedMyCellRenderer implements ListCellRenderer<PreparedMyCell>{
		
		private JCheckBox box;
		
		public PreparedMyCellRenderer() {
			box = new JCheckBox();
			box.setBorder(new EmptyBorder(2, 5, 2, 5));
			box.setText("             ");
		}
		
		public Component getListCellRendererComponent(
				JList<? extends PreparedMyCell> list, PreparedMyCell value,
				int index, boolean isSelected, boolean cellHasFocus) {
			
			//box.setText("Ziarno nr: "+(index+1));
			//box.setForeground(value.getColor());
			box.setBackground(value.getColor());
			box.setSelected(value.isSelected());
			return box;
		}
		/*
		private Color getNegative(Color c){
			int r = Math.abs(c.getRed()-255);
			int g = Math.abs(c.getGreen()-255);
			int b = Math.abs(c.getBlue()-255);
			return new Color(r, g, b);
		}*/
	}

}
