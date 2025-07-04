import javax.swing.*;
import java.awt.event.*;
/**
 * @brief Clase que representa la interfaz gráfica del sistema de veterinaria.
 * 
 * Proporciona una interfaz de usuario para interactuar con el sistema:
 * - Registro de nuevas mascotas
 * - Visualización de cola de atención
 * - Visualización de mascotas registradas
 * - Operaciones de búsqueda y eliminación
 */
public class VentanaVeterinaria extends JFrame {
    private Veterinaria vet;
    private JTextField campoNombre, campoTipo, campoEliminarId;
    private JTextArea areaCola, areaArbol;

    public VentanaVeterinaria() {
        setTitle("Clínica Veterinaria");
        setSize(650, 550);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

/** 
 * @brief Inicializamos el modelo.
 * 
 */
        ArbolMascotas arbol = new ArbolMascotas();
        vet = new Veterinaria(arbol);

        /** 
 * @brief --- Campos de registro ---
 */
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 20, 60, 25);
        add(lblNombre);
        campoNombre = new JTextField();
        campoNombre.setBounds(90, 20, 150, 25);
        add(campoNombre);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(260, 20, 40, 25);
        add(lblTipo);
        campoTipo = new JTextField();
        campoTipo.setBounds(300, 20, 150, 25);
        add(campoTipo);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(470, 20, 120, 30);
        add(btnRegistrar);

     /** 
 * @brief --- Botón atender ---
 */
        JButton btnAtender = new JButton("Atender");
        btnAtender.setBounds(470, 60, 120, 30);
        add(btnAtender);

     /** 
 * @brief --- Campo y botón para eliminar por ID ---
 */
        JLabel lblEliminar = new JLabel("Eliminar ID:");
        lblEliminar.setBounds(20, 60, 80, 25);
        add(lblEliminar);
        campoEliminarId = new JTextField();
        campoEliminarId.setBounds(110, 60, 100, 25);
        add(campoEliminarId);
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(220, 60, 100, 30);
        add(btnEliminar);

        /** 
 * @brief --- area de texto para cola y árbol ---
 */
        areaCola = new JTextArea();
        areaCola.setBounds(20, 150, 300, 380);
        areaCola.setEditable(false);
        add(areaCola);

        areaArbol = new JTextArea();
        areaArbol.setBounds(340, 150, 280, 380);
        areaArbol.setEditable(false);
        add(areaArbol);

        /** 
 * @brief --- Listeners ---
 */
        btnRegistrar.addActionListener(e -> {
            String nombre = campoNombre.getText().trim();
            String tipo    = campoTipo.getText().trim();
            if (nombre.isEmpty() || tipo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar nombre y tipo.");
                return;
            }
            vet.registrar(nombre, tipo);
            campoNombre.setText("");
            campoTipo.setText("");
            actualizar();
        });

        btnAtender.addActionListener(e -> {
            Mascota m = vet.atender();
            if (m != null) {
                JOptionPane.showMessageDialog(this, "Atendiendo a:\n" + m);
            } else {
                JOptionPane.showMessageDialog(this, "No hay mascotas en cola.");
            }
            actualizar();
        });

        btnEliminar.addActionListener(e -> {
            String texto = campoEliminarId.getText().trim();
            if (texto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingresa un ID para eliminar.");
                return;
            }
            try {
                int id = Integer.parseInt(texto);
                boolean ok = vet.eliminarMascotaPorId(id);
                if (ok) {
                    JOptionPane.showMessageDialog(this, "Mascota con ID " + id + " eliminada.");
                } else {
                    JOptionPane.showMessageDialog(this, "No existe mascota con ID " + id + ".");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido. Debe ser un número.");
            }
            campoEliminarId.setText("");
            actualizar();
        });
/** 
         * @brief --- Botón buscar por nombre ---
         */
        JPanel panelBuscar = new JPanel(null);
        panelBuscar.setBounds(340, 100, 280, 60);
        panelBuscar.setBorder(BorderFactory.createTitledBorder("Buscar mascota por nombre"));

        JLabel lblBuscar = new JLabel("Nombre:");
        lblBuscar.setBounds(10, 20, 70, 25);
        panelBuscar.add(lblBuscar);

        JTextField campoBuscarNombre = new JTextField();
        campoBuscarNombre.setBounds(80, 20, 100, 30);
        panelBuscar.add(campoBuscarNombre);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(190, 18, 80, 35);
        panelBuscar.add(btnBuscar);

        add(panelBuscar);

        btnBuscar.addActionListener(e -> {
            String nombre = campoBuscarNombre.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingresa un nombre para buscar.");
                return;
            }
            Mascota m = vet.buscarPorNombre(nombre);
            if (m != null) {
                JOptionPane.showMessageDialog(this, "Mascota encontrada:\n" + m);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró mascota con ese nombre.");
            }
            campoBuscarNombre.setText("");
        });

        /** 
 * @brief Inicializamos las vistas
 */
        actualizar();
        setVisible(true);
    }

    private void actualizar() {
        areaCola.setText("Cola de espera:\n" + vet.verCola());
        areaArbol.setText("Mascotas (ordenadas por ID):\n" + vet.verMascotasOrdenadas());
    }

    public static void main(String[] args) {
        new VentanaVeterinaria();
    }
}
