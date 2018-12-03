import javafx.scene.Group ;
import javafx.scene.shape.Circle ;
import java.util.ArrayList  ;
import javafx.scene.paint.Color ;
import java.io.Serializable ;

class Snake implements Serializable
{
    private int  X ;
    private int Y ;
    private int length ;
    private int radius ;
    private int Column ;
    private boolean canMove ;
    private boolean con=false ;
    transient private ArrayList<Circle> body ;
    transient private Group root ;
    transient private ArrayList<Wall> wallarray ;
    
    Snake(Group root)
    {
        canMove = true ;
        this.root = root ;
        X= 15 ;
        Y = 400 ;
        length = 8;
        radius = 15 ;
        Column = 1;
        body = new ArrayList<Circle>() ;
    }
    
    public void SetWallArray(ArrayList<Wall> wallarray)
    {
        this.wallarray = wallarray ;
    }
    
    public void CompleteSnake(Group root)
    {
        this.root = root ;
        body = new ArrayList<Circle>() ;
    }
    
    public void MakeSnake()
    {
        for(int i=0;i<length;i++)
        {
            Circle circle = new Circle(X,Y+2*i*radius,radius,Color.BLUE) ;
            root.getChildren().add(circle) ;
            body.add(circle)  ;
        }
    }
    
    public  void MoveLeft()
    {
        
        
        if( Column%3==1)
        {
            if(Column!=1)
            {
                
                
                for(int i=0;i<wallarray.size();i++)
                {
                    if(  Column == 3*wallarray.get(i).GetColumn()+1  )
                    {
                        if( Y-15>wallarray.get(i).GetY() && Y-15<wallarray.get(i).GetY()+wallarray.get(i).length()  )
                        {
                            canMove=false ;
                            break ;
                        }
                        else if( Y-15<wallarray.get(i).GetY()  &&  Y-15+length*radius*2>wallarray.get(i).length()+wallarray.get(i).GetY()    )
                        {
                            canMove=false ;
                            break ;
                        }
                        else if(   Y-15 +length*radius*2>wallarray.get(i).GetY() && Y-15+length*radius*2<wallarray.get(i).GetY()+wallarray.get(i).length() )
                        {
                            canMove = false ;
                            break ;
                        }
                        else
                        {
                            canMove=true ;
                            break ;
                        }
                    }
                }
                
                
                if(canMove==true)
                {
                for(int i=0;i<length;i++)
                {
                    body.get(i).setCenterX( body.get(i).getCenterX() -35 ) ;
                }
                X = X - 35 ;
                Column-- ;
                }
            }
        }
        else
        {
            for(int i=0;i<length;i++)
            {
                body.get(i).setCenterX( body.get(i).getCenterX() - 30 ) ;
            }
            X = X-30 ;
            Column-- ;
        }
        
    
        
    }
    
    
    
    public  void MoveRight()
    {
        if(Column%3==0)
        {
            if(Column!=15)
            {
                for(int i=0;i<wallarray.size();i++)
                {
                    if(Column==3*wallarray.get(i).GetColumn())
                    {
                        if( Y-15>wallarray.get(i).GetY() && Y-15<wallarray.get(i).GetY()+wallarray.get(i).length()  )
                        {
                            canMove=false ;
                            break ;
                        }
                        else if( Y-15<wallarray.get(i).GetY()  &&  Y-15+length*radius*2>wallarray.get(i).length()+wallarray.get(i).GetY()    )
                        {
                            canMove=false ;
                            break ;
                        }
                        else if(   Y-15 +length*radius*2>wallarray.get(i).GetY() && Y-15+length*radius*2<wallarray.get(i).GetY()+wallarray.get(i).length() )
                        {
                            canMove = false ;
                            break ;
                        }
                        else
                        {
                            canMove=true ;
                            break ;
                        }
                    }
                }
                
                if(canMove==true)
                {
                for(int i=0;i<length;i++)
                {
                    body.get(i).setCenterX( body.get(i).getCenterX() + 35 ) ;
                }
                X = X + 35 ;
                Column++ ;
                }
                
            }
        }
        else
        {
            for(int i=0;i<length;i++)
            {
                body.get(i).setCenterX( body.get(i).getCenterX() +30 ) ;
            }
            X= X+30 ;
            Column++ ;
        }
    }
    
    
    public void IncLength(int unit)
    {
        for(int i=0;i<unit;i++)
        {
            int j=Y + 2*radius*length + 2*i*radius  ;
            Circle circle =new Circle(X,j,radius,Color.BLUE) ;
            body.add(circle) ;
            root.getChildren().add(circle) ;
        }
        length = length + unit ;
    }
    
    public void DecLength(int unit)
    {
        if(con==false)
        {
        
            for(int i=0;i<unit  && length>0;i++)
            {
                Circle circle = body.get( body.size()-1 ) ;
                body.remove(circle) ;
                root.getChildren().remove(circle )    ;
                length-- ;
            }
        }
    }
    
    public void NoHarm(boolean con)
    {
        this.con = con ;
    }

    public int Radius()
    {
        return radius ;
    }
    
    public int DefaultY()
    {
        return Y ;
    }
    
    public int length()
    {
        return length ;
    }
    
    public int currentX()
    {
        return X ;
    }
    
    public boolean IsAlive()
    {
        if(length==0)
        {
            return false ;
        }
        else
        {
            return true ;
        }
    }
    
    public void NowMove()
    {
        canMove = true ;
    }
    
}


