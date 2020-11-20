package hw4;

/**
 * Implementation of a register with an internal "state" consisting of a fixed number of bits. If enabled, the
 * tick() method causes the input bits to be copied to the state (provided that the inputs are valid).
 * Outputs are always valid and equal to the state.
 * @author nmv
 */
public class Register extends AbstractStatefulComponent
{
    /**
     * Constructs a register whose state consists of the given number of bits.
     * @param size number of bits in the register
     */
    public Register(int size) {
        super(size, size);
    }

    /**
     * Updates the internal state, if any, provided that the component is enabled.
     */
    public void tick()
    {
        if (!outputsValid() || !isEnabled()) return;

        for (int i = 0; i < this.inputs().length; i++)
        {
            this.outputs()[i].set(inputs()[i].getValue());
        }
    }
}
