import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaTimeThread extends JDialog {

	private JPanel jPanel1 = new JPanel(new GridBagLayout()); // Painel de Componentes
	
	// Campo 1
	private JLabel nameField = new JLabel("Nome");
	private JTextField showName = new JTextField();

	// Campo 2
	private JLabel emailField = new JLabel("E-mail");
	private JTextField showEmail = new JTextField();

	// Botões
	private JButton jButtonAdd = new JButton("Add Lista");
	private JButton jButtonStop = new JButton("Stop");
	
	private ImplementacaoFilaThread fila = new ImplementacaoFilaThread();
	
	public TelaTimeThread() {
		// Parte do cógido responsável pela aparencia e tamanho da tela.
		setTitle("Minha tela de time com Thread.");
		setSize(new Dimension(240, 240));
		setLocationRelativeTo(null);
		setResizable(false);

		// Campo da tela
		GridBagConstraints gridBagConstraints = new GridBagConstraints(); // Controlador de posicionamento
		gridBagConstraints.gridx = 0; // Tela no eixo X (Linha)
		gridBagConstraints.gridy = 0; // Tela no eixo y (coluna)
		gridBagConstraints.gridwidth = 2; // Quantidade de espaço que cada componetente ocupado na horizontal (n espaços
											// = espaço de n componentes)
		gridBagConstraints.insets = new Insets(5, 10, 5, 5); // Distancia entre os componentes dentro do painel
		gridBagConstraints.anchor = GridBagConstraints.WEST; // Disposição dos componentes dentro da tela

		nameField.setPreferredSize(new Dimension(200, 25));
		jPanel1.add(nameField, gridBagConstraints); // Adiciona o campo ao JPanel

		showName.setPreferredSize(new Dimension(200, 25));
		gridBagConstraints.gridy++; // Desce o componente no eixo y
		jPanel1.add(showName, gridBagConstraints);

		emailField.setPreferredSize(new Dimension(200, 25));
		gridBagConstraints.gridy++;
		jPanel1.add(emailField, gridBagConstraints);

		showEmail.setPreferredSize(new Dimension(200, 25));
		gridBagConstraints.gridy++;
		jPanel1.add(showEmail, gridBagConstraints);

		gridBagConstraints.gridwidth = 1; // Cada componente passa a ocupar 1 espaço na horizontal

		jButtonAdd.setPreferredSize(new Dimension(92, 25));
		gridBagConstraints.gridy++;
		jPanel1.add(jButtonAdd, gridBagConstraints);

		jButtonStop.setPreferredSize(new Dimension(92, 25));
		gridBagConstraints.gridx++;
		jPanel1.add(jButtonStop, gridBagConstraints);
		
		jButtonAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { // Executa o click no Botão
				
				if(fila == null) {
					fila = new ImplementacaoFilaThread();
					fila.start();
				}
				
				//Simulacao de 100 envios em massa
				for(int i = 0; i < 100; i++) {
					ObjetoFilaThread filaThread = new ObjetoFilaThread();
					filaThread.setName(showName.getText());
					filaThread.setEmail(showEmail.getText() + " - " + i);
					
					fila.add(filaThread);
				}
			}
		});
		
		jButtonStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fila.stop();
				fila = null;
			}
		});
		
		fila.start();
		add(jPanel1, BorderLayout.WEST); // Posiciona os campos no lado esquerdo do painel
		// Torna a tela visível. Sempre será o último a ser executado 
		setVisible(true);

	}

}
