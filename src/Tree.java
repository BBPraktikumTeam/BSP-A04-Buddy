/**
 * 
 */

/**
 * @author Kai Bielenberg (kai.bielenberg@haw-hamburg.de)
 * 
 */
public class Tree {
    private final int blockSize;
    private Integer dataSize;
    private Tree left;
    private Tree right;
    private Tree parent;
    private int startAdress;

    public Tree(int blockSize, int startAdress) {
        this.blockSize = blockSize;
        this.startAdress = startAdress;
    }

    public int malloc(int dataSize) {
        // TODO Alloc rückgabe StartAdress, -1 bei Error
        if (dataSize > blockSize)
            return -1;
//        System.out.println(dataSize);
        int allocatedSize = 2;
        while (allocatedSize < dataSize)
            allocatedSize *= 2;
        if (isLeaf() && this.dataSize != null)
            return -1;
        else if (isLeaf() && allocatedSize == blockSize
                && this.dataSize == null) {
            this.dataSize = dataSize;
            return startAdress;
        } else if (left == null) {
            left = new Tree(blockSize / 2, startAdress);
        } else if (right == null) {
            right = new Tree(blockSize / 2, startAdress + (blockSize / 2));
        }
        // TODO: Refactor
        int leftAdress=left.malloc(dataSize);
        if (leftAdress==-1) return right.malloc(dataSize); 
        else return leftAdress; 
    }

    public boolean dealloc(int memoryAdress) {
        if (memoryAdress > blockSize + startAdress) {
            System.out.println("-----------out of bounds");
            return false;
        }
        if (isLeaf()) {
            System.out.println("data " + dataSize);
            System.out.println("startAdress " + startAdress);
            if (memoryAdress == startAdress && dataSize != null) {
                this.dataSize = null;
                return true;
            } else {
                System.out.println("no leaf with data");
                return false;
            }
        }

        else {
            if (memoryAdress < (blockSize / 2) + startAdress)
                return this.left().dealloc(memoryAdress);
            else
                return this.right().dealloc(memoryAdress);
        }
    }

    public void clean() {
        boolean cleaned = true;
        while (cleaned) {
            cleaned = clean_();
        }
    }

    public boolean clean_() {
        if (left != null) {
            if (left.isEmptyLeaf()) {
                left = null;
                return true;
            } else
                return left.clean_();
        } else if (right != null) {
            if (right.isEmptyLeaf()) {
                right = null;
                return true;
            } else
                return right.clean_();
        } else
            return false;
    }


    public boolean isEmptyLeaf() {
        return isLeaf() && dataSize == null;
    }

    /**
     * @return
     */
    private boolean isLeaf() {
        return left == null && right == null;
    }

    public int blockSize() {
        return blockSize;
    }

    public Tree left() {
        return left;
    }

    public Tree right() {
        return right;
    }


    public Tree parent() {
        return parent;
    }

    public String toString() {
        return toString(0);
    }

    public String toString(int indent) {
        String space = "";
        for (int i = 0; i < indent * 3; i++)
            space += " ";
        String result = space + "Adress: " + startAdress + "\n" + space
                + "BlockSize: " + blockSize + "\n";
        if (dataSize != null)
            result += space + "DataSize: " + dataSize + "\n";
        else {

            if (left != null) {
                result += space + "Left:\n" + left.toString(indent + 1) + "\n";
            } else
                result += space + "Left: Empty\n";
            if (right != null) {
                result += space + "Right:\n" + right.toString(indent + 1)
                        + "\n";
            } else
                result += space + "Right: Empty\n";
        }
        return result;

    }

}
