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
package geometrias.curvas;
/*
 * Dibujo de líneas con pendiente -1<m<1
 * @author Hernan Nina Hanco 
 */

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.*;

public class Renderer_Circulo implements GLEventListener {

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
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
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
        // dibujarSistemaReferencia(-300, 300, -300, 300);
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

        // Dibujo de Circulos 
        // utilizando diferentes métodos 
        // Se realiza el dibujo en el segundo octante
        // A) Dibujar un Círculo según la ecuación Canónica
        dibujarCirculoCanonica(200, 0, 0);
        // B) Dibujar un Círculo según la ecuación parámetrica polar
        dibujarCirculoPolar(150,0,0);
        // C) Dibujar un Círculo según el algoritmo de punto medio
        dibujarCirculoPuntoMedio(100,0,0);
        // D) Dibujar un Círculo según el algoritmo implementado por OpenGL

    }

    /*
     * Algoritmo de dibujo de un Círculo basado en la ecuación Canónica
     */
    void dibujarCirculoCanonica(int r, int xc, int yc) {
        int x = 0;
        int y = r;

        while (x <= y) {
            this.dibujarPunto(x + xc, y + yc);
            x++;
            double yd = Math.sqrt(r * r - x * x);
            y = (int) Math.round(yd);
        }

    }
    /*
     * Algoritmo para el dibujo de círculo basado en la ecuación parámetrica 
     * polar de la circunferencia.
     */

    void dibujarCirculoPolar(int r, int xc, int yc) {
        // Determinar el angulo de variación
        double theta = Math.toRadians(0);
        // Punto inicial
        int x = r;
        int y = 0;
        // Mientras el angulo no exceda a 360 dibujar puntos
        while (theta <= 2*Math.PI) {
            this.dibujarPunto(x + xc, y + yc);
            // Incrementar el ángulo
            theta=theta+Math.toRadians(5);
            // Cálcular los valores x e y de forma parámetrica
            double xd = r * Math.cos(theta);
            x = (int) Math.round(xd);
            double yd = r * Math.sin(theta);
            y = (int) yd;
        }
    }

    /*
     * Algoritmo para el dibujo de Círculo con el algoritmo de  
     * punto medio
     */
    public void dibujarCirculoPuntoMedio(int r, int xc, int yc) {
        // Punto inicial del círculo
        int x = 0;
        int y = r;
        // Cálcular el parámetro inicial de decisión
        int pk = 1-r;
        
        // verificar el pk para determinar las posiciones de pixel siguuientes
        while (x<=y)
        {
            System.out.println("(x,y)= "+x+","+y+" pk="+pk);
            dibujarPunto(xc+x,yc+y);
            if (pk<0){
                pk+=2*(x+1)+1;
                x++;
            }
            else // pk>=0
            {
                pk+=2*(x+1)+1 - 2*(y-1);
                x++;
                y--;
            }
        }
    }
    /*
     * Algoritmo para el dibujo de Círculo con el algoritmo implementado  
     * por OpenGL.
     */

    void dibujarCirculoOpenGL(int r, int xc, int yc) {
    }

    /*
     * Dibujar un punto 
     */
    protected void dibujarPunto(int x, int y) {
        gl.glPointSize(2);
        gl.glBegin(GL.GL_POINTS);
        gl.glVertex2i(x, y);
        gl.glEnd();
    }
}
