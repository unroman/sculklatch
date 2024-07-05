package chaos.sculklatch.recipes.custom;

import chaos.sculklatch.items.ModItems;
import chaos.sculklatch.recipes.ModRecipes;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.*;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class SculkBudelRecipe extends SpecialCraftingRecipe {
    public SculkBudelRecipe(CraftingRecipeCategory category) {
        super(category);
    }


    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        int sculkLatchCount = 0;
        int bundles = 0;
        for(int k = 0; k < input.getSize(); ++k) {
            ItemStack itemStack = input.getStackInSlot(k);
            if (!itemStack.isEmpty()) {
                if (itemStack.getItem() == ModItems.SCULK_LATCH) {
                    sculkLatchCount ++;
                }
                if (itemStack.getItem() == Items.BUNDLE) {
                    bundles ++;
                }
            }
        }
        return sculkLatchCount == 1 && bundles == 1;
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        ItemStack itemStack = ItemStack.EMPTY;

        for(int i = 0; i < input.getSize(); ++i) {
            ItemStack itemStack2 = input.getStackInSlot(i);
            if (!itemStack2.isEmpty()) {
                Item item = itemStack2.getItem();
                if (item instanceof BundleItem) {
                    itemStack = itemStack2;
                }
            }
        }

        return itemStack.copyComponentsToNewStack(ModItems.SCULK_BUNDLE, 1);
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.SCULK_LATCH_BUNDEL;
    }
}
