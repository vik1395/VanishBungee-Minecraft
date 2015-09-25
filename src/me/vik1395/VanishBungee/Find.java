package me.vik1395.VanishBungee;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/*

Author: Vik1395
Project: VanishBungee

Copyright 2015

Licensed under Creative CommonsAttribution-ShareAlike 4.0 International Public License (the "License");
You may not use this file except in compliance with the License.

You may obtain a copy of the License at http://creativecommons.org/licenses/by-sa/4.0/legalcode

You may find an abridged version of the License at http://creativecommons.org/licenses/by-sa/4.0/
 */

public class Find extends Command
{
	public Find()
	{
		super("find");
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) 
	{
		if(sender instanceof ProxiedPlayer)
		{
			ProxiedPlayer p = (ProxiedPlayer)sender;
			
			if (args.length == 0)
	        {
	            sender.sendMessage(new TextComponent(ChatColor.RED + "Please follow this command by a user name"));
	        } 
			else if(p.hasPermission("vanishbungee.admin"))
	        {
	            ProxiedPlayer player = ProxyServer.getInstance().getPlayer(args[0]);
	            if ( player == null || player.getServer() == null )
	            {
	                sender.sendMessage(new TextComponent(ChatColor.RED + "That user is not online"));
	            }
	            else if(Main.vanish.contains(player.getName()))
	            {
	            	sender.sendMessage(new TextComponent(ChatColor.BLUE + args[0] + " is online and " + ChatColor.RED + "vanished " + ChatColor.BLUE + "at " + player.getServer().getInfo().getName()));
	            }
	            else
	            {
	                sender.sendMessage(new TextComponent(ChatColor.BLUE + args[0] + " is online at " + player.getServer().getInfo().getName()));
	            }
	        }
			else if(p.hasPermission("vanishbungee.find"))
	        {
	            ProxiedPlayer player = ProxyServer.getInstance().getPlayer( args[0] );
	            if ( player == null || player.getServer() == null || Main.vanish.contains(player.getName()))
	            {
	                sender.sendMessage(new TextComponent(ChatColor.RED + "That user is not online"));
	            }
	            else
	            {
	                sender.sendMessage(new TextComponent(ChatColor.BLUE + args[0] + " is online at " + player.getServer().getInfo().getName()));
	            }
	        }
		}
		
		else
		{
			ProxiedPlayer player = ProxyServer.getInstance().getPlayer(args[0]);
			if ( player == null || player.getServer() == null )
            {
                sender.sendMessage(new TextComponent(ChatColor.RED + "That user is not online"));
            }
            else if(Main.vanish.contains(player.getName()))
            {
            	sender.sendMessage(new TextComponent(ChatColor.BLUE + args[0] + " is online and " + ChatColor.RED + "vanished " + ChatColor.BLUE + "at " + player.getServer().getInfo().getName()));
            }
            else
            {
                sender.sendMessage(new TextComponent(ChatColor.BLUE + args[0] + " is online at " + player.getServer().getInfo().getName()));
            }
		}
	}
}
