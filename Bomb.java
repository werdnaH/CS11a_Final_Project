public class Bomb extends Block {
  public Bomb(){
    super();
    type = "Bomb";
  }
  @Override
  public void click() {
    ic = true;
    s = "B";

  }
}
