package poolgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ColisionDeBolas extends JPanel implements ActionListener {
    private Timer timer;
    private Pelota pelota1, pelota2;
    private boolean golpeando = false;

    public ColisionDeBolas() {
        // Inicializa las pelotas con velocidad cero
        pelota1 = new Pelota(100, 200, 20, Color.RED, 0, 0);
        pelota2 = new Pelota(300, 200, 20, Color.BLUE, 0, 0);

        // Configura el temporizador
        timer = new Timer(10, this);
        timer.start();

        // Agrega un KeyListener para controlar el golpe
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_SPACE && !golpeando) {
                    golpearPelota();
                    golpeando = true;
                }
            }
        });
        setFocusable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Mueve las pelotas si se están golpeando
        if (golpeando) {
            pelota1.mover();
            pelota2.mover();
        }

        // Verifica colisión entre pelota1 y pelota2
        if (pelota1.colision(pelota2)) {
            pelota1.dividirVelocidad(pelota2);
        }

        // Verifica colisión con las paredes y ajusta la dirección
        pelota1.verificarColisionConParedes(getWidth(), getHeight());
        pelota2.verificarColisionConParedes(getWidth(), getHeight());

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        pelota1.dibujar(g);
        pelota2.dibujar(g);
    }

    private void golpearPelota() {
        // Establece velocidades iniciales para las pelotas
        pelota1.setVelocidadX(2);
        pelota1.setVelocidadX(2);
        pelota2.setVelocidadX(-1);
        pelota2.setVelocidadX(1);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Colisión de Bolas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        ColisionDeBolas colisionDeBolas = new ColisionDeBolas();
        frame.add(colisionDeBolas);

        frame.setVisible(true);
    }
}
