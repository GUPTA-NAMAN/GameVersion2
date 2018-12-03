
import javafx.stage.* ;
import javafx.scene.* ;
import javafx.scene.paint.Color  ;
import javafx.scene.control.* ;
import javafx.event.* ;
import javafx.scene.text.* ;
import javafx.scene.input.* ;
import javafx.scene.shape.* ;
import java.util.ArrayList ;
import java.io.* ;
import javafx.scene.control.Button ;
import javafx.event.EventHandler ;
import javafx.event.ActionEvent ;
import  java.util.Arrays  ;


class GamePage
{
    private Group root ;
    private Scene scene ;
    private Stage PrimaryStage ;
    private Snake snake ;
    private MyTimer animator ;
    private Scene back ;
    private Button b1 ;
    private ArrayList<Integer> Scores ;
    private ArrayList<String> Name ;
    private String person ;
    private int score ;
    private int ii ;
    
    GamePage(Stage PrimaryStage,Scene back)
    {
        
        try
        {
            FileInputStream fin =new  FileInputStream("Scores.txt") ;
            ObjectInputStream oin=new ObjectInputStream(fin)  ;
            Scores=(ArrayList<Integer>)oin.readObject()   ;
            oin = new ObjectInputStream(new FileInputStream("Names.txt") )   ;
            Name=(ArrayList<String>)oin.readObject()  ;
            System.out.println(Scores==null)   ;
            System.out.println("successcomplete") ;
        }
        catch(Exception e)
        {
            try
            {
                
                FileOutputStream fout=new FileOutputStream("Scores.txt")  ;
                ObjectOutputStream out=new ObjectOutputStream(fout)  ;
                Scores = new ArrayList<Integer>()  ;
                out.writeObject(Scores) ;
                fout.close()  ;
                Name = new ArrayList<String>()  ;
                out=new ObjectOutputStream(new FileOutputStream("Names.txt") )   ;
                out.writeObject(Name)   ;
                fout.close()  ;
                System.out.println("failure") ;
            }
            catch(Exception E)
            {
                System.out.println(E)  ;
            }
        }
        
        this.back = back ;
        this.PrimaryStage = PrimaryStage ;
        root = new Group() ;
        scene = new Scene(root,470,800,Color.BLACK) ;
        PrimaryStage.setY(0) ;
        PrimaryStage.setScene(scene) ;
        PrimaryStage.setWidth(470) ;
        PrimaryStage.setHeight(800) ;
        PrimaryStage.setResizable(false) ;
    }
    
