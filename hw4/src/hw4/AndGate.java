package hw4;

/**
 * A class to represent a software And gate.
 * Truth Table:
 * +---+---+---+
 * | A | B | X |
 * |---|---|---|
 * | 0 | 0 | 0 |
 * | 0 | 1 | 0 |
 * | 1 | 0 | 0 |
 * | 1 | 1 | 1 |
 * +---+---+---+
 * @author nmv
 */
public class AndGate extends AbstractComponent {

    /**
     * Implementation of an and-gate with two inputs.
     * Constructs an and gate with 2 inputs and 1 output
     */
    public AndGate()
    {
        super(2, 1);
    }

    /**
     * Propagates inputs to outputs. Does nothing if not all inputs are valid
     */
    @Override
    public void propagate()
    {
        if (!inputsValid()) return;

        // Truth table for And gate. Wish java had list comprehensions
        if (this.inputs()[0].getValue() == 1 && this.inputs()[1].getValue() == 1) {
            this.outputs()[0].set(1);
        } else {
            this.outputs()[0].set(0);
        }
    }
}
