package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.customization_importer;

import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;

public interface IHeroGridCustomizationFromFileImporter
{
    public HeroGridCustomization importCustomization(File file) throws InvalidFileFormatException, IOException;
}