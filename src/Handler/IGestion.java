package Handler;

public interface IGestion {
    public String commandHandler ( String in, String nom );
    public String credit(String montant, String name);
    public String debit(String montant, String name);
    public String solde( String name );

}
