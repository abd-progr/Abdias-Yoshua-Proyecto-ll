import java.io.*;

public class Veterinaria {
    private ListaEnlazada cola;
    private ArbolMascotas arbol;
    private static final String ARCHIVO_COLA = "cola.txt";

    public Veterinaria(ArbolMascotas a) {
        this.arbol = a;
        this.cola = new ListaEnlazada();
        cargarCola();
    }

    public void registrar(String nombre, String tipo) {
        Mascota m = arbol.buscarPorNombre(nombre);
        if (m == null) {
            m = new Mascota(nombre, tipo);
            arbol.agregarMascota(m);
        }
        cola.agregar(m);
        guardarCola();
    }

    public Mascota atender() {
        Mascota m = cola.sacar();
        guardarCola();
        return m;
    }

    public String verCola() {
        return cola.toString();
    }

    private void guardarCola() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_COLA))) {
            NodoLista actual = cola.getCabeza();
            while (actual != null) {
                Mascota m = actual.getMascota();
                pw.println(m.getNombre() + "," + m.getTipo() + "," + m.getId());
                actual = actual.getSiguiente();
            }
        } catch (IOException e) {
            System.err.println("Error guardando cola: " + e.getMessage());
        }
    }

    private void cargarCola() {
        File f = new File(ARCHIVO_COLA);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String nombre = partes[0];
                    String tipo = partes[1];
                    int id = Integer.parseInt(partes[2]);

                    Mascota m = new Mascota(nombre, tipo, id);
                    arbol.agregarMascota(m);
                    cola.agregar(m);
                }
            }
        } catch (IOException e) {
            System.err.println("Error cargando cola: " + e.getMessage());
        }
    }

    public String verMascotasOrdenadas() {
        return arbol.recorridoInOrden();
    }

    public boolean eliminarPorId(int id) {
        return arbol.eliminar(id);
    }

    public Mascota buscarPorNombre(String nombre) {
        return arbol.buscarPorNombre(nombre);
    }
}