    public void ShowPage()
    {
        
        b1=new Button("BACK") ;
        
        
        b1.setLayoutX(420)  ;
        
        
        
        b1.setOnAction
        (
            new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent Event)
                {
                    PrimaryStage.setX(550) ;
                    PrimaryStage.setY(200) ;
                    PrimaryStage.setWidth(300) ;
                    PrimaryStage.setHeight(500) ;
                    PrimaryStage.setScene(back) ;
                    PrimaryStage.show() ;
                    
                    
                    ObjectOutputStream out= null ;
                    try
                    {
                        out = new ObjectOutputStream( new FileOutputStream("snake.txt")) ;
                        out.writeObject(snake) ;
                        out.close();
                        out = new ObjectOutputStream(new FileOutputStream("animator.txt"))  ;
                        out.writeObject(animator) ;
                        out.close() ;
                    }
                    catch(Exception e)
                    {
                        System.out.println(e+"here") ;
                    }
                    
                    
                }
            }
        )
        ;
        
        
        
        
        
        
        
        
        
        root.getChildren().addAll(b1) ;
        PrimaryStage.show() ;
        animator.start() ;
    }
    
    public  void  NewSnake()
    {
        snake = new Snake(root) ;
        snake.MakeSnake() ;
        this.SetController() ;
        this.SetSaving() ;
        animator = new MyTimer(root,snake,this) ;
        animator.SOPD()   ;
    }
    
    public void LoadSnake()
    {
        ObjectInputStream in=null ;
        try
        {
            in= new ObjectInputStream ( new FileInputStream("snake.txt")) ;
            snake = (Snake)in.readObject() ;
            in.close() ;
            in = new ObjectInputStream(new FileInputStream("animator.txt")) ;
            animator=(MyTimer)in.readObject() ;
            in.close() ;
        }
        catch(Exception e)
        {
            System.out.println(e+"in input") ;
        }
        finally
        {
            if( ! this.IsGameEnd())
            {
            snake.CompleteSnake(root) ;
            snake.MakeSnake() ;
            this.SetSaving() ;
            this.SetController() ;
            animator.SOPE(root,snake,this) ;
            }
            else
            {
                Label message= new Label("Last Game was ended")  ;
                message.setTextFill(Color.WHITE)  ;
                root.getChildren().add(message)  ;
            }
        }
        
    }
    
    
    private void SetController()
    {
        scene.setOnKeyPressed
        (
         new EventHandler<KeyEvent>()
         {
             @Override
             public void handle(KeyEvent Event)
             {
                 String ActionName = Event.getCode().getName() ;
                 
                 if( ActionName.compareTo("Left")==0)
                 {
                     snake.MoveLeft() ;
                 }
                 else if( ActionName.compareTo("Right")==0 )
                 {
                     snake.MoveRight() ;
                 }
                 else
                 {
                     
                 }
             }
         }
         )
        ;
    }
    
    private void SetSaving()
    {
        
        PrimaryStage.setOnCloseRequest
        (
         new EventHandler<WindowEvent>()
         {
             @Override
             public void handle(WindowEvent Event)
             {
                 ObjectOutputStream out= null ;
                 try
                 {
                     out = new ObjectOutputStream( new FileOutputStream("snake.txt")) ;
                     out.writeObject(snake) ;
                     out.close();
                     out = new ObjectOutputStream(new FileOutputStream("animator.txt"))  ;
                     out.writeObject(animator) ;
                     out.close() ;
                 }
                 catch(Exception e)
                 {
                     System.out.println(e+"here") ;
                 }
             }
          }
        )
        ;
    }
    
    public boolean IsGameEnd()
    {
        return animator.IsGameEnd() ;
    }
    
    public void GameIsOver()
    {
        score = animator.GetScore()  ;
        Label message = new Label("Your Game Is Over and your Score was  "+Integer.toString(score))  ;
        TextField tf=new TextField() ;
        tf.setLayoutX(100) ;
        Button button =new Button("press")   ;
        button.setOnAction
        (
            new EventHandler<ActionEvent>()
            {
            @Override
            public void handle(ActionEvent E)
            {
                person=tf.getText()  ;
                Scores.add(ii,score)   ;
                System.out.println(person)  ;
                Name.add(ii,person)   ;
                
                try
                {
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Scores.txt"))   ;
                    out.writeObject(Scores)   ;
                    out.close()   ;
                    out=new ObjectOutputStream(new FileOutputStream("Names.txt")) ;
                    out.writeObject(Name) ;
                    out.close()  ;
                    System.out.println(Scores.size()+"     "+Name.size()+"   kjhkjgkjgjkgjf")  ;
                    for(int i=0;i<Name.size();i++)
                    {
                        System.out.println(i+1+"    "+Name.get(i))  ;
                    }
                }
                catch(Exception e)
                {
                    System.out.println(e)  ;
                }
                
                PrimaryStage.close()   ;
            }
            }
        )
        ;
        button.setLayoutX(350)   ;
        
        
        message.setTextFill(Color.ORANGE)  ;
        message.setLayoutX(200) ;
        message.setLayoutY(400)  ;
        
        root.getChildren().addAll(message)  ;  //ugugg
        if(Scores.size()==0 )
        {
            ii=0 ;
            root.getChildren().addAll(tf,button)   ;
            
        }
        else
        {
            if(score>=Scores.get(0))
            {
                ii=0 ;
                root.getChildren().addAll(tf,button)   ;
               // Scores.add(score,0)  ;
            }
            else
            {
                if(Scores.size()>9 &&  score>=Scores.get(9))
                {
                    root.getChildren().addAll(tf,button)   ;
                    for(ii=0;ii<=8;ii++)
                    {
                        if( Scores.get(ii)>score  &&  score>Scores.get(ii+1) )
                        {
                            break ;
                        }
                    }
                    ii++  ;
                }
                else
                {
                    root.getChildren().addAll(tf,button)   ;
                  int   rank=0   ;
                    ii=Scores.size()   ;
                    while(ii+1<Scores.size())
                    {
                        if( Scores.get(rank)>score  &&  score>Scores.get(rank+1) )
                        {
                            rank++  ;
                            ii=rank  ;
                            break  ;
                        }
                    }
                }
            }
        }
        
        
        
      /*
        try
        {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Scores.txt"))   ;
            out.writeObject(Scores)   ;
        }
        catch(Exception e)
        {
            System.out.println(e)  ;
        }
        
        
        System.out.println(Scores.size())  ;
        
        
        for(int i=0;i<Scores.size();i++)
        {
            System.out.println(Scores.get(i))   ;
        }
        
        root.getChildren().addAll(message)  ;
        
      */
        
    }
    
    
    public ArrayList<Integer> GetArray()
    {
        return Scores  ;
    }
    
    
   
    

}
