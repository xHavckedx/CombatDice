package LeonidasUrfo.CombatDice.comandos;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import LeonidasUrfo.CombatDice.CombatDice;
import LeonidasUrfo.CombatDice.Jugadores.Jugadores;
import net.md_5.bungee.api.ChatColor;

public class ComandoCombatDiceAttack {

	CombatDice cd;
	ArrayList<Jugadores> jugadorArray;
	int intJugador1;

	public ComandoCombatDiceAttack(CombatDice cd, ArrayList<Jugadores> jugadorArray) {
		this.cd = cd;
		this.jugadorArray = jugadorArray;
	}

	public void onCommand(CommandSender sender, Command comando, String label, String[] args) {
		Player jugador1 = (Player) sender;
		// AQUÍ IRÁ EL COMANDO DE ATAQUE
		for (int i = 0; i < jugadorArray.size(); i++) {
			if (jugadorArray.get(i).getNombre().equals(jugador1.getName())) {
				intJugador1 = i;
				break;
			}
		}
		if (jugadorArray.get(intJugador1).isMiTurno()) {
			if (args[1].equals("fuerza") || args[1].equals("f")) {

				int tiradaJugador1 = new Random().nextInt(20 - 0 + 1) + 0;
				int tiradaJugador1Final = tiradaJugador1 + jugadorArray.get(intJugador1).getFuerza();
				Bukkit.broadcastMessage(ChatColor.BOLD + jugadorArray.get(intJugador1).getNombre() + " ha tirado un "
						+ ChatColor.RED + tiradaJugador1 + ChatColor.BOLD + " + ("
						+ jugadorArray.get(intJugador1).getFuerza() + ") " + ChatColor.RED + tiradaJugador1Final);
				jugadorArray.get(intJugador1).setNumeroAtaque(tiradaJugador1Final);
				jugadorArray.get(intJugador1).setMiTurno(false);

			} else if (args[1].equals("destreza") || args[1].equals("ds")) {

				int tiradaJugador1 = new Random().nextInt(20 - 0 + 1) + 0;
				int tiradaJugador1Final = tiradaJugador1 + jugadorArray.get(intJugador1).getDestreza();
				Bukkit.broadcastMessage(ChatColor.BOLD + jugadorArray.get(intJugador1).getNombre() + " ha tirado un "
						+ ChatColor.RED + tiradaJugador1 + ChatColor.BOLD + " + ("
						+ jugadorArray.get(intJugador1).getDestreza() + ") " + ChatColor.RED + tiradaJugador1Final);
				jugadorArray.get(intJugador1).setNumeroAtaque(tiradaJugador1Final);
				jugadorArray.get(intJugador1).setMiTurno(false);

			}
		}
	}
}
