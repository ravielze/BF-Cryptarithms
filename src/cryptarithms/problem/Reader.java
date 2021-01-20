package cryptarithms.problem;

import java.io.File;
import java.util.Scanner;
import java.util.logging.Logger;

import cryptarithms.CBF;

public class Reader {

    private File problemFile = null;
    
    public Reader(String fileName){
        Logger logger = CBF.getInstance().getLogger();
        try {
            File folder = new File("../test");
            if (!folder.exists()){
                folder.mkdir();
            }
            this.problemFile = new File(folder, fileName);
            if (!this.problemFile.exists()){
                logger.warning("File " + fileName + " is not found.");;
                this.problemFile = null;
            }
        } catch (NullPointerException ex){
            logger.severe("File name cannot be null!");
            this.problemFile = null;
        } catch (SecurityException ex2){
            logger.severe("File security error!");
            this.problemFile = null;
        } catch (Exception ex3){
            logger.warning("An unknown error has occured!");;
            this.problemFile = null;
        }
    }

    /**
     * If file is ready and available
     * @return boolean
     */
    public boolean isAvailable(){
        return this.problemFile != null;
    }

    /**
     * Get Scanner
     * @return null if file is not exist/error on load, else the scanner
     */
    public Scanner getScanner(){
        if (!this.isAvailable()) return null;

        try{
            return new Scanner(this.problemFile);
        } catch (Exception ignored) { return null; }
    }
}
