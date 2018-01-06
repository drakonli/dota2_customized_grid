package drakonli.dota2.hero_grid_customizer.component.hero.names.file.filter;

import drakonli.component.filter.FilterInterface;

public class HeroTranslationsFileSkipLineFilter implements FilterInterface<String>
{
    private final String skipLine;

    public HeroTranslationsFileSkipLineFilter(String skipLine)
    {
        this.skipLine = skipLine;
    }

    @Override
    public boolean match(String line)
    {
        return line.contains(this.skipLine);
    }
}
