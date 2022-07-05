public class _C extends Thread implements I_C{

    public static void fun(String [] tab){
        for(String str : tab){
            System.out.print(str);
        }
    }

    public static void main(String[] args) {
        String [] tab = new String[3];
        tab[0]="ab";
        tab[1]="cd";
        tab[2]="ef";
        fun(tab);
    }
}
