package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;
import br.pro.hashi.ensino.desagil.aps.model.Light;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class GateView extends FixedPanel implements ItemListener, MouseListener {
    private final Switch[] switches;
    private final Gate gate;

    private final JCheckBox[] inputBoxes;
    private final JCheckBox outputBox;

    private final Image image;
    private final Light light;
    private Color color;

    private int R = 255;
    private int G = 0;
    private int B = 0;



    public GateView(Gate gate) {
        super(245, 346);

        this.gate = gate;

        int inputSize = gate.getInputSize();

        switches = new Switch[inputSize];
        inputBoxes = new JCheckBox[inputSize];

        light = new Light();
        light.connect(0, gate);



        for (int i = 0; i < inputSize; i++) {
            switches[i] = new Switch();
            inputBoxes[i] = new JCheckBox();

            gate.connect(i, switches[i]);
        }

        if (inputBoxes.length == 1){
            add(inputBoxes[0],5,45,20,20);
        }
        else{
            int y = 22;
            for (JCheckBox inputBox : inputBoxes) {
                add(inputBox,10,y,20,20);
                y += 45;
            }
        }
        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

        //Fontes das imagens:
        //http://baudaeletronica.blogspot.com/2013/06/porta-or-porta-ou.html
        //https://commons.wikimedia.org/wiki/File:XOR_ANSI.svg
        //https://en.wikibooks.org/wiki/A-level_Computing/WJEC_(Eduqas)/Component_1/Logical_operations
        //https://en.wikipedia.org/wiki/File:NAND_ANSI.svg

        outputBox = new JCheckBox();

        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        for (JCheckBox inputBox : inputBoxes) {
            add(inputBox);
        }

        //add(outputBox);

        for (JCheckBox inputBox : inputBoxes) {
            inputBox.addItemListener(this);
        }

        outputBox.setEnabled(false);

        addMouseListener(this);

        update();
    }

    private void update() {
        for (int i = 0; i < gate.getInputSize(); i++) {
            if (inputBoxes[i].isSelected()) {
                switches[i].turnOn();
            } else {
                switches[i].turnOff();
            }
        }

        repaint();

        light.setR(R);
        light.setG(G);
        light.setB(B);

        color = new Color(light.getR(), light.getG(), light.getB());
        repaint();
    }

    @Override
    public void itemStateChanged(ItemEvent event) {

        update();
    }


    @Override
    public void mouseClicked(MouseEvent event) {

        int x = event.getX();
        int y = event.getY();


        if (x >= 200 && x <= 215 && y >= 47 && y <= 52) {

            color = JColorChooser.showDialog(this, null, new Color(light.getR(), light.getG(), light.getB()));
            if (color != null) {
                light.setB(color.getBlue());
                light.setG(color.getGreen());
                light.setR(color.getRed());
            }

            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {

    }

    @Override
    public void mouseReleased(MouseEvent event) {

    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }

    @Override
    public void mouseExited(MouseEvent event) {

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.drawImage(image, 0, 0, 220, 110, this);

        g.setColor(new Color(light.getR(), light.getG(), light.getB()));
        g.fillOval(200, 47, 15, 15);

        getToolkit().sync();
    }
}

