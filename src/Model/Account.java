package Model;

import java.util.concurrent.atomic.AtomicInteger;

public class Account {
    private static final AtomicInteger count = new AtomicInteger(1);
    private int id = 0;
    private double solde = 0;
    private String clientName;

    public Account () {
    }

    public Account ( String clientName ) {
        this.id         = count.incrementAndGet();
        this.clientName = clientName;
    }

    public int getId () {
        return id;
    }

    public void setId ( int id ) {
        this.id = id;
    }

    public double getSolde () {
        return solde;
    }

    public void setSolde ( double solde ) {
        this.solde = solde;
    }

    public String getClientName () {
        return clientName;
    }

    public void setClientName ( String clientName ) {
        this.clientName = clientName;
    }
}
