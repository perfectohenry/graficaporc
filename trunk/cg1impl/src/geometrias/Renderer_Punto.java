/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrias;

// Librerias de registro de eventos
import java.util.logging.Level;
import java.util.logging.Logger;
// Librerias de OpenGL
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

/**
 *
 * @author hernan
 */
public class Renderer_Punto implements GLEventListener {

    static final Logger logger = Logger.getLogger("BasicLoggingExample");
    //     
    private GL2 gl;

    @Override
    public void init(GLAutoDrawable gLDrawable) {
        logger.log(Level.INFO, "método - init");
        // Provee un objeto que enlaza las APIs del OpenGL
        // Que se encargara de realizar las instrucciones de dibujos 
        gl = gLDrawable.getGL().getGL2();

        // 6. Especificar el color del dibujo: Rojo
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glDrawBuffer(GL2.GL_FRONT_AND_BACK);
    }

    @Override
    public void reshape(GLAutoDrawable glad, int x, int y, int width,
            int height) {
        logger.log(Level.INFO, "Reshape");
        // 7. Especificar el área de dibujo (frame) utilizamos coordenadas
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0, width, 0, height, -1.0, 1.0);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        logger.log(Level.INFO, "Display");
        // 8. Especificar el dibujo de un punto
        gl.glPointSize(10); // Determina el tamaño de un punto
        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex2i(150, 150);
        gl.glEnd();
    }
    @Override
    public void dispose(GLAutoDrawable glad) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
