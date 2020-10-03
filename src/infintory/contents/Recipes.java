package infintory.contents;

import infintory.types.logic.Recipe;
import infintory.types.logic.RecipeType;
import mindustry.ctype.ContentList;
import mindustry.type.ItemStack;

public class Recipes implements ContentList {
    public static Recipe
    //packs
    red_pack,green_pack,yellow_pack,blue_pack
    ;
    public Recipes(){

    }
    @Override
    public void load() {
        red_pack=new Recipe(new ItemStack(Items.red_pack,1),new ItemStack(mindustry.content.Items.copper,1),new ItemStack(mindustry.content.Items.lead,1));
        green_pack=new Recipe(new ItemStack(Items.green_pack,1),new ItemStack(mindustry.content.Items.graphite,1),new ItemStack(mindustry.content.Items.silicon,1));
        yellow_pack=new Recipe(new ItemStack(Items.yellow_pack,1),new ItemStack(mindustry.content.Items.plastanium,1),new ItemStack(mindustry.content.Items.titanium,1));
        blue_pack=new Recipe(new ItemStack(Items.blue_pack,1),new ItemStack(mindustry.content.Items.coal,1),new ItemStack(mindustry.content.Items.phasefabric,1));
    }
}
