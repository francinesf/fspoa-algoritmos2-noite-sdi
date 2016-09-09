package game;

import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Sprite;

public class Quadrado extends Sprite {

	private boolean estouVivo;

	public Quadrado(Color background) {
		super(18, 10, background);
		estouVivo = true;
	}
	
	public boolean colidiu (Bola bola){
		
		if (!estouVivo) return false;
		
		boolean bateu = true;
		Point posicaoQuadrado = this.getPosition();
		Point posicao = bola.getPosition();
		
		// Se qualquer teste for verdadeiro, a bola nao bateu no quadrado.
		if (posicao .x > posicaoQuadrado.x + this.getWidth()) 
		{
			bateu = false; 
		}
		if (posicao.x  + bola.getWidth() < posicaoQuadrado.x) 
		{
			bateu = false;
		}
		if (posicao.y > posicaoQuadrado.y + this.getHeight()) 
		{
			bateu = false;
		}
		if (posicao.y  + bola.getHeight() < posicaoQuadrado.y) 
		{
			bateu = false;
		}
		
		if (bateu)
			estouVivo = false;
		
		return bateu;
	}
	
	@Override
	public void draw(Canvas canvas) {
		if (estouVivo)
			super.draw(canvas);
	}
}
