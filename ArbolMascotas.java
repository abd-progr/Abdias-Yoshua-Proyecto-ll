/**
 * @brief Clase que representa un Árbol Binario de Búsqueda (ABB) para almacenar Mascotas.
 * 
 * Esta clase implementa la estructura de un ABB donde cada nodo contiene un objeto Mascota.
 */
public class ArbolMascotas {
    private NodoABB raiz;
/**
 * @brief Agrega una nueva mascota al árbol binario de búsqueda.
 * 
 * @param m La mascota a agregar (no puede ser null).
 * @post El árbol se mantiene ordenado.
 */
    public void agregarMascota(Mascota m) {
        raiz = agregarRecursivo(raiz, m);
            }
        
/**
 * @brief Método auxiliar recursivo para insertar una mascota en el árbol.
 * 
 * @param actual Nodo actual en la recursión (puede ser null).
 * @param m Mascota a insertar.
 * @return NodoABB El nodo modificado/subárbol resultante.
 * @details El algoritmo:
 * 1. Si el nodo actual es null, crea un nuevo nodo con la mascota.
 * 2. Si el ID es menor, inserta en el subárbol izquierdo.
 * 3. Si el ID es mayor, inserta en el subárbol derecho.
 * 4. Ignora si el ID ya existe (no permite duplicados).
 */ 
    private NodoABB agregarRecursivo(NodoABB actual, Mascota m) {
        if (actual == null) return new NodoABB(m);
        if (m.getId() < actual.getMascota().getId())
            actual.setIzquierda(agregarRecursivo(actual.getIzquierda(), m));
        else if (m.getId() > actual.getMascota().getId())
            actual.setDerecha(agregarRecursivo(actual.getDerecha(), m));
        return actual;
    }
/**
 * @brief Busca una mascota en el árbol por su ID.
 * 
 * @param id El ID de la mascota a buscar.
 */
    public Mascota buscarPorId(int id) {
        NodoABB nodo = buscarRecursivo(raiz, id);
        return nodo == null ? null : nodo.getMascota();
    }
/**
 * @brief Método auxiliar recursivo para buscar por ID.
 * 
 * @param nodo Nodo actual en la recursión.
 * @param id ID a buscar.
 * @return NodoABB El nodo que contiene la mascota, o null si no se encuentra.
 * @details Realiza una búsqueda binaria:
 * 1. Si el nodo es null, retorna null (no encontrado)
 * 2. Si el ID coincide, retorna el nodo actual
 * 3. Si el ID es menor, busca en el subárbol izquierdo
 * 4. Si el ID es mayor, busca en el subárbol derecho
 */
    private NodoABB buscarRecursivo(NodoABB nodo, int id) {
        if (nodo == null) return null;
        if (id == nodo.getMascota().getId()) return nodo;
        if (id < nodo.getMascota().getId())
            return buscarRecursivo(nodo.getIzquierda(), id);
        else
            return buscarRecursivo(nodo.getDerecha(), id);
    }
/**
 * @brief Busca una mascota por su nombre (búsqueda en profundidad).
 * 
 * @param nombre Nombre a buscar (comparación insensible a mayúsculas).
 * @return Mascota La primera mascota con ese nombre encontrada, o null.
 */
    public Mascota buscarPorNombre(String nombre) {
        return buscarNombreRecursivo(raiz, nombre);
    }

/**
 * @brief Método auxiliar recursivo para buscar por nombre.
 * 
 * @param nodo Nodo actual en la recursión.
 * @param nombre Nombre a buscar.
 * @return Mascota La mascota encontrada, o null.
 *  @details Realiza un recorrido:
 * 1. Busca primero en el subárbol izquierdo
 * 2. Luego verifica el nodo actual
 * 3. Finalmente busca en el subárbol derecho
 */
    private Mascota buscarNombreRecursivo(NodoABB nodo, String nombre) {
        if (nodo == null) return null;
        if (nodo.getMascota().getNombre().equalsIgnoreCase(nombre))
            return nodo.getMascota();
        Mascota izq = buscarNombreRecursivo(nodo.getIzquierda(), nombre);
        if (izq != null) return izq;
        return buscarNombreRecursivo(nodo.getDerecha(), nombre);
    }
/**
 * @brief Elimina una mascota del árbol por su ID.
 * 
 * @param id ID de la mascota a eliminar.
 * @return boolean true si la mascota existía y fue eliminada, false si no existía.
 */
    public boolean eliminar(int id) {
        if (buscarPorId(id) == null) return false;
        raiz = eliminarRecursivo(raiz, id);
        return true;
    }
/**
 * @brief Método auxiliar recursivo para eliminar un nodo.
 * 
 * @param nodo Nodo actual en la recursión.
 * @param id ID de la mascota a eliminar.
 * @return NodoABB La nueva raíz del subárbol después de la eliminación.
 * @details Implementa los tres casos de eliminación en BST:
 * 1. Nodo hoja: se elimina directamente
 * 2. Nodo con un hijo: se reemplaza por su hijo
 * 3. Nodo con dos hijos: se reemplaza por el mínimo del subárbol derecho
 * 
 * @note Utiliza el método encontrarMinimo() para el caso de dos hijos.
 */
    private NodoABB eliminarRecursivo(NodoABB nodo, int id) {
        if (nodo == null) return null;

        if (id < nodo.getMascota().getId())
            nodo.setIzquierda(eliminarRecursivo(nodo.getIzquierda(), id));
        else if (id > nodo.getMascota().getId())
            nodo.setDerecha(eliminarRecursivo(nodo.getDerecha(), id));
        else {
            if (nodo.getIzquierda() == null) return nodo.getDerecha();
            if (nodo.getDerecha() == null) return nodo.getIzquierda();

            Mascota menor = encontrarMinimo(nodo.getDerecha());
            nodo.setMascota(menor);
            nodo.setDerecha(eliminarRecursivo(nodo.getDerecha(), menor.getId()));
        }
        return nodo;
    }
/**
 * @brief Encuentra la mascota con ID mínimo en un subárbol.
 * 
 * @param nodo Raíz del subárbol donde buscar.
 * @return Mascota La mascota con el ID más pequeño en el subárbol. 
* @details Recorre siempre el hijo izquierdo hasta encontrar el mínimo.
 */ 
    private Mascota encontrarMinimo(NodoABB nodo) {
        while (nodo.getIzquierda() != null) {
            nodo = nodo.getIzquierda();
        }
        return nodo.getMascota();
    }
    
/**
 * @brief Realiza un recorrido in-order del árbol y devuelve un String con las mascotas.
 */
    public String recorridoEnOrden() {
        StringBuilder sb = new StringBuilder();
        enOrdenRecursivo(raiz, sb);
        return sb.toString();
    }

/**
 * @brief Método auxiliar recursivo para recorrido in-order.
 * 
 * @param nodo Nodo actual en la recursión.
 * @param sb StringBuilder que acumula el resultado.
 */
    private void enOrdenRecursivo(NodoABB nodo, StringBuilder sb) {
        if (nodo != null) {
            enOrdenRecursivo(nodo.getIzquierda(), sb);
            sb.append(nodo.getMascota()).append("\n");
            enOrdenRecursivo(nodo.getDerecha(), sb);
        }
    }
}
