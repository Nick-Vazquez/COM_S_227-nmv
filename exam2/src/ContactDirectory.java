import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactDirectory {
    ArrayList<Contact> contactArrayList = new ArrayList<>();

    public ContactDirectory()
    {

    }

    public void addContact(Contact c)
    {
        contactArrayList.add(c);
    }

    public void addFromFile(String filename) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(filename);

        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            String[] details = line.split(", ");

            String name = details[0];
            String phone = details[1];

            Contact c = new Contact(name, phone);
            this.addContact(c);
        }
    }

    public String lookUp(String name)
    {
        for (int i = 0; i < contactArrayList.size(); i++)
        {
            if (contactArrayList.get(i).getName().equals(name))
            {
                return contactArrayList.get(i).getPhoneNumber();
            }
        }
        return null;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Contact one = new Contact("Nick Vazquez", "5633431530");
        Contact two = new Contact("John Doe", "1234567890");
        Contact three = new Contact("Jane Doe", "9876543210");

        ContactDirectory directory = new ContactDirectory();

        //directory.addContact(one);
        //directory.addContact(two);
        //directory.addContact(three);

        directory.addFromFile("exam2/src/Names.txt");
        System.out.println("test");
    }
}
