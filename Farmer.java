public class Farmer{
    String type, lines;
    public Farmer(int count){
        if(count == 0){
            lines = "My sheep have been taken… My family is gone and I have nothing left except" + 
            "\n" + "for bronze amor I have buried… Only a true hero will receive this" + "\n" + 
            "Please slain 2 monsters";
            type = "armor";
            Armor bronzeAmor = new Armor();
        }else{
            lines = "I will give you this long sword when you slain 4 monsters";
            type = "sword";
            LongSword lsword = new LongSword();
        }
    }
}