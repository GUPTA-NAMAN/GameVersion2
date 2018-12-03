
import javafx.application.* ;
import javafx.stage.* ;






public class Main extends Application
{
    
    public static void main(String args[])
    {
        Main.launch(args) ;
    }
    
    @Override
    public void start(Stage PrimaryStage)
    {
        StartPage startpage = new StartPage(PrimaryStage) ;
        startpage.DesignPage() ;
        startpage.ShowPage() ;
        
    }
    
}
