package LeonidasUrfo.CombatDice.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import LeonidasUrfo.CombatDice.CombatDice;
import LeonidasUrfo.CombatDice.Jugadores.Jugadores;

public class ComandoCombatDiceRunAway {

	CombatDice cd;
	Jugadores jugador1;
	Jugadores jugador2;
	public ComandoCombatDiceRunAway(CombatDice cd, Jugadores jugador1, Jugadores jugador2) {
		this.cd = cd;
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
	}
	
	public void onCommand(CommandSender sender, Command comando, String label, String[] args) {
		jugador1.setenCombate(false);
		jugador2.setenCombate(false);		
	}
	//AQUÍ VA EL COMANDO RUN AWAY
}
