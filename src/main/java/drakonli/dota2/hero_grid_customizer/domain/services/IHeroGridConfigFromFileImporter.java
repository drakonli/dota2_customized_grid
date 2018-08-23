package drakonli.dota2.hero_grid_customizer.domain.services;

import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;

public interface IHeroGridConfigFromFileImporter
{
    public HeroGridCustomization importHeroNamesByFile(File file) throws InvalidFileFormatException, IOException;
}
