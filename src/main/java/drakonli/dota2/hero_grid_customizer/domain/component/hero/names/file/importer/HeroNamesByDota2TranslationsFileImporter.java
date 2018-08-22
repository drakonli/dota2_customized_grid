package drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.importer;

import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.exception.Dota2InvalidFileFormatException;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.extractor.HeroTranslationByFileLineExtractor;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroNameCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.IHeroGridConfigFromFileImporter;
import drakonli.jcomponents.file.reader.buffered.BufferedFileReaderFactoryInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;

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

    public void importHeroNamesByFile(File heroNamesFile, List<HeroNameCustomization> heroNameCustomizations)
            throws Dota2InvalidFileFormatException, IOException
    {
        BufferedReader reader = this.readerFactory.createFileReader(heroNamesFile);

        heroNameCustomizations.clear();

        String currentLine;
        while (null != (currentLine = reader.readLine())) {
            if (currentLine.isEmpty()) {
                continue;
            }

            HeroNameCustomization heroNameCustomization = this.heroTranslationByFileLineExtractor.extractByLine(currentLine);

            if (null == heroNameCustomization) {
                continue;
            }

            heroNameCustomizations.add(heroNameCustomization);
        }

        reader.close();

        if (!heroNameCustomizations.isEmpty()) {
            return;
        }

        throw new Dota2InvalidFileFormatException();
    }
}
