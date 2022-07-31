	package LeonidasUrfo.CombatDice.comandos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import LeonidasUrfo.CombatDice.CombatDice;
import LeonidasUrfo.CombatDice.Jugadores.Jugadores;
import net.md_5.bungee.api.ChatColor;

public class ComandoCombatDice implements CommandExecutor{
	//AQUÍ IRA LA INSTANCIA DEL COMBATE
	CombatDice cd;
	ComandoCombatDiceHelp ccdh; 
	ComandoCombatDiceStart ccds; 
	ComandoCombatDiceAttack ccda; 
	ComandoCombatDiceDefense ccdd; 
	ComandoCombatDiceRunAway ccdr;
	static ArrayList<Jugadores> jugadorstat = new ArrayList<Jugadores>();
	Jugadores jugadorstat1 = null;
	Jugadores jugadorstat2 = null;
	int intJugador1;
	int intJugador2;

	public ComandoCombatDice(CombatDice cd){
		this.cd = cd;
	}
	public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) {
		Player jugador = (Player) sender;
		if(jugadorstat.isEmpty()) {
			jugadorstat.add(new Jugadores("test",false));
		}
		if(args.length > 0) {
			if(args[0].equalsIgnoreCase("version")) {
				jugador.sendMessage(cd.nombre + " " + cd.version);
				return true;
			} else if(args[0].equalsIgnoreCase("help")) {
				ccdh = new ComandoCombatDiceHelp(cd);
				ccdh.onCommand(sender, comando, label, args);
				return true;
			} else if(args[0].equalsIgnoreCase("start") && args.length == 3) {
				if(jugadorstat.stream().map((n) -> n.getNombre()).collect(Collectors.toList()).contains(args[1]) &&
						jugadorstat.stream().map((n) -> n.getNombre()).collect(Collectors.toList()).contains(jugador.getName())) {
				
					//si los jugadores existen, instancia una batalla, siempre y cuando el otro jugador acepte
					jugador.sendMessage("Ahora si los jugadores ya tienen ficha.");
					for(int i = 0; i < jugadorstat.size();i++) {
						if(jugadorstat.get(i).getNombre().equals(jugador.getName())) {
							intJugador1 = i;
							break;
						}
					}
					for(int i = 0; i < jugadorstat.size();i++) {
						if(jugadorstat.get(i).getNombre().equals(args[1])) {
							intJugador2 = i;
							break;
						}
					}
					if( jugadorstat.get(intJugador1).isenCombate() == false && jugadorstat.get(intJugador2).isenCombate() == false){
						ccds = new ComandoCombatDiceStart(cd, jugadorstat.get(intJugador1) );
						ccds.onCommand(sender, comando, label, args);
					}
					return true;
				} else {
					//crear jugador en el array en caso de no existir
					if(Bukkit.getOnlinePlayers().stream().map(n -> n.getDisplayName()).collect(Collectors.toList()).contains(args[1]) ) {
						jugadorstat.add(new Jugadores(args[1], false));
					} else {
						jugador.sendMessage( ChatColor.RED + "El jugador " + args[1] + " no existe" );
					}
					if(Bukkit.getOnlinePlayers().stream().map(n -> n.getDisplayName()).collect(Collectors.toList()).contains(jugador.getName()) &&
							!jugadorstat.stream().map(n -> n.getNombre()).collect(Collectors.toList()).contains(jugador.getName())) {
						jugadorstat.add(new Jugadores(jugador.getName(), false));
						onCommand(sender, comando, label, args);
					}
				}
				return true;
					
			} else if(args[0].equalsIgnoreCase("attack")) {
				ccda = new ComandoCombatDiceAttack(cd, jugadorstat);
				ccda.onCommand(sender, comando, label, args);
				System.out.println(jugadorstat.get(2).getNumeroAtaque());
				System.out.println(jugadorstat.get(1).getNumeroAtaque());
				return true;
			} else if(args[0].equalsIgnoreCase("defense")) {
				ccdd = new ComandoCombatDiceDefense(cd);
				ccdd.onCommand(sender, comando, label, args);
				return true;
			} else if(args[0].equalsIgnoreCase("runaway")) {
				ccdr = new ComandoCombatDiceRunAway(cd, jugadorstat1, jugadorstat2);
				ccdr.onCommand(sender, comando, label, args);
				return true;
			} else if(args[0].equalsIgnoreCase("yes") ){
				for(int i = 0; i < jugadorstat.size();i++) {
					if(jugadorstat.get(i).getNombre().equals(args[2])) {
						intJugador1 = i;
						break;
					}
				}
				for(int i = 0; i < jugadorstat.size();i++) {
					if(jugadorstat.get(i).getNombre().equals(args[1])) {
						intJugador2 = i;
						break;
					}
				}
				if(jugadorstat.get(intJugador2).isenCombate() == false) {
					//establece los combatientes en combate
					jugadorstat.get(intJugador1).setenCombate(true); 
					jugadorstat.get(intJugador2).setenCombate(true); 
					//agrega los turnos del combate a los jugadores
					jugadorstat.get(intJugador1).setMaxTurno(Integer.parseInt(args[3])); 
					jugadorstat.get(intJugador2).setMaxTurno(Integer.parseInt(args[3]));
					//indica el nombre del contrincante en cada jugador
					jugadorstat.get(intJugador1).setContrincante(jugadorstat.get(intJugador2).getNombre());
					jugadorstat.get(intJugador2).setContrincante(jugadorstat.get(intJugador1).getNombre());
					
					Bukkit.broadcastMessage(ChatColor.BLUE + "####################################################\nSe ha iniciado el combate entre " 
							+ ChatColor.YELLOW + jugadorstat.get(intJugador1).getNombre() + ChatColor.BLUE + " y " + ChatColor.YELLOW + jugadorstat.get(intJugador2).getNombre()
							+ ChatColor.BLUE + " a " + args[3] + " golpes");
					Bukkit.broadcastMessage("\n" + ChatColor.YELLOW + jugadorstat.get(intJugador1).getNombre() + ChatColor.BLUE +" 0 - 0 " + ChatColor.YELLOW + jugadorstat.get(intJugador2).getNombre());
					
					//AQUI IRÁ LOS DADOS PARA ELEGIR QUIEN COMIENZA
					// (Max - Min + 1) + min == número random entre 20 y 1
					int tiradaJugador1 = new Random().nextInt(20 - 0 + 1) + 0;
					int tiradaJugador2 = new Random().nextInt(20 - 0 + 1) + 0;
					
					Bukkit.broadcastMessage( ChatColor.BOLD + jugadorstat.get(intJugador1).getNombre() + " ha tirado un " + ChatColor.RED + tiradaJugador1);
					Bukkit.broadcastMessage( ChatColor.BOLD + jugadorstat.get(intJugador2).getNombre() + " ha tirado un " + ChatColor.RED + tiradaJugador2);
					
					if(tiradaJugador1 > tiradaJugador2) {
						Bukkit.broadcastMessage( ChatColor.BOLD + "\nEmpieza el jugador " + jugadorstat.get(intJugador1).getNombre());
						jugadorstat.get(intJugador1).setMiTurno(true);
					} else if(tiradaJugador2 > tiradaJugador1 ) {
						Bukkit.broadcastMessage( ChatColor.BOLD + "\nEmpieza el jugador " + jugadorstat.get(intJugador2).getNombre());
						jugadorstat.get(intJugador2).setMiTurno(true);
					} else {
						tiradaJugador1 = new Random().nextInt(20 - 0 + 1) + 0;
						tiradaJugador2 = new Random().nextInt(20 - 0 + 1) + 0;
						
						Bukkit.broadcastMessage( ChatColor.BOLD + jugadorstat.get(intJugador1).getNombre() + " ha tirado un " + ChatColor.RED + tiradaJugador1);
						Bukkit.broadcastMessage( ChatColor.BOLD + jugadorstat.get(intJugador2).getNombre() + " ha tirado un " + ChatColor.RED + tiradaJugador2);
						if(tiradaJugador1 > tiradaJugador2) {
							Bukkit.broadcastMessage( ChatColor.BOLD + "\nEmpieza el jugador " + jugadorstat.get(intJugador1).getNombre());
							jugadorstat.get(intJugador1).setMiTurno(true);
						} else if(tiradaJugador2 > tiradaJugador1 ) {
							Bukkit.broadcastMessage( ChatColor.BOLD + "\nEmpieza el jugador " + jugadorstat.get(intJugador2).getNombre());
							jugadorstat.get(intJugador2).setMiTurno(true);
						}
					}
				}
				return true;
			} else if(args[0].equalsIgnoreCase("no")){
				for(int i = 0; i < jugadorstat.size();i++) {
					if(jugadorstat.get(i).getNombre().equals(args[2])) {
						intJugador1 = i;
						break;
					}
				}
				for(int i = 0; i < jugadorstat.size();i++) {
					if(jugadorstat.get(i).getNombre().equals(args[1])) {
						intJugador2 = i;
						break;
					}
				}
				if(jugadorstat.get(intJugador2).isenCombate() == false) {
					jugadorstat.get(intJugador1).setenCombate(false);
					Bukkit.broadcastMessage( ChatColor.BLUE + "No se inició el combate");					
				}
				return true;
			} else if(args[0].equalsIgnoreCase("info")) {
				jugadorstat.forEach( (n) -> n.getAllInfo(jugador)); 
				return true;
			} else {
				jugador.sendMessage(cd.nombre + " El comando no existe");
				return false;
			}
		} else {
			jugador.sendMessage(cd.nombre + " El comando  no existe");
			return false;
		}	
	}
}
