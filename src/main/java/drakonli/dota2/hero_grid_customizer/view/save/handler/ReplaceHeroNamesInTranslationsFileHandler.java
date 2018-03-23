package drakonli.dota2.hero_grid_customizer.view.save.handler;

import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.exception.Dota2InvalidFileFormatException;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.exporter.HeroNamesIntoFileExporter;
import drakonli.dota2.hero_grid_customizer.entity.HeroTranslation;
import drakonli.dota2.hero_grid_customizer.view.handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.view_model.hero.grid.HeroGridViewModel;
import drakonli.dota2.hero_grid_customizer.view_model.hero.translation.HeroTranslationViewModelsToEntityMapper;

import java.io.IOException;
import java.util.List;

public class ReplaceHeroNamesInTranslationsFileHandler implements SaveButtonHandlerInterface
{
    private final HeroGridViewModel heroGridViewModel;
    private final HeroNamesIntoFileExporter heroNamesIntoFileExporter;
    private final HeroTranslationViewModelsToEntityMapper heroTranslationViewModelsToEntityMapper;

    public ReplaceHeroNamesInTranslationsFileHandler(
            HeroGridViewModel heroGridViewModel,
            HeroNamesIntoFileExporter heroNamesIntoFileExporter,
            HeroTranslationViewModelsToEntityMapper heroTranslationViewModelsToEntityMapper
    )
    {
        this.heroGridViewModel = heroGridViewModel;
        this.heroNamesIntoFileExporter = heroNamesIntoFileExporter;
        this.heroTranslationViewModelsToEntityMapper = heroTranslationViewModelsToEntityMapper;
    }

    @Override
    public void handle() throws HandlerException
    {
        try {
            List<HeroTranslation> heroTranslations = this.heroTranslationViewModelsToEntityMapper
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
