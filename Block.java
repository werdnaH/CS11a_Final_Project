public abstract class Block {
  private boolean ic;// abbreviation of isclicked
  private String s;
  public Block(){
    ic = false;
    s = null;
  }
  public void click() {
    System.out.println(s);
  }
  public void setFlag();
  public void setqm(); //set question mark
  public String display();// return the content that will show on the command line  
}
