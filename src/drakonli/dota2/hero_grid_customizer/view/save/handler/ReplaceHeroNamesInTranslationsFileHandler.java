package drakonli.dota2.hero_grid_customizer.view.save.handler;

import drakonli.dota2.hero_grid_customizer.component.hero.names.file.exception.Dota2InvalidFileFormatException;
import drakonli.dota2.hero_grid_customizer.component.hero.names.file.exporter.HeroNamesIntoFileExporter;
import drakonli.dota2.hero_grid_customizer.view.handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.view_model.hero.grid.HeroGridViewModel;
import drakonli.dota2.hero_grid_customizer.view_model.hero.translation.HeroTranslationViewModelsToEntityConverter;

import java.io.IOException;

public class ReplaceHeroNamesInTranslationsFileHandler implements SaveButtonHandlerInterface
{
    private final HeroNamesIntoFileExporter replacer;
    private final HeroTranslationViewModelsToEntityConverter converter;

    public ReplaceHeroNamesInTranslationsFileHandler(
            HeroNamesIntoFileExporter replacer,
            HeroTranslationViewModelsToEntityConverter converter
    )
    {
        this.replacer = replacer;
        this.converter = converter;
    }

    @Override
    public void handle(HeroGridViewModel heroGridViewModel) throws HandlerException
    {
        try {
            this.replacer.replaceHeroNames(
                    heroGridViewModel.getChosenHeroGridFile(),
                    converter.convert(heroGridViewModel.getHeroTranslations())
            );
        } catch (Dota2InvalidFileFormatException | IOException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
