package hw4;

import api.IComponent;

/**
 * Implementation of a standard full adder circuit. This version extends CompoundComponent and uses two instances
 * of HalfAdder as subcomponents.
 * @author nmv
 */
public class FullAdder extends CompoundComponent
{
    /**
     * Constructs a FullAdder.
     */
    public FullAdder()
    {
        super(3, 2);
        IComponent halfAdder = new HalfAdder();
        IComponent halfAdder2 = new HalfAdder();
        IComponent orGate = new OrGate();
        addComponent(halfAdder);
        addComponent(halfAdder2);
        addComponent(orGate);

        // wire inputs
        inputs()[0].connectTo(halfAdder.inputs()[0]);
        inputs()[1].connectTo(halfAdder.inputs()[1]);
        inputs()[2].connectTo(halfAdder2.inputs()[0]);

        // wiring to compute (A or B) and (not (A and B))
        halfAdder.outputs()[0].connectTo(halfAdder2.inputs()[1]);
        halfAdder.outputs()[1].connectTo(orGate.inputs()[0]);
        halfAdder2.outputs()[1].connectTo(orGate.inputs()[1]);

        // wire outputs:
        // output[0] is the "sum", which is the output of the second and-gate
        halfAdder2.outputs()[0].connectTo(outputs()[0]);

        // output[1] is the "carry", output of the first and-gate
        orGate.outputs()[0].connectTo(outputs()[1]);
    }
}
