class EnvoyerCalcul extends Thread {
  private TacheCalcul calcul;
  private Disp disp
  private InterfaceNoeudDeCalcul noeud;
  
  public EnvoyerCalcul (int x0, int y0, int l, int h, Scene s, Disp d, InterfaceNoeudDeCalcul n) {
    this.calcul = new TacheCalcul(s, x0, y0, l, h);
    this.disp = d;
    this.noeud = n;
  }
  
  public run () {
    this.disp.setImage(this.noeud.calculer(this.calcul), this.calcul.getX(), this.calcul.getY());
  }

}
