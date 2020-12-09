package excecoes;

/**
 * Classe que lan�a exce��es em determinados casos.
 * @author Guilherme Aureliano - 119210371
 *
 */

public class GuiUtil {
	
	/**
	 * Verifica se a entrada � vazia, lan�ando uma mensagem caso seja.
	 * @param valor A entrada a ser verificada
	 * @param mensagem A mensagem a ser lan�ada
	 */
	public static void verificaStringVazia(String valor, String mensagem) {
		if (valor.trim().isEmpty()) {
			throw new IllegalArgumentException(mensagem);
		}
	}
	
	/**
	 * Verifica se a entrada � nula, lan�ando uma mensagem caso seja.
	 * @param valor A entrada a ser verificada
	 * @param mensagem A mensagem a ser lan�ada
	 */
	public static void verificaStringNull(String valor, String mensagem) {
		if(valor == null) {
			throw new NullPointerException(mensagem);
		}
	}
}
