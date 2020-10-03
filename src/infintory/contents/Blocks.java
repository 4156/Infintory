package infintory.contents;


import infintory.types.block.MutiCrafterBlock;
import infintory.types.block.ResearchCoreBlock;
import mindustry.content.Items;
import mindustry.ctype.ContentList;
import mindustry.gen.Building;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.production.GenericCrafter;

import static mindustry.type.ItemStack.with;

public class Blocks implements ContentList {
    public static Block
     //special
     research_core,
     //craft
     research_pack_maker
      ;
    @Override
    public void load() {
       research_core=new ResearchCoreBlock("research"){{
           requirements(Category.crafting, with(Items.lead, 30, Items.titanium, 20));
           size = 3;
        }};
       research_pack_maker=new MutiCrafterBlock("pack-maker"){{
           requirements(Category.crafting, with(Items.lead, 30, Items.titanium, 20));
           size=3;
           consumes.power(1.0f);
           recipes.add(Recipes.blue_pack);
           recipes.add(Recipes.red_pack);
           recipes.add(Recipes.yellow_pack);
           recipes.add(Recipes.green_pack);
           craftTime=60f;
       }};
    }
}
