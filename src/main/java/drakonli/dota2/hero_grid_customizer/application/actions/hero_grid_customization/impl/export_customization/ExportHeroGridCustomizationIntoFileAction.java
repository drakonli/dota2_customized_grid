package drakonli.dota2.hero_grid_customizer.application.actions.hero_grid_customization.impl.export_customization;

import drakonli.dota2.hero_grid_customizer.application.actions.ApplicationActionException;
import drakonli.dota2.hero_grid_customizer.application.actions.hero_grid_customization.IExportHeroGridCustomizationIntoFileAction;
import drakonli.dota2.hero_grid_customizer.application.actions.hero_grid_customization.impl.export_customization.event.publisher.IExportHeroGridCustomizationIntoFileActionEventPublisher;
import drakonli.dota2.hero_grid_customizer.application.models.HeroNameCustomizationVM;
import drakonli.dota2.hero_grid_customizer.application.services.IHeroGridCustomizationByViewModelsFactory;
import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.IHeroGridCustomizationToFileExporter;
import drakonli.dota2.hero_grid_customizer.domain.services.hero_grid_customization.impl.customization_exporter.exception.ExportException;
import drakonli.jcomponents.file.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ExportHeroGridCustomizationIntoFileAction implements IExportHeroGridCustomizationIntoFileAction
{
    private final IHeroGridCustomizationToFileExporter                     exporter;
    private final IHeroGridCustomizationByViewModelsFactory                heroGridCustomizationFactory;
    private final IExportHeroGridCustomizationIntoFileActionEventPublisher eventPublisher;

    public ExportHeroGridCustomizationIntoFileAction(
            IHeroGridCustomizationToFileExporter exporter,
            IHeroGridCustomizationByViewModelsFactory heroGridCustomizationFactory,
            IExportHeroGridCustomizationIntoFileActionEventPublisher eventPublisher
    )
    {
        this.exporter = exporter;
        this.heroGridCustomizationFactory = heroGridCustomizationFactory;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void exportCustomization(File file, List<HeroNameCustomizationVM> heroNameCustomizationVMListToExport)
            throws InvalidFileFormatException, IOException, ApplicationActionException
    {
        HeroGridCustomization heroGridCustomization =
                this.heroGridCustomizationFactory.create(heroNameCustomizationVMListToExport);

        this.eventPublisher.publishBeforeExportEvent(
                file,
                heroNameCustomizationVMListToExport,
                heroGridCustomization
        );

        try {
            this.exporter.export(file, heroGridCustomization);
        } catch (ExportException e) {
            throw new ApplicationActionException(e);
        }

        this.eventPublisher.publishAfterExportEvent(
                file,
                heroNameCustomizationVMListToExport,
                heroGridCustomization
        );
    }
}
