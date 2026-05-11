package hust.soict.hedspi.aims.media;

import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable{
    private String artist;

    private ArrayList<Track> tracks = new ArrayList<Track>();

    public CompactDisc(String artist, ArrayList<Track> tracks) {
        this.artist = artist;
        this.tracks = tracks;
    }

    public CompactDisc(String title, String category, float cost) {
        super();
        setCategory(category);
        setTitle(title);
        setCost(cost);
    }

    public CompactDisc(int id, String title, String category, float cost, int length, String director, String artist, ArrayList<Track> tracks) {
        super(id, title, category, cost, length, director);
        this.artist = artist;
        this.tracks = tracks;
    }

    public CompactDisc (){
        super();
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }

    public void addTrack(Track track){
        if (!tracks.contains(track)){
            tracks.add(track);
            System.out.println("Track added !");
        } else {
            System.out.print("Track already exists !");
        }
    }

    public void removeTrack(Track track){
        if(tracks.contains(track)){
            tracks.remove(track);
            System.out.print("Track removed !");
        } else {
            System.out.print("Track does not exist !");
        }
    }

    @Override
    public int getLength(){
        int total = 0;
        for(Track track : tracks){
            total += track.getLength();
        }
        return total;
    }

    @Override
    public void play(){
        System.out.println("Playing CD: " + getTitle());
        System.out.println("CD length: " + getLength());

        for(Track track : tracks){
            track.play();
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("CD - ").append(getTitle()).append(" - ").append(getCategory())
                .append(" - ").append(artist).append(" - ").append(getDirector())
                .append(" - ").append(getLength()).append(" min: ").append(getCost()).append(" $\n");
        if (!tracks.isEmpty()){
            sb.append(" Tracks: \n");
            for(Track track : tracks){
                sb.append("     ").append(track.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}
