package unity.world.blocks.power;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import mindustry.*;
import mindustry.graphics.*;
import mindustry.ui.*;
import unity.world.blocks.*;
import unity.world.graph.*;

public class TorqueMeter extends GenericGraphBlock{

    public TorqueMeter(String name){
        super(name);
    }
    public static class TorqueMeterGraphNode extends TorqueGraphNode{
        public TorqueMeterGraphNode(float friction, float inertia, GraphBuild build){
            super(friction, inertia, build);
        }

        @Override
        public void displayBars(Table table){
            super.displayBars(table);
            var n1 = connector.first();
            table.row();
            table.add(new Bar(() -> Core.bundle.format("bar.unity-torqueinertia", Strings.fixed(n1.getGraph().lastInertia, 1)), () -> Pal.gray, () -> 1));
            table.row();
            table.add(new Bar(() -> Core.bundle.format("bar.unity-torqueforce", Strings.fixed(n1.getGraph().lastGrossForceApplied,1)), () -> Pal.boostTo, () -> 1));
            table.row();
            table.add(new Bar(() -> Core.bundle.format("bar.unity-torqueuse", Strings.fixed(n1.getGraph().lastGrossForceApplied-n1.getGraph().lastNetForceApplied,1)), () -> Pal.health, () -> 1));
        }
    }
    Color col = new Color();
    public class TorqueMeterBuild extends GenericGraphBuild{
        @Override
        public void draw(){
            var graph = getGraph(TorqueGraph.class);
            float t = Mathf.clamp(Mathf.map(graph.lastVelocity,0,90,0,1) * Mathf.random(0.95f,1.05f));
            Draw.rect(this.block.region, this.x, this.y, this.getCorrectRotation());
            col.set(1f,0.5f,0.5f);
            col.lerp(Pal.heal,t);
            Lines.stroke(1f, col);
            Lines.lineAngle(x,y,Mathf.map(t ,0,1,225,-45), Vars.tilesize*0.25f);
            this.drawTeamTop();
        }
    }

}
