package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.impl.customization_exporter.decorator;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.IHeroGridCustomizationToFileExporter;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.impl.customization_exporter.exception.ExportException;
import drakonli.jcomponents.file.backuper.IFileBackuper;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;

public class BackupFileDecorator extends HeroGridCustomizationToFileExporterAbstractDecorator
{
    private final IFileBackuper backuper;

    public BackupFileDecorator(
            IHeroGridCustomizationToFileExporter heroGridCustomizationToFileExporter,
            IFileBackuper backuper
    )
    {
        super(heroGridCustomizationToFileExporter);

        this.backuper = backuper;
    }

    @Override
    public void export(
            File file, HeroGridCustomization heroGridCustomization
    ) throws InvalidFileFormatException, IOException, ExportException
    {
        super.export(file, heroGridCustomization);

        this.backuper.backup(file);
    }
}
