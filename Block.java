public abstract class Block {
  public boolean ic;// abbreviation of isclicked
  public String s;
  public String type;
  public Block(){
    ic = false;
    s = "N"; // for testing
    type = null;
  }
  public void setFlag() {
    if(ic == true){
      ;
    } else {
      s = "F";
    }
  }
  public void setqm() {
    if(ic == true){
      ;
    } else {
      s = "?";
    }
  } //set question mark
  public abstract void click();
}
