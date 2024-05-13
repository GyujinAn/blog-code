public class EuclideanMethod {

    static int calculateGreatestCommonDivisor(int x, int y) {
        if (y == 0) {
            return x;
        } else {
            return calculateGreatestCommonDivisor(y, x % y);
        }
    }

    public static void main(String[] args) {
        System.out.println(calculateGreatestCommonDivisor(22,8));
    }
}
