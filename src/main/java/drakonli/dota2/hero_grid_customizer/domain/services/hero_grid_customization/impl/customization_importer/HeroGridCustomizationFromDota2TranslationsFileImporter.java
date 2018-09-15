package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.impl.customization_importer;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.models.HeroNameCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file.IHeroNameCustomizationByLineExtractor;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.IHeroGridCustomizationFromFileImporter;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.impl.exceptions.Dota2InvalidFileFormatException;
import drakonli.jcomponents.file.IBufferedFileReaderFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class HeroGridCustomizationFromDota2TranslationsFileImporter implements IHeroGridCustomizationFromFileImporter
{
    private final IBufferedFileReaderFactory            readerFactory;
    private final IHeroNameCustomizationByLineExtractor heroNameCustomizationByLineExtractor;

    public HeroGridCustomizationFromDota2TranslationsFileImporter(
            IBufferedFileReaderFactory readerFactory,
            IHeroNameCustomizationByLineExtractor heroNameCustomizationByLineExtractor
    )
    {
        this.readerFactory = readerFactory;
        this.heroNameCustomizationByLineExtractor = heroNameCustomizationByLineExtractor;
    }

    public HeroGridCustomization importCustomization(File file) throws Dota2InvalidFileFormatException, IOException
    {
        BufferedReader reader = this.readerFactory.createFileReader(file);

        HeroGridCustomization heroGridCustomization = new HeroGridCustomization(file.getName());

        String currentLine;
        while (null != (currentLine = reader.readLine())) {
            if (currentLine.isEmpty()) {
                continue;
            }

            Optional<HeroNameCustomization> optionalHeroNameCustomization
                    = this.heroNameCustomizationByLineExtractor.extractByLine(currentLine);

            if (!optionalHeroNameCustomization.isPresent()) {
                continue;
            }

            heroGridCustomization.add(optionalHeroNameCustomization.get());
        }

        reader.close();

        if (heroGridCustomization.isEmpty()) {
            throw new Dota2InvalidFileFormatException();
        }

        return heroGridCustomization;
    }
}
