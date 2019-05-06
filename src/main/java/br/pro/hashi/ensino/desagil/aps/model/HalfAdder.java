package br.pro.hashi.ensino.desagil.aps.model;

public class HalfAdder extends Gate {
    private final NandGate nandLeft;
    private final NandGate nandTop;
    private final NandGate nandCenterBottom;
    private final NandGate nandRightTop;
    private final NandGate nandRightBottom;


    public HalfAdder() {
        super("Half-Adder", 2, 2);

        nandLeft = new NandGate();

        nandTop = new NandGate();
        nandTop.connect(1, nandLeft);

        nandCenterBottom = new NandGate();
        nandCenterBottom.connect(0, nandLeft);

        nandRightTop = new NandGate();
        nandRightTop.connect(0, nandTop);
        nandRightTop.connect(1, nandCenterBottom);

        nandRightBottom = new NandGate();
        nandRightBottom.connect(0, nandLeft);
        nandRightBottom.connect(1, nandLeft);

    }


    @Override
    public boolean read(int outputPin) {
        if (outputPin < 0 || outputPin > 1) {
            throw new IndexOutOfBoundsException(outputPin);
        } else if (outputPin == 0) {
            return nandRightTop.read();
        } else {
            return nandRightBottom.read();
        }
    }


    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nandTop.connect(0, emitter);
                nandLeft.connect(0, emitter);
                break;
            case 1:
                nandLeft.connect(1, emitter);
                nandCenterBottom.connect(1, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
    }
}

