package drakonli.dota2.hero_grid_customizer.component.hero.names.file.replacer;

import drakonli.component.file.chooser.InvalidFileFormatException;
import drakonli.dota2.hero_grid_customizer.entity.HeroTranslation;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HeroNamesInFileReplacer
{
    private static final String LINE_TO_BEGIN_EXTRACTING_HERO_NAMES_WITH = "//Hero Names";
    private static final String LINE_TO_END_EXTRACTING_HERO_NAMES_WITH = "// Hero Hype";

    public void replaceHeroNames(File heroNamesFile, List<HeroTranslation> heroTranslations)
            throws InvalidFileFormatException, IOException
    {
        Scanner fileScanner = this.createScannerForFile(heroNamesFile);
        Map<String, String> heroCodeToHeroNameMap = this.createMapOfHeroCodesToNames(heroTranslations);

        StringBuilder stringBuilder = this.skipToHeroNamesAndGetContentUpToStartLine(fileScanner);

        while (fileScanner.hasNextLine()) {
            String currentLine = fileScanner.nextLine() + System.lineSeparator();

            if (currentLine.isEmpty()) {
                stringBuilder.append(currentLine);

                continue;
            }

            currentLine = this.replaceHeroTranslationInLine(currentLine, heroCodeToHeroNameMap);

            stringBuilder.append(currentLine);

            if (this.isContainsHeroTranslationEnd(currentLine)) {
                break;
            }
        }

        this.withdrawRestOfFileContents(stringBuilder, fileScanner);

        fileScanner.close();

        Files.write(
                heroNamesFile.toPath(),
                stringBuilder.toString().getBytes(StandardCharsets.UTF_16LE)
        );
    }

    private Scanner createScannerForFile(File file) throws FileNotFoundException
    {
        return new Scanner(
                new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(file),
                                StandardCharsets.UTF_16LE
                        )
                )
        );
    }

    private String replaceHeroTranslationInLine(String line, Map<String, String> heroCodeToHeroNameMap)
    {
        Pattern p = Pattern.compile("\"(.*)\".*\"(.*)\"");
        Matcher m = p.matcher(line);

        if (m.find()) {
            String hero_code = m.group(1);
            String current_hero_name = m.group(2);

            String newHeroName = heroCodeToHeroNameMap.get(hero_code);

            if (null != newHeroName) {
                return line.replace("\"" + current_hero_name + "\"", "\"" + newHeroName + "\"");
            }
        }

        return line;
    }

    private StringBuilder skipToHeroNamesAndGetContentUpToStartLine(Scanner fileScanner) throws InvalidFileFormatException
    {
        StringBuilder stringBuilder = new StringBuilder();

        String currentLine;
        while (fileScanner.hasNextLine()) {
            currentLine = fileScanner.nextLine() + System.lineSeparator();

            stringBuilder.append(currentLine);

            if (this.isContainsHeroTranslationStart(currentLine)) {
                return stringBuilder;
            }
        }

        throw new InvalidFileFormatException(
                "Chosen file has wrong format. Please, choose dota2 translation file"
        );
    }

    private void withdrawRestOfFileContents(StringBuilder stringBuilder, Scanner fileScanner)
    {
        while (fileScanner.hasNextLine()) {
            String currentLine = fileScanner.nextLine() + System.lineSeparator();

            stringBuilder.append(currentLine);
        }
    }

    private Map<String, String> createMapOfHeroCodesToNames(List<HeroTranslation> heroTranslations)
    {
        Map<String, String> heroCodeToHeroName = new HashMap<>();

        for (HeroTranslation heroTranslation : heroTranslations) {
            heroCodeToHeroName.put(heroTranslation.getHeroCode(), heroTranslation.getHeroName());
        }

        return heroCodeToHeroName;
    }

    private Boolean isContainsHeroTranslationStart(String currentLine)
    {
        return currentLine.contains(LINE_TO_BEGIN_EXTRACTING_HERO_NAMES_WITH);
    }

    private Boolean isContainsHeroTranslationEnd(String currentLine)
    {
        return currentLine.contains(LINE_TO_END_EXTRACTING_HERO_NAMES_WITH);
    }
}
