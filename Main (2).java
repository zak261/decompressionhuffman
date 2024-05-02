// Main.java
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        HuffmanDecompressor decompressor = new HuffmanDecompressor("exemple_freq.txt");
        decompressor.decompress("exemple_comp.bin", "exemple_freq_decompressed.txt");

        File compressedFile = new File("exemple_comp.bin");
        File originalFile = new File("exemple_freq.txt");
        double compressionRatio = 1.0 - (double) compressedFile.length() / originalFile.length();
        System.out.println("Taux de compression : " + compressionRatio);

        double bitsPerCharacter = (double) compressedFile.length() * 8 / originalFile.length();
        System.out.println("Nombre moyen de bits par caractère : " + bitsPerCharacter);
    }
}
