package jb.com.hotelsapp;

/**
 * Created by jbt on 15/01/2017.
 */

public class Hotel {
    private long id;
    private String name, address;
    private boolean isKosher;
    private int rating;

    // ctor
    public Hotel(String name, String address, boolean isKosher, int rating) {
        super();
        this.name = name;
        this.address = address;
        this.isKosher = isKosher;
        this.rating = rating;
    }



    public Hotel(long id, String name, String address, boolean isKosher, int rating) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.isKosher = isKosher;
        this.rating = rating;
    }



    // getters/setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isKosher() {
        return isKosher;
    }

    public void setKosher(boolean isKosher) {
        this.isKosher = isKosher;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", rating=" + rating;
    }
}
