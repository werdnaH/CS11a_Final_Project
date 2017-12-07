/**
This represents a block that is blank (not a bomb or a number).
*/
package CS11a_Final_Project;
public class Blank extends Block {
  public Blank() {
    super();
    type = "Blank";
  }
  @Override
  /**
  * Perferm the operation of clicking one block
  */
  public void click() {
    ic = true;
    s = "#";
  }
}
