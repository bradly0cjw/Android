
public class Main {
    public static void main(String[] args) {
    Doctor doc = new Doctor();
    Driver driver = new Driver();
    doc.show();
    doc.doCPR();
    doc.drive();
    driver.show();
    driver.drive();
    driver.doCPR();
    }
}
class Doctor implements Drive{
    public void show(){
        System.out.println("I am a doctor");
    }

    public void doCPR(){
        System.out.println("I am a doctor");
        System.out.println("Of course, I can do CPR");
    }

    public void drive(){
        System.out.println("I am a doctor");
        System.out.println("Of course, I can drive");
    }


}

class Driver implements CPR{
    public void show(){
        System.out.println("I am a driver");
    }

    public void drive(){
        System.out.println("I am a driver");
        System.out.println("Of course, I can drive");
    }

    public void doCPR(){
        System.out.println("I am a driver");
        System.out.println("Of course, I can do CPR");
    }
}

interface CPR{
    public void doCPR();
}

interface Drive{
    public void drive();
}