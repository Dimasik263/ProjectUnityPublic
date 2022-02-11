package unity.world.meta;

import arc.struct.*;
import mindustry.content.*;
import mindustry.type.*;
import unity.content.*;
import unity.world.graph.*;

public class CrucibleRecipes{
    public static ObjectMap<Item,CrucibleItem> items = new ObjectMap<>();
    public static Seq<CrucibleRecipe> recipes = new Seq<>();
    static {
        items.put(Items.copper,new CrucibleItem(Items.copper, HeatGraphNode.celsiusZero + 900, 0.1f, 20));
        items.put(Items.lead,new CrucibleItem(Items.lead, HeatGraphNode.celsiusZero + 400, 0.15f, 10));
        items.put(UnityItems.nickel,new CrucibleItem(UnityItems.nickel, HeatGraphNode.celsiusZero + 930, 0.15f, 20));
        items.put(UnityItems.cupronickel,new CrucibleItem(UnityItems.cupronickel, HeatGraphNode.celsiusZero + 800, 0.15f, 30));
        items.put(Items.sand,new CrucibleItem(Items.sand, HeatGraphNode.celsiusZero + 1500, 0.15f, 10));
        items.put(Items.metaglass,new CrucibleItem(Items.metaglass, HeatGraphNode.celsiusZero + 900, 0.15f, 20));
        items.put(Items.titanium,new CrucibleItem(Items.sand, HeatGraphNode.celsiusZero + 1600, 0.15f, 40));

        recipes.add(new CrucibleRecipe(UnityItems.cupronickel,0.1f,HeatGraphNode.celsiusZero + 150,
            needs(Items.copper, 2,true),
                   needs(UnityItems.nickel, 1,true)));
        recipes.add(new CrucibleRecipe(Items.metaglass, 0.1f,HeatGraphNode.celsiusZero + 950,
            needs(Items.lead, 0.5f,true),
                   needs(Items.sand, 0.5f,false)));
    }

    public static class CrucibleItem{
        public Item item;
        public float meltingpoint;
        public float meltspeed;
        public float phaseChangeEnergy;

        public CrucibleItem(Item item, float meltingpoint, float meltspeed, float phaseChangeEnergy){
            this.item = item;
            this.meltingpoint = meltingpoint;
            this.meltspeed = meltspeed;
            this.phaseChangeEnergy = phaseChangeEnergy;
        }
    }
    public static class RecipeIngredient{
        public Item item;
        public float amount;
        public boolean melted;

        public RecipeIngredient(Item item, float amount, boolean melted){
            this.item = item;
            this.amount = amount;
            this.melted = melted;
        }
    }
    static RecipeIngredient needs(Item item, float amount, boolean melted){
        return new RecipeIngredient(item,amount,melted);
    }

    public static class CrucibleRecipe{
        public RecipeIngredient items[];
        public Item output;
        public float minTemp = 0;
        public float speed = 0.1f;

        //most would be melted items i think
        CrucibleRecipe(Item output, float speed, float minTemp,RecipeIngredient... items){
            this.output=output;
            this.items = items;
            this.speed=speed;
            this.minTemp=minTemp;
        }
    }
    public static ItemStack[] with(Object... items){
        var stacks = new ItemStack[items.length / 2];
        for(int i = 0; i < items.length; i += 2){
            stacks[i / 2] = new ItemStack((Item)items[i], ((Number)items[i + 1]).intValue());
        }
        return stacks;
    }
}