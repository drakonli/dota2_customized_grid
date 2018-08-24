package drakonli.dota2.hero_grid_customizer.application.action.hero_grid_customization.import_customization.latest_save;

import drakonli.dota2.hero_grid_customizer.application.action.ApplicationActionException;
import drakonli.dota2.hero_grid_customizer.application.view_model.models.HeroNameCustomizationVM;
import drakonli.dota2.hero_grid_customizer.domain.component.hero.names.file.storage.StorageException;

import java.util.List;

public interface IImportHeroGridCustomizationByLatestSaveAction
{
    public void importCustomization(List<HeroNameCustomizationVM> heroNameCustomizationVMListToImportInto)
            throws StorageException, LastVersionOfHeroGridCustomizationIsEmptyException, ApplicationActionException;
}
