package schoolbot.commands.school;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import schoolbot.commands.Command;
import schoolbot.natives.util.MatrixOps;
import schoolbot.natives.util.StringOperations;

public class LinearAlgebra extends Command {

    public LinearAlgebra() {
        super(new String[] { "linearalgebra", "la" });
    }

    @Override
    public void run(MessageReceivedEvent event) {

    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        MessageChannel chan = event.getChannel();
        if (args.length > 1) {
            if (args[1].equals("rref")) {
                msg(chan, StringOperations.formatMatrix(MatrixOps.rref(getMatrix(args[0]))));
            } else if (args[1].equals("transpose") || args[1].equals("t")) {
                msg(chan, StringOperations.formatMatrix(MatrixOps.transpose(getMatrix(args[0]))));
            }
        } else {
            msg(chan, StringOperations.formatMatrix(parse(args[0])));
        }
    }

    public void msg(MessageChannel chan, String text) {
        chan.sendMessage(text).queue();
    }

    public static double[][] getMatrix(String input) {
        String temp = input.replace("[", "").replace("]", "");
        int cols = 1 + temp.split(";")[0].length() - temp.split(";")[0].replace(",", "").length();
        int rows = 1 + temp.length() - temp.replace(";", "").length();
        double[][] a = new double[rows][cols];
        for (int i = 0; i < temp.split(";").length; i++) {
            String[] c_row = temp.split(";")[i].split(",");
            for (int j = 0; j < c_row.length; j++) {
                a[i][j] = Double.parseDouble(c_row[j]);
            }
        }
        return a;
    }

    /*
     * matrix : [0 1 0] [1 0 1] [0 1 0] [1 0 1]
     * 
     * text input: [0,1,0;1,0,1;0,1,0;1,0,1] separate individual nums by ',' and
     * rows by ';'
     */

    public static double[][] parse(String input) {
        if (input.startsWith("[") && input.endsWith("]")) {
            int end1 = input.indexOf("]");
            if (input.charAt(end1 + 2) != '[') {
                if (input.contains("dot")) {
                    double[][] a = getMatrix(input.substring(0, end1));
                    double[][] b = getMatrix(input.substring(end1 + 4, input.length() - 1));
                    return MatrixOps.dot(a, b);
                }
            } else {
                double[][] a = getMatrix(input.substring(0, end1));
                double[][] b = getMatrix(input.substring(end1 + 2, input.length() - 1));
                switch (input.charAt(end1 + 1)) {
                    case '+':
                        return MatrixOps.add(a, b);
                    case '-':
                        return MatrixOps.subtract(a, b);
                    case '*':
                        return MatrixOps.multiply(a, b);
                    default:
                        return MatrixOps.zero(1, 1);
                }
            }
        }
        return MatrixOps.zero(1, 1);
    }
}
