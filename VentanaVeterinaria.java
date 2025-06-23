import javax.swing.*;
import java.awt.event.*;

public class VentanaVeterinaria extends JFrame {

    private Veterinaria veterinaria;
    private JTextField campoTipo, campoId;
    private JTextArea areaCola;
    private JLabel etiquetaAtendiendo;

    public VentanaVeterinaria(Veterinaria v) {
        this.veterinaria = v;

        setTitle("Clínica Veterinaria");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblTipo = new JLabel("Tipo Mascota:");
        lblTipo.setBounds(20, 20, 100, 25);
        add(lblTipo);

        campoTipo = new JTextField();
        campoTipo.setBounds(130, 20, 150, 25);
        add(campoTipo);

        JLabel lblId = new JLabel("ID Mascota:");
        lblId.setBounds(20, 60, 100, 25);
        add(lblId);

        campoId = new JTextField();
        campoId.setBounds(130, 60, 150, 25);
        add(campoId);

        JButton btnRegistrar = new JButton("Registrar Llegada");
        btnRegistrar.setBounds(300, 20, 160, 30);
        add(btnRegistrar);

        JButton btnAtender = new JButton("Atender Siguiente");
        btnAtender.setBounds(300, 60, 160, 30);
        add(btnAtender);

        etiquetaAtendiendo = new JLabel("Atendiendo: Ninguna");
        etiquetaAtendiendo.setBounds(20, 100, 400, 25);
        add(etiquetaAtendiendo);

        areaCola = new JTextArea();
        areaCola.setBounds(20, 140, 440, 200);
        areaCola.setEditable(false);
        add(areaCola);

        // Acciones
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String tipo = campoTipo.getText();
                    int id = Integer.parseInt(campoId.getText());
                    veterinaria.registrarLlegada(tipo, id);
                    actualizarCola();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido. Debe ser un número.");
                }
            }
        });

        btnAtender.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Mascota m = veterinaria.atenderSiguiente();
                if (m != null) {
                    etiquetaAtendiendo.setText("Atendiendo: " + m.getTipo() + " (ID: " + m.getId() + ")");
                    actualizarCola();
                } else {
                    JOptionPane.showMessageDialog(null, "No hay mascotas en espera.");
                }
            }
        });

        actualizarCola();
        setVisible(true);
    }

    private void actualizarCola() {
        areaCola.setText("Cola de espera:\n" + veterinaria.verCola());
    }

    // Método main para lanzar
    public static void main(String[] args) {
        ArbolMascotas arbol = new ArbolMascotas(new Mascota("Perro", 1)); // dummy inicial
        Veterinaria vet = new Veterinaria(arbol);
        new VentanaVeterinaria(vet);
    }
}