import javafx.scene.Group ;
import javafx.animation.AnimationTimer ;
import java.util.ArrayList ;
import java.io.Serializable ;
import javafx.scene.paint.Color ;
import javafx.scene.control.Label ;
import javafx.scene.shape.Circle ;
import java.lang.Math ;
import java.util.Random ;

class MyTimer extends AnimationTimer implements Serializable
{
    private int i ;
    private int displace ;
    private long l=1000000 ;
    private int snakeharm ;
    private boolean movedown ;
    private int score ;
    private boolean IsGameOver ;
    transient private Group root ;
    transient private Snake snake ;
    transient private Label ScoreBox ;
    transient private Random rand ;
    transient private GamePage gp ;
    private ArrayList<Wall> wallarray ;
    private ArrayList<Block> blockarray ;
    private ArrayList<BasicBall> Balls ;
    
    
    
    MyTimer(Group root,Snake snake,GamePage gp)
    {
        i=0 ;
        score=0 ;
        movedown=true ;
        IsGameOver = false ;
        displace =  2 ;
        this.snake = snake ;
        this.root = root ;
        this.gp = gp ;
    }
    
    public void SOPD()
    {
        rand = new Random() ;
        ScoreBox = new Label(Integer.toString(score)) ;
        
        wallarray = new ArrayList<Wall>() ;
        blockarray =new ArrayList<Block>() ;
        Balls = new ArrayList<BasicBall>() ;
        snake.SetWallArray(wallarray) ;
        root.getChildren().add(ScoreBox)  ;
        snakeharm=0 ;
    }
    
    public void SOPE(Group root, Snake snake,GamePage gp)
    {
        this.gp=gp ;
        this.root = root ;
        this.snake = snake ;
        snake.SetWallArray(wallarray) ;
        ScoreBox = new Label(Integer.toString(score)) ;
        root.getChildren().add(ScoreBox)  ;
        rand = new Random() ;
        for(int i=0;i<wallarray.size();i++)
        {
            wallarray.get(i).Complete(root,snake) ;
        }
        
        for(int i=0;i<blockarray.size();i++)
        {
            blockarray.get(i).Complete(root,snake) ;
        }
        
        for(int i=0;i<Balls.size();i++)
        {
            Balls.get(i).Complete(root,snake) ;
        }
    }
    
    @Override
    public void handle(long l)
    {
        
        
        
        //   new creator
        if(i%300==0)
        {
            int j=rand.nextInt(3)+1 ;
            
            for(int k=1;k<=j;k++)
            {
                BasicBall sb=new ScoreBall(rand.nextInt(5)+(k-1)*5,rand.nextInt(5)+1,root,snake) ;
                Balls.add(sb)  ;
            }
        }
        
        if(i==250)
        {
            BasicBall lf=new LifeBall(1,root,snake) ;
            Balls.add(lf) ;
        }
        
        if(i==350)
        {
            DestroyBoxBall destroy= new DestroyBoxBall(rand.nextInt(15),root,snake) ;
            Balls.add(destroy) ;
        }
        
        if(i==200)
        {
            BasicBall gsb=new GetScoreballBall(rand.nextInt(15),root,snake) ;
            Balls.add(gsb) ;
        }
        
        if(i==100)
        {
            Wall wall = new Wall(2,500,root,snake) ;
            wallarray.add(wall) ;
            
        }
        
        if(  i==100   )
        {
            
                Block block=new Block(rand.nextInt(2)+1,rand.nextInt(20)+1,root,snake) ;
                blockarray.add(block) ;
            
            Block b2=new Block(rand.nextInt(3)+3,rand.nextInt(10)+1,root,snake)  ;
            blockarray.add(b2)  ;
            
            
        }
        if(i==400)
        {
            Block block=new Block(rand.nextInt(3)+1,rand.nextInt(9)+1,root,snake) ;
            Block b2=new Block(rand.nextInt(1)+4,rand.nextInt(15)+1,root,snake) ;
            blockarray.add(block) ;
            blockarray.add(b2) ;
        }
        
        if(i==500)
        {
            Block block=new Block(rand.nextInt(2)+1,rand.nextInt(3)+1,root,snake) ;
            Block b2=new Block(rand.nextInt(1)+3,rand.nextInt(7)+1,root,snake) ;
            Block b3=new Block(rand.nextInt(1)+5,rand.nextInt(3)+1,root,snake) ;
            
            blockarray.add(block) ;
            blockarray.add(b2) ;
            blockarray.add(b3) ;
        }
    
        for(int i=0;i<blockarray.size();i++)
        {
            if(!blockarray.get(i).Perform(displace) )
            {
                root.getChildren().remove(blockarray.get(i)) ;
                blockarray.remove(i) ;
            }
            
        }
        
        if(snake.IsAlive()==false)
        {
            IsGameOver= true ;
            this.stop() ;
            gp.GameIsOver() ;
        }
        
        
        for(int i=0;i<wallarray.size() ;i++)
        {
            
            if(!wallarray.get(i).Perform(displace) )
            {
                root.getChildren().remove(wallarray.get(i)) ;
                wallarray.remove(i) ;
            }
        }
        
        
        
        
        for(int i=0;i<Balls.size();i++)
        {
            int code = Balls.get(i).Perform(displace) ;
            if( code==1 || code==2)
            {
                BasicBall type=Balls.get(i) ;
                Balls.remove(i) ;
                if(code==2)
                {
                    if( type instanceof ScoreBall)
                    {
                    snake.IncLength(type.GetPoint()) ;
                        score=score+type.GetPoint()  ;
                    }
                    else if( type instanceof DestroyBoxBall)
                    {
                        for(int j=0;j<blockarray.size();j++)
                        {
                            blockarray.get(j).Destroy(true) ;
                       //     blockarray.remove(j)  ;
                        }
                        blockarray.clear()  ;
                    }
                    else if(type instanceof GetScoreballBall)
                    {
                        Circle centerBall = type.GetBall() ;
                        
                        for(int j=0;j<Balls.size();j++)
                        {
                            if(Balls.get(j) instanceof ScoreBall )
                            {
                              Circle  ball = Balls.get(j).GetBall() ;
                                
                                if( Math.sqrt( Math.pow(ball.getCenterX()-centerBall.getCenterX(),2) + Math.pow(ball.getCenterY()-centerBall.getCenterY(),2) )   < 500    )
                                {
                                    
                                root.getChildren().removeAll(Balls.get(j).GetBall(),Balls.get(j).GetLabel())  ;
                                snake.IncLength(Balls.get(j).GetPoint()) ;
                                    score=score+Balls.get(j).GetPoint()  ;
                                Balls.remove(j) ;
                                j=j-1 ;
                                }
                            }
                        }
                    }
                    else if(type instanceof LifeBall)
                    {
                        snake.NoHarm(true) ;
                        snakeharm=1 ;
                    }
                }
            }
        }
        
        
        if(snakeharm>0  )
        {
            snakeharm++ ;
        }
        if(snakeharm==300 )
        {
            snakeharm=0 ;
            snake.NoHarm(false) ;
        }
    
        i++ ;
        if(i==601)
        {
            i=0 ;
        }
        ScoreBox.setText(Integer.toString(score))  ;
        displace=2+snake.length()/4 ;
    
    }
    
    public boolean IsGameEnd()
    {
        return IsGameOver ;
    }
    
    public int GetScore()
    {
        return score ;
    }
}

