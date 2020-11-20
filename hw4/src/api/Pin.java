package api;

import java.util.ArrayList;

/**
 * A Pin represents a connection point in a digital 
 * circuit.  Each Pin has a value of 0 or 1, and may be
 * valid or invalid.  An Pin may be connected to 
 * zero or more other Pins, in which case setting its
 * value will also set the values of connected Pins.
 */
public class Pin
{
  /**
   * The value stored in this Pin.
   */
  private int value;
  
  /**
   * Represents whether or not this Pin is valid.
   */
  private boolean valid;
  
  /**
   * List of destination Pins to which this one is connected.
   */
  private ArrayList<Pin> connections;
  
  /**
   * List of listeners to be notified when this Pin changes.
   */
  private ArrayList<IPinListener> listeners; 
  
  /**
   * Component object to which this Pin belongs.
   */
  private IComponent parent;
  
  /**
   * Constructs a Pin with the given parent component.
   * Initially the state is invalid and the value is zero.
   * @param parent
   *   the component to which this pin belongs
   */
  public Pin(IComponent parent)
  {
    connections = new ArrayList<Pin>();
    listeners = new ArrayList<IPinListener>();
    this.parent = parent;
  }
  
  /**
   * Returns a reference to the component to which this
   * Pin belongs
   * @return
   *   reference to the parent component
   */
  public IComponent getParent()
  {
    return parent;
  }
  
  /**
   * Add a connection from this Pin to the given
   * destination.
   * @param destination
   *   the Pin that is to be connected to this one
   */
  public void connectTo(Pin destination)
  {
    connections.add(destination);
  }
  
  /**
   * Returns the list of Pins connected to this one.
   * @return
   *   list of Pins connected to this one
   */
  public ArrayList<Pin> getConnections()
  {
    return connections;
  }
  
  /**
   * Returns the value stored in this pin.
   * @return
   *   value stored in this pin
   */
  public int getValue()
  {
    return value;
  }
  
  /**
   * Sets this Pin to the invalid state.  Note that
   * this method does not modify connected Pins.
   */
  public void invalidate()
  {
    valid = false;
    notifyListeners();
  }
  
  /**
   * Returns whether this Pin is currently valid.
   * @return
   *   whether this Pin is valid
   */
  public boolean isValid()
  {
    return valid;
  }
  
  /**
   * Sets this Pin to have the given value (0 or 1) and changes the
   * state to valid.  All connected Pins are also set with
   * the same value.
   * @param givenValue
   *   value to be set
   */
  public void set(int givenValue)
  {
    value = givenValue;
    valid = true;
    
    for (Pin e : connections)
    {
      e.set(givenValue);
    }
    notifyListeners();
  }
  
  /**
   * Returns a String representation of this Pin's 
   * value as either "0" or "1", or "-" if the state is invalid.
   * @return
   *   a string representation of the value
   */
  public String toString()
  {
    if (valid)
    {
      return "" + value;
    }
    else
    {
      return "-";
    }
  }
  
  /**
   * Adds the given listener to this Pin's list
   * of listeners.
   * @param listener
   *   the listener to be added
   */
  public void addListener(IPinListener listener)
  {
    listeners.add(listener);
  }
  
  /**
   * Helper method notifies all listeners.
   */
  private void notifyListeners()
  {
    for (IPinListener listener : listeners)
    {
      listener.update(parent);
    }
  }
}
