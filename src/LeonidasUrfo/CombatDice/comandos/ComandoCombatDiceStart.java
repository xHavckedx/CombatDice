package LeonidasUrfo.CombatDice.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import LeonidasUrfo.CombatDice.CombatDice;
import LeonidasUrfo.CombatDice.Jugadores.Jugadores;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class ComandoCombatDiceStart {
	CombatDice cd;
	Jugadores jugador1;

	public ComandoCombatDiceStart(CombatDice cd, Jugadores jugador) {
		this.cd = cd;
		this.jugador1 = jugador;
	}

	public void onCommand(CommandSender sender, Command comando, String label, String[] args) {
		// AQUI IRÁ LA PETICIÓN DE COMBATE.
		Player jugador = (Player) sender;
		Player jugador2 = Bukkit.getPlayer(args[1]);
		if (args[2].matches("[+-]?\\d*(\\.\\d+)?")) {
			int golpes = Integer.parseInt(args[2]);
			jugador.sendMessage(ChatColor.BLUE + "Enviando petición de combate a: " + jugador2.getName()
					+ " con un número de " + golpes + " golpes ...");

			// Mensaje al contrincante para empezar el combate
			TextComponent Mensaje = new TextComponent(
					"Quieres aceptar el combate de " + jugador.getName() + " a " + golpes + " golpes? ");
			TextComponent Aceptar = new TextComponent("Aceptar ");
			TextComponent Rechazar = new TextComponent("Rechazar");
			// colores
			Mensaje.setColor(ChatColor.YELLOW);
			Aceptar.setColor(ChatColor.GREEN);
			Rechazar.setColor(ChatColor.RED);
			// eventos el comando cd yes devuelve el jugador2 el jugador1 y el número de
			// turnos
			// evento el comando cd no devuelve el jugador 1 y le quita el estado de pelea
			Aceptar.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,
					"/cd yes " + jugador2.getName() + " " + jugador.getName() + " " + Integer.toString(golpes)));
			Rechazar.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,
					"/cd no " + jugador2.getName() + " " + jugador.getName()));
			jugador2.spigot().sendMessage(Mensaje, Aceptar, Rechazar);
		} else {
			jugador.sendMessage("El argumento de golpes es incorrecto, por favor indica un valor numérico");
		}

	}
}
