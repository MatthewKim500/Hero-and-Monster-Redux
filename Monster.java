public class Monster{
    int attack, health, speed;
    
    public Monster(){
        attack = (int)(Math.random() * 30 + 1) - 10;
        if(attack <= 0){
            attack = 1;
        }
        health = (int)(Math.random() * 100 + 1);
        speed = (int)(Math.random() * 4);
    }
    
    public String toString(){
        return ("Attack: " + attack + "\n" + "Health: " + health + "\n"
        + "speed: " + speed + "\n");
    }
}