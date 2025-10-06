import java.util.ArrayList;
import java.util.Scanner;

class Contact {
    String name;
    String phone;

    Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}

public class ContactManager {
    public static void main(String[] args) {
        ArrayList<Contact> contacts = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Add Contact\n2. View Contacts\n3. Search\n4. Update Contact\n5. Delete Contact\n6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Phone: ");
                    String phone = sc.nextLine();
                    contacts.add(new Contact(name, phone));
                    break;

                case 2:
                    for (int i = 0; i < contacts.size(); i++)
                        System.out.println(i + ": " + contacts.get(i).name + " - " + contacts.get(i).phone);
                    break;

                case 3:
                    System.out.print("Search Name: ");
                    String search = sc.nextLine();
                    boolean found = false;
                    for (Contact c : contacts) {
                        if (c.name.equalsIgnoreCase(search)) {
                            System.out.println("Found: " + c.name + " - " + c.phone);
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("Contact not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter index to update: ");
                    int idx = sc.nextInt();
                    sc.nextLine();
                    if (idx >= 0 && idx < contacts.size()) {
                        System.out.print("New Name: ");
                        contacts.get(idx).name = sc.nextLine();
                        System.out.print("New Phone: ");
                        contacts.get(idx).phone = sc.nextLine();
                    } else {
                        System.out.println("Invalid index.");
                    }
                    break;

                case 5:
                    System.out.print("Enter index to delete: ");
                    int delIdx = sc.nextInt();
                    if (delIdx >= 0 && delIdx < contacts.size())
                        contacts.remove(delIdx);
                    else
                        System.out.println("Invalid index.");
                    break;

                case 6:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 6);
        sc.close();
    }
}
