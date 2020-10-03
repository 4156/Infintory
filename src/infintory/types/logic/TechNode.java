package infintory.types.logic;

import arc.graphics.g2d.Lines;
import arc.scene.style.TextureRegionDrawable;
import arc.scene.ui.ImageButton;
import arc.scene.ui.layout.Scl;
import infintory.types.block.ResearchCoreBlock;
import mindustry.ctype.UnlockableContent;
import mindustry.gen.Building;
import mindustry.graphics.Pal;
import mindustry.type.ItemStack;
import mindustry.ui.Cicon;
import mindustry.ui.dialogs.BaseDialog;

public class TechNode extends ImageButton {
    public TechNode child;
    public float panX = 0, panY = -200, lastZoom = -1;
    public int red,blue,green,yellow,rm,bm,gm,ym;
    public float timeUsage=10;
    public Runnable runnable;
    public boolean isUnlocked=false;
    public boolean isDone=false;
    public String des;
    public ResearchCoreBlock.ResearchCoreBuild build;
    public float progress=0;
    public TechNode(UnlockableContent content, Runnable runnable, TechNode child, ResearchCoreBlock.ResearchCoreBuild build){
        super(new TextureRegionDrawable(content.icon(Cicon.medium)));
        TechNode its=this;
        this.runnable=runnable;
        this.child=child;
        setSize(50);
        changed(()->{
            BaseDialog dialog=new BaseDialog(content.name);
            dialog.cont.add(new ImageButton(new TextureRegionDrawable(content.icon(Cicon.medium))){{
                changed(()->{
                    build.TTN=its;
                });


            }});
            dialog.cont.row();
            dialog.cont.add("Time :"+timeUsage);
            dialog.cont.row();
            dialog.cont.add("Use :");
            dialog.cont.row();
            dialog.cont.add(des);
            dialog.cont.row();
            dialog.addCloseButton();
            dialog.show();
        });
        this.build=build;
    }
    public void done(){
        runnable.run();
        isDone=true;
    }


    @Override
    public void draw() {
        super.draw();
        if(child!=null){
            float offsetX = panX + width / 2f, offsetY = panY + height / 2f;
            Lines.line(x+100 + offsetX, y+100 + offsetY, child.x + offsetX, y + offsetY);
            Lines.line(child.x + offsetX, y + offsetY, child.x + offsetX, child.y + offsetY);
        }
        if(isUnlocked==true&&isDone==false){
            done();
            isDone=true;
        }
    }

}
