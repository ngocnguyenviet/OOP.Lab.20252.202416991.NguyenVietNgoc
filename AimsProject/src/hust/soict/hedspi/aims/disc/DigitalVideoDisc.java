package hust.soict.hedspi.aims.disc;

public class DigitalVideoDisc {
    private String title;
    private String category;
    private String director;
    private int length;
    private float cost;

    private int id;
    private static int nbDigitalVideoDisc = 0;

    public DigitalVideoDisc(String title) {
        this.title = title;
    }

    public DigitalVideoDisc(String category, String title, float cost) {
        this.category = category;
        this.title = title;
        this.cost = cost;

        nbDigitalVideoDisc++;
        this.id = nbDigitalVideoDisc;
    }

    public DigitalVideoDisc(String director, String category, String title, float cost) {
        this.director = director;
        this.category = category;
        this.title = title;
        this.cost = cost;

        nbDigitalVideoDisc++;
        this.id = nbDigitalVideoDisc;
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        this.title = title;
        this.category = category;
        this.director = director;
        this.length = length;
        this.cost = cost;

        nbDigitalVideoDisc++;
        this.id = nbDigitalVideoDisc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public boolean isMatch(String title){
        if(title == null || title.trim().isEmpty()){
            return false;
        }

        String lowerCaseTitle = this.title.toLowerCase();
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
        return id + ". DVD - " + title + " - " + category + " - " + director + " - " + length + ": " + cost + "$";
    }
}
