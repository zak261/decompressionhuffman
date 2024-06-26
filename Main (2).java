// Main.java
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        HuffmanDecompressor decompressor = new HuffmanDecompressor("exemple_freq.txt");
        decompressor.decompress("exemple_comp.bin", "exemple_freq_decompressed.txt");
      
        // Étape 4 : détermination du taux de compression
        File compressedFile = new File("exemple_comp.bin");
        File originalFile = new File("exemple_freq.txt");
          //les deux premières lignes ouvrent des flux d'entrée et de sortie 
        //pour lire le fichier compressé et écrire le fichier décompressé. 
        double compressionRatio = 1.0 - (double) compressedFile.length() / originalFile.length();
        System.out.println("Taux de compression : " + compressionRatio);
        
        // Étape 5 : détermination du nombre moyen de bits de stockage d'un caractère du 
        //texte compressé
        double bitsPerCharacter = (double) compressedFile.length() * 8 / originalFile.length();
        System.out.println("Nombre moyen de bits par caractère : " + bitsPerCharacter);
    }
}
