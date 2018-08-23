package drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.importer;

import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.exception.Dota2InvalidFileFormatException;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.extractor.HeroTranslationByFileLineExtractor;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroNameCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.IHeroGridConfigFromFileImporter;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;
import drakonli.jcomponents.file.reader.buffered.BufferedFileReaderFactoryInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class HeroNamesByDota2TranslationsFileImporter implements IHeroGridConfigFromFileImporter
{
    private final BufferedFileReaderFactoryInterface readerFactory;
    private final HeroTranslationByFileLineExtractor heroTranslationByFileLineExtractor;

    public HeroNamesByDota2TranslationsFileImporter(
            BufferedFileReaderFactoryInterface readerFactory,
            HeroTranslationByFileLineExtractor heroTranslationByFileLineExtractor
    )
    {
        this.readerFactory = readerFactory;
        this.heroTranslationByFileLineExtractor = heroTranslationByFileLineExtractor;
    }

    public HeroGridCustomization importHeroNamesByFile(File file) throws InvalidFileFormatException, IOException
    {
        BufferedReader reader = this.readerFactory.createFileReader(file);

        HeroGridCustomization heroGridCustomization = new HeroGridCustomization();

        String currentLine;
        while (null != (currentLine = reader.readLine())) {
            if (currentLine.isEmpty()) {
                continue;
            }

            HeroNameCustomization heroNameCustomization = this.heroTranslationByFileLineExtractor.extractByLine(currentLine);

            if (null == heroNameCustomization) {
                continue;
            }

            heroGridCustomization.add(heroNameCustomization);
        }

        reader.close();

        if (heroGridCustomization.isEmpty()) {
            throw new Dota2InvalidFileFormatException();
        }

        return heroGridCustomization;
    }
}
