package LeonidasUrfo.CombatDice;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import LeonidasUrfo.CombatDice.comandos.ComandoCombatDice;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

public class CombatDice extends JavaPlugin{
	
	PluginDescriptionFile pdf = getDescription();
	public String version = pdf.getVersion();
	public String nombre = ChatColor.RED + "["+ org.bukkit.ChatColor.BLUE + pdf.getName() + ChatColor.RED + "]";
	
	public void onEnable(){
		Bukkit.getConsoleSender().sendMessage(nombre + ChatColor.WHITE + " Cargando el plugin CombatDice en la versión " + version);
		fncRegistrarComandos();
		fncCarpetaConStats();
	}
	
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(nombre + ChatColor.WHITE + " Desactivando el plugin CombatDice en la versión " + version);
	}
	
	public void fncRegistrarComandos() {
		//this.getCommand("CombatDice").setExecutor(new ComandoCombatDiceHelp(this));
		//this.getCommand("cd").setExecutor(new ComandoCombatDiceHelp(this));
		this.getCommand("CombatDice").setExecutor(new ComandoCombatDice(this));
		this.getCommand("cd").setExecutor(new ComandoCombatDice(this));
	}
	
	public void fncCarpetaConStats() {
		File f = new File("plugins/CombatDice");
		File f2 = new File("plugins/CombatDice/PlayersStats.yaml");
		YamlConfiguration yf = YamlConfiguration.loadConfiguration(f2);
		if(!f.exists()) {
			f.mkdir();
		}
		if(!f2.exists()) {
			try {
				f2.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
