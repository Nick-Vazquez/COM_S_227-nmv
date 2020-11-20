package hw4;

import api.Util;

/**
 * Implementation of a single-output multiplexer.
 * @author nmv
 */
public class Multiplexer extends AbstractComponent
{
    /** Number of selector pins for the component */
    private final int numSelectors;

    /**
     * Constructs a multiplexer that uses k bits to select from one of 2^k inputs.
     * The total number of inputs is 2^k+k, where the last k inputs are interpreted
     * as a binary number to select one of the first 2^k inputs to be the output
     * value.
     *
     * @param k Number of selection bits
     */
    public Multiplexer(int k)
    {
        super((int) Math.pow(2, k) + k, 1);
        this.numSelectors = k;
    }

    /** Propagates inputs to outputs. Does nothing if not all inputs are valid */
    @Override
    public void propagate()
    {
        if (!inputsValid()) return;

        int plexIndex = Util.toIntValue(this.inputs(), this.inputs().length - this.numSelectors, this.inputs().length - 1);
        this.outputs()[0].set(this.inputs()[plexIndex].getValue());
    }
}
