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
import javax.media.opengl.glu.GLU;

/**
 *
 * @author hernan
 */
public class Renderer_Punto_Entrada implements GLEventListener {

    static final Logger logger = Logger.getLogger("BasicLoggingExample");
    //     
    protected GL2 gl;
    private int x;
    private int y;

    public Renderer_Punto_Entrada() {
        this.x = 0;
        this.y = 0;
    }

    public Renderer_Punto_Entrada(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void reshape(GLAutoDrawable glad, int x, int y, int width,
            int height) {
        logger.log(Level.INFO, "Reshape");
        // 7. Especificar el área de dibujo (frame) utilizamos coordenadas


        GLU glu = new GLU();
//        gl.glMatrixMode(GL2.GL_PROJECTION);
//        gl.glLoadIdentity();
//        gl.glOrtho(0, width, 0, height, -1.0, 1.0);
//        glu.gluLookAt(0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
        gl.glViewport(0, 0, 680, 680);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(75, 680.0 / 680.0, 0.10, 100.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        // glu.gluLookAt(0.0, 0.0, 20.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
//Acercamos la cámara a los objetos

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        // Especificar aleatoriamente el color de dibujo
        gl.glColor3d(Math.random(), Math.random(), Math.random());
        gl.glPointSize(10);

        // Dibujar un punto
        dibujarPunto(this.x, this.y);
    }

    protected void dibujarPunto(int x, int y) {
        gl.glBegin(GL.GL_POINTS);
        gl.glVertex2d(x, y);
        gl.glEnd();
    }

    @Override
    public void init(GLAutoDrawable glad) {
        logger.log(Level.INFO, "método - init");
        // Provee un objeto que enlaza las APIs del OpenGL
        // Que se encargara de realizar las instrucciones de dibujos 
        gl = glad.getGL().getGL2();

        // 6. Especificar el color del dibujo: Rojo
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glDrawBuffer(GL2.GL_FRONT_AND_BACK);
    }

    @Override
    public void dispose(GLAutoDrawable glad) {
    }
}
