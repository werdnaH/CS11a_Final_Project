public class Blank extends Block {
  public String type;
  public Blank() {
    super();
    type = "Blank";
  }
  @Override
  public void click() {
    ic = true;
    s = "N";
  }
}
