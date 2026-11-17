package Beta;

import javax.swing.*;
import java.awt.Font;
import java.awt.*;
import java.awt.event.*;

import java.util.function.IntConsumer;

public class ventana {

	private IntConsumer respuesta;
	
	public static JFrame ventana = new JFrame("LUNA");
    public static JPanel datos = new JPanel();
    public static PanelConFondo panel = new PanelConFondo();
    
	public static class PanelConFondo extends JPanel {

	private Image imagen;

	public PanelConFondo() {
		imagen = new ImageIcon("src/Beta/Cuadrícula de estrellas pixeladas (0.5x).gif").getImage();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
	}
}
	public static class GifEscalado extends JLabel {

    private int ancho;
    private int alto;

    public GifEscalado(ImageIcon imagen, int ancho, int alto) {
        super(imagen);
        this.ancho = ancho;
        this.alto = alto;

        setPreferredSize(new Dimension(ancho, alto));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Icon icono = getIcon();

        if (icono != null) {
            Graphics2D g2 = (Graphics2D) g.create();

            g2.scale(
                (double) ancho / icono.getIconWidth(),
                (double) alto / icono.getIconHeight()
            );

            icono.paintIcon(this, g2, 0, 0);
            g2.dispose();
        }
    }
}
	public static class BotonRedondo extends JButton {

    private Color colorFondo = new Color(35, 50, 75);
    private Color colorBorde = new Color(100, 150, 200);
    private Color colorHover = new Color(65, 85, 120);

    public BotonRedondo(String texto) {
        super(texto);

        setForeground(Color.WHITE);
        setFont(new Font("Consolas", Font.BOLD, 16));

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);

        setAlignmentX(Component.CENTER_ALIGNMENT);

        // Efecto al pasar el cursor
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                colorFondo = colorHover;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                colorFondo = new Color(35, 50, 75);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        // Fondo
        g2.setColor(colorFondo);
        g2.fillRoundRect(
            0, 0,
            getWidth() - 1,
            getHeight() - 1,
            20, 20
        );

        // Borde
        g2.setColor(colorBorde);
        g2.setStroke(new BasicStroke(2));

        g2.drawRoundRect(
            1, 1,
            getWidth() - 3,
            getHeight() - 3,
            20, 20
        );

        g2.dispose();

