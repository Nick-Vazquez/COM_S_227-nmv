package hw4;

import api.IStatefulComponent;
import api.Pin;

/**
 * Provides a base component class for all stateful components.
 * @author nmv
 */
public abstract class AbstractStatefulComponent extends AbstractComponent implements IStatefulComponent
{
    /** Whether the component is enabled or not */
    protected boolean enabled;

    /**
     * Creates an {@code AbstractStatefulComponent} with the number of inputs and outputs specified.
     * @param numInputs The number of inputs the component possesses.
     * @param numOutputs The number of outputs the component possesses.
     */
    protected AbstractStatefulComponent(int numInputs, int numOutputs)
    {
        super(numInputs, numOutputs);
        this.enabled = false;
    }

    /**
     * Returns the state of the component, enabled or not
     * @return true if the component is enabled, false otherwise
     */
    protected boolean isEnabled()
    {
        return this.enabled;
    }

    /**
     * Enables or disables updates to the internal state, if any, when processing the tick() operation.
     * @param enabled whether or not this component should be enabled
     */
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    /**
     * Clears the internal state, if any(sets to all zeros)
     */
    public void clear()
    {
        for (Pin output : outputs())
        {
            output.set(0);
        }
    }

    /**
     * Propagates all inputs to the outputs
     */
    @Override
    public void propagate()
    {

    }
}
