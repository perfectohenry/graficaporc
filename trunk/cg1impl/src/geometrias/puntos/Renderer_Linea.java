/*
 * Creado el 23 de mayo, 2012 por Hernán Nina Hanco
 *
 * Este trabajo es parte del proyecto CG1, que corresponde a la 
 * implementación de algoritmos de Dibujo de graficas.
 * 
 * Universidad Nacional de San Antonio Abad del Cusco
 * Carrera Profesional de Ingeniería Informática y de Sistemas
 * Asignatura: Computación Gráfica I
 */

package geometrias.puntos;
/*
 * Dibujo de líneas con pendiente -1<m<1
 * @author Hernan Nina Hanco 
 */

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.*;

public class Renderer_Linea implements GLEventListener {

    static final Logger logger = Logger.getLogger("BasicLoggingExample");
    protected GL2 gl;
    /*
     * Inicializar graficador OpenGL
     */

    @Override
    public void init(GLAutoDrawable gLDrawable) {
        logger.log(Level.INFO, "método - init");
        // Provee un objeto que enlaza las APIs del OpenGL
        // Que se encargara de realizar las instrucciones de dibujos 
        gl = gLDrawable.getGL().getGL2();

        // Color de fondo del GLCanvas
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        // definir el color del pincel
        gl.glColor3f(1.0f, 0.0f, 0.0f);

    }
    /*
     * Método para actualizar el dibujo cuando,
     * se modifica el tamaño de la ventana.
     */

    @Override
    public void reshape(GLAutoDrawable glad, int x, int y, int width,
            int height) {
        logger.log(Level.INFO, "Reshape");
        // 7. Especificar el área de dibujo (frame) utilizamos coordenadas
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-300, 300, -300, 300, -1.0, 1.0);
        // Dibujar un Sistema de Referencia.
        dibujarSistemaReferencia(-300, 300, -300, 300);
    }
    /*
     *  
     */

    @Override
    public void dispose(GLAutoDrawable glad) {
        // no implementado
    }

    /*
     * Inicializar y presentar el dibujo de OpenGL
     */
    @Override
    public void display(GLAutoDrawable drawable) {
        int x0, y0, xn, yn, dx, dy;
        // Establecer el tamaño y color del punto     
        gl.glPointSize(1);
        gl.glColor3f(0.0f, 0.0f, 1.0f);

        // Dibujo de Líneas de pendiente positiva y menor a 1 
        // utilizando diferentes métodos 
        // A) Dibujar una Línea según la ecuación punto pendiente
        lineaPuntoPendiente(50, 50, 290, 120);
        // B) Dibujar una Línea utilizando el algoritmo de línea DDA
        lineaDDA(50, 70, 290, 140);
        // C) Dibujar una Línea utilizando el algoritmo de línea de Bresenham
        lineaBresenham(50, 90, 290, 160);
        // D) Dibujar una Línea utilizando el algoritmo de OpenGL        
        lineaOpenGL(50, 110, 290, 180);
    }
    
    /*
     * Algoritmo DDA con pendiente -1<m<1
     */

    void lineaDDA(int x0, int y0, int xn, int yn) {
        int x;
        float m, y;
        // Calcular la pendiente
        m = (float) (yn - y0) / (xn - x0);
        x = x0;
        y = y0;
        // Tomar el intervalo del eje X y determinar Y    
        while (x < xn + 1) {
            //Dibujar un punto en la coordenada X, Y
            dibujarPunto(x, Math.round(y));
            /* Determinar el siguiente pixel */
            x++;
            y += m; // el incremento es la pendiente
        }
    }
    /*
     * Algoritmo para el dibujo de Línea con la ecuación 
     * punto pendiente con pendiente -1<m<1
     */

    void lineaPuntoPendiente(int x0, int y0, int xn, int yn) {
        int x;
        float m, b, y;
        // Calcular la pendiente y la constante b
        m = (float) (yn - y0) / (xn - x0);
        b = (float) (y0 - m * x0);

        x = x0;
        y = y0;
        // Tomar el intervalo del eje X y determinar Y    
        while (x < xn + 1) {
            //Dibujar un pixel en la posición X, Y
            dibujarPunto(x, Math.round(y));
            x++;
            y = m * x + b; /* Ecuación punto pendiente de la recta */
        }
    }

    /*
     * Algoritmo para el dibujo de Línea con el algoritmo de  
     * Bresenham con pendiente -1<m<1
     */

    public void lineaBresenham(int x0, int y0, int xn, int yn) {

        // Identificar los valores x0,y0
        int x = x0;
        int y = y0;
        // Calcular la constantes deltax, deltay, 2.deltay y 2deltay-2deltax
        int deltax = xn-x0;
        int deltay = yn-y0;
        int incrA = 2*deltay; // incremento si Pk es menor a 0 
        int incrB = 2 * (deltay - deltax);
        int pk = 2*deltay-deltax; // calcular p0
        // Tomar el intervalo del eje X y determinar Y    
        while (x < xn + 1) {
            dibujarPunto(x, y); /* Escribir en el framebuffer */
            /* Deteminar el siguiente pixel */
            x++; 
            if (pk < 0) {
                pk += incrA;
            } else {
                y++;
                pk += incrB;
            }
        }
    }
    /*
     * Algoritmo para el dibujo de Línea con el algoritmo implementado  
     * por OpenGL considere la pendiente -1<m<1
     */

    void lineaOpenGL(int x0, int y0, int xn, int yn) {
        // Determinar el grosor de la línea
        gl.glLineWidth(1.0f);
        // Activar el estado de dibujo de líneas
        gl.glBegin(GL2.GL_LINES);
            // Establecer el punto inicial y final
            gl.glVertex2i(x0, y0);
            gl.glVertex2i(xn, yn);
        gl.glEnd();
    }
    /*
     * Dibujar el sistema de referencia.
     */

    private void dibujarSistemaReferencia(int xMin, int xMax, int yMin, int yMax) {

        gl.glColor3f(1.0f, 1.0f, 1.0f);

        gl.glBegin(GL2.GL_LINES);

        for (int i = yMin; i <= yMax; i = i + 10) {
            gl.glVertex2i(xMin, i);
            gl.glVertex2i(xMax, i);
        }

        for (int i = xMin; i <= xMax; i = i + 10) {
            gl.glVertex2i(i, yMin);
            gl.glVertex2i(i, yMax);
        }
        gl.glEnd();

        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glLineWidth(3.0f);
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2i(xMin, 0);
        gl.glVertex2i(xMax, 0);

        gl.glVertex2i(0, yMin);
        gl.glVertex2i(0, yMax);
        gl.glEnd();
    }
    /*
     * Dibujar un punto 
     */

    protected void dibujarPunto(int x, int y) {
        gl.glBegin(GL.GL_POINTS);
            gl.glVertex2i(x, y);
        gl.glEnd();
    }
}
