public class Number extends Block{
  public int n;
  public Number() {
    super();
    n = 0;
    type = "Number";
  }
  @Override
  public void click() {
    ic = true;
  }

}
