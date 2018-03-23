package drakonli.dota2.hero_grid_customizer.application.view_handler.save;

import drakonli.dota2.hero_grid_customizer.application.view_handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.application.view_model.grid.HeroGridViewModel;
import drakonli.dota2.hero_grid_customizer.application.view_model.translation.HeroTranslationViewModelsToDomainModelMapper;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.exception.Dota2InvalidFileFormatException;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.exporter.HeroNamesIntoFileExporter;
import drakonli.dota2.hero_grid_customizer.domain.model.HeroTranslation;

import java.io.IOException;
import java.util.List;

public class ReplaceHeroNamesInTranslationsFileHandler implements SaveButtonHandlerInterface
{
    private final HeroGridViewModel heroGridViewModel;
    private final HeroNamesIntoFileExporter heroNamesIntoFileExporter;
    private final HeroTranslationViewModelsToDomainModelMapper heroTranslationViewModelsToDomainModelMapper;

    public ReplaceHeroNamesInTranslationsFileHandler(
            HeroGridViewModel heroGridViewModel,
            HeroNamesIntoFileExporter heroNamesIntoFileExporter,
            HeroTranslationViewModelsToDomainModelMapper heroTranslationViewModelsToDomainModelMapper
    )
    {
        this.heroGridViewModel = heroGridViewModel;
        this.heroNamesIntoFileExporter = heroNamesIntoFileExporter;
        this.heroTranslationViewModelsToDomainModelMapper = heroTranslationViewModelsToDomainModelMapper;
    }

    @Override
    public void handle() throws HandlerException
    {
        try {
            List<HeroTranslation> heroTranslations = this.heroTranslationViewModelsToDomainModelMapper
                    .mapToNewEntityList(this.heroGridViewModel.getHeroTranslations());

            this.heroNamesIntoFileExporter.export(
                    this.heroGridViewModel.getChosenHeroGridFile(),
                    heroTranslations
            );
        } catch (Dota2InvalidFileFormatException | IOException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
