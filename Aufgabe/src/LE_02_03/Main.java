package LE_02_03;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Area and Volume Calculation: ");

        while (true) {
            System.out.println();
            System.out.println("Choose type of packaging: ");
            System.out.println("1 - Circle and cylinder");
            System.out.println("2 - Rectangle and cuboid");
            System.out.println("3 - Equilateral triangle and prism");
            System.out.println("4 - Exit");

            int choice = scanner.nextInt();

            if (choice == 4) {
                System.exit(0);
            }

            switch (choice){
                case 1:
                    System.out.println("=======Area circle and volume cylinder: =======");
                    final float PI = 3.14f;
                    System.out.print("Enter the radius of the circle: ");
                    float radius = scanner.nextFloat();
                    float area_circle = PI * (float)Math.pow(radius,2);
                    System.out.println("Area of the circle: " + String.format("%.2f", area_circle));

                    System.out.print("Enter the height of cylinder: ");
                    float height = scanner.nextFloat();
                    float volume_cylinder    = area_circle * height;
                    System.out.println("Volume of the cylinder: " + String.format("%.2f", volume_cylinder));
                    continue;

                case 2:
                    System.out.println("=======Area rectangle and volume cuboid: =======");
                    System.out.print("Enter the first side of the rectangle: ");
                    float side1 = scanner.nextFloat();
                    System.out.print("Enter the second side of the rectangle: ");
                    float side2 = scanner.nextFloat();
                    float area_rectangle = side1*side2;
                    System.out.println("Area of rectangle: " + String.format("%.2f", area_rectangle));
                    System.out.print("Enter the third side of the cuboid: ");
                    float side3 = scanner.nextFloat();
                    System.out.println("Volume of the cuboid: "+ String.format("%.2f", area_rectangle*side3));
                    continue;

                case 3:
                    System.out.println("=======Area equilateral triangle and volume triangular prism: =======");
                    System.out.print("Enter the length of an equilateral triangle: ");
                    float side_triangle = scanner.nextFloat();
                    float area_eq_triangle = (float)(Math.pow(side_triangle,2)*Math.sqrt(3))/4;
                    System.out.println("Area of equilateral triangle: " + String.format("%.2f", area_eq_triangle));

                    System.out.print("Enter the height of triangular prism: ");
                    float height_prism = scanner.nextFloat();
                    System.out.println("Volume of triangular prism: " + String.format("%.2f",height_prism*area_eq_triangle));
                    continue;

                default:
                    System.out.println("Invalid choice");
            }
            System.out.println();
        }
    }
}
