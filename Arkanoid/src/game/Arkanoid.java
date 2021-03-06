package game;

import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.GraphicApplication;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Resolution;
import com.senac.SimpleJava.Graphics.events.KeyboardAction;

public class Arkanoid extends GraphicApplication {

	private Bola bola;
	private Quadrado[] quadrado = new Quadrado [50];
	private Paddle paddle;
	private int posiQuadX = 10; 
	private int posiQuadY = 20;
	private boolean mensagem = false;
	private int tempo;
	private boolean jogando = false; 
	private Color[] cores = { Color.GRAY, Color.GREEN, Color.MAGENTA, Color.BLUE, Color.CYAN};
	
	@Override
	protected void draw(Canvas canvas) {		
		canvas.clear();
		
		bola.draw(canvas);
		
		for(Quadrado q : quadrado){
			q.draw(canvas);
		}
		
		paddle.draw(canvas);
		
		Point posicao = bola.getPosition();

		if(mensagem){
			canvas.putText(20, 30, 30, "Voc� morreu!");
		}
	}

	@Override
	protected void setup() {
		this.setTitle("Arkanoid");
		this.setResolution(Resolution.MSX);
		this.setFramesPerSecond(60);
		
		bola = new Bola();
		bola.setPosition(150, 150);
		
		int numBlocosPorLinha = 10;
		for(int i = 0; i < quadrado.length; i++){
			int linha = i / numBlocosPorLinha;
			Quadrado novoQuadrado = new Quadrado(cores[linha]);
			quadrado[i] = novoQuadrado;
			novoQuadrado.setPosition(posiQuadX + (i%numBlocosPorLinha) * 20, posiQuadY + (i/numBlocosPorLinha) * 12);
		}
		
		paddle = new Paddle();
		paddle.setPosition(Resolution.MSX.width/2 - 10, Resolution.MSX.height - 15);
		
		this.bindKeyPressed("LEFT", new KeyboardAction() {
			@Override
			public void handleEvent() {
				Point posicao = paddle.getPosition();
				if(posicao.x <= 0 || posicao.x >= Resolution.MSX.width){
					paddle.move(0, 0);
				} else {
					paddle.move(-5, 0);
				}
			}
		});
		
		this.bindKeyPressed("RIGHT", new KeyboardAction() {
			@Override
			public void handleEvent() {
				Point posicao = paddle.getPosition();
				if(posicao.x >= Resolution.MSX.width-33){
					paddle.move(0, 0);
				} else {
					paddle.move(+5, 0);
				}
			}
		});
		
		jogando = true;
	}

	@Override
	protected void loop() {
		if(jogando){
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
			
			for (int i = 0; i < quadrado.length; i++){
				if (quadrado[i].colidiu(bola)){
					bola.direcaoY();
				}
			}

			if(posicao.y >= Resolution.MSX.height-5){
				mensagem = true;
				tempo = 3 * 60;
				jogando  = false;
			}
			redraw();
		}
		
		if(mensagem){
			tempo--;
			if(tempo==0){
				mensagem=false;
				setup();
			}
		}		
		
	}
}