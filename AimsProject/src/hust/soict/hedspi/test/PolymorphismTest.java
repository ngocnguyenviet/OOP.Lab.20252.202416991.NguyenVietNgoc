package hust.soict.hedspi.test;

import hust.soict.hedspi.aims.media.*;

import java.util.ArrayList;
import java.util.List;

public class PolymorphismTest {
    public static void main(String[] args) {
        List<Media> media = new ArrayList<Media>();

        //Media cd = new CompactDisc("Greatest Hits", "Music", 15.5f);
        Media dvd = new DigitalVideoDisc("Inception", "Sci-fi", 25.0f);
        //Media book = new Book("Clean Code", "Technology", 30.0f);

        //media.add(cd);
        media.add(dvd);
        //media.add(book);

        for(Media m : media){
            System.out.println(m.toString());
        }
    }
}
