/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrias.frontend;

// Librerias de Swing - GUI
import geometrias.curvas.Renderer_Circulo;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

// Librerias de OpenGL
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
// Clase para presentación de dibujo de OpenGL
import geometrias.puntos.Renderer_Punto_Aleatorios;
import geometrias.lineas.Renderer_Linea;
import geometrias.puntos.Renderer_Punto;

/**
 *
 * @author hernan
 */
public class Ventana {

    public static void main(String[] args) {
        // 1. Configuración de OpenGL Version 2
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities 
                capabilities = new GLCapabilities(profile);

        // 2. Canvas es el aplicativo gráfico que se empotra en un JFrame - Ventana
        GLCanvas glcanvas = new GLCanvas(capabilities);
        glcanvas.addGLEventListener(new Renderer_Circulo());
                                        
        glcanvas.setSize(600, 600);
        
        // 3. Crear la ventana para mostrar la aplicación de dibujo
        JFrame frame = new JFrame("Aplicación de OpenGL");
        frame.getContentPane().add(glcanvas);
        // 4. Añadir el evento para cerrar la ventana
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ev) {
                System.exit(0);
            }
        });
        // 5. Cambiar el tamaño de la ventana y visualizarla
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }
}
