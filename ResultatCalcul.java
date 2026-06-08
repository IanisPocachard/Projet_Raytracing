import java.io.Serializable;

public class ResultatCalcul implements Serializable {
  private Image image;
  private int x, y;
  
  public TacheCalcul (Image image, int x, int y) {
    this.image = image;
    this.x = x;
    this.y = y;
  }
  
  public Image getImage() {
    return this.image;
  }
  
  public int getX() {
    return this.x;
  }
  
  public int getY() {
    return this.y;
  }
}
