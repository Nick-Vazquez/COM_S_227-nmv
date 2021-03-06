package api;

/**
 * An ExternalValue is a component representing a  value that 
 * might be set by setting switches or reading
 * external data.  It is a "stateful" component whose outputs
 * are always valid.
 */
public class ExternalValue implements IStatefulComponent
{
  /**
   * Outputs for this component.
   */
  private Pin[] outputs;
  
  /**
   * Inputs for this component (always empty).
   */
  private Pin[] inputs;
  
  /**
   * Constructs an ExternalValue of the given number of bits.
   * @param size
   *   number of bits for the value represented by this component
   */
  public ExternalValue(int size)
  {
    outputs = new Pin[size];
    for (int i = 0; i < size; i += 1)
    {
      outputs[i] = new Pin(this);
    }
    inputs = new Pin[0];
  }
  
  /**
   * Sets the state of this component from the given string of 
   * '0' and '1' characters, where index 0 in this object's state 
   * corresponds to the rightmost character of the string.  
   * If the string contains a character other than '0' or '1' in 
   * some position, the corresponding bit in this object's state
   * will not be modified. 
   * @param values
   *   string of 0's and 1's
   */
  public void setValues(String values)
  {
    int[] valueArray = Util.stringToIntArray(values);
    setValues(valueArray);
  }
  
  /**
   * Sets the state of this component from the given integer,
   * interpreted as a binary string. 
   * @param value
   */
  public void setValues(int value)
  {
    String temp = Integer.toBinaryString(value);
    setValues(temp);
  }
  
  /**
   * Sets the state of this component from the given array
   * of integers. If the array contains any number other than 0 or 1 in
   * some position, the corresponding bit in this object's state
   * will not be modified.
   * @param values
   *   int array of 0's and 
   */
  public void setValues(int[] values)
  {
    int max = Math.max(values.length, outputs.length);
    for (int i = 0; i < max; i += 1)
    {
      if (values[i] == 0 || values[i] == 1)
      {
        outputs[i].set(values[i]);
      }
    }
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
  public boolean inputsValid()
  {
    return true;
  }

  @Override
  public boolean outputsValid()
  {
    return true;
  }

  @Override
  public void invalidateInputs()
  {
    // do nothing   
  }

  @Override
  public void invalidateOutputs()
  {
    // do nothing
  }

  @Override
  public void propagate()
  {
    // basically just validate the outputs so
    // they propagate to connected Endpoints
    for (Pin e : outputs)
    {
      e.set(e.getValue());
    }
  }

  @Override
  public void tick()
  {
    // do nothing
  }
  
  @Override
  public void setEnabled(boolean enabled)
  {
    // do nothing
  }
  
  @Override
  public void clear()
  {
    for (Pin e : outputs)
    {
      e.set(0);
    }
  }
}
