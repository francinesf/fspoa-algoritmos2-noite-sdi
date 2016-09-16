package game;

import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Sprite;

public class Paddle extends Sprite {


	public Paddle() {
		super(30, 5, Color.RED);
	}
	
	public boolean colidiu (Bola bola){
		
		boolean bateu = true;
		Point posicaoPaddle = this.getPosition();
		Point posicao = bola.getPosition();
		
		// Se qualquer teste for verdadeiro, a bola nao bateu no quadrado.
		if (posicao .x > posicaoPaddle.x + this.getWidth()) 
		{
			bateu = false; 
		}
		if (posicao.x  + bola.getWidth() < posicaoPaddle.x) 
		{
			bateu = false;
		}
		if (posicao.y > posicaoPaddle.y + this.getHeight()) 
		{
			bateu = false;
		}
		if (posicao.y  + bola.getHeight() < posicaoPaddle.y) 
		{
			bateu = false;
		}			
		
		return bateu;
		
		
	}
	
}
