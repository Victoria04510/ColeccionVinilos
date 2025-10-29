package controlador;

import modelo.ColeccionVinilos;
import modelo.Vinilo;
import vista.MenuVista;

import java.util.List;

public class ColeccionControlador {
    private final ColeccionVinilos modelo;
    private final MenuVista vista;
    private boolean enEjecucion;

    public ColeccionControlador() {
        this.modelo = new ColeccionVinilos();
        this.vista = new MenuVista();
        this.enEjecucion = true;
    }

    public void iniciarAplicacion() {
        mostrarEstadisticas();
        buscarViniloPorArtistaEjemplo("AC-DC");
        mostrarColeccionCompleta();

        while (enEjecucion) {
            int opcion = vista.mostrarMenuYObtenerOpcion();
            ejecutarOpcion(opcion);
        }
    }

    private void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                agregarNuevoVinilo();
                break;
            case 2:
                buscarViniloPorArtistaInteractivo();
                break;
            case 3:
                mostrarEstadisticas();
                break;
            case 4:
                mostrarColeccionCompleta();
                break;
            case 5:
                finalizarAplicacion();
                break;
            default:
                vista.mostrarOpcionInvalida();
                break;
        }
    }

    private void agregarNuevoVinilo() {
        String[] datos = vista.solicitarDatosVinilo();
        if (datos == null) {
            return;
        }

        try {
            String artista = datos[0];
            String nombreDisco = datos[1];
            int añoLanzamiento = Integer.parseInt(datos[2]);

            Vinilo nuevoVinilo = new Vinilo(artista, nombreDisco, añoLanzamiento);

            if (modelo.agregarVinilo(nuevoVinilo)) {
                vista.mostrarMensajeExito("Vinilo de " + artista + " - " + nombreDisco + " agregado correctamente.");
            } else {
                vista.mostrarMensajeError("La colección ha alcanzado su capacidad máxima (" + modelo.getMaxCapacidad() + "). No se pudo agregar el vinilo.");
            }
        } catch (NumberFormatException e) {
            vista.mostrarMensajeError("El año de lanzamiento debe ser un número válido.");
        }
    }


    private void buscarViniloPorArtistaInteractivo() {
        String artista = vista.solicitarArtistaBusqueda();
        if (artista.isEmpty()) {
            vista.mostrarMensajeError("Debe ingresar un nombre de artista para buscar.");
            return;
        }
        List<Vinilo> resultados = modelo.buscarArtista(artista);
        vista.mostrarResultadosBusqueda(artista, resultados);
    }


    private void buscarViniloPorArtistaEjemplo(String artista) {
        System.out.println("\n--- Ejecutando ejemplo inicial (simulando el código procedural) ---");
        List<Vinilo> resultados = modelo.buscarArtista(artista);
        vista.mostrarResultadosBusqueda(artista, resultados);
        System.out.println("-------------------------------------------------------------------");
    }


    private void mostrarEstadisticas() {
        int total = modelo.contarVinilos();
        int disponibles = modelo.obtenerEspaciosDisponibles();
        int maxCapacidad = modelo.getMaxCapacidad();
        vista.mostrarEstadisticas(maxCapacidad, total, disponibles);
    }

    private void mostrarColeccionCompleta() {
        List<Vinilo> todosLosVinilos = modelo.obtenerTodos();
        vista.mostrarColeccion(todosLosVinilos);
    }

    private void finalizarAplicacion() {
        this.enEjecucion = false;
        vista.mostrarMensajeDespedida();
    }
}
