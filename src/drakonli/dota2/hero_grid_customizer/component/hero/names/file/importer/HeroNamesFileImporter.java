package drakonli.dota2.hero_grid_customizer.component.hero.names.file.importer;

import drakonli.component.file.exception.InvalidFileFormatException;
import drakonli.component.file.reader.buffered.BufferedFileReaderFactoryInterface;
import drakonli.component.file.reader.buffered.segment.InvalidSkipToMatcherException;
import drakonli.dota2.hero_grid_customizer.view_model.hero.translation.HeroTranslationViewModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HeroNamesFileImporter
{
    private static final String HERO_TRANSLATION_MATCH_PATTERN = "\"(npc_dota_hero_[^_]*)\".*\"(.*)\"";

    private final BufferedFileReaderFactoryInterface readerFactory;

    public HeroNamesFileImporter(BufferedFileReaderFactoryInterface readerFactory)
    {
        this.readerFactory = readerFactory;
    }

    public void importHeroNamesByFile(File heroNamesFile, List<HeroTranslationViewModel> heroTranslations)
            throws InvalidFileFormatException, IOException
    {
        BufferedReader reader;

        try {
            reader = this.readerFactory.createFileReader(heroNamesFile);
        } catch (InvalidSkipToMatcherException e) {
            throw new InvalidFileFormatException("Chosen file has wrong format. Please, choose dota2 translation file");
        }

        heroTranslations.clear();

        String currentLine;
        while (null != (currentLine = reader.readLine())) {
            if (currentLine.isEmpty()) {
                continue;
            }

            HeroTranslationViewModel heroTranslation = this.createHeroTranslationByCurrentLine(currentLine);

            if (null != heroTranslation) {
                heroTranslations.add(heroTranslation);
            }
        }

        reader.close();
    }

    private HeroTranslationViewModel createHeroTranslationByCurrentLine(String currentLine)
    {
        Pattern p = Pattern.compile(HERO_TRANSLATION_MATCH_PATTERN);
        Matcher m = p.matcher(currentLine);

        if (m.find()) {
            String hero_code = m.group(1);
            String current_hero_name = m.group(2);

            return new HeroTranslationViewModel(current_hero_name, hero_code);
        }

        return null;
    }
}
