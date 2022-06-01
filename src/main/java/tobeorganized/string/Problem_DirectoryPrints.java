package tobeorganized.string;

import java.util.List;

public class Problem_DirectoryPrints {
    public void print(List<String> paths) {
        GenericTrie<String> trie = new GenericTrie<>();
        for (String path : paths) {
            trie.addEntity(List.of(path.split("\\\\")));
        }
        depthFirst(trie.root, 0);
    }

    private void depthFirst(GenericTrie.GenericTrieNode<String> node, int depth) {
        if (depth >= 1) {
            for(int i =0; i< depth * 2; i++){
                System.out.print(" ");
            }
            System.out.println(node.id);
        }
        node.children.forEach((k, v) -> depthFirst(v, depth + 1));
    }

    public static void main(String[] args) {
        Problem_DirectoryPrints p = new Problem_DirectoryPrints();
        p.print(List.of("b\\cst", "d\\", "a\\d\\e", "a\\b\\c"));
    }
}
