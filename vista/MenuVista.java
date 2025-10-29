package vista;

import modelo.Vinilo;

import java.util.List;
import java.util.Scanner;

public class MenuVista {
    private final Scanner scanner;

    public MenuVista() {
        this.scanner = new Scanner(System.in);
    }


    public int mostrarMenuYObtenerOpcion() {
        System.out.println("\n=============================================");
        System.out.println("     MANTENEDOR DE COLECCIÓN DE VINILOS");
        System.out.println("=============================================");
        System.out.println("1. Agregar un nuevo vinilo");
        System.out.println("2. Buscar vinilo por artista");
        System.out.println("3. Mostrar estadísticas de la colección");
        System.out.println("4. Ver colección completa");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String[] solicitarDatosVinilo() {
        System.out.print("Ingrese nombre del Artista: ");
        String artista = scanner.nextLine().trim();
        System.out.print("Ingrese nombre del Disco: ");
        String nombreDisco = scanner.nextLine().trim();
        System.out.print("Ingrese Año de lanzamiento: ");
        String anio = scanner.nextLine().trim();

        if (artista.isEmpty() || nombreDisco.isEmpty() || anio.isEmpty()) {
            mostrarMensajeError("Todos los campos son obligatorios.");
            return null;
        }

        return new String[]{artista, nombreDisco, anio};
    }

    public String solicitarArtistaBusqueda() {
        System.out.print("\nBuscar artista: ");
        return scanner.nextLine().trim();
    }

    public void mostrarMensajeExito(String mensaje) {
        System.out.println("\n[ÉXITO] " + mensaje);
    }


    public void mostrarMensajeError(String mensaje) {
        System.out.println("\n[ERROR] " + mensaje);
    }


    public void mostrarColeccion(List<Vinilo> vinilos) {
        if (vinilos.isEmpty()) {
            System.out.println("\n[INFO] La colección está vacía.");
            return;
        }
        System.out.println("\n--- Colección Completa ---");
        for (int i = 0; i < vinilos.size(); i++) {
            System.out.printf("Fila %d: %s\n", (i + 1), vinilos.get(i).toString());
        }
        System.out.println("--------------------------");
    }


    public void mostrarResultadosBusqueda(String artistaBuscado, List<Vinilo> resultados) {
        System.out.println("\nBuscar artista: " + artistaBuscado);
        if (resultados.isEmpty()) {
            System.out.println("El artista " + artistaBuscado + " NO está en la colección.");
        } else {
            // Verifica si el artista está (al menos un disco)
            boolean estaExacto = resultados.stream()
                    .anyMatch(v -> v.getArtista().equalsIgnoreCase(artistaBuscado));

            if (estaExacto) {
                System.out.println("El artista " + artistaBuscado + " SI está en la colección.");
            } else {
                System.out.println("Se encontraron coincidencias parciales para " + artistaBuscado + ":");
            }

            System.out.println("Discos encontrados:");
            for (Vinilo vinilo : resultados) {
                System.out.println("- " + vinilo.getNombreDisco() + " de " + vinilo.getArtista() + " (" + vinilo.getAnioLanzamiento() + ")");
            }
        }
    }


    public void mostrarEstadisticas(int capacidadMaxima, int totalVinilos, int disponibles) {
        System.out.println("\n-------------------------------------------------");
        System.out.println("Espacio máximo colección: " + capacidadMaxima);
        System.out.println("Hay un total de: " + totalVinilos + " vinilos en la colección.");
        System.out.println("Hay un total de: " + disponibles + " espacios disponibles en la colección.");
        System.out.println("-------------------------------------------------");
    }


    public void mostrarMensajeDespedida() {
        System.out.println("\n¡Gracias por usar el Mantenedor de Colección de Vinilos!");
        System.out.println("Aplicación terminada.");
    }


    public void mostrarOpcionInvalida() {
        System.out.println("\n[ADVERTENCIA] Opción no válida. Intente de nuevo.");
    }
}
