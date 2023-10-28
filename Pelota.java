package poolgame;

import java.awt.*;

public class Pelota {
    private int x, y;
    private int radio;
    private Color color;
    private double velocidadX, velocidadY;

    public Pelota(int x, int y, int radio, Color color, double velocidadX, double velocidadY) {
        this.x = x;
        this.y = y;
        this.radio = radio;
        this.color = color;
        this.velocidadX = velocidadX;
        this.velocidadY = velocidadY;
    }

    public void mover() {
        x += velocidadX;
        y += velocidadY;

        // Disminuye la velocidad debido al rozamiento
        velocidadX *= 0.99;
        velocidadY *= 0.99;
    }

    public void verificarColisionConParedes(int ancho, int alto) {
        if (x - radio < 0 || x + radio > ancho) {
            velocidadX = -velocidadX;
        }
        if (y - radio < 0 || y + radio > alto) {
            velocidadY = -velocidadY;
        }
    }

    public boolean colision(Pelota otra) {
        double distancia = Math.sqrt(Math.pow(x - otra.x, 2) + Math.pow(y - otra.y, 2));
        return distancia <= radio + otra.radio;
    }

    public void dividirVelocidad(Pelota otra) {
        double velocidadTotal = Math.sqrt(velocidadX * velocidadX + velocidadY * velocidadY);
        double direccionX = (otra.x - x) / distancia(otra);
        double direccionY = (otra.y - y) / distancia(otra);
        velocidadX = velocidadTotal * direccionX;
        velocidadY = velocidadTotal * direccionY;
        otra.velocidadX = -velocidadX; // Cambia la dirección de la otra pelota
        otra.velocidadY = -velocidadY; // Cambia la dirección de la otra pelota
    }

    private double distancia(Pelota otra) {
        return Math.sqrt(Math.pow(x - otra.x, 2) + Math.pow(y - otra.y, 2));
    }

    public void dibujar(Graphics g) {
        g.setColor(color);
        g.fillOval(x - radio, y - radio, 2 * radio, 2 * radio);
    }

	public void setVelocidadX(int i) {
		// TODO Auto-generated method stub
		
	}
}
