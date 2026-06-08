import java.io.Serializable;

public class TacheCalcul implements Serializable {
  private Scene scene;
  private int x, y, hauteur, largeur;
  
  public TacheCalcul (Scene scene, int x, int y, int hauteur, int largeur) {
    this.scene = scene;
    this.x = x;
    this.y = y;
    this.hauteur = hauteur;
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
  
  public int getHauteur() {
    return this.hauteur;
  }
  
  public int getLargeur() {
    return this.largeur;
  }
}
