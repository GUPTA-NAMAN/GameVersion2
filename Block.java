import javafx.scene.* ;
import javafx.scene.shape.*  ;
import javafx.scene.paint.Color ;
import java.util.* ;
import javafx.scene.control.* ;
import java.io.Serializable ;


class Block implements Serializable
{
    private transient Rectangle box ;
    private int x ;
    private int y ;
    private int len ;
    private int point ;
    private int counter ;
    private transient Group root ;
    private static transient Snake snake  ;
    private  transient Label number ;
    
    
    Block(int i,int point,Group root,Snake snake)
    {
        counter=0 ;
        this.point = point ;
        this.root = root ;
        this.snake = snake  ;
        number = new Label("1") ;
        number.setTextFill(Color.WHITE) ;
        x=90*(i-1)+(i-1)*5 ;
        y=50 ;
        len=90 ;
        box = new Rectangle(x,y,len,len) ;
        box.setFill(Color.WHITE) ;
        box.setSmooth(true) ;
        root.getChildren().add(box) ;
        
        number= new Label(Integer.toString(point)) ;
        number.setLayoutX(box.getX()+35) ;
        number.setLayoutY(box.getY()+50) ;
        root.getChildren().add(number)  ;
    }
    
    public void Complete(Group root,Snake snake)
    {
        this.root = root ;
        this.snake = snake  ;
        box=new Rectangle(x,y,len,len) ;
        box.setFill(Color.WHITE) ;
        root.getChildren().add(box) ;
        number= new Label(Integer.toString(point)) ;
        number.setLayoutX(box.getX()+35) ;
        number.setLayoutY(box.getY()+50) ;
        root.getChildren().add(number)  ;
    }
    
    public boolean Perform(int displace)
    {
        if( box.getY()+90>snake.DefaultY()-snake.Radius()  &&  box.getY()<385+30*snake.length()  &&    box.getX()<snake.currentX() &&  box.getX()+90>snake.currentX() )  // got touched by snake
        {
            
            this.Destroy(false)   ;
            if(point==0)
            {
            return false ;
            }
            else
            {
                return true ;
            }
        }
        else
        {
            boolean c = this.MoveDown(displace)   ;
            return c ;
        }
    }
    
    
    private boolean MoveDown(int displace)
    {
        box.setY(box.getY()+displace) ;
            y=y+displace ;
        number.setLayoutY(box.getY()+50+displace)  ;
      //  number.setLayoutY(box.getY()+50+inc)  ;
        if(box.getY()>800)
        {
         //   BlockArray.remove(this.box)  ;
            //   root.getChildren().remove(this.box) ;
            return false ;
        }
        return true  ;
    }
    
    public void Destroy(Boolean Isdirect)
    {
        if(point<=5 || Isdirect)
        {
            if(Isdirect==false)
            {
        snake.DecLength(point) ;
            }
            point=0 ;
        root.getChildren().removeAll(box,number) ;
        }
        else
        {
            if(counter==15)
            {
            snake.DecLength(2) ;
            point=point-2 ;
            number.setText(Integer.toString(point))  ;
                counter=0 ;
            }
            counter++ ;
        }
     
    }
    
   
   

}


