package sample;

import api.Pin;
import api.IComponent;

/**
 * Concrete implementation of the IComponent interface representing
 * an and-gate with two inputs and one output.
 */
public class SampleAndGate implements IComponent
{
  /**
   * Outputs for this component.
   */
  private Pin[] outputs;
  
  /**
   * Inputs for this component.
   */
  private Pin[] inputs;
  
  /**
   * Constructs an and-gate.
   */
  public SampleAndGate()
  {
    inputs = new Pin[2];
    for (int i = 0; i < inputs.length; i += 1)
    {
      inputs[i] = new Pin(this);
    }

    outputs = new Pin[1];
    outputs[0] = new Pin(this);
  }
  
  @Override
  public void invalidateInputs()
  {
    for (Pin e : inputs) e.invalidate();
  }
  
  @Override
  public void invalidateOutputs()
  {
    for (Pin e : outputs) e.invalidate();
  }
  
  @Override
  public boolean inputsValid()
  {
    for (Pin e : inputs)
    {
      if (!e.isValid()) return false;
    }
    return true;
  }
  
  @Override
  public boolean outputsValid()
  {
    for (Pin e : outputs)
    {
      if (!e.isValid()) return false;
    }
    return true;
  }
  
  @Override
  public Pin[] inputs()
  {
    return inputs;
  }

  @Override
  public Pin[] outputs()
  {
    return outputs;
  }
  
  @Override
  public void propagate()
  {
    if (inputsValid())
    {
      int newValue = 0;
      if (inputs()[0].getValue() == 1 && inputs()[1].getValue() == 1)
      {
        newValue = 1;
      }
      outputs()[0].set(newValue);
    }
  }
}
