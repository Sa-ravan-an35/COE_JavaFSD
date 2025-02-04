package Week1;

import java.io.*;
import java.util.*;

class FileIO {
    private List<String> users = new ArrayList<>();

    public void addUser(String name, String email) {
        users.add(name + "," + email);
    }

    public void saveUsersToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String user : users) {
                writer.write(user);
                writer.newLine();
            }
        }
    }

    public void loadUsersFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            users.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                users.add(line);
            }
        }
    }

    public static void main(String[] args) throws IOException {
    	FileIO um = new FileIO();
        um.addUser("John Doe", "john@example.com");
        um.addUser("Jane Smith", "jane@example.com");

        String filename = "users.txt";
        um.saveUsersToFile(filename);
        um.loadUsersFromFile(filename);
    }
}

