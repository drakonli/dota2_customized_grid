package drakonli.dota2.hero_grid_customizer.component.hero.names.file.matcher;

import drakonli.component.matcher.MatcherInterface;

public interface LineToEditMatcherInterface extends MatcherInterface<String>
{
    @Override
    Boolean match(String line);
}
