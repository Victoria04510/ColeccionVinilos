package modelo;

import java.util.Objects;
public class Vinilo {
    private final String artista;
    private final String nombreDisco;
    private final int añoLanzamiento;

    public Vinilo(String artista, String nombreDisco, int añoLanzamiento) {
        this.artista = artista;
        this.nombreDisco = nombreDisco;
        this.añoLanzamiento = añoLanzamiento;
    }

    public String getArtista() {
        return artista;
    }

    public String getNombreDisco() {
        return nombreDisco;
    }

    public int getAnioLanzamiento() {
        return añoLanzamiento;
    }

    @Override
    public String toString() {
        return artista + " - " + nombreDisco + " - " + añoLanzamiento;
    }
}
