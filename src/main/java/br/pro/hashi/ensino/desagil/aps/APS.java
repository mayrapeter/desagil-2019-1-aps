package br.pro.hashi.ensino.desagil.aps;

import br.pro.hashi.ensino.desagil.aps.model.AndGate;
import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.OrGate;
import br.pro.hashi.ensino.desagil.aps.model.XorGate;
import br.pro.hashi.ensino.desagil.aps.view.View;


import javax.swing.*;
import java.util.LinkedList;

public class APS {
    public static void main(String[] args) {
        LinkedList<Gate> model = new LinkedList<>();
        model.add(new OrGate());
        model.add(new AndGate());
        model.add(new XorGate());

        // Constrói a visão, que também é um controlador.
        // Juntar visão e controlador prejudica a coesão,
        // mas decidir fazê-lo para simplificar o código.
        View view = new View(model);

        // Todas as linhas abaixo já foram explicadas nos Desafios.
        // Além disso, não é importante entendê-las para estas APS.
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setContentPane(view);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);
        });

    }
}
