import java.util.Scanner;
 class EcoThreadsManager {
    static Cloth[] clothArray = new Cloth[100];
    static int count = 0;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        NotificationThread notify = new NotificationThread();
        notify.start();

        while (true) {
            System.out.println("\n--- EcoThreads Manager ---");
            System.out.println("1. Add Cloth");
            System.out.println("2. View All Clothes");
            System.out.println("3. View Donation Centers");
            System.out.println("4. View DIY Ideas");
            System.out.println("5. View Recycling Units");
            System.out.println("6. Sell/Exchange Cloth");
            System.out.println("7. View Marketplace");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = 0;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addCloth();
                    break;
                case 2:
                    viewClothes();
                    break;
                case 3:
                    SuggestionManager.showDonationCenters();
                    break;
                case 4:
                    SuggestionManager.showDIYIdeas();
                    break;
                case 5:
                    SuggestionManager.showRecyclingUnits();
                    break;
                case 6:
                    sellOrExchange();
                    break;
                case 7:
                    Marketplace.viewListings();
                    break;
                case 8:
                    notify.interrupt();
                    System.out.println("Thank you for using EcoThreads Manager!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void addCloth() {
        if (count >= clothArray.length) {
            System.out.println("Cloth list is full!");
            return;
        }
        System.out.print("Enter cloth name: ");
        String name = sc.nextLine();
        System.out.print("Enter category (Reusable/Recyclable/Upcyclable): ");
        String category = sc.nextLine();

        clothArray[count++] = new Cloth(name, category);
        System.out.println("Cloth added successfully!");
    }

    public static void viewClothes() {
        if (count == 0) {
            System.out.println("No clothes added yet.");
            return;
        }
        System.out.println("\nYour Clothes:");
        for (int i = 0; i < count; i++) {
            System.out.println(clothArray[i]);
        }
    }

    public static void sellOrExchange() {
        System.out.print("Enter cloth name to sell/exchange: ");
        String clothName = sc.nextLine();
        Marketplace.addListing(clothName);
    }
}


class Cloth {
    String name;
    String category;

    public Cloth(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public String toString() {
        return name + " - " + category;
    }
}

class NotificationThread extends Thread {
    public void run() {
        try {
            while (true) {
                System.out.println("\n[Notification]: Remember to attend upcoming recycling events!");
                Thread.sleep(10000000);
            }
        } catch (InterruptedException e) {
            System.out.println("\n[Notification Stopped]");
        }
    }
}

class SuggestionManager {
    public static void showDonationCenters() {
        System.out.println("\nNearby Donation Centers:");
        System.out.println("1. Bodhimaram - Salem");
        System.out.println("2. Anbalayam - Salem");
    }

    public static void showDIYIdeas() {
        System.out.println("\nDIY Ideas:");
        System.out.println("- Turn old jeans into a bag.");
        System.out.println("- Make cushion covers from T-shirts.");
    }

    public static void showRecyclingUnits() {
        System.out.println("\nNearby Recycling Units:");
        System.out.println("1. Textile Recycle Co. - Salem");
        System.out.println("2. ClothCycle Ltd. - Salem");
    }
}

class Marketplace {
    static String[] listings = new String[50];
    static int index = 0;

    public static void addListing(String clothName) {
        if (index < listings.length) {
            listings[index++] = clothName;
            System.out.println("Item listed for sale/exchange!");
        } else {
            System.out.println("Marketplace is full.");
        }
    }

    public static void viewListings() {
        System.out.println("\nMarketplace Listings:");
        if (index == 0) {
            System.out.println("No items listed yet.");
            return;
        }
        for (int i = 0; i < index; i++) {
            System.out.println("- " + listings[i]);
        }
    }
}


     
