package drakonli.dota2.hero_grid_customizer.component.hero.names.file.importer;

import drakonli.component.file.chooser.InvalidFileFormatException;
import drakonli.component.file.scanner.factory.ScannerFactoryInterface;
import drakonli.dota2.hero_grid_customizer.view_model.hero.translation.HeroTranslationViewModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HeroNamesFileImporter
{
    private static final String LINE_TO_BEGIN_EXTRACTING_HERO_NAMES_WITH = "//Hero Names";
    private static final String LINE_TO_END_EXTRACTING_HERO_NAMES_WITH = "// Hero Hype";

    private ScannerFactoryInterface scannerFactory;

    public HeroNamesFileImporter(ScannerFactoryInterface scannerFactory)
    {
        this.scannerFactory = scannerFactory;
    }

    public void importHeroNamesByFile(File heroNamesFile, List<HeroTranslationViewModel> heroTranslations)
            throws FileNotFoundException, InvalidFileFormatException
    {
        Scanner fileScanner = this.scannerFactory.createScanner(heroNamesFile);

        if (!this.skipToHeroNamesStartLine(fileScanner)) {
            throw new InvalidFileFormatException(
                    "Chosen file has wrong format. Please, choose dota2 translation file"
            );
        }

        heroTranslations.clear();

        String currentLine;
        while (fileScanner.hasNextLine()) {
            currentLine = fileScanner.nextLine();

            if (currentLine.isEmpty()) {
                continue;
            }

            HeroTranslationViewModel heroTranslation = this.createHeroTranslationByCurrentLine(currentLine);

            if (null != heroTranslation) {
                heroTranslations.add(heroTranslation);
            }

            if (this.hasHeroTranslationEndString(currentLine)) {
                break;
            }
        }

        fileScanner.close();
    }

    private Boolean skipToHeroNamesStartLine(Scanner fileScanner)
    {
        while (fileScanner.hasNextLine()) {
            String currentLine = fileScanner.nextLine();

            if (this.hasHeroTranslationStartString(currentLine)) {
                return true;
            }
        }

        return false;
    }

    private HeroTranslationViewModel createHeroTranslationByCurrentLine(String currentLine)
    {
        Pattern p = Pattern.compile("\"(.*)\".*\"(.*)\"");
        Matcher m = p.matcher(currentLine);

        if (m.find()) {
            String hero_code = m.group(1);
            String current_hero_name = m.group(2);

            return new HeroTranslationViewModel(current_hero_name, hero_code);
        }

        return null;
    }

    private Boolean hasHeroTranslationStartString(String currentLine)
    {
        return currentLine.contains(LINE_TO_BEGIN_EXTRACTING_HERO_NAMES_WITH);
    }

    private Boolean hasHeroTranslationEndString(String currentLine)
    {
        return currentLine.contains(LINE_TO_END_EXTRACTING_HERO_NAMES_WITH);
    }
}
