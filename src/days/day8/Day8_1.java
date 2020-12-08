package days.day8;

import utils.InputReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Day8_1 {
    public static void main(String[] args) throws FileNotFoundException {
        List<Instruction> instructions = new ArrayList<>();
        new InputReader().readAsStream("8.txt")
                .forEach(s -> instructions.add(new Instruction(s.substring(0, s.indexOf(' ')),
                        Integer.parseInt(s.substring(s.indexOf(' ')+1)),
                        false)));

        int acc = 0;
        int ip = 0; // instruction pointer
        while(true){
            Instruction currentInstruction = instructions.get(ip);
            if(currentInstruction.isExecuted)
                break;
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
        System.out.println(acc);
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
