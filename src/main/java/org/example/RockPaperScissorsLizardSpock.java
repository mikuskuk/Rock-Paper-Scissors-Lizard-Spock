package org.example;

import java.util.Arrays;

import static org.example.RockPaperScissorsLizardSpockApp.*;

public class RockPaperScissorsLizardSpock {

    public static void playGame(String[] playerNames, String playerName, int numRounds) {

        // Get the index of the player's name in the array
        int playerIndex = Arrays.asList(playerNames).indexOf(playerName);
        System.out.println("\nGet ready to play!\n");

        // Play against other players
        for (int i = 0; i < playerNames.length; i++) {
            if (i == playerIndex) {
                continue; // Skip playing against oneself
            }

            String opponentName = playerNames[i];
            System.out.print(playerName + " vs " + opponentName + "\n");

            // Play the amount of rounds required
            for (int round = 1; round <= numRounds; round++) {
                System.out.println("\nRound " + round);
                System.out.print(playerName + ", enter your move - rock, paper, scissors, lizard or spock: ");
                String playerChoice = scanner.nextLine().toLowerCase();

                // Validate the player's choice
                if (!isValidChoice(playerChoice)) {
                    System.out.println("Invalid choice! Try again.");
                    round--;
                    continue;
                }

                String computerChoice = getRandomChoice();
                System.out.println(opponentName + " move: " + computerChoice);

                // Determine result of the round and update the scores
                int result = getResult(playerChoice, computerChoice);
                if (result == 1) {
                    System.out.println(playerName + " wins this round!\n");
                    scores[playerIndex]++;
                } else if (result == -1) {
                    System.out.println(opponentName + " wins this one!\n");
                    int opponentIndex = Arrays.asList(playerNames).indexOf(opponentName);
                    scores[opponentIndex]++;
                } else {
                    System.out.println("It's a tie!\n");
                }
            }
        }

        // Computers play amongst themselves
        for (int i = 1; i < playerNames.length; i++) {
            for (int j = i + 1; j < playerNames.length; j++) {
                String player1 = playerNames[i];
                String player2 = playerNames[j];
                System.out.print(player1 + " vs " + player2 + "\n");

                for (int round = 1; round <= numRounds; round++) {
                    String player1Choice = getRandomChoice();
                    String player2Choice = getRandomChoice();
                    System.out.println("\nRound " + round);
                    System.out.println(player1 + " move: " + player1Choice);
                    System.out.println(player2 + " move: " + player2Choice);

                    int result = getResult(player1Choice, player2Choice);
                    if (result == 1) {
                        System.out.println(player1 + " wins this round!\n");
                        int player1Index = Arrays.asList(playerNames).indexOf(player1);
                        scores[player1Index]++;
                    } else if (result == -1) {
                        System.out.println(player2 + " wins this round!\n");
                        int player2Index = Arrays.asList(playerNames).indexOf(player2);
                        scores[player2Index]++;
                    } else {
                        System.out.println("It's a tie!\n");
                    }
                }
            }
        }
    }

    // Check if the given choice is a valid option
    private static boolean isValidChoice(String choice) {
        return Arrays.asList(choice).contains(choice);
    }

    // Generate random choice for the available options
    private static String getRandomChoice() {
        String[] choices = {"rock", "paper", "scissors", "lizard", "spock"};
        int randomIndex = random.nextInt(choices.length);
        return choices[randomIndex];
    }

    // Determine the result of a game round based on the choices made by the player and the computer
    private static int getResult(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            return 0;
        }

        switch (playerChoice) {
            case "rock" -> {
                switch (computerChoice) {
                    case "scissors", "lizard" -> {
                        System.out.println("Rock crushes " + computerChoice + ".");
                        return 1;
                    }
                    case "paper", "spock" -> {
                        System.out.println(capitalize(computerChoice) + " covers Rock.");
                        return -1;
                    }
                    default -> {
                        System.out.println("Invalid choice for the computer.");
                        return 0;
                    }
                }
            }
            case "paper" -> {
                switch (computerChoice) {
                    case "rock", "spock" -> {
                        System.out.println("Paper covers " + computerChoice + ".");
                        return 1;
                    }
                    case "scissors", "lizard" -> {
                        System.out.println(capitalize(computerChoice) + " cuts Paper.");
                        return -1;
                    }
                    default -> {
                        System.out.println("Invalid choice for the computer.");
                        return 0;
                    }
                }
            }
            case "scissors" -> {
                switch (computerChoice) {
                    case "paper", "lizard" -> {
                        System.out.println("Scissors cuts " + computerChoice + ".");
                        return 1;
                    }
                    case "rock", "spock" -> {
                        System.out.println(capitalize(computerChoice) + " crushes Scissors.");
                        return -1;
                    }
                    default -> {
                        System.out.println("Invalid choice for the computer.");
                        return 0;
                    }
                }
            }
            case "lizard" -> {
                switch (computerChoice) {
                    case "paper", "spock" -> {
                        System.out.println(capitalize(computerChoice) + " is poisoned by Lizard.");
                        return 1;
                    }
                    case "rock", "scissors" -> {
                        System.out.println("Lizard eats " + computerChoice + ".");
                        return -1;
                    }
                    default -> {
                        System.out.println("Invalid choice for the computer.");
                        return 0;
                    }
                }
            }
            case "spock" -> {
                switch (computerChoice) {
                    case "rock", "scissors" -> {
                        System.out.println("Spock vaporizes " + computerChoice + ".");
                        return 1;
                    }
                    case "paper", "lizard" -> {
                        System.out.println(capitalize(computerChoice) + " disproves Spock.");
                        return -1;
                    }
                    default -> {
                        System.out.println("Invalid choice for the computer.");
                        return 0;
                    }
                }
            }
            default -> {
                System.out.println("Invalid choice.");
                return 0;
            }
        }
    }

    private static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String generateNames(int playerIndex) {
        String[] names = {
                "Bot McBotface",
                "Gigabyte Gang",
                "Fast Fingers",
                "Pac Man",
                "The Trouble Maker",
                "Shaquille Oatmeal",
                "Karen",
                "Stranger Danger",
                "Username"
        };

        int index = (playerIndex - 1) % names.length;
        return names[index];
    }

    // Retrieve valid user input within a specified range
    public static int getValidInput(int min, int max) {
        int input;
        while (true) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    break;
                } else {
                    System.out.print("Invalid input! Please enter a number between " + min + " and " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a number between " + min + " and " + max + ": ");
            }
        }
        return input;
    }
}
