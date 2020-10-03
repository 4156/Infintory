package infintory;

import infintory.contents.Blocks;
import infintory.contents.Items;
import infintory.contents.Recipes;
import infintory.contents.Researches;
import infintory.types.logic.Recipe;
import mindustry.mod.*;

public class Infintory extends Mod{
    public static Items items;
    public static Blocks blocks;
    public static Recipes recipes;

    public Infintory(){
        items=new Items();
        blocks=new Blocks();
        recipes=new Recipes();
    }

    @Override
    public void loadContent() {
        items.load();
        recipes.load();
        blocks.load();

    }
}
