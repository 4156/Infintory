package infintory.types.block;


import arc.scene.style.TextureRegionDrawable;
import arc.scene.ui.layout.Table;
import infintory.Infintory;
import infintory.contents.Items;
import infintory.contents.Recipes;
import infintory.types.logic.Recipe;
import infintory.types.logic.RecipeType;
import mindustry.gen.Building;
import mindustry.type.Item;
import mindustry.ui.Cicon;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.consumers.ConsumeType;
import mindustry.world.consumers.Consumers;

import java.util.ArrayList;


public class MutiCrafterBlock extends GenericCrafter {
    public static ArrayList<Recipe> recipes = new ArrayList<>();


    public MutiCrafterBlock(String name) {
        super(name);
        acceptsItems=true;
        configurable = true;
    }
    public class MutiCrafterBuild extends GenericCrafterBuild {
        public Recipe main;
        @Override
        public boolean acceptItem(Building source, Item item) {
            return true;
        }

        float time;
        @Override
        public void updateTile() {
            time++;
            if(main!=null) {
                if (time > craftTime) {
                    if (main.type == RecipeType.ItemToItem) {
                        if (items.has(main.input)) {
                            items.remove(main.input);
                            for (int i = 0; i < main.output.amount; i++) {
                                offload(main.output.item);
                            }
                        }
                    }
                    if (main.type == RecipeType.ItemAndLiquidToItem) {
                        if (items.has(main.input) && liquids.current() == main.inp.liquid && liquids.currentAmount() >= main.inp.amount) {
                            items.remove(main.input);
                            liquids.remove(main.inp.liquid, main.inp.amount);
                            for (int i = 0; i < main.output.amount; i++) {
                                offload(main.output.item);
                            }
                        }
                    }
                    if (main.type == RecipeType.LiquidToLiquid) {
                        if (liquids.current() == main.inp.liquid && liquids.currentAmount() >= main.inp.amount) {
                            liquids.remove(main.inp.liquid, main.inp.amount);
                            handleLiquid(this, main.oup.liquid, main.oup.amount);
                        }
                    }
                    if (main.type == RecipeType.ItemAndLiquidToLiquid) {
                        if (items.has(main.input) && liquids.current() == main.inp.liquid && liquids.currentAmount() >= main.inp.amount) {
                            items.remove(main.input);
                            liquids.remove(main.inp.liquid, main.inp.amount);
                            handleLiquid(this, main.oup.liquid, main.oup.amount);
                        }
                    }
                    time = 0;
                }
                if (outputItem != null && this.timer(timerDump, 5.0F)) {
                    this.dump(outputItem.item);
                }

                if (outputLiquid != null) {
                    this.dumpLiquid(outputLiquid.liquid);
                }
            }
        }



        @Override
        public void buildConfiguration(Table table) {
            for (int i = 0; i < recipes.size(); i++) {
                if (recipes.get(i).type == RecipeType.ItemToItem) {
                    int ii = i;
                    table.button(new TextureRegionDrawable(recipes.get(i).output.item.icon(Cicon.medium)), () -> {
                        main=recipes.get(ii);
                        outputItem = recipes.get(ii).output;
                    });
                }
                if (recipes.get(i).type == RecipeType.ItemAndLiquidToItem) {
                    int ii = i;
                    table.button(new TextureRegionDrawable(recipes.get(i).oup.liquid.icon(Cicon.medium)), () -> {
                        main=recipes.get(ii);
                        outputItem = recipes.get(ii).output;
                    });
                }
                if (recipes.get(i).type == RecipeType.LiquidToLiquid) {
                    int ii = i;
                    table.button(new TextureRegionDrawable(recipes.get(i).oup.liquid.icon(Cicon.medium)), () -> {
                        main=recipes.get(ii);
                        outputLiquid = recipes.get(ii).oup;
                    });

                }
                if (recipes.get(i).type == RecipeType.ItemAndLiquidToLiquid) {
                    int ii = i;
                    table.button(new TextureRegionDrawable(recipes.get(i).oup.liquid.icon(Cicon.medium)), () -> {
                        main=recipes.get(ii);
                        outputLiquid = recipes.get(ii).oup;
                    });
                }
            }
        }
    }
}