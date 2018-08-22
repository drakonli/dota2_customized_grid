package drakonli.dota2.hero_grid_customizer.domain.services;

import drakonli.dota2.hero_grid_customizer.domain.model.HeroNamesGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.export.ExportException;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;

public interface IHeroGridConfigToFileExporter
{
    public void export(File file, HeroNamesGridCustomization heroNamesGridCustomization)
            throws InvalidFileFormatException, IOException, ExportException;
}
