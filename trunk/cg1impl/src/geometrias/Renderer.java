/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrias;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;


/**
 *
 * @author hernan
 */
public class Renderer implements GLEventListener {
    
    private GL2 gl;

    @Override
    public void init(GLAutoDrawable gLDrawable) {
        //gl = drawable.getGL(); // just to show what the drawable provides
                
        gl = gLDrawable.getGL().getGL2();

        // 6. specify a drawing color: gray
        gl.glColor3f(0.5f, 0.5f, 0.5f);
        gl.glDrawBuffer(GL2.GL_FRONT_AND_BACK);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
               // 8. specify to draw a point
        gl.glPointSize(10); // just to make it large enough to be seen
        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex2i(300, 300);
        gl.glEnd();

        // sometimes more buffers are used, so draw it again. 
        // drawable.repaint(); //Schedules a repaint of the component at some point in the future.
        //gl.glFlush(); // seems not needed anyway
    }

    @Override
    public void reshape(GLAutoDrawable glad, int x, int y, int width,
            int height) {
         // 7. specify the drawing area (frame) coordinates
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0, width, 0, height, -1.0, 1.0);
    }

    @Override
    public void dispose(GLAutoDrawable glad) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
