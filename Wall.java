import javafx.scene.shape.Rectangle ;
import javafx.scene.paint.Color  ;
import javafx.scene.Group ;
import java.io.Serializable ;

class Wall  implements Serializable
{
    private int length ;
    private int x ;
    private int y ;
    private int column ;
    transient Rectangle wall ;
    private  static  transient Group root ;
    private  static transient Snake snake ;


    Wall(int column ,int length,Group root , Snake snake)
    {
        this.column = column ;
        this.length = length ;
        this.root = root ;
        this.snake = snake ;
        wall = new Rectangle(90*column + 5*(column-1),-length+50,5,length) ;
        x=90*column + 5*(column-1) ;
        y = -length + 50 ;
        wall.setFill(Color.RED) ;
        root.getChildren().add(wall)  ;
    }
    
    public void Complete(Group root,Snake snake)
    {
        wall = new Rectangle(x,y,5,length) ;
        wall.setFill(Color.RED)  ;
        this.root = root ;
        this.snake = snake ;
        root.getChildren().add(wall) ;
    }
    
    
    
    
    public  boolean Perform(int displace)
    {
        wall.setY(wall.getY()+displace) ;
        y=y+displace ;
        if(wall.getY()>800)
        {
            snake.NowMove() ;
            root.getChildren().remove(this.wall)  ;
            return false ;
        }
        return true  ;
        
    }
    
    public int GetColumn()
    {
        return column ;
    }
    
    public int GetY()
    {
        return y ;
    }
    
    public int length()
    {
        return length ;
    }
    

}
