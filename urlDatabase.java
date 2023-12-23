package myprojectjava;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UrlDatabase {
    private static Map<String, UrlEntry> urlMap = new HashMap<>();
    private static int uniqueKeyCounter = 1;

    public static void main(String[] args) {
        System.out.println("To start the program: run java UrlDatabase.");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine();
            String[] command = input.split("\\s+");

            switch (command[0].toLowerCase()) {
                case "storeurl":
                    if (command.length == 2) {
                        storeUrl(command[1]);
                    } else {
                        System.out.println("Invalid command. Usage: storeurl <url>");
                    }
                    break;

                case "get":
                    if (command.length == 2) {
                        getUrl(command[1]);
                    } else {
                        System.out.println("Invalid command. Usage: get <url>");
                    }
                    break;

                case "count":
                    if (command.length == 2) {
                        getCount(command[1]);
                    } else {
                        System.out.println("Invalid command. Usage: count <url>");
                    }
                    break;

                case "list":
                    listUrls();
                    break;

                case "exit":
                    System.out.println("Exiting program.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Unknown command. Valid commands: storeurl, get, count, list, exit");
                    break;
            }
        }
    }

    private static void storeUrl(String url) {
        String shortKey = "key" + uniqueKeyCounter++;
        urlMap.put(url, new UrlEntry(shortKey, 0));
        System.out.println("URL stored with short key: " + shortKey);
    }

    private static void getUrl(String url) {
        UrlEntry entry = urlMap.get(url);
        if (entry != null) {
            entry.incrementCount();
            System.out.println("Short key for " + url + ": " + entry.getShortKey());
        } else {
            System.out.println("URL not found.");
        }
    }

    private static void getCount(String url) {
        UrlEntry entry = urlMap.get(url);
        if (entry != null) {
            System.out.println("Usage count for " + url + ": " + entry.getCount());
        } else {
            System.out.println("URL not found.");
        }
    }

    private static void listUrls() {
        System.out.println("List of URLs and counts:");

        for (Map.Entry<String, UrlEntry> entry : urlMap.entrySet()) {
            System.out.println("{ \"url\": \"" + entry.getKey() + "\", \"shortKey\": \"" + entry.getValue().getShortKey() + "\", \"count\": " + entry.getValue().getCount() + " }");
        }
    }

    private static class UrlEntry {
        private String shortKey;
        private int count;

        public UrlEntry(String shortKey, int count) {
            this.shortKey = shortKey;
            this.count = count;
        }

        public String getShortKey() {
            return shortKey;
        }

        public int getCount() {
            return count;
        }

        public void incrementCount() {
            count++;
        }
    }
}

//javac UrlDatabase.java
//java UrlDatabase