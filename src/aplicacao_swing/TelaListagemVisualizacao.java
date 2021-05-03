package aplicacao_swing;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import fachada.Fachada;
import modelo.Visualizacao;
import java.awt.Toolkit;

public class TelaListagemVisualizacao {

	private JFrame frmListagemVisu;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton button;
	private JButton button_1;
	private JButton btnAbrirLink;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// TelaListar window = new TelaListar();
	// window.frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the application.
	 */
	public TelaListagemVisualizacao() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListagemVisu = new JFrame();
		frmListagemVisu.setIconImage(
				Toolkit.getDefaultToolkit().getImage(TelaListagemVisualizacao.class.getResource("/imagem/icon.png")));
		frmListagemVisu.setResizable(false);
		frmListagemVisu.setTitle("Listagem de visualizacao");
		frmListagemVisu.setBounds(100, 100, 592, 480);
		frmListagemVisu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmListagemVisu.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 29, 543, 311);
		frmListagemVisu.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.YELLOW);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "", "", "" }));
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setRowHeight(30);
		

		button = new JButton("Listar Visualizacoes");
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel model = new DefaultTableModel();
					model.addColumn("id");
					model.addColumn("video");
					model.addColumn("usuario");
					model.addColumn("nota");

					List<Visualizacao> lista = Fachada.listarVisualizacoes();
					for (Visualizacao v : lista) {
						model.addRow(new Object[] { v.getId(), v.getVideo().getLink(), v.getUsuario().getEmail(), v.getNota() });
						table.setModel(model);
					}
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(frmListagemVisu, erro.getMessage());
				}
			}
		});
		button.setBounds(21, 351, 150, 45);
		frmListagemVisu.getContentPane().add(button);

		button_1 = new JButton("Apagar visualização selecionada");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					int id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
					try {
						Fachada.apagarVisualizacao(id);
						button.doClick();
					} catch (Exception erro) {
					}
				} else {
					JOptionPane.showMessageDialog(null, "selecionar uma linha");
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setHorizontalAlignment(SwingConstants.LEFT);
		button_1.setBounds(364, 351, 200, 45);
		frmListagemVisu.getContentPane().add(button_1);
		
		btnAbrirLink = new JButton("Abrir Link");
		btnAbrirLink.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnAbrirLink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					String url = (String) table.getValueAt(table.getSelectedRow(), 1);
					try {
						Desktop.getDesktop().browse(new URL(url).toURI());
					} catch (Exception erro) {
					}
				} else {
					JOptionPane.showMessageDialog(null, "selecionar uma linha");
				}}
		});
		
		btnAbrirLink.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAbrirLink.setBounds(193, 351, 150, 45);
		frmListagemVisu.getContentPane().add(btnAbrirLink);

		frmListagemVisu.setVisible(true);
	}

	public JFrame getFrmListagemVisu() {
		return frmListagemVisu;
	}

	public void setFrmListagemVisu(JFrame frmListagemVisu) {
		this.frmListagemVisu = frmListagemVisu;
	}
}