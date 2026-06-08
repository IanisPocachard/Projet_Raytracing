import java.io.Serializable;

public class TacheCalcul implements Serializable {
  private Scene scene;
  private int x, y, longueur, largeur;
  
  public TacheCalcul (Scene scene, int x, int y, int longueur, int largeur) {
    this.scene = scene;
    this.x = x;
    this.y = y;
    this.longueur = longueur;
    this.largeur = largeur;
  }
  
  public Scene getScene() {
    return this.scene;
  }
  
  public int getX() {
    return this.x;
  }
  
  public int getY() {
    return this.y;
  }
  
  public int getLongueur() {
    return this.longueur;
  }
  
  public int getLargeur() {
    return this.largeur;
  }
}
