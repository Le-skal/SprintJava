package initiation;
import java.util.Arrays;


public class exo3 {
    public static void main(String[] args) {
        int[] tab = {3, 5, 7, 2, 8, 10, 11, 1, 6, 4};
        double sum = 0;
        for (int value : tab){
            sum += value;
        }

        Arrays.sort(tab);
        

        int min = tab[0];
        System.out.println("min: " + min);

        int max = tab[tab.length-1];
        System.out.println("max: " + max);


        double mean = sum / tab.length;
        System.out.println("moyenne: " + mean);

        double median;
        if (tab.length % 2 == 0)
            median = ((double)tab[tab.length/2] + (double)tab[tab.length/2 - 1])/2;
        else
            median = (double) tab[tab.length/2];
        System.out.println("median: " + median);

        double ecartType = 0;
        for (int i=0; i< tab.length;i++) {
            ecartType += Math.pow(tab[i] - mean, 2);
        }
        ecartType = Math.sqrt(ecartType/tab.length);
        System.out.println("L'ecart Type est: " + ecartType);

    }
}
