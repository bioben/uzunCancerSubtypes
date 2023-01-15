/*
* Represents an array of genes of Gene class that are involved in a cancer subtype
* @author Benjamin Ahn
* @version 1.0
*/

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GeneSet {
    /*
    * Initialize with blank ArrayList of genes. With the buildGeneSet method,
    * this should be filled with genes of Gene class
    */
    ArrayList<Gene> subtypeGeneSet = new ArrayList<Gene>();

    /*
    * getter and setter
    */
    public ArrayList<Gene> getSubtypeGeneSet() {
        return subtypeGeneSet;
    }
    public void setSubtypeGeneSet(ArrayList<Gene> subtypeGeneSet) {
        this.subtypeGeneSet = subtypeGeneSet;
    }

    /*
    * Builds a gene set
    * @param tissueType name of tissue ie. "breast"
    * @param cancerSubtypeName name of cancer subtype ie. "brca MMRdeficient"
    * @return subtypeGeneSet an ArrayList of genes that belong to the cancer subtype, built from the text file
    */
    public ArrayList<Gene> buildGeneSet(String tissueType, String cancerSubtypeName) throws FileNotFoundException {
        //Build path to text file
        String pathToGeneSetTextFile = "./masterData/" + tissueType + "/" + cancerSubtypeName + "/" + cancerSubtypeName + "_geneSet.txt";

        File geneSetTextFile = new File(pathToGeneSetTextFile);
        Scanner sc = new Scanner(geneSetTextFile);

        // Initialize ArrayList
        ArrayList<Gene> subtypeGeneSet = new ArrayList<Gene>();

        // Iterate through text file and add to subtypeGeneSet
        int lineCount = 0;
        while (sc.hasNextLine()){
            // Skip header line
            if (lineCount == 0) {
                sc.nextLine();
            }

            // geneInformationSplit holds the following [geneName, origin, count] of Gene class
            String geneInformation = sc.nextLine();
            String[] geneInformationSplit = geneInformation.split("\t", 0);

            // build gene
            String name = geneInformationSplit[0];
            String origin = geneInformationSplit[1];
            double count = Double.valueOf(geneInformationSplit[2]);
            Gene currGene = new Gene(name, origin, count);

            // add gene to subtypeGeneSet
            subtypeGeneSet.add(currGene);

            lineCount++;
        }

        // set subtypeGeneSet
        setSubtypeGeneSet(subtypeGeneSet);

        return subtypeGeneSet;

    }
}
