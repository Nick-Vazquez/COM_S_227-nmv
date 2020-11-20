package hw4;

import api.IComponent;
import api.Pin;

/**
 * Abstract supertype with common code for all components
 * @author nmv
 */
public abstract class AbstractComponent implements IComponent {
    private final Pin[] inputs;
    private final Pin[] outputs;

    /**
     * Creates a new instance of an AbstractComponent with {@code numInputs}
     * and {@code numOutputs}. This class handles all of the base component
     * functions that all components inherit.
     *
     * @param numInputs The number of inputs the component possesses
     * @param numOutputs The number of inputs the component possesses
     */
    protected AbstractComponent(int numInputs, int numOutputs)
    {
        this.inputs = new Pin[numInputs];
        this.outputs = new Pin[numOutputs];

        for (int i = 0; i < this.inputs.length; i++)
        {
            this.inputs[i] = new Pin(this);
        }

        for (int i = 0; i < this.outputs.length; i++)
        {
            this.outputs[i] = new Pin(this);
        }
    }

    /**
     * Returns the array of Pins representing this component's inputs.
     * @return Current component's input pin objects
     */
    @Override
    public Pin[] inputs()
    {
        return this.inputs;
    }

    /**
     * Returns the array of Pins representing this component's outputs.
     * @return Current component's output pin objects
     */
    @Override
    public Pin[] outputs()
    {
        return this.outputs;
    }

    /**
     * Returns whether all inputs are valid
     * @return true if all inputs are valid, false otherwise
     */
    @Override
    public boolean inputsValid()
    {
        for (Pin pin : this.inputs)
        {
            if (!pin.isValid()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns whether all outputs are valid
     * @return true if all outputs are valid, false otherwise
     */
    @Override
    public boolean outputsValid()
    {
        for (Pin pin : this.outputs)
        {
            if (!pin.isValid())
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Changes the state of all inputs to invalid
     */
    @Override
    public void invalidateInputs()
    {
        for (Pin pin : this.inputs)
        {
            pin.invalidate();
        }
    }

    /**
     * Changes the state of all outputs to invalid
     */
    @Override
    public void invalidateOutputs()
    {
        for (Pin pin : this.outputs)
        {
            pin.invalidate();
        }
    }
}