import java.util.Scanner;

public class GrigoryLocation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int K = scanner.nextInt();
        double sumX = 0, sumY = 0, sumZ = 0, sumD = 0;

        for (int i = 0; i < K; i++) {
            double Xi = scanner.nextDouble();
            double Yi = scanner.nextDouble();
            double Zi = scanner.nextDouble();
            double Di = scanner.nextDouble();

            sumX += Xi / Di;
            sumY += Yi / Di;
            sumZ += Zi / Di;
            sumD += 1 / Di;
        }

        double XG = sumX / sumD;
        double YG = sumY / sumD;
        double ZG = sumZ / sumD;

        System.out.println(XG + " " + YG + " " + ZG);
    }
}