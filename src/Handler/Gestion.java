package Handler;

import Model.Account;

import java.util.ArrayList;
import java.util.List;

public class Gestion implements IGestion{

    private static List<Account> listAccounts = new ArrayList();
    private static List<String>  listOperation = new ArrayList();
    @Override
    public String commandHandler ( String in, String nom ) {
        String[] splittedMsg = in.split(" ");
        if (in.startsWith("CREATION") && (in.length() > 9)){
            String retour = createAccount(in.substring(10));
            if (!retour.startsWith("error")){
                logOperation(in, nom);
            }
            return retour;
        } else if (in.startsWith("TRANSFER") && (in.length() > 9)){
            String retour = transfert(in.substring(10),nom);
            if (!retour.startsWith("error")){
                logOperation(in, nom);
            }
            return retour;
        } else if (in.startsWith("log") && (in.length() > 4)){
            return listOperations();
        } else if (in.startsWith("CREDIT") && (in.length() > 7)) {
            String retour = credit(in.substring(7),nom);
            if (!retour.startsWith("error")){
                logOperation(in, nom);
            }
            return retour;
        } else if (in.startsWith("DEBIT") && (in.length() > 5)) {
            String retour = debit(in.substring(6),nom);
            if (!retour.startsWith("error")){
                logOperation(in, nom);
            }
            return retour;
        } else if (in.equals("SOLDE")) {
            return solde(nom);
        } else {
            return "Wrong command!";
        }
    }

    @Override
    public String createAccount (String name){
        for (Account i : listAccounts){
            if (i.equals(name)){
                return "error :Name already used, please enter another name.";
            }
        }
        listAccounts.add(new Account(name));
        return "Le compte est créé avec succés";
        }
    @Override
    public String credit ( String montant, String name ) {
        boolean e = true;
        for (Account i : listAccounts) {
            if (i.getClientName().equals(name)){
                i.setSolde(i.getSolde() + Double.parseDouble(montant));
                e = false;
            }
        }
        if (e){
            return "error credit";
        }
        return montant + "added successfully";
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
        return "error debit";
    }

    @Override
    public String solde ( String name ) {
        for (Account i : listAccounts) {
            if (i.getClientName().equals(name)){
                return String.valueOf(i.getSolde());
            }
        }
        return "error solde" + name;
    }

    @Override
    public String transfert ( String in, String nom ) {
        String[] inp = in.split(" ");
        String montant = inp[1];
        String receiver = inp[0];
        for (Account i : listAccounts) {
            if (i.getClientName().equals(nom)){
                for (Account j : listAccounts) {
                    if (j.getClientName().equals(receiver)){
                        if (i.getSolde() < Double.parseDouble(montant)){
                            return "Solde insuffisant";
                        } else {
                            i.setSolde(i.getSolde()-Double.parseDouble(montant));
                            debit(montant, nom);
                            credit(montant, receiver);
                            return "Operation de transfert faite avec succées";
                        }
                    } else {
                        return "Reciever not found!";
                    }
                }
            }
        }
        return "error in transfer";
    }

    @Override
    public String logOperation ( String in, String nom ) {
        return null;
    }

    @Override
    public String listOperations () {
        return listOperations();
    }
}
