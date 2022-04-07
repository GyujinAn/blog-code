package algorithm.bubblesort;

/**
 * @author agj017@gmail.com
 * @since 2022/01/21
 */
class BubbleSortMain {
    //가장 작은 수를 앞으로 옮기는 pass
    private static int[] pass0(int[] a, int n){
        for(int i=n-1; i>0; i--){
            if(a[i] <  a[i-1]){
                swap(a, i, i-1);
            }
        }
        return a;
    }

    //가장 큰 수를 뒤로 옮기는 pass
    private static int[] pass1(int[] a, int n){
        for(int i=0; i<n-1; i++){
            if(a[i] > a [i+1]){
                swap(a, i, i+1);
            }
        }
        return a;
    }

    //가장 작은 수를 앞으로 옮기는 pass로 구성 된 버블 정렬
    private static int[] bubbleSort0(int[] a, int n){
        int passCount = 0;
        int swapCount = 0;
        int compareCount = 0;
        for(int j=0; j<n-1; j++){
            System.out.println("---"+"pass"+passCount+"---");
            for(int i=n-1; i>j; i--){
                if(a[i] <  a[i-1]){
                    swap(a, i, i-1);
                    swapCount++;
                }
                compareCount++;
            }
            printArray(a);
            passCount++;
        }
        System.out.println("passCount: " + passCount + ", compareCount: " + compareCount + ", swapCount: " + swapCount);
        return a;
    }

    //가장 큰 수를 뒤로 옮기는 pass로 구성 된 버블 정렬
    private static int[] bubbleSort1(int[] a, int n){
        int passCount = 0;
        int swapCount = 0;
        int compareCount = 0;
        for(int j=n-1; j>0; j--){
            System.out.println("---"+"pass"+passCount+"---");
            for(int i=0; i<j; i++){
                if(a[i] > a[i+1]){
                    swap(a, i, i+1);
                    swapCount++;
                }
                compareCount++;
            }
            printArray(a);
            passCount++;
        }
        System.out.println("passCount: " + passCount + ", compareCount: " + compareCount + ", swapCount: " + swapCount);
        return a;
    }

    //가장 작은 수를 앞으로 옮기는 pass로 구성 된 버블 정렬
    //교환이 발생하지 않은 경우는 정렬이 완료되었으므로 정렬을 종료시키는 업그레이드 버전
    private static int[] upgradedBubbleSort0(int[] a, int n){
        int passCount = 0;
        int swapCount = 0;
        int compareCount = 0;
        for(int j=0; j<n-1; j++){
            System.out.println("---"+"pass"+passCount+"---");
            boolean isSwap = false;
            for(int i=n-1; i>j; i--){
                if(a[i] <  a[i-1]){
                    swap(a, i, i-1);
                    swapCount++;
                    isSwap = true;
                }
                compareCount++;

            }
            printArray(a);
            passCount++;
            if(!isSwap) break;
        }
        System.out.println("passCount: " + passCount + ", compareCount: " + compareCount + ", swapCount: " + swapCount);
        return a;
    }

    //가장 큰 수를 뒤로 옮기는 pass로 구성 된 버블 정렬
    //교환이 발생하지 않은 경우는 정렬이 완료되었으므로 정렬을 종료시키는 업그레이드 버전
    private static int[] upgradedBubbleSort1(int[] a, int n){
        int passCount = 0;
        int swapCount = 0;
        int compareCount = 0;
        for(int j=n-1; j>0; j--){
            System.out.println("---"+"pass"+passCount+"---");
            boolean isSwap = false;
            for(int i=0; i<j; i++){
                if(a[i] > a[i+1]){
                    swap(a, i, i+1);
                    swapCount++;
                    isSwap = true;
                }
                compareCount++;
            }
            printArray(a);
            passCount++;
            if(!isSwap) break;
        }
        System.out.println("passCount: " + passCount + ", compareCount: " + compareCount + ", swapCount: " + swapCount);
        return a;
    }

    //가장 작은 수를 앞으로 옮기는 pass로 구성 된 버블 정렬
    //정렬이 완료된 인덱스를 기록하여 정렬이 완료되지 않은 부분만 정렬하는 업그레이드 버전
    private static int[] upgradedBubbleSort2(int[] a, int n){
        int passCount = 0;
        int swapCount = 0;
        int compareCount = 0;
        int j = 0;
        while(j<n-1){
            System.out.println("---"+"pass"+passCount+"---");
            int firstIndex = n-1;
            for(int i=n-1; i>j; i--){
                if(a[i] <  a[i-1]){
                    swap(a, i, i-1);
                    swapCount++;
                    firstIndex = i;
                }
                compareCount++;

            }
            printArray(a);
            passCount++;
            j = firstIndex;

        }

        System.out.println("passCount: " + passCount + ", compareCount: " + compareCount + ", swapCount: " + swapCount);
        return a;
    }

