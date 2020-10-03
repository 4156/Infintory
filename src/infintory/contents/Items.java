package infintory.contents;

import mindustry.ctype.ContentList;
import mindustry.graphics.MultiPacker;
import mindustry.type.Item;

public class Items implements ContentList {
    public static Item
    //Tech
    red_pack,green_pack,yellow_pack,blue_pack;
    ;
    @Override
    public void load() {
        red_pack=new Item("red-research-pack"){{
            description="Produce red scientific research points for researching powerful weapons";
        }};
        yellow_pack=new Item("yellow-research-pack"){{
            description="Produce yellow research points, used to increase industrial production capacity and new materials";
        }};
        green_pack=new Item("green-research-pack"){{
            description="Produce green technology points for research on biotechnology and planning";
        }};
        blue_pack=new Item("blue-research-pack"){{
            description="Produce blue technology points for research on energy production and physics applications";
        }};
    }
}
