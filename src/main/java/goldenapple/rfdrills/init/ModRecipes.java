package goldenapple.rfdrills.init;

import cofh.api.modhelpers.ThermalExpansionHelper;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import goldenapple.rfdrills.config.ConfigHandler;
import goldenapple.rfdrills.crafting.ShapedUpgradeRecipe;
import goldenapple.rfdrills.reference.LibMetadata;
import goldenapple.rfdrills.reference.Reference;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ModRecipes {

    public static void init(){
        RecipeSorter.register(Reference.MOD_ID + ":upgrading", ShapedUpgradeRecipe.class, RecipeSorter.Category.SHAPED, "after:minecraft:shaped before:minecraft:shapeless");
        if(Loader.isModLoaded("ThermalExpansion") && ConfigHandler.integrateTE) initTE();
        if(Loader.isModLoaded("EnderIO") && ConfigHandler.integrateEIO) initEIO();
        if(Loader.isModLoaded("RedstoneArsenal") && ConfigHandler.integrateRArs) initRArs();
    }

    private static void initTE(){
        ItemStack motorLeadstone = new ItemStack(ModItems.motorTE, 1, LibMetadata.MOTOR_LEASDSTONE);
        ItemStack motorHardened = new ItemStack(ModItems.motorTE, 1, LibMetadata.MOTOR_HARDENED);
        ItemStack motorRedstoneFrame = new ItemStack(ModItems.motorTE, 1, LibMetadata.MOTOR_REDSTONE_FRAME);
        ItemStack motorRedstone = new ItemStack(ModItems.motorTE, 1, LibMetadata.MOTOR_REDSTONE);
        ItemStack motorResonantFrame = new ItemStack(ModItems.motorTE, 1, LibMetadata.MOTOR_RESONANT_FRAME);
        ItemStack motorResonant = new ItemStack(ModItems.motorTE, 1, LibMetadata.MOTOR_RESONANT);

        ItemStack pneumaticServo = new ItemStack(GameRegistry.findItem("ThermalExpansion", "material"), 1, 0);
        ItemStack receptionCoil = new ItemStack(GameRegistry.findItem("ThermalExpansion", "material"), 1, 1);
        ItemStack cryocoilAugment = new ItemStack(GameRegistry.findItem("ThermalExpansion", "augment"), 1, 82);
        ItemStack capacitorLeadstone = new ItemStack(GameRegistry.findItem("ThermalExpansion", "capacitor"), 1, 2);
        ItemStack capacitorHardened = new ItemStack(GameRegistry.findItem("ThermalExpansion", "capacitor"), 1, 3);
        ItemStack capacitorRedstone = new ItemStack(GameRegistry.findItem("ThermalExpansion", "capacitor"), 1, 4);
        ItemStack capacitorResonant = new ItemStack(GameRegistry.findItem("ThermalExpansion", "capacitor"), 1, 5);

        //Leadstone motor
        GameRegistry.addRecipe(new ShapedOreRecipe(motorLeadstone.copy(),
                "iDi",
                "GPG",
                "iCi", 'i', "ingotLead", 'D', "dustRedstone", 'G', "gearTin", 'P', pneumaticServo.copy(), 'C', receptionCoil.copy()));

        //Hardened motor
        GameRegistry.addRecipe(new ShapedOreRecipe(motorHardened.copy(),
                "iDi",
                "GPG",
                "iCi", 'i', "ingotInvar", 'D', "dustGlowstone", 'G', "gearElectrum", 'P', pneumaticServo.copy(), 'C', receptionCoil.copy()));

        //Redstone motor frame
        GameRegistry.addRecipe(new ShapedOreRecipe(motorRedstoneFrame.copy(),
                "iHi",
                "GPG",
                "iCi", 'i', "ingotElectrum", 'H', "blockGlassHardened", 'G', "gearSignalum", 'P', pneumaticServo.copy(), 'C', receptionCoil.copy()));

        //Redstone motor
        ThermalExpansionHelper.addTransposerFill(16000, motorRedstoneFrame.copy(), motorRedstone.copy(), FluidRegistry.getFluidStack("redstone", 4000), false);

        //Resonant motor frame
        GameRegistry.addRecipe(new ShapedOreRecipe(motorResonantFrame.copy(),
                "iAi",
                "GPG",
                "iCi", 'i', "ingotSilver", 'A', cryocoilAugment.copy(), 'G', "gearEnderium", 'P', pneumaticServo.copy(), 'C', receptionCoil.copy()));

        //Resonant motor
        ThermalExpansionHelper.addTransposerFill(16000, motorResonantFrame.copy(), motorResonant.copy(), FluidRegistry.getFluidStack("cryotheum", 4000), false);


        //Leadstone drill
        GameRegistry.addRecipe(new ShapedUpgradeRecipe(ModItems.leadstoneDrill,
                " i ",
                "iMi",
                "ICI", 'I', "ingotLead", 'i', "ingotBronze", 'C', capacitorLeadstone.copy(), 'M', motorLeadstone.copy()));

        //Leadstone chainsaw
        GameRegistry.addRecipe(new ShapedUpgradeRecipe(ModItems.leadstoneChainsaw,
                " ii",
                "IMi",
                "CI ", 'I', "ingotLead", 'i', "ingotBronze", 'C', capacitorLeadstone.copy(), 'M', motorLeadstone.copy()));

        //Hardened drill
        GameRegistry.addRecipe(new ShapedUpgradeRecipe(ModItems.hardenedDrill,
                " D ",
                "iMi",
                "ICI", 'I', "ingotInvar", 'i', "ingotIron", 'C', capacitorHardened.copy(), 'M', motorHardened.copy(), 'D', new ItemStack(ModItems.leadstoneDrill)));

        //Hardened chainsaw
        GameRegistry.addRecipe(new ShapedUpgradeRecipe(ModItems.hardenedChainsaw,
                " iS",
                "IMi",
                "CI ", 'I', "ingotInvar", 'i', "ingotIron", 'C', capacitorHardened.copy(), 'M', motorHardened.copy(), 'S', new ItemStack(ModItems.leadstoneChainsaw)));

        //Redstone drill
        GameRegistry.addRecipe(new ShapedUpgradeRecipe(ModItems.redstoneDrill,
                " D ",
                "iMi",
                "ICI", 'I', "ingotElectrum", 'i', "ingotInvar", 'C', capacitorRedstone.copy(), 'M', motorRedstone.copy(), 'D', new ItemStack(ModItems.hardenedDrill)));

        //Redstone chainsaw
        GameRegistry.addRecipe(new ShapedUpgradeRecipe(ModItems.redstoneChainsaw,
                " iS",
                "IMi",
                "CI ", 'I', "ingotElectrum", 'i', "ingotInvar", 'C', capacitorRedstone.copy(), 'M', motorRedstone.copy(), 'S', new ItemStack(ModItems.hardenedChainsaw)));

        //Resonant drill
        GameRegistry.addRecipe(new ShapedUpgradeRecipe(ModItems.resonantDrill,
                " D ",
                "iMi",
                "ICI", 'I', "ingotEnderium", 'i', "ingotSilver", 'C', capacitorResonant.copy(), 'M', motorResonant.copy(), 'D', new ItemStack(ModItems.redstoneDrill)));

        //Resonant chainsaw
        GameRegistry.addRecipe(new ShapedUpgradeRecipe(ModItems.resonantChainsaw,
                " iS",
                "IMi",
                "CI ", 'I', "ingotEnderium", 'i', "ingotSilver", 'C', capacitorResonant.copy(), 'M', motorResonant.copy(), 'S', new ItemStack(ModItems.redstoneChainsaw)));
    }

    private static void initEIO(){
        ItemStack motorBasic = new ItemStack(ModItems.motorEIO, 1, LibMetadata.MOTOR_BASIC);
        ItemStack motorAdvanced = new ItemStack(ModItems.motorEIO, 1, LibMetadata.MOTOR_ADVANCED);
        ItemStack machineChassis = new ItemStack(GameRegistry.findItem("EnderIO", "itemMachinePart"), 1, 0);
        ItemStack pulsatingCrystal = new ItemStack(GameRegistry.findItem("EnderIO", "itemMaterial"), 1, 5);
        ItemStack capacitorBasic = new ItemStack(GameRegistry.findItem("EnderIO", "itemBasicCapacitor"), 1, 0);
        ItemStack capacitorAdvanced = new ItemStack(GameRegistry.findItem("EnderIO", "itemBasicCapacitor"), 1, 1);

        //Basic motor
        GameRegistry.addRecipe(new ShapedOreRecipe(motorBasic.copy(),
                "iCi",
                "GFG",
                "iRi", 'i', "itemSilicon", 'F', machineChassis.copy(), 'G', "gearStone", 'R', "dustRedstone", 'C', capacitorBasic.copy()));

        //Advanced motor
        GameRegistry.addRecipe(new ShapedOreRecipe(motorAdvanced.copy(),
                "iCi",
                "PFP",
                "iRi", 'i', "ingotEnergeticAlloy", 'F', machineChassis.copy(), 'P', pulsatingCrystal.copy(), 'R', "ingotRedstoneAlloy", 'C', capacitorAdvanced.copy()));

        //Basic drill
        GameRegistry.addRecipe(new ShapedUpgradeRecipe(ModItems.basicDrill,
                " i ",
                "iMi",
                "ICI", 'I', "itemSilicon", 'i', "ingotConductiveIron", 'C', capacitorBasic.copy(), 'M', motorBasic.copy()));

        //Basic chainsaw
        GameRegistry.addRecipe(new ShapedUpgradeRecipe(ModItems.basicChainsaw,
                " ii",
                "IMi",
                "CI ", 'I', "itemSilicon", 'i', "ingotConductiveIron", 'C', capacitorBasic.copy(), 'M', motorBasic.copy()));

        //Advanced drill
        GameRegistry.addRecipe(new ShapedUpgradeRecipe(ModItems.advancedDrill,
                " D ",
                "iMi",
                "ICI", 'I', "ingotEnergeticAlloy", 'i', "ingotElectricalSteel", 'C', capacitorAdvanced.copy(), 'M', motorAdvanced.copy(), 'D', new ItemStack(ModItems.basicDrill)));

        //Advanced chainsaw
        GameRegistry.addRecipe(new ShapedUpgradeRecipe(ModItems.advancedChainsaw,
                " iS",
                "IMi",
                "CI ", 'I', "ingotEnergeticAlloy", 'i', "ingotElectricalSteel", 'C', capacitorAdvanced.copy(), 'M', motorAdvanced.copy(), 'S', new ItemStack(ModItems.basicChainsaw)));
    }

    public static void initRArs(){
        ItemStack fluxRod = new ItemStack(GameRegistry.findItem("RedstoneArsenal", "material"), 1, 193);
        ItemStack fluxPickaxe = new ItemStack(GameRegistry.findItem("RedstoneArsenal", "tool.pickaxeFlux"), 1, 0);
        ItemStack fluxShovel = new ItemStack(GameRegistry.findItem("RedstoneArsenal", "tool.shovelFlux"), 1, 0);
        ItemStack fluxAxe = new ItemStack(GameRegistry.findItem("RedstoneArsenal", "tool.axeFlux"), 1, 0);

        GameRegistry.addRecipe(new ShapedUpgradeRecipe(ModItems.fluxBreaker,
                "PSA",
                "DBC",
                " R ", 'B', "blockElectrumFlux", 'R', fluxRod, 'P', fluxPickaxe, 'S', fluxShovel, 'A', fluxAxe, 'D', new ItemStack(ModItems.resonantDrill), 'C', new ItemStack(ModItems.resonantChainsaw)));
    }
}
