import java.io.*;//importe les classes n�cessaires pour effectuer les entr�es/sorties sur des fichiers.

public class HuffmanDecompression {

    // Structure de l'arbre de Huffman
    private static class Node { // stocker la structure de l'arbre de Huffman. 
    	//Un noeud contient une valeur (qui peut �tre un caract�re ou une fr�quence), 
    	//et des liens vers les noeuds enfants gauche et droit.
        public char value;
        public Node left, right;
        public Node(char value) { this.value = value; }
    }

    public static void main(String[] args) throws IOException {
        // �tape 1+2 : lecture de l'alphabet et des fr�quences et la construire
        BufferedReader reader = new BufferedReader(new FileReader("exemple_freq.txt"));
        int alphabetSize = Integer.parseInt(reader.readLine());
        Node root = null;
        for (int i = 0; i < alphabetSize; i++) {// La premi�re ligne lit le nombre de symboles 
        	//dans l'alphabet, 
        	//puis pour chaque symbole, les lignes suivantes lisent le symbole 
        	//et sa fr�quence correspondante, 
            char symbol = reader.readLine().charAt(0);
            int frequency = Integer.parseInt(reader.readLine());
            root = insert(root, symbol, frequency); //puis ins�rent le symbole dans l'arbre 
            //de Huffman � l'aide de la fonction "insert".
        }
        reader.close();


        // �tape 3 : d�codage du texte comprim�
        InputStream input = new FileInputStream("exemple_freq_comp.bin");
        OutputStream output = new FileOutputStream("exemple_freq_decompressed.txt"); 
        //les deux premi�res lignes ouvrent des flux d'entr�e et de sortie 
        //pour lire le fichier compress� et �crire le fichier d�compress�. 
        int bit;
        Node node = root;
        while ((bit = input.read()) != -1) { //lit chaque bit du fichier compress�. 
        	//Si le bit est 0, le noeud courant est mis � jour avec le fils gauche, 
        	//sinon le noeud courant est mis � jour avec le fils droit. 
        	//Si le noeud courant n'a pas de fils gauche ni de fils droit
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

        // �tape 4 : d�termination du taux de compression
        File compressedFile = new File("exemple_freq_comp.bin");
        File originalFile = new File("exemple_freq.txt");
        double compressionRatio = 1.0 - (double) compressedFile.length() / originalFile.length();
        System.out.println("Taux de compression : " + compressionRatio);

        // �tape 5 : d�termination du nombre moyen de bits de stockage d'un caract�re du 
        //texte compress�
        double bitsPerCharacter = (double) compressedFile.length() * 8 / originalFile.length();
        System.out.println("Nombre moyen de bits par caract�re : " + bitsPerCharacter);
    }

    // Fonction pour ins�rer un n�ud dans l'arbre de Huffman
    private static Node insert(Node root, char value, int frequency) {
    	//ins�rer de nouveaux n�uds dans l'arbre de Huffman en fonction de leur fr�quence. 
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
}