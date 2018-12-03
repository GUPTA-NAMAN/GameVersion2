
import javafx.scene.Group ;
import javafx.scene.paint.Color ;
import java.io.Serializable ;



class LifeBall extends BasicBall implements Serializable
{
    
    private int Point ;
    
    
    LifeBall(int column,Group root,Snake snake)
    {
        super(column,Color.GOLD,root,snake)  ;
        this.Point = Point ;
    }
    
    @Override
    public void Complete(Group root,Snake snake)
    {
        InternalComplete(Color.GOLD,root,snake) ;
    }
    
    public int GetPoint()
    {
        return -1  ;
    }
}
