// Node.java
public class Node {// stocker la structure de l'arbre de Huffman. 
    	//Un noeud contient une valeur (qui peut être un caractère ou une fréquence), 
    	//et des liens vers les noeuds enfants gauche et droit.
    public char value;
    public Node left, right;

    public Node(char value) {
        this.value = value;
    }
}
