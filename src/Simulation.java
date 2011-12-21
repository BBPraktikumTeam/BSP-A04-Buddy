/**
 * 
 */

/**
 * @author Kai Bielenberg (kai.bielenberg@haw-hamburg.de)
 *
 */
public class Simulation {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Tree tree=new Tree(64,0);
        tree.malloc(4);
        tree.malloc(4);
        tree.malloc(4);
        tree.dealloc(0);
        tree.dealloc(4);
        System.out.println(tree);
        tree.clean();
        System.out.println(tree);
//        System.out.println(tree.malloc(32));
//        System.out.println(tree.malloc(15));
//        System.out.println(tree.malloc(4));
//        System.out.println(tree.malloc(4));
//        System.out.println(tree.malloc(8));
//        System.out.println(tree.malloc(15));
//        System.out.println(tree.malloc(7));
//        System.out.println(tree.malloc(9));
//        System.out.println(tree.malloc(4));
//        
////        System.out.println(tree.malloc(64));
////        System.out.println(tree.malloc(7));
////    
////        System.out.println(tree);
//        System.out.println(tree.dealloc(40));
////        System.out.println(tree.dealloc(4));
//        System.out.println(tree);

    }

}
