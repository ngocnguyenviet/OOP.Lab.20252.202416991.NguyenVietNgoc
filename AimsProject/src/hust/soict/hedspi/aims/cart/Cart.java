package hust.soict.hedspi.aims.cart;

import java.util.ArrayList;
import java.util.Collections;

public class Cart {
    private ArrayList<Media> itemsOrdered = new ArrayList<Media>();

    public void addMedia(Media media){
        if(itemsOrdered.contains(media)){
            System.out.println("Media already exists");
        } else {
            itemsOrdered.add(media);
            System.out.println("Media has been added");
        }
    }

    public void removeMedia(Media media){
        if(itemsOrdered.contains(media)){
            System.out.println("Media already exists");
        } else {
            System.out.println("Media is not in the cart");
        }
    }

    public float totalCost(){
        float sum = 0;
        for(Media media : itemsOrdered){
            sum += media.getCost();
        }
        return sum;
    }

    public void print(){
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items: ");
        int i = 1;
        for(Media media : itemsOrdered){
            System.out.println(i + ". " + media.getTitle());
            i++;
        }
        System.out.println("Total Cost: " + totalCost() + " $");
        System.out.println("***************************************************");
    }

    public void searchByID(int ID){
        boolean found = false;
        for(Media media : itemsOrdered){
            if(media.getId() == ID){
                System.out.println("Found match");
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("Not found");
        }
    }

    public Media searchByTitle(String title){
        for(Media media : itemsOrdered){
            if(media.isMatch(title))
                return media;
        }
        return null;
    }

    public void sortByTitle() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
    }

    public void sortByCost(){
        Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
    }

    public void emptyCart(){
        itemsOrdered.clear();
    }
}
