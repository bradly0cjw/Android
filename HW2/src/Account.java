public class Account {
    String name;
    String account;
    int money;

    public Account(String name, int money) {
        this.name = name;
        this.money = money;
        this.account = "Bank" + (int)(Math.random() * 900000 + 100000);
        System.out.println("您的帳戶訊息:");
        System.out.println("帳號: "+ account);
        System.out.println("姓名: " + name);
        System.out.println("帳戶餘額: " + money);
        System.out.println();
    }

    public void deposit(int amount) {
        if (amount > 0) {
            money += amount;
            System.out.println("您存入" + amount + "元!");
            System.out.println("帳號: "+ account);
            System.out.println("姓名: " + name);
            System.out.println("餘額: " + money);
            System.out.println();
        } else {
            System.out.println("存款金額需大於0");
            System.out.println();
        }
    }
    public void withdraw(int amount) {
        if (amount > 0 && amount <= money) {
            money -= amount;
            System.out.println("您提領" + amount + "元!");
            System.out.println("帳號: "+ account);
            System.out.println("姓名: " + name);
            System.out.println("餘額: " + money);
            System.out.println();
        } else if (amount > money) {
            System.out.println("存款餘額不足!");
            System.out.println();
        } else {
            System.out.println("提領金額需大於0");
            System.out.println();
        }
    }
    public void chkBalance() {
        System.out.println("帳號: "+ account);
        System.out.println("姓名: " + name);
        System.out.println("帳戶餘額: " + money);
        System.out.println();
    }

    public void showInfo() {
        System.out.println("帳號: "+ account);
        System.out.println("姓名: " + name);
        System.out.println();
//        System.out.println("帳戶餘額: " + money);
    }
}
