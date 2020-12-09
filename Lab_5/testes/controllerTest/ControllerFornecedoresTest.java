package controllerTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.ControllerFornecedores;
import saga.ProdutoID;

class ControllerFornecedoresTest {

	private ControllerFornecedores controllerFornecedores;
	
	@BeforeEach
	void criaControllerFornecedores() {
		this.controllerFornecedores = new ControllerFornecedores();
	}
	
	@Test
	void testAdicionaFornecedor() {
		assertEquals("Guilherme", this.controllerFornecedores.adicionaFornecedor("Guilherme", "guigol@gmail.com", "1212"));
		assertEquals("Cristiano Ronaldo", this.controllerFornecedores.adicionaFornecedor("Cristiano Ronaldo", "cr7@gmail.com", "777"));
		
		assertThrows( IllegalArgumentException.class, ()-> 
		this.controllerFornecedores.adicionaFornecedor("Guilherme", "guigol@gmail.com", "1212")); // fornecedor j� existe
		assertThrows( IllegalArgumentException.class, ()->
		this.controllerFornecedores.adicionaFornecedor("", "guigol@gmail.com", "1212"));
		assertThrows( IllegalArgumentException.class, ()-> 
		this.controllerFornecedores.adicionaFornecedor("Guilherme", "        ", "1212"));	
		assertThrows( IllegalArgumentException.class, ()-> 
		this.controllerFornecedores.adicionaFornecedor("Guilherme", "guigol@gmail.com", ""));
		
		assertThrows( NullPointerException.class, ()->
		this.controllerFornecedores.adicionaFornecedor(null, "guigol@gmail.com", "1212"));
		assertThrows( NullPointerException.class, ()->
		this.controllerFornecedores.adicionaFornecedor("Guilherme", null, "1212"));
		assertThrows( NullPointerException.class, ()->
		this.controllerFornecedores.adicionaFornecedor("Guilherme", "guigol@gmail.com", null));
	}

