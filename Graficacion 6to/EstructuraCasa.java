import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import java.util.ArrayList;
import java.util.List;

public class EstructuraCasa {

    private final float ancho, largo, alto, grosor;
    private final List<Nivel> niveles = new ArrayList<>();
    
    // Conectamos la nueva clase de muebles
    private final Muebles muebles = new Muebles();

    public EstructuraCasa(float ancho, float largo, float alto, float grosor) {
        this.ancho  = ancho;
        this.largo  = largo;
        this.alto   = alto;
        this.grosor = grosor;
        
        // Llamamos a nuestra nueva fábrica de planos para llenar la lista
        niveles.add(PlanosCasa.construirPlantaBaja());
        niveles.add(PlanosCasa.construirSegundaPlanta());
        niveles.add(PlanosCasa.construirTerceraPlanta());
    }
    
    public boolean hayColisionConMuros(float x, float z, int pisoActual, float radioJugador) {
        if (pisoActual < 0 || pisoActual >= niveles.size()) return false;

        float margenMuro = radioJugador + (grosor / 2.0f);
        for (Muro muro : niveles.get(pisoActual).muros) {
            if (muro.distanciaAlJugador(x, z) <= margenMuro) {
                return true;
            }
        }
        return false;
    }

    public void dibujar(GL2 gl, GLUT glut, int pisoVisible) {
        // SUELO EXTERIOR DE PASTO
        gl.glColor3f(0.3f, 0.6f, 0.3f);
        gl.glBegin(GL2.GL_QUADS);
            gl.glNormal3f(0, 1, 0);
            gl.glVertex3f(-300, 0,  300); gl.glVertex3f( 300, 0,  300);
            gl.glVertex3f( 300, 0, -300); gl.glVertex3f(-300, 0, -300);
        gl.glEnd();

        // RENDERIZAR TODOS LOS NIVELES
        for (int i = 0; i <= pisoVisible; i++) {
            if (i >= niveles.size()) break; 
            
            Nivel nivelActual = niveles.get(i);
            float elevacionBase = i * alto;

            // DIBUJAR LOSAS RECTANGULARES
            gl.glColor3f(0.85f, 0.82f, 0.78f); 
            for (Losa losa : nivelActual.losas) {
                float cx = (losa.x1 + losa.x2) / 2.0f;
                float cz = (losa.z1 + losa.z2) / 2.0f;
                float anchoLosa = Math.abs(losa.x2 - losa.x1);
                float largoLosa = Math.abs(losa.z2 - losa.z1);

                gl.glPushMatrix();
                    gl.glTranslatef(cx, elevacionBase - 1.0f, cz);
                    gl.glScalef(anchoLosa, 1.0f, largoLosa);
                    glut.glutSolidCube(1.0f);
                gl.glPopMatrix();
            }

            // DIBUJAR LOSAS TRIANGULARES
            for (LosaTriangular tri : nivelActual.triangulos) {
                float yTop = elevacionBase;
                float yBot = elevacionBase - 1.0f; 

                gl.glBegin(GL2.GL_TRIANGLES);
                    gl.glNormal3f(0, 1, 0);
                    gl.glVertex3f(tri.x1, yTop, tri.z1); gl.glVertex3f(tri.x2, yTop, tri.z2); gl.glVertex3f(tri.x3, yTop, tri.z3);
                    gl.glNormal3f(0, -1, 0);
                    gl.glVertex3f(tri.x1, yBot, tri.z1); gl.glVertex3f(tri.x3, yBot, tri.z3); gl.glVertex3f(tri.x2, yBot, tri.z2);
                gl.glEnd();

                gl.glBegin(GL2.GL_QUADS);
                    gl.glVertex3f(tri.x1, yTop, tri.z1); gl.glVertex3f(tri.x2, yTop, tri.z2); gl.glVertex3f(tri.x2, yBot, tri.z2); gl.glVertex3f(tri.x1, yBot, tri.z1);
                    gl.glVertex3f(tri.x2, yTop, tri.z2); gl.glVertex3f(tri.x3, yTop, tri.z3); gl.glVertex3f(tri.x3, yBot, tri.z3); gl.glVertex3f(tri.x2, yBot, tri.z2);
                    gl.glVertex3f(tri.x3, yTop, tri.z3); gl.glVertex3f(tri.x1, yTop, tri.z1); gl.glVertex3f(tri.x1, yBot, tri.z1); gl.glVertex3f(tri.x3, yBot, tri.z3);
                gl.glEnd();
            }

            // DIBUJAR MUROS
            gl.glColor3f(1.0f, 1.0f, 1.0f);
            for (Muro m : nivelActual.muros) {
                float altoReal = alto * m.factorAltura;
                gl.glPushMatrix();
                gl.glTranslatef(m.x1, elevacionBase, m.z1);
                gl.glRotatef(m.angulo, 0, 1, 0);
                gl.glTranslatef(m.largo / 2, altoReal / 2, 0);
                gl.glScalef(m.largo, altoReal, grosor);
                glut.glutSolidCube(1.0f);
                gl.glPopMatrix();
            }
        }
        
        // DIBUJAR MUEBLES AL FINAL
        muebles.dibujarTodos(gl, glut);
    }
}
