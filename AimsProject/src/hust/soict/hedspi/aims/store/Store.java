package hust.soict.hedspi.aims.store;

import hust.soict.hedspi.aims.disc.DigitalVideoDisc;

public class Store {
    public static final int MAX_NUMBER_DISC = 100;

    private int qtyInStore = 0;

    private final DigitalVideoDisc[] itemsInStore = new DigitalVideoDisc[MAX_NUMBER_DISC];

    public void addDVD(DigitalVideoDisc disc){
        if(disc == null){
            System.out.println("Cannot add a null disc");
            return;
        }

        if(qtyInStore == MAX_NUMBER_DISC){
            System.out.println("The store is full !");
            return;
        }

        itemsInStore[qtyInStore] = disc;
        qtyInStore++;
        System.out.println("The disc has been added to the store !");
    }

    public void removeDVD(DigitalVideoDisc disc){
        boolean found = false;

        if(qtyInStore == 0){
            System.out.println("The store is empty. Nothing to remove!");
            return;
        }

        for(int i = 0; i < qtyInStore; i++){
            if(itemsInStore[i] == disc){
                found = true;
                for(int j = i; j < qtyInStore; j++){
                    itemsInStore[j] = itemsInStore[j + 1];
                }
                itemsInStore[qtyInStore - 1] = null;
                qtyInStore--;

                System.out.println("The disc has been removed from the store !");
                break;
            }
        }
        if(!found){
            System.out.println("No matched disc in store. Please check again !");
        }
    }

    public void viewCart1(){
        if(qtyInStore == 0){
            System.out.println("The store is empty. Nothing to view!");
            return;
        }

        for(int i = 0; i < qtyInStore; i++){
            System.out.print(itemsInStore[i].getTitle());
        }
    }
}
