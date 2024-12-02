import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class primerproyecto extends JFrame implements ActionListener {
    private JTextField pantalla;
    private String operador = "";
    private double numero1 = 0;
    private double numero2 = 0;
    private boolean nuevoNumero = true;

    public primerproyecto() {
        setTitle("Calculadora Colorida");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Pantalla
        pantalla = new JTextField();
        pantalla.setEditable(false);
        pantalla.setBackground(new Color(230, 230, 250)); // Fondo lavanda
        pantalla.setFont(new Font("Arial", Font.BOLD, 24));
        pantalla.setHorizontalAlignment(JTextField.RIGHT);
        pantalla.setForeground(new Color(25, 25, 112)); // Texto azul oscuro
        add(pantalla, BorderLayout.NORTH);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 4));

        // Agregar botones (números, operadores, etc.)
        String[] botones = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+"};
        for (String boton : botones) {
            JButton b = new JButton(boton);
            b.addActionListener(this);

            // Personalizar colores y fuente
            b.setFont(new Font("Arial", Font.BOLD, 20));
            if (boton.equals("=")) {
                b.setBackground(new Color(236, 3, 3)); // Rojo brillante para "="
                b.setForeground(Color.WHITE);
            } else if (boton.matches("[0-9]")) {
                b.setBackground(new Color(96, 105, 243)); // Azul claro para números
                b.setForeground(Color.BLACK);
            } else {
                b.setBackground(new Color(96, 105, 243)); // Dorado para operadores
                b.setForeground(Color.BLACK);
            }

            // Quitar el borde para un estilo más moderno
            b.setFocusPainted(false);
            b.setBorderPainted(false);
            b.setOpaque(true);

            panelBotones.add(b);
        }

        add(panelBotones, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        // Si se presiona un número o el punto decimal
        if (Character.isDigit(comando.charAt(0)) || comando.equals(".")) {
            if (nuevoNumero) {
                pantalla.setText(comando);
                nuevoNumero = false;
            } else {
                pantalla.setText(pantalla.getText() + comando);
            }
        }
        // Si se presiona un operador
        else if (comando.matches("[+\\-*/]")) {
            numero1 = Double.parseDouble(pantalla.getText());
            operador = comando;
            nuevoNumero = true; // Permitir ingresar el segundo número
        }
        // Si se presiona el botón "="
        else if (comando.equals("=")) {
            numero2 = Double.parseDouble(pantalla.getText());
            double resultado = calcular(numero1, numero2);
            pantalla.setText(String.valueOf(resultado));
            operador = "";
            nuevoNumero = true; // Reiniciar para un nuevo cálculo
        }
    }

    private double calcular(double numero1, double numero2) {
        switch (operador) {
            case "+":
                return numero1 + numero2;
            case "-":
                return numero1 - numero2;
            case "*":
                return numero1 * numero2;
            case "/":
                if (numero2 != 0) {
                    return numero1 / numero2;
                } else {
                    JOptionPane.showMessageDialog(this, "Error: División por cero");
                    return 0;
                }
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        // Cambiar el aspecto de la interfaz para que se vea más moderna
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new primerproyecto();
    }
}
