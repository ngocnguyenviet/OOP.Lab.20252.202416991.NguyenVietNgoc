package hust.soict.hedspi.aims.media;

public class DigitalVideoDisc extends Disc implements Playable {
    private static int nbDigitalVideoDisc = 0;

    public DigitalVideoDisc(String title) {
        super();
        setTitle(title);
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        super();
        setCategory(category);
        setTitle(title);
        setCost(cost);
    }

    public DigitalVideoDisc(String director, String category, String title, float cost) {
        super();
        setDirector(director);
        setCategory(category);
        setTitle(title);
        setCost(cost);

    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(++nbDigitalVideoDisc, title, category, cost, length, director);
    }

    public boolean isMatch(String title){
        if(title == null || title.trim().isEmpty()){
            return false;
        }

        String lowerCaseTitle = getTitle().toLowerCase();
        String[] keywords = title.toLowerCase().split("\\s+");

        for(String word : keywords){
            if(lowerCaseTitle.contains(word)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return getId() + ". DVD - " + getTitle() + " - " + getCategory() + " - " + getDirector() + " - " + getLength() + ": " + getCost() + "$";
    }

    @Override
    public void play() {
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength());
    }
}
