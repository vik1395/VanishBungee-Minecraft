package me.vik1395.VanishBungee;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
/*

Author: Vik1395
Project: VanishBungee

Copyright 2015

Licensed under Creative CommonsAttribution-ShareAlike 4.0 International Public License (the "License");
You may not use this file except in compliance with the License.

You may obtain a copy of the License at http://creativecommons.org/licenses/by-sa/4.0/legalcode

You may find an abridged version of the License at http://creativecommons.org/licenses/by-sa/4.0/
 */

public class PlayerChatListener implements Listener 
{

    @EventHandler
    public void onPlayerChat(ChatEvent e) 
    {
		for(int i = 0; i<Main.vcmds.length;i++)
		{
			if(e.getMessage().equalsIgnoreCase(Main.vcmds[i]))
			{
				CommandSender sender = (CommandSender) e.getSender();
    			e.setCancelled(true);
    			
    			if(sender instanceof ProxiedPlayer)
    			{
    				ProxiedPlayer p = (ProxiedPlayer) sender;
    				if(p.hasPermission("vanishbungee.vanish"))
    				{
    					if(Main.vanish.contains(p.getName()))
    					{
    						for(int j = 0; j<Main.vanish.size(); j++)
    						{
    							if(Main.vanish.get(j).equalsIgnoreCase(p.getName()))
    							{
    								Main.vanish.remove(j);
    								p.sendMessage(new TextComponent(ChatColor.GREEN + "You have become visible!"));
    							}
    						}
    					}
    					
    					else
    					{
    						Main.vanish.add(p.getName());
    						p.sendMessage(new TextComponent(ChatColor.GREEN + "You have become hidden from glist and find commands!"));
    					}
    				}
    			}
			}
		}
    }
}

