package creature;

public class Position {
    private int x;
    private int y;

    public Position()
    {
        this.x=0;
        this.y=0;
    }
    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setPosition(int x, int y)
    {
        this.x=x;
        this.y=y;
    }
}
