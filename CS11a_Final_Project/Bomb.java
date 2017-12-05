package CS11a_Final_Project;
public class Bomb extends Block {
  public Bomb(){
    super();
    s = "B";
    type = "Bomb";
  }
  @Override
  /**
  * Perferm the operation of clicking one block
  */
  public void click() {
    ic = true;
    s = "B";

  }
}
