package LE_06_03_sequence;

import utils.InputUtils;
import java.util.Scanner;

public class Main {
    static int[] sequence (int n) {
        final int SIZE = n;
        int [] data =new int[SIZE];

        for(int i=0;i<data.length;i++){
            data[i]=(int)(Math.random()*SIZE+1);
            System.out.print(data[i]+" ");
        }
        int max = data[0];
        int min = data[0];
        for(int i=1;i<data.length;i++){
            if(data[i]>max) max=data[i];
            if(data[i]<min) min=data[i];
        }
        System.out.println("\nMin "+ min+" Max "+ max);

        return data;
    }

    static boolean containSequence (int [] mainSequence, int [] subSeq) {
        if(mainSequence.length < subSeq.length || mainSequence.length==0) {
            return false;
        }
        for(int i=0;i<=mainSequence.length-subSeq.length;i++) {
            boolean found = true;
            for(int j=0;j<subSeq.length;j++) {
                if(mainSequence[i+j]!=subSeq[j]) {
                    found = false;
                    break;
                }
            }
            if(found) {
                System.out.printf("Your subsequence is  found in the main sequence.\n" +
                        "The first number is at position %d in the main sequence\n", i+1);
                return true;
            }
        }
        return false;
    }


   public  static void main(String[] args) {
        int [] mainSeq = sequence(30);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter 4 numbers to check:");
            int [] subSeq =new int[4];

            for(int i=0;i<4;i++) {
                int number=InputUtils.isPositiveInt(scanner, "Number "+(1+i)+": ");
                subSeq [i]= number;
            }

            System.out.println("Your subsequence is: "+subSeq[0]+" "+subSeq[1]+" "+subSeq[2]+" "+subSeq[3]);

            if(!containSequence (mainSeq,subSeq)) {
                System.out.println("Your subsequence is not found in the main sequence");
            }

            scanner.nextLine();
            System.out.println("\nDo you want to check another sequence? (y/n): ");

            String answer = scanner.nextLine();
            if(answer.equalsIgnoreCase("n")) {
                System.out.println("Bye!");
                break;
            }
        }

   }

}
