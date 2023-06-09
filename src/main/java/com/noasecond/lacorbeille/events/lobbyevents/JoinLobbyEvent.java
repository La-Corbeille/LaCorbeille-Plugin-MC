package com.noasecond.lacorbeille.events.lobbyevents;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class JoinLobbyEvent implements Listener {

    private final String WORLD_NAME = "lobby";

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (!player.getWorld().getName().equalsIgnoreCase(WORLD_NAME))
            return;
        if (player.getInventory().contains(Material.WRITTEN_BOOK))
            return;
        ItemStack welcomeBook = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) welcomeBook.getItemMeta();
        bookMeta.setTitle("Bienvenue !");
        bookMeta.setAuthor("Yoru_Kiwi");
        bookMeta.addPage("Bienvenue dans,\n" +
                                "§5§k!!!§5 §3La Corbeille§5§k !!!§0\n" +
                                "\n" +
                                "Sommaire :\n" +
                                "contributeurs ---- p1\n" +
                                "infos ----------- p2\n" +
                                "commandes ------ p3\n" +
                                "règles ---------- p4");
        welcomeBook.setItemMeta(bookMeta);
        if (player.getInventory().getItem(8) == null)
            player.getInventory().setItem(8, welcomeBook);
        else
            player.getInventory().addItem(welcomeBook);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        ItemStack item = e.getItemDrop().getItemStack();
        if (!item.getType().equals(Material.WRITTEN_BOOK))
            return;
        e.setCancelled(true);
    }
}

