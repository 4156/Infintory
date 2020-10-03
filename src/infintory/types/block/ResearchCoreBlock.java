package infintory.types.block;

import arc.graphics.Color;
import arc.scene.ui.ScrollPane;
import arc.scene.ui.layout.Table;
import arc.util.io.Reads;
import arc.util.io.Writes;
import infintory.types.logic.TechNode;
import mindustry.content.Items;
import mindustry.gen.Icon;
import mindustry.ui.Bar;
import mindustry.ui.dialogs.BaseDialog;
import mindustry.world.blocks.storage.StorageBlock;



public class ResearchCoreBlock extends StorageBlock {
    public static TechNode copper,titanium,graphite;
    ScrollPane pane;
    public ResearchCoreBlock(String name) {
        super(name);
        configurable=true;
        update=true;

    }

    @Override
    public void setBars() {
        super.setBars();
        bars.add("progress", (ResearchCoreBuild entity) -> new Bar(() -> "progress", () -> Color.orange, () -> entity.progress));
    }

    public class ResearchCoreBuild extends StorageBuild {
        public int red;
        public int blue;
        public int yellow;
        public int green;
        public float progress=0;
        public int pro;
        public float zh;

        public ResearchCoreBuild its=this;
        public TechNode TTN;

        @Override
        public void update() {
            super.update();
            if(items.has(infintory.contents.Items.blue_pack)){
                items.remove(infintory.contents.Items.blue_pack,1);
                blue++;
            }
            if(items.has(infintory.contents.Items.red_pack)){
                items.remove(infintory.contents.Items.red_pack,1);
                red++;
            }
            if(items.has(infintory.contents.Items.yellow_pack)){
                items.remove(infintory.contents.Items.yellow_pack,1);
                yellow++;
            }
            if(items.has(infintory.contents.Items.green_pack)){
                items.remove(infintory.contents.Items.green_pack,1);
                green++;
            }
            if(pro>60){
                pro=0;
            }

            if(pro==60){
                if(TTN!=null){
                if(TTN.isUnlocked==false) {
                    if (TTN.green > 0 && green > 0 && TTN.gm < TTN.green) {
                        green--;
                        TTN.gm++;
                        pro = 0;


                    }
                    if (TTN.blue > 0 && blue > 0 && TTN.bm < TTN.blue) {
                        blue--;
                        TTN.gm++;
                        pro=0;

                    }
                    if (TTN.yellow > 0 && yellow > 0 && TTN.ym < TTN.yellow) {
                        yellow--;
                        TTN.ym++;
                        pro=0;

                    }
                    if (TTN.red > 0 && red > 0 && TTN.rm < TTN.red) {
                        red--;
                        TTN.rm++;
                        pro=0;

                    }
                 }
                }
            }
            if(TTN!=null){
                progress=((TTN.bm+TTN.gm+TTN.ym+TTN.rm)/(TTN.green+TTN.yellow+TTN.red+TTN.blue))*100/100;
                if((TTN.bm + TTN.gm + TTN.ym + TTN.rm) ==(TTN.green + TTN.yellow + TTN.red + TTN.blue)){
                    TTN.isUnlocked=true;
                    if(!TTN.isDone) {
                        TTN.done();
                    }
                    TTN=null;
                }
            }
            pro++;



        }

        @Override
        public void buildConfiguration(Table table) {
            table.button(Icon.file,()->{
                BaseDialog dialog = new BaseDialog("Tech Tree"){{

                }};

                dialog.addCloseButton();
                if(TTN!=null) {
                    dialog.cont.add("research :" + TTN.des);
                    dialog.cont.row();
                    dialog.cont.add("progress :"+ progress);
                }
                dialog.cont.row();
                dialog.cont.add("blue :"+blue);
                dialog.cont.row();
                dialog.cont.add("yellow :"+yellow);
                dialog.cont.row();
                dialog.cont.add("red :"+red);
                dialog.cont.row();
                dialog.cont.add("green :"+green);
                dialog.cont.row();
                pane=new ScrollPane(new Table(){{
                    graphite=new TechNode(Items.graphite,()->{},null,its){{
                        des="Give you";

                    }};

                    titanium=new TechNode(Items.titanium,()->{},graphite,its){{
                        des="Give you";

                    }};

                    copper=new TechNode(Items.copper,()->{
                        kill();
                    },titanium,its){{
                        des="Research the method of making pure copper";
                        timeUsage=60f;
                        yellow=100;
                        green=50;
                        blue=50;
                    }};
                    add(graphite);
                    add(titanium);
                    add(copper);
                }});
                pane.name="researches";
                dialog.cont.add(pane);



                //dialog.cont.image(Core.atlas.find("example-java-mod-frog")).pad(20f).row();
               //dialog.cont.button("I see", dialog::hide).size(100f, 50f);
                dialog.show();
            });


        }
        @Override
        public void write(Writes write){
            super.write(write);
            write.f(progress);

        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            progress = read.f();

        }
    }

}
