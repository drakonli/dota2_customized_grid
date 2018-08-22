package drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.storage;

import drakonli.dota2.hero_grid_customizer.domain.model.HeroNameCustomization;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HeroNamesByFileStorage
{
    private static final String HERO_NAMES_SAVE_FILENAME = "hero_names_save.txt";

    public void store(List<HeroNameCustomization> heroNameCustomizations) throws StorageException
    {
        try {
            File heroSavesFile = new File(HERO_NAMES_SAVE_FILENAME);
            FileOutputStream fileOut = new FileOutputStream(heroSavesFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(heroNameCustomizations);
            out.close();
            fileOut.close();
        } catch (Exception e)
        {
            throw new StorageException(e);
        }
    }

    public List<HeroNameCustomization> getLatest() throws StorageException
    {
        File heroNamesSaveFile = new File(HERO_NAMES_SAVE_FILENAME);

        if (!heroNamesSaveFile.isFile()) {
            return new ArrayList<>();
        }

        try {
            FileInputStream fileIn = new FileInputStream(HERO_NAMES_SAVE_FILENAME);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List<HeroNameCustomization> heroCodeToHeroName =  (List<HeroNameCustomization>) in.readObject();
            in.close();
            fileIn.close();

            return heroCodeToHeroName;
        } catch (Exception e)
        {
            throw new StorageException(e);
        }
    }
}
