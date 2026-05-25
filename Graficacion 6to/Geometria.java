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
}
class MuroTriangularInvertido {
    float x1, z1, x2, z2, h1, h2, largo, angulo;

    MuroTriangularInvertido(float x1, float z1, float h1, float x2, float z2, float h2) {
        this.x1 = x1;
        this.z1 = z1;
        this.h1 = h1; // Qué tan bajo llega en el primer punto
        this.x2 = x2;
        this.z2 = z2;
        this.h2 = h2; // Qué tan bajo llega en el segundo punto
        this.largo = (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(z2 - z1, 2));
        this.angulo = (float) Math.toDegrees(Math.atan2(z1 - z2, x2 - x1));
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
}
