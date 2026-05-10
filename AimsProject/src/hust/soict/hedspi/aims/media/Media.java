package hust.soict.hedspi.aims.media;

import java.util.Comparator;

public abstract class Media {
    private int id;
    private String title;
    private String category;
    private float cost;

    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();

    public Media(){

    }

    public Media(int id, String title, String category, float cost) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
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
    public boolean equals(Object obj){
        if(obj == null || !(obj instanceof Media)){
            return false;
        }

        Media other = (Media) obj;
        if(this.title == null || other.getTitle() == null){
            return false;
        }
        return this.title.equalsIgnoreCase(other.getTitle());
    }
}
