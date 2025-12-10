package space_invaders.sprites;


public class AlienMock extends Alien {
    private int x;
    private int y;
    public AlienMock(int x, int y){
        super();
        this.x = x;
        this.y = y;
    }

    public int getX() {return x;}
    public int getY() {return y;}
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public boolean isVisible(){return true;}

    public void act(int direction) {this.x += direction;}
}

