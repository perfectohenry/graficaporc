/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrias.puntos;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

/**
 *
 * @author hernan
 */
public class Renderer_Punto_Aleatorios implements GLEventListener {

    static final Logger logger = Logger.getLogger("BasicLoggingExample");
    //     
    protected GL2 gl; // proporciona funciones de dibujo

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

        for (int i = 1; i <= 20; i++) {
            // 7. Generar un punto de coordenadas aleatorias		
            double x = Math.random() * 400;
            double y = Math.random() * 400;

            // Especificar aleatoriamente el color de dibujo
            gl.glColor3d(Math.random(), Math.random(), Math.random());
            gl.glPointSize(5);

            // Dibujar un punto
            dibujarPunto(x, y);
        }
    }

    protected void dibujarPunto(double x, double y) {
        gl.glBegin(GL.GL_POINTS);
        gl.glVertex2d(x, y);
        gl.glEnd();
    }

    @Override
    public void dispose(GLAutoDrawable glad) {
        // throw new UnsupportedOperationException("Not supported yet.");
    }
}
