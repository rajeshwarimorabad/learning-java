import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("This is bank application");
        System.out.println("--------------------------");
        // get name,age and acc from user
        System.out.println("Enter Name:");
        String name = scanner.nextLine();
        System.out.println("Enter ur age:");
        int age = scanner.nextInt();
        scanner.nextLine();
        Person p1 = new Person(name,age);
        System.out.println("Enter ur acc no:");
        int acc = scanner.nextInt();
        scanner.nextLine();
        Bank bank = new Bank();
        bank.addAccount(123,"SAVINGS");
        bank.addAccount(234,"CURRENT");
        bank.addAccount(345,"SAVINGS");
        bank.addAccount(456,"CURRENT");
        String trnMsg = "Hello "+p1.getName()+", ";
        if(bank.isAccExist(acc)) {
            
            // check given account is savings or current acc
            String accType = bank.getAccountType(acc);
            BankAccount accountObject = null;
            switch(accType){
                case "SAVINGS": accountObject = new SavingsAccount();
                    break;
                case "CURRENT": accountObject = new CurrentAccount();
                    break;
            }
            System.out.println("Enter 1 = Withdrow, 2 = Deposite, 3= check balance");
            int transType = scanner.nextInt();
            scanner.nextLine();
            switch(transType){
                case 1: System.out.println("Enter amount to withdrow:");
                int withAmt = scanner.nextInt();
                scanner.nextLine();
                trnMsg = trnMsg+accountObject.withdrow(withAmt);
                    break;
                case 2: System.out.println("Enter amount to deposite:");
                int depositeAmt = scanner.nextInt();
                scanner.nextLine();
                trnMsg = trnMsg+accountObject.deposite(depositeAmt);
                    break;
                case 3: double balance = accountObject.getBalance();
                    trnMsg = trnMsg+" Current available balance ="+balance;
                    break;
            }
        }else{
            trnMsg = trnMsg+", Your entered  acc no is incorrect";
        }
         System.out.println(trnMsg);
    }
}
class Person{
    private String name = null;
    private int age = 0;
    public Person(String name,int age){
        this.name = name;
        this.age = age;
    }
    
    String getName(){
        return this.name;
    }
    int getAge(){
        return this.age;
    }
}

abstract class BankAccount{
    protected double balance;
    abstract String withdrow(double amount);
    double getBalance(){
        return this.balance;
    }
    String deposite(double amount){
        if(amount > 0){
            this.balance += amount;
            return "Available Balance :"+this.balance;
        }
        return "invalid amount";
    }
}

class SavingsAccount extends BankAccount{
    String withdrow(double amount){
        if(this.getBalance() > 0){
            this.balance -= amount;
            return "Avaliable Balance = "+this.getBalance();
        }
        return "Insuffucient Balance";
    }
}

class CurrentAccount extends BankAccount{
    String withdrow(double amount){
        if(this.getBalance() > -5000){
            this.balance -= amount;
            return "Avaliable Balance = "+this.getBalance();
        }
        return "Insuffucient Balance";
    }
}
/*
This class handles the accounts
*/
class Bank{
    private Map<Integer,String> accounts = new HashMap<>();
    Map printAccounts(){
        return this.accounts;
    }
    
    String addAccount(int acc,String type) {
        // check account exist before add
        if(!this.isAccExist(acc)) {
             this.accounts.put(acc,type);
             return "Successfully added";
        }
        return "Account already present";
    }
    
    boolean isAccExist(int acc){
        return  this.accounts.containsKey(acc);
    }
    
    String getAccountType(int acc){
        return this.accounts.get(acc);
    }
}





