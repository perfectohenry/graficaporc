/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrias.puntos;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

/**
 *
 * @author hernan
 */
public class Renderer_Punto_Aleatorios extends Renderer_Punto {

    @Override
    public void display(GLAutoDrawable drawable) {
        
        for (int i=1;i<=20;i++)
        {
            // 7. Generar un punto de coordenadas aleatorias		
            double x = Math.random() * 400;
            double y = Math.random() * 400;

            // Especificar aleatoriamente el color de dibujo
            gl.glColor3d(Math.random(), Math.random(), Math.random());
            gl.glPointSize(10);

            // Dibujar un punto
            dibujarPunto(x, y);
        }
    }
    
    protected void dibujarPunto(double x, double y) {
        gl.glBegin(GL.GL_POINTS);
        gl.glVertex2d(x, y);
        gl.glEnd();
    }
}
