package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.customization_exporter;

import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.customization_exporter.exception.ExportException;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;

public interface IHeroGridCustomizationToFileExporter
{
    public void export(File file, HeroGridCustomization heroGridCustomization)
            throws InvalidFileFormatException, IOException, ExportException;
}
