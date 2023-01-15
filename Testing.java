import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Testing {
    public static void main (String args[]) throws FileNotFoundException {
        /*
        * Test Gene class
        */
        System.out.println("***TESTING GENE CLASS***");
        Gene myGene = new Gene("abc", "origin1", 3.0);
        System.out.println(myGene.getGeneName());
        System.out.println(myGene.getCount());

        /*
        * Test GeneSet class
        * - access a text file
        * - build datastructure subtypeGeneSet, an ArrayList of genes from Gene class
        * - print out all genes in the ArrayList
        */
        System.out.println("**TESTING GENESET***");
        GeneSet myGeneSet = new GeneSet();
        System.out.println(myGeneSet.subtypeGeneSet.size());
        ArrayList<Gene> subtypeGeneSetForMyCancer = myGeneSet.buildGeneSet("breast", "brca_MMRdeficient");
        System.out.println(myGeneSet.subtypeGeneSet.size());

        for (int i = 0; i < subtypeGeneSetForMyCancer.size(); i++) {
            Gene currGene = subtypeGeneSetForMyCancer.get(i);
            System.out.println("Gene Name: " + currGene.getGeneName());
        }

        /*
        * Test GeneInteractions class
        * - access a text file
        * - build datastructure allGeneInteractions, an ArrayList of single gene interactions from SingleGeneInteraction class
        * - print out the source gene for each interaction
        */
        System.out.println("***TESTING GENEINTERACTIONS***");
        GeneInteractions myGeneInteractions = new GeneInteractions();
        ArrayList<SingleGeneInteraction> subtypeGeneInteractionsForMyCancer = myGeneInteractions.buildGeneInteractions("breast", "brca_MMRdeficient");

        for (int i = 0; i < subtypeGeneInteractionsForMyCancer.size(); i++) {
            SingleGeneInteraction currGeneInteraction = subtypeGeneInteractionsForMyCancer.get(i);
            String currSource = currGeneInteraction.getSource();
            String currTarget = currGeneInteraction.getTarget();
            int currWeight = currGeneInteraction.getWeight();
            System.out.println("Source: " + currSource + ", Target: " + currTarget + ", Weight: " + currWeight);
        }



    }
}
