package saga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Classe que representa a compra de um cliente com determinado fornecedor.
 * Toda compra tem data, nome do produto comprado, descri��o do produto e o pre�o do produto.
 * @author Guilherme Aureliano - 119210371
 *
 */
public class Compra {
	
	private String data;
	private String nomeProduto;
	private String descricao;
	private double preco;
	
	/**
	 * Cria uma compra a partir de seus respectivos par�metros.
	 * @param data Data de compra.
	 * @param nomeProduto Nome do produto.
	 * @param descricao Descri��o do produto.
	 * @param preco Pre�o do produto.
	 */
	public Compra(String data, String nomeProduto, String descricao, double preco) {
		this.data = data;
		this.nomeProduto = nomeProduto;
		this.descricao = descricao;
		this.preco = preco;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String toString() {
		return this.nomeProduto + " - " + this.data;
	}

	
}
