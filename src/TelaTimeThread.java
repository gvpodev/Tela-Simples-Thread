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
	private JLabel hourDescription = new JLabel("Time Thread 1");
	private JTextField showTime = new JTextField();

	// Campo 2
	private JLabel hourDescription2 = new JLabel("Time Thread 2");
	private JTextField showTime2 = new JTextField();

	// Botões
	private JButton jButtonStart = new JButton("Start");
	private JButton jButtonStop = new JButton("Stop");

	private Runnable thread1 = new Runnable() {

		@Override
		public void run() {
			while (true) {
				showTime.setText(new SimpleDateFormat("dd/MM/yyyy hh:mm.ss").format(Calendar.getInstance().getTime()));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};

	private Runnable thread2 = new Runnable() {
		@Override
		public void run() {
			while (true) {
				showTime2.setText(new SimpleDateFormat("dd/MM/yyyy hh:mm.ss").format(Calendar.getInstance().getTime()));
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
	
	//Objetos Thread
	private Thread thread1time;
	private Thread thread2time;

	public TelaTimeThread() {
		// Parte do cógido respondável pela aparencia e tamanho da tela.
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

		hourDescription.setPreferredSize(new Dimension(200, 25));
		jPanel1.add(hourDescription, gridBagConstraints); // Adiciona o campo ao JPanel

		showTime.setPreferredSize(new Dimension(200, 25));
		gridBagConstraints.gridy++; // Desce o componente no eixo y
		showTime.setEditable(false); // Campo de texto sem edição, apenas visualização
		jPanel1.add(showTime, gridBagConstraints);

		hourDescription2.setPreferredSize(new Dimension(200, 25));
		gridBagConstraints.gridy++;
		jPanel1.add(hourDescription2, gridBagConstraints);

		showTime2.setPreferredSize(new Dimension(200, 25));
		gridBagConstraints.gridy++;
		showTime2.setEditable(false);
		jPanel1.add(showTime2, gridBagConstraints);

		gridBagConstraints.gridwidth = 1; // Cada componente passa a ocupar 1 espaço na horizontal

		jButtonStart.setPreferredSize(new Dimension(92, 25));
		gridBagConstraints.gridy++;
		jPanel1.add(jButtonStart, gridBagConstraints);

		jButtonStop.setPreferredSize(new Dimension(92, 25));
		gridBagConstraints.gridx++;
		jPanel1.add(jButtonStop, gridBagConstraints);
		
		//Ações dos botões Start e Stop de ambas as Threads.
		
		//Thread 1
		jButtonStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { // Executa o click no Botão

				thread1time = new Thread(thread1);
				thread1time.start();
				
				jButtonStart.setEnabled(false);
				jButtonStop.setEnabled(true);

			}
		});

		jButtonStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thread1time.stop();
				
				jButtonStart.setEnabled(true);
				jButtonStop.setEnabled(false);
			}
		});
		
		//Thread 2
		jButtonStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thread2time = new Thread(thread2);
				thread2time.start();
				
				jButtonStart.setEnabled(false);
				jButtonStop.setEnabled(true);

			}
		});

		jButtonStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thread2time.stop();
				
				jButtonStart.setEnabled(true);
				jButtonStop.setEnabled(false);
			}
		});
		
		jButtonStop.setEnabled(false);
		
		add(jPanel1, BorderLayout.WEST); // Posiciona os campos no lado esquerdo do painel
		// Torna a tela visível. Sempre será o último a ser executado
		setVisible(true);

	}

}
