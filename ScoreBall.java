import javafx.scene.Group ;
import javafx.scene.paint.Color ;
import java.io.Serializable ;
import javafx.scene.control.Label ;


class ScoreBall extends BasicBall implements Serializable
{
    

    
    ScoreBall(int column,int Point,Group root,Snake snake)
    {
        super(column,Color.PINK,root,snake)  ;
        this.Point = Point ;
        number = new Label(Integer.toString(Point)) ;
        number.setLayoutX(ball.getCenterX()-5) ;
        number.setLayoutY(ball.getCenterY()-15) ;
        root.getChildren().add(number)  ;
    }
    
    @Override
    public void Complete(Group root,Snake snake)
    {
        InternalComplete(Color.PINK,root,snake) ;
        number = new Label(Integer.toString(Point)) ;
        number.setLayoutX(ball.getCenterX()) ;
        number.setLayoutY(ball.getCenterY()-20) ;
        root.getChildren().add(number)  ;
    }
    
    @Override
    public int GetPoint()
    {
        return Point ;
    }
}
