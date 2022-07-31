package LeonidasUrfo.CombatDice.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import LeonidasUrfo.CombatDice.CombatDice;

public class ComandoCombatDiceDefense {
	
	CombatDice cd;
	public ComandoCombatDiceDefense(CombatDice cd) {
		this.cd = cd;
	}
	
	public void onCommand(CommandSender sender, Command comando, String label, String[] args) {
		
		//AQUÍ VA EL COMANDO DE DEFENSA
		
		//IDENTIFICAR SI EL JUGADOR CONTRINCANTE HA TIRADO LOS DADOS
			// SI EL JUGADOR HA TIRADO DADOS EL DEFENSOR TIRA DADOS Y CAMBIA EL ESTADO DEL DEFENSOR A SU TURNO
				// AGREGAR AL MARCADOR 
			//IDENTIFICAR SI EL COMBATE HA SIDO UN EMPATE Y ESTABLECER EL TURNO DEL ATACANTE A TRUE EN CASO DE SER CIERTO
		// SI NO LE ENVÍA UN MENSAJE DICIENDO QUE ESPERE SU TURNO
	}
	
}
