package CS11a_Final_Project;
public abstract class Block {
  public boolean ic;// abbreviation of isclicked
  public boolean isf;
  public boolean isq;
  public String s;
  public String type;
  /**
  * Constructor
  */
  public Block(){
    isf = false;
    isq = false;
    ic = false;
    s = "N"; // for testing
    type = null;
  }
  /**
  * Perferm the operation of setting flags
  */
  public void setFlag() {
    new FlagSound();
    if(ic == true){
      ;
    } else if(isf == true) {
      isf = false;
      switch (this.type) {
        case "Bomb":
        this.s = "B";
        break;
        case "Number":
        this.s = String.valueOf(((Number)this).n);
        case "Blank":
        this.s = "#";
      }
    } else {
      s = "F";
      isf = true;
    }
  }
  /**
  * Perferm the operation of setting question marks
  */
  public void setqm() {
    if(ic == true){
      ;
    } else if(isq == true) {
      isq = false;
      switch (this.type) {
        case "Bomb":
        this.s = "B";
        break;
        case "Number":
        this.s = String.valueOf(((Number)this).n);
        case "Blank":
        this.s = "#";
      }
    } else {
      isq = true;
      s = "?";
    }
  } //set question mark
  public abstract void click();
}
