package hust.soict.hedspi.aims.cart;

import hust.soict.hedspi.aims.disc.DigitalVideoDisc;

public class Cart {

    public static final int MAX_NUMBERS_ORDERED = 20;
    private int qtyOrdered = 0;
    private DigitalVideoDisc itemsOrdered[] =
            new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
    public void addDigitalVideoDisc(DigitalVideoDisc disc) {
        if(qtyOrdered == MAX_NUMBERS_ORDERED) {
            System.out.println("The cart is full!");
            return;
        }
        itemsOrdered[qtyOrdered] = disc;
        qtyOrdered++;
        System.out.println("The disc has been added!");
    }

    public void addDigitalVideoDisc(DigitalVideoDisc[] dvdList) {
        for(DigitalVideoDisc dvd : dvdList) {
            if(qtyOrdered < MAX_NUMBERS_ORDERED) {
                addDigitalVideoDisc(dvd);
            }else{
                System.out.println("The cart is full!");
                break;
            }
        }
    }

//    public void addDigitalVideoDisc(hust.soict.hedspi.aims.disc.DigitalVideoDisc... dvds) {
//        for(hust.soict.hedspi.aims.disc.DigitalVideoDisc dvd : dvds) {
//            addDigitalVideoDisc(dvd);
//        }
//    }

    public void addDigitalVideoDisc(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
        addDigitalVideoDisc(dvd1);
        addDigitalVideoDisc(dvd2);
    }

    public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
        boolean found = false;
        if(qtyOrdered == 0) {
            System.out.println("The cart is empty!");
            return;
        }

        for(int i = 0; i < qtyOrdered; i++) {
            if(itemsOrdered[i] == disc) {
                found = true;
                for(int j = i; j < qtyOrdered; j++) {
                    itemsOrdered[j] = itemsOrdered[j + 1];
                }
                itemsOrdered[qtyOrdered - 1] = null;
                qtyOrdered--;

                System.out.println("The disc has been removed!");
                break;
            }
        }

        if(!found){
            System.out.println("No matching found!");
        }
    }

    public float totalCost(){
        float sum = 0;
        for(int i = 0; i < qtyOrdered; i++){
            sum += itemsOrdered[i].getCost();
        }
        return sum;
    }

    public void viewCart1(){
        if(qtyOrdered == 0) {
            System.out.println("The cart is empty!");
            return;
        }
        for(int i = 0; i < qtyOrdered; i++){
            System.out.println(itemsOrdered[i].getTitle());
        }
    }

    public void viewCart2(){
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items: ");
        for(int i = 0; i < qtyOrdered; i++){
            System.out.println(itemsOrdered[i].toString());
        }

        System.out.println("Total Cost: " + totalCost());
        System.out.println("***************************************************");
    }

    public void searchByID(int id){
        boolean found = false;
        for(int i = 0; i < qtyOrdered; i++){
            if(itemsOrdered[i].getId() == id){
                System.out.println("Found match for ID" + id + ":");
                System.out.println(itemsOrdered[i].toString());
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("No matching found for ID: " + id);
        }
    }

    public void searchByTitle(String title){
        boolean found = false;
        System.out.println("Search results for title: \"" + title + "\"");
        for(int i = 0; i < qtyOrdered; i++){
            if (itemsOrdered[i].isMatch(title)){
                System.out.println(itemsOrdered[i].toString());
                found = true;
            }
        }
        if(!found) {
            System.out.println("No matching found for title: \"" + title + "\"");
        }
    }
}