	@Test
	void testExibeFornecedor() {
		this.controllerFornecedores.adicionaFornecedor("Cristiano Ronaldo", "cr7@gmail.com", "777");
		
		assertEquals("Cristiano Ronaldo - cr7@gmail.com - 777", this.controllerFornecedores.exibeFornecedor("Cristiano Ronaldo"));
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.exibeFornecedor("Fulano")); // fornecedor n�o existe
		
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.exibeFornecedor(""));
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.exibeFornecedor("               "));
		assertThrows( NullPointerException.class, ()-> this.controllerFornecedores.exibeFornecedor(null));
		
	}
	
	@Test
	void editaFornecedor() {
		this.controllerFornecedores.adicionaFornecedor("Cristiano Ronaldo", "cr7@gmail.com", "777");
		
		this.controllerFornecedores.editaFornecedor("Cristiano Ronaldo", "email", "cristiano@gmail.com");
		assertEquals("cristiano@gmail.com", this.controllerFornecedores.getMapaFornecedores().get("Cristiano Ronaldo").getEmail());
		
		this.controllerFornecedores.editaFornecedor("Cristiano Ronaldo", "telefone", "1010");
		assertEquals("1010", this.controllerFornecedores.getMapaFornecedores().get("Cristiano Ronaldo").getTelefone());
		
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.editaFornecedor("Cristiano Ronaldo", "nome", "Cr7")); // nome n�o pode alterar
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.editaFornecedor("Cristiano Ronaldo", "instagram", "Cr7")); // atributo n�o existe
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.editaFornecedor("Z� pipoca", "email", "z�@gmail.com")); // fornecedor n�o existe
		
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.editaFornecedor("", "email", "z�@gmail.com"));
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.editaFornecedor("Z� pipoca", "", "z�@gmail.com"));
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.editaFornecedor("Z� pipoca", "email", ""));
		
		assertThrows( NullPointerException.class, ()-> this.controllerFornecedores.editaFornecedor(null, "email","z�@gmail.com"));
		assertThrows( NullPointerException.class, ()-> this.controllerFornecedores.editaFornecedor("Z�", "email", null));
		assertThrows( NullPointerException.class, ()-> this.controllerFornecedores.editaFornecedor("Z�", null,"z�@gmail.com"));	
	}
	
	@Test
	void removeFornecedor() {
		this.controllerFornecedores.adicionaFornecedor("Cristiano", "cr7@gmail.com", "777");
		assertTrue(true == this.controllerFornecedores.getMapaFornecedores().containsKey("Cristiano"));
		
		this.controllerFornecedores.removeFornecedor("Cristiano");
		assertFalse(true == this.controllerFornecedores.getMapaFornecedores().containsKey("Cristiano"));
		
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.removeFornecedor("Fulano de Sal"));
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.removeFornecedor(""));
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.removeFornecedor("             "));
		assertThrows( NullPointerException.class, ()-> this.controllerFornecedores.removeFornecedor(null));
		
	}
	
	@Test
	void adicionaProduto() {
		this.controllerFornecedores.adicionaFornecedor("Cristiano", "cr7@gmail.com", "777");
		this.controllerFornecedores.adicionaProduto("Cristiano", "P�o", "Bola", 0.75);
		
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.adicionaProduto("Jus�", "Marmita", "Completa", 12.75)); // fornecedor n�o existe
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.adicionaProduto("", "Marmita", "Completa", 12.75));
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.adicionaProduto("Cristiano", "", "Completa", 12.75));
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.adicionaProduto("Cristiano", "Marmita", "", 12.75));
		assertThrows( NullPointerException.class, ()-> this.controllerFornecedores.adicionaProduto(null, "Marmita", "M�dia", 12.75));
		assertThrows( NullPointerException.class, ()-> this.controllerFornecedores.adicionaProduto("Cristiano", null, "Pequena", 12.75));
		assertThrows( NullPointerException.class, ()-> this.controllerFornecedores.adicionaProduto("Cristiano", "Marmita", null, 12.75));
	}
	
	@Test
	void exibeProduto() {
		this.controllerFornecedores.adicionaFornecedor("Cristiano", "cr7@gmail.com", "777");
		this.controllerFornecedores.adicionaProduto("Cristiano", "P�o", "Bola", 0.75);
		
		assertEquals("P�o - Bola - R$0,75", this.controllerFornecedores.exibeProduto("P�o", "Bola", "Cristiano"));
		
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.exibeProduto("P�o", "Bola", "Jus�")); // fornecedor n�o existe
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.exibeProduto("", "Bola", "Cristiano"));
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.exibeProduto("P�o", "", "Cristiano"));
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.exibeProduto("P�o", "Bola", ""));
		assertThrows( NullPointerException.class, ()-> this.controllerFornecedores.exibeProduto("P�o", "Bola", null));
		assertThrows( NullPointerException.class, ()-> this.controllerFornecedores.exibeProduto("P�o", null, "Cristiano"));
		assertThrows( NullPointerException.class, ()-> this.controllerFornecedores.exibeProduto(null, "Bola", "Cristiano"));
	}
	
	@Test
	void editaProduto() {
		this.controllerFornecedores.adicionaFornecedor("Cristiano", "cr7@gmail.com", "777");
		this.controllerFornecedores.adicionaProduto("Cristiano", "P�o", "Bola", 0.75);
		assertEquals("P�o - Bola - R$0,75", this.controllerFornecedores.exibeProduto("P�o", "Bola", "Cristiano"));
		
		this.controllerFornecedores.editaProduto("P�o", "Bola", "Cristiano", 0.3);
		assertEquals("P�o - Bola - R$0,30", this.controllerFornecedores.exibeProduto("P�o", "Bola", "Cristiano"));
		
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.editaProduto("P�o", "Bola", "Cristiano", -5.5)); // pre�o inv�lido
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.editaProduto("P�o", "Bola", "Guilhergol", 10.5)); // fornecedor n�o existe
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.editaProduto("", "Bola", "Cristiano", 0.3));
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.editaProduto("P�o", "", "Cristiano", 0.3));
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.editaProduto("P�o", "Bola", "", 0.3));
		assertThrows( NullPointerException.class, ()-> this.controllerFornecedores.editaProduto(null, "Bola", "Cristiano", 0.3));
		assertThrows( NullPointerException.class, ()-> this.controllerFornecedores.editaProduto("P�o", null, "Cristiano", 0.3));
		assertThrows( NullPointerException.class, ()-> this.controllerFornecedores.editaProduto("P�o", "Bola", null, 0.3));
	}
	
	@Test
	void removeProduto() {
		this.controllerFornecedores.adicionaFornecedor("Cristiano", "cr7@gmail.com", "777");
		this.controllerFornecedores.adicionaProduto("Cristiano", "P�o", "Bola", 0.75);
		assertEquals("P�o - Bola - R$0,75", this.controllerFornecedores.exibeProduto("P�o", "Bola", "Cristiano"));
		
		this.controllerFornecedores.removeProduto("P�o", "Bola", "Cristiano");
		assertFalse (true == this.controllerFornecedores.getMapaFornecedores().get("Cristiano")
				.getMapaProdutos().containsKey(new ProdutoID("P�o", "Bola")));
		
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.removeProduto("P�o", "Bola", "Josu�")); // fornecedor n�o existe
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.removeProduto("", "Bola", "Cristiano"));
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.removeProduto("P�o", "", "Cristiano"));
		assertThrows( IllegalArgumentException.class, ()-> this.controllerFornecedores.removeProduto("P�o", "Bola", ""));
		assertThrows( NullPointerException.class, ()-> this.controllerFornecedores.removeProduto(null, "Bola", "Cristiano"));
		assertThrows( NullPointerException.class, ()-> this.controllerFornecedores.removeProduto("P�o", null, "Cristiano"));
		assertThrows( NullPointerException.class, ()-> this.controllerFornecedores.removeProduto("P�o", "Bola", null));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
