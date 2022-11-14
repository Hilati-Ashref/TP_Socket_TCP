package Handler;

import Model.Account;

import java.util.ArrayList;
import java.util.List;

public class Gestion implements IGestion{

    private static List<Account> listAccounts = new ArrayList();
    @Override
    public String commandHandler ( String in, String nom ) {
        String[] splittedMsg = in.split(" ");
        if (in.startsWith("CREATION") && (in.length() > 9)){
            listAccounts.add(new Account(in.substring(10)));
            return "Le compte est créé avec succés";
        } else if (in.startsWith("CREDIT") && (in.length() > 7)) {
            return credit(in.substring(7),nom);
        } else if (in.startsWith("DEBIT") && (in.length() > 5)) {
            return debit(in.substring(6),nom);
        } else if (in.equals("SOLDE")) {
            return solde(nom);
        } else {
            return "Wrong command!";
        }
    }

    @Override
    public String credit ( String montant, String name ) {
        for (Account i : listAccounts) {
            if (i.getClientName().equals(name)){
                i.setSolde(i.getSolde() + Double.parseDouble(montant));
            }
        }
        return "error";
    }

    @Override
    public String debit ( String montant, String name ) {
        for (Account i : listAccounts) {
            if (i.getClientName().equals(name)){
                if (i.getSolde() < Double.parseDouble(montant)){
                    return "Solde insuffisant";
                } else {
                    i.setSolde(i.getSolde()-Double.parseDouble(montant));
                    return "Operation faite avec succées";
                }
            }
        }
        return "error";
    }

    @Override
    public String solde ( String name ) {
        for (Account i : listAccounts) {
            if (i.getClientName().equals(name)){
                return String.valueOf(i.getSolde());
            }
        }
        return "error" + name;
    }
}
