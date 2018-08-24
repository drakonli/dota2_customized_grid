package drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.customization_exporter.decorator;

import drakonli.dota2.hero_grid_customizer.domain.model.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.customization_exporter.IHeroGridCustomizationToFileExporter;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.customization_exporter.exception.ExportException;
import drakonli.jcomponents.file.backuper.FileBackuper;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;

public class BackupFileDecorator extends HeroGridCustomizationToFileExporterAbstractDecorator
{
    private FileBackuper backuper;

    public BackupFileDecorator(
            IHeroGridCustomizationToFileExporter heroGridCustomizationToFileExporter,
            FileBackuper backuper
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
