/**
 * @brief Clase que representa una mascota con identificador único, tipo y nombre.
 */

public class Mascota {
    private static final int RANGO_MAX = 100;
    private static int[] idsGenerados = new int[RANGO_MAX]; // 0 si libre, 1 si usado

    private int id;
    private String tipo;
    private String nombre;

    public Mascota(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.id = generarIdUnico();
    }

    public Mascota(String nombre, String tipo, int id) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.id = id;
        if (id >= 0 && id < RANGO_MAX) {
            idsGenerados[id] = 1;
        }
    }
    /**
 * @brief Genera un ID único aleatorio dentro del rango predefinido (0 a RANGO_MAX - 1).
 * 
 * El método evita colisiones marcando los IDs ya utilizados en el array estático `idsGenerados`.
 * Utiliza un bucle do-while para garantizar que el ID generado no esté en uso.
 *
 * @return int ID único generado (entre 0 y RANGO_MAX - 1).
 */
    private int generarIdUnico() {
        int nuevoId;
        do {
            nuevoId = (int) (Math.random() * RANGO_MAX);
        } while (idsGenerados[nuevoId] == 1);
        idsGenerados[nuevoId] = 1;
        return nuevoId;
    }

    public int getId() { return id; }
    public String getTipo() { return tipo; }
    public String getNombre() { return nombre; }

    public String toString() {
        return "Nombre: " + nombre + " | Tipo: " + tipo + " | ID: " + id;
    }
}
