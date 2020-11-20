package hw4;

import api.IComponent;
import api.Pin;

import java.util.ArrayList;

/**
 * Implementation of IComponent that contains other components. Clients are responsible for adding the subcomponents
 * using the addComponent() method and for creating connections between components.
 * @author nmv
 */
public class CompoundComponent extends AbstractComponent
{
    /** ArrayList of components that the CompoundComponent contains */
    private final ArrayList<IComponent> components;

    /**
     * Constructs a CompoundComponent with the given number of inputs and number of outputs. Initially the subcomponent list is empty.
     * @param numInputs Number of inputs
     * @param numOutputs Number of outputs
     */
    public CompoundComponent(int numInputs, int numOutputs)
    {
        super(numInputs, numOutputs);
        this.components = new ArrayList<>();
    }

    /**
     * Adds the given component to the list of subcomponents.
     * @param component Component to be added
     */
    public void addComponent(IComponent component)
    {
        this.components.add(component);
    }

    /**
     * Returns a reference to the list of subcomponents.
     * @return The list of subcomponents contained in the component.
     */
    public ArrayList<IComponent> getComponents()
    {
        return this.components;
    }

    /**
     * Propagates inputs to outputs. Does nothing if not all inputs are valid.
     */
    @Override
    public void propagate()
    {
        if (!this.inputsValid()) return;

        // Invalidate the Inputs and outputs on sub-components, output on this.
        this.invalidateOutputs();
        for (IComponent component : this.components) {
            component.invalidateInputs();
            component.invalidateOutputs();

            for (Pin pin : component.inputs()) { pin.invalidate(); }
        }

        // Set the input pins to their set values
        for (Pin pin : this.inputs()) {
            pin.set(pin.getValue());
        }

        // While this component's inputs, aren't valid, iterate
        while (!this.outputsValid()) {
            for (IComponent component : this.components) {
                for (Pin pin : component.inputs())
                {
                    if (pin.isValid())
                    {
                        pin.set(pin.getValue());
                    }
                }
                component.propagate();
            }
        }
    }
}
