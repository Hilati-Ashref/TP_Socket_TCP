package Handler;

public interface IGestion {
    public String commandHandler ( String in, String nom );
    public String createAccount (String name);
    public String credit(String montant, String name);
    public String debit(String montant, String name);
    public String solde( String name );
    public String transfert( String in, String nom);
    public String logOperation( String in, String nom);
    public String listOperations();
}
