public class Hero{
    public int attack, health, kill, x, y; 
    boolean hasArmor, hasSword;
    
    public Hero(){
        attack = (int)(Math.random() * 21 + 10);
        health = 100;
        kill = 0;
        x = 9;
        y = 0;
        hasArmor = false;
        hasSword = false;
    }
    
}