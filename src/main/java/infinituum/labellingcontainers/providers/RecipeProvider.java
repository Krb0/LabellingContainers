package infinituum.labellingcontainers.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

import static infinituum.labellingcontainers.registration.ItemRegistration.LABEL_PRINTER;

public class RecipeProvider extends FabricRecipeProvider {

    public RecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, LABEL_PRINTER)
                .pattern("CIC")
                .pattern("RPR")
                .pattern("CIC")
                .input('R', Items.REDSTONE)
                .input('C', Items.COPPER_INGOT)
                .input('P', Items.PAPER)
                .input('I', Items.IRON_INGOT)
                .criterion("has_items", InventoryChangedCriterion.Conditions.items(Items.PAPER, Items.COPPER_INGOT, Items.IRON_INGOT, Items.REDSTONE))
                .offerTo(exporter, new Identifier(output.getModId(), "label_printer_recipe"));
    }
}
