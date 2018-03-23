package drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.importer;

import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.exception.Dota2InvalidFileFormatException;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.extractor.HeroTranslationByFileLineExtractor;
import drakonli.dota2.hero_grid_customizer.entity.HeroTranslation;
import drakonli.jcomponents.file.reader.buffered.BufferedFileReaderFactoryInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class HeroNamesByFileImporter
{
    private final BufferedFileReaderFactoryInterface readerFactory;
    private final HeroTranslationByFileLineExtractor heroTranslationByFileLineExtractor;

    public HeroNamesByFileImporter(
            BufferedFileReaderFactoryInterface readerFactory,
            HeroTranslationByFileLineExtractor heroTranslationByFileLineExtractor
    )
    {
        this.readerFactory = readerFactory;
        this.heroTranslationByFileLineExtractor = heroTranslationByFileLineExtractor;
    }

    public void importHeroNamesByFile(File heroNamesFile, List<HeroTranslation> heroTranslations)
            throws Dota2InvalidFileFormatException, IOException
    {
        BufferedReader reader = this.readerFactory.createFileReader(heroNamesFile);

        heroTranslations.clear();

        String currentLine;
        while (null != (currentLine = reader.readLine())) {
            if (currentLine.isEmpty()) {
                continue;
            }

            HeroTranslation heroTranslation = this.heroTranslationByFileLineExtractor.extractByLine(currentLine);

            if (null == heroTranslation) {
                continue;
            }

            heroTranslations.add(heroTranslation);
        }

        reader.close();

        if (!heroTranslations.isEmpty()) {
            return;
        }

        throw new Dota2InvalidFileFormatException();
    }
}
