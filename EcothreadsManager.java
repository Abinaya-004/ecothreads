public class EcothreadsManager {
    static Cloth[] clothArray = new Cloth[100]; 
    static int count = 0; 
    static final String FILE_NAME = "donations.csv";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        NotificationThread notify = new NotificationThread();
        notify.start();

        while (true) {
            System.out.println("\n--- EcoThreads Manager ---");
            System.out.println("1. Add Cloth");
            System.out.println("2. View All Clothes");
            System.out.println("3. Save Donations");
            System.out.println("4. Load Donations");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addCloth();
                    break;
                case 2:
                    viewClothes();
                    break;
                case 3:
                    saveToFile();
                    break;
                case 4:
                    loadFromFile();
                    break;
                case 5:
                    notify.interrupt();
                    System.out.println("Thank you for using EcoThreads Manager!");
                    return;
                default:
                    System.out.println("Invalid choice.");
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

        clothArray[count] = new Cloth(name, category);
        count++;
        System.out.println("Cloth added successfully!");
    }

    public static void viewClothes() {
        if (count == 0) {
            System.out.println("No clothes added.");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println(clothArray[i].name + "Your Clothes:"+" - " + clothArray[i].category);
        }
    }





 public class NotificationThread extends Thread {
   public NotificationThread() {
   }

   public void run() {
      try {
         while(true) {
            System.out.println("Reminder: Attend the nearby textile recycling event this weekend!");
            Thread.sleep(1000000L);
         }
      } catch (InterruptedException var2) {
         System.out.println("Notification thread stopped.");
      }
   }
}






public class Cloth {
   public String name;
   public String category;

    public Cloth(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public String toString() {
        return name + "," + category;
    }
}
