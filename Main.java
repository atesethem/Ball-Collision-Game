import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        creatingboard(args[0]);
        boardaftermoves(args[1],args[0]);
        writer(args[0],args[1]);
    }
    public static String[][] creatingboard(String thefile) throws IOException {
        File file = new File(thefile);
        FileReader freader = new FileReader(file);
        String line;
        BufferedReader breader = new BufferedReader(freader);
        String[] n = breader.readLine().split(" ");
        int a = n.length;
        int b = 1;
        while (breader.readLine() != null) {
            b++;
        }
        String[][] board = new String[b][a];
        if (a <= 20 && b <= 20) {
            breader = new BufferedReader(new FileReader(file));

            for (int row = 0; row < b; row++) {
                for (int col = 0; col < a; col++) {
                    board[row][col] = "-";
                }
            }

            int boardrow = 0;
            while ((line = breader.readLine()) != null) {
                String[] thelist = line.split(" ");
                int col = n.length;
                System.arraycopy(thelist, 0, board[boardrow], 0, col - 1 + 1);
                boardrow++;
            }

        } else {
            System.err.println("Satır ve sütun sayıları 20'den büyük olamaz.");

        }
        return board;
    }
    public static int[] location(String t, String a) throws IOException {
        String[][] board = creatingboard(a);
        String eleman = t;
        int row = 0;
        int col = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals(eleman)) {
                    row = i;
                    col = j;
                }
            }
        }
        int[] konum = {row, col};
        return konum;
    }
    public static String[][] boardaftermoves(String thefile,String secondfile) throws IOException {
        File file = new File(thefile);
        FileReader freader = new FileReader(file);
        BufferedReader breader = new BufferedReader(freader);
        String[] list = breader.readLine().split(" ");
        String[][] board = creatingboard(secondfile);
        int boardsrows = board.length;
        int boardscolumns = board[0].length;
        int[] thearray = location("*", secondfile);
        int therow = thearray[0];
        int thecol = thearray[1];
        int abc = 0;
        for (String movement : list) {
            abc++;
            if (movement.equals("L")) {
                if (thecol == 0) {
                    if (board[therow][boardscolumns - 1].equals("W")) {
                        if (board[therow][1].equals("R") || board[therow][1].equals("Y") || board[therow][1].equals("B")) {
                            board[therow][0] = "X";
                            thecol = 1;
                        } else if (board[therow][1].equals("H")) {
                            board[therow][0] = " ";
                            break;
                        } else {
                            String word = board[therow][1];
                            board[therow][thecol] = (word);
                            thecol = 1;
                        }
                    } else if (board[therow][boardscolumns - 1].equals("R") || board[therow][boardscolumns - 1].equals("Y") || board[therow][boardscolumns - 1].equals("B")) {
                        board[therow][0] = "X";
                        thecol = boardscolumns - 1;
                    } else if (board[therow][boardscolumns - 1].equals("H")) {
                        board[therow][0] = " ";
                        break;
                    } else {
                        String word = board[therow][boardscolumns - 1];
                        board[therow][0] = word;
                        thecol = boardscolumns - 1;
                    }
                } else if (board[therow][thecol - 1].equals("R") || board[therow][thecol - 1].equals("Y") || board[therow][thecol - 1].equals("B")) {
                    board[therow][thecol] = ("X");
                    thecol--;
                } else if (board[therow][thecol - 1].equals(" ")) {
                    board[therow][thecol] = " ";
                    break;
                } else if (board[therow][thecol - 1].equals("W")) {
                    if (board[therow][thecol + 1].equals("R") || board[therow][thecol + 1].equals("Y") || board[therow][thecol + 1].equals("B")) {
                        board[therow][thecol] = "X";
                        thecol++;
                    } else if (thecol == boardscolumns - 1) {
                        if (board[therow][0].equals("R") || board[therow][0].equals("Y") || board[therow][0].equals("B")) {
                            board[therow][thecol] = "X";
                            thecol = 0;
                        } else if (board[therow][0].equals("H")) {
                            board[therow][boardscolumns - 1] = " ";
                            break;
                        } else {
                            String word = board[therow][thecol + 1];
                            board[therow][thecol] = (word);
                            thecol = 0;
                        }
                    } else if (board[therow][thecol + 1].equals("H")) {
                        board[therow][thecol] = " ";
                        break;
                    } else {
                        String word = board[therow][thecol + 1];
                        board[therow][thecol] = (word);
                        thecol++;
                    }
                } else {
                    String word = board[therow][thecol - 1];
                    board[therow][thecol] = (word);
                    thecol--;
                }
            } else if (movement.equals("R")) {
                if (thecol == boardscolumns - 1) {
                    if (board[therow][0].equals("W")) {
                        if (board[therow][boardscolumns - 2].equals("R") || board[therow][boardscolumns - 2].equals("Y") || board[therow][boardscolumns - 2].equals("B")) {
                            board[therow][boardscolumns - 1] = "X";
                            thecol = 3;
                        } else if (board[therow][boardscolumns - 2].equals("H")) {
                            board[therow][0] = " ";
                            break;
                        } else {
                            String word = board[therow][boardscolumns - 2];
                            board[therow][thecol] = (word);
                            thecol = boardscolumns - 2;
                        }
                    } else if (board[therow][0].equals("R") || board[therow][0].equals("Y") || board[therow][0].equals("B")) {
                        board[therow][boardscolumns - 1] = "X";
                        thecol = 0;
                    } else if (board[therow][0].equals("H")) {
                        board[therow][boardscolumns - 1] = " ";
                        break;
                    } else {
                        String word = board[therow][0];
                        board[therow][boardscolumns - 1] = word;
                        thecol = 0;
                    }
                } else if (board[therow][thecol + 1].equals("R") || board[therow][thecol + 1].equals("Y") || board[therow][thecol + 1].equals("B")) {
                    board[therow][thecol] = ("X");
                    thecol++;
                } else if (board[therow][thecol + 1].equals("H")) {
                    board[therow][thecol] = " ";
                    break;
                } else if (board[therow][thecol + 1].equals("W")) {
                    if (board[therow][thecol - 1].equals("R") || board[therow][thecol - 1].equals("Y") || board[therow][thecol - 1].equals("B")) {
                        board[therow][thecol] = "X";
                        thecol--;
                    } else if (thecol == 0) {
                        if (board[therow][boardscolumns - 1].equals("R") || board[therow][boardscolumns - 1].equals("Y") || board[therow][boardscolumns - 1].equals("B")) {
                            board[therow][0] = "X";
                            thecol = boardscolumns - 1;
                        } else if (board[therow][0].equals("H")) {
                            board[therow][boardscolumns - 1] = " ";
                            break;
                        } else {
                            String word = board[therow][thecol - 1];
                            board[therow][thecol] = (word);
                            thecol = boardscolumns - 1;
                        }
                    } else if (board[therow][thecol - 1].equals("H")) {
                        board[therow][thecol] = " ";
                        break;
                    } else {
                        String word = board[therow][thecol - 1];
                        board[therow][thecol] = (word);
                        thecol--;
                    }
                } else {
                    String word = board[therow][thecol + 1];
                    board[therow][thecol] = (word);
                    thecol++;
                }
            } else if (movement.equals("U")) {
                if (therow == 0) {
                    if (board[boardsrows - 1][thecol].equals("W")) {
                        if (board[1][thecol].equals("R") || board[1][thecol].equals("Y") || board[1][thecol].equals("B")) {
                            board[0][thecol] = "X";
                            therow = 1;
                        } else if (board[1][thecol].equals("H")) {
                            board[0][thecol] = " ";
                            break;
                        } else {
                            String word = board[1][thecol];
                            board[therow][thecol] = (word);
                            therow = 1;
                        }
                    } else if (board[boardsrows - 1][thecol].equals("R") || board[boardsrows - 1][thecol].equals("Y") || board[boardsrows - 1][thecol].equals("B")) {
                        board[0][thecol] = "X";
                        therow = boardsrows - 1;
                    } else if (board[boardsrows - 1][thecol].equals("H")) {
                        board[0][thecol] = " ";
                        break;
                    } else {
                        String word = board[boardsrows - 1][thecol];
                        board[0][thecol] = word;
                        therow = boardsrows - 1;
                    }
                } else if (board[therow - 1][thecol].equals("R") || board[therow - 1][thecol].equals("Y") || board[therow - 1][thecol].equals("B")) {
                    board[therow][thecol] = ("X");
                    therow--;
                } else if (board[therow - 1][thecol].equals("H")) {
                    board[therow][thecol] = " ";
                    break;
                } else if (board[therow - 1][thecol].equals("W")) {
                    if (board[therow + 1][thecol].equals("R") || board[therow + 1][thecol].equals("Y") || board[therow + 1][thecol].equals("B")) {
                        board[therow][thecol] = "X";
                        therow++;
                    } else if (therow == boardsrows - 1) {
                        if (board[0][thecol].equals("R") || board[0][thecol].equals("Y") || board[0][thecol].equals("B")) {
                            board[boardsrows - 1][thecol] = "X";
                            therow = 0;
                        } else if (board[0][thecol].equals("H")) {
                            board[boardsrows - 1][thecol] = " ";
                            break;
                        } else {
                            String word = board[0][thecol];
                            board[therow][thecol] = (word);
                            therow = 0;
                        }
                    } else if (board[therow + 1][thecol].equals("H")) {
                        board[therow][thecol] = " ";
                        break;
                    } else {
                        String word = board[therow + 1][thecol];
                        board[therow][thecol] = (word);
                        therow++;
                    }
                } else {
                    String word = board[therow - 1][thecol];
                    board[therow][thecol] = (word);
                    therow--;
                }
            } else if (movement.equals("D")) {
                if (therow == boardsrows - 1) {
                    if (board[0][thecol].equals("W")) {
                        if (board[boardsrows - 2][thecol].equals("R") || board[boardsrows - 2][thecol].equals("Y") || board[boardsrows - 2][thecol].equals("B")) {
                            board[boardsrows - 1][thecol] = "X";
                            therow = boardsrows - 2;
                        } else if (board[boardsrows - 2][thecol].equals("H")) {
                            board[boardsrows - 1][thecol] = " ";
                            break;
                        } else {
                            String word = board[boardsrows - 2][thecol];
                            board[therow][thecol] = (word);
                            therow = boardsrows - 2;
                        }
                    } else if (board[0][thecol].equals("R") || board[0][thecol].equals("Y") || board[0][thecol].equals("B")) {
                        board[boardsrows - 1][thecol] = "X";
                        therow = 0;
                    } else if (board[0][thecol].equals("H")) {
                        board[boardsrows - 1][thecol] = " ";
                        break;
                    } else {
                        String word = board[0][thecol];
                        board[boardsrows - 1][thecol] = word;
                        therow = 0;
                    }
                } else if (board[therow + 1][thecol].equals("R") || board[therow + 1][thecol].equals("Y") || board[therow + 1][thecol].equals("B")) {
                    board[therow][thecol] = ("X");
                    therow++;
                } else if (board[therow + 1][thecol].equals("H")) {
                    board[therow][thecol] = " ";
                    break;
                } else if (board[therow + 1][thecol].equals("W")) {
                    if (board[therow - 1][thecol].equals("R") || board[therow - 1][thecol].equals("Y") || board[therow - 1][thecol].equals("B")) {
                        board[therow][thecol] = "X";
                        therow++;
                    } else if (therow == 0) {
                        if (board[boardsrows - 1][thecol].equals("R") || board[boardsrows - 1][thecol].equals("Y") || board[boardsrows - 1][thecol].equals("B")) {
                            board[0][thecol] = "X";
                            therow = boardsrows - 1;
                        } else if (board[boardsrows - 1][thecol].equals("H")) {
                            board[0][thecol] = " ";
                            break;
                        } else {
                            String word = board[boardsrows - 1][thecol];
                            board[therow][thecol] = (word);
                            therow = boardsrows - 1;
                        }
                    } else if (board[therow - 1][thecol].equals("H")) {
                        board[therow][thecol] = " ";
                        break;
                    } else {
                        String word = board[therow - 1][thecol];
                        board[therow][thecol] = (word);
                        therow++;
                    }
                } else {
                    String word = board[therow + 1][thecol];
                    board[therow][thecol] = (word);
                    therow++;
                }
            } else {
                System.err.println("Direction names aren't correct.");
            }
            if (board[therow][thecol] != " ") {
                board[therow][thecol] = "*";
            }
        }
            return board;
        }

        public static int score(String thefile, String secondfile) throws IOException {
        String[][] board = creatingboard(thefile);
        String[][] finalboard = boardaftermoves(secondfile,thefile);
        int r = 0; int y = 0 ; int b = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals("R")) {
                    r++;
                    }
                if (board[i][j].equals("Y")) {
                    y++;
                }
                if (board[i][j].equals("B")) {
                    b++;
                }
                }
            }
        int fr=0; int fy =0; int fb= 0;
            for (int i = 0; i < finalboard.length; i++) {
                for (int j = 0; j < finalboard[i].length; j++) {
                    if (finalboard[i][j].equals("R")) {
                        fr++;
                    }
                    if (finalboard[i][j].equals("Y")) {
                        fy++;
                    }
                    if (finalboard[i][j].equals("B")) {
                        fb++;
                    }
                }
            }
            int cal = 10*(r-fr)+5*(y-fy)-5*(b-fb);
            return cal;




        }
        public static void writer(String thefile, String secondfile) throws IOException {
        FileReader thereader = new FileReader("move.txt");
        BufferedReader reader = new BufferedReader(thereader);
        FileWriter thewriter = new FileWriter("output.txt");
        BufferedWriter writer = new BufferedWriter(thewriter);
        writer.write("Game board:" + "\n");
        String[][] firstboard = creatingboard(thefile);
        String[][] finalboard = boardaftermoves(secondfile,thefile);
        for(String[] eleman : firstboard){
            for(String element : eleman){
                writer.write(element+ " ");
            }
            writer.write("\n");
        }
        writer.write("\n");
        writer.write("Your movement is:" + "\n");
        String[] movelist = reader.readLine().split(" ");
        boolean truth = false;
        for(String moves: movelist){
            writer.write(moves+ " ");
        }
        writer.write("\n\n");
        writer.write("Your output is:" + "\n");
        for(String[] eleman : finalboard){
            for(String element : eleman){
                if(element == " "){
                    truth = true;
                }
                writer.write(element+ " ");
            }
            writer.write("\n");
        }
        writer.write("\n");
        if(truth == true){
            writer.write("Game Over!"+ "\n");
        }
        writer.write("Score:"+ score(thefile,secondfile));
        writer.close();
        thewriter.close();



        }
    }









