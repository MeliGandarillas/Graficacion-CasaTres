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
            // 4. DIBUJAR MUROS TRIANGULARES / INCLINADOS
            gl.glColor3f(1.0f, 1.0f, 1.0f); // Color blanco para paredes
            for (MuroTriangular mt : nivelActual.murosTriangulares) {
                gl.glPushMatrix();
                
                // Nos posicionamos en el punto de origen de la pared y rotamos
                gl.glTranslatef(mt.x1, elevacionBase, mt.z1);
                gl.glRotatef(mt.angulo, 0, 1, 0);

                float L = mt.largo;
                float g = grosor / 2.0f; // Mitad del grosor hacia adelante y atrás
                float h1 = mt.h1;
                float h2 = mt.h2;

                gl.glBegin(GL2.GL_QUADS);
                    // Frente
                    gl.glNormal3f(0, 0, 1);
                    gl.glVertex3f(0, 0, g); gl.glVertex3f(L, 0, g); gl.glVertex3f(L, h2, g); gl.glVertex3f(0, h1, g);
                    // Atrás
                    gl.glNormal3f(0, 0, -1);
                    gl.glVertex3f(0, 0, -g); gl.glVertex3f(0, h1, -g); gl.glVertex3f(L, h2, -g); gl.glVertex3f(L, 0, -g);
                    // Abajo
                    gl.glNormal3f(0, -1, 0);
                    gl.glVertex3f(0, 0, g); gl.glVertex3f(0, 0, -g); gl.glVertex3f(L, 0, -g); gl.glVertex3f(L, 0, g);
                    // Arriba (Inclinada)
                    gl.glNormal3f(0, 1, 0); 
                    gl.glVertex3f(0, h1, g); gl.glVertex3f(L, h2, g); gl.glVertex3f(L, h2, -g); gl.glVertex3f(0, h1, -g);
                    // Borde izquierdo
                    gl.glNormal3f(-1, 0, 0);
                    gl.glVertex3f(0, 0, -g); gl.glVertex3f(0, 0, g); gl.glVertex3f(0, h1, g); gl.glVertex3f(0, h1, -g);
                    // Borde derecho
                    gl.glNormal3f(1, 0, 0);
                    gl.glVertex3f(L, 0, g); gl.glVertex3f(L, 0, -g); gl.glVertex3f(L, h2, -g); gl.glVertex3f(L, h2, g);
                gl.glEnd();
                
                gl.glPopMatrix();
            }

            // 5. DIBUJAR LOSAS INCLINADAS (Rampas y Techos)
            gl.glColor3f(0.85f, 0.82f, 0.78f); // Mismo color de las losas
            for (LosaInclinada li : nivelActual.losasInclinadas) {
                float gro = 1.0f; // Grosor de la losa
                
                gl.glBegin(GL2.GL_QUADS);
                    // Cara Superior
                    gl.glNormal3f(0, 1, 0);
                    gl.glVertex3f(li.x1, elevacionBase + li.h1, li.z1);
                    gl.glVertex3f(li.x1, elevacionBase + li.h4, li.z2);
                    gl.glVertex3f(li.x2, elevacionBase + li.h3, li.z2);
                    gl.glVertex3f(li.x2, elevacionBase + li.h2, li.z1);
                    
                    // Cara Inferior
                    gl.glNormal3f(0, -1, 0);
                    gl.glVertex3f(li.x1, elevacionBase + li.h1 - gro, li.z1);
                    gl.glVertex3f(li.x2, elevacionBase + li.h2 - gro, li.z1);
                    gl.glVertex3f(li.x2, elevacionBase + li.h3 - gro, li.z2);
                    gl.glVertex3f(li.x1, elevacionBase + li.h4 - gro, li.z2);

                    // Borde Frente (z = z1)
                    gl.glNormal3f(0, 0, -1);
                    gl.glVertex3f(li.x1, elevacionBase + li.h1, li.z1);
                    gl.glVertex3f(li.x2, elevacionBase + li.h2, li.z1);
                    gl.glVertex3f(li.x2, elevacionBase + li.h2 - gro, li.z1);
                    gl.glVertex3f(li.x1, elevacionBase + li.h1 - gro, li.z1);

                    // Borde Atrás (z = z2)
                    gl.glNormal3f(0, 0, 1);
                    gl.glVertex3f(li.x1, elevacionBase + li.h4, li.z2);
                    gl.glVertex3f(li.x1, elevacionBase + li.h4 - gro, li.z2);
                    gl.glVertex3f(li.x2, elevacionBase + li.h3 - gro, li.z2);
                    gl.glVertex3f(li.x2, elevacionBase + li.h3, li.z2);

                    // Borde Izquierdo (x = x1)
                    gl.glNormal3f(-1, 0, 0);
                    gl.glVertex3f(li.x1, elevacionBase + li.h1, li.z1);
                    gl.glVertex3f(li.x1, elevacionBase + li.h1 - gro, li.z1);
                    gl.glVertex3f(li.x1, elevacionBase + li.h4 - gro, li.z2);
                    gl.glVertex3f(li.x1, elevacionBase + li.h4, li.z2);

                    // Borde Derecho (x = x2)
                    gl.glNormal3f(1, 0, 0);
                    gl.glVertex3f(li.x2, elevacionBase + li.h2, li.z1);
                    gl.glVertex3f(li.x2, elevacionBase + li.h3, li.z2);
                    gl.glVertex3f(li.x2, elevacionBase + li.h3 - gro, li.z2);
                    gl.glVertex3f(li.x2, elevacionBase + li.h2 - gro, li.z1);
                gl.glEnd();
            }
            // 6. DIBUJAR MUROS TRIANGULARES INVERTIDOS
            gl.glColor3f(1.0f, 1.0f, 1.0f); // Color blanco para paredes
            for (MuroTriangularInvertido mi : nivelActual.murosInvertidos) {
                gl.glPushMatrix();
                
                gl.glTranslatef(mi.x1, elevacionBase, mi.z1);
                gl.glRotatef(mi.angulo, 0, 1, 0);

                float L = mi.largo;
                float g = grosor / 2.0f;
                float h1 = mi.h1; // Límite inferior 1
                float h2 = mi.h2; // Límite inferior 2
                float techo = alto; // La parte superior siempre toca el techo

                gl.glBegin(GL2.GL_QUADS);
                    // Frente
                    gl.glNormal3f(0, 0, 1);
                    gl.glVertex3f(0, h1, g); gl.glVertex3f(L, h2, g); gl.glVertex3f(L, techo, g); gl.glVertex3f(0, techo, g);
                    // Atrás
                    gl.glNormal3f(0, 0, -1);
                    gl.glVertex3f(0, h1, -g); gl.glVertex3f(0, techo, -g); gl.glVertex3f(L, techo, -g); gl.glVertex3f(L, h2, -g);
                    // Arriba (Plana contra el techo)
                    gl.glNormal3f(0, 1, 0);
                    gl.glVertex3f(0, techo, g); gl.glVertex3f(L, techo, g); gl.glVertex3f(L, techo, -g); gl.glVertex3f(0, techo, -g);
                    // Abajo (Inclinada)
                    gl.glNormal3f(0, -1, 0);
                    gl.glVertex3f(0, h1, g); gl.glVertex3f(0, h1, -g); gl.glVertex3f(L, h2, -g); gl.glVertex3f(L, h2, g);
                    // Borde Izquierdo
                    gl.glNormal3f(-1, 0, 0);
                    gl.glVertex3f(0, h1, -g); gl.glVertex3f(0, h1, g); gl.glVertex3f(0, techo, g); gl.glVertex3f(0, techo, -g);
                    // Borde Derecho
                    gl.glNormal3f(1, 0, 0);
                    gl.glVertex3f(L, h2, g); gl.glVertex3f(L, h2, -g); gl.glVertex3f(L, techo, -g); gl.glVertex3f(L, techo, g);
                gl.glEnd();
                
                gl.glPopMatrix();
            }
            
        }
        
        // DIBUJAR MUEBLES AL FINAL
        muebles.dibujarTodos(gl, glut);
    }
}
