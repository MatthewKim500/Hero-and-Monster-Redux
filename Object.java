public class Object{
    public String name;
    public int countF = 0;
    public Hero hero;
    public Monster mon;
    public Farmer farmer;
    public Potion potion;
    public boolean show = false;
    
    public Object(String n){
        name = n;
        
        if(name.equals("Hero")){
            hero = new Hero();
            mon = null;
            farmer = null;
            potion = null;
        }else if(name.equals("Monster")){
            mon = new Monster();
        }else if(name.equals("Farmer")){
            farmer = new Farmer(countF);
            countF++;
        }else if(name.equals("Potion")){
            potion = new Potion();
        }
    }
    
    public String getName(){
        return name;
    }
}