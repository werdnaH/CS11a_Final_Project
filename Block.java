public abstract class Block {
  public boolean ic;// abbreviation of isclicked
  public String s;
  public Block(){
    ic = false;
    s = null;
  }
  public void setFlag() {
    s = "F";
  }
  public void setqm() {
    s = "?";
  } //set question mark
  public abstract void click();
}
