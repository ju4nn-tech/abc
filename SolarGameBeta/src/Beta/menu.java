package Beta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class menu extends JFrame implements ActionListener {

    private Color colorFondo = new Color(8, 14, 28);
    private Color colorBoton = new Color(34, 54, 82);
    private Color colorAcento = new Color(69, 219, 194);

    private JButton btnIniciar;
    private JButton btnSalir;
    private boolean juegoListo = false;

    public menu() {
        setTitle("SOLAR GAME");
        setSize(1050, 720);
        setMinimumSize(new Dimension(900, 650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Usamos el panel con estrellas estáticas como fondo principal
        FondoEstrellas panelFondo = new FondoEstrellas();
        panelFondo.setLayout(new BorderLayout());

        JPanel centro = new JPanel();
        centro.setOpaque(false);
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("SOLAR GAME");
        titulo.setFont(new Font("Monospaced", Font.BOLD, 54));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Nivel 1: Misión Luna");
        subtitulo.setFont(new Font("Monospaced", Font.PLAIN, 16));
        subtitulo.setForeground(Color.LIGHT_GRAY);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnIniciar = crearBoton("INICIAR", true);
        btnIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnSalir = crearBoton("SALIR", false);
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);

        centro.add(Box.createVerticalGlue());
        centro.add(titulo);
        centro.add(Box.createVerticalStrut(10));
        centro.add(subtitulo);
        centro.add(Box.createVerticalStrut(40));
        centro.add(btnIniciar);
        centro.add(Box.createVerticalStrut(12));
        centro.add(btnSalir);
        centro.add(Box.createVerticalGlue());

        panelFondo.add(centro, BorderLayout.CENTER);
        setContentPane(panelFondo);
        setVisible(true);
    }

    private JButton crearBoton(String texto, boolean destacado) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Monospaced", Font.BOLD, 16));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(12, 28, 12, 28));
        if (destacado) {
            boton.setBackground(colorAcento);
            boton.setForeground(colorFondo);
        } else {
            boton.setBackground(colorBoton);
            boton.setForeground(Color.WHITE);
        }
        boton.addActionListener(this);
        return boton;
    }

    public boolean isJuegoListo() {
        return juegoListo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIniciar) {
            juegoListo = true;
            dispose();
        } else if (e.getSource() == btnSalir) {
            System.exit(0);
        }
    }

    // Panel con estrellas fijas generadas con Random (sin Timer)
    private class FondoEstrellas extends JPanel {
        private int[] x = new int[80];
        private int[] y = new int[80];
        private Random random = new Random();

        public FondoEstrellas() {
            setBackground(colorFondo);
            for (int i = 0; i < x.length; i++) {
                x[i] = random.nextInt(1050);
                y[i] = random.nextInt(720);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            for (int i = 0; i < x.length; i++) {
                g.fillOval(x[i], y[i], 3, 3);
            }
        }
    }
}