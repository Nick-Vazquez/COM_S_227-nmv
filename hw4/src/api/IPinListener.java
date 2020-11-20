package api;

/**
 * Callback interface for notifying applications when the 
 * outputs of a component change.
 */
public interface IPinListener
{
  /**
   * Invoked by a Pin when its value changes.  The argument 
   * is the component to which the Pin belongs.
   * @param c
   *   parent component for the Pin invoking this method
   */
  public void update(IComponent c);
}