        super.paintComponent(g);
    }
}

	public static void nave(int turno,int combustible,int oxigeno,int comida,int vidanave,int agua){
    	
    	//Creacion de la ventana
    	 

         ventana.setSize(1050, 720);
         ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         ventana.setLayout(new GridLayout(1,1));
         ventana.setLocationRelativeTo(null);

         //Configuracion de labels general
         UIManager.put("Label.font", new Font("Consolas", Font.BOLD, 19));
         UIManager.put("Label.foreground", Color.white);
         
         //Panel principal con el fondo fondo
         panel.setLayout(new BorderLayout());
         panel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
         ventana.add(panel);
         
         // Panel superior contenedor del titulo
         JPanel superior = new JPanel();
         superior.setLayout(new FlowLayout(FlowLayout.CENTER,0,30));
         superior.setOpaque(false);
         JLabel texto = new JLabel("MISION LUNA");
         texto.setFont(new Font("Consolas", Font.BOLD, 40));
         superior.add(texto);
         
         // Panel Grid (tabal 2x2)
         datos.setLayout(new GridLayout(2,2));
         datos.setOpaque(false);
         
         
         // Estado de la nave (1,1)
         JPanel estadoT = new JPanel();
         estadoT.setLayout(new BorderLayout());
         estadoT.setOpaque(false);
         JPanel estado = new JPanel();
         estado.setLayout(new GridLayout(6,2,0,-80));
         estado.setOpaque(false);
         
         JLabel nave = new JLabel("ESTADO DE LA NAVE");
         nave.setFont(new Font("Consolas", Font.BOLD, 23));
         JLabel turnoM = new JLabel("Turno : ");
         JLabel combustibleM = new JLabel("Combustible : ");
         JLabel oxigenoM = new JLabel("O₂ : ");
         JLabel comidaM = new JLabel("Comida : ");
         JLabel vidaM = new JLabel("Vida : ");
         JLabel aguaM = new JLabel("Agua : ");
         JLabel turnoR = new JLabel(turno+"/5");
         JLabel combustibleR = new JLabel(combustible+"/500");
         JLabel oxigenoR = new JLabel(oxigeno+"/100");
         JLabel comidaR = new JLabel(comida+"/100");
         JLabel vidaR = new JLabel(vidanave+"/1000");
         JLabel aguaR = new JLabel(agua+"/50");
         
         estado.add(turnoM);
         estado.add(turnoR);
         estado.add(combustibleM);
         estado.add(combustibleR);
         estado.add(oxigenoM);
         estado.add(oxigenoR);
         estado.add(comidaM);
         estado.add(comidaR);
         estado.add(vidaM);
         estado.add(vidaR);
         estado.add(aguaM);
         estado.add(aguaR);
         estadoT.add(nave, BorderLayout.NORTH);
         estadoT.add(estado, BorderLayout.CENTER);
         datos.add(estadoT);

         // Imagen GIF (1,2)
         ImageIcon imagen = new ImageIcon("src/Beta/Animación pixelada de luna giratoria (0.25x).gif");

         GifEscalado icono = new GifEscalado(imagen, 220, 220);

         JPanel contenedor = new JPanel(new GridBagLayout());
         contenedor.setOpaque(false);
         contenedor.add(icono);

         datos.add(contenedor);
         
         // Agregar todo al panel principal
         panel.add(superior, BorderLayout.NORTH);
         panel.add(datos, BorderLayout.CENTER);
         
         ventana.setVisible(true);
         
    }
	public void event(String ev, String prob1, int menos1, String prob2, int menos2, String prob3, int menos3, String boton1, String boton2, String boton3,IntConsumer respuesta) {
		
		this.respuesta = respuesta;
		
		// Evento (2,1) 
		JPanel eventoT = new JPanel();
        eventoT.setLayout(new BorderLayout());
        eventoT.setBorder(BorderFactory.createEmptyBorder(28, 0, 0, 0));
        eventoT.setOpaque(false);
        JPanel eventoSup = new JPanel();
        eventoSup.setLayout(new BoxLayout(eventoSup,BoxLayout.Y_AXIS));
        eventoSup.setOpaque(false);
        JPanel evento = new JPanel();
        evento.setLayout(new GridLayout(3,2,20,-100));
        evento.setOpaque(false);
        
        JLabel eveTitle = new JLabel("EVENTO: ");
        JLabel eve = new JLabel(ev);
        eventoSup.add(eveTitle);
        eventoSup.add(eve);
        
        eveTitle.setFont(new Font("Consolas", Font.BOLD, 23));
        eve.setFont(new Font("Consolas", Font.BOLD, 23));
        JLabel case1 = new JLabel(prob1+" : ");
        JLabel case1R = new JLabel("-"+menos1);
        if(menos2!=0) {
        	JLabel case2 = new JLabel(prob2+" :");
        	JLabel case2R = new JLabel("-"+menos2);
        	evento.add(case2);
        	evento.add(case2R);
        	if(menos3!=0) {
        		JLabel case3 = new JLabel(prob3+" :");
            	JLabel case3R = new JLabel("-"+menos3);
            	evento.add(case3);
            	evento.add(case3R);
        	}
        }
        evento.add(case1);
        evento.add(case1R);
        eventoT.add(eventoSup, BorderLayout.NORTH);
        eventoT.add(evento, BorderLayout.CENTER);
        datos.add(eventoT);
        
        
        // Opciones (2,2)
        JPanel opc = new JPanel();
        opc.setLayout(new BoxLayout(opc, BoxLayout.Y_AXIS));
        opc.setOpaque(false);
        JLabel elige = new JLabel("ESCOGE UNA OPCION");
        elige.setFont(new Font("Consolas", Font.BOLD, 23));
        BotonRedondo opc1 = new BotonRedondo(boton1);
        BotonRedondo opc2 = new BotonRedondo(boton2);
        BotonRedondo opc3 = new BotonRedondo(boton3);
        opc3.setFont(new Font("Consolas", Font.BOLD, 13));
        BotonRedondo opc4 = new BotonRedondo("RENDIRSE ( -1000hp )");
        
        elige.setAlignmentX(Component.CENTER_ALIGNMENT);
        opc1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        opc2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        opc3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        opc4.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        opc.add(Box.createVerticalStrut(25));
        opc.add(elige);
        opc.add(Box.createVerticalStrut(10));
        opc.add(opc1);
        opc.add(Box.createVerticalStrut(10));
        opc.add(opc2);
        opc.add(Box.createVerticalStrut(10));
        opc.add(opc3);
        opc.add(Box.createVerticalStrut(10));
        opc.add(opc4);
        
        
        //Accion de los botoenes

        opc1.addActionListener(e -> {
        	respuesta.accept(1);
        	datos.removeAll();
        });
        opc2.addActionListener(e -> {
        	respuesta.accept(2);
        	datos.removeAll();
        });
        opc3.addActionListener(e -> {
        	respuesta.accept(3);
        	datos.removeAll();
        });
        opc4.addActionListener(e -> {
        	respuesta.accept(4);
        	datos.removeAll();
        });
        
        datos.add(opc);
        panel.add(datos, BorderLayout.CENTER);
        
        panel.revalidate();
        panel.repaint();
	}
}
