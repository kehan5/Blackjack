
/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.*;

public class Game
{

    public static void main(String[] args) throws InterruptedException
    {
        
        //scan in user's name to make the game more personal
        System.out.print("Welcome to Blackjack, please enter your name: ");
        Scanner sc = new Scanner(System.in);
        String user = sc.next();
        Thread.sleep(800);
        boolean play = true; //play=true when user wants to play, false if user doesn't
        int money=1000; //money amount user starts with
        int counter=1; //counts the number of rounds the user has played

        while(play){
            //initialize variables that will be used during the game
            boolean hit = true;
            boolean win=true;
            int bet=1000000;
            int cards=156; 
            int dsum=0;
            int psum=0;
            //Create an ArrayList of cards for the decks used for the game
            ArrayList<Card> decks = new ArrayList<Card>();
            String [] suit = {"Spades", "Hearts", "Clubs", "Diamonds"};
            int [] value = {11,2,3,4,5,6,7,8,9,10,10,10,10};
            String [] name= {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
            for(int i=0; i<3; i++){ //using 3 decks for the game
                for(int a=0; a<4; a++){ 
                    for(int b=0;b<13;b++){ 
                        Card p= new Card(name[b],value[b],suit[a]);
                        decks.add(p);
                    }
                }
            }
            //Storing the dealer's and player's hands in their own Arraylists
            ArrayList<Card> dhand= new ArrayList<Card>(); 
            ArrayList<Card> phand= new ArrayList<Card>();

            System.out.println("\nRound "+counter);
            Thread.sleep(1500);
            System.out.println("\nYour total money value is $"+money);
            Thread.sleep(1000);

            while(bet>money){
                System.out.print("How much would you like to bet? ");
                bet=sc.nextInt();
                if(bet<=money)
                    break;
                else{
                    System.out.println("Can't bet more than you have...");
                    Thread.sleep(300);
                    
                }
                    

            }
            Thread.sleep(600);
            int d1=(int)(Math.random()*cards);
            System.out.println("Dealer draws a "+ decks.get(d1));
            dsum+=decks.get(d1).getValue();
            dhand.add(decks.get(d1));
            decks.remove(d1);
            cards--;
            Thread.sleep(1000);

            int p1=(int)(Math.random()*cards);
            System.out.print(user+" draws a "+decks.get(p1));
            psum+=decks.get(p1).getValue();
            phand.add(decks.get(p1));
            decks.remove(p1);
            cards--;
            int p2=(int)(Math.random()*cards);
            System.out.println(" and also a "+decks.get(p2)); 
            psum+=decks.get(p2).getValue();
            phand.add(decks.get(p2));
            decks.remove(p2);
            cards--;
            Thread.sleep(1000);

            if(psum==21){
                System.out.println("Blackjack!");
            }
            else if(psum<21){
                while(hit){
                    System.out.println("Hit (H) or Stand (S)?");
                    String q=sc.next();
                    if(q.equals("s") || q.equals("S")){
                        hit=false;
                    }
                    else{
                        Thread.sleep(500);
                        int p3=(int)(Math.random()*cards);
                        System.out.println(user+" adds a "+decks.get(p3));
                        psum+=decks.get(p3).getValue();
                        phand.add(decks.get(p3));
                        decks.remove(p3);
                        cards--;

                        if(psum==21){
                            System.out.println("Blackjack!");
                            hit=false;
                        }
                        else if(psum>21){
                            int acecounter=0;
                            for(int a=0; a<phand.size(); a++){
                                if(phand.get(a).getValue()==11){
                                    acecounter++;
                                    phand.remove(a);
                                }
                            }
                            if(acecounter==0){
                                System.out.println("Bust!");
                                hit=false;
                                win=false;
                            }
                            else{
                                while(psum>21 && acecounter>0){
                                    psum=psum-10;
                                    acecounter--;
                                }
                            }
                        }
                        Thread.sleep(1000);
                    }

                }
            }

            if(psum<=21){
                Thread.sleep(400);
                int d2=(int)(Math.random()*cards);
                System.out.println("Dealer's second card is a "+ decks.get(d2));
                dsum+=decks.get(d2).getValue();
                dhand.add(decks.get(d2));
                decks.remove(d2);
                cards--;

                Thread.sleep(1000);

                while(dsum<17)
                {
                    int d3=(int)(Math.random()*cards);
                    System.out.println("Dealer hits and draws a "+ decks.get(d3));
                    dsum+=decks.get(d3).getValue();
                    dhand.add(decks.get(d3));
                    decks.remove(d3);
                    cards--;
                    if(dsum==21){
                        System.out.println("Blackjack!");
                    }
                    else if(dsum>21){
                        int acecount=0;
                        for(int b=0; b<dhand.size(); b++){
                            if(dhand.get(b).getValue()==11){
                                acecount++;
                                dhand.remove(b);
                                
                            }
                        }
                        if(acecount==0){
                            System.out.println("Bust!");
                            win=true;
                        }
                        else{
                            while(dsum>21 && acecount>0){
                                dsum=dsum-10;
                                acecount--;

                            }
                        }
                    }
                    Thread.sleep(1000);
                }
            }

                if(psum>dsum && psum<21)
                    win=true;
                if(dsum<21 && dsum>psum)
                    win=false;

                
                if(dsum==psum){
                    money+=0;
                    System.out.println("Tie");
                    Thread.sleep(600);
                }

                else if(win){
                    System.out.println("You win the hand!");
                    money+=bet;
                    Thread.sleep(600);
                }
                else if(!win){
                    System.out.println("You lost the hand");
                    money-=bet;
                    Thread.sleep(600);
                }
            

            decks.clear();
            dhand.clear();
            phand.clear();
            if(money!=0){
                System.out.print("Play another round? N to Quit: ");
                String a= sc.next();
                if(a.equals("n") || a.equals("N"))
                    play=false;
                else
                    counter++;
            }
            else{
                play=false;
            }

        }
        System.out.println("\nYou ended with $" +money);
        Thread.sleep(1000);

        if(money>1000)
            System.out.println("Great Job!");
        else if(money==1000)
            System.out.println("You did alright");
        else if(money==0)
            System.out.println("HA, You're broke!");
        else
            System.out.println("You're not very good at this");
            
            System.out.println("\n***********GAME OVER**************");
    }
}

