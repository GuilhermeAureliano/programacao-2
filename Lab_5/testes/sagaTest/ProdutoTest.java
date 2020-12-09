package sagaTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import saga.ProdutoFinal;

class ProdutoTest {

	private ProdutoFinal produto1;
	private ProdutoFinal produto2;
	private ProdutoFinal produto3;
	
	@BeforeEach
	void criaProduto() {
		this.produto1 = new ProdutoFinal("Sab�o", "em p�", 2.99);
		this.produto2 = new ProdutoFinal("Sab�o", "em p�", 5.99);
		this.produto3 = new ProdutoFinal("Toddy", "Puro", 7.75);
	}
	
	@Test
	void testToString() {
		assertEquals("Sab�o - em p� - R$2,99", this.produto1.toString());
		assertNotEquals("Sab�o - l�quido - R$5,99", this.produto2.toString());
		assertEquals("Toddy - Puro - R$7,75", this.produto3.toString());
	}
	
	@Test
	void testHashCode() {
		assertEquals(this.produto1.hashCode(), this.produto2.hashCode());
		assertNotEquals(this.produto2.hashCode(), this.produto3.hashCode());
	}
	
	@Test
	void testEquals() {
		assertTrue(true == this.produto1.equals(produto2));
		assertFalse(true == this.produto1.equals(produto3));
		assertFalse(true == this.produto1.equals(null));
	}

}
