import java.util.ArrayList;
import java.util.List;

class Losa {
    float x1, z1, x2, z2;

    Losa(float x1, float z1, float x2, float z2) {
        this.x1 = x1; this.z1 = z1;
        this.x2 = x2; this.z2 = z2;
    }
}

class LosaTriangular {
    float x1, z1, x2, z2, x3, z3;

    LosaTriangular(float x1, float z1, float x2, float z2, float x3, float z3) {
        this.x1 = x1; this.z1 = z1;
        this.x2 = x2; this.z2 = z2;
        this.x3 = x3; this.z3 = z3;
    }
}
class LosaInclinada {
    float x1, z1, x2, z2;
    float h1, h2, h3, h4; // Alturas de las 4 esquinas

    LosaInclinada(float x1, float z1, float x2, float z2, float h1, float h2, float h3, float h4) {
        this.x1 = x1; this.z1 = z1;
        this.x2 = x2; this.z2 = z2;
        this.h1 = h1; this.h2 = h2;
        this.h3 = h3; this.h4 = h4;
    }
}

class Muro {
    float x1, z1, x2, z2, largo, angulo;
    float factorAltura = 1.0f;

    Muro(float x1, float z1, float x2, float z2) {
        this.x1 = x1; 
        this.z1 = z1; 
        this.x2 = x2; 
        this.z2 = z2;
        this.largo = (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(z2 - z1, 2));
        this.angulo = (float) Math.toDegrees(Math.atan2(z1 - z2, x2 - x1));
    }

    public Muro aMitad() {
        this.factorAltura = 0.5f;
        return this;
    }

    float distanciaAlJugador(float px, float pz) {
        float l2 = (float) (Math.pow(x2 - x1, 2) + Math.pow(z2 - z1, 2));
        if (l2 == 0) return (float) Math.sqrt(Math.pow(px - x1, 2) + Math.pow(pz - z1, 2));
        float t = ((px - x1) * (x2 - x1) + (pz - z1) * (z2 - z1)) / l2;
        t = Math.max(0, Math.min(1, t));
        float cx = x1 + t * (x2 - x1), cz = z1 + t * (z2 - z1);
        return (float) Math.sqrt(Math.pow(px - cx, 2) + Math.pow(pz - cz, 2));
    }
}
class MuroTriangular {
    float x1, z1, x2, z2, h1, h2, largo, angulo;

    MuroTriangular(float x1, float z1, float h1, float x2, float z2, float h2) {
        this.x1 = x1;
        this.z1 = z1;
        this.h1 = h1;
        this.x2 = x2;
        this.z2 = z2;
        this.h2 = h2;
        this.largo = (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(z2 - z1, 2));
        this.angulo = (float) Math.toDegrees(Math.atan2(z1 - z2, x2 - x1));
    }
public boolean colisiona(float px, float pz, float radio, float piesY, int pisoActual) {
        float longitudCuadrada = (x2 - x1) * (x2 - x1) + (z2 - z1) * (z2 - z1);
        if (longitudCuadrada == 0) return false; 

        float t = ((px - x1) * (x2 - x1) + (pz - z1) * (z2 - z1)) / longitudCuadrada;
        float tClamp = Math.max(0, Math.min(1, t)); 

        float projX = x1 + tClamp * (x2 - x1);
        float projZ = z1 + tClamp * (z2 - z1);

        float distCuadrada = (px - projX) * (px - projX) + (pz - projZ) * (pz - projZ);
        
        if (distCuadrada <= (radio * radio)) {
            // VERIFICACIÓN 3D: ¿Estás tocando el muro a lo alto?
            float hLocal = h1 + tClamp * (h2 - h1); // Altura de la diagonal en este punto exacto
            float piesYLocal = piesY - (pisoActual * 26.0f); // Tu altura respecto al piso que pisas
            
            // Este muro crece desde el suelo hacia arriba. Si tus pies están por debajo del límite, chocas.
            if (piesYLocal < hLocal) {
                return true;
            }
        }
        return false;
    }
}

class MuroTriangularInvertido {
    float x1, z1, x2, z2, h1, h2, largo, angulo;

