package Proxy;

public class WebPageImpl implements WebPage {
    @Override
    public void renderPage(String url) throws Exception{
        System.out.println(url + "rendered successfully.");
    }
    
}