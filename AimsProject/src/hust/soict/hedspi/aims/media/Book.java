package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media {
    private List<String> authors = new ArrayList<String>();

    public Book(String title, String category, float cost) {
        super(title, category, cost);
    }

    public Book(String title) {
        super(title);
    }

    public void addAuthor(String authorName) {
        if (authors.contains(authorName)) {
            System.out.println("Author already exists!");
            return;
        }
        authors.add(authorName);
    }
    public void removeAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            System.out.println("Author does not exists!");
            return;
        }
        authors.remove(authorName);
    }

    @Override
    public String toString() {
        String result = getId() + ". Book - "
                + this.getTitle() + " - "
                + this.getCategory() + " - ";

        if (authors.isEmpty()) {
            result += "No author";
        } else {
            for (String author : authors) {
                result += author + ", ";
            }
            result = result.substring(0, result.length() - 2);
        }

        result += ": " + getCost() + " $";
        return result;
    }
}
