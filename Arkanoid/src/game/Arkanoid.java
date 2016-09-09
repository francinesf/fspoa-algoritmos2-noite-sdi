package game;


import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.GraphicApplication;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Resolution;
import com.senac.SimpleJava.Graphics.Sprite;
import com.senac.SimpleJava.Graphics.events.KeyboardAction;

public class Arkanoid extends GraphicApplication {

	private Bola bola;
	private Quadrado quadrado;
	private Sprite paddel;
	private Quadrado quadrado2; 
	
	@Override
	protected void draw(Canvas canvas) {		
		canvas.clear();
		
		bola.draw(canvas);
		
		quadrado.draw(canvas);
		quadrado2.draw(canvas);
		
		paddel.draw(canvas);
	}

	@Override
	protected void setup() {
		this.setTitle("Arkanoid");
		this.setResolution(Resolution.MSX);
		this.setFramesPerSecond(60);
		
		bola = new Bola();
		bola.setPosition(150, 150);
		
		quadrado = new Quadrado(Color.BLUE);
		quadrado.setPosition(50, 50);
		quadrado2 = new Quadrado(Color.GREEN);
		quadrado2.setPosition(70, 50);
		
		paddel = new Sprite(20, 5, Color.RED);
		paddel.setPosition(Resolution.MSX.width/2 - 10, Resolution.MSX.height - 25);
		
		this.bindKeyPressed("LEFT", new KeyboardAction() {
			@Override
			public void handleEvent() {
				paddel.move(-3, 0);
			}
		});
		
		this.bindKeyPressed("RIGHT", new KeyboardAction() {
			@Override
			public void handleEvent() {
				paddel.move(+3, 0);
			}
		});
	}

	@Override
	protected void loop() {
		bola.move();
	
		Point posicao = bola.getPosition();
	
		if (paddle.colidiu(bola))
				bola.direcaoY();
		
		if(posicao.y <= 0 || posicao.y >= Resolution.MSX.height-5){
			bola.direcaoY();
		}
		if(posicao.x <= 0 || posicao.x >= Resolution.MSX.width-5){
			bola.direcaoX();
		}
		
		quadrado.colidiu(bola);
		quadrado2.colidiu(bola);

		redraw();
	}
}
