package me.vik1395.VanishBungee;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

/*

Author: Vik1395
Project: BungeeAuth

Copyright 2014

Licensed under Creative CommonsAttribution-ShareAlike 4.0 International Public License (the "License");
You may not use this file except in compliance with the License.

You may obtain a copy of the License at http://creativecommons.org/licenses/by-sa/4.0/legalcode

You may find an abridged version of the License at http://creativecommons.org/licenses/by-sa/4.0/
 */

public class YamlGenerator 
{
	public static Configuration config;
    public static ConfigurationProvider cProvider;
    public static File cFile;
    
	public void setup()
	{
		File cFolder = new File(Main.plugin.getDataFolder(),"");
		
		if (!cFolder.exists()) 
		{
	        cFolder.mkdir();
		}
		
		cFile = new File(Main.plugin.getDataFolder() + "/config.yml");
		
		if (!cFile.exists()) 
		{
	        save();
		}
		
		cProvider = ConfigurationProvider.getProvider(YamlConfiguration.class);
	    try 
	    {
	        config = cProvider.load(cFile);
	    } 
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	}
	
	public void save()
	{
		try 
        {
        	String file = ""
        			+ "Vanish Commands: /vanish;/v;/evanish;/ev \n"
        			+ "# Please use semicolon(;) to separate each vanish command.\n";
        	
            FileWriter fw = new FileWriter(cFile);
			BufferedWriter out = new BufferedWriter(fw);
            out.write(file);
            out.close();
            fw.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
	}
}
