/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrias.frontend;

import geometrias.puntos.Renderer_Punto_Entrada;
import java.awt.Container;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class Ventana_Entrada extends JFrame implements ActionListener {
    // Atributos de Clase 
    // objetos de swing

    private JPanel pOpenGL;
    private JPanel pEntrada;
    private JButton btnDibujar;
    private JTextField txtX;
    private JTextField txtY;
    // objetos de libreria JOGL
    private GLCanvas glCanvas;
    // Objeto graficador de un punto
    private Renderer_Punto_Entrada render_punto;

    public Ventana_Entrada() {
        inicializarVentana();
    }

    // Inicializar la ventana
    private void inicializarVentana() {
        // habilita el botón cerrar de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un panel para ubicar el graficador Opengl
        pOpenGL = new JPanel(new BorderLayout());
        pOpenGL.setBorder(new TitledBorder(
                new EtchedBorder(), // Tipo de Borde
                "OpenGL"));        // Título del borde
        
        // Añadir el graficador al panel de OpenGL
        // 1. Configuración de OpenGL Version 2
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        // 2. Canvas es el aplicativo gráfico que se empotra en un JFrame - Ventana
        glCanvas = new GLCanvas(capabilities);
        render_punto = new Renderer_Punto_Entrada();
        glCanvas.addGLEventListener(render_punto);  
                
        pOpenGL.add(glCanvas); // Se añade el graficador OpenGL al panel 
        glCanvas.setSize(500,500);
        
        // Crear el panel de entrada de datos
        pEntrada = new JPanel();
        pEntrada.setBorder(new TitledBorder(
                new EtchedBorder(), // Tipo de Borde
                "Datos de gráfico"));
        // Añadir controles
        JLabel lblX = new JLabel("X: ");
        txtX = new JTextField(5);
        JLabel lblY = new JLabel("Y: ");
        txtY = new JTextField(5);
        btnDibujar = new JButton("Dibujar");
        // Añadir los controles al panel de entrada de datos
        pEntrada.add(lblX);
        pEntrada.add(txtX);
        pEntrada.add(lblY);
        pEntrada.add(txtY);
        pEntrada.add(btnDibujar);
        
        // hacer que el botón capte eventos cuando se hace click sobre él.
        btnDibujar.addActionListener(this);

        // Añadir los paneles en la ventana
        // Obtener el contenedor de componentes
        Container content = getContentPane();    
        content.add(pOpenGL, BorderLayout.CENTER);
        content.add(pEntrada, BorderLayout.SOUTH);
        // Mostrar ventana
        pack();
        setVisible(true);                        
    }
    /*
     * Método para tratar eventos en el formulario.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        // Identificar el objeto o control donde se genero el evento
        Object source = e.getSource();
        if (source == btnDibujar) { // si hay evento click en el botón
            // ubicar las nuevas coordenadas del punto
            int x = Integer.parseInt(txtX.getText());
            int y = Integer.parseInt(txtY.getText());
            // actualizar los valores de los puntos en el dibujador
            render_punto.setX(x);
            render_punto.setY(y);
            // repintar el nuevo punto
            glCanvas.repaint();
        }
    }

    public static void main(String args[]) {
        // iniciar la ventana
        Ventana_Entrada ventana;
        ventana = new Ventana_Entrada();
    }
}