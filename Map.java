import java.io.*;
import java.util.*;
public class Map{
    Scanner kbReader = new Scanner(System.in);
    Object[][] map = new Object[15][15];
    public int x, y;
    public Object charHero = new Object("Hero");
    
    public Map(){
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                map[i][j] = new Object("nothing");
            }
        }
        map[14][0] = charHero;
        System.out.println("Hero has been created");
        x = randomPos();
        y = randomPos();
    }
    
    public int randomPos(){
        return (int)(Math.random() * 15);
    }
    
    public void create(){
        int numMon = 0;
        int numFar = 0;
        int numPot = 0;
        while(numMon < 6){
            x = randomPos();
            y = randomPos();
            if(map[x][y].getName().equals("nothing")){
                map[x][y] = new Object("Monster");
                System.out.println("Monster has been created.");
                numMon++;
            }
        }
        
        while(numFar < 2){
            x = randomPos();
            y = randomPos();
            if(map[x][y].getName().equals("nothing")){
                map[x][y] = new Object("Farmer");
                map[x][y].farmer = new Farmer(numFar);
                System.out.println("Farmer has been created.");
                numFar++;
            }
        }
        
        while(numPot < 2){
            x = randomPos();
            y = randomPos();
            if(map[x][y].getName().equals("nothing")){
                map[x][y] = new Object("Potion");
                System.out.println("Potion has been created.");
                numPot++;
            }
        }
    }
    
    public void move(String direction){
        boolean runAway = false;
        if(charHero.hero.x > 0 && direction.equals("w")){
            System.out.println("Hero moves to the north");
            if(!map[charHero.hero.x][charHero.hero.y].name.equals("Farmer")){
                map[charHero.hero.x][charHero.hero.y] = new Object("nothing");
            }
            charHero.hero.x -= 1;
            //check(charHero.hero.x, charHero.hero.y, runAway);
            if(check(charHero.hero.x, charHero.hero.y, runAway)){
                charHero.hero.x += 1;
            }else{
                map[charHero.hero.x][charHero.hero.y] = charHero;
            }
        }else if(charHero.hero.x < 14 && direction.equals("s")){
            System.out.println("Hero moves to the south");
            if(!map[charHero.hero.x][charHero.hero.y].name.equals("Farmer")){
                map[charHero.hero.x][charHero.hero.y] = new Object("nothing");
            }
            charHero.hero.x += 1;
            //check(charHero.hero.x, charHero.hero.y, runAway);
            if(check(charHero.hero.x, charHero.hero.y, runAway)){
                charHero.hero.x -= 1;
            }else{
                map[charHero.hero.x][charHero.hero.y] = charHero;
            }
        }else if(charHero.hero.y > 0 && direction.equals("a")){
            System.out.println("Hero moves to the east");
            if(!map[charHero.hero.x][charHero.hero.y].name.equals("Farmer")){
                map[charHero.hero.x][charHero.hero.y] = new Object("nothing");
            }
            charHero.hero.y -= 1;
            //check(charHero.hero.x, charHero.hero.y, runAway);
            if(check(charHero.hero.x, charHero.hero.y, runAway)){
                charHero.hero.y += 1;
            }else{
                map[charHero.hero.x][charHero.hero.y] = charHero;
            }
        }else if(charHero.hero.y < 14 && direction.equals("d")){
            System.out.println("Hero moves to the west");
            if(!map[charHero.hero.x][charHero.hero.y].name.equals("Farmer")){
                map[charHero.hero.x][charHero.hero.y] = new Object("nothing");
            }
            charHero.hero.y += 1;
            //check(charHero.hero.x, charHero.hero.y, runAway);
            if(check(charHero.hero.x, charHero.hero.y, runAway)){
                charHero.hero.y -= 1;
            }else{
                map[charHero.hero.x][charHero.hero.y] = charHero;
            }
        }else{
            System.out.println("Hero is at the edge of the world");
        }
    }
    
    public boolean check(int a, int b, boolean runAway){
        runAway = false;
        if(map[a][b].name.equals("Monster")){
            return foundMon(a, b, runAway);
        }else if(map[a][b].name.equals("Farmer")){
            return foundFarmer(a, b);
        }else if(map[a][b].name.equals("Potion")){
            foundPotion();
            map[a][b] = new Object("nothing");
        }
        return runAway;
    }
    
    public boolean foundMon(int a, int b, boolean runAway){
        runAway = false;
        boolean encounter = true;
        System.out.println("Hero encounters a monster! The monster engages!" +
                           "\n" + "Enter an action (run, attack):");
        while(encounter){
            String action = kbReader.next();
            if(charHero.hero.weapon.equals("Axe"))
            {
              if(action.equals("attack") && charHero.hero.health > 0){
                attack(a, b);
                if(map[a][b].mon.health <= 0){
                    encounter = false;
                    System.out.println("The monster is dead.");
                }else{
                    charHero.hero.health -= map[a][b].mon.attack;
                    System.out.println("Monster attacks, Hero's health is now " + charHero.hero.health);
                    charHero.hero.health -= map[a][b].mon.attack;
                    System.out.println("Monster attacks, Hero's health is now " + charHero.hero.health);
                }
            }else if(action.equals("run")){
                if(run(a, b)){
                    runAway = true;
                    encounter = false;
                    System.out.println("Hero successfully run away from the monster." + "\n" +"Hero returns to previous position");
                }else{
                    charHero.hero.health -= map[a][b].mon.attack;
                    System.out.println("Hero tries to run! The monster is too fast!" + "\n" +
                    "The monster attacks! Hero’s energy goes down to " + charHero.hero.health);
                };
            }  
            }
            if(action.equals("attack") && charHero.hero.health > 0){
                attack(a, b);
                if(map[a][b].mon.health <= 0){
                    encounter = false;
                    System.out.println("The monster is dead.");
                }else{
                    charHero.hero.health -= map[a][b].mon.attack;
                    System.out.println("Monster attacks, Hero's health is now " + charHero.hero.health);
                }
            }else if(action.equals("run")){
                if(run(a, b)){
                    runAway = true;
                    encounter = false;
                    System.out.println("Hero successfully run away from the monster." + "\n" +"Hero returns to previous position");
                }else{
                    charHero.hero.health -= map[a][b].mon.attack;
                    System.out.println("Hero tries to run! The monster is too fast!" + "\n" +
                    "The monster attacks! Hero’s energy goes down to " + charHero.hero.health);
                };
            }
        }
        if(!runAway){
            map[a][b] = new Object("nothing");
        }
        return runAway;
    }
    
    public void attack(int a, int b){
        System.out.println("The hero attacks");
        map[a][b].mon.health -= charHero.hero.attack;
        if(map[a][b].mon.health <= 0){
            System.out.println("Hero slains a monster");
            charHero.hero.kill++;
        }else if(charHero.hero.health <= 0){
            System.out.println("Hero dies");
        }else{
            System.out.println("Monster's health is now: " + map[a][b].mon.health);
        }
        
    }
    
    public boolean run(int a, int b){
        int chance = (int)(Math.random() * 4);
        boolean runAway = false;
        System.out.println(map[a][b].mon.speed);
        if(map[a][b].mon.speed == 0){
            if(chance == 0){
                runAway = false;
            }else{
                runAway = true;
            };
        };
        
        if(map[a][b].mon.speed == 1){
            if(chance == 0 || chance == 1){
                runAway = false;
            }else{
                runAway = true;
            };
        };
        
        if(map[a][b].mon.speed == 2){
            if(chance == 0 || chance == 1 || chance == 2){
                runAway = false;
            }else{
                runAway = true;
            };
        };
        
        if(map[a][b].mon.speed == 3){
            runAway = false;
        }
        return runAway;
    }
    
    public void foundPotion(){
        System.out.println("Hero found a potion.");
        System.out.println("Restore Hero's health to 100");
        charHero.hero.health = 100;
    }
    
    public void foundSword(){
        System.out.println("Hero found a sword.");
        Weapon sword = new Weapon();
        charHero.hero.attack = sword.damage;
    }
    
    public boolean foundFarmer(int a, int b){
        boolean notDisappear = true;
        System.out.println("Hero meets a scared farmer." + "\n" + map[a][b].farmer.lines);
        if(map[a][b].farmer.type.equals("armor") && charHero.hero.kill >= 2){
            charHero.hero.hasArmor = true;
            notDisappear = false;
            System.out.println("Hero receives an armor.");
        }else if(map[a][b].farmer.type.equals("sword") && charHero.hero.kill >= 4){
            charHero.hero.hasSword = true;
            notDisappear = false;
            System.out.println("Hero receives a sword.");
        }
        
        if(!notDisappear){
                map[a][b] = new Object("nothing");
            }
        return notDisappear;
    }
    
    public boolean checkEnd(){
        boolean end = false;
        if(charHero.hero.kill == 6){
            System.out.println("All the monster has been slain." + "\n" + 
            "The village is saved from monster's invasion."  + "\n" + 
            "Hero fly up the sky and disappear forever." + "\n" + 
            "The story of a hero ended." );
            end = true;
        }
        if(charHero.hero.health <= 0){
            System.out.println("The hero dies.");
            end = true;
        }
        return end;
    }
    
}
