import javax.swing.*;
import java.awt.event.*;

public class VentanaVeterinaria extends JFrame {
    private Veterinaria vet;
    private JTextField campoNombre, campoTipo;
    private JTextArea areaCola, areaArbol;

    public VentanaVeterinaria() {
        setTitle("Clínica Veterinaria");
        setSize(600, 500);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ArbolMascotas arbol = new ArbolMascotas();
        vet = new Veterinaria(arbol);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 20, 80, 25);
        add(lblNombre);
        campoNombre = new JTextField();
        campoNombre.setBounds(100, 20, 150, 25);
        add(campoNombre);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(270, 20, 50, 25);
        add(lblTipo);
        campoTipo = new JTextField();
        campoTipo.setBounds(320, 20, 150, 25);
        add(campoTipo);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(100, 60, 120, 30);
        add(btnRegistrar);

        JButton btnAtender = new JButton("Atender");
        btnAtender.setBounds(250, 60, 120, 30);
        add(btnAtender);

        areaCola = new JTextArea();
        areaCola.setBounds(20, 110, 260, 320);
        areaCola.setEditable(false);
        add(areaCola);

        areaArbol = new JTextArea();
        areaArbol.setBounds(300, 110, 260, 320);
        areaArbol.setEditable(false);
        add(areaArbol);

        btnRegistrar.addActionListener(e -> {
            String nombre = campoNombre.getText();
            String tipo = campoTipo.getText();
            if (!nombre.isEmpty() && !tipo.isEmpty()) {
                vet.registrar(nombre, tipo);
                actualizar();
            }
        });

        btnAtender.addActionListener(e -> {
            Mascota m = vet.atender();
            if (m != null) {
                JOptionPane.showMessageDialog(this, "Atendiendo a: " + m);
                actualizar();
            }
        });

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
