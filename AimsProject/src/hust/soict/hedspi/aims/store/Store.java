package hust.soict.hedspi.aims.store;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;

import java.util.ArrayList;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();

    public void addMedia(Media media){
        if(media == null){
            System.out.println("Cannot add a null disc");
            return;
        }
        if(itemsInStore.contains(media)){
            System.out.println("Media already exists");
        } else {
            System.out.println("The item has been added");
        }
    }

    public void removeMedia(Media media){
        if(itemsInStore.remove(media)){
            System.out.println("The item has been removed");
        } else {
            System.out.println("No matched item found in store");
        }
    }

    public void printStore(){
        System.out.println("***********************STORE***********************");
        if(itemsInStore.isEmpty()){
            System.out.println("No items in store");
        } else {
            for(int i = 0; i < itemsInStore.size(); i++){
                System.out.println((i+1) + ". " + itemsInStore.get(i).toString());
            }
        }
        System.out.println("***************************************************");
    }

    public Media searchByTitle(String title){
        for(Media media : itemsInStore){
            if(media.isMatch(title)){
                return media;
            }
        }
        return null;
    }
}
