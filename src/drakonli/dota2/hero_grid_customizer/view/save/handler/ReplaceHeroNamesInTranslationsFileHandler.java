package drakonli.dota2.hero_grid_customizer.view.save.handler;

import drakonli.component.file.exception.InvalidFileFormatException;
import drakonli.dota2.hero_grid_customizer.component.hero.names.file.replacer.HeroNamesInFileReplacer;
import drakonli.dota2.hero_grid_customizer.view.handler.HandlerException;
import drakonli.dota2.hero_grid_customizer.view_model.hero.grid.HeroGridViewModel;
import drakonli.dota2.hero_grid_customizer.view_model.hero.translation.HeroTranslationViewModelsToEntityConverter;

import java.io.IOException;

public class ReplaceHeroNamesInTranslationsFileHandler implements SaveButtonHandlerInterface
{
    private final HeroNamesInFileReplacer replacer;
    private final HeroTranslationViewModelsToEntityConverter converter;

    public ReplaceHeroNamesInTranslationsFileHandler(
            HeroNamesInFileReplacer replacer,
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
        } catch (InvalidFileFormatException | IOException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
