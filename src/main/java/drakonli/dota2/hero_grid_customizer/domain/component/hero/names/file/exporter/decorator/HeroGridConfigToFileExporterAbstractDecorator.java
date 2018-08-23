package drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.exporter.decorator;

import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.IHeroGridConfigToFileExporter;
import drakonli.dota2.hero_grid_customizer.domain.services.export.ExportException;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;

abstract class HeroGridConfigToFileExporterAbstractDecorator implements IHeroGridConfigToFileExporter
{
    private IHeroGridConfigToFileExporter heroGridConfigToFileExporter;

    public HeroGridConfigToFileExporterAbstractDecorator(
            IHeroGridConfigToFileExporter heroGridConfigToFileExporter
    )
    {
        this.heroGridConfigToFileExporter = heroGridConfigToFileExporter;
    }

    @Override
    public void export(File file, HeroGridCustomization heroGridCustomization)
            throws InvalidFileFormatException, IOException, ExportException
    {
        this.heroGridConfigToFileExporter.export(file, heroGridCustomization);
    }
}
