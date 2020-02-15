import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.text.html.HTMLDocument.Iterator;

public class ImplementacaoFilaThread extends Thread {
	
	private static ConcurrentLinkedQueue<ObjetoFilaThread> pilha1 = new ConcurrentLinkedQueue<ObjetoFilaThread>();
	
	public static void add(ObjetoFilaThread filaThread) {
		pilha1.add(filaThread);
	}
	
	@Override
	public void run() {
		
		System.out.println("Fila rodando.");
		
		while(true) {
			synchronized (pilha1) { //Bloquea o acesso à lista por outros processos
				
				java.util.Iterator iteracao = pilha1.iterator();
				while(iteracao.hasNext()) {
					
					ObjetoFilaThread processar = (ObjetoFilaThread) iteracao.next();
					
					//Processos executados em 2o plano
					
					System.out.println("--------------------------");
					System.out.println(processar.getEmail());
					System.out.println(processar.getName());
					
					iteracao.remove(); //Remove o objeto processado
					
					try {
						Thread.sleep(1000); //Tempo para descarga e manutenção da memória
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			try {
				Thread.sleep(1000); //Tempo para limpeza da memória após o fim do processo
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
