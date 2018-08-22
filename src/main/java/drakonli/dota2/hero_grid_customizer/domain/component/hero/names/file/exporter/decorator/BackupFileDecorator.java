package drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.exporter.decorator;

import drakonli.dota2.hero_grid_customizer.domain.model.HeroNamesGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.IHeroGridConfigToFileExporter;
import drakonli.dota2.hero_grid_customizer.domain.services.export.ExportException;
import drakonli.jcomponents.file.backuper.FileBackuper;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;

public class BackupFileDecorator extends HeroGridConfigToFileExporterAbstractDecorator
{
    private FileBackuper backuper;

    public BackupFileDecorator(
            IHeroGridConfigToFileExporter heroGridConfigToFileExporter,
            FileBackuper backuper
    )
    {
        super(heroGridConfigToFileExporter);

        this.backuper = backuper;
    }

    @Override
    public void export(
            File file, HeroNamesGridCustomization heroNamesGridCustomization
    ) throws InvalidFileFormatException, IOException, ExportException
    {
        super.export(file, heroNamesGridCustomization);

        this.backuper.backup(file);
    }
}
