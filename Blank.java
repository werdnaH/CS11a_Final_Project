public class Blank extends Block {
  public Blank() {
    super();
    type = "Blank";
  }
  @Override
  public void click() {
    ic = true;
    s = "#";
  }
}
