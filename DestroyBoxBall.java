import javafx.scene.Group ;
import javafx.scene.paint.Color ;
import java.io.Serializable ;



class DestroyBoxBall extends BasicBall implements Serializable
{
    DestroyBoxBall(int column,Group root,Snake snake)
    {
        super(column,Color.RED,root,snake)  ;
    }
    
    @Override
    public void Complete(Group root,Snake snake)
    {
        InternalComplete(Color.RED,root,snake) ;
    }

}
