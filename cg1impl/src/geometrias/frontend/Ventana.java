/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrias.frontend;

import geometrias.Renderer;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

/**
 *
 * @author hernan
 */
public class Ventana extends JFrame {

    public static void main(String[] args) {
        // setup OpenGL Version 2
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        // The canvas is the widget that's drawn in the JFrame
        GLCanvas glcanvas = new GLCanvas(capabilities);
        glcanvas.addGLEventListener(new Renderer());
        glcanvas.setSize(400, 400);

        JFrame frame = new JFrame("Hello World");
        frame.getContentPane().add(glcanvas);

        // shutdown the program on windows close event
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent ev) {
                System.exit(0);
            }
        });

        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }
}
