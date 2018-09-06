package drakonli.dota2.hero_grid_customizer.domain.services.dota2_translations_file;

import drakonli.dota2.hero_grid_customizer.domain.models.HeroGridCustomization;
import drakonli.jcomponents.ITxtLineEditor;

public interface ILineEditorByGridCustomizationFactory
{
    public ITxtLineEditor create(HeroGridCustomization heroGridCustomization);
}
