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
