public class Triangle {
    float a, b, c;
    // constructor does not need the return data type.
    public Triangle() {
        a = b = c = 0;
    }

    public Triangle(float a, float b, float c) {

        this.a = a;
        this.b = b;
        this.c = c;
        System.out.println("Construct Triangle with sides: " + a + ", " + b + ", " + c);
    }
    public float area() {
        if (!check()) return -1;
        float halfPerimeter = (a + b + c) / 2;
        return (float) Math.sqrt(halfPerimeter * (halfPerimeter - a) * (halfPerimeter - b) * (halfPerimeter - c));
    }
    public boolean check() {
        if (a + b > c && a + c > b && b + c > a) {
            System.out.println("This is a valid triangle.");
            return  true;
        } else {
            System.out.println("This is not a valid triangle.");
            return false;
        }

    }
}