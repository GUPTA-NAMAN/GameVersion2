
import javafx.stage.* ;
import javafx.scene.control.* ;
import javafx.scene.paint.Color  ;
import javafx.scene.* ;
import javafx.event.* ;
import java.util.ArrayList  ;
import java.io.*  ;
import javafx.scene.layout.GridPane  ;

class StartPage
{
    
    private Stage primaryStage ;
    private Group root ;
    private Scene scene ;
    
    private GamePage gamepage ;
    
    StartPage(Stage primaryStage)
    {
        this.primaryStage = primaryStage ;
    
    }
    
    public void DesignPage()
    {
        
        
        root=new Group() ;
        
        Button b1=new Button("NEW GAME") ;
        b1.setLayoutX(100) ;
        b1.setLayoutY(100) ;
        b1.setOnAction
        (
         new EventHandler<ActionEvent>()
         {
             @Override
             public void handle(ActionEvent Event)
             {
                 gamepage = new GamePage(primaryStage,scene) ;
                 gamepage.NewSnake() ;
                 gamepage.ShowPage() ;
                 
             }
         }     
         )
        ;
        
        
        
        
        Button b2=new Button("RESUME") ;
        b2.setLayoutX(100) ;
        b2.setLayoutY(200) ;
        
        b2.setOnAction
        (
         
         new EventHandler<ActionEvent>()
         {
             @Override
             public void handle(ActionEvent Event)
             {
                 gamepage = new GamePage(primaryStage,scene) ;
                 gamepage.LoadSnake() ;
                 if( ! gamepage.IsGameEnd() )
                 {
                     gamepage.ShowPage() ;
                 }
                 else
                 {
                 }
             }
         }
         
         )
        ;
        
        
        
        
        
        Button b3=new Button("LEADERBOARD") ;
        b3.setLayoutX(100) ;
        b3.setLayoutY(300) ;
        b3.setOnAction
        (
            new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent Event)
                {
                    boolean firstgame = false  ;
                    ArrayList<Integer> Scores=null ;
                    ArrayList<String> names=null ;
                    try
                    {
                        ObjectInputStream oin=new ObjectInputStream(new FileInputStream("Scores.txt"))   ;
                         Scores =(ArrayList<Integer>)oin.readObject()   ;
                        oin.close()  ;
                        oin=new ObjectInputStream(new FileInputStream("Names.txt"))   ;
                        names=(ArrayList<String>)oin.readObject() ;
                        oin.close()  ;
                    }
                    catch(Exception e)
                    {
                        firstgame = true ;
                    }
                    
                    Group root = new Group() ;
                    
                    
                    Scene scene=new Scene(root,500,800,Color.CYAN)  ;
                    if(  firstgame==true || Scores.size()==0  )
                    {
                        Label message=new Label("No One Has EverRecorded Score")   ;
                        root.getChildren().add(message )   ;
                    }
                    System.out.println("kbjggjhghj    "+Scores.size()+"   "+names.size())  ;
                    if(Scores.size()>0 )
                    {
                        GridPane root2=new GridPane()  ;
                        root2.setHgap(20)  ;
                        
                        root2.addRow(0,new Label("Rank"),new Label("Score"),new Label("Player Name"))   ;
                        for(int i=0;i<Scores.size();i++)
                        {
                            root2.addRow(i+1,new Label(Integer.toString(i+1)),new Label(Integer.toString(Scores.get(i))),new Label(names.get(i)))   ;
                        }
                        System.out.println("here")   ;
                        scene.setRoot(root2)   ;
                    }
                    
                    
                    
                    primaryStage.setY(0) ;
                    primaryStage.setWidth(500)  ;
                    primaryStage.setHeight(800)  ;
                    primaryStage.setTitle("ScoreBoard")   ;
                    primaryStage.setScene(scene)  ;
                    System.out.println(Scores==null)   ;
                }
            }
        )
        ;
        
        
        Button b4=new Button("EXIT")  ;
        b4.setLayoutX(100) ;
        b4.setLayoutY(400) ;
        b4.setOnAction
        (
         new EventHandler<ActionEvent>()
         {
             @Override
             public void handle(ActionEvent Event)
             {
                 Stage PopUp = new Stage() ;
                 Group root = new Group() ;
                 
                 Label INFO = new Label("ARE YOU SURE TO EXIT") ;
                 INFO.setTextFill(Color.CYAN) ;
                 
                 INFO.setLayoutY(0) ;
                 INFO.setLayoutX(150) ;
                 
                 
                 Button b11=new Button("YES") ;
                 b11.setLayoutY(100) ;
                 b11.setLayoutX(0) ;
                 b11.setOnAction
                 (
                  new EventHandler<ActionEvent>()
                  {
                      @Override
                      public void handle(ActionEvent Event)
                      {
                          PopUp.close() ;
                          primaryStage.close() ;
                      }
                  }
                  )
                 ;
                 
                 Button b12=new Button("NO") ;
                 b12.setLayoutY(100) ;
                 b12.setLayoutX(100) ;
                 b12.setOnAction
                 (
                  new EventHandler<ActionEvent>()
                  {
                      @Override
                      public void handle(ActionEvent Event)
                      {
                          PopUp.close()  ;
                      }
                  }
                  )
                 ;
                 
                 root.getChildren().addAll(b11,b12,INFO) ;
                 Scene scene = new Scene(root,400,200,Color.BLACK) ;
                 PopUp.setScene(scene) ;
                 PopUp.show() ;
             }
         }
         )
        ;
        
        
        
        
        
        root.getChildren().addAll(b1,b2,b3,b4) ;
        
        scene = new Scene(root,300,500,Color.BLUE) ;
        
        primaryStage.setTitle("SELECTION WINDOW") ;
        primaryStage.setScene(scene) ;
        
    }
    
    
    public void ShowPage()
    {
        primaryStage.show() ;
    }
    
    
    
}
