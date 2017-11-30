public class Blank extends Block {
  public Blank() {
    super();
    type = "Blank";
  }
  @Override
  public void click() {
    new FlagSound();
    ic = true;
    s = "#";
  }
}
