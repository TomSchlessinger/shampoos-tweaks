package myshampooisdrunk.stackables.config;

import com.mojang.datafixers.util.Pair;

import java.util.List;
import java.util.ArrayList;

public class ModConfigProvider implements SimpleConfig.DefaultConfig{
    private String configContents = "";

    public List<Pair> getConfigList(){
        return configsList;
    }
    private final List<Pair> configsList = new ArrayList<>();

    public void addKeyValuePair(Pair<String, ?> keyValuePair, String comment) {
        configsList.add(keyValuePair);
        configContents += keyValuePair.getFirst() + "=" + keyValuePair.getSecond() + " #" + comment + " | default: " + keyValuePair.getSecond() + "\n";
    }

    @Override
    public String get(String namespace) {
        return configContents;
    }
}
