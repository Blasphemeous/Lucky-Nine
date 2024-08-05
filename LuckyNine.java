
/**
 *
 * @author blasphemeous
 */


package LuckyNine; 


import java.util.Scanner; 
import java.util.ArrayList; 
import java.util.Random; 
import java.util.Collections; 
import java.util.InputMismatchException;
import java.util.Arrays; 

class Card{
    int value; 
    String face; 
    
    Card(int value, String face){
    this.value = value; 
    this.face = face; 
}
    @Override
    public String toString(){
        return value + " " + face; 
    }
}

public class LuckyNine {
  public static void main(String[] args){
      Scanner scan = new Scanner(System.in); 
      
      System.out.println("LUCKY 9 GAME"); 
      
      System.out.println("[1]Start"); 
      
      System.out.println("[2]Exit");
      
      System.out.print("Choice: "); 
      
      int choice = scan.nextInt(); 
      
      switch(choice){
          
          case 1: 
              GameInterface(); 
              break;
              
          case 2: 
              break; 
              
          default: 
              System.out.println("INVALID INPUT"); 
              
      }
  }                                             
  
  
  
  public static void GameInterface(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of players: ");
        int numberOfPlayers = scan.nextInt();
        
        if(numberOfPlayers == 1){
            BankerGame(numberOfPlayers);
        }else{
        int[] balances = new int[numberOfPlayers];
        Arrays.fill(balances, 500); 
        boolean continuePlaying = true;
        
        while (continuePlaying) {
            Game(scan, numberOfPlayers, balances);
            System.out.print("Do you want to play another round? (yes/no): ");
            continuePlaying = scan.next().equalsIgnoreCase("yes");
        }

        System.out.println("Game over! Final balances:");
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Player " + (i + 1) + ": " + balances[i]);
        }
  }
  }
  
  
  
  public static void Game(Scanner scan, int numberOfPlayers, int[] balances){  
      
      ArrayList<Integer> result = new ArrayList<>(); 
     
      
      String faceCard[] = {"Spade", "Diamond", "Clover", "Heart"}; 
      
      ArrayList<Card> deck = new ArrayList<>(); 
      
      for(int value = 1 ; value <= 9; value++){
          for(String face : faceCard){
              deck.add(new Card(value, face)); 
          }
      }
      
      
      
      Random number = new Random(); 
      
     
    
      System.out.println("Each player has 500 tokens to bet in each game.\nThe maximum bet is 500 tokens."); 
     
      System.out.println("A pleasant day, my dear players. I am your banker, we will play Lucky 9.\n"); 
      
      Collections.shuffle(deck, new Random());
      
      
      int players[] = new int[numberOfPlayers]; 
      int bets[] = new int[numberOfPlayers];
      
      int index = 0; 
              
        for (int h = 1; h <= numberOfPlayers; h++) {
            int bet = -1;

         
            while (true) {
                try {
                    System.out.print("Player " + h + ", enter your bet (current balance: " + balances[h-1] + "): ");
                    bet = scan.nextInt();

                    if (bet > balances[h-1]) {
                        System.out.println("Insufficient funds. Please enter a valid bet.");
                    } else if (bet <= 0) {
                        System.out.println("Bet must be greater than 0.");
                    } else {
                        balances[h-1] -= bet; 
                        bets[h - 1] = bet;
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Input. Please enter a number.");
                    scan.next(); 
                }
            }
      
        }
      
      for(int i = 0; i < numberOfPlayers; i++){
          int sum = 0;
          System.out.println("\nPlayer " + (i + 1)  + "'s"+ " turn" + ":"); 
          for(int k = 0; k < 3; k++){ 
              
             Card draw = deck.get(index++); 
               
              int cards = number.nextInt(9) + 1; 
            
              sum+=draw.value; 
              
              System.out.println(draw); 
          }
           
          int score = sum % 10; 
          
          players[i] = score; 
      
          result.add(score); 
          
          System.out.println("Score: " + score + "\n");
          
      }
      
      int maxScore = -1;
      
      ArrayList<Integer> winners = new ArrayList<>(); 
      
      for(int x = 0; x < numberOfPlayers ; x++){ 
          if(players[x] > maxScore){ 
              maxScore = players[x]; 
              winners.clear(); 
              winners.add(x + 1); 
          }else if(players[x] == maxScore){
              winners.add(x = 1); 
          }
      }
      
      
      if(winners.size() == 1){
      System.out.println("Player"  + " " + winners.get(0) + " is the winner with the score of" + " " + maxScore); 
      }else{
          System.out.println("There's a tie! The winners are Players ");
          for(int y = 0; y < winners.size(); y++){
              System.out.print(winners.get(y));
              if(y < winners.size() - 1){
                  System.out.print(", ");
          }
      }
         System.out.println(" with a score of " + maxScore + "\n");
        
      }
   
      
      int totalBets = 0; 
      
      for(int bet : bets){
          totalBets += bet; 
      }
      
      for(int winner : winners){ 
          balances[winner - 1] += totalBets;
      }
      
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Player " + (i + 1) + "'s final balance: " + balances[i]);
        }
        
}
  
  public static void BankerGame(int player) {
    Scanner scan = new Scanner(System.in);
    int playerBalance = 500;
    int bankerBalance = 500;
    boolean continuePlaying = true;
    
    while (continuePlaying) {
        String faceCard[] = {"Spade", "Diamond", "Clover", "Heart"};
        ArrayList<Card> deck = new ArrayList<>();
        
        for (int value = 1; value <= 9; value++) {
            for (String face : faceCard) {
                deck.add(new Card(value, face));
            }
        }
        
        Collections.shuffle(deck, new Random());
        
        int playerBet = -1;
        while (true) {
            try {
                System.out.print("Enter your bet (current balance: " + playerBalance + "): ");
                playerBet = scan.nextInt();
                
                if (playerBet > playerBalance) {
                    System.out.println("Insufficient funds. Please enter a valid bet.");
                } else if (playerBet <= 0) {
                    System.out.println("Bet must be greater than 0.");
                } else {
                    playerBalance -= playerBet;
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scan.next();
            }
        }
        
        int playerSum = 0;
        int bankerSum = 0;
        
        System.out.println("\nPlayer's turn:");
        for (int i = 0; i < 3; i++) {
            Card draw = deck.remove(0);
            playerSum += draw.value;
            System.out.println(draw);
        }
        int playerScore = playerSum % 10;
        System.out.println("Player's score: " + playerScore + "\n");
        
        System.out.println("Banker's turn:");
        for (int i = 0; i < 3; i++) {
            Card draw = deck.remove(0);
            bankerSum += draw.value;
            System.out.println(draw);
        }
        int bankerScore = bankerSum % 10;
        System.out.println("Banker's score: " + bankerScore + "\n");
        
        if (playerScore > bankerScore) {
            playerBalance += 2 * playerBet;
            System.out.println("Player wins!");
        } else if (playerScore < bankerScore) {
            bankerBalance += playerBet;
            System.out.println("Banker wins!");
        } else {
            playerBalance += playerBet;
            System.out.println("It's a tie!");
        }
        
        if (playerBalance <= 0) {
            System.out.println("Player has run out of tokens. Game over!");
            continuePlaying = false;
        } else if (bankerBalance <= 0) {
            System.out.println("Banker has run out of tokens. Player wins the game!");
            continuePlaying = false;
        } else {
            System.out.print("Do you want to play another round? (yes/no): ");
            continuePlaying = scan.next().equalsIgnoreCase("yes");
        }
    }
    
    System.out.println("Game over! Final balance:");
    System.out.println("Player: " + playerBalance);
    System.out.println("Banker: " + bankerBalance);
}
}

