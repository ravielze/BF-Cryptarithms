package cryptarithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Logger;

import cryptarithms.logger.LogFormatter;
import cryptarithms.problem.NewSolver;
import cryptarithms.utils.PUtils;
import cryptarithms.utils.ShowUtils;

public class CBF {

    private static CBF instance;
    private final Logger logger = Logger.getLogger("CryptaBF");

    {
        logger.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        Formatter formatter = new LogFormatter();
        handler.setFormatter(formatter);

        logger.addHandler(handler);
    }

    public Logger getLogger(){
        return this.logger;
    }

    public static CBF getInstance(){
        return instance;
    }

    public CBF(){
        instance = this;
    }

    public void run(){
        getLogger().info("Generating all permutations, Please wait... (Approx. 10s)");
        PUtils.permutations.get(0); // Trigger static block biar nyala
        Scanner scn = new Scanner(System.in);
        ArrayList<Character> ch = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        List<List<Integer>> sol = new ArrayList<>();
        while (true){
            getLogger().info("Silakan masukkan nama file cryptarithms (ketik exit untuk keluar): ");
            String fileName = scn.next();
            if (fileName.equalsIgnoreCase("exit")){
                getLogger().info("Berhasil keluar dari sistem.");
                break;
            }
            NewSolver ns = new NewSolver(fileName);
            ns.parse();
            if (ns.isError()){
                getLogger().severe("Terjadi kesalahan saat membaca file!");
            } else {
                while (true){
                    getLogger().info("Mau berapa banyak solusi?");
                    getLogger().info("1. Satu saja");
                    getLogger().info("2. Semua");
                    getLogger().info("3. Kembali");
                    try {
                        int pil = scn.nextInt();
                        double now = System.currentTimeMillis()/1000D;
                        if (pil == 2){
                            ns.findAllSolution(sol, ch);
                        } else if (pil == 1){
                            sol.clear();
                            ns.findSolution(temp, ch);
                            sol.add(temp);
                        } else {
                            break;
                        }
                        ShowUtils.showProblem(ns.getOperands(), ns.getBar());
                        getLogger().info("");
                        ShowUtils.showAll(ns.getOperands(), sol, ch, ns.getBar());
                        double dt = (System.currentTimeMillis()/1000D)-now;
                        getLogger().info(String.format("Waktu yang dibutuhkan: %.2fs", dt));
                        break;
                    } catch (Exception ex){
                        getLogger().severe("Input error.");
                        break;
                    }
                }
                promptEnterKey();
            }
        }
        scn.close();
    }

    public void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        try
        {
            System.in.read();
        }  
        catch(Exception e)
        {}  
     }
    
}