    //가장 큰 수를 뒤로 옮기는 pass로 구성 된 버블 정렬
    //교환이 발생하지 않은 경우는 정렬이 완료되었으므로 정렬을 종료시키는 업그레이드 버전
    private static int[] upgradedBubbleSort3(int[] a, int n){
        int passCount = 0;
        int swapCount = 0;
        int compareCount = 0;
        int j = n-1;
        while(j>0){
            System.out.println("---"+"pass"+passCount+"---");
            int lastIndex = 0;
            for(int i=0; i<j; i++){
                if(a[i] > a[i+1]){
                    swap(a, i, i+1);
                    swapCount++;
                    lastIndex = i;
                }
                compareCount++;
            }
            printArray(a);
            passCount++;
            j = lastIndex;

        }

        System.out.println("passCount: " + passCount + ", compareCount: " + compareCount + ", swapCount: " + swapCount);
        return a;
    }

    private static void printArray(int[] a){
        for(int tmp : a){
            System.out.print(tmp+" ");
        }
        System.out.println();
    }

    private static void swap(int a[], int i0, int i1){
        int tmp = a[i0];
        a[i0] = a[i1];
        a[i1] = tmp;
    }

    public static void main(String[] args) {

        int[] array;

        array = new int[]{6, 4, 3, 7, 1, 9, 8};
        System.out.println("===array0 before pass0===");
        printArray(array);
        pass0(array, array.length);
        System.out.println("===array0 after pass0===");
        printArray(array);

        System.out.println();

        array = new int[]{6, 4, 3, 7, 1, 9, 8};
        System.out.println("===array1 before pass1===");
        printArray(array);
        pass1(array, array.length);
        System.out.println("===array1 after pass1===");
        printArray(array);

        System.out.println();

        array = new int[]{6, 4, 3, 7, 1, 9, 8};
        System.out.println("===array2 before bubbleSort0===");
        printArray(array);
        bubbleSort0(array, array.length);
        System.out.println("===array2 after bubbleSort0===");
        printArray(array);

        System.out.println();

        array = new int[]{6, 4, 3, 7, 1, 9, 8};
        System.out.println("===array3 before bubbleSort1===");
        printArray(array);
        bubbleSort1(array, array.length);
        System.out.println("===array3 after bubbleSort1===");
        printArray(array);

        System.out.println();

        array = new int[]{6, 4, 3, 7, 1, 9, 8};
        System.out.println("===array4 before upgradedBubbleSort0===");
        printArray(array);
        upgradedBubbleSort0(array, array.length);
        System.out.println("===array4 after upgradedBubbleSort0===");
        printArray(array);

        System.out.println();

        array = new int[]{6, 4, 3, 7, 1, 9, 8};
        System.out.println("===array5 before upgradedBubbleSort1===");
        printArray(array);
        upgradedBubbleSort1(array, array.length);
        System.out.println("===array5 after upgradedBubbleSort1===");
        printArray(array);

        System.out.println();

        array = new int[]{1, 3, 9, 4, 7, 8, 6};
        System.out.println("===array6 before bubbleSort0===");
        printArray(array);
        bubbleSort0(array, array.length);
        System.out.println("===array6 after bubbleSort0===");
        printArray(array);

        System.out.println();

        array = new int[]{1, 3, 9, 4, 7, 8, 6};
        System.out.println("===array7 before upgradedBubbleSort2===");
        printArray(array);
        upgradedBubbleSort2(array, array.length);
        System.out.println("===array7 after upgradedBubbleSort2===");
        printArray(array);

        System.out.println();

        array = new int[]{6, 4, 3, 7, 1, 9, 8};
        System.out.println("===array8 before bubbleSort1===");
        printArray(array);
        bubbleSort1(array, array.length);
        System.out.println("===array8 after bubbleSort1===");
        printArray(array);

        System.out.println();

        array = new int[]{6, 4, 3, 7, 1, 9, 8};
        System.out.println("===array9 before upgradedBubbleSort3===");
        printArray(array);
        upgradedBubbleSort3(array, array.length);
        System.out.println("===array9 after upgradedBubbleSort3===");
        printArray(array);
    }

}