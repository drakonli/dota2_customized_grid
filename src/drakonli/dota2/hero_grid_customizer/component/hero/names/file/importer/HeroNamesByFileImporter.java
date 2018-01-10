package drakonli.dota2.hero_grid_customizer.component.hero.names.file.importer;

import drakonli.component.file.reader.buffered.BufferedFileReaderFactoryInterface;
import drakonli.dota2.hero_grid_customizer.component.hero.names.file.exception.Dota2InvalidFileFormatException;
import drakonli.dota2.hero_grid_customizer.component.hero.names.file.extractor.HeroTranslationViewModelByFileLineExtractor;
import drakonli.dota2.hero_grid_customizer.view_model.hero.translation.HeroTranslationViewModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class HeroNamesByFileImporter
{
    private final BufferedFileReaderFactoryInterface readerFactory;
    private final HeroTranslationViewModelByFileLineExtractor heroTranslationViewModelExtractor;

    public HeroNamesByFileImporter(
            BufferedFileReaderFactoryInterface readerFactory,
            HeroTranslationViewModelByFileLineExtractor heroTranslationViewModelExtractor
    )
    {
        this.readerFactory = readerFactory;
        this.heroTranslationViewModelExtractor = heroTranslationViewModelExtractor;
    }

    public void importHeroNamesByFile(File heroNamesFile, List<HeroTranslationViewModel> heroTranslations)
            throws Dota2InvalidFileFormatException, IOException
    {
        BufferedReader reader = this.readerFactory.createFileReader(heroNamesFile);

        heroTranslations.clear();

        String currentLine;
        while (null != (currentLine = reader.readLine())) {
            if (currentLine.isEmpty()) {
                continue;
            }

            HeroTranslationViewModel heroTranslationViewModel =
                    this.heroTranslationViewModelExtractor.extractByLine(currentLine);

            if (null == heroTranslationViewModel) {
                continue;
            }

            heroTranslations.add(heroTranslationViewModel);
        }

        reader.close();

        if (!heroTranslations.isEmpty()) {
            return;
        }

        throw new Dota2InvalidFileFormatException();
    }
}
