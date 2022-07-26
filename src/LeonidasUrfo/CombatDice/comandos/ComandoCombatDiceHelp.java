package LeonidasUrfo.CombatDice.comandos;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import LeonidasUrfo.CombatDice.CombatDice;

public class ComandoCombatDiceHelp implements CommandExecutor {

	private CombatDice cd;
	public ComandoCombatDiceHelp(CombatDice cd) {
		this.cd = cd;
	}
	
	@Override
		public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) {
			if(!(sender instanceof Player)) { // si no es un jugador el que inicia el comando le dará una descripción de los jugadores y sus stats.
				Collection<? extends Player> OnlinePlayers = Bukkit.getOnlinePlayers(); 
				Object[] arrOnlinePlayers = OnlinePlayers.toArray(); //devuelve un array con el nombre de los jugadores
				
				for(int i = 0; i < arrOnlinePlayers.length; i++) {
					Bukkit.getConsoleSender().sendMessage(cd.nombre + ChatColor.WHITE + arrOnlinePlayers[i]);
				}
				return true;
			} else if (sender instanceof Player) { // muestra la guía de como utilizar CombatDice si es un jugador
				Player jugador = (Player) sender;
				 // /cd help te da información del comando
					jugador.sendMessage(cd.nombre + " " + cd.version + ChatColor.WHITE + "\n\n/CombatDice <player> <turno> - Permite instanciar un combate"
							+ "\n/CombatDice start - Los jugadores tienen que tirar este comando para ver quien empieza"
							+ "\n/CombatDice attack force/dexterity - Permite atacar en el turno del atacante con fuerza o destreza"
							+ "\n/CombatDice defense agility/constitution - Permite defenderse en el turno del defensor con agilidad o constitución"
							+ "\n/CombatDice runAway - Permite huir de un combate en curso");
					return true;
			} else {
				return false;
			}
		}
}
