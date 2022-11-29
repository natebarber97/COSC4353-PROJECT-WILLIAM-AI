package Proxy;

public class MainApp {

    public static void main(String[] args) {

        Internet internet = new ProxyInternet();
        internet.connectTo("google.com");

        System.out.println("\n=== Download World Cup 2022 Highlights ===");
        System.out.println();
        VideoDownloader videoDownloader = new ProxyVideoDownloader();
        videoDownloader.getVideo("Brazil vs. Switzerland Highlights");
        System.out.println();
        videoDownloader.getVideo("Brazil vs. Switzerland Highlights");
        System.out.println();
        videoDownloader.getVideo("South Korea vs. Ghana Highlights");
        System.out.println();
        videoDownloader.getVideo("South Korea vs. Ghana Highlights");
        System.out.println();
        videoDownloader.getVideo("Cameroon vs. Serbia Highlights");

    }

}
