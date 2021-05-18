package no.MCH.config;

import java.io.File;
import java.io.IOException;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import no.MCH.constants.Constants;

public class Config {
	private MainConfig config;
	private ConfigurationNode node;
	
	public Config() {
		
	}
	
	public void load() {
        try {
            File file = Constants.CONFIG_PATH.resolve("mch_main.conf").toFile();
            ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder()
                    .setFile(file).build();

            if (!file.exists()) {
                file.createNewFile();
            }

            node = loader.load(
                    ConfigurationOptions.defaults()
                            .setHeader(MainConfig.HEADER)
                            .setShouldCopyDefaults(true)
            );
            config = node.getValue(TypeToken.of(MainConfig.class), new MainConfig());

            loader.save(node);
        } catch (IOException e) {
        	e.printStackTrace();
        } catch (ObjectMappingException e) {
			e.printStackTrace();
		}
    }

    public MainConfig get() {
        return this.config;
    }

}
