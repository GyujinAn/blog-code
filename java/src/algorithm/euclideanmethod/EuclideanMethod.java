public class EuclideanMethod {

    static int calculateGreatestCommonDivisor(int x, int y) {
        if (y == 0) {
            return x;
        } else {
            return calculateGreatestCommonDivisor(y, x % y);
        }
    }

    static int calculateGreatestCommonDivisor(int [] array) {
        int result = array[0];

        for (int i = 1; i < array.length; i ++) {
            result = calculateGreatestCommonDivisor(result, array[i]);

            if (result == 1) {
                return result;
            }
        }

        return result;
    }    

    public static void main(String[] args) {
        System.out.println(calculateGreatestCommonDivisor(22,8));

        int [] array = {3 ,9, 12};
        System.out.println(calculateGreatestCommonDivisor(array));
    }
}
