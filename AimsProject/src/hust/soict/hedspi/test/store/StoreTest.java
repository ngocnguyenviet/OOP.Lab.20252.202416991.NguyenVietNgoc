package hust.soict.hedspi.test.store;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;

public class StoreTest {
    static void main(String [] args){
        Store Apple_Music_Store = new Store();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        Apple_Music_Store.addDVD(dvd1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        Apple_Music_Store.addDVD(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", 18.99f);
        Apple_Music_Store.addDVD(dvd3);

        Apple_Music_Store.viewCart1();

        Apple_Music_Store.removeDVD(dvd2);

        Apple_Music_Store.viewCart1();
    }
}
