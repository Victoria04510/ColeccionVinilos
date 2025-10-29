package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ColeccionVinilos {
    private static final int MAX_CAPACIDAD = 100;
    private final List<Vinilo> coleccion;


    public ColeccionVinilos() {
        this.coleccion = new ArrayList<>();
        inicializarDatos();
    }


    public boolean agregarVinilo(Vinilo vinilo) {
        if (coleccion.size() < MAX_CAPACIDAD) {
            coleccion.add(vinilo);
            return true;
        }
        return false;
    }


    public List<Vinilo> buscarArtista(String artista) {
        String artistaLowerCase = artista.trim().toLowerCase();
        return coleccion.stream()
                .filter(v -> v.getArtista().toLowerCase().contains(artistaLowerCase))
                .collect(Collectors.toList());
    }


    public int contarVinilos() {
        return coleccion.size();
    }


    public int obtenerEspaciosDisponibles() {
        return MAX_CAPACIDAD - coleccion.size();
    }


    public List<Vinilo> obtenerTodos() {
        return List.copyOf(coleccion);
    }

    public int getMaxCapacidad() {
        return MAX_CAPACIDAD;
    }

    private void inicializarDatos() {
        agregarVinilo(new Vinilo("Iron Maiden", "Iron Maiden", 1980));
        agregarVinilo(new Vinilo("Iron Maiden", "Killers", 1981));
        agregarVinilo(new Vinilo("Iron Maiden", "The number of the beast", 1982));
        agregarVinilo(new Vinilo("AC-DC", "Back in black", 1980));
        agregarVinilo(new Vinilo("AC-DC", "Highway to Hell", 1979));
        agregarVinilo(new Vinilo("AC-DC", "Who made who", 1986));
        agregarVinilo(new Vinilo("Judas Priest", "British steel", 1980));
        agregarVinilo(new Vinilo("Judas Priest", "Painkiller", 1990));
        agregarVinilo(new Vinilo("Judas Priest", "Defenders of the faith", 1984));
        agregarVinilo(new Vinilo("Kiss", "Destroyer", 1976));
    }
}