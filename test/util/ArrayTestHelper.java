package util;

public class ArrayTestHelper<T> {

    public ArrayTestHelper() {
    }

    public boolean arraysEquals1D(T[] in, T[] out) {

        if(in.length != out.length) return false;

        for(int i = 0; i < in.length; i++)
            if(!in[i].equals(out[i])) return false;

        return true;

    }

    public boolean arraysEquals2D(T[][] in, T[][] out) {

        if(in.length != out.length) return false;

        for(int i = 0; i < in.length; i++) {

            if(!arraysEquals1D(in[i], out[i])) return false;

        }

        return true;

    }

    public void print(T[][] arr) {
        String full = "";
        for(int i = 0; i < arr.length; i++) {
            String row = "";
            for(int j = 0; j < arr[i].length; j++) {
                if(arr[i][j] != null)
                    row += arr[i][j].toString() + ", ";
                else
                    row += "_ , ";
            }
            full += row + "\n";
        }
        System.out.println(full + "\n");
    }


}
