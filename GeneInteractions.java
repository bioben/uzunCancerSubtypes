/*
* Represents an array of individual gene interactions of SingleGeneInteraction class that's involved with a cancer subtype
* @author Benjamin Ahn
* @version 1.0
*/

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GeneInteractions{

    // Initialize
    ArrayList<SingleGeneInteraction> allGeneInteractions = new ArrayList<SingleGeneInteraction>();

    /*
    * getter and setter
    */
    public ArrayList<SingleGeneInteraction> getAllGeneInteractions() {
        return allGeneInteractions;
    }
    public void setAllGeneInteractions(ArrayList<SingleGeneInteraction> allGeneInteractions) {
        this.allGeneInteractions = allGeneInteractions;
    }

    /*
    * Builds an arraylist of gene interactions
    * @param tissueType name of tissue ie. "breast"
    * @param cancerSubtypeName name of cancer subtype ie. "brca MMRdeficient"
    * @return allGeneInteractions an ArrayList of genes that belong to the cancer subtype, built from the text file
    */
    public ArrayList<SingleGeneInteraction> buildGeneInteractions(String tissueType, String cancerSubtypeName) throws FileNotFoundException {
        //Build path to text file
        String pathToGeneInteractTextFile = "./masterData/" + tissueType + "/" + cancerSubtypeName + "/" + cancerSubtypeName + "_interactions.txt";

        File geneInteractTextFile = new File(pathToGeneInteractTextFile);
        Scanner sc = new Scanner(geneInteractTextFile);

        // Initialize ArrayList
        ArrayList<SingleGeneInteraction> allGeneInteractions = new ArrayList<SingleGeneInteraction>();

        // Iterate through text file and add to subtypeGeneSet
        int lineCount = 0;
        while (sc.hasNextLine()){
            // Skip header line
            if (lineCount == 0) {
                sc.nextLine();
            }

            // interactionInfoSplit holds the following [geneName, origin, count] of Gene class
            String interactionInfo = sc.nextLine();
            String[] interactionInfoSplit = interactionInfo.split("\t", 0);

            // build gene
            String source = interactionInfoSplit[0];
            String target = interactionInfoSplit[1];
            int weight = Integer.valueOf(interactionInfoSplit[2]);
            SingleGeneInteraction currGeneInteraction = new SingleGeneInteraction(source, target, weight);

            // add interaction to arraylist of gene interactions
            allGeneInteractions.add(currGeneInteraction);

            lineCount++;
        }

        // set subtypeGeneInteraction
        setAllGeneInteractions(allGeneInteractions);

        return allGeneInteractions;
    }
}
