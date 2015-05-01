package me.vik1395.VanishBungee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.md_5.bungee.Util;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
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
	private static ArrayList<String> vanish = new ArrayList<String>();
	
	@Override
    public void onEnable()
    {
		getProxy().getPluginManager().registerListener(this, new PlayerChatListener());
		plugin = this;
		
		YamlGenerator yg = new YamlGenerator();
	    yg.setup();
	    
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
	
	public void vanish(CommandSender sender)
	{
		if(sender instanceof ProxiedPlayer)
		{
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(p.hasPermission("vanishbungee.vanish"))
			{
				if(vanish.contains(p.getName()))
				{
					for(int i = 0; i<vanish.size(); i++)
					{
						vanish.remove(i);
					}
				}
				
				else
				{
					vanish.add(p.getName());
				}
			}
			
			else
			{}
			
			/*String s = p.getServer().toString();
			System.out.println(s);
			
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("ForwardToPlayer");
			out.writeUTF(p.getName());
			out.writeUTF("Vanish");
			System.out.println("van");

			ByteArrayOutputStream msgbytes = new ByteArrayOutputStream();
			DataOutputStream msgout = new DataOutputStream(msgbytes);
			try
			{
				msgout.writeUTF("/vanish");
				msgout.writeShort(123);
				System.out.println("command");
			}
			catch(Exception e)
			{
				
			}
			System.out.println("presend");
			out.writeShort(msgbytes.toByteArray().length);
			out.write(msgbytes.toByteArray());
			System.out.println("postsend");*/
		}
	}
	
	public void glistCmd(CommandSender sender, String[] args)
	{
		if(sender instanceof ProxiedPlayer)
		{
			ProxiedPlayer p = (ProxiedPlayer)sender;
			if(p.hasPermission("vanishbungee.glist"))
	        {
				int hplt = 0; //Hidden Players Total
				for (ServerInfo server:ProxyServer.getInstance().getServers().values())
		        {
		            if (!server.canAccess(sender))
		            {
		                continue;
		            }

		            List<String> players = new ArrayList<>();
		            int hpl = 0; //Hidden Players
		            
		            for (ProxiedPlayer player:server.getPlayers())
		            {
		            	if(vanish.contains(player.getName()))
		            	{
		            		hpl++;
		            		hplt++;
		            	}
		            	
		            	else
		            	{
		            		players.add(player.getDisplayName());
		            	}
		            }
		            Collections.sort(players, String.CASE_INSENSITIVE_ORDER);
		            
		            sender.sendMessage(new TextComponent(ProxyServer.getInstance().getTranslation("command_list", server.getName(), server.getPlayers().size()-hpl, Util.format(players, ChatColor.RESET + ", "))));
		        }
				sender.sendMessage(new TextComponent(ProxyServer.getInstance().getTranslation("total_players", ProxyServer.getInstance().getOnlineCount()-hplt)));
	        }
			
			else if(p.hasPermission("vanishbungee.admin"))
			{
				for (ServerInfo server:ProxyServer.getInstance().getServers().values())
		        {
		            if (!server.canAccess(sender))
		            {
		                continue;
		            }

		            List<String> players = new ArrayList<>();
		            for (ProxiedPlayer player:server.getPlayers())
		            {
		                players.add(player.getDisplayName());
		            }
		            Collections.sort(players, String.CASE_INSENSITIVE_ORDER);
		            
		            sender.sendMessage(new TextComponent(ProxyServer.getInstance().getTranslation("command_list", server.getName(), server.getPlayers().size(), Util.format(players, ChatColor.RESET + ", "))));
		        }
				sender.sendMessage(new TextComponent(ProxyServer.getInstance().getTranslation("total_players", ProxyServer.getInstance().getOnlineCount())));
			}
			else
			{
				p.sendMessage(new TextComponent(ChatColor.RED + "You are not allowed to use this command."));
			}
		}
    	
    	else
    	{
    		for (ServerInfo server:ProxyServer.getInstance().getServers().values())
	        {
	            if (!server.canAccess(sender))
	            {
	                continue;
	            }

	            List<String> players = new ArrayList<>();
	            for (ProxiedPlayer player:server.getPlayers())
	            {
	                players.add(player.getDisplayName());
	            }
	            Collections.sort(players, String.CASE_INSENSITIVE_ORDER);
	            
	            sender.sendMessage(new TextComponent(ProxyServer.getInstance().getTranslation("command_list", server.getName(), server.getPlayers().size(), Util.format(players, ChatColor.RESET + ", "))));
	        }
			sender.sendMessage(new TextComponent(ProxyServer.getInstance().getTranslation("total_players", ProxyServer.getInstance().getOnlineCount())));
    	}
	}
	
	public void findCmd(CommandSender sender, String[] args)
	{
		if(sender instanceof ProxiedPlayer)
		{
			ProxiedPlayer p = (ProxiedPlayer)sender;
			
			if (args.length == 1)
	        {
	            sender.sendMessage(new TextComponent(ChatColor.RED + "Please follow this command by a user name"));
	        } 
			else if(p.hasPermission("vanishbungee.find"))
	        {
	            ProxiedPlayer player = ProxyServer.getInstance().getPlayer( args[1] );
	            if ( player == null || player.getServer() == null || vanish.contains(player.getName()))
	            {
	                sender.sendMessage(new TextComponent(ChatColor.RED + "That user is not online"));
	            }
	            else
	            {
	                sender.sendMessage(new TextComponent(ChatColor.BLUE + args[1] + " is online at " + player.getServer().getInfo().getName()));
	            }
	        }
			else if(p.hasPermission("vanishbungee.admin"))
	        {
	            ProxiedPlayer player = ProxyServer.getInstance().getPlayer( args[1] );
	            if ( player == null || player.getServer() == null )
	            {
	                sender.sendMessage(new TextComponent(ChatColor.RED + "That user is not online"));
	            } else
	            {
	                sender.sendMessage(new TextComponent(ChatColor.BLUE + args[1] + " is online at " + player.getServer().getInfo().getName()));
	            }
	        }
		}
		
		else
		{
			ProxiedPlayer player = ProxyServer.getInstance().getPlayer( args[0] );
            if ( player == null || player.getServer() == null )
            {
                sender.sendMessage(new TextComponent(ChatColor.RED + "That user is not online"));
            } else
            {
                sender.sendMessage(new TextComponent(ChatColor.BLUE + args[0] + " is online at " + player.getServer().getInfo().getName()));
            }
		}
	}
	
	public static Plugin plugin;
	public static String[] vcmds;
}
