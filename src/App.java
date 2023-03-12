import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class App {
    static int countBomb(int[][] map, int x, int y, int n){
        int totalBomb = 0;
        for (int i = x-1; i <= x+1; i++) {
            if (i < 0 || i >= n) continue;
            for (int j = y-1; j <= y+1; j++) {
                if (j < 0 || j >= n) continue;
                
                if (map[i][j] == -1){
                    totalBomb++;
                }
            }
        }
        return totalBomb;
    }

    static void printMap(int[][] map, int n){
        System.out.print("- ");
        
        for (int i = 0; i < n; i++) {
            System.out.print(i+1);
            System.out.print(" ");
            
        }
        System.out.print("\n");
        
        for (int i = 0; i < n; i++) {
            System.out.print(i+1);
            System.out.print(" ");
            
            for (int j = 0; j < n; j++) {
                if (map[i][j] < 0){
                    System.out.print("_");
                } else {
                    System.out.print(map[i][j]);
                }
                System.out.print(" ");
                
            }
            System.out.print("\n");
        }

    }

    static void printMapWithBomb(int[][] map, int n){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == -1){
                    System.out.print("*");
                } else if (map[i][j] == -2){
                    System.out.print("_");
                } else {
                    System.out.print(map[i][j]);
                }
                System.out.print(" ");
                
            }
            System.out.print("\n");
        }

    }


    public static void main(String[] args) throws Exception {
        // -2: Unexplored
        // -1: Bomb
        int size = 5;
        int numOfBomb = 4;
        int maxPoint = (size*size) - numOfBomb;
        int point = 0;
        int[][] map = new int[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(map[i], -2);
        }

        Random rand = new Random();

        for (int i = 0; i < numOfBomb; i++) {
            int x = rand.nextInt(size);
            int y = rand.nextInt(size);


            map[x][y] = -1;
        }
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Point: "+Integer.toString(point));
            printMap(map, size);
            System.out.println("Choose tile to be revealed (format: [x] [y])");
            int y = input.nextInt()-1;
            int x = input.nextInt()-1;
            if (x < 0 || x >= size || y < 0 || y >= size || map[x][y] >= 0){
                System.out.println("Invalid!");
                continue;
                
            }
            if (map[x][y] == -1){
                // You lose
                System.out.println("Kaboom!");
                printMapWithBomb(map, size);
                break;
            }
            map[x][y] = countBomb(map, x, y, size);
            point++;

            // Explore adjacent
            if (map[x][y] == 0){
                for (int i = x-1; i <= x+1; i++) {
                    if (i < 0 || i >= size) continue;
                    for (int j = y-1; j <= y+1; j++) {
                        if (j < 0 || j >= size) continue;
                        if (map[i][j] >= 0) continue;
                        map[i][j] = countBomb(map, i, j, size);
                        point++;
                    }
                }
            }
            if (point >= maxPoint){
                // You Win
                System.out.println("You Win!");
                printMapWithBomb(map, size);
                break;
            }
        }
        input.close();
        System.out.println("Point: "+Integer.toString(point));
        // System.out.println("Point: "+Integer.toString(maxPoint));

        // int n = rand.nextInt(2);
        // System.out.println(n);
    }
}
