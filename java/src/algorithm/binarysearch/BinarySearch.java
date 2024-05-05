public class BinarySearch {

    static int search(int[] array, int target) {
        int start = -1;
        int center = array.length / 2;
        int end = array.length;
        
        System.out.printf("length: %d \n", array.length);
        System.out.printf("start: %d, center: %d, end: %d \n", start, center, end);
        do {
            if (array[center] == target) {
                return center;
            }

            if (array[center] > target) {
                System.out.println("go left");
                end = center;
                center = ((center - start) / 2) + start;
                System.out.printf("start: %d, center: %d, end: %d \n", start, center, end);
                continue;
            }

            if (array[center] < target) {
                System.out.println("go right");
                start = center;
                center = ((end - center) / 2) + start;
                System.out.printf("start: %d, center: %d, end: %d \n", start, center, end);
                continue;
            }
    
        } while (start < center && center < end );

        return -1;
    }

    public static void main(String[] args) {
        
        int [] array = {0,1,2,3,4,5,6,7,8,9,10};
        
        int result = search(array, 10);
        System.out.printf("result: %d \n", result);
        
    }
}
