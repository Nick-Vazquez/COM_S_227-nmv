package api;

/**
 * Implementation of IPinListener that prints the affected component's 
 * state to the console.
 */
public class Probe implements IPinListener
{
  /**
   * Text identifier for this listener.
   */
  private String name;
  
  /**
   * Constructs a Probe with the given name.
   * @param givenName
   *   text identifier to use for this probe
   */
  public Probe(String givenName)
  {
    name = givenName;
  }
  
  @Override
  public void update(IComponent c)
  {
    String s = Util.toString(c.outputs());
    System.out.println(name + ": " + s);
  }
}
