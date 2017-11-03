public class Number extends Block{
  public String type;
  public int num;
  public Blank() {
    super();
    type = "Number";
  }
  @Override
  public void click() {
    ic = true;
    s = "N";
  }

}
