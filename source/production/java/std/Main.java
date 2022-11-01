/*
Project 4: Hydra Heads

 x = 3 heads
 y = 3 tails
 S = 9

 2: y = y+1
 3: x = x-2
 4: x = x+1
  y = y-2
*/

package std;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scannerObj = new Scanner(System.in);
    int headNum = 0;
    int tailNum = 0;

    // need at least 1 of these
    while (headNum <= 0 && tailNum <= 0) {
      String headQ = "How many heads would you like?: ";
      headNum = getInt(scannerObj, headQ);

      String tailQ = "How many tails would you like?: ";
      tailNum = getInt(scannerObj, tailQ);
    }

    int moveNum;
    int userCount = 0;

    while (!isWinner(headNum,tailNum)) {
      showOptions();
      String moveQ = "Choose an option (1–6): ";
      moveNum = optNum(scannerObj, moveQ);

      if (moveNum == 6) {
        System.out.println("Exiting");
        System.exit(0);
      }

      if (moveNum == 5) {
        int S = shortWin(headNum, tailNum);
        System.out.println();
        System.out.println("Minimum number of moves to win: " + S);
        System.out.println();
        continue;
      }

      if (moveNum >= 1 && moveNum <= 4) {
        tailNum = processTails(moveNum, tailNum);
        headNum = processHeads(moveNum, headNum);

        // returns -1 if only one left and asking for 2
        if (tailNum != -1 && headNum != -1) {
          userCount++;
        } else {
            if (headNum == -1)
              headNum = 1;

            if (tailNum == -1) {
              tailNum = 1;
              headNum -= 1;
            }
        }
      }

      if ((headNum >= 1 && tailNum >= 0) ||
          (headNum >= 0 && tailNum >= 1)) {
        System.out.println("Number of Heads: " + headNum);
        System.out.println("Number of Tails: " + tailNum);
        System.out.println("Moves so far: " + userCount);
      }
      System.out.println();
    }

    if (isWinner(headNum,tailNum)) {
      System.out.println("You win in " + userCount + " moves.");
    }

  } //close public static void

  public static int optNum(Scanner scannerObj, String args) {
    int inputNum = -1;

    while (inputNum > 6 || inputNum < 1) {
      System.out.print(args);

      try {
        inputNum = Integer.parseInt(scannerObj.next());
      } catch (NumberFormatException e) {
          System.out.println();
          System.out.println("Invalid user input");
          System.out.println();
      }
    }
    return inputNum;
  }

  public static int getInt(Scanner scannerObj, String args) {
    int inputNum = -1;

    while (inputNum < 0) {
      System.out.print(args);

      try {
        inputNum = Integer.parseInt(scannerObj.next());
      } catch (NumberFormatException e) {
          System.out.println();
          System.out.println("Invalid user input");
          System.out.println();
      }
    }
    return inputNum;
  }

  public static int processHeads(int moveNum, int headNum) {
    if (moveNum == 3)
      headNum = headNum - 2;

    if (moveNum == 4)
      headNum = headNum + 1;

    if (headNum >= 0) {
      return headNum;
    } else {
        System.out.println();
        System.out.println("Only one head left. Choose a different move.");
        System.out.println();
        return -1;
    }
  }

  public static int processTails(int moveNum, int tailsNum) {
    if (moveNum == 2)
      tailsNum++;

    if (moveNum == 4)
      tailsNum = tailsNum - 2;

    if (tailsNum >= 0) {
      return tailsNum;
    } else {
        System.out.println();
        System.out.println("Only one tail left. Choose a different move!");
        System.out.println();
        return -1;
    }
  }

  public static boolean isWinner(int totalHeads, int totalTails) {
    return totalTails == 0 && totalHeads == 0;
  }

  public static int shortWin(int headCalc, int tailCalc) {
    int totalCalc = 0;

    // do tails first because it changes heads.
    // repeatedly do turn 4 down to 0 or 1
    while (tailCalc >= 2) {
      totalCalc++;
      tailCalc = tailCalc - 2;
      headCalc++;
    }

    // now repeatedly do turn 3 on heads down to 0 or 1
    while (headCalc >= 2) {
      totalCalc++;
      headCalc = headCalc - 2;
    }

    // (0,1) is 6 turns left
    if (headCalc == 0 && tailCalc == 1)
      totalCalc += 6;

    //(1,0) is 4
    if (headCalc == 1 && tailCalc == 0)
      totalCalc += 4;

    //(1,1) is 3
    if (headCalc == 1 && tailCalc == 1)
      totalCalc += 3;

    return totalCalc;
  }

  public static void showOptions() {
    System.out.println("Princess Perly has been kidnapped by the magical Hydra!");
    System.out.println("The kingdom is in chaos.");
    System.out.println("Make your move to defeat the hydra!");
    System.out.println("\t1 : With the first move, Knight PyPy can cut off exactly " +
        "one of Hydra’s heads. Another grows back immediately!");
    System.out.println("\t2 : With the second move, Knight PyPy can cut off exactly " +
        "one of Hydra’s tails. Two more grow back!");
    System.out.println("\t3 : With the third move, Knight PyPy can cut off exactly " +
        "two of Hydra’s heads. Nothing grows!");
    System.out.println("\t4 : With the fourth move, Knight PyPy can cut off exactly " +
        "two of Hydra’s tails. One head grows back!");
    System.out.println("\t5 : What is the minimum number of moves that PyPy needs to " +
        "use to kill Hydra?");
    System.out.println("\t6 : Exit, give up.");
  }
}
