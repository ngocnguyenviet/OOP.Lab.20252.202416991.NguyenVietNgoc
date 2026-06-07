package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

public class DigitalVideoDisc extends Disc implements Playable {

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, director, length, cost);
    }

    public DigitalVideoDisc(String title) {
        super(title);
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        super(title, category, cost);
    }

    public DigitalVideoDisc(String title, String director, String category, float cost) {
        super(title, director, category, cost);
    }

    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing DVD: " + this.getTitle());
            System.out.println("DVD length: " + this.getLength());
        } else {
            throw new PlayerException("Error: DVD length is non-positive!");
        }
    }


    public boolean isMatch(String title) {
        return this.getTitle().equalsIgnoreCase(title);
    }

    @Override
    public String toString() {
        return getId() + ". DVD - " + this.getTitle() + " - " + this.getCategory() + " - "
                + getDirector() + " - " + getLength() + " minutes: " + getCost() + " $";
    }
}
