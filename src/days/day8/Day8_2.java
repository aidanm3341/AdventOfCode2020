package days.day8;

import utils.InputReader;
import utils.Pair;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Day8_2 {
    public static void main(String[] args) throws FileNotFoundException {
        List<Instruction> instructions = new ArrayList<>();
        new InputReader().readAsStream("8.txt")
                .forEach(s -> instructions.add(new Instruction(s.substring(0, s.indexOf(' ')),
                        Integer.parseInt(s.substring(s.indexOf(' ')+1)),
                        false)));


        for(int i=0; i<instructions.size(); i++){//Instruction i : instructions){
            System.out.println(i);
            if(instructions.get(i).op.equals("nop")){
                instructions.get(i).op = "jmp";
                Pair<Boolean, Integer> terminates = doesTerminate(instructions);
                if(terminates.getFst()){
                    System.out.println(terminates.getSnd());
                    break;
                }
                instructions.get(i).op = "nop";
            }
            else if(instructions.get(i).op.equals("jmp")){
                instructions.get(i).op = "nop";
                Pair<Boolean, Integer> terminates = doesTerminate(instructions);
                if(terminates.getFst()){
                    System.out.println(terminates.getSnd());
                    break;
                }
                instructions.get(i).op = "jmp";
            }
        }
    }

    private static Pair<Boolean, Integer> doesTerminate(List<Instruction> instructions){
        for(Instruction j : instructions){
            j.isExecuted = false;
        }

        int acc = 0;
        int ip = 0; // instruction pointer

        Instruction currentInstruction;
        while(true){
            if(ip >= instructions.size()-1)
                return new Pair<>(true, acc);

            currentInstruction = instructions.get(ip);
            if(currentInstruction.isExecuted) {
                break;
            }
            switch (currentInstruction.op){
                case "acc":
                    acc += currentInstruction.val;
                    ip++;
                    break;
                case "jmp":
                    ip += currentInstruction.val;
                    break;
                case "nop":
                    ip++;
                    break;
            }
            currentInstruction.isExecuted = true;
        }

        return new Pair<>(false, acc);
    }

    private static class Instruction{
        String op;
        Integer val;
        Boolean isExecuted;

        public Instruction(String op, Integer val, Boolean isExecuted) {
            this.op = op;
            this.val = val;
            this.isExecuted = isExecuted;
        }
    }
}
