package Block;

public class Browser {
    public static void main(String[] args) {
        WebPageProxy WebPageProxy = new WebPageProxy();
        
        System.out.println("\nConnecting to Website");
        try {
            WebPageProxy.renderPage("www.youtube.com");
        }
        catch(Exception e){
            System.out.println("Exception Happened " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }
}
