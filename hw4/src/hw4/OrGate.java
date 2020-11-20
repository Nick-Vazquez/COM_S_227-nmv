package hw4;

/**
 * Implementation of an or-gate with two inputs.
 * @author nmv
 */
public class OrGate extends AbstractComponent
{
    /**
     * Constructs an OrGate with two inputs.
     */
    public OrGate()
    {
        super(2, 1);
    }

    /**
     * Propagates inputs to outputs. Does nothing if not all inputs are valid.
     */
    @Override
    public void propagate()
    {
        if (!inputsValid()) return;

        if (inputs()[0].getValue() == 1 || inputs()[1].getValue() == 1) {
            outputs()[0].set(1);
        } else {
            outputs()[0].set(0);
        }
    }
}
