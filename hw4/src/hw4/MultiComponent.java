package hw4;

import api.IComponent;

/**
 * Implementation of a specialized CompoundComponent in which all subcomponents are identical and have one output.
 * For each i less than n, inputs i * m up to (i + 1) * m are connected to the i-th subcomponent and the output of
 * that subcomponent is connected to output i.
 * @author nmv
 */
public class MultiComponent extends CompoundComponent
{
    /**
     * Constructs a MultiComponent with the given array of subcomponents. All elements of the array must be identical
     * and must have one output.
     * @param components Subcomponents for this multicomponent
     */
    public MultiComponent(IComponent[] components) {
        super(components.length * components[0].inputs().length, components.length);
        for (IComponent component : components)
        {
            addComponent(component);
        }

        for (int i = 0; i < components.length; i++)
        {
            int m = components[i].inputs().length;
            for (int j = i * m; j < (i + 1) * m; j++)
            {
                this.inputs()[j].connectTo(getComponents().get(i).inputs()[j - i * m]);
                //components[i].outputs()[0].connectTo(this.outputs()[0]);
            }
            this.getComponents().get(i).outputs()[0].connectTo(this.outputs()[i]);
        }
    }
}
