public class Bomb extends Block {
  public String type;
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
