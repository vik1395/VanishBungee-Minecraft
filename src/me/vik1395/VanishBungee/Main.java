package me.vik1395.VanishBungee;

import java.util.ArrayList;
import net.md_5.bungee.api.plugin.Plugin;

/*

Author: Vik1395
Project: VanishBungee

Copyright 2015

Licensed under Creative CommonsAttribution-ShareAlike 4.0 International Public License (the "License");
You may not use this file except in compliance with the License.

You may obtain a copy of the License at http://creativecommons.org/licenses/by-sa/4.0/legalcode

You may find an abridged version of the License at http://creativecommons.org/licenses/by-sa/4.0/
 */

public class Main extends Plugin
{
	//private List<String> vanish = new ArrayList<String>();
	protected static ArrayList<String> vanish = new ArrayList<String>();
	
	@Override
    public void onEnable()
    {
		plugin = this;
		getProxy().getPluginManager().registerListener(this, new PlayerChatListener());
		getProxy().getPluginManager().registerCommand(this, new Glist());
		getProxy().getPluginManager().registerCommand(this, new Find());
		
		YamlGenerator yg = new YamlGenerator();
	    yg.saveDefaultConfig();
	    
	    try 
		{
		    String vancmd = YamlGenerator.config.getString("Vanish Commands");
		    
		    vcmds = vancmd.split(";");
		} 
		catch(Exception ex) 
		{
		    System.err.println("[VanishBungee] Your Config file is missing or broken. Please reset the file.");
		    ex.printStackTrace();
		}
	    
	    getLogger().info("VanishBungee has successfully started!");
		getLogger().info("Created by Vik1395");
    }
	
	public static Plugin plugin;
	public static String[] vcmds;
}
