public class Potion{
    int health;
    public Potion(){
        health = 100;
    }
    
    public void use(Hero h){
        h.health = health;
    }
    
}