    MuroTriangularInvertido(float x1, float z1, float h1, float x2, float z2, float h2) {
        this.x1 = x1;
        this.z1 = z1;
        this.h1 = h1; 
        this.x2 = x2;
        this.z2 = z2;
        this.h2 = h2; 
        this.largo = (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(z2 - z1, 2));
        this.angulo = (float) Math.toDegrees(Math.atan2(z1 - z2, x2 - x1));
    }
public boolean colisiona(float px, float pz, float radio, float piesY, int pisoActual) {
        float longitudCuadrada = (x2 - x1) * (x2 - x1) + (z2 - z1) * (z2 - z1);
        if (longitudCuadrada == 0) return false; 

        float t = ((px - x1) * (x2 - x1) + (pz - z1) * (z2 - z1)) / longitudCuadrada;
        float tClamp = Math.max(0, Math.min(1, t)); 

        float projX = x1 + tClamp * (x2 - x1);
        float projZ = z1 + tClamp * (z2 - z1);

        float distCuadrada = (px - projX) * (px - projX) + (pz - projZ) * (pz - projZ);
        
        if (distCuadrada <= (radio * radio)) {
            // VERIFICACIÓN 3D: ¿Te pegas en la cabeza?
            float hLocal = h1 + tClamp * (h2 - h1); // Borde colgante del muro
            float piesYLocal = piesY - (pisoActual * 26.0f);
            float alturaCabeza = piesYLocal + 10.0f; // La altura de tu cabeza
            
            // Este muro cuelga del techo. Si tu cabeza es más alta que el borde inferior del muro, te pegas.
            if (alturaCabeza > hLocal) {
                return true;
            }
        }
        return false;
    }
}
// Clase corregida para escaleras orientadas en el Eje X (De izquierda a derecha)
class EscaleraU {
    float xMin, xMax, zMin, zMax;
    float xDescanso; // El punto en X donde comienza el descanso plano
    float alturaInicial, alturaFinal, midH;

    EscaleraU(float xMin, float xMax, float zMin, float zMax, float xDescanso, float hInicial, float hFinal) {
        this.xMin = xMin; 
        this.xMax = xMax;
        this.zMin = zMin; 
        this.zMax = zMax;
        this.xDescanso = xDescanso;
        this.alturaInicial = hInicial;
        this.alturaFinal = hFinal;
        this.midH = hInicial + ((hFinal - hInicial) / 2.0f);
    }

    public float obtenerAltura(float px, float pz, float piesY) {
        // 1. Si estás fuera de la caja general de la escalera, ignora por completo
        if (px < xMin - 1.0f|| px > xMax + 1.0f|| pz < zMin - 1.0f || pz > zMax + 1.0f) return -1.0f;

        float midZ = (zMin + zMax) / 2.0f; // Línea central que divide el Tramo 1 del Tramo 2
        float alturaCalculada = 0f;

        // 2. ZONA DE DESCANSO (Lado derecho de la casa)
        if (px >= xDescanso) {
            alturaCalculada = midH;
        } 
        // 3. TRAMO 1 (Lado izquierdo, zona frontal Z < midZ)
        else if (pz < midZ) {
            // Sube desde xMin (30) hacia el descanso (60)
            float t = (px - xMin) / (xDescanso - xMin);
            if (t < 0) t = 0f; if (t > 1) t = 1f;
            alturaCalculada = alturaInicial + (t * (midH - alturaInicial));
        } 
        // 4. TRAMO 2 (Lado izquierdo, zona trasera Z >= midZ)
        else {
            // Sube desde el descanso (60) de regreso a xMin (30)
            float t = (xDescanso - px) / (xDescanso - xMin);
            if (t < 0) t = 0f; if (t > 1) t = 1f;
            alturaCalculada = midH + (t * (alturaFinal - midH));
        }

        // 5. EL FILTRO 3D
        // Evita que te teletransporte si caminas por debajo del segundo tramo o si estás en otro piso 10
        if (Math.abs(piesY - alturaCalculada) > 3.0f) {
            return -1.0f;
        }

        return alturaCalculada;
    }
}
// Clase exclusiva para DIBUJAR los escalones en 3D (Solo guarda los datos)
class TramoEscalera {
    float x, z, alturaInicio;
    float ancho, huella, peralte;
    int escalones;
    float angulo;

    TramoEscalera(float x, float z, float alturaInicio, float ancho, float huella, float peralte, int escalones, float angulo) {
        this.x = x; 
        this.z = z; 
        this.alturaInicio = alturaInicio;
        this.ancho = ancho; 
        this.huella = huella; 
        this.peralte = peralte;
        this.escalones = escalones; 
        this.angulo = angulo;
    }
}

class ColisionObjeto {
    float x, z, ancho, largo;
    ColisionObjeto(float x, float z, float ancho, float largo) {
        this.x = x;
        this.z = z;
        this.ancho = ancho;
        this.largo = largo;
    }

    boolean colisiona(float px, float pz, float radioJugador) {
        return px >= x - ancho / 2 - radioJugador &&
               px <= x + ancho / 2 + radioJugador &&
               pz >= z - largo / 2 - radioJugador &&
               pz <= z + largo / 2 + radioJugador;
    }
}

class Nivel {
    public List<Muro> muros = new ArrayList<>();
    public List<Losa> losas = new ArrayList<>();
    public List<LosaTriangular> triangulos = new ArrayList<>();
    public List<MuroTriangular> murosTriangulares = new ArrayList<>();
    public List<LosaInclinada> losasInclinadas = new ArrayList<>();
    public List<MuroTriangularInvertido> murosInvertidos = new ArrayList<>();
    public List<TramoEscalera> escaleras = new ArrayList<>();
    public List<EscaleraU> escalerasU = new ArrayList<>(); 

}
