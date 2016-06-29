package de.mineforce.enderwolf;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import de.mineforce.enderwolf.entity.EntityEnderWolf;
import de.mineforce.enderwolf.entity.ModelEnderWolf;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.renderer.entity.RenderEnderWolf;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.world.biome.BiomeGenBase;

@Mod(modid = Main.MODID, version = Main.VERSION)
public class Main
{
    public static final String MODID = "enderwolf";
    public static final String VERSION = "1.0";
    public static int enderwolfid = 0;
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        EntityRegistry.registerGlobalEntityID(EntityEnderWolf.class, "EnderWolf", getUniqueEntityId(), 80, 1);
        RenderingRegistry.registerEntityRenderingHandler(EntityEnderWolf.class, new RenderEnderWolf(new ModelEnderWolf(),new ModelEnderWolf(), 0.5F));

        BiomeGenBase[] allBiomes = Iterators.toArray(Iterators.filter(Iterators.forArray(BiomeGenBase.getBiomeGenArray()), Predicates.notNull()), BiomeGenBase.class);
        EntityRegistry.addSpawn(EntityEnderWolf.class,5,5,10, EnumCreatureType.creature,allBiomes);

        LanguageRegistry.instance().addStringLocalization("entity.EnderWolf.name","Enderwolf");
    }

    public static int getUniqueEntityId()
    {
        do
        {
            enderwolfid += 1;
        } while (EntityList.getStringFromID(enderwolfid) != null);
        return enderwolfid++;
    }
}
