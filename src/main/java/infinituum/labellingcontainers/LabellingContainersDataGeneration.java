package infinituum.labellingcontainers;

import infinituum.labellingcontainers.providers.language.EnglishLangProvider;
import infinituum.labellingcontainers.providers.ModelProvider;
import infinituum.labellingcontainers.providers.RecipeProvider;
import infinituum.labellingcontainers.providers.language.ItalianLangProvider;
import infinituum.labellingcontainers.providers.language.SpanishLangProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;

import java.util.List;
import java.util.function.Consumer;

public class LabellingContainersDataGeneration implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(RecipeProvider::new);
        pack.addProvider(ModelProvider::new);

        registerLanguages(pack);
    }

    private void registerLanguages(FabricDataGenerator.Pack pack) {
        pack.addProvider(EnglishLangProvider::new);
        pack.addProvider(ItalianLangProvider::new);

        // Spanish
        List<String> languageCodes = List.of("es_mx", "es_ve", "es_es", "es_ar", "es_ec", "es_cl", "es_uy");
        Consumer<String> addProvider = (languageCode) -> pack.addProvider((FabricDataOutput dataOutput) -> new SpanishLangProvider(dataOutput, languageCode));
        languageCodes.stream().forEach(addProvider);
    }
}
