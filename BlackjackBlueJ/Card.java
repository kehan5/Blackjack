
/**
 * Write a description of class Card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Card
{
    // instance variables - replace the example below with your own

private String name;
private int value;
private String suit;


public Card(String name, int value, String suit) {
    this.name = name;
    this.value = value;
    this.suit=suit;
}


public void card(int value) {
    this.value = value;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public int getValue(){
    return value;
}

public void AceValue(int a){
    if(a==1 || a==11)
    a=value;
}

public String toString(){
    return name+" of "+ suit;
}
}
