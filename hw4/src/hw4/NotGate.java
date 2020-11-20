package hw4;

/**
 * Implementation of a not-gate with one input.
 * @author nmv
 */
public class NotGate extends AbstractComponent {

    /**
     * Constructs a NotGate with one input.
     */
    public NotGate()
    {
        super(1, 1);
    }

    /**
     * Propagates inputs to outputs. Does nothing if all inputs are valid.
     */
    @Override
    public void propagate()
    {
        if (!inputsValid()) return;

        // Truth table for And gate. Wish java had
        if (this.inputs()[0].getValue() == 1) {
            this.outputs()[0].set(0);
        } else {
            this.outputs()[0].set(1);
        }
    }
}
