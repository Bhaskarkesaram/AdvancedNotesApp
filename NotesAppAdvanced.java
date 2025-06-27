import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class NotesAppAdvanced {

    private static final String FILE_NAME = "notes.txt";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== Notes App (Advanced) =====");
            System.out.println("1. Write a new note");
            System.out.println("2. View all notes");
            System.out.println("3. Search note by title");
            System.out.println("4. Delete note by title");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    writeNote();
                    break;
                case 2:
                    readNotes();
                    break;
                case 3:
                    searchNoteByTitle();
                    break;
                case 4:
                    deleteNoteByTitle();
                    break;
                case 5:
                    System.out.println("Exiting the app.");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }

        } while (choice != 5);
    }

    private static void writeNote() {
        System.out.print("Enter note title: ");
        String title = scanner.nextLine();
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        System.out.println("Enter your note (type 'END' to finish):");

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write("=== " + title + " ===\n");
            writer.write("Timestamp: " + timestamp + "\n");
            while (true) {
                String line = scanner.nextLine();
                if (line.equalsIgnoreCase("END")) break;
                writer.write(line + "\n");
            }
            writer.write("----END NOTE----\n");
            System.out.println("Note saved successfully.");
        } catch (IOException e) {
            System.out.println("Error writing note: " + e.getMessage());
        }
    }

    private static void readNotes() {
        System.out.println("\n----- All Notes -----");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean isEmpty = true;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                isEmpty = false;
            }
            if (isEmpty) {
                System.out.println("No notes available.");
            }
        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
        }
    }

    private static void searchNoteByTitle() {
        System.out.print("Enter the title to search: ");
        String searchTitle = scanner.nextLine().toLowerCase();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean printing = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("=== ")) {
                    String currentTitle = line.substring(4, line.length() - 4).toLowerCase();
                    if (currentTitle.equals(searchTitle)) {
                        found = true;
                        printing = true;
                        System.out.println("\n--- Note Found ---");
                    } else {
                        printing = false;
                    }
                }

                if (printing) {
                    System.out.println(line);
                }

                if (line.equals("----END NOTE----")) {
                    printing = false;
                }
            }

            if (!found) {
                System.out.println("Note with title '" + searchTitle + "' not found.");
            }

        } catch (IOException e) {
            System.out.println("Error searching notes: " + e.getMessage());
        }
    }

    private static void deleteNoteByTitle() {
        System.out.print("Enter the title to delete: ");
        String deleteTitle = scanner.nextLine().toLowerCase();

        File inputFile = new File(FILE_NAME);
        File tempFile = new File("temp_notes.txt");

        boolean found = false;

        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String line;
            boolean skip = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("=== ")) {
                    String currentTitle = line.substring(4, line.length() - 4).toLowerCase();
                    if (currentTitle.equals(deleteTitle)) {
                        found = true;
                        skip = true;
                        continue;
                    }
                }

                if (skip && line.equals("----END NOTE----")) {
                    skip = false;
                    continue;
                }

                if (!skip) {
                    writer.write(line);
                    writer.newLine();
                }
            }

            if (found) {
                if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                    throw new IOException("Could not replace original file.");
                }
                System.out.println("Note deleted successfully.");
            } else {
                tempFile.delete(); // Clean up temp file if note not found
                System.out.println("Note with title '" + deleteTitle + "' not found.");
            }

        } catch (IOException e) {
            System.out.println("Error deleting note: " + e.getMessage());
        }
    }
}
