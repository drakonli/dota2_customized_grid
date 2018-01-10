package drakonli.dota2.hero_grid_customizer.component.hero.names.file.storage;

import drakonli.dota2.hero_grid_customizer.entity.HeroTranslation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HeroNamesByFileStorage
{
    private static final String HERO_NAMES_SAVE_FILENAME = "hero_names_save.txt";

    public void store(List<HeroTranslation> heroTranslations) throws IOException
    {
        File heroSavesFile = new File(HERO_NAMES_SAVE_FILENAME);
        FileOutputStream fileOut = new FileOutputStream(heroSavesFile);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(heroTranslations);
        out.close();
        fileOut.close();
    }

    public List<HeroTranslation> getLatest() throws IOException, ClassNotFoundException
    {
        File heroNamesSaveFile = new File(HERO_NAMES_SAVE_FILENAME);

        if (!heroNamesSaveFile.isFile()) {
            return new ArrayList<>();
        }

        FileInputStream fileIn = new FileInputStream(HERO_NAMES_SAVE_FILENAME);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        List<HeroTranslation> heroCodeToHeroName =  (List<HeroTranslation>) in.readObject();
        in.close();
        fileIn.close();

        return heroCodeToHeroName;
    }
}
