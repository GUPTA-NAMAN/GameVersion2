import javafx.scene.Group ;
import javafx.scene.paint.Color ;
import java.io.Serializable ;



class GetScoreballBall extends BasicBall implements Serializable
{
    GetScoreballBall(int column,Group root,Snake snake)
    {
        super(column,Color.GREEN,root,snake)  ;
    }
    
    @Override
    public void Complete(Group root,Snake snake)
    {
        InternalComplete(Color.GREEN,root,snake) ;
    }
}
