package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.impl.customization_exporter.decorator;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.IHeroGridCustomizationToFileExporter;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.impl.customization_exporter.exception.ExportException;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;

abstract class HeroGridCustomizationToFileExporterAbstractDecorator implements IHeroGridCustomizationToFileExporter
{
    private final IHeroGridCustomizationToFileExporter heroGridCustomizationToFileExporter;

    public HeroGridCustomizationToFileExporterAbstractDecorator(
            IHeroGridCustomizationToFileExporter heroGridCustomizationToFileExporter
    )
    {
        this.heroGridCustomizationToFileExporter = heroGridCustomizationToFileExporter;
    }

    @Override
    public void export(File file, HeroGridCustomization heroGridCustomization)
            throws InvalidFileFormatException, IOException, ExportException
    {
        this.heroGridCustomizationToFileExporter.export(file, heroGridCustomization);
    }
}
