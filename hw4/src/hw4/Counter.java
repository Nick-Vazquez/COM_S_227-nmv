package hw4;

import api.Util;

/**
 * Implementation of binary counter with a fixed number of bits
 * @author nmv
 */
public class Counter extends AbstractStatefulComponent
{
    /**
     * Constructs a counter with the given number of bits.
     * @param bits Number of bits in this counter
     */
    public Counter(int bits)
    {
        super(0, bits);
        clear();
    }

    /**
     * Updates the internal state, if any, provided that the component is enabled.
     */
    public void tick()
    {
        if (!isEnabled()) return;

        int currentOutput = Util.toIntValue(outputs());
        String numBinary = "";

        for (int i = 0; i < outputs().length; i++)
        {
            numBinary = numBinary.concat("0");
        }

        numBinary = numBinary.concat(Integer.toBinaryString(currentOutput + 1));
        numBinary = numBinary.substring(numBinary.length() - outputs().length);

        for (int i = 0; i < numBinary.length(); i++)
        {
            outputs()[i].set(Util.stringToIntArray(numBinary)[i]);
        }
    }
}
