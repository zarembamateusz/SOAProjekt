package models;

public class Zone {
    private long id;
    private int takenSeat;
    private int purchasedTickets;

    public long getId() {
        return id;
    }

    public Zone(long id, int takenSeat, int purchasedTickets) {
        this.id = id;
        this.takenSeat = takenSeat;
        this.purchasedTickets = purchasedTickets;
    }

    public void setId(long id) {
        this.id = id;

    }

    public int getTakenSeat() {
        return takenSeat;
    }

    public void setTakenSeat(int takenSeat) {
        this.takenSeat = takenSeat;
    }

    public int getPurchasedTickets() {
        return purchasedTickets;
    }

    public void setPurchasedTickets(int purchasedTickets) {
        this.purchasedTickets = purchasedTickets;
    }
}
