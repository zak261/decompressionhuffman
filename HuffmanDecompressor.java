// HuffmanDecompressor.java
import java.io.*;

public class HuffmanDecompressor {
    private Node root;

    public HuffmanDecompressor(String freqFile) throws IOException {
        buildTree(freqFile);
    }

    private void buildTree(String freqFile) throws IOException {
                // Étape 1+2 : lecture de l'alphabet et des fréquences et la construire

        BufferedReader reader = new BufferedReader(new FileReader(freqFile));
        int alphabetSize = Integer.parseInt(reader.readLine().trim()); // Lire et nettoyer la première ligne qui indique la taille de l'alphabet
        root = null;
        for (int i = 0; i < alphabetSize; i++) {// La première ligne lit le nombre de symboles 
        	//dans l'alphabet, 
        	//puis pour chaque symbole, les lignes suivantes lisent le symbole 
        	//et sa fréquence correspondante, 
            String line = reader.readLine().trim(); // Lire la ligne contenant le symbole et la fréquence
            String[] parts = line.split("\\s+"); // Diviser la ligne en parties basées sur un ou plusieurs espaces
            char symbol = parts[0].charAt(0); // Le premier élément est le symbole
            int frequency = Integer.parseInt(parts[1]); // Le deuxième élément est la fréquence
            root = insert(root, symbol, frequency);//puis insèrent le symbole dans l'arbre 
            //de Huffman à l'aide de la fonction "insert".
        }
        reader.close();
    }
    
    
    // Fonction pour insérer un nœud dans l'arbre de Huffman
    private Node insert(Node root, char value, int frequency) {
        //insérer de nouveaux nœuds dans l'arbre de Huffman en fonction de leur fréquence. 

        if (root == null) {
            return new Node(value);
        } else if (frequency < root.value) {
            Node node = new Node(value);
            node.left = root;
            return node;
        } else {
            root.right = insert(root.right, value, frequency);
            return root;
        }
    }

    public void decompress(String compressedFile, String outputFile) throws IOException {
        InputStream input = new FileInputStream(compressedFile);
        OutputStream output = new FileOutputStream(outputFile);
        int bit;
        Node node = root;
        while ((bit = input.read()) != -1) {
            if (bit == '0') {
                node = node.left;
            } else if (bit == '1') {
                node = node.right;
            }
            if (node.left == null && node.right == null) {
                output.write(node.value);
                node = root;
            }
        }
        input.close();
        output.close();
    }
}
