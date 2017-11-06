public abstract class Block {
  public boolean ic;// abbreviation of isclicked
  public boolean isf;
  public boolean isq;
  public String s;
  public String type;

  public Block(){
    isf = false;
    isq = false;
    ic = false;
    s = "N"; // for testing
    type = null;
  }
  public void setFlag() {
    if(ic == true){
      ;
    } else {
      s = "F";
      isf = true;
    }
  }
  public void setqm() {
    if(ic == true){
      ;
    } else {
      isq = true;
      s = "?";
    }
  } //set question mark
  public void df() {// stands for deflag
    isf = false;



  }
  public void dq() {// stands for dequesiton mark
    isq = false;



  }
  public abstract void click();
}
