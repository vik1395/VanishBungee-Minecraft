package me.vik1395.VanishBungee;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
/*

Author: Vik1395
Project: GList

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
    		
    		String[] args = null;
    		Main m = new Main();
    		if(e.getMessage().toLowerCase().startsWith("/vanish"))
    		{
    			CommandSender cm = (CommandSender) e.getSender();
    			m.vanish(cm);
    		}
    		
    		else if(e.getMessage().toLowerCase().startsWith("/glist"))
        	{
    			CommandSender cm = (CommandSender) e.getSender();
        		args = e.getMessage().split(" ");
        		m.glistCmd(cm, args);
        		e.setCancelled(true);
        	}
        	
        	else if(e.getMessage().toLowerCase().startsWith("/find"))
        	{
        		CommandSender cm = (CommandSender) e.getSender();
        		args = e.getMessage().split(" ");
        		m.findCmd(cm, args);
        		e.setCancelled(true);
        	}
    		
        	else
        	{
        		for(int i = 0; i<Main.vcmds.length;i++)
        		{
        			if(e.getMessage().startsWith(Main.vcmds[i]))
        			{
        				CommandSender cm = (CommandSender) e.getSender();
            			m.vanish(cm);
            			e.setCancelled(true);
        			}
        		}
        	}
    }
}

