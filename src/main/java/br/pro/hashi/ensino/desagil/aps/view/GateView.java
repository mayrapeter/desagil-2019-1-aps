package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// A classe JPanel representa uma das componentes mais
// simples da Swing. A função dela é simplesmente ser
// um contêiner para colocar outras componentes dentro.
// A razão de implementar ActionListener está mais abaixo.
public class GateView extends JPanel implements ActionListener {

    // A ideia é que essa componente gráfica represente
    // uma calculadora específica. Essa calculadora que
    // está sendo representada é guardada como atributo.
    private final Gate gate;

    // A classe JTextField representa um campo de texto.
    private final JCheckBox checkboxEntrada;
    private final JCheckBox checkboxEntrada1;
    private final JCheckBox checkboxSaida;

    private final Switch switchEntrada = new Switch();
    private final Switch switchEntrada1 = new Switch();


    public GateView(Gate gate) {


        this.gate = gate;

        // Nada de especial na construção dos campos.
        checkboxEntrada = new JCheckBox();
        checkboxEntrada1 = new JCheckBox();
        checkboxSaida = new JCheckBox();



        // A classe JLabel representa um rótulo, ou seja,
        // um texto não-editável que queremos colocar na
        // interface para identificar alguma coisa. Não
        // precisa ser atributo, pois não precisamos mais
        // mexer nesses objetos depois de criar e adicionar.
        JLabel entradaLabel = new JLabel("Entrada:");
        JLabel saidaLabel = new JLabel("Saída:");

        // Um JPanel tem um layout, ou seja, um padrão para
        // organizar as componentes dentro dele. A linha abaixo
        // estabelece um dos padrões mais simples: simplesmente
        // colocar uma componente debaixo da outra, alinhadas.
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        checkboxEntrada.addActionListener(this);
        checkboxEntrada1.addActionListener(this);
        checkboxSaida.setEnabled(false);

        // Colocamos todas componentes aqui no contêiner.

        if (gate.getInputSize() == 1){
            add(entradaLabel);
            add(checkboxEntrada);
            add(saidaLabel);
            add(checkboxSaida);


        }

        else {
            add(entradaLabel);
            add(checkboxEntrada);
            add(checkboxEntrada1);
            add(saidaLabel);
            add(checkboxSaida);

        }


        update();
    }

    private void update() {

        if (checkboxEntrada.isSelected()) {
            switchEntrada.turnOn();
            this.gate.connect(0, switchEntrada);
        }
        else {
            switchEntrada.turnOff();
        }

        // Ligamoes a switchB se a checkbox B está ligada.
        if (checkboxEntrada1.isSelected()) {
            switchEntrada1.turnOn();
            this.gate.connect(1, switchEntrada1);
        } else {
            switchEntrada1.turnOff();
        }



        checkboxSaida.setSelected(this.gate.read());

    }


    public void actionPerformed(ActionEvent event) {
        update();
    }
}


