import javafx.scene.shape.* ;
import javafx.scene.Group ;
import javafx.scene.paint.Color ;
import javafx.scene.control.Label ;
import java.io.Serializable ;



abstract class BasicBall implements Serializable
{
    protected int column ;
   protected int x ;
   protected int y ;
    protected int Point ;
   protected Snake snake ;
   protected static final int radius=15 ;
    protected transient Label number ;
   protected transient Circle ball ;
    protected transient Group root ;
    
    BasicBall(int column,Color colour,Group root,Snake snake)
    {
        y=85 ;
        if(column!=15)
        {
        x= (column-1)*radius*2 + radius + ((column-1)/3)*5  ;
        }
        else
        {
            x=455 ;
        }
        ball = new Circle(x,y,radius,colour) ;
        this.snake =snake ;
        this.root = root ;
        root.getChildren().add(ball)  ;
    }
    
    protected void InternalComplete(Color colour,Group root,Snake snake)
    {
        ball = new Circle(x,y,radius,colour) ;
        this.root = root ;
        this.snake = snake ;
        root.getChildren().add(ball) ;
    }
    
    protected int Perform(int displace)
    {
        if(  ball.getCenterX()==snake.currentX() && ball.getCenterY()+15>385 && ball.getCenterY()-15<385+30*snake.length()  )
        {
            root.getChildren().removeAll(ball,number) ;
            return 2 ;
        }
        else
        {
            int code = this.MoveDown(displace) ;
            return code ;
        }
    }
    
    protected int MoveDown(int displace)
    {
        ball.setCenterY(ball.getCenterY()+displace) ;
        y=y+displace ;
        
        if(this instanceof ScoreBall)
        {
            number.setLayoutY(ball.getCenterY()-20+displace) ;
        }
        
        if( ball.getCenterY()-15>800)
        {
            
            if(this instanceof  ScoreBall)
            {
                root.getChildren().remove(number) ;
            }
            root.getChildren().remove(ball) ;
            return 1 ;
        }
        return 3  ;
        
    }
    
    protected Circle GetBall()
    {
        return ball ;
    }
    
    public int GetPoint()
    {
        return -1 ;
    }
    
    protected Label GetLabel()
    {
        return number ;
    }
    
    abstract public void Complete(Group root,Snake snake)  ;
    
}
