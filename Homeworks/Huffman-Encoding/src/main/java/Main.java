import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Input -c to compress or -d to decompress:");
        if (input.hasNext()) {
            String ipt = input.next();
            if (ipt.equals("-c")) {
                Compression cps = new Compression();
                System.out.print("File to compress:");
                String InputFileName = input.next();
                System.out.print("Output file name:");
                String OutputFileName = input.next();
                cps.compress(InputFileName,OutputFileName);
            }
            else if (ipt.equals("-d")) {
                Decompression dps = new Decompression();
                System.out.print("File to decompress:");
                String InputFileName = input.next();
                System.out.print("Output file name:");
                String OutputFileName = input.next();
                dps.decompress(InputFileName,OutputFileName);
            }
            else {
                System.out.println("Invalid input.");
            }
        }
    }
}