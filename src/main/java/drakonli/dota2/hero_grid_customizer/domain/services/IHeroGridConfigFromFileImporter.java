package drakonli.dota2.hero_grid_customizer.domain.services;

import drakonli.dota2.hero_grid_customizer.domain.model.HeroNameCustomization;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface IHeroGridConfigFromFileImporter
{
    public void importHeroNamesByFile(File file, List<HeroNameCustomization> heroNameCustomizations)
            throws InvalidFileFormatException, IOException;
}
