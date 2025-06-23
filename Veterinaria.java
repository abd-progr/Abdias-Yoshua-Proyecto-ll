import java.io.*;

// La clase Veterinaria  la cola y el árbol de mascotas
public class Veterinaria {
    private ListaEnlazada colaEspera;   
    private ArbolMascotas mascotas;                // árbol binario que implementa tu compañero
    private static final String ARCHIVO_COLA = "colaEspera.txt";

    // Constructor: recibe el ABB 
    public Veterinaria(ArbolMascotas arbolMascotas) {
        this.colaEspera = new ListaEnlazada();
        this.mascotas = arbolMascotas;
        cargarCola(); 
    }

    // 1) Registrar llegada de una mascota
    public void registrarLlegada(String tipo, int id) {
        // Busca en el árbol si ya existe
        Mascota m = mascotas.buscarMascota(id);
        if (m == null) {
            //  Si no existe, crea  la mascota y la inserta en el árbol
            m = new Mascota(tipo, id);
            mascotas.agregarMascota(m);
        }
        //  La agrega a la cola de espera
        colaEspera.agregar(m);
        //  Guarda el estado de la cola en el archivo
        guardarCola();
    }

    // 2) Atender a la siguiente mascota en la cola
    public Mascota atenderSiguiente() {
        if (colaEspera.estaVacia()) {
            return null; // no hay nadie en espera
        }
        Mascota m = colaEspera.sacar();
        guardarCola();  // actualizo el archivo tras atender
        return m;
    }

    // 3) Mostrar la cola actual como texto
    public String verCola() {
        return colaEspera.toString();
    }

    // Persistencia; guardar la cola en archivo
    private void guardarCola() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_COLA))) {
            // Recorre nodos de la lista y escrib "id,tipo" por línea
            NodoLista actual = colaEspera.getCabeza();  // usar getter 
            while (actual != null) {
                Mascota m = actual.getMascota();
                pw.println(m.getId() + "," + m.getTipo());
                actual = actual.getSiguiente();
            }
        } catch (IOException e) {
            // No detener la app, solo avisa por consola
            System.err.println("Error guardando cola: " + e.getMessage());
        }
    }

    // Persistencia; cargar la cola desde archivo 
    private void cargarCola() {
        File f = new File(ARCHIVO_COLA);
        if (!f.exists()) {
            return;  // si no existe el archivo, arranca con cola vacía
        }
      
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_COLA))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length != 2) continue;

                int id = Integer.parseInt(partes[0]);
                String tipo = partes[1];

                Mascota m = mascotas.buscarMascota(id);
                if (m == null) {
                    m = new Mascota(tipo, id);
                    mascotas.agregarMascota(m);
                }
                colaEspera.agregar(m);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error cargando cola: " + e.getMessage());
        }
    }
}