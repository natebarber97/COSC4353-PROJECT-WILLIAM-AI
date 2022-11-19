package Proxy;

public class Browser {
    public static void main(String[] args) {
        WebPageProxy WebPageProxy = new WebPageProxy();
        try {
            WebPageProxy.renderPage("www.stackoverflow.com");
        }
        catch(Exception e){
            System.out.println("Exception Happened " + e.getMessage());
            e.printStackTrace();
        }
    }
}
