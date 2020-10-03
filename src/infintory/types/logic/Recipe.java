package infintory.types.logic;

import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;

public class Recipe {
    public RecipeType type;
    public ItemStack[] input;
    public ItemStack output;
    public LiquidStack inp;
    public LiquidStack oup;
    public Recipe(ItemStack output,ItemStack... input){
        this.output=output;
        this.input=input;
        type=RecipeType.ItemToItem;
    }
    public Recipe(LiquidStack oup,LiquidStack inp){
        this.oup=oup;
        this.inp=inp;
        type=RecipeType.LiquidToLiquid;
    }
    public Recipe(RecipeType type){
        this.type=type;
    }

    public void setOutput(ItemStack output) {
        this.output = output;
    }

    public void setInput(ItemStack[] input) {
        this.input = input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public ItemStack[] getInput() {
        return input;
    }

    public void setType(RecipeType type) {
        this.type = type;
    }

    public LiquidStack getOup() {
        return oup;
    }

    public LiquidStack getInp() {
        return inp;
    }

    public RecipeType getType() {
        return type;
    }

    public void setInp(LiquidStack inp) {
        this.inp = inp;
    }

    public void setOup(LiquidStack oup) {
        this.oup = oup;
    }
}